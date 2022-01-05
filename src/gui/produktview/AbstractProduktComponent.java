package gui.produktview;

import sklepy.Produkt;
import wzorzecobserwator.Observable;
import wzorzecobserwator.Observer;

import javax.swing.*;
import java.util.ArrayList;

// Widżet reprezentujący jeden produkt : wiele widżetów tworzy stronę sklepu
// Jako Observable: oznacza to, że widżet wysyła do słuchaczy
// informację o tym, że użytkownik chce kupić dany produkt w danej ilości
// Jako Observer: musi uaktualniać dostępność produktu, tak
// by możliwa ilość do zakupienia była zawsze ta sama, co na magazynie
// sklepu
public abstract class AbstractProduktComponent extends JComponent
        implements Observable, Observer {        // odsyłam do pakietu 'wzorzecobserwator'

    protected java.util.List<Observer> observators;

    public AbstractProduktComponent(){
        observators = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observators.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observators.remove(o);
    }

}
