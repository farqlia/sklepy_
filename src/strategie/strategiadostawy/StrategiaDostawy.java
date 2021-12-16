package strategie.strategiadostawy;

import java.util.ArrayList;
import serializacja.Zamowienie;
import sklepy.Sklep;

public interface StrategiaDostawy {
    // Niech zwraca true gdy dostarczono produkty i false gdy trzeba poczekać
    boolean zrobDostawe(Sklep sklep, ArrayList<Zamowienie> zamowienia);
}
