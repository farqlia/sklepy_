package serializacja;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

// Klasa zawiera wspólne części dla obu klas serializujących

public class Serializator<E> implements Serializable{

    private static final long serialVersionUID = 98L;

    protected String sciezkaPliku;
    protected String sciezkaFolderu;
    protected String rodzajSciezki = ".ser";

    public Serializator(String sciezkaFolderu, String nazwaPliku) {
        this.sciezkaPliku = sciezkaFolderu + nazwaPliku + rodzajSciezki;
        this.sciezkaFolderu = sciezkaFolderu;
        stworzPotrzebnePliki();
    }

    // TODO : przetestowac tą metodę
    public void stworzPotrzebnePliki() {
        try {
            File file = new File(sciezkaFolderu);
            file.mkdirs();
            file = new File(sciezkaPliku);
            file.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

