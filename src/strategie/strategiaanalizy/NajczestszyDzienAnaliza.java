package strategie.strategiaanalizy;

import serializacja.Transakcja;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class NajczestszyDzienAnaliza implements Analityk<DayOfWeek> {

    @Override
    public DayOfWeek analizujDane(List<Transakcja> transakcje) {

        // W sytuacji, gdy nie mamy żadnych danych, to nie da się ustalić tej promocji
        // Lepiej jednak wygenerować jakieś dane wcześniej
        if (transakcje == null) {
            return null;
        }

        int[] freq = new int[7]; // 7 dni w tyg mamy

        transakcje
                .stream().filter(x -> testDay(x.getData(), 7))     // filtruje dane sprzed tygodnia
                // dni mają przypisane liczby 1 - 7, więc przesuwamy o jeden
                .forEach(x -> freq[x.getData().getDayOfWeek().getValue() - 1]++);

        int max = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > freq[max]) max = i;
        }
        return DayOfWeek.of(max + 1);
    }

    public boolean testDay(LocalDate date, int days){
        // True gdy data mieści się w zakresie
        return LocalDate.now().minusDays(days).compareTo(date) < 0;
    }
}
