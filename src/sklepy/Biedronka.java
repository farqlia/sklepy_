package sklepy;

import java.io.Serializable;
import java.util.Objects;

public class Biedronka extends Supermarket{

    private static final long serialVersionUID = 21L;

    private boolean czyMaKasySamoobslugowe;

    public Biedronka(String adres, String adresWWW, boolean czyMaKasySamoobslugowe) {
        super(adres, adresWWW);
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
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

    public boolean isCzyMaKasySamoobslugowe() {
        return czyMaKasySamoobslugowe;
    }

    public void setCzyMaKasySamoobslugowe(boolean czyMaKasySamoobslugowe) {
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
    }

}