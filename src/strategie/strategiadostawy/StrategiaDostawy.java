package strategie.strategiadostawy;

import java.util.ArrayList;

import serializacja.Zamowienie;
import sklepy.Sklep;

public interface StrategiaDostawy {
    void dostawa(Sklep sklep, ArrayList<Zamowienie> zamowienia);
}
