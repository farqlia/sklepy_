package strategie.strategiapromocji;

import serializacja.Transakcja;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;

import java.io.Serializable;
import java.util.List;

public class RabatNaSumeZAnaliza extends StrategiaPromocjiZAnaliza<Double> implements Serializable {

    private static final long serialVersionUID = 15L;

    private final double rabat;

    public RabatNaSumeZAnaliza(Sklep sklep, Analityk<Double> analiza, double rabat) {
        super(analiza, sklep);
        this.rabat = rabat;
    }

    public double getSumaRabatowa() {
        return analiza.analizujDane();
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {
        double normalnaSuma = produkt.getCena() * ilosc;
        return (normalnaSuma > getSumaRabatowa()) ? (1 - rabat) * normalnaSuma : normalnaSuma;
    }
}
