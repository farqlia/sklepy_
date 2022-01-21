package RabatyTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import sklepy.Castorama;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategia2za1.Rabat2za1BezAnalizy;
import strategie.strategiapromocji.RabatKartaKlienta;
import strategie.strategiapromocji.StrategiaPromocji;

import static org.junit.jupiter.api.Assertions.*;

public class RabatKartaKlientaTest {

    private Sklep sklep;
    private Produkt produkt;
    private double rabat = 0.05;
    private int ilosc = 5;
    private StrategiaPromocji strategia;
    private StrategiaPromocji oryginalnaStrategiaPromocji;


    @BeforeEach
    public void setUp() throws Exception {
        sklep = new Castorama("3 Maja", "castorama.com",true);
        produkt = new Produkt("ProduktTest",2.0);
        sklep.aktualizujIloscProduktow(produkt, 20);
    }

    @Test
    public void bezOryginalnejStrategiiTest () throws Exception
    {
        strategia = new RabatKartaKlienta(rabat,oryginalnaStrategiaPromocji);
        sklep.zmienStrategie(strategia);
        assertEquals(9.5,sklep.sprzedajProdukt(produkt,ilosc).getSumaAktualna());
    }

    @Test
    public void zOryginalnaStrategia2za1Test () throws Exception
    {
        // brakuje funkcji pozwalajacej zmienic oryginalna
        // strategie w strategii rabatu na karte klienta

        oryginalnaStrategiaPromocji = new Rabat2za1BezAnalizy(10);
        strategia = new RabatKartaKlienta(rabat,oryginalnaStrategiaPromocji);
        sklep.zmienStrategie(strategia);

        assertEquals(5.69,sklep.sprzedajProdukt(produkt,ilosc).getSumaAktualna());
    }
}