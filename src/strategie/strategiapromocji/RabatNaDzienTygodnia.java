package strategie.strategiapromocji;

import sklepy.Produkt;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class RabatNaDzienTygodnia implements StrategiaPromocji, Serializable {

    private static final long serialVersionUID = 12L;

    private DayOfWeek dzienRabatowy;
    private final double rabat;

    // Tutaj dzień rabatowy narzucony jest przez sklep
    public RabatNaDzienTygodnia(DayOfWeek dzienRabatowy, double rabat) {
        this.dzienRabatowy = dzienRabatowy;
        this.rabat = rabat;
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {
        double cenaBezRabatu = (produkt.getCena() * ilosc);
        return LocalDate.now().getDayOfWeek() == dzienRabatowy ? (1 - rabat) * cenaBezRabatu : cenaBezRabatu;
    }

    public void zmienDzienRabatowy(DayOfWeek dzien, double rabat) {
        dzienRabatowy = dzien;
    }
}
