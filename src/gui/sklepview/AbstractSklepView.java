package gui.sklepview;

import gui.oknaztabela.DialogsWithTables;
import gui.oknaztabela.HistoriaTransakcjiDialog;
import gui.oknaztabela.Koszyk;
import gui.produktview.AbstractProduktComponent;
import serializacja.Transakcja;
import wzorzecobserwator.Observable;
import wzorzecobserwator.Observer;
import wzorzecobserwator.ProduktEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Implementuje interfejs Observable, czyli będzie informować
// obserwatorów w momencie, gdy użytkownik dokona zakupu produktów
public abstract class AbstractSklepView extends JFrame implements Observable, Observer {

    // Obserwatorzy którzy będą powiadamiani o chęci zakupu produktów przez użytkownika
    // (w tym przypadku kontroler)
    private List<Observer> obserwatorzy;
    // Komponenty obserwują widok, który będzie je informował o zmianach w modelu
    private List<Observer> komponentyObserwujace;

    // Jak należy dodać to okno do swojej klasy: tworzymy obiekt klasy JButton (lub inny)
    // podpinamy pod niego słuchacza zdefiniowanego w tej klasie: 'HistoriaTransakcjiHandler'
    // i dodajemy do widoku sklepu
    private HistoriaTransakcjiDialog hTDialog;
    // Tak samo dodajemy koszyk, jak wyżej opisane
    private Koszyk koszyk;

    // Logika umiejscawiania kompontentów w widoku: nie podpinamy żadnych słuchaczy tutaj!, to
    // jest załatwione w metodzie 'addProduktComponent', żeby nie powtarzał się kod
    abstract void placeProduktComponent(AbstractProduktComponent comp);

    public AbstractSklepView(String nazwaSklepu){

        setSize(800, 800);
        setTitle(nazwaSklepu);
        obserwatorzy = new ArrayList<>();
        komponentyObserwujace = new ArrayList<>();

        koszyk = new Koszyk();
        // Rejestrujemy słuchacza do koszyka, który będzie reagował na wydarzenie, jakim jest kupno produktów
        koszyk.registerObserver(new KupProduktyHandler());

        hTDialog = new HistoriaTransakcjiDialog();

        // Nie zamyka okna, ale go chowa, dzięki czemu główne
        // okno pozostaje aktywne
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    // Metoda, za pomocą której dodajemy komponenty do widoku: jej używamy,
    // bo inaczej komponenty nie zostaną zarejestrowane jako słuchacze i nie będzie to działać
    public void addProduktComponent(AbstractProduktComponent comp){
        placeProduktComponent(comp);
        komponentyObserwujace.add(comp);
        // Teraz koszyk obserwuje komponenty i dodaje je do tabeli, gdy
        // zostaną wybrane
        comp.registerObserver(koszyk);

    }

    public void showMessageDialog(String message){
        // Wyświetla proste okno z wiadomością
        JOptionPane.showMessageDialog(AbstractSklepView.this, message);

    }

    public void aktualizujHistorieTransakcji(List<Transakcja> transakcje){
        SwingUtilities.invokeLater(() -> {
            hTDialog.addDataToTable(transakcje);
        });
    }

    @Override
    public void notifyObservers(ProduktEvent e) {
        for (Observer o : obserwatorzy){
            o.update(e);
        }
    }

    @Override
    public void registerObserver(Observer o) {
        obserwatorzy.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        obserwatorzy.remove(o);
    }

    // Reaguje na zmiany w modelu (kontroler wywołuje tą metodę) i przekazuje je do swoich komponentow
    @Override
    public void update(ProduktEvent e) {
        for (Observer comp : komponentyObserwujace){
            comp.update(e);
        }
    }

    // Obserwujący koszyka, informacje o zakupionych produktach przekazuje dalej do kontrolera
    // Nie chcę bezpośrednio wiązać kontrolera z koszykiem, bo koszyk nie jest żadną abstrakcją i w ten
    // sposób nie możemy zmienić później np. klasy koszyka na inną bez zmian w kontrolerze
    // Innymi słowy, koszyk to szczegół implementacji, więc musi zostać w tej klasie ukryty
    private class KupProduktyHandler implements Observer{
        @Override
        public void update(ProduktEvent e) {
            notifyObservers(e);
        }
    }


    // Wyświetla ramkę z historią transakcji
    protected class HistoriaTransakcjiHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            hTDialog.showDialog(AbstractSklepView.this);
        }
    }

    // Wyświetla ramkę z koszykiem
    protected class KoszykHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            koszyk.showDialog(AbstractSklepView.this);
        }
    }


}
