package strategie.strategianadzial;

import sklepy.Produkt;
import sklepy.SklepBudowniczy;
import strategie.strategiapromocji.StrategiaPromocji;

import java.io.Serializable;
import java.util.Objects;

public class RabatNaDzial implements StrategiaPromocji, Serializable {

    private static final long serialVersionUID = 10L;

    private String dzialRabatowy;
    private double rabat;
    // Tylko sklepy budownicze mają działy
    private SklepBudowniczy sklep;

    // Tutaj dzień rabatowy narzucony jest przez sklep
    public RabatNaDzial(String dzialRabatowy, double rabat, SklepBudowniczy sklep) {
        this.dzialRabatowy = dzialRabatowy;
        this.rabat = rabat;
        this.sklep = sklep;
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {

        double cenaBezRabatu = (produkt.getCena() * ilosc);

        if (Objects.equals(sklep.getDzialProduktu(produkt), dzialRabatowy))
            return (1 - rabat) * cenaBezRabatu;
        else
            return cenaBezRabatu;
    }

    public void zmienDzialRabatowy(String dzial, double rabat) {
        dzialRabatowy = dzial;
    }

}
