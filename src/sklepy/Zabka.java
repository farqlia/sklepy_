package sklepy;

import serializacja.Transakcja;
import strategie.strategiapromocji.RabatKartaKlienta;
import strategie.strategiapromocji.StrategiaPromocji;


public class Zabka extends Sklep{

    private static final long serialVersionUID = 31L;

    boolean czyJestMozliwoscSprzedazyProduktowNaCieplo;
    boolean czyJestKasaSamoobslugowa;
    private final StrategiaPromocji strategiaPromocjiZAplikacja;

    // KONSTRUKTORY:
    public Zabka(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo, String adres,
                 String adresWWW, boolean czyJestKasaSamoobslugowa) {
        super(adres, adresWWW);
        this.czyJestMozliwoscSprzedazyProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
        // Przyjmujemy, że zniżki dostają tylko użytkownicy aplikacji sklepu
        strategiaPromocjiZAplikacja = new RabatKartaKlienta(.1, null);
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

    public boolean czyProduktDoSprzedaniaNaCieplo(Produkt produkt){
        return  ((produkt.getNazwa().equalsIgnoreCase("hotdog")
                || produkt.getNazwa().equalsIgnoreCase("panini")
                || produkt.getNazwa().equalsIgnoreCase("kawa")));
    }

    public Transakcja sprzedajProdukt(Produkt produkt, int ilosc) {

        if (czyProduktDoSprzedaniaNaCieplo(produkt)){
            if (!czyJestMozliwoscSprzedazyProduktowNaCieplo){
                return null;
            }
        }
        return super.sprzedajProdukt(produkt, ilosc);
    }

    //METODY:
    @Override
    public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
        return godzina >= 6 && godzina <= 23;
    }

    public Transakcja sprzedajProduktZAplikacja(Produkt produkt, int ilosc) {

        strategiaPromocji = strategiaPromocjiZAplikacja;
        Transakcja t = sprzedajProdukt(produkt, ilosc);
        System.out.print("Punkty w aplikacji zostaly naliczone.\n");
        strategiaPromocji = null;
        return t;

    }
}
