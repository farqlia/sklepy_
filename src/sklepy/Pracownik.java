package sklepy;

import java.util.Date;

public class Pracownik {

    private String imie;
    private String nazwisko;
    private Date dataZatrudnienia;
    private double pensja;

    public Pracownik(String imie, String nazwisko, Date dataZatrudnienia, double pensja) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataZatrudnienia = dataZatrudnienia;
        this.pensja = pensja;
    }

    // -------- GETTERS & SETTERS --------------

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Date getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(Date dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataZatrudnienia=" + dataZatrudnienia +
                ", pensja=" + pensja +
                '}';
    }
}