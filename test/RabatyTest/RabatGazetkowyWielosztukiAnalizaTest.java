package RabatyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sklepy.DniTygodnia;
import sklepy.Produkt;
import sklepy.Supermarket;
import strategie.strategiagazetki.RabatGazetkowy;
import strategie.strategiagazetki.RabatGazetkowyWielosztukiAnaliza;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RabatGazetkowyWielosztukiAnalizaTest {
    RabatGazetkowyWielosztukiAnaliza gazetka;
    Supermarket sklep;
    Produkt produkt1;
    Produkt produkt2;

    float p1Cena;
    float p2Cena;
    int p1Ilosc;
    int p2Ilosc;

    @BeforeEach
    void setUp() {
        p1Cena = 10.0f;
        p2Cena = 10.0f;
        p1Ilosc = 1000;
        p2Ilosc = 1000;

        sklep = new Supermarket("RabatGazetkowyWielosztukiAnaliza", "Test", true) {
            @Override
            public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
                return true;
            }
        };

        sklep.getHistoriaTransakcji().getWszystko().clear();

        produkt1 = new Produkt("Kasza ekskluzywna", p1Cena);
        produkt2 = new Produkt("Ryż równie ekskluzywny", p2Cena);

        sklep.aktualizujIloscProduktow(produkt1, p1Ilosc);
        sklep.aktualizujIloscProduktow(produkt2, p2Ilosc);

        gazetka = new RabatGazetkowyWielosztukiAnaliza(sklep);

        sklep.sprzedajProdukt(produkt1, 100);
        sklep.sprzedajProdukt(produkt1, 100);
        sklep.sprzedajProdukt(produkt1, 100);
        sklep.sprzedajProdukt(produkt2, 8);

        sklep.aktualizujIloscProduktow(produkt1, 300);
        sklep.aktualizujIloscProduktow(produkt2, 8);
    }

    @Test
    void GazetkaZAnaliza_ZmianaCeny() {
        assertEquals(p1Cena, produkt1.getCena());
        assertEquals(p2Cena, produkt2.getCena());

        sklep.otworzGazetke(gazetka);

        assertTrue(produkt1.getCena() < p1Cena);
        assertEquals(p2Cena, produkt2.getCena());

        sklep.zamknijGazetke();

        assertEquals(p1Cena, produkt1.getCena());
        assertEquals(p2Cena, produkt2.getCena());
    }

    @Test
    void GazetkaZAnaliza_ZmianaIlosci() {
        assertEquals(p1Ilosc, sklep.sprawdzDostepnoscProduktu(produkt1));
        assertEquals(p2Ilosc, sklep.sprawdzDostepnoscProduktu(produkt2));

        sklep.otworzGazetke(gazetka);

        assertEquals(p1Ilosc, sklep.sprawdzDostepnoscProduktu(produkt1));
        assertEquals(p2Ilosc, sklep.sprawdzDostepnoscProduktu(produkt2));

        sklep.zamknijGazetke();

        assertEquals(p1Ilosc, sklep.sprawdzDostepnoscProduktu(produkt1));
        assertEquals(p2Ilosc, sklep.sprawdzDostepnoscProduktu(produkt2));

    }
}