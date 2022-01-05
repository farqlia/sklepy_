package kontrolery.listeners;

import kontrolery.ShopCreatorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DalejListener implements ActionListener {
    public DalejListener(ShopCreatorController kontroler) {
        this.kontroler = kontroler;
    }

    ShopCreatorController kontroler;

    @Override
    public void actionPerformed(ActionEvent e) {
        kontroler.przejdzDoSzczegolow();
    }
}