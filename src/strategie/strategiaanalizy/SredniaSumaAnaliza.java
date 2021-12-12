package strategie.strategiaanalizy;

import serializacja.Transakcja;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SredniaSumaAnaliza implements Analityk<Double>{

    @Override
    public Double analizujDane(List<Transakcja> dane) {

        double suma = 0;
        for (Transakcja t : dane){
            suma += t.getSumaAktualna();
        }

        return (suma / dane.size());
    }
}
