package serializacja;

import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;

// Typ generyczny umożliwia nam używanie tej klasy do serializowania
// jakichkolwiek obiektów
public class Historia<E> implements Serializable {

    private static final long serialVersionUID = 97L;

    private String sciezkaPliku;
    private final String glownaSciezka = "src/historia/";
    private String rodzajSciezki = ".ser";


    public Historia(String sciezkaFolderu, String nazwaPliku) {
        this.sciezkaPliku = glownaSciezka + sciezkaFolderu + nazwaPliku + rodzajSciezki;
        stworzFolder(glownaSciezka + sciezkaFolderu);
        stworzPlik();
    }

    public void stworzFolder(String sciezkaFolderu){
        new File(sciezkaFolderu).mkdirs();
    }

    public void stworzPlik() {
        try {
            File file = new File(sciezkaPliku);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dodaj(E obiekt) {
                // Gdy dodajemy na koniec pliku (append), to wymaga to innego podejścia,
        // jako że strumień wczytujący te dane nie zadziała (zablokuje się)
        FileOutputStream fOS = null;
        ObjectOutputStream oOS = null;
        try {
            File file = new File(sciezkaPliku);

            fOS = new FileOutputStream(sciezkaPliku, true);

            if (file.length() == 0L) {
                // Za pierwszym razem używamy normalnej implementacji
                oOS = new ObjectOutputStream(fOS);
            } else {
                oOS = new MyObjectOutputStream(fOS);
            }

            oOS.writeObject(obiekt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oOS != null) {
                try {
                    oOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fOS != null) {
                try {
                    fOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Można wyłączyć ostrzeżenie, bo zawsze odczytujemy z tego samego
    // pliku, do którego wczytaliśmy dane, więc rzutowanie jest bezpieczne
    @SuppressWarnings("unchecked")
    public List<E> getWszystko() {

        List<E> zdeserializowanaLista = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        try {
            fIS = new FileInputStream(sciezkaPliku);
            oIS = new ObjectInputStream(fIS);

            while (fIS.available() != 0) {
                E t = (E) oIS.readObject();
                zdeserializowanaLista.add(t);
            }


            // End of file czyli nie mamy co wczytywać (w razie, gdy strumień jest pusty)
        } catch (EOFException e) {
            return zdeserializowanaLista;
        } catch(FileNotFoundException e) {
            return new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fIS != null) {
                try {
                    fIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oIS != null) {
                try {
                    oIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return zdeserializowanaLista;

    }

}
