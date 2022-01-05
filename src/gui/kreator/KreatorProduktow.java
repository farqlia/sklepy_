package gui.kreator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import sklepy.Produkt;
import sklepy.Sklep;

public class KreatorProduktow implements KreatorInterfejs {

    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;

    private final JFrame frame;
    private final JButton przycisk;
    private final JPanel panel;
    private final JLabel opisNazwa;
    private final JTextField nazwa;
    private final JLabel opisCena;
    private final JTextField cena;
    private final JLabel opisIlosc;
    private final JTextField ilosc;
    private final JLabel opisGrafika;
    private final JFileChooser grafika;

    private String sciezkaGrafiki;

    private Sklep sklep;

    private KreatorProduktow() {
        frame = new JFrame("Kreator Produktów");
        przycisk = new JButton("Dodaj produkt");
        panel = new JPanel();
        
        opisNazwa = new JLabel("Nazwa produktu");
        nazwa = new JTextField(20);

        opisCena = new JLabel("Cena produktu");
        cena = new JTextField(5);

        opisIlosc = new JLabel("Ilość");
        ilosc = new JTextField(5);

        opisGrafika = new JLabel("Wybierz grafikę");
        grafika = new JFileChooser(System.getProperty("user.dir") + "/images/icons");
        grafika.setFileFilter(new FileNameExtensionFilter("JPG, GIF, PNG", "jpg", "gif", "png"));
    }

    private void konfiguruj() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
        frame.setResizable(true);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.getContentPane().add(BorderLayout.CENTER, panel);

        przycisk.addActionListener(new StworzProduktListener());
        grafika.addActionListener(new WybierzIkone());
        
        frame.getContentPane().add(BorderLayout.SOUTH, przycisk);

        panel.add(Box.createVerticalGlue());

        panel.add(opisNazwa);
        opisNazwa.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(nazwa);
        nazwa.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(opisCena);
        opisCena.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(cena);
        cena.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(opisIlosc);
        opisIlosc.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(ilosc);
        ilosc.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(opisGrafika);
        opisGrafika.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(grafika);
        grafika.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Przyciski JFileChooser zapisują ścieżkę wybranego folderu i się "wyłączają"
    class WybierzIkone implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sciezkaGrafiki = grafika.getName(grafika.getSelectedFile());
            grafika.setControlButtonsAreShown(false);
            frame.repaint();
        }
    }

    // Przycisk JButton tworzy produkt na podstawie wpisanych informacji i przekazuje mu śćieżkę ikony
    class StworzProduktListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nazwaProduktu = nazwa.getText();
            int cenaProduktu = Integer.parseInt(cena.getText());
            Produkt produkt = new Produkt(nazwaProduktu, cenaProduktu);
            
            produkt.setFileName(sciezkaGrafiki);

            int iloscProduktow = Integer.parseInt(ilosc.getText());
            sklep.aktualizujIloscProduktow(produkt, iloscProduktow);


            nazwa.setText("");
            cena.setText("");
            ilosc.setText("");
            nazwa.requestFocus();
        }
    }

    @Override
    public void zrobGUI(Sklep sklep) {
         this.sklep = sklep;
         konfiguruj();
    }

}
