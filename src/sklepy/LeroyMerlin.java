package sklepy;


public class LeroyMerlin extends SklepBudowniczy {

    private static final long serialVersionUID = 24L;

    private boolean czySaStanowiskaSamoobslugowe;


    public LeroyMerlin(String adres, String adresWWW, boolean czySaKasySamoobslugowe) {
        super(adres, adresWWW);
        this.czySaStanowiskaSamoobslugowe = czySaKasySamoobslugowe;
    }

    @Override
    public boolean czyJestOtwarty(DniTygodnia dzienTygodnia, int godzina) {

        // Niedziela 10-20, reszta 7-21
        if (dzienTygodnia == DniTygodnia.NIEDZIELA) {
            return godzina >= 10 && godzina <= 20;
        } else {
            return godzina >= 7 && godzina <= 21;
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
