package strategie.strategiapromocji;


import serializacja.Transakcja;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class RabatNaDzienTygodniaZAnaliza implements StrategiaPromocji, Analityk<DayOfWeek> {

    private double rabat;
    private Sklep sklep;

    public RabatNaDzienTygodniaZAnaliza(Sklep sklep,  double rabat){
        this.rabat = rabat;
        this.sklep = sklep;
    }

    @Override
    public DayOfWeek analizujDane() {

        List<Transakcja> transakcje = sklep.getHistoriaTransakcji().getWszystko();

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

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {

        // Raczej taki dzień powinien byc ustalany raz na jakiś czas i to z
        // wykorzystaniem danych np. z zeszłego tygodnia, ale ten kod
        // ma tylko pokazać, że zadziała

        if (LocalDate.now().getDayOfWeek() == analizujDane()){
            return produkt.getCena() * ilosc * (1 - rabat);
        }

        return produkt.getCena() * ilosc;
    }
}

