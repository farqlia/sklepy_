package gui.kreator;

import java.awt.event.ActionListener;

public interface KreatorInterfejs<E> {

    void zrobGUI();

    E getStworzonyObiekt() throws IllegalArgumentException;

    void addStworzObiektListener(ActionListener listener);

}
