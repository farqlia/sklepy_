package gui.ShopCreatorView;

import kontrolery.listeners.DalejListener;
import kontrolery.listeners.MenuMarkiListener;
import kontrolery.ShopCreatorController;

import javax.swing.*;
import java.util.Objects;

public class ShopCreatorView extends JFrame {

    public ShopCreatorView() {
        super("Kreator sklepu");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 800);
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
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(new JLabel("Wybierz markę sklepu"));
            add(listaMarek(kontroler));
            add(przyciskDalej(kontroler));
        }

        private JComboBox<String> listaMarek(ShopCreatorController kontroler) {
            final String[] nazwySklepow = {"Biedronka", "Castorama", "Ikea", "Leroy Merlin", "Lidl", "Vox", "Zabka"};
            final JComboBox<String> lista = new JComboBox<>(nazwySklepow);
            lista.setSelectedIndex(0);
            lista.addActionListener(new MenuMarkiListener(kontroler));
            return lista;
        }

        private JButton przyciskDalej(ShopCreatorController kontroler) {
            final JButton przycisk = new JButton("Dalej");
            przycisk.addActionListener(new DalejListener(kontroler));
            return przycisk;
        }
    }

    private static class PanelSzczegolow extends JPanel {
        public PanelSzczegolow(ShopCreatorController kontroler) {
            this.kontroler = kontroler;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(new JLabel("Wprowadź szczegóły sklepu"));
            add(poleAdres());
        }

        private JTextField poleAdres() {
            final JTextField pole = new JTextField();
            pole.setName("aaa");

            return pole;
        }

        private final ShopCreatorController kontroler;
    }

    private class PoleFormularza extends JPanel {
        public PoleFormularza(String podpis) {
            this.podpis = podpis;
        }

        final private String podpis;
    }

    public enum EtapTworzeniaSklepu {
        wyborMarki, szczegoly;
    }
}