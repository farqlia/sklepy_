package RabatyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sklepy.DniTygodnia;
import sklepy.Produkt;
import sklepy.Supermarket;
import sklepy.Zabka;
import strategie.strategiagazetki.RabatGazetkowy;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RabatGazetkowyTest {

    RabatGazetkowy gazetka;
    Supermarket supermarket;
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

        supermarket = new Supermarket("a", "a", true) {
            @Override
            public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
                return true;
            }
        };

        produkt1 = new Produkt("Kasza ekskluzywna i to na gazetce", p1Cena);
        produkt2 = new Produkt("Ryż równie ekskluzywny ale nie ma go na gazetce", p2Cena);

        supermarket.aktualizujIloscProduktow(produkt1, p1Ilosc);
        supermarket.aktualizujIloscProduktow(produkt2, p2Ilosc);

        supermarket.dodajDoGazetki(produkt1);

        gazetka = new RabatGazetkowy(LocalDate.now().getDayOfWeek(), supermarket);
    }

    @Test
    void gazetkowaPromocja_ZmianaCeny(){
        assertEquals(p1Cena, produkt1.getCena());
        assertEquals(p2Cena, produkt2.getCena());

        supermarket.otworzGazetke(gazetka);

        double rabat = LocalDate.now().getDayOfWeek().getValue() * 0.1;

        assertEquals(p1Cena * (1 - rabat), produkt1.getCena());
        assertEquals(p2Cena, produkt2.getCena());

        supermarket.zamknijGazetke();
        assertEquals(p1Cena, produkt1.getCena());
        assertEquals(p2Cena, produkt2.getCena());
    }

    @Test
    void gazetkowaPromocja_IloscProduktow() {
        assertEquals(p1Ilosc, supermarket.sprawdzDostepnoscProduktu(produkt1));
        assertEquals(p2Ilosc, supermarket.sprawdzDostepnoscProduktu(produkt2));

        supermarket.otworzGazetke(gazetka);
        assertEquals(p1Ilosc, supermarket.sprawdzDostepnoscProduktu(produkt1));
        assertEquals(p2Ilosc, supermarket.sprawdzDostepnoscProduktu(produkt2));

        supermarket.sprzedajProdukt(produkt1, 10);
        supermarket.sprzedajProdukt(produkt2, 10);
        assertEquals(p1Ilosc - 10, supermarket.sprawdzDostepnoscProduktu(produkt1));
        assertEquals(p2Ilosc - 10, supermarket.sprawdzDostepnoscProduktu(produkt2));

        supermarket.zamknijGazetke();
        assertEquals(p1Ilosc - 10, supermarket.sprawdzDostepnoscProduktu(produkt1));
        assertEquals(p2Ilosc - 10, supermarket.sprawdzDostepnoscProduktu(produkt2));
    }
}