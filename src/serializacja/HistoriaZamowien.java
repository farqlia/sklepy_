package serializacja;

import java.util.List;

public class HistoriaZamowien extends Historia {

    private static final long serialVersionUID = 98L;

    public HistoriaZamowien(String nazwaDostawcy) {
        super(nazwaDostawcy);
        this.mainPath = "historiezamowien/";
    }

    @Override
    public void dodaj(Object objekt) {
        Zamowienie zamowienie = (Zamowienie) objekt;
        super.dodaj(zamowienie);
    }

    @Override
    public List<Object> getWszystko(Object objekt) {
        Zamowienie zamowienie = (Zamowienie) objekt;
        return super.getWszystko(zamowienie);
    }

}
