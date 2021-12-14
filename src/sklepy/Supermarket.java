package sklepy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import strategie.strategiagazetki.StrategiaGazetki;

public abstract class Supermarket extends Sklep{

    private static final long serialVersionUID = 29L;


    private boolean czyMaKasySamoobslugowe;

    // Typowa biedronkowa gazetka gdzie wszystko jest -50%
    private final ArrayList<Produkt> gazetka;

    // HashMap sluzaca do przechowania oryginalnych cen
    // aby mozna zmienic cene podczas przebywania w gazetce a potem
    // ja przywrocic
    protected HashMap<String, Double> originalneCeny;

    // protected czyli podklas to dziedziczą
    protected StrategiaGazetki strategiaGazetki;

    public Supermarket(String adres, String adresWWW) {
        super(adres, adresWWW);
        gazetka = new ArrayList<>();
        originalneCeny = new HashMap<>();
    }

    // W kwestii rabatow itp - w tej metodzie chcialbym ustalic
    // wspolne rabaty dla rzeczy w gazetce
    // np setCena(getCena()*0.8)
    public void otworzGazetke(StrategiaGazetki strategia) {
        strategiaGazetki = strategia;
        strategiaGazetki.gazetkowaPromocja(gazetka);
    }

    // Po kazdym otwarciu gazetki trzeba ja zamknac
    // aby np moc stworzyc nowa
    public void zamknijGazetke() {
        for(Produkt produkt : gazetka) {
            produkt.setCena(originalneCeny.get(produkt.getNazwa()));
        }
        gazetka.clear();
    }

    public void wyswietlGazetke() {
        System.out.println("W gazetce:");
        for(Produkt produkt : gazetka) {
            System.out.println(produkt.toString());
        }
    }

    //----------- getters & setters

    public void dodajDoGazetki(Produkt produkt) {
        if(gazetka.contains(produkt)) {
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
