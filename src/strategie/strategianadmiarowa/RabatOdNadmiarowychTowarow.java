package strategie.strategianadmiarowa;

import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiapromocji.StrategiaPromocji;

import java.util.Map;

public class RabatOdNadmiarowychTowarow implements StrategiaPromocji {

    private static final long serialVersionUID = 17L;

    public RabatOdNadmiarowychTowarow(Sklep sklep) {
        this.sklep = sklep;
    }

    private final Sklep sklep;

    public double naliczRabat(Produkt produkt, int ilosc) {

        Map<Produkt, Integer> magazyn;
        magazyn = sklep.getStanMagazynu();

        if (magazyn.get(produkt) > 1000) {
            return produkt.getCena() * ilosc * 0.8;
        } else {
            return produkt.getCena() * ilosc;
        }
    }
}
