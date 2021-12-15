package strategie.strategiagazetki;

import java.io.Serializable;
import java.util.ArrayList;

import sklepy.Produkt;

public interface StrategiaGazetki extends Serializable {

    // Metoda używana aby wprowadzic zmiany w cenach w gazetce
    public void gazetkowaPromocja();
}
