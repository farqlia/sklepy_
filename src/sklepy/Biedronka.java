package sklepy;

import java.util.Objects;

public class Biedronka extends Supermarket {

    private static final long serialVersionUID = 21L;

    public Biedronka(String adres, String adresWWW, boolean czyMaKasySamoobslugowe) {
        super(adres, adresWWW, czyMaKasySamoobslugowe);
    }

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        // W niedziele otwarta: 8-20, w rezte dni: 6-23
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA)) {
            return godzina >= 8 && godzina <= 20;
        } else {
            return godzina >= 6 && godzina <= 23;
        }
    }

}