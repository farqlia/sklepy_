package gui.oknaztabela;

import serializacja.Transakcja;

import java.util.List;

public class HistoriaTransakcjiDialog extends DialogsWithTables<List<Transakcja>> {

    public HistoriaTransakcjiDialog(){

        super(new String[]{"Data", "Produkt", "Ilość", "Cena"});

    }

    @Override
    public void addDataToTable(java.util.List<Transakcja> transakcje) {
        for (int i = tableModel.getRowCount(); i < transakcje.size(); i++) {
            Transakcja t = transakcje.get(i);
            Object[] newRow = new Object[]{t.getData(), t.getProdukt().getNazwa(), t.getIlosc(), t.getSumaAktualna()};
            tableModel.addRow(newRow);
        }

    }

}
