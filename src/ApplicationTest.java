import gui.produktview.AbstractProduktComponent;
import gui.produktview.ProduktComponent;
import gui.sklepview.AbstractSklepView;
import gui.sklepview.ApplicationView;
import gui.sklepview.TestSklepView;
import kontrolery.Controller;
import gui.sklepview.BiedronkaView;
import sklepy.Biedronka;
import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiapromocji.RabatNaDzienTygodniaZAnaliza;

import javax.swing.*;
import java.util.HashMap;

// W metodzie main tworzymy obiekty: sklep (model), panele reprezentujące produkty,
// widoki sklepów (AbstractSklepView) i odpowiednio ze sobą łączymy, tzn. do widoków
// sklepu dodajemy powyższe panele, widoki sklepów dodajemy do głównego widoku i inicjalizujemy
// kontrolerów dla każdego sklepu
// Miała być rozwarstwiona aplikacja, ale czy tak wyszło to nie wiem
public class ApplicationTest {

    public static void main(String[] args) {

        Sklep testSklep =
                new Biedronka("Wrocław", "randombiedronka.pl", true);

        testSklep.zmienStrategie(new RabatNaDzienTygodniaZAnaliza(testSklep, 0.1));

        Produkt p = new Produkt("Awokado", 10.00);
        testSklep.aktualizujIloscProduktow(p, 10);

        Produkt p2 = new Produkt("Ser", 5.00);
        testSklep.aktualizujIloscProduktow(p2, 4);

        Produkt p3 = new Produkt("Brokuł", 6.00);
        testSklep.aktualizujIloscProduktow(p3, 12);

        Produkt p4 = new Produkt("Arbuz", 12.00);
        testSklep.aktualizujIloscProduktow(p4, 8);

        AbstractProduktComponent produktComp = new ProduktComponent("avocado-icon.png", p, testSklep.sprawdzDostepnoscProduktu(p));
        AbstractProduktComponent produktComp2 = new ProduktComponent("cheese-icon.png", p2, testSklep.sprawdzDostepnoscProduktu(p2));
        AbstractProduktComponent produktComp3 = new ProduktComponent("broccoli-icon.png", p3, testSklep.sprawdzDostepnoscProduktu(p3));
        AbstractProduktComponent produktComp4 = new ProduktComponent("watermelon.png", p4, testSklep.sprawdzDostepnoscProduktu(p4));

        AbstractSklepView view = new BiedronkaView();
        // To dodałam tylko po to, żeby zobaczyć jak się doda kolejny przycisk na panelu
        AbstractSklepView view2 = new BiedronkaView();

        view.addProduktComponent(produktComp);
        view.addProduktComponent(produktComp2);
        view.addProduktComponent(produktComp3);
        view.addProduktComponent(produktComp4);

        Controller controller = new Controller(testSklep, view);

        HashMap<AbstractSklepView, String> map = new HashMap<>();
        // Nazwa pliku ikony
        map.put(view, "bieda.png");
        map.put(view2, "zabkaicon.png");

        ApplicationView applicationView = new ApplicationView(map);

        SwingUtilities.invokeLater(() -> applicationView.setVisible(true));

    }


}
