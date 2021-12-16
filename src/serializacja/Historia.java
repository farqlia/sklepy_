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

public abstract class Historia implements Serializable {

    private static final long serialVersionUID = 97L;

    protected String sciezkaPliku;
    protected String rodzajSciezki = ".ser";
    protected String mainPath;

    public Historia(String unikalnaNazwaSklepu) {
        this.sciezkaPliku = mainPath + unikalnaNazwaSklepu + rodzajSciezki;
        stworzPlik();
    }

    public void stworzPlik() {
        try {
            File file = new File(sciezkaPliku);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dodaj(Object objekt) {
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

            oOS.writeObject(objekt);
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

    public List<Object> getWszystko(Object objekt) {

        List<Object> transakcje = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        try {
            fIS = new FileInputStream(sciezkaPliku);
            oIS = new ObjectInputStream(fIS);

            while (fIS.available() != 0) {
                Object t = oIS.readObject();
                transakcje.add(t);
            }


            // End of file czyli nie mamy co wczytywać (w razie, gdy strumień jest pusty)
        } catch (EOFException e) {
            return transakcje;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
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
        return transakcje;

    }

}
