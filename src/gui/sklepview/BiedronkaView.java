package gui.sklepview;

import gui.ImageResizer;
import gui.produktview.AbstractProduktComponent;
import serializacja.Transakcja;

import javax.swing.*;
import java.awt.*;

public class BiedronkaView extends AbstractSklepView{

    private JPanel produktyPanel;

    public BiedronkaView(){

        super("Biedronka");
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

        icon = new ImageIcon("images/shopicons/basket3.png");
        // Tworzymy 'JButton', dodajemy ikonę
        icon = new ImageIcon(ImageResizer.getScaledInstance(icon.getImage(), 32, 32));

        JButton koszykButton = new JButton(icon);
        koszykButton.addActionListener(new KoszykHandler());

        icon = new ImageIcon("images/shopicons/new-icon.png");
        // Tworzymy 'JButton', dodajemy ikonę
        icon = new ImageIcon(ImageResizer.getScaledInstance(icon.getImage(), 32, 32));

        JButton kreatorProduktowButton = new JButton(icon);
        kreatorProduktowButton.addActionListener(e -> getKreatorProduktow().zrobGUI());

        JToolBar bar = new JToolBar();
        bar.add(Box.createGlue());
        bar.add(historiaTransakcjiButton);
        bar.add(koszykButton);
        bar.add(kreatorProduktowButton);
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
