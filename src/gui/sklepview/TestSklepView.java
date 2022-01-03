package gui.sklepview;

import gui.ImageResizer;
import gui.produktview.AbstractProduktComponent;
import javax.swing.*;
import java.awt.*;

public class TestSklepView extends AbstractSklepView{

    JPanel produktyPanel;

    public TestSklepView(){

        setSize(800, 800);

        JPanel mainPanel = new JPanel();
        setLayout(null);
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("images/shopicons/history.png");

        // Tworzymy 'JButton', dodajemy ikonę
        icon = new ImageIcon(ImageResizer.getScaledInstance(icon.getImage(), 32, 32));
        JButton historiaTransakcjiButton = new JButton(icon);

        // Dodajemy słuchacza: teraz, gdy klikniemy ten przycisk to wyświetlą nam
        // się wszystkie transakcje
        historiaTransakcjiButton.addActionListener(new HistoriaTransakcjiHandler());
        JToolBar bar = new JToolBar();
        bar.add(Box.createGlue());
        bar.add(historiaTransakcjiButton);
        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createEtchedBorder());
        upperPanel.add(bar, BorderLayout.WEST);

        produktyPanel = new JPanel();
        produktyPanel.setBorder(BorderFactory.createEtchedBorder());
        produktyPanel.setLayout(new GridLayout(2, 2));

        // W tym przypadku BorderLayout sprawdza się dobrze
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(produktyPanel, BorderLayout.CENTER);

        //LayoutManager.addComp(mainPanel, upperPanel, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
        //LayoutManager.addComp(mainPanel, produktyPanel, 0, 1, 1, 4, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
    }

    @Override
    void placeProduktComponent(AbstractProduktComponent comp) {
        produktyPanel.add(comp);
        pack();
    }
}
