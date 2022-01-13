package gui.kreator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gui.LayoutManager;
import gui.produktview.AbstractProduktComponent;
import sklepy.Produkt;
import gui.sklepview.ProduktEvent;

public class KreatorProduktow implements KreatorInterfejs<ProduktEvent> {

    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;

    private final JFrame frame;
    private final JButton przycisk;
    private final JButton przycisk2;
    private final JPanel panelTworzenie;
    private final JPanel panelAktualizowanie;
    private final JPanel panelPrzyciski;
    private final JTabbedPane zakladki;

    private final JLabel opisWyborProduktu;
    private final JComboBox<Produkt> wyborProduktu;
    private final JLabel opisIlosc2;
    private final JTextField ilosc2;

    private final JLabel opisNazwa;
    private final JTextField nazwa;
    private final JLabel opisCena;
    private final JTextField cena;
    private final JLabel opisIlosc;
    private final JTextField ilosc;
    private final JLabel opisGrafika;
    private final JFileChooser grafika;

    private String sciezkaGrafiki;

    private final JSpinner wybranaIloscJSpinner;

    boolean czyStworzone = false;

    public KreatorProduktow() {

        frame = new JFrame("Kreator Produktów");
        przycisk = new JButton("Dodaj produkt");
        przycisk2 = new JButton("Aktualizuj produkt");
        panelTworzenie = new JPanel();
        panelAktualizowanie = new JPanel();
        panelPrzyciski = new JPanel();
        zakladki = new JTabbedPane();

        opisWyborProduktu = new JLabel("Produkt do zaktualizowania");
        wyborProduktu = new JComboBox<>();
        opisIlosc2 = new JLabel("Ilość");
        ilosc2 = new JTextField(5);

        opisNazwa = new JLabel("Nazwa produktu");
        nazwa = new JTextField(20);

        opisCena = new JLabel("Cena produktu");
        cena = new JTextField(5);

        opisIlosc = new JLabel("Ilość");
        ilosc = new JTextField(5);

        opisGrafika = new JLabel("Wybierz grafikę");
        grafika = new JFileChooser(System.getProperty("user.dir") + "/images/icons");
        grafika.setFileFilter(new FileNameExtensionFilter("JPG, GIF, PNG", "jpg", "gif", "png"));

        wybranaIloscJSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

    }

    private void konfigurujTworzenie() {

        panelTworzenie.setLayout(new BoxLayout(panelTworzenie, BoxLayout.Y_AXIS));

        grafika.addActionListener(new WybierzIkone());

        panelTworzenie.add(Box.createVerticalGlue());

        panelTworzenie.add(opisNazwa);
        opisNazwa.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTworzenie.add(nazwa);
        nazwa.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTworzenie.add(opisCena);
        opisCena.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTworzenie.add(cena);
        cena.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTworzenie.add(opisIlosc);
        opisIlosc.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTworzenie.add(ilosc);
        ilosc.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTworzenie.add(opisGrafika);
        opisGrafika.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTworzenie.add(grafika);
        grafika.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void konfigurujAktualizowanie() {

        /*
        panelAktualizowanie.setLayout(new BoxLayout(panelAktualizowanie, BoxLayout.Y_AXIS));

        panelAktualizowanie.add(Box.createVerticalGlue());

        panelAktualizowanie.add(opisIlosc2);
        opisIlosc2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAktualizowanie.add(ilosc2);
        ilosc.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAktualizowanie.add(wyborProduktu);

         */

        JPanel panel = new JPanel(new GridBagLayout());

        LayoutManager.addComp(panel, opisIlosc2, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        LayoutManager.addComp(panel, wybranaIloscJSpinner, 0, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        LayoutManager.addComp(panel, wyborProduktu, 0, 3, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        panelAktualizowanie.setLayout(new GridBagLayout());
        LayoutManager.addComp(panelAktualizowanie, panel, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

    }

    private void stworzZakladki() {
        zakladki.addTab("Tworzenie", null, panelTworzenie);
        zakladki.addTab("Aktualizowanie", null, panelAktualizowanie);
    }

    // Ta metoda jest wywoływana w klasie widoku sklepu w metodzie addProduktComponent(),
    // więc ten combobox dodaje tylko raz ten produkt
    @Override
    public void pobierzProdukty(ProduktEvent e) {
        wyborProduktu.addItem(e.getProdukt());
    }

    @Override
    public void zrobGUI() {
        // Dodałam tą zmienną, bo nie trzeba za każdym razem tworzyć tego okna, ale
        // zmieniać widoczność, a poza tym to do combobox'a dodawały się znowu produkty
        if (!czyStworzone){
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);

            frame.setResizable(true);
            frame.getContentPane().add(zakladki);
            frame.getContentPane().add(BorderLayout.SOUTH, panelPrzyciski);
            panelPrzyciski.add(BorderLayout.SOUTH, przycisk);
            panelPrzyciski.add(BorderLayout.SOUTH, przycisk2);

            konfigurujTworzenie();
            konfigurujAktualizowanie();
            stworzZakladki();
            czyStworzone = true;

        }
        frame.setVisible(true);
    }

    @Override
    public void addZaktualizujObiektListener(ActionListener listener) {
        przycisk2.addActionListener(listener);
    }

    @Override
    public ProduktEvent getZaktualizowanyObiekt() throws IllegalArgumentException {
        Produkt produkt = (Produkt) wyborProduktu.getSelectedItem();
        //int iloscProduktow = Integer.parseInt(ilosc2.getText());
        int iloscProduktow = (Integer) (wybranaIloscJSpinner.getValue());

        wyczyscPola();

        return new ProduktEvent(produkt, iloscProduktow);
    }

    // Dodajemy słuchacza (kontrolera) bezpośrednio do przycisku który wywołuje wydarzenie
    @Override
    public void addStworzObiektListener(ActionListener a){
        przycisk.addActionListener(a);
    }

    private void wyczyscPola(){
        nazwa.setText("");
        cena.setText("");
        ilosc.setText("");
        ilosc2.setText("");
        nazwa.requestFocus();
        wybranaIloscJSpinner.getModel().setValue(0);
    }

    @Override
    public ProduktEvent getStworzonyObiekt() throws IllegalArgumentException{

        String nazwaProduktu = nazwa.getText();
        double cenaProduktu = Double.parseDouble(cena.getText());
        Produkt produkt = new Produkt(nazwaProduktu, cenaProduktu);
        int iloscProduktow = Integer.parseInt(ilosc.getText());

        wyczyscPola();
        return new ProduktEvent(produkt, iloscProduktow, sciezkaGrafiki);
    }

    class WybierzIkone implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sciezkaGrafiki = grafika.getName(grafika.getSelectedFile());
            grafika.setControlButtonsAreShown(false);
            frame.repaint();
        }
    }
}
