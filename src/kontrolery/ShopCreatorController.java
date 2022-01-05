package kontrolery;

import gui.ShopCreatorModel;
import gui.ShopCreatorView.ShopCreatorView;

import java.util.Observer;
import java.util.Observable;

public class ShopCreatorController implements Observer {
    public ShopCreatorController(ShopCreatorModel model, ShopCreatorView view) {
        this.model = model;
        this.view = view;
        if (model != null) {
            model.addObserver(this);
        }
    }

    public void przejdzDoSzczegolow() {
        view.setEtapTworzeniaSklepu(ShopCreatorView.EtapTworzeniaSklepu.szczegoly);

    }

    private ShopCreatorModel model;

    private ShopCreatorView view;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("AAAAA");
    }

    public Sklepy getWybranySklep() {
        return model.wybranaMarka;
    }

    public void setWybranySklep(Sklepy wybranySklep) {
        model.wybranaMarka = wybranySklep;
    }

    public ShopCreatorModel getModel() {
        return model;
    }

    public void setModel(ShopCreatorModel model) {
        model.deleteObserver(this);
        this.model = model;
        model.addObserver(this);
    }

    public ShopCreatorView getView() {
        return view;
    }

    public void setView(ShopCreatorView view) {
        this.view = view;
    }
}

