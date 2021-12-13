package strategie.strategiaanalizy;

import serializacja.Transakcja;
import sklepy.Sklep;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SredniaSumaAnaliza implements Analityk<Double>{

    Sklep sklep;
    public SredniaSumaAnaliza(Sklep sklep){
        this.sklep = sklep;
    }
    @Override
    public Double analizujDane() {

        List<Transakcja> dane = sklep.getHistoriaTransakcji().getWszystkieTransakcje();
        double suma = 0;
        for (Transakcja t : dane){
            suma += t.getSumaAktualna();
        }

        return (suma / dane.size());
    }
}
