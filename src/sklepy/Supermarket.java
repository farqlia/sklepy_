package sklepy;

import serializacja.Transakcja;
import strategie.strategiagazetki.StrategiaGazetki;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Supermarket extends Sklep {

    private static final long serialVersionUID = 29L;

    private boolean czyMaKasySamoobslugowe;

    // Zbiór produktów poddawanych pewnym promocjom
    private final ArrayList<Produkt> gazetka;

    // HashMap przechowująca oryginalne ceny przedmiotów
    // W przypadku usunięcia gazetki, ceny przedmiotów przyjmują orygnialne wartości
    protected HashMap<String, Double> originalneCeny;

    protected StrategiaGazetki strategiaGazetki;

    public Supermarket(String adres, String adresWWW, boolean czyMaKasySamoobslugowe) {
        super(adres, adresWWW);
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
        gazetka = new ArrayList<>();
        originalneCeny = new HashMap<>();
    }

    // Metoda implementująca strategię promocji w danej gazetce
    public void otworzGazetke(StrategiaGazetki strategia) {
        strategiaGazetki = strategia;
        strategiaGazetki.gazetkowaPromocja();
    }

    // Metoda zamykająca gazetkę
    // W przypadku otwarcia nowej gazetki (nadpisania starej), wpierw zamknąć starą
    public void zamknijGazetke() {
        for (Produkt produkt : gazetka) {
            produkt.setCena(originalneCeny.get(produkt.getNazwa()));
        }
        gazetka.clear();
    }

    // Ze względu na specyfikę rabatu z gazetką musimy nadpisać tą metodę,
    // ponieważ
    public Transakcja sprzedajProdukt(Produkt produkt, int ilosc) {
        int aktualnaIlosc = sprawdzDostepnoscProduktu(produkt);

        if (ilosc > aktualnaIlosc) {
            ilosc = aktualnaIlosc;
        }

        aktualizujIloscProduktow(produkt, -ilosc);
        double cena;
        if (gazetka.contains(produkt)){
            cena = gazetka.get(gazetka.indexOf(produkt)).getCena() * ilosc;
        } else {
            cena = produkt.getCena() * ilosc;
        }

        return autoryzujTransakcje(produkt, ilosc, cena);
    }

    public void wyswietlGazetke() {
        System.out.println("W gazetce:");
        for (Produkt produkt : gazetka) {
            System.out.println(produkt.toString());
        }
    }

    //----------- Getters & Setters

    public void dodajDoGazetki(Produkt produkt) {
        if (gazetka.contains(produkt)) {
            System.out.println("Produkt znajduje się już w gazetce");
        } else {
            gazetka.add(produkt);
            originalneCeny.put(produkt.getNazwa(), produkt.getCena());
        }
    }

    public void usunZGazetki(Produkt produkt) {
        produkt.setCena(originalneCeny.get(produkt.getNazwa()));
        gazetka.remove(produkt);
        originalneCeny.remove(produkt.getNazwa());
    }

    public ArrayList<Produkt> getGazetka() {
        return gazetka;
    }

    public void setCzyMaKasySamoobslugowe(boolean czyMaKasySamoobslugowe) {
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
    }

    public boolean getCzyMaKasySamoobslugowe() {
        return czyMaKasySamoobslugowe;
    }
}
