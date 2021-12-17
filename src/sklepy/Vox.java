package sklepy;


public class Vox extends SklepMeblowy {

    private static final long serialVersionUID = 30L;

    private boolean czyMaUporzadkowaneSekcje;
    // zmienilem skladowa w polu na jakas inna bo poprzednia "czyDzienPromocyjny" razem z metoda, ktora odpowiadala
    // za rabat chyba juz nie ma sensu odkąd jest taki sposob realizacji strategii

    public Vox(String adres, String adresWWW, boolean czyMoznaKupowacNaRaty, boolean czyMaUporzadkowaneSekcje) {
        super(czyMoznaKupowacNaRaty, adres, adresWWW);
        this.czyMaUporzadkowaneSekcje = czyMaUporzadkowaneSekcje;
    }

    public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
        if (dzienTygodnia == DniTygodnia.NIEDZIELA) {
            return godzina >= 10 && godzina <= 16;
        } else {
            return godzina >= 10 && godzina <= 20;
        }
    }


    public void setCzyMaUporzadkowaneSekcje(boolean czyMaUporzadkowaneSekcje) {

        this.czyMaUporzadkowaneSekcje = czyMaUporzadkowaneSekcje;
    }

    public boolean getCzyMaUporzadkowaneSekcje() {

        return this.czyMaUporzadkowaneSekcje;
    }
}
