package sklepy;

public class Lidl extends Supermarket {

    private static final long serialVersionUID = 25L;

    public Lidl(String adres, String adresWWW, boolean czyMaKasySamoobslugowe) {
        super(adres, adresWWW, czyMaKasySamoobslugowe);
    }

    @Override
    public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
        // W niedziele zamkniety, w reszte dni: 6-22
        if (dzienTygodnia == DniTygodnia.NIEDZIELA)
            return false;
        else
            return godzina >= 6 && godzina <= 22;

    }

}