package gui.kreator;

import gui.produktview.AbstractProduktComponent;
import sklepy.Produkt;

import java.awt.event.ActionListener;
import java.util.Map;

public interface KreatorInterfejs<E> {

    void pobierzProdukty(Map<Produkt, AbstractProduktComponent> mapaProduktow);

    void zrobGUI();

    E getStworzonyObiekt() throws IllegalArgumentException;

    E getZaktualizowanyObiekt() throws IllegalArgumentException;

    void addStworzObiektListener(ActionListener listener);

    void addZaktualizujObiektListener(ActionListener listener);

}
