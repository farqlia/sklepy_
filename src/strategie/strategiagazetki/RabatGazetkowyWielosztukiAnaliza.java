package strategie.strategiagazetki;

import java.io.Serializable;
import strategie.strategiaanalizy.Analityk;
import sklepy.Supermarket;
import java.util.List;
import serializacja.Transakcja;

public class RabatGazetkowyWielosztukiAnaliza implements StrategiaGazetki, Analityk<List<Transakcja>> {
    
    private static final long serialVersionUID = 9L;

    private double rabat;

    private Supermarket sklep;

    public RabatGazetkowyWielosztukiAnaliza(Supermarket sklep) {
        this.sklep = sklep;
    }

    // Metoda analizująca historię transakcji
    // Wyciąga średnią ilość kupowanych produktów w jednej transakcji
    // Wylicza rabat na podstawie średniego odchylenia dla produktów kupowanych w największych ilościach
    @Override
    public List<Transakcja> analizujDane() {
        List<Transakcja> dane = sklep.getHistoriaTransakcji().getWszystkieTransakcje();
        float sredniaIlosc = 0;
        
        for (Transakcja transakcja : dane) {
            sredniaIlosc += transakcja.getIlosc();
        }

        sredniaIlosc = sredniaIlosc/dane.size();

        for (Transakcja transakcja : dane) {
            if (transakcja.getIlosc()>=sredniaIlosc) {
                rabat+=(transakcja.getIlosc()-sredniaIlosc)*0.01;
            } else {
                dane.remove(transakcja);
            }
        }
        return dane;
    }

    // Automatyzacja tworzenia gazetki
    // Na podstawie wcześniejszej analizy dodajemy chciane przedmioty do gazetki
    // Następnie nakładamy obliczony wcześniej rabat na każdy produkt
    @Override
    public void gazetkowaPromocja() {
        // W razie otwarcia starej gazetki zamykamy ją
        sklep.zamknijGazetke();
        //
        analizujDane().stream().
            filter(x -> sklep.getStanMagazynu().containsKey(x.getProdukt())).
            forEach( x -> {sklep.dodajDoGazetki(x.getProdukt());});

        sklep.getGazetka().forEach(x -> {x.setCena(x.getCena()*rabat);});
    }

}
