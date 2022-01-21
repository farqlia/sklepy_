package RabatyTest;

import  org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sklepy.Castorama;
import sklepy.Produkt;
import sklepy.Sklep;
import sklepy.SklepBudowniczy;
import strategie.strategianadzial.RabatNaDzial;

import static org.junit.jupiter.api.Assertions.*;

class RabatNaDzialTest {

    private SklepBudowniczy sklep;
    private Produkt donica;
    private Produkt kuchenka;
    private String dzial1 = "Ogrodniczy";
    private String dzial2 = "Kuchnia";

    @BeforeEach
    public void inicjalizacja(){
        sklep = new Castorama("ul.Polna", "castorama.com", true);
        sklep.zmienStrategie(new RabatNaDzial(dzial1, 0.2, sklep));
        donica = new Produkt("Donica", 10);
        kuchenka = new Produkt("Kuchenka", 500);
    }
    @Test
    public void RabatNaDzialTest(){

        sklep.dodajDzial(dzial1);
        sklep.dodajProduktDoDzialu(donica, dzial1);
        sklep.aktualizujIloscProduktow(donica, 1000);
        assertEquals(800, sklep.sprzedajProdukt(donica,100).getSumaAktualna());
    }
    @Test
    public void RabatNaNiepoprawnyDzialTest(){

        sklep.dodajDzial(dzial1);
        sklep.dodajDzial(dzial2);
        sklep.dodajProduktDoDzialu(kuchenka,dzial2);
        sklep.aktualizujIloscProduktow(kuchenka, 100);
        assertEquals(500, sklep.sprzedajProdukt(kuchenka, 1).getSumaAktualna());
    }
}