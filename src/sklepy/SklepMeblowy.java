package sklepy;


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
    public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
        if (dzienTygodnia == DniTygodnia.NIEDZIELA) {
            return false;
        }
        return godzina >= 9 && godzina <= 21;
    }
}
