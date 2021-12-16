package sklepy;

import java.util.Objects;

public class Zabka extends Sklep {

    private static final long serialVersionUID = 31L;

    boolean sprzedazProduktowNaCieplo;
    boolean czyJestKasaSamoobslugowa;

    // KONSTRUKTOR:

    public Zabka(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo, String adres, String adresWWW, boolean czyJestKasaSamoobslugowa) {
        super(adres, adresWWW);
        this.sprzedazProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
    }

    // GETTERY:

    public boolean getSprzedazProduktowNaCieplo() {
        return sprzedazProduktowNaCieplo;
    }

    public boolean getCzyJestKasaSamoobslugowa() {

        return czyJestKasaSamoobslugowa;
    }

    //SETTERY:

    public void setCzyJestMozliwoscSprzedazyProduktowNaCieplo(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo) {
        this.sprzedazProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
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

        if ((Objects.equals(produkt.getNazwa(), "hotdog") || Objects.equals(produkt.getNazwa(), "panini") || Objects.equals(produkt.getNazwa(), "kawa")) && !sprzedazProduktowNaCieplo) {

            System.out.print("Mozliwosc sprzedazy produktow na cieplo nie jest aktualnie mozliwa.\n");
            return 0;
        } else {
            //przy sprzedaży produktów z aplikacją, zostaje naliczony rabat
            sprzedajProdukt(produkt, ilosc);
            System.out.print("Punkty w aplikacji zostaly naliczone.\n");
            return strategiaPromocji.naliczRabat(produkt, ilosc);
        }
    }
}
