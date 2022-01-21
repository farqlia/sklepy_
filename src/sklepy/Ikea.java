package sklepy;


public class Ikea extends SklepMeblowy {

    private static final long serialVersionUID = 23L;

    boolean czyPosiadaRestauracje;

    public Ikea(String adres, String adresWWW, boolean czyMoznaBracNaRaty, boolean czyPosiadaRestauracje) {
        super(czyMoznaBracNaRaty, adres, adresWWW);
        this.czyPosiadaRestauracje = czyPosiadaRestauracje;
    }

    public String kupHotDoga() {
        if (this.czyPosiadaRestauracje) {
            return "Klient zakupił hot-doga";
        } else {
            return "Sklep nie posiada restauracji";
        }
    }

    public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
        if (dzienTygodnia == DniTygodnia.NIEDZIELA) {
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
