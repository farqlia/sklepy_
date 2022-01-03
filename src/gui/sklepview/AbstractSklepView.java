package gui.sklepview;

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
import java.util.stream.Collectors;

// Implementuje interfejs Observable, czyli będzie informować
// obserwatorów w momencie, gdy użytkownik dokona zakupu jednego z produktów
public abstract class AbstractSklepView extends JFrame implements Observable, Observer {

    // Obserwatorzy którzy będą powiadamiani o chęci zakupu produktu przez użytkownika
    // (w tym przypadku kontroler)
    List<Observer> obserwatorzy;
    // Komponenty obserwują widok, który będzie je informował o zmianach w modelu
    List<Observer> komponentyObserwujace;
    Observer listenForProduktSelection;

    // Jak należy dodać to okno do swojej klasy: tworzymy obiekt klasy JButton,
    // podpinamy pod niego słuchacza zdefiniowanego w tej klasie: 'HistoriaTransakcjiHandler'
    // i dodajemy do widoku sklepu
    HistoriaTransakcjiPanel dialog;

    // Logika umiejscawiania kompontentów w widoku: nie podpinamy żadnych słuchaczy tutaj!, to
    // jest załatwione w metodzie 'addProduktComponent', żeby nie powtarzał się kod
    abstract void placeProduktComponent(AbstractProduktComponent comp);

    public AbstractSklepView(){

        obserwatorzy = new ArrayList<>();
        komponentyObserwujace = new ArrayList<>();
        // Słuchacz podpinany do każdej instancji 'AbstractProduktComponent'
        listenForProduktSelection = new ListenForProdukt();

        // Nie zamyka okna, ale go chowa, dzięki czemu główne
        // okno pozostaje aktywne
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    // Metoda, za pomocą której dodajemy komponenty do widoku: jej używamy,
    // bo inaczej komponenty nie zostaną zarejestrowane jako słuchacze i nie będzie to działać
    public void addProduktComponent(AbstractProduktComponent comp){
        placeProduktComponent(comp);
        komponentyObserwujace.add(comp);
        // Rejestrujemy instancję klasy wewnętrznej jako nasłuchiwacza
        // Wtedy, gdy produkt zostanie wybrany, to wykonany zostanie kod z tej klasy wewnętrznej
        comp.registerObserver(listenForProduktSelection);
    }

    public void showMessageDialog(String message){
        // Wyświetla proste okno z wiadomością
        JOptionPane.showMessageDialog(AbstractSklepView.this, message);

    }

    public void aktualizujHistorieTransakcji(List<Transakcja> transakcje){
        if (dialog == null){
            dialog = new HistoriaTransakcjiPanel();
        }
        dialog.setText(transakcje.stream().map(x -> x.toString() + System.lineSeparator())
                .collect(Collectors.joining()));
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

    private class ListenForProdukt implements Observer{

        @Override
        public void update(ProduktEvent e) {

            String message = String.format("Czy na pewno chcesz kupić %s w ilości %d", e.getProdukt().getNazwa(),
                    e.getIlosc());

            int option = JOptionPane.showConfirmDialog(AbstractSklepView.this,
                    message, "Potwierdź zakup", JOptionPane.OK_CANCEL_OPTION
                    , JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                // Gdy użytkownik potwierdził chęć zakupu, to powiadamiamy słuchaczy (kontrolera)
                notifyObservers(e);
            } else {
                System.out.println("Zamowienie anulowane");
            }

        }
    }

    // Wyświetla ramkę z historią transakcji
    protected class HistoriaTransakcjiHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (dialog == null) dialog = new HistoriaTransakcjiPanel();
            dialog.showDialog(AbstractSklepView.this);
        }
    }

}
