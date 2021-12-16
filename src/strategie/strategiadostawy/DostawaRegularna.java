package strategie.strategiadostawy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import serializacja.Zamowienie;
import sklepy.Sklep;

public class DostawaRegularna implements StrategiaDostawy {

    private final DayOfWeek dzienDostaw;
    private final LocalDate data = LocalDate.now();

    public DostawaRegularna(DayOfWeek dzienDostaw) {
        this.dzienDostaw = dzienDostaw;
    }

    // Jeśli jest dzień dostawy to produkty zostają dostarczone
    // W przeciwnym razie informujemy w który dzień zostanie wykonana dostawa
    @Override
    public void dostawa(Sklep sklep, ArrayList<Zamowienie> zamowienia) {
        if (data.getDayOfWeek() == dzienDostaw) {
            zamowienia.forEach(
                    x -> {
                        sklep.aktualizujIloscProduktow(x.getProdukt(), x.getIlosc());
                    }
            );
        } else {
            System.out.println("Produkty zostaną dostarczone wraz z najbliższą dostawą w " + dzienDostaw);
        }
    }
}
