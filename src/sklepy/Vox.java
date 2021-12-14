package sklepy;

import java.io.Serializable;
import java.util.Objects;

public class Vox extends SklepMeblowy {

    private static final long serialVersionUID = 30L;

    private boolean czyDzienRabatowy;

    public Vox(String adres, String adresWWW, boolean czyMoznaKupowacNaRaty, boolean czyDzienRabatowy) {
        super(czyMoznaKupowacNaRaty, adres, adresWWW);
        this.czyDzienRabatowy = czyDzienRabatowy;
    }

    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA)) {
            return godzina >= 10 && godzina <= 16;
        } else {
            return godzina >= 10 && godzina <= 20;
        }
    }


    public void setCzyDzienRabatowy(String dzienTygodnia) {
        this.czyDzienRabatowy = Objects.equals(dzienTygodnia, DniTygodnia.CZWARTEK) || Objects.equals(dzienTygodnia, DniTygodnia.WTOREK);
    }

    public boolean getCzyDzienRabatowy() {
        return this.czyDzienRabatowy;
    }
}
