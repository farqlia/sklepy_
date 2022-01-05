package gui.oknaztabela;

import sklepy.Produkt;
import wzorzecobserwator.Observable;
import wzorzecobserwator.Observer;
import wzorzecobserwator.ProduktEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

// W tym przypadku obserwującym jest widok sklepu, a koszyk obserwuje panele z produktami (podklasy AbstractProduktComponent)
public class Koszyk extends DialogsWithTables<ProduktEvent> implements Observer, Observable {

    private java.util.List<Observer> observers;

    public Koszyk(){
        super(new String[]{"Produkt", "Cena", "Ilość"});
        observers = new ArrayList<>();

        JButton deleteRow = new JButton("Usuń pozycję");
        deleteRow.setToolTipText("Nakliknij raz na daną pozycję");
        deleteRow.addActionListener(new DeleteRowHandler());

        JButton buyProductsButton = new JButton("Do Kasy");
        // Akurat w tym przypadku nie jest potrzebne przekazywanie jakiś informacji,
        // bo one są pobierane z tabeli
        buyProductsButton.addActionListener(e -> notifyObservers(null));

        addButton(deleteRow);
        addButton(buyProductsButton);

    }

    // Wywoływana, gdy jakiś element zostanie dodany do koszyka
    @Override
    public void update(ProduktEvent e) {
        addDataToTable(e);
    }

    @Override
    public void addDataToTable(ProduktEvent e) {
        tableModel.addRow(new Object[]{e.getProdukt().getNazwa(),
                e.getProdukt().getCena(), e.getIlosc()});
    }

    // W tym przypadku obserwatorem koszyka jest widok sklepu, i to do niego
    // przesyłamy wszystkie wybrane przez użytkownika pozycje (czyli obiekty klasy ProduktEvent)
    @Override
    public void notifyObservers(ProduktEvent e) {
        for (Observer observer : observers) {
            Vector v = (Vector) tableModel.getDataVector();
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--){
                Vector rowV = (Vector) v.elementAt(i);
                observer.update(new ProduktEvent(
                        new Produkt((String)rowV.elementAt(0), (double)rowV.elementAt(1)),
                        (int)rowV.elementAt(2)));
                tableModel.removeRow(i);
            }
        }
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    private class DeleteRowHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();
            tableModel.removeRow(row);
        }
    }

}
