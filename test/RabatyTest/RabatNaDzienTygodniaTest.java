package RabatyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sklepy.Castorama;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiapromocji.RabatNaDzienTygodnia;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RabatNaDzienTygodniaTest {
    private Sklep sklep;
    private Produkt produkt;
    private RabatNaDzienTygodnia rabat;

    @BeforeEach
    public void inicjalizacja(){
        sklep = new Castorama("ul. Polna 3", "castorama.com", false);
        produkt = new Produkt("dywan", 100);
        rabat = new RabatNaDzienTygodnia(LocalDate.now().getDayOfWeek(), 0.5);
    }
    @Test
    public void rabatNaDzienTygodnia(){

        sklep.zmienStrategie(rabat);
        sklep.aktualizujIloscProduktow(produkt, 100);
        assertEquals(50, sklep.sprzedajProdukt(produkt, 1).getSumaAktualna());

        rabat.zmienDzienRabatowy(DayOfWeek.THURSDAY, 0.5);

        assertNotEquals(50, sklep.sprzedajProdukt(produkt, 1).getSumaAktualna());

    }

}