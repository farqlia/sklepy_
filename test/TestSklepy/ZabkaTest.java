package TestSklepy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serializacja.Transakcja;
import sklepy.Produkt;
import sklepy.Sklep;
import sklepy.Zabka;

import static org.junit.jupiter.api.Assertions.*;

class ZabkaTest {

    private Produkt produkt1;
    private Produkt produkt2;
    private Zabka zabka;

    @BeforeEach
    public void inicjalizuj(){
        zabka = new Zabka(true, "ul.Polna", "zabka.pl", false);
        produkt1 = new Produkt("Hotdog", 5);
        produkt2 = new Produkt("cegła", 1);
        zabka.aktualizujIloscProduktow(produkt1,10);
        zabka.aktualizujIloscProduktow(produkt2,10);
    }
    @Test
    public void czyProduktDoSprzedaniaNaCieplo(){

        assertTrue(zabka.czyProduktDoSprzedaniaNaCieplo(produkt1));
        assertFalse(zabka.czyProduktDoSprzedaniaNaCieplo(produkt2));
    }
    @Test
    public void sprzedajZAplikacjaTest(){

        Transakcja t = new Transakcja(produkt1, 1, 4.5);
        Transakcja t2 = new Transakcja(produkt1, 15, 67.5);
        assertEquals(t.getSumaAktualna(), zabka.sprzedajProduktZAplikacja(produkt1, 1).getSumaAktualna());
        assertNotEquals(t2.getSumaAktualna(), zabka.sprzedajProduktZAplikacja(produkt1, 15).getSumaAktualna());
    }
}