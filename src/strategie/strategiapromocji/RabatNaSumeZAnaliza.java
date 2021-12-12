package strategie.strategiapromocji;

import serializacja.Transakcja;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;

import java.util.List;

public class RabatNaSumeZAnaliza extends StrategiaPromocjiZAnaliza<Double>{

    private double rabat;

    public RabatNaSumeZAnaliza(Sklep sklep, Analityk<Double> analiza, double rabat){
        super(analiza, sklep);
        this.rabat = rabat;
    }

    public double getSumaRabatowa(){
        List<Transakcja> transakcje = sklep.getHistoriaTransakcji().getWszystkieTransakcje();
        return analiza.analizujDane(transakcje);
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {
        double normalnaSuma = produkt.getCena() * ilosc;
        return (normalnaSuma > getSumaRabatowa()) ? (1 - rabat) * normalnaSuma : normalnaSuma;
    }
}
