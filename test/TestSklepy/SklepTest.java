package TestSklepy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pracownicy.Pracownik;
import pracownicy.PracownikEtatowy;
import serializacja.Transakcja;
import sklepy.Castorama;
import sklepy.Produkt;
import sklepy.Sklep;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SklepTest {

    private Produkt produkt;
    private Sklep sklep;

    @BeforeEach
    public void inicjalizuj(){
        sklep = new Castorama("ul.Kwiatowa", "castorama.com", false);
        produkt = new Produkt("cegła", 1);
        sklep.aktualizujIloscProduktow(produkt, 1000);
    }

    @Test
    public void aktualizujIloscProduktowTest(){

        assertTrue(sklep.getStanMagazynu().containsKey(produkt));
        assertEquals(1000, sklep.getStanMagazynu().get(produkt));

        sklep.aktualizujIloscProduktow(produkt, -10000);
        assertEquals(0, sklep.getStanMagazynu().get(produkt));
    }
    @Test
    public void sprzedajProduktTest(){


        Transakcja t = new Transakcja(produkt, 100, 100);
        assertEquals(t.getProdukt(), sklep.sprzedajProdukt(produkt,100).getProdukt());
        assertEquals(t.getIlosc(), sklep.sprzedajProdukt(produkt, 100).getIlosc());
        assertEquals(t.getSumaAktualna(), sklep.sprzedajProdukt(produkt,100).getSumaAktualna());
    }
    @Test
    public void sprawdzDostepnoscProduktuTest(){
        assertEquals(1000, sklep.sprawdzDostepnoscProduktu(produkt));
        sklep.aktualizujIloscProduktow(produkt, -500);
        assertEquals(500, sklep.sprawdzDostepnoscProduktu(produkt));
        sklep.aktualizujIloscProduktow(produkt, -500);
        assertEquals(0, sklep.sprawdzDostepnoscProduktu(produkt));
    }
    @Test
    public void autoryzujTransakcjeTest(){

        Transakcja t= new Transakcja(produkt, 100, 1);
        assertEquals(t.getProdukt(), sklep.autoryzujTransakcje(produkt, 100, 1).getProdukt());
        assertEquals(t.getIlosc(), sklep.autoryzujTransakcje(produkt, 100, 1).getIlosc());
        assertEquals(t.getSumaAktualna(), sklep.autoryzujTransakcje(produkt, 100, 1).getSumaAktualna());
    }
}