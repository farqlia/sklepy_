package sklepy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class SklepBudowniczy extends Sklep{

    private static final long serialVersionUID = 27L;


    // Lista działów sklepu budowlanego
    // Porządkujących jego produkty
    private final ArrayList<String> dzialy;

    public SklepBudowniczy(String adres, String adresWWW) {
        super(adres, adresWWW);
        dzialy = new ArrayList<>();
    }

    // HashMap pozwola na przyporządkowanie produktu do działu
    protected HashMap<Produkt, String> dzialProduktu = new HashMap<>();

    // Metoda przyporządkująca produkt do działu
    // Wymagana dla samego produktu, nie każdej jego sztuki
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

    // Metoda kontrolująca przypisanie obiektów do działów
    // W razie sprzedaży przedmiotu i jego braku na magazynie, usuwa go
    // TODO Implementowana w podklasach w metodzie sprzedaży
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
