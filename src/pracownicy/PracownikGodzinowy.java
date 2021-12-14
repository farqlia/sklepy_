package pracownicy;

import java.io.Serializable;
import java.time.LocalDate;

public class PracownikGodzinowy extends Pracownik implements Serializable {
    public PracownikGodzinowy(String imie, String nazwisko, LocalDate dataZatrudnienia, double wynagrodzenieZaGodzine) {
        super(imie, nazwisko, dataZatrudnienia);
        this.wynagrodzenieZaGodzine = wynagrodzenieZaGodzine;
    }

    private static final long serialVersionUID = 20L;

    private double wynagrodzenieZaGodzine;

    public double getWynagrodzenieZaGodzine() {
        return wynagrodzenieZaGodzine;
    }

    public void setWynagrodzenieZaGodzine(double wynagrodzenieZaGodzine) {
        this.wynagrodzenieZaGodzine = wynagrodzenieZaGodzine;
    }

    @Override
    public String toString() {
        return "Pracownik {" +
                "imie='" + getImie() + '\'' +
                ", nazwisko='" + getNazwisko() + '\'' +
                ", dataZatrudnienia=" + getDataZatrudnienia() +
                ", wynagrodzenieZaGodzine=" + wynagrodzenieZaGodzine +
                '}';
    }
}
