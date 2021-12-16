package sklepy;

public class FirmaDostawcza {

    private String nazwaFirmy;
    private int czasDostawy;
    private String adresSiedziby;

    // typical konstruktor
    public FirmaDostawcza(String nazwaFirmy, int czasDostawy, String adresSiedziby) {
        this.nazwaFirmy = nazwaFirmy;
        this.czasDostawy = czasDostawy;
        this.adresSiedziby = adresSiedziby;
    }

    public void dostarczMaterialy(SklepBudowniczy sklep){
        System.out.format("Dostarczono produkty do klientów sklepu budowniczego przy %s", sklep.getAdres());
    }

    public void dostarczMeble(SklepMeblowy sklep){
        System.out.format("Dostarczono produkty do klientów sklepu budowniczego przy %s", sklep.getAdres());
    }

    // settery/gettery
    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    public void setCzasDostawy(int czasDostawy) {
        this.czasDostawy = czasDostawy;
    }

    public void setAdresSiedziby(String adresSiedziby) {
        this.adresSiedziby = adresSiedziby;
    }


    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public int getCzasDostawy() {
        return czasDostawy;
    }

    public String getAdresSiedziby() {
        return adresSiedziby;
    }

}
