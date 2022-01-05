package gui.produktview;

import gui.oknaztabela.Koszyk;

import javax.swing.*;

public abstract class AbstractProduktComponent extends JComponent {

    protected Koszyk koszyk;

    public abstract void aktualizujIloscProduktow(int ilosc);

    public void setKoszyk(Koszyk koszyk) {
        this.koszyk = koszyk;
    }

}
