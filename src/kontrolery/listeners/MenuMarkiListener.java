package kontrolery.listeners;

import kontrolery.ShopCreatorController;
import kontrolery.Sklepy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuMarkiListener implements ActionListener {
    public MenuMarkiListener(ShopCreatorController kontroler) {
        this.kontroler = kontroler;
    }

    private final ShopCreatorController kontroler;

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String marka = (String) cb.getSelectedItem();
        switch (marka) {
            case "Biedronka":
                kontroler.setWybranySklep(Sklepy.biedronka);
                break;
            case "Castorama":
                kontroler.setWybranySklep(Sklepy.castorama);
                break;
            case "Ikea":
                kontroler.setWybranySklep(Sklepy.ikea);
                break;
            case "Leroy Merlin":
                kontroler.setWybranySklep(Sklepy.leroyMerlin);
                break;
            case "Lidl":
                kontroler.setWybranySklep(Sklepy.lidl);
                break;
            case "Vox":
                kontroler.setWybranySklep(Sklepy.vox);
                break;
            case "Zabka":
                kontroler.setWybranySklep(Sklepy.zabka);
                break;
        }
    }
}
