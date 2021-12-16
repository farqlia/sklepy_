package strategie.strategiapromocji;

import sklepy.Produkt;

// tu bez żadnej analizy danych
public class RabatNaSumeBezAnalizy implements StrategiaPromocji {

    private static final long serialVersionUID = 14L;

    private final double rabat;
    private final double minSuma;

    public RabatNaSumeBezAnalizy(double rabat, double minSuma) {
        this.rabat = rabat;
        this.minSuma = minSuma;
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {
        double sumaBezRabatu = produkt.getCena() * ilosc;
        return (sumaBezRabatu >= minSuma) ? (1 - rabat) * sumaBezRabatu : sumaBezRabatu;
    }
}
