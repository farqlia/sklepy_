package strategie.strategiadostawy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import serializacja.Zamowienie;
import sklepy.Sklep;

public class DostawaRegularna implements StrategiaDostawy {

    private final DayOfWeek dzienDostaw;
    private LocalDate data = LocalDate.now();

    public DostawaRegularna(DayOfWeek dzienDostaw) {
        this.dzienDostaw = dzienDostaw;
    }

    @Override
    public void dostawa(Sklep sklep, ArrayList<Zamowienie> zamowienia) {
        if(data.getDayOfWeek()==dzienDostaw) {
            zamowienia.forEach(
                x -> {sklep.aktualizujIloscProduktow(x.getProdukt(), x.getIlosc());}
            );
        } else {
            System.out.println("Produkty zostaną dostarczone wraz z najbliższą dostawą w " + dzienDostaw);
        }

    }
}
