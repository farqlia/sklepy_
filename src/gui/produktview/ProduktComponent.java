package gui.produktview;

import sklepy.Produkt;
import gui.sklepview.ProduktEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ProduktComponent extends AbstractProduktComponent {

    // Potrzebujemy referencji do produktu, by potem przekazać go
    // do metody sprzedającej
    private final Produkt produkt;

    private String pathToIcons = "images/icons/foodicons/";

    private final JSpinner wybranaIloscJSpinner;
    private JButton kupProduktButton;

    public ProduktComponent(String fileName, Produkt produkt, int ilosc) {

        this.produkt = produkt;

        setLayout(new BorderLayout());
        JPanel upperPanel = new JPanel();
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        ImageIcon icon = new ImageIcon(pathToIcons + fileName);
        icon = new ImageIcon(icon.getImage().getScaledInstance(120, 120, 0));

        JLabel titleLabel = new JLabel(produkt.getNazwa());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel imageLabel = new JLabel(icon);

        upperPanel.add(titleLabel, BorderLayout.NORTH);
        upperPanel.add(imageLabel, BorderLayout.CENTER);

        JToolBar toolBar = new JToolBar();

        icon = new ImageIcon("images/shopicons/basket3.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(20, 20, 0));

        kupProduktButton = new JButton(icon);
        kupProduktButton.addActionListener(new ListenForButton());

        wybranaIloscJSpinner = new JSpinner(new SpinnerNumberModel(0, 0, ilosc, 1));

        toolBar.add(Box.createGlue());
        toolBar.add(Box.createGlue());

        toolBar.add(new JLabel("Cena: " + produkt.getCena()), toolBar.getComponentCount() - 1);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(new JLabel("Ilość"), toolBar.getComponentCount() - 1);
        toolBar.addSeparator();
        toolBar.add(wybranaIloscJSpinner, toolBar.getComponentCount() - 1);
        toolBar.addSeparator();
        toolBar.add(new JLabel("Dodaj do koszyka"), toolBar.getComponentCount() - 1);
        toolBar.addSeparator();
        toolBar.add(kupProduktButton, toolBar.getComponentCount() - 1);

        add(toolBar, BorderLayout.SOUTH);
        add(upperPanel, BorderLayout.NORTH);

    }


    // Metoda wywoływana przez widok sklepu
    @Override
    public void aktualizujIloscProduktow(int ilosc) {
        // Zmieniamy dostępną ilość produktu
        ((SpinnerNumberModel)wybranaIloscJSpinner.getModel()).setMaximum(ilosc);
    }

    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == kupProduktButton){
                // Dodajemy do koszyka
                koszyk.addPozycjeDoTabeli(new ProduktEvent(produkt, (int) wybranaIloscJSpinner.getValue()));
                // Resetujemy komponent
                wybranaIloscJSpinner.getModel().setValue(0);
            }
        }
    }


}
