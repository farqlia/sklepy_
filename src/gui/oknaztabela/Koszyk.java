package gui.oknaztabela;

import sklepy.Produkt;
import gui.sklepview.ProduktEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Koszyk extends DialogsWithTables<ProduktEvent> {

    JButton kupProduktyButton;

    public Koszyk(){
        super(new String[]{"Produkt", "Cena", "Ilość"});

        JButton deleteRow = new JButton("Usuń pozycję");
        deleteRow.setToolTipText("Nakliknij raz na daną pozycję");
        deleteRow.addActionListener(new DeleteRowHandler());

        kupProduktyButton = new JButton("Do Kasy");
        addButton(deleteRow);
        addButton(kupProduktyButton);

    }

    // Dodajemy słuchacza bezpośrednio do przycisku, które wywołuje wydarzenie,
    // czyli sprzedaż produktów
    public void addListenerForKoszyk(ActionListener a){
        kupProduktyButton.addActionListener(a);
    }

    public void addPozycjeDoTabeli(ProduktEvent e) {
        tableModel.addRow(new Object[]{e.getProdukt().getNazwa(),
                e.getProdukt().getCena(), e.getIlosc()});
    }

    public List<ProduktEvent> getListeProduktowZKoszyka() {

        List<ProduktEvent> kupioneProdukty = new ArrayList<>();

        Vector v = (Vector) tableModel.getDataVector();
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--){
            Vector rowV = (Vector) v.elementAt(i);
            kupioneProdukty.add(new ProduktEvent(
                    new Produkt((String)rowV.elementAt(0), (double)rowV.elementAt(1)),
                    (int)rowV.elementAt(2)));
            tableModel.removeRow(i);
            }

        return kupioneProdukty;
    }

    private class DeleteRowHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();
            tableModel.removeRow(row);
        }
    }

}
