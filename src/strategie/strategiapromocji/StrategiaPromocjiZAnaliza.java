package strategie.strategiapromocji;

import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;

// To nadal może być traktowane jako typ interfejsu
public abstract class StrategiaPromocjiZAnaliza<E> implements StrategiaPromocji{

    // Ten kod by się powtarzał wśród klas, które wykorzystują analizę danych
    protected Analityk<E> analiza;
    protected Sklep sklep;

    public StrategiaPromocjiZAnaliza(Analityk<E> analiza, Sklep sklep) {
        this.analiza = analiza;
        this.sklep = sklep;
    }

}
