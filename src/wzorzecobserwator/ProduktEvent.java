package wzorzecobserwator;

import sklepy.Produkt;

public class ProduktEvent {

    private Produkt produkt;
    private int ilosc;

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
}
