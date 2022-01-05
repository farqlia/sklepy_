package kontroler;

import gui.produktview.ProduktComponent;
import serializacja.Transakcja;
import wzorzecobserwator.Observer;
import gui.sklepview.AbstractSklepView;
import sklepy.Sklep;
import wzorzecobserwator.ProduktEvent;

// Pośrednik między modelem (Sklep) a widokiem (AbstractSklepView)
// Klasy model i widok nie wiedzą o swoim istnieniu i nie nawiązują żadnych interakcji
// ze sobą bezpośrednio
public class Controller {

    private Sklep model;
    private AbstractSklepView view;

    public Controller(Sklep model, AbstractSklepView view){
        this.model = model;
        this.view = view;
        model.registerObserver(new ListenForSklepChanges());
        view.getKoszyk().registerObserver(new ListenForProduktSelling());
        view.aktualizujHistorieTransakcji(model.getHistoriaTransakcji().getWszystko());
        view.getKreatorProduktow().registerObserver(new ListenForProduktCreated());
    }

    // Słuchacz sklepu
    private class ListenForSklepChanges implements Observer{
        // Przekazuje zmiany z modelu do widoku
        @Override
        public void update(ProduktEvent e) {
            view.update(e);
        }

    }

    // Słuchacz klasy KreatoraProduktow : aktualizuje model i widok
    private class ListenForProduktCreated implements Observer{

        @Override
        public void update(ProduktEvent e) {
            model.aktualizujIloscProduktow(e.getProdukt(), e.getIlosc());
            view.addProduktComponent(new ProduktComponent(e.getSciezkaPliku(), e.getProdukt(), e.getIlosc()));
        }
    }

    // Klasa, która jest słuchaczem koszyka
    private class ListenForProduktSelling implements Observer{

        @Override
        public void update(ProduktEvent e) {
            // Upewniamy się raz jeszcze, że dysponujemy daną ilością produktu
            if (model.sprawdzDostepnoscProduktu(e.getProdukt()) >= e.getIlosc()){
                Transakcja t = model.sprzedajProdukt(e.getProdukt(), e.getIlosc());
                // Na konsoli wyświetlą się wszystkie transakcje, więc można sprawdzić poprawność działania
                System.out.println("Nowa Transakcja: " + t);
                view.aktualizujHistorieTransakcji(model.getHistoriaTransakcji().getWszystko());
            }
        }
    }

}
