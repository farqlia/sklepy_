package pracownicy;

import java.time.LocalDate;
import java.util.Objects;

public class PracownikGodzinowy extends Pracownik {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PracownikGodzinowy that = (PracownikGodzinowy) o;
        return Double.compare(that.wynagrodzenieZaGodzine, wynagrodzenieZaGodzine) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wynagrodzenieZaGodzine, getImie(), getNazwisko(), getDataZatrudnienia());
    }
}
