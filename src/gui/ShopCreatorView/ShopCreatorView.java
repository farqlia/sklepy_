package gui.ShopCreatorView;

import kontrolery.Sklepy;
import kontrolery.listeners.DalejListener;
import kontrolery.listeners.MenuMarkiListener;
import kontrolery.ShopCreatorController;
import sklepy.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ShopCreatorView extends JFrame {

    public ShopCreatorView() {
        super("Kreator sklepu");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(450, 400);
    }

    ShopCreatorController kontroler;

    public void setEtapTworzeniaSklepu(EtapTworzeniaSklepu etap) {
        getContentPane().removeAll();
        if (etap == EtapTworzeniaSklepu.wyborMarki) {
            add(new PanelWyboruMarki(kontroler));
        } else {
            add(new PanelSzczegolow(kontroler));
        }
        revalidate();
        repaint();
    }

    public void setKontroler(ShopCreatorController kontroler) {
        this.kontroler = kontroler;
        add(new PanelWyboruMarki(kontroler));
    }

    private static class PanelWyboruMarki extends JPanel {
        public PanelWyboruMarki(ShopCreatorController kontroler) {
            setLayout(new BorderLayout(10, 10));
            JLabel tytul = new JLabel("Wybierz markę sklepu");
            tytul.setHorizontalAlignment(JLabel.CENTER);
            setBorder(new EmptyBorder(20, 20, 20, 20));
            tytul.setVerticalAlignment(JLabel.BOTTOM);
            add(BorderLayout.NORTH, tytul);
            add(BorderLayout.CENTER, listaMarek(kontroler));
            add(BorderLayout.SOUTH, przyciskDalej(kontroler));

        }

        private JComboBox<String> listaMarek(ShopCreatorController kontroler) {
            final String[] nazwySklepow = {"Biedronka", "Castorama", "Ikea", "Leroy Merlin", "Lidl", "Vox", "Zabka"};
            final JComboBox<String> lista = new JComboBox<>(nazwySklepow);
            lista.setSelectedIndex(0);
            lista.addActionListener(new MenuMarkiListener(kontroler));
            lista.setPreferredSize(new Dimension(30, 30));
            //lista.setBorder(new EmptyBorder(30, 40, 30, 40));
            return lista;
        }

        private JButton przyciskDalej(ShopCreatorController kontroler) {
            final JButton przycisk = new JButton("Dalej");
            przycisk.addActionListener(new DalejListener(kontroler));
            return przycisk;
        }
    }

    private static class Formularz extends JPanel {
        public Formularz(ShopCreatorController kontroler) {
            int iloscPol;
            Sklepy marka = kontroler.getModel().wybranaMarka;
            if (marka == Sklepy.ikea || marka == Sklepy.vox || marka == Sklepy.zabka) {
                iloscPol = 4;
            } else {
                iloscPol = 3;
            }
            setLayout(new GridLayout(iloscPol, 2, 1, 1));

            add(new JLabel("Adres"));
            add(new PoleTekstowe((a) -> kontroler.getModel().sklep.setAdres(a)));

            add(new JLabel("Adres WWW"));
            add(new PoleTekstowe((a) -> kontroler.getModel().sklep.setAdresWWW(a)));

            if (marka == Sklepy.biedronka || marka == Sklepy.castorama || marka == Sklepy.zabka || marka == Sklepy.lidl || marka == Sklepy.leroyMerlin) {
                add(new JLabel("Czy ma kasy samoobsługowe?"));
                final JCheckBox przycisk = new JCheckBox();
                przycisk.addActionListener(e -> {
                    if (marka == Sklepy.biedronka) {
                        Biedronka sklep = (Biedronka) kontroler.getModel().sklep;
                        sklep.setCzyMaKasySamoobslugowe(przycisk.isSelected());
                    }
                    if (marka == Sklepy.castorama) {
                        Castorama sklep = (Castorama) kontroler.getModel().sklep;
                        sklep.setCzySaKasySamoobslugowe(przycisk.isSelected());
                    }
                    if (marka == Sklepy.zabka) {
                        Zabka sklep = (Zabka) kontroler.getModel().sklep;
                        sklep.setCzyJestKasaSamoobslugowa(przycisk.isSelected());
                    }
                    if (marka == Sklepy.lidl) {
                        Lidl sklep = (Lidl) kontroler.getModel().sklep;
                        sklep.setCzyMaKasySamoobslugowe(przycisk.isSelected());
                    }
                    if (marka == Sklepy.leroyMerlin) {
                        LeroyMerlin sklep = (LeroyMerlin) kontroler.getModel().sklep;
                        sklep.setCzySaKasySamoobslugowe(przycisk.isSelected());
                    }
                });
                add(przycisk);
            }

            if (marka == Sklepy.ikea || marka == Sklepy.vox) {
                add(new JLabel("Czy można kupować na raty?"));
                final JCheckBox przycisk = new JCheckBox();
                przycisk.addActionListener(e -> {
                    if (marka == Sklepy.ikea) {
                        Ikea sklep = (Ikea) kontroler.getModel().sklep;
                        sklep.setCzyMoznaKupowacNaRaty(przycisk.isSelected());
                    } else {
                        Vox sklep = (Vox) kontroler.getModel().sklep;
                        sklep.setCzyMoznaKupowacNaRaty(przycisk.isSelected());
                    }
                });

                add(przycisk);
            }

            if (marka == Sklepy.ikea) {
                add(new JLabel("Czy posiada restaurację?"));
                final JCheckBox przycisk = new JCheckBox();
                przycisk.addActionListener(e -> {
                    Ikea sklep = (Ikea) kontroler.getModel().sklep;
                    sklep.setCzyPosiadaRestauracje(przycisk.isSelected());
                });
                add(przycisk);
            }

            if (marka == Sklepy.vox) {
                add(new JLabel("Czy ma uporządkowane sekcje?"));
                final JCheckBox przycisk = new JCheckBox();
                przycisk.addActionListener(e -> {
                    Vox sklep = (Vox) kontroler.getModel().sklep;
                    sklep.setCzyMaUporzadkowaneSekcje(przycisk.isSelected());
                });

                add(przycisk);
            }

            if (marka == Sklepy.zabka) {
                add(new JLabel("Czy jest możliwość sprzedaży produktów na ciepło?"));
                final JCheckBox przycisk = new JCheckBox();
                przycisk.addActionListener(e -> {
                    Zabka sklep = (Zabka) kontroler.getModel().sklep;
                    sklep.setCzyJestMozliwoscSprzedazyProduktowNaCieplo(przycisk.isSelected());
                });
                add(przycisk);
            }
        }
    }

    private static class PanelSzczegolow extends JPanel {
        PanelSzczegolow(ShopCreatorController kontroler) {
            setLayout(new BorderLayout(10, 10));
            setBorder(new EmptyBorder(20, 20, 20, 20));
            JLabel tekst = new JLabel("Uzupełnij szczegóły");
            tekst.setHorizontalAlignment(JLabel.CENTER);
            add(tekst, BorderLayout.NORTH);
            add(new Formularz(kontroler), BorderLayout.CENTER);
            JButton przycisk = new JButton("Dalej");
            przycisk.addActionListener(e -> kontroler.zapiszSklep());
            add(przycisk, BorderLayout.SOUTH);
        }
    }

    private static class PoleTekstowe extends JTextField implements ActionListener {
        private final Consumer<String> setter;

        public PoleTekstowe(Consumer<String> setter) {
            this.setter = setter;
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setter.accept(getText());
        }
    }

    public enum EtapTworzeniaSklepu {
        wyborMarki, szczegoly;
    }
}