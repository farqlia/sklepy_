package wzorzecobserwator;

public interface Observable {

    void notifyObservers(ProduktEvent e);

    void registerObserver(Observer o);

    void removeObserver(Observer o);

}
