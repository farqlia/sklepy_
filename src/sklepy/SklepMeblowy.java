package sklepy;

import java.util.Objects;

abstract public class SklepMeblowy extends Sklep {

    private static final long serialVersionUID = 28L;

    boolean czyMoznaKupowacNaRaty;

    //KONSTRUKTOR:

    public SklepMeblowy(boolean czyMoznaKupowacNaRaty, String adres, String adresWWW) {

        super(adres, adresWWW);
        this.czyMoznaKupowacNaRaty = czyMoznaKupowacNaRaty;
    }

    // GETTERY:

    public boolean getCzyMoznaKupowacNaRaty() {
        return czyMoznaKupowacNaRaty;
    }

    //SETTERY:

    public void setCzyMoznaKupowacNaRaty(boolean czyMoznaKupowacNaRaty) {
        this.czyMoznaKupowacNaRaty = czyMoznaKupowacNaRaty;
    }

    //METODY:

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        if (Objects.equals(dzienTygodnia, "Niedziela")) {
            return false;
        }
        if (godzina >= 9 && godzina <= 21)
            return true;
        else return false;
    }
}
