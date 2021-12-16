package strategie.strategiagazetki;

import java.time.DayOfWeek;
import java.time.LocalDate;

import sklepy.Supermarket;

public class RabatGazetkowy implements StrategiaGazetki {

    private static final long serialVersionUID = 8L;

    private final double rabat;
    private final DayOfWeek dzien;

    private final Supermarket sklep;

    public RabatGazetkowy(DayOfWeek dzien, Supermarket sklep) {
        this.dzien = dzien;
        this.sklep = sklep;

        this.rabat = dzien.getValue() * 0.1;
    }

    // Metoda obliczająca rabat na produkty
    // Rabat jest z góry określony przez dni tygodnia
    @Override
    public void gazetkowaPromocja() {
        if (LocalDate.now().getDayOfWeek() == dzien) {
            sklep.getGazetka().forEach((x) -> x.setCena(x.getCena() * (1 - rabat))
            );
        } else {
            System.out.println("Dziś nie jest podany dzień");
        }
    }
}
