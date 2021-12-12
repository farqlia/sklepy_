package sklepy;


import java.io.Serializable;
import java.util.Objects;

// Prosta klasa, która reprezentuje produkt i jego cenę
public class Produkt implements Serializable {

    private String nazwa;
    private double cena;

    public Produkt(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    // -------- GETTERS & SETTERS --------

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    // -------------------------------

    @Override
    public String toString() {
        return "Produkt{" +
                "nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produkt)) return false;
        Produkt produkt = (Produkt) o;
        return Double.compare(produkt.getCena(), getCena()) == 0 && Objects.equals(getNazwa(), produkt.getNazwa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNazwa(), getCena());
    }
}
