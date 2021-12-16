package sklepy;

import java.util.Objects;

public class Ikea extends SklepMeblowy {

    private static final long serialVersionUID = 23L;

    boolean czyPosiadaRestauracje;

    public Ikea(String adres, String adresWWW, boolean czyMoznaBracNaRaty, boolean czyPosiadaRestauracje) {
        super(czyMoznaBracNaRaty, adres, adresWWW);
        this.czyPosiadaRestauracje = czyPosiadaRestauracje;
    }

    public void kupHotDoga() {
        if (this.czyPosiadaRestauracje) {
            System.out.println("Klient zakupił hot-doga");
        } else {
            System.out.println("Sklep nie posiada restauracji");
        }
    }

    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA)) {
            return godzina >= 9 && godzina <= 21;
        } else {
            return godzina >= 9 && godzina <= 22;
        }
    }

    public boolean getCzyPosiadaRestauracje() {
        return this.czyPosiadaRestauracje;
    }

    public void setCzyPosiadaRestauracje(boolean czyPosiadaRestauracje) {
        this.czyPosiadaRestauracje = czyPosiadaRestauracje;
    }
}
