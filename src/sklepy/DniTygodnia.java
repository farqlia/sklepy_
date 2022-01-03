package sklepy;

public enum DniTygodnia {

    PONIEDZIALEK ("Poniedzialek"),
    WTOREK  ("Wtorek"),
    SRODA  ("Sroda"),
    CZWARTEK  ("Czwartek"),
    PIATEK  ("Piatek"),
    SOBOTA ("Sobota"),
    NIEDZIELA  ("Niedziela");

    private String nazwa;
    DniTygodnia(String nazwa){
        this.nazwa = nazwa;
    }


}