package sklepy;

import pracownicy.Pracownik;
import serializacja.Historia;
import serializacja.Transakcja;
import strategie.strategiapromocji.StrategiaPromocji;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Sklep implements Serializable {

    private static final long serialVersionUID = 21L;

    private String adres;
    private String adresWWW;
    private List<Pracownik> pracownicy;

    // To jest struktura która będzie każdemu produktowi
    // przyporządkowywać jego ilość w sklepie
    private Map<Produkt, Integer> magazyn;

    protected StrategiaPromocji strategiaPromocji;
    protected Historia<Transakcja> historiaTransakcji;

    public Sklep() {};

    public Sklep(String adres, String adresWWW) {
        this.adres = adres;
        this.adresWWW = adresWWW;
        historiaTransakcji = new Historia<>("historia/historiatransakcji/", idSklepu());
        pracownicy = new ArrayList<>();
        magazyn = new HashMap<>();
    }

    public abstract boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina);

    // W tej zmianie musimy jednak zrezygnować z wywoływania tej metody
    // w innych metodach sprzedających, by ta sama transakcja nie była zapisana dwa razy
    // Powodem zmiany było to, że inaczej nie jesteśmy w stanie zapisać tej transakcji
    // Ale w sumie też ma sens to zwracać, bo reprezentuje taki jakby rachunek/paragon
    // Aby stworzyć obiekt Transakcji, wywołujemy na końcu metodę 'autoryzujTransakcje', wtedy sie ona zapisze
    // w historii
    public Transakcja sprzedajProdukt(Produkt produkt, int ilosc) {
        int aktualnaIlosc = sprawdzDostepnoscProduktu(produkt);

        if (ilosc > aktualnaIlosc) {
            ilosc = aktualnaIlosc;
        }

        aktualizujIloscProduktow(produkt, -ilosc);
        double cena;
        if (strategiaPromocji == null) {
            cena = produkt.getCena() * ilosc;
        } else {
            cena = strategiaPromocji.naliczRabat(produkt, ilosc);
        }

        return autoryzujTransakcje(produkt, ilosc, cena);
    }

    public Transakcja autoryzujTransakcje(Produkt produkt, int ilosc, double cena) {
        if (ilosc == 0) {
            return null;
        }
        Transakcja t = new Transakcja(produkt, ilosc, cena);
        historiaTransakcji.dodaj(t);
        return t;
    }

    // Tworzy nazwę w stylu : nazwa sklepu + adres sklepu, tak aby
    // było to unikalne dla każdego sklepu
    // Można to tez wykorzystac do tworzenia nazw plików, gdy będziemy
    // stosować serializację
    // działa jeśli adresy www stron to cos w stylu 'biedronka.pl'
    public String idSklepu() {
        return adresWWW.split("\\.")[0] +
                adres.replaceAll("[\\s,.]", "");
    }

    public void zmienStrategie(StrategiaPromocji strategiaPromocji) {
        this.strategiaPromocji = strategiaPromocji;
    }
    //------------------------------------------------------------------------

    // To uaktualnia stan produktu na 'magazynie'
    // Jeśli ilość jest dodatnia, to oznacza dostawę produktu
    // Jeśli ujemna oznacza sprzedaż, czyli zmniejszamy ilość produktów na 'magazynie'
    public void aktualizujIloscProduktow(Produkt produkt, int ilosc) {

        int aktualnaIlosc = 0;

        // Jeśli ten produkt jest już na magazynie, to musimy to też uwzględnić
        if (magazyn.containsKey(produkt)) {
            aktualnaIlosc = magazyn.get(produkt);
        }
        magazyn.put(produkt, aktualnaIlosc + ilosc);
    }

    // Ta metoda służy sprawdzeniu, czy sklep dysponuje jakąś ilością produktów.
    // Powinna być użyta w funkcji sprzedajProdukt() do upewnienia się,
    // że można go sprzedać w tej ilości
    public int sprawdzDostepnoscProduktu(Produkt produkt) {
        return magazyn.getOrDefault(produkt, 0);
    }

    public void zrekrutuj(Pracownik pracownik) {
        pracownicy.add(pracownik);
    }
    
     public void zwolnij(Pracownik pracownik) {
    	pracownicy.remove(pracownik);
    }

    // ------------------- GETTERS & SETTERS -----------------------------

    public Map<Produkt, Integer> getStanMagazynu() {
        return magazyn;
    }

    public Historia<Transakcja> getHistoriaTransakcji() {
        return historiaTransakcji;
    }

    public void wyswietlPracownikow() {
        for (Pracownik pracownik : pracownicy) {
            System.out.println(pracownik);
        }
    }

    public void wyswietlOferteSklepu() {
        for (Map.Entry<Produkt, Integer> el : magazyn.entrySet()) {
            System.out.println(el.getKey() + ", ilosc=" + el.getValue());
        }
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getAdresWWW() {
        return adresWWW;
    }

    public void setAdresWWW(String adresWWW) {
        this.adresWWW = adresWWW;
    }

    public int getAktualnaIloscPracownikow() {
        return pracownicy.size();
    }
}

