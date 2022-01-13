package gui.sklepview;

import gui.kreator.KreatorInterfejs;
import gui.kreator.KreatorProduktow;
import gui.oknaztabela.HistoriaTransakcjiDialog;
import gui.oknaztabela.Koszyk;
import gui.produktview.AbstractProduktComponent;
import serializacja.Transakcja;
import sklepy.Produkt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSklepView extends JFrame {

    // Jak należy dodać to okno do swojej klasy: tworzymy obiekt klasy JButton (lub inny)
    // podpinamy pod niego słuchacza zdefiniowanego w tej klasie: 'HistoriaTransakcjiHandler'
    // i dodajemy do widoku sklepu
    private HistoriaTransakcjiDialog hTDialog;
    // Tak samo dodajemy koszyk, jak wyżej opisane
    private Koszyk koszyk;

    // W konretnej podklasie tworzymy przycisk, który reaguje na akcję otwierając to okno
    private KreatorInterfejs<ProduktEvent> kreatorProduktow;

    Map<Produkt, AbstractProduktComponent> produktIJegoWidzet;

    // Logika umiejscawiania kompontentów w widoku: nie podpinamy żadnych słuchaczy tutaj!, to
    // jest załatwione w metodzie 'placeProduktComponent', żeby nie powtarzał się kod
    abstract void placeProduktComponent(AbstractProduktComponent comp);

    public AbstractSklepView(String nazwaSklepu){

        setSize(800, 800);
        setTitle(nazwaSklepu);

        produktIJegoWidzet = new HashMap<>();
        koszyk = new Koszyk();

        hTDialog = new HistoriaTransakcjiDialog();
        kreatorProduktow = new KreatorProduktow();
        kreatorProduktow.pobierzProdukty(produktIJegoWidzet);

        // Nie zamyka okna, ale go chowa, dzięki czemu główne
        // okno pozostaje aktywne
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public void addProduktComponent(Produkt produkt, AbstractProduktComponent comp){
        placeProduktComponent(comp);
        comp.setKoszyk(koszyk);
        produktIJegoWidzet.put(produkt, comp);
        kreatorProduktow.pobierzProdukty(produktIJegoWidzet);
    }

    public void aktualizujIloscProduktow(Produkt produkt, int ilosc){
        // Teraz pobieramy panel, który reprezentuje podany produkt i aktualizujemy jego ilość
        if (produktIJegoWidzet.containsKey(produkt)){
            produktIJegoWidzet.get(produkt).aktualizujIloscProduktow(ilosc);
        }
    }

    public void showMessageDialog(String message){
        // Wyświetla proste okno z wiadomością
        JOptionPane.showMessageDialog(AbstractSklepView.this, message);

    }

    public void aktualizujHistorieTransakcji(List<Transakcja> transakcje){
        SwingUtilities.invokeLater(() -> {
            hTDialog.addPozycjeDoTabeli(transakcje);
        });
    }

    // Wyświetla ramkę z historią transakcji
    protected class HistoriaTransakcjiHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            hTDialog.showDialog(AbstractSklepView.this);
        }
    }

    // Wyświetla ramkę z koszykiem
    protected class KoszykHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            koszyk.showDialog(AbstractSklepView.this);
        }
    }

    public KreatorInterfejs<ProduktEvent> getKreatorProduktow(){
        return kreatorProduktow;
    }

    public Koszyk getKoszyk(){
        return koszyk;
    }
}
