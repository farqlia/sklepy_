package kontrolery;

import gui.ShopCreatorModel;
import gui.ShopCreatorView.ShopCreatorView;
import sklepy.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class ShopCreatorController implements Observer {
    public ShopCreatorController(ShopCreatorModel model, ShopCreatorView view, ArrayList<Sklep> sklepy) {
        this.model = model;
        this.view = view;
        this.sklepy = sklepy;
        if (model != null) {
            model.addObserver(this);
        }
    }

    ArrayList<Sklep> sklepy;

    public void przejdzDoSzczegolow() {
        switch(model.wybranaMarka) {
            case biedronka:
                model.sklep = new Biedronka("", "", false);
                break;
            case castorama:
                model.sklep = new Castorama("", "", false);
                break;
            case ikea:
                model.sklep = new Ikea("", "", false, false);
                break;
            case leroyMerlin:
                model.sklep = new LeroyMerlin("", "", false);
                break;
            case lidl:
                model.sklep = new Lidl("", "", false);
                break;
            case vox:
                model.sklep = new Vox("", "", false, false);
                break;
            case zabka:
                model.sklep = new Zabka(false, "", "", false);
                break;
        }
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

    public void zapiszSklep() {
        sklepy.add(model.sklep);
        view.setVisible(false);
        view.setEtapTworzeniaSklepu(ShopCreatorView.EtapTworzeniaSklepu.wyborMarki);
        model = new ShopCreatorModel();
    }
}

