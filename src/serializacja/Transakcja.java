package serializacja;

import sklepy.Produkt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Transakcja implements Serializable {

    private static final long serialVersionUID = 1234L;

    private LocalDate data = LocalDate.now();
    private Produkt produkt;
    private int ilosc;
    // Nie możemy pominąć właściwej ceny, za
    // jaką sprzedaliśmy towar
    private double sumaAktualna;

    public Transakcja(Produkt produkt, int ilosc, double sumaPoRabacie){
        this.produkt = produkt;
        this.ilosc = ilosc;
        this.sumaAktualna = BigDecimal.valueOf(sumaPoRabacie)
                .setScale(2, BigDecimal.ROUND_DOWN)
                .doubleValue();
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public int getIlosc() {
        return ilosc;
    }

    public double getSumaAktualna() {
        return sumaAktualna;
    }

    @Override
    public String toString() {
        return "Transakcja{" +
                "data=" + data +
                ", produkt=" + produkt +
                ", ilosc=" + ilosc +
                ", sumaAktualna=" + sumaAktualna +
                '}';
    }
}