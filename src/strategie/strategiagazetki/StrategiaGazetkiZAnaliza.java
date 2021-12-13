package strategie.strategiagazetki;

import java.util.ArrayList;

import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;

public abstract class StrategiaGazetkiZAnaliza<E> implements StrategiaGazetki {

    protected Analityk<E> analiza;
    protected Sklep sklep;

    public StrategiaGazetkiZAnaliza(Analityk<E> analiza, Sklep sklep) {
        this.analiza = analiza;
        this.sklep = sklep;
    }

    @Override
    public void gazetkowaPromocja(ArrayList<Produkt> gazetka) {
        gazetka.forEach( (x) -> {x.setCena(x.getCena()*rabat());} );
    }

    // Oblicza rabat w konkretnej klasie
    public abstract double rabat();

}
