package sklepy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class SklepBudowniczy extends Sklep implements Serializable {

    private static final long serialVersionUID = 27L;


    // To bedzie lista dzialow sklepu budowlanego,
    // aby wszystkie przedmioty byly uporzadkowane
    private final ArrayList<String> dzialy;

    public SklepBudowniczy(String adres, String adresWWW) {
        super(adres, adresWWW);
        dzialy = new ArrayList<>();
    }

    // HashMap pozwoli nam na dodawanie przedmiotow do dzialow
    // Kazdy przedmiot bedzie mial przyporzadkowany dzial

    // Nie zrobilem getterow i setterow dla Mapy,
    // bo uznalem ze protected zalatwi sprawe
    protected HashMap<Produkt, String> dzialProduktu = new HashMap<>();

    // Ta metoda wymaga wprowadzenie tylko jednego przedmiotu
    // W sensie dodajemy 2 krzesla do sklepu ale jedno do dzialu
    public void dodajProduktDoDzialu(Produkt produkt, String dzial) {
        if (dzialy.contains(dzial)) {
            if (dzialProduktu.containsKey(produkt)) {
                System.out.println("Produkt jest już przypisany do działu");
            } else {
                dzialProduktu.put(produkt, dzial);
            }
        } else {
            System.out.println("Proszę wybrać poprawny dział");
        }
    }

    // Prosilbym zaimplementowac te metode w konkrentnych klasach
    // odnosnie abstrakcyjnej metody sprzedazy
    // aby w razie braku produktu na stanie wykreslic go z dzialu
    public void usunProduktZDzialu(Produkt produkt) {
        if (sprawdzDostepnoscProduktu(produkt) == 0) {
            dzialProduktu.remove(produkt);
        }
    }

    public void wyswietlDzialProduktu(Produkt produkt) {
        System.out.println(dzialProduktu.get(produkt));
    }

    public void wyswietlDzialy() {
        for (String dzial : dzialy) {
            System.out.println(dzial);
        }
    }

    public String getDzialProduktu(Produkt p){
        return dzialProduktu.getOrDefault(p, null);
    }

    //------------- Getters & Setters

    public ArrayList<String> getDzialy() {
        return dzialy;
    }

    public void dodajDzial(String dzial) {
        dzialy.add(dzial);
    }

    public void usunDzial(String dzial) {
        dzialy.remove(dzial);
    }
}
