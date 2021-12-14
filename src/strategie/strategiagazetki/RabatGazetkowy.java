package strategie.strategiagazetki;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import sklepy.Produkt;

public class RabatGazetkowy implements StrategiaGazetki, Serializable {

    private static final long serialVersionUID = 8L;

    private double rabat;
    private final DayOfWeek dzien;

    public RabatGazetkowy(DayOfWeek dzien) {
        this.dzien = dzien;

        if (dzien.equals(DayOfWeek.MONDAY)) {
            this.rabat = 0.1;
        }
        if (dzien.equals(DayOfWeek.TUESDAY)) {
            this.rabat = 0.2;
        }
        if (dzien.equals(DayOfWeek.WEDNESDAY)) {
            this.rabat = 0.3;
        }
        if (dzien.equals(DayOfWeek.THURSDAY)) {
            this.rabat = 0.4;
        }
        if (dzien.equals(DayOfWeek.FRIDAY)) {
            this.rabat = 0.5;
        }
        if (dzien.equals(DayOfWeek.SATURDAY)) {
            this.rabat = 0.6;
        }
        if (dzien.equals(DayOfWeek.SUNDAY)) {
            this.rabat = 0.7;
        }
    }

    @Override
    public void gazetkowaPromocja(ArrayList<Produkt> gazetka) {
        if (LocalDate.now().getDayOfWeek() == dzien) {
            gazetka.forEach((x) -> {
                x.setCena(x.getCena() * (1 - rabat));
            });
        } else {
            System.out.println("Dzisiaj nie jest podany dzien");
        }
    }
}
