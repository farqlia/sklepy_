package test;

import pracownicy.Pracownik;
import pracownicy.PracownikEtatowy;
import pracownicy.PracownikGodzinowy;
import serializacja.SerializatorSklepow;
import sklepy.*;
import strategie.strategiaanalizy.SredniaSumaAnaliza;
import strategie.strategiapromocji.RabatNaDzienTygodnia;
import strategie.strategiapromocji.RabatNaSumeZAnaliza;
import strategie.strategiapromocji.StrategiaPromocji;

import java.io.File;
import java.math.BigDecimal;

import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class MainTest {
    static Random r = new Random();
    static int magicNum = 10;

    public static void main(String[] args) {
        Sklep[] sklepy;

        File serializowaneSklepy = new File("sklepy.ser");
        SerializatorSklepow serializator = new SerializatorSklepow();
        if (serializowaneSklepy.exists()) {
            // sklepy są zserializowane, trzeba je zdeserializować
            System.out.println("Wykryto zserializowane sklepy, deserializowanie...");
            sklepy = serializator.deserializujSklepy();
            System.out.println("Zdeserializowane sklepy:");
            for(Sklep sklep : sklepy) {
                System.out.println(sklep.idSklepu());
            }
            System.out.println();
        } else {
            // nie za zserializowanych sklepów, trzeba wygenerować je od zera
            System.out.println("Nie wykryto zserializowanych sklepów, zostaną wygenerowane nowe");
            sklepy = wygenerujSklepy();
        }

        final Lidl lidl = (Lidl) sklepy[4];

        List<Produkt> produkty = getListeRandomowychProduktow(magicNum);

        produkty.forEach(x -> lidl.aktualizujIloscProduktow(x, r.nextInt(2 * magicNum)));

        System.out.println(lidl.sprzedajProdukt(produkty.get(1), 2));

        StrategiaPromocji strategia1 = new RabatNaDzienTygodnia(DayOfWeek.SUNDAY, 0.05);

        lidl.zmienStrategie(strategia1);

        System.out.println(lidl.sprzedajProdukt(produkty.get(1), 2));

        StrategiaPromocji strategia2 = new RabatNaSumeZAnaliza(lidl, new SredniaSumaAnaliza(lidl), 0.1);

        lidl.zmienStrategie(strategia2);

        System.out.println(lidl.sprzedajProdukt(produkty.get(2), 2));

        lidl
                .getHistoriaTransakcji()
                .getWszystkieTransakcje()
                .forEach(System.out::println);


        // Serializujemy sklepy, żeby zapisać zmiany do następnego uruchomienia programu
        System.out.println("Serializowanie sklepów...");
        serializator.serializujSklepy(sklepy);

    }

    private static List<Produkt> getListeRandomowychProduktow(int ilosc) {

        List<String> nazwyProduktow = Arrays.asList(
                "Mleko", "Kawa", "Sok", "Papierosy", "Woda"
        );

        List<Produkt> produkty = new ArrayList<>();
        for (int i = 0; i < ilosc; i++) {

            BigDecimal bD = BigDecimal.valueOf(r.nextDouble() * 10).setScale(2, RoundingMode.DOWN);

            produkty.add(
                    new Produkt(nazwyProduktow.get(r.nextInt(nazwyProduktow.size())),
                            bD.doubleValue()));
        }
        return produkty;
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

