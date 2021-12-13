package strategie.strategiagazetki;

import java.util.ArrayList;

import sklepy.Produkt;

public interface StrategiaGazetki {

    // Metoda używana aby wprowadzic zmiany w cenach w gazetce
    public void gazetkowaPromocja(ArrayList<Produkt> gazetka);
}
