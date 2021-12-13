package strategie.strategiagazetki;

import java.time.DayOfWeek;
import java.util.List;

import serializacja.Transakcja;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;
import java.time.LocalDate;

public class RabatGazetkowyAnaliza extends StrategiaGazetkiZAnaliza<DayOfWeek> {

    private double rabat;
    private LocalDate ostatniaAnaliza;
    private DayOfWeek ostatniDzienRabatowy;

    public RabatGazetkowyAnaliza(Analityk<DayOfWeek> analiza, Sklep sklep, double rabat) {
        super(analiza, sklep);
        this.rabat = rabat;
    }

    public DayOfWeek getDzienRabatowy() {
        if (LocalDate.now().minusDays(7).compareTo(ostatniaAnaliza) > 0){
            ostatniaAnaliza = LocalDate.now();
            return ostatniDzienRabatowy = analiza.analizujDane();
        }
        return ostatniDzienRabatowy;
    }

    @Override
    public double rabat() {
        if (LocalDate.now().getDayOfWeek() == getDzienRabatowy()){
            return (1 - rabat);
        }
        return 1;
    }

}
