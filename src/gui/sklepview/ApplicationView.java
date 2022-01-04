package gui.sklepview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

import static gui.ImageResizer.getScaledInstance;

// Główny widok, przez który będziemy wywoływać widoki sklepów
public class ApplicationView extends JFrame {

    private final String pathToIcons = "images/shopicons/";

    private JToolBar buttonBar = new JToolBar();
    private Map<AbstractSklepView, String> sklepyINazwyIkon;

    public ApplicationView(Map<AbstractSklepView, String> sklepyINazwyIkon){

        setSize(800, 800);
        setLocationRelativeTo(null);
        this.sklepyINazwyIkon = sklepyINazwyIkon;
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Dodaje niewidoczne komponenty, które zajmują przestrzeń: będziemy
        // dodawać obiekty 'JButton' pomiędzy nimi i dzięki temu będą one wyśrodkowane
        buttonBar.add(Box.createGlue());
        buttonBar.add(Box.createGlue());

        add(buttonBar, BorderLayout.CENTER);

        // Rozpoczyna wątek roboczy, który tworzy obiekty 'JButton'
        loadimages.execute();

    }

    // Obiekt który wykonuje czasochłonne zadania
    // Oparte na kodzie : https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/IconDemoProject/src/components/IconDemoApp.java
    private SwingWorker<Void, OpenShopViewAction> loadimages = new SwingWorker<Void, OpenShopViewAction>() {
        @Override
        protected Void doInBackground() {

            for (Map.Entry<AbstractSklepView, String> entry : sklepyINazwyIkon.entrySet()){
                ImageIcon icon = new ImageIcon(pathToIcons + entry.getValue());
                icon = new ImageIcon(getScaledInstance(icon.getImage(), 50, 50));

                OpenShopViewAction action = new OpenShopViewAction(entry.getKey(), icon);
                publish(action);
            }
            return null;
        }

        @Override
        protected void process(java.util.List<OpenShopViewAction> chunks) {
            for (OpenShopViewAction thumbAction : chunks) {
                JButton actionButton = new JButton(thumbAction);
                buttonBar.add(actionButton, buttonBar.getComponentCount() - 1);
            }
        }

    };

    private static class OpenShopViewAction extends AbstractAction {

        OpenShopViewAction(AbstractSklepView view, Icon icon){
            putValue(Action.SMALL_ICON, icon);
            putValue("VIEW", view);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Otwiera widok sklepu
            ((AbstractSklepView)getValue("VIEW")).setVisible(true);
        }
    }

}
