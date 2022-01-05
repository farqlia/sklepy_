package gui.oknaztabela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Koszyk i Okno z historią transakcji są dosyć podobne,
// więc mają wspólną nadklasę
public abstract class DialogsWithTables<T> extends JPanel {

    JDialog dialog;
    JTable table;
    private Object[][] data;
    DefaultTableModel tableModel;
    // Do tego mogą podklasy dodawać przyciski, które rozszerzą funkcjonalność tabeli
    private JToolBar toolBar;

    public abstract void addPozycjeDoTabeli(T object);

    public DialogsWithTables(String[] columnNames){

        tableModel = new DefaultTableModel(data, columnNames){
            // Zwraca prawdziwy typ obiektu, inaczej wszystko jest traktowane jako Object
            @Override
            public Class<?> getColumnClass(int column){
                return getValueAt(0, column).getClass();
            }
        };
        table = new JTable(tableModel);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(event ->
                dialog.setVisible(false));

        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        toolBar = new JToolBar();
        toolBar.add(Box.createGlue());
        toolBar.add(Box.createGlue());

        add(toolBar, BorderLayout.SOUTH);
    }

    protected void addButton(JButton button){
        // Dodaje na środku
        toolBar.add(button, toolBar.getComponentCount() - 1);
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
            // Dodaje ten panel do okna
            dialog.add(this);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
        }

        dialog.setVisible(true);

    }



}
