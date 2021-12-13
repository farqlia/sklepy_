package strategie.strategiapromocji;


import serializacja.Transakcja;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class RabatNaDzienTygodniaZAnaliza extends StrategiaPromocjiZAnaliza<DayOfWeek> {

    double rabat;

    public RabatNaDzienTygodniaZAnaliza(Sklep sklep, Analityk<DayOfWeek> analiza, double rabat){
        super(analiza, sklep);
        this.rabat = rabat;
    }

    public DayOfWeek getDzienRabatowy(){

        return analiza.analizujDane();
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {

        // Raczej taki dzień powinien byc ustalany raz na jakiś czas i to z
        // wykorzystaniem danych np. z zeszłego tygodnia, ale ten kod
        // ma tylko pokazać, że zadziała

        if (LocalDate.now().getDayOfWeek() == getDzienRabatowy()){
            return produkt.getCena() * ilosc * (1 - rabat);
        }

        return produkt.getCena() * ilosc;
    }
}

