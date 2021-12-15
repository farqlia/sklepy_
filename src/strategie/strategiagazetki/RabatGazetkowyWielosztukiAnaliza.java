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

    // Metoda analizuje historie transakcji i wyciaga z niej srednia ilosc
    // sprzedawanych na raz produktow
    // Te kupowane po wiele sztuk zostawiamy w liscie i przekazujemy ja do tworzenia gazetki
    // Rabat jest naliczany na podstawie jakiegos odchylenia od standardu
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

    // Zautomatyzowane robienie gazetki
    // Pomysl polega na tym ze jezeli magazyn sklepu zawiera przeanalizowany produkt
    // to dodajemy go do gazetki a pozniej nakladamy na cala gazetke rabat
    @Override
    public void gazetkowaPromocja() {
        // W razie otwarcia starej gazetki zamykamy ja
        sklep.zamknijGazetke();
        //
        analizujDane().stream().filter(x -> sklep.getStanMagazynu().containsKey(x.getProdukt())).forEach( x -> {sklep.dodajDoGazetki(x.getProdukt());});
        sklep.getGazetka().forEach(x -> {x.setCena(x.getCena()*rabat);});
    }

}
