package strategie.strategiadostawy;

import serializacja.Zamowienie;
import sklepy.Sklep;

import java.util.ArrayList;

public interface StrategiaDostawy {
    // Niech zwraca true gdy dostarczono produkty i false gdy trzeba poczekać
    boolean dostawa(Sklep sklep, ArrayList<Zamowienie> zamowienia);
}
