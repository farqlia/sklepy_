package pracownicy;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Pracownik implements Serializable {
    public Pracownik(String imie, String nazwisko, LocalDate dataZatrudnienia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataZatrudnienia = dataZatrudnienia;
    }

    private static final long serialVersionUID = 18L;


    private String imie;
    private String nazwisko;
    private LocalDate dataZatrudnienia;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }
}
