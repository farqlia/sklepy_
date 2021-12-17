package sklepy;

public class Castorama extends SklepBudowniczy {

    private static final long serialVersionUID = 22L;

    private boolean czySaStanowiskaSamoobslugowe;


    public Castorama(String adres, String adresWWW, boolean czySaKasySamoobslugowe) {
        super(adres, adresWWW);
        this.czySaStanowiskaSamoobslugowe = czySaKasySamoobslugowe;
    }

    @Override
    public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {
        // Niedziela 10-18, reszta 6-21
        if (dzienTygodnia == DniTygodnia.NIEDZIELA) {
            return godzina >= 10 && godzina <= 18;
        } else {
            return godzina >= 6 && godzina <= 21;
        }
    }


    // ------------------- GETTERS & SETTERS -----------------------------

    public boolean getCzySaKasySamoobslugowe() {
        return czySaStanowiskaSamoobslugowe;
    }

    public void setCzySaKasySamoobslugowe(boolean czySaKasySamoobslugowe) {
        this.czySaStanowiskaSamoobslugowe = czySaKasySamoobslugowe;
    }
}