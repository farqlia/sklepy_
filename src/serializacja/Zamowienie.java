package serializacja;

import java.io.Serializable;
import java.time.LocalDate;

import sklepy.Produkt;

public class Zamowienie implements Serializable {

    private static final long serialVersionUID = 96L;

    private LocalDate data = LocalDate.now();
    private final Produkt produkt;
    private final int ilosc;
    // Uznałam, że może też się przydać nazwa sklepu, który dokonał zamówienia
    private String nazwaSklepu;

    public Zamowienie(Produkt produkt, int ilosc, String nazwaSklepu) {
        this.produkt = produkt;
        this.ilosc = ilosc;
        this.nazwaSklepu = nazwaSklepu;
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


    @Override
    public String toString() {
        return "Zamowienie{" +
                "data=" + data +
                ", produkt=" + produkt +
                ", ilosc=" + ilosc + "}";
    }

}
