package test;


import sklepy.Produkt;
import strategie.strategiaanalizy.SredniaSumaAnaliza;
import strategie.strategiapromocji.RabatNaDzienTygodnia;
import strategie.strategiapromocji.RabatNaSumeZAnaliza;
import strategie.strategiapromocji.StrategiaPromocji;

import java.math.BigDecimal;

import java.time.DayOfWeek;
import java.util.*;

public class MainTest {

    static Random r = new Random();
    static int magicNum = 10;

    public static void main(String[] args) {

        TestSklep testSklep = new TestSklep("ul. Wittiga 12, Wrocław", "sklep.pl");

        List<Produkt> produkty = getListeRandomowychProduktow(magicNum);

        produkty.forEach(x -> testSklep.aktualizujIloscProduktow(x, r.nextInt(2 * magicNum)));

        // Tworzymy jakieś dane początkowe by było coś do analizy
        // Jeszcze lepiej by było jakieś inne daty uzwględnić
        //produkty.forEach(x -> testSklep.sprzedajProdukt(x, r.nextInt(2 * magicNum)));

        System.out.println(testSklep.sprzedajProdukt(produkty.get(1), 2));

        StrategiaPromocji sP = new RabatNaDzienTygodnia(DayOfWeek.SUNDAY, 0.05);

        testSklep.zmienStrategie(sP);

        System.out.println(testSklep.sprzedajZKartaKlienta(produkty.get(1), 2));

        StrategiaPromocji sp = new RabatNaSumeZAnaliza(testSklep, new SredniaSumaAnaliza(), 0.1);

        testSklep.zmienStrategie(sP);

        System.out.println(testSklep.sprzedajZKartaKlienta(produkty.get(2), 2));

        testSklep
                .getHistoriaTransakcji()
                .getWszystkieTransakcje()
                .forEach(System.out::println);

    }

    public static List<Produkt> getListeRandomowychProduktow(int ilosc){

        List<String> nazwyProduktow = Arrays.asList(
                "Mleko", "Kawa", "Sok", "Papierosy", "Woda"
        );

        List<Produkt> produkty = new ArrayList<>();
        for (int i = 0; i < ilosc; i++){

            BigDecimal bD = BigDecimal.valueOf(r.nextDouble() * 10).setScale(2, BigDecimal.ROUND_DOWN);

            produkty.add(
                    new Produkt(nazwyProduktow.get(r.nextInt(nazwyProduktow.size())),
                            bD.doubleValue()));
        }
        return produkty;
    }

}