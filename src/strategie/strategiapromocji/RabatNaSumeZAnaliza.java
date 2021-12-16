package strategie.strategiapromocji;

import serializacja.Transakcja;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;

import java.io.Serializable;
import java.util.List;

public class RabatNaSumeZAnaliza implements StrategiaPromocji, Analityk<Double> {

    private static final long serialVersionUID = 15L;

    private double rabat;
    private Sklep sklep;

    public RabatNaSumeZAnaliza(Sklep sklep, double rabat){
        this.sklep = sklep;
        this.rabat = rabat;
    }

    @Override
    public Double analizujDane() {

        List<Transakcja> dane = sklep.getHistoriaTransakcji().getWszystko();
        double suma = 0;
        for (Transakcja t : dane){
            suma += t.getSumaAktualna();
        }

        return (suma / dane.size());
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {
        double normalnaSuma = produkt.getCena() * ilosc;
        return (normalnaSuma > analizujDane()) ? (1 - rabat) * normalnaSuma : normalnaSuma;
    }
}
