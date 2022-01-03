package gui.produktview;

import sklepy.Produkt;
import wzorzecobserwator.Observer;
import wzorzecobserwator.ProduktEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ProduktComponent extends AbstractProduktComponent {

    // Potrzebujemy referencji do produktu, by potem przekazać go
    // do metody sprzedającej
    private Produkt produkt;

    private String pathToIcons = "images/icons/";

    private JCheckBox kupProduktCheckBox;
    private JSpinner wybranaIloscJSpinner;

    public ProduktComponent(String fileName, Produkt produkt, int ilosc) {
        super(produkt, ilosc);

        this.produkt = produkt;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(produkt.getNazwa()));

        JPanel upperPanel = new JPanel();
        ImageIcon icon = new ImageIcon(pathToIcons + fileName);
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, 0));
        JLabel imageLabel = new JLabel(icon);

        upperPanel.add(imageLabel);

        JPanel lowerPanel = new JPanel();
        wybranaIloscJSpinner = new JSpinner(new SpinnerNumberModel(0, 0, ilosc, 1));
        JLabel cenaLabel = new JLabel("Cena: " + produkt.getCena());
        kupProduktCheckBox = new JCheckBox("Kup produkt");
        kupProduktCheckBox.addItemListener(new ListenForCheckBox());

        lowerPanel.add(cenaLabel);
        lowerPanel.add(kupProduktCheckBox);
        lowerPanel.add(wybranaIloscJSpinner);

        add(lowerPanel, BorderLayout.SOUTH);
        add(upperPanel, BorderLayout.NORTH);

    }

    // Metoda wywoływana przez widok sklepu
    @Override
    public void update(ProduktEvent e) {
        // Upewniamy się, że zmiana dotyczy tego produktu
        if (e.getProdukt().equals(produkt)){
            // Zmieniamy dostępną ilość produktu
            ((SpinnerNumberModel)wybranaIloscJSpinner.getModel()).setMaximum(e.getIlosc());
            wybranaIloscJSpinner.getModel().setValue(0);
        }
    }

    private class ListenForCheckBox implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                notifyObservers(null);
            }
        }
    }

    // Powiadamia słuchaczy o wybraniu danego produktu w danej ilości
    @Override
    public void notifyObservers(ProduktEvent e) {
        for (Observer ob : observators){
            ob.update(new ProduktEvent(produkt, (Integer)wybranaIloscJSpinner.getValue()));
        }
    }

}
