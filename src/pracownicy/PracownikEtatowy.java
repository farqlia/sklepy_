package pracownicy;

import java.time.LocalDate;
import java.util.Objects;

public class PracownikEtatowy extends Pracownik {
    private double pensja;

    private static final long serialVersionUID = 19L;

    public PracownikEtatowy(String imie, String nazwisko, LocalDate dataZatrudnienia, double pensja) {
        super(imie, nazwisko, dataZatrudnienia);

        this.pensja = pensja;
    }

    // -------- GETTERS & SETTERS --------------


    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return "Pracownik {" +
                "imie='" + getImie() + '\'' +
                ", nazwisko='" + getNazwisko() + '\'' +
                ", dataZatrudnienia=" + getDataZatrudnienia() +
                ", pensja=" + pensja +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PracownikEtatowy that = (PracownikEtatowy) o;
        return Double.compare(that.pensja, pensja) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pensja, getImie(), getNazwisko(), getDataZatrudnienia());
    }
}
