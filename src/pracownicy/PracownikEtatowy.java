package pracownicy;

import java.io.Serializable;
import java.time.LocalDate;

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

}
