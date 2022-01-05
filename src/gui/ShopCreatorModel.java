package gui;

import kontrolery.Sklepy;
import sklepy.Sklep;

import java.util.Observable;

public class ShopCreatorModel extends Observable {
    public Sklep sklep;
    public Sklepy wybranaMarka = Sklepy.biedronka;
}
