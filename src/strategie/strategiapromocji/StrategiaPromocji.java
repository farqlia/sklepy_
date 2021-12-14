package strategie.strategiapromocji;

import sklepy.Produkt;

import java.io.Serializable;

public interface StrategiaPromocji extends Serializable {
    double naliczRabat(Produkt produkt, int ilosc);
}
