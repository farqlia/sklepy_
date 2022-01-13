package kontrolery;

import gui.produktview.ProduktComponent;
import serializacja.Transakcja;
import gui.sklepview.AbstractSklepView;
import sklepy.Sklep;
import gui.sklepview.ProduktEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Pośrednik między modelem (Sklep) a widokiem (AbstractSklepView)
// Klasy model i widok nie wiedzą o swoim istnieniu i nie nawiązują żadnych interakcji
// ze sobą bezpośrednio
public class Controller {

    private Sklep model;
    private AbstractSklepView view;

    public Controller(Sklep model, AbstractSklepView view){
        this.model = model;
        this.view = view;

        view.getKoszyk().addListenerForKoszyk(new KupProduktListener());
        view.getKreatorProduktow().addStworzObiektListener(new StworzProduktListener());
        view.getKreatorProduktow().addZaktualizujObiektListener(new AktualizujProduktListener());

        view.aktualizujHistorieTransakcji(model.getHistoriaTransakcji().getWszystko());
    }


    // Słuchacz klasy KreatoraProduktow : aktualizuje model i widok
    private class StworzProduktListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {

            try {
                // Pobieramy nowo utworzony produkt
                 ProduktEvent e = view.getKreatorProduktow().getStworzonyObiekt();
                // Dodajemy do magazynu sklepu
                model.aktualizujIloscProduktow(e.getProdukt(), e.getIlosc());
                // Dodajemy do widoku sklepu
                view.addProduktComponent(e.getProdukt(),
                        new ProduktComponent(e.getSciezkaPliku(), e.getProdukt(), e.getIlosc()));
            }
            catch (IllegalArgumentException exception){
                view.showMessageDialog("Nie można było utworzyć produktu");
            }
        }
    }

    private class AktualizujProduktListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                ProduktEvent e = view.getKreatorProduktow().getZaktualizowanyObiekt();

                model.aktualizujIloscProduktow(e.getProdukt(), e.getIlosc());
                view.aktualizujIloscProduktow(e.getProdukt(), model.sprawdzDostepnoscProduktu(e.getProdukt()));
            } catch (IllegalArgumentException exception) {
                view.showMessageDialog("Nie można było zaktualizować produktu");
            }
        }
    }

    // Klasa, która jest słuchaczem koszyka
    private class KupProduktListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {

            // Iteruje przez każdy produkt koszyka, sprzedaje go poprzez model (sklep)
            // i aktualizuje ilość produktu
            for (ProduktEvent e: view.getKoszyk().getListeProduktowZKoszyka()){
                if (model.sprawdzDostepnoscProduktu(e.getProdukt()) >= e.getIlosc()) {
                    Transakcja t = model.sprzedajProdukt(e.getProdukt(), e.getIlosc());
                    // Na konsoli wyświetlą się wszystkie transakcje, więc można sprawdzić poprawność działania
                    System.out.println("Nowa Transakcja: " + t);
                    view.aktualizujHistorieTransakcji(model.getHistoriaTransakcji().getWszystko());
                    // Odpowiedni widzet produktu zaktualizuje swoją ilosc
                    view.aktualizujIloscProduktow(e.getProdukt(), model.sprawdzDostepnoscProduktu(e.getProdukt()));
                }

            }
        }
    }

}
