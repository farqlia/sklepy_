package wzorzecobserwator;

import sklepy.Produkt;

public class ProduktEvent {

    private Produkt produkt;
    private int ilosc;
    private String sciezkaPliku = "";

    public Produkt getProdukt() {
        return produkt;
    }

    public int getIlosc() {
        return ilosc;
    }

    public ProduktEvent(Produkt produkt, int ilosc) {
        this.produkt = produkt;
        this.ilosc = ilosc;
    }

    public ProduktEvent(Produkt produkt, int ilosc, String sciezkaPliku) {
        this(produkt, ilosc);
        this.sciezkaPliku = sciezkaPliku;
    }

    public String getSciezkaPliku() {
        return sciezkaPliku;
    }
}
