package sklepy;

import serializacja.Historia;
import serializacja.Zamowienie;
import strategie.strategiadostawy.DostawaRegularna;
import strategie.strategiadostawy.StrategiaDostawy;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class FirmaDostawcza implements Serializable {

    private String nazwaFirmy;
    private int czasDostawy;
    private String adresSiedziby;
    private StrategiaDostawy strategiaDostawy;
    private final Historia<Zamowienie> historiaZamowien;

    // Klienci firmy i ich lista zamówień
    private final HashMap<Sklep, ArrayList<Zamowienie>> listaKlientow;

    private static final long serialVersionUID = 99L;

    // typical konstruktor
    public FirmaDostawcza(String nazwaFirmy, int czasDostawy, DayOfWeek dzienDostaw, String adresSiedziby) {
        this.nazwaFirmy = nazwaFirmy;
        this.czasDostawy = czasDostawy;
        this.adresSiedziby = adresSiedziby;
        this.historiaZamowien = new Historia<>("historiazamowien/", nazwaFirmy);
        this.listaKlientow = new HashMap<>();
        this.strategiaDostawy = new DostawaRegularna(dzienDostaw);
    }

    // Zapisuje zamówienia klienta i jeżeli jest spełniona określona strategia, produkty są dostarczane
    // W innym wypadku tylko spisuje listę zamówień
    public void dostarczProdukty(Sklep sklep, Produkt produkt, int ilosc) {

        Zamowienie zamowienie = new Zamowienie(produkt, ilosc, sklep.idSklepu());

        if (!listaKlientow.containsKey(sklep)) {
            listaKlientow.put(sklep, new ArrayList<>());
        }

        listaKlientow.get(sklep).add(zamowienie);

        if (strategiaDostawy.dostawa(sklep, listaKlientow.get(sklep))){

            // Gdy udało się dostarczyć produkty, to usuwamy zrealizowanie zamowienia
            listaKlientow.get(sklep).clear();

            // Zapisujemy w pliku zamowienie
            historiaZamowien.serializuj(zamowienie);
        }

    }

    // settery/gettery

    public Historia<Zamowienie> getHistoriaZamowien() {
        return historiaZamowien;
    }

    public void setStrategiaDostawy(StrategiaDostawy strategiaDostawy) {
        this.strategiaDostawy = strategiaDostawy;
    }

    public HashMap<Sklep, ArrayList<Zamowienie>> getListaKlientow() {
        return listaKlientow;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    public void setCzasDostawy(int czasDostawy) {
        this.czasDostawy = czasDostawy;
    }

    public void setAdresSiedziby(String adresSiedziby) {
        this.adresSiedziby = adresSiedziby;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public int getCzasDostawy() {
        return czasDostawy;
    }

    public String getAdresSiedziby() {
        return adresSiedziby;
    }

}
