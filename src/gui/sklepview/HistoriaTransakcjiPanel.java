package gui.sklepview;

import serializacja.Transakcja;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class HistoriaTransakcjiPanel extends JPanel {

    JDialog dialog;
    JTable table;

    AbstractTableModel model = new TableModel();

    public HistoriaTransakcjiPanel(){

        JButton okButton = new JButton("OK");
        okButton.addActionListener(event ->
                dialog.setVisible(false));

        setLayout(new BorderLayout());

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        JToolBar bar = new JToolBar();
        bar.add(Box.createGlue());
        bar.add(okButton, bar.getComponentCount() - 1);

        add(bar, BorderLayout.SOUTH);

    }

    public void sendData(java.util.List<Transakcja> transakcje) {
        for (int i = model.getRowCount(); i < transakcje.size(); i++) {
            Transakcja t = transakcje.get(i);
            model.setValueAt(t, i, 0);
        };
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

    private static class TableModel extends AbstractTableModel{

        private final String[] columnNames = {"Data", "Produkt", "Ilość", "Cena"};
        int DEFAULT_CAPACITY = 20;
        int numOfCols = 4;
        int currentLength = 0;
        Object[][] data = new Object[DEFAULT_CAPACITY][numOfCols];

        @Override
        public Class<?> getColumnClass(int column){
            return data[0][column].getClass();
        }
        @Override
        public int getRowCount(){
            return currentLength;
        }
        @Override
        public int getColumnCount(){
            return numOfCols;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex){
            return data[rowIndex][columnIndex];
        }
        @Override
        public void setValueAt(Object value, int row, int col){
            ensureCapacity();
            Transakcja t = ((Transakcja)(value));
            data[row] = new Object[]{t.getData(), t.getProdukt().getNazwa(), t.getIlosc(), t.getSumaAktualna()};
            currentLength++;
            fireTableChanged(new TableModelEvent(this));
;        }

        @Override
        public String getColumnName(int columnIndex){
            return columnNames[columnIndex];
        }

        private void ensureCapacity(){
            if (currentLength == DEFAULT_CAPACITY){
                Object[][] biggerArr = new Object[2 * DEFAULT_CAPACITY][numOfCols];
                System.arraycopy(data, 0, biggerArr, 0, currentLength);
                data = biggerArr;
            }
        }
    }

}
