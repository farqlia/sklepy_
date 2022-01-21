package RabatyTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import sklepy.*;
import strategie.strategia2za1.Rabat2za1BezAnalizy;
import strategie.strategiapromocji.StrategiaPromocji;

import static org.junit.jupiter.api.Assertions.*;

public class Rabat2za1BezAnalizyTest {

    private Sklep sklep;
    private Produkt produkt;
    private StrategiaPromocji strategia;
    private final double minSuma=20.0;

    @BeforeEach
    public void setUp() {

        sklep = new Castorama("3 Maja", "castorama.com",true);

        produkt= new Produkt("TestProdukt", 2.00);

        sklep.aktualizujIloscProduktow(produkt, 200);

        strategia = new Rabat2za1BezAnalizy(minSuma);

        sklep.zmienStrategie(strategia);

    }

    @Test
    public void parzystaIloscTest()
    {
        sklep.aktualizujIloscProduktow(produkt, 200);
        int ilosc = 16;
        double sumaBezRabatu = 32.0;
        double sumaZRabatem = sumaBezRabatu * 0.5;
        assertEquals(sumaZRabatem,sklep.sprzedajProdukt(produkt,ilosc).getSumaAktualna());
    }

    @Test
    public void nieparzystaIloscTest()
    {
        sklep.aktualizujIloscProduktow(produkt, 200);
        double sumaZRabatem = (produkt.getCena() * 14 * 0.5) + produkt.getCena();
        assertEquals(sumaZRabatem,sklep.sprzedajProdukt(produkt,15).getSumaAktualna());

    }

    @Test
    public void ponizejMinimalnejTest()
    {
        sklep.aktualizujIloscProduktow(produkt, 200);
        double sumaBezRabatu = 5 * produkt.getCena();
        assertEquals(sumaBezRabatu,sklep.sprzedajProdukt(produkt,5).getSumaAktualna());

    }

    @Test
    public void minimalnaTest()
    {
        sklep.aktualizujIloscProduktow(produkt, 200);
        double sumaBezRabatu = 10 *produkt.getCena();
        double sumaZRabatem = sumaBezRabatu * 0.5;
        assertEquals(sumaZRabatem,sklep.sprzedajProdukt(produkt,10).getSumaAktualna());

    }

}