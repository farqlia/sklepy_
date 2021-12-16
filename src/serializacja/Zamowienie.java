package serializacja;

import java.io.Serializable;
import java.time.LocalDate;

import sklepy.Produkt;

public class Zamowienie implements Serializable {

    private static final long serialVersionUID = 96L;

    private LocalDate data = LocalDate.now();
    private Produkt produkt;
    private int ilosc;
    
    public Zamowienie(Produkt produkt, int ilosc) {
        this.produkt = produkt;
        this.ilosc = ilosc;
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
        return "Transakcja{" +
                "data=" + data +
                ", produkt=" + produkt +
                ", ilosc=" + ilosc + "}";
    }

}
