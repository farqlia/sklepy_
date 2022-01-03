package gui.sklepview;

import javax.swing.*;
import java.awt.*;

public class HistoriaTransakcjiPanel extends JPanel {

    JDialog dialog;
    JTextArea displayArea;

    public HistoriaTransakcjiPanel(){

        JButton okButton = new JButton("OK");
        okButton.addActionListener(event ->
                dialog.setVisible(false));

        setLayout(new BorderLayout());

        displayArea = new JTextArea(30, 50);

        JScrollPane scrollPane = new JScrollPane(displayArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        JToolBar bar = new JToolBar();
        bar.add(Box.createGlue());
        bar.add(okButton, bar.getComponentCount() - 1);

        add(bar, BorderLayout.SOUTH);

    }

    public void setText(String text){
        displayArea.setText(text);
    }

    public void showDialog(Component parent){

        Frame owner;
        if (parent instanceof Frame){
            owner = (Frame) parent;
        } else {
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
        }
        if (dialog == null || dialog.getOwner() != owner) {
            dialog = new JDialog(owner, true);
            dialog.add(this);
            dialog.pack();
        }

        dialog.setVisible(true);

    }

}
