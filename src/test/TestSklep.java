package test;

import serializacja.Transakcja;
import sklepy.DniTygodnia;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiapromocji.RabatKartaKlienta;
import strategie.strategiapromocji.RabatNaDzienTygodniaZAnaliza;
import strategie.strategiapromocji.StrategiaPromocji;

// Przykładowa klasa
public class TestSklep extends Sklep {

    // Zmienne są typu interfejsu, tak jak jest w strategii
    private StrategiaPromocji oryginalnaStrategiaPromocji;
    private StrategiaPromocji strategiaPromocjiZKarta;

    private double oryginalnyRabat = 0.1;
    private double rabatKartaKlienta = 0.05;

    public TestSklep(String adres, String adresWWW){
        super(adres, adresWWW);
        oryginalnaStrategiaPromocji = new RabatNaDzienTygodniaZAnaliza(this, oryginalnyRabat);
        strategiaPromocjiZKarta = new RabatKartaKlienta(rabatKartaKlienta, oryginalnaStrategiaPromocji);
    }

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        switch (dzienTygodnia) {
            case DniTygodnia.SOBOTA:
            case DniTygodnia.NIEDZIELA:
                return false;
            default:
                return (godzina >= 6 && godzina <= 22);
        }
    }

    // Potrzebujemy tej metody, gdy mamy więcej niż jedną metodę sprzedającą produkty
    // W zasadzie to można też przyjąć, że w przyszłości planujemy dodać więcej strategii promocji,
    // więc ta metoda też miałaby sens
    public Transakcja zainicjujTransakcje(Produkt produkt, int ilosc, StrategiaPromocji strategiaPromocji){

        int dotychczasowaIlosc = sprawdzDostepnoscProduktu(produkt);
        if (ilosc > dotychczasowaIlosc){
            ilosc = dotychczasowaIlosc;
        }

        aktualizujIloscProduktow(produkt, -ilosc);

        return autoryzujTransakcje(produkt, ilosc,
                strategiaPromocji.naliczRabat(produkt, ilosc));
    }

    @Override
    public Transakcja sprzedajProdukt(Produkt produkt, int ilosc) {
        return zainicjujTransakcje(produkt, ilosc, oryginalnaStrategiaPromocji);
    }

    public Transakcja sprzedajZKartaKlienta(Produkt produkt, int ilosc) {
        return zainicjujTransakcje(produkt, ilosc, strategiaPromocjiZKarta);
    }

    public void zmienStrategie(StrategiaPromocji strategiaPromocji){
        oryginalnaStrategiaPromocji = strategiaPromocji;
        // Nowa promocja dotyczy też rabatów na karte klienta
        strategiaPromocjiZKarta = new RabatKartaKlienta(rabatKartaKlienta, oryginalnaStrategiaPromocji);
    }

}

