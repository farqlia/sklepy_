package FirmaDostawczaTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serializacja.Zamowienie;
import sklepy.FirmaDostawcza;
import sklepy.Lidl;
import sklepy.Produkt;
import sklepy.Sklep;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

class FirmaDostawczaTest {
    private FirmaDostawcza firmaDostawcza;
    private Sklep sklep;
    private Produkt produkt;
    private Produkt produkt2;

    @BeforeEach
    public void stworzSklep(){
        sklep = new Lidl("ul. Akademicka 6, Wrocław", "lidl.pl", true);
        firmaDostawcza = new FirmaDostawcza("Fedex", 2, LocalDate.now().getDayOfWeek(), "Polska");
        produkt = new Produkt("TestProdukt", 2.00);
        produkt2 = new Produkt("TestProdukt2", 2.00);
    }

    @Test
    public void dostarczProduktyTest(){

        firmaDostawcza.dostarczProdukty(sklep, produkt, 400);
        sklep.wyswietlOferteSklepu();
        Assertions.assertEquals(400, sklep.sprawdzDostepnoscProduktu(produkt));

        firmaDostawcza = new FirmaDostawcza("Fedex", 2, DayOfWeek.WEDNESDAY, "Polska");
        firmaDostawcza.dostarczProdukty(sklep, produkt, 200);
        Assertions.assertFalse(sklep.getStanMagazynu().containsKey(produkt2));
    }
    @Test
    public void dostarczUjemneProduktyTest(){

        firmaDostawcza.dostarczProdukty(sklep, produkt, -34);
        Assertions.assertNotEquals(-34, sklep.sprawdzDostepnoscProduktu(produkt));
    }

}