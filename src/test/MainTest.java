package test;

import pracownicy.Pracownik;
import pracownicy.PracownikEtatowy;
import pracownicy.PracownikGodzinowy;
import serializacja.SerializatorZwykly;
import sklepy.*;
import strategie.strategia2za1.Rabat2za1Analiza;
import strategie.strategiagazetki.RabatGazetkowyWielosztukiAnaliza;
import strategie.strategiagazetki.StrategiaGazetki;
import strategie.strategianadmiarowa.RabatOdNadmiarowychTowarow;
import strategie.strategianadzial.RabatNaDzial;
import strategie.strategiapromocji.RabatNaDzienTygodnia;
import strategie.strategiapromocji.RabatNaSumeZAnaliza;
import strategie.strategiapromocji.StrategiaPromocji;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainTest {
    static Random r = new Random();
    static int magicNum = 10;

    public static void main(String[] args) {
        List<Sklep> sklepy;

        File serializowaneSklepy = new File("serializowanesklepy/sklepy.ser");
        SerializatorZwykly<Sklep> serializator = new SerializatorZwykly<>("serializowanesklepy/", "sklepy");
        // Plik może istnieć, ale jeśli jest pusty, to wyrzuci EOFException
        if (serializowaneSklepy.length() != 0L) {
            // sklepy są zserializowane, trzeba je zdeserializować
            System.out.println("Wykryto zserializowane sklepy, deserializowanie...");
            sklepy = serializator.deserializuj();
            System.out.println("Zdeserializowane sklepy:");
            for(Sklep sklep : sklepy) {
                System.out.println(sklep.idSklepu());
            }
            System.out.println();
        } else {
            // nie ma zserializowanych sklepów, trzeba wygenerować je od zera
            System.out.println("Nie wykryto zserializowanych sklepów, zostaną wygenerowane nowe");
            sklepy = Arrays.asList(wygenerujSklepy());
        }

        testujStrategiePromocji(sklepy);

        testujStrategieDlaSklepuBudowniczego((SklepBudowniczy) sklepy.get(1));

        testujFirmeDostawcza();

        testujStrategieGazetki();

        // Serializujemy sklepy, żeby zapisać zmiany do następnego uruchomienia programu
        System.out.println("Serializowanie sklepów...");
        serializator.serializuj(sklepy);

    }

    private static List<Produkt> getListeRandomowychProduktow(int ilosc) {

        List<String> nazwyProduktow = new ArrayList<>();

        for (int i = 0; i < ilosc; i++){
            nazwyProduktow.add("Produkt" + i);
        }

        List<Produkt> produkty = new ArrayList<>();
        for (int i = 0; i < ilosc; i++) {

            BigDecimal bD = BigDecimal.valueOf(r.nextDouble() * 10).setScale(2, RoundingMode.DOWN);

            produkty.add(
                    new Produkt(nazwyProduktow.get(r.nextInt(nazwyProduktow.size())),
                            bD.doubleValue()));
        }
        return produkty;
    }

    // Jak poznamy testy jednostkowe to napiszemy jakieś porządniejsze testy :)
    private static void testujFirmeDostawcza(){

        System.out.println("Firma Dostawcza");
        Sklep sklep = new Lidl("ul. Akademicka 6, Wrocław", "lidl.pl", true);

        FirmaDostawcza firmaDostawcza = new FirmaDostawcza("Fedex", 2, DayOfWeek.FRIDAY, "Polska");

        Produkt produkt = new Produkt("TestProdukt", 2.00);

        if (sklep.sprzedajProdukt(produkt, 2) == null){
            firmaDostawcza.dostarczProdukty(sklep, produkt, 2);
        }
        sklep.wyswietlOferteSklepu();

        firmaDostawcza.getHistoriaZamowien().deserializuj().forEach(System.out::println);

    }

    private static void testujStrategieGazetki(){

        Supermarket supermarket = new Biedronka("ul. Wittiga 12, Wrocław", "biedronka.pl", true);

        Produkt produkt = new Produkt("TestProdukt", 2.00);

        supermarket.aktualizujIloscProduktow(produkt, 20);

        // Średnia z (1 + 2 + 3 + 4 + 5) / 5 = 3
        for (int i = 0; i < 5; i++){
            System.out.println(supermarket.sprzedajProdukt(produkt, i));
        }

        supermarket.dodajDoGazetki(produkt);

        StrategiaGazetki strategiaGazetki = new RabatGazetkowyWielosztukiAnaliza(supermarket);

        supermarket.otworzGazetke(strategiaGazetki);

        System.out.println(supermarket.sprzedajProdukt(produkt, 4));

        System.out.println(supermarket.sprzedajProdukt(produkt, 1));

    }

    private static void testujStrategieDlaSklepuBudowniczego(SklepBudowniczy sklepBudowniczy){

        String dzial = "Kuchnia";
        Produkt produkt = new Produkt("Kuchenka", 1000.00);
        System.out.println("Testowanie: " + RabatNaDzial.class.getSimpleName());
        sklepBudowniczy.dodajDzial(dzial);
        sklepBudowniczy.dodajProduktDoDzialu(produkt, dzial);
        sklepBudowniczy.aktualizujIloscProduktow(produkt, 1002);

        StrategiaPromocji strategia = new RabatNaDzial(dzial, 0.05, sklepBudowniczy);

        sklepBudowniczy.zmienStrategie(strategia);

        System.out.println(sklepBudowniczy.sprzedajProdukt(produkt, 4));
    }

    private static void testujStrategiePromocji(List<Sklep> sklepy){

        int iloscProduktow = 5;
        for (Sklep sklep: sklepy){

            System.out.println("Adres Sprawdzanego Sklepu: " + sklep.getAdres());

            List<Produkt> produkty = getListeRandomowychProduktow(magicNum);

            produkty.forEach(x -> sklep.aktualizujIloscProduktow(x, r.nextInt(2 * magicNum)));

            System.out.println(sklep.sprzedajProdukt(produkty.get(r.nextInt(magicNum)), 2));

            StrategiaPromocji strategia1 = new RabatNaDzienTygodnia(DayOfWeek.SUNDAY, 0.05);

            sklep.zmienStrategie(strategia1);

            System.out.println(sklep.sprzedajProdukt(produkty.get(r.nextInt(magicNum)), iloscProduktow++));

            StrategiaPromocji strategia2 = new RabatNaSumeZAnaliza(sklep, 0.1);

            sklep.zmienStrategie(strategia2);

            System.out.println(sklep.sprzedajProdukt(produkty.get(r.nextInt(magicNum)), iloscProduktow++));

            StrategiaPromocji strategia3 = new Rabat2za1Analiza(sklep, 4);

            sklep.zmienStrategie(strategia3);

            System.out.println(sklep.sprzedajProdukt(produkty.get(r.nextInt(magicNum)), iloscProduktow++));

            StrategiaPromocji strategia4 = new RabatOdNadmiarowychTowarow(sklep);

            sklep.zmienStrategie(strategia4);

            System.out.println(sklep.sprzedajProdukt(produkty.get(r.nextInt(magicNum)), iloscProduktow++));

            sklep
                    .getHistoriaTransakcji()
                    .deserializuj()
                    .forEach(System.out::println);

            System.out.println("\n");
        }

    }

    private static Sklep[] wygenerujSklepy() {
        final Sklep[] sklepy = new Sklep[7];
        sklepy[0] = new Biedronka("ul. Wittiga 12, Wrocław", "biedronka.pl", true);
        sklepy[1] = new Castorama("ul. Wschodnia 1, Wrocław", "castorama.pl", false);
        sklepy[2] = new Ikea("ul. Poznańska 9, Wrocław", "ikea.pl", true, true);
        sklepy[3] = new LeroyMerlin("ul. Lerłowska 422, Poznań", "leroymerlin.pl", true);
        sklepy[4] = new Lidl("ul. Akademicka 6, Wrocław", "lidl.pl", true);
        sklepy[5] = new Vox("ul. Meblowa 44, Gdańsk", "vox.pl", true, true);
        sklepy[6] = new Zabka(false, "ul. Płazowa 4, Wrocław", "zabka.pl", true);

        final Pracownik[] pracownicy = wygenerujPracownikow();

        sklepy[0].zrekrutuj(pracownicy[0]);
        sklepy[0].zrekrutuj(pracownicy[10]);
        sklepy[1].zrekrutuj(pracownicy[1]);
        sklepy[1].zrekrutuj(pracownicy[11]);
        sklepy[2].zrekrutuj(pracownicy[2]);
        sklepy[2].zrekrutuj(pracownicy[12]);
        sklepy[3].zrekrutuj(pracownicy[3]);
        sklepy[3].zrekrutuj(pracownicy[13]);
        sklepy[4].zrekrutuj(pracownicy[4]);
        sklepy[4].zrekrutuj(pracownicy[14]);
        sklepy[5].zrekrutuj(pracownicy[5]);
        sklepy[5].zrekrutuj(pracownicy[15]);
        sklepy[6].zrekrutuj(pracownicy[6]);
        sklepy[6].zrekrutuj(pracownicy[16]);
        return sklepy;
    }

    private static Pracownik[] wygenerujPracownikow() {
        Pracownik[] pracownicy = new Pracownik[20];
        pracownicy[0] = new PracownikEtatowy("Grzegorz", "Świąder", losowaData(), losowaPensja());
        pracownicy[1] = new PracownikEtatowy("Małgorzata", "Nowak", losowaData(), losowaPensja());
        pracownicy[2] = new PracownikEtatowy("Anna", "Kowalska", losowaData(), losowaPensja());
        pracownicy[3] = new PracownikEtatowy("Paweł", "Owsiak", losowaData(), losowaPensja());
        pracownicy[4] = new PracownikEtatowy("Konrad", "Łączny", losowaData(), losowaPensja());
        pracownicy[5] = new PracownikEtatowy("Zuzanna", "Szczupak", losowaData(), losowaPensja());
        pracownicy[6] = new PracownikEtatowy("Mikołaj", "Święty", losowaData(), losowaPensja());
        pracownicy[7] = new PracownikEtatowy("Patryk", "Wysocki", losowaData(), losowaPensja());
        pracownicy[8] = new PracownikEtatowy("Aniela", "Różnowartościowa", losowaData(), losowaPensja());
        pracownicy[9] = new PracownikEtatowy("Bartosz", "Monotoniczny", losowaData(), losowaPensja());
        pracownicy[10] = new PracownikGodzinowy("Wiktor", "Mętny", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[11] = new PracownikGodzinowy("Rupert", "Pomysłowy", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[12] = new PracownikGodzinowy("Bartosz", "Król", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[13] = new PracownikGodzinowy("Lech", "Poznański", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[14] = new PracownikGodzinowy("Fryderyk", "Kwaśnioch", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[15] = new PracownikGodzinowy("Artur", "Śmieszny", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[16] = new PracownikGodzinowy("Robert", "Kubitza", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[17] = new PracownikGodzinowy("Amelia", "Winna", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[18] = new PracownikGodzinowy("Konstancja", "Sokolska", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[19] = new PracownikGodzinowy("Dominika", "Kulczyk", losowaData(), losoweWynagrodzenieGodzinowe());
        return pracownicy;
    }

    private static LocalDate losowaData() {
        return LocalDate.of(r.nextInt(13) + 2008, r.nextInt(12) + 1, r.nextInt(28) + 1);
    }

    private static int losowaPensja() {
        return r.nextInt(3801) + 2400;
    }

    private static double losoweWynagrodzenieGodzinowe() {
        return 18.7 + (45 - 18.7) * r.nextDouble();
    }

}

