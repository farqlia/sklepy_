package sklepy;

import java.io.Serializable;

public class Zabka extends Sklep{

    private static final long serialVersionUID = 31L;

    boolean czyJestMozliwoscSprzedazyProduktowNaCieplo;
    boolean czyJestKasaSamoobslugowa;

    // KONSTRUKTORY:
    public Zabka(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo, String adres, String adresWWW, boolean czyJestKasaSamoobslugowa) {
        super(adres, adresWWW);
        this.czyJestMozliwoscSprzedazyProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
    }

    // GETTERY:

    public boolean getCzyJestMozliwoscSprzedazyProduktowNaCieplo() {
        return czyJestMozliwoscSprzedazyProduktowNaCieplo;
    }

    public boolean getCzyJestKasaSamoobslugowa() {
        return czyJestKasaSamoobslugowa;
    }

    //SETTERY:

    public void setCzyJestMozliwoscSprzedazyProduktowNaCieplo(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo) {
        this.czyJestMozliwoscSprzedazyProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
    }

    public void setCzyJestKasaSamoobslugowa(boolean czyJestKasaSamoobslugowa) {
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
    }

    //METODY:
    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        return godzina >= 6 && godzina <= 23;
    }

    public double sprzedajProduktZAplikacja(Produkt produkt, int ilosc) {
        sprzedajProdukt(produkt, ilosc);
        System.out.print("Punkty w aplikacji zostaly naliczone.\n");
        return produkt.getCena() * ilosc;
    }
}
