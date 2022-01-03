package gui;

import javax.swing.*;
import java.awt.*;

public class LayoutManager {

    // Aby używać tej metody do dodawania komponentów, menadżerem rozkładu
    // powinien być 'GridBagLayout'
    // Ten menadżer daje dość duże możliwości jeśli chodzi o ustawianie komponentów tam, gdzie
    // chcemy
    public static void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){

        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);
    }

}
