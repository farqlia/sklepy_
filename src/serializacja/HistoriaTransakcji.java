package serializacja;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriaTransakcji implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sciezkaPliku;
    private String rodzajSciezki = ".ser";
    private String mainPath = "historietransakcji/";

    public HistoriaTransakcji(String unikalnaNazwaSklepu) {
        sciezkaPliku = mainPath + unikalnaNazwaSklepu + rodzajSciezki;
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

    public void dodajTransakcje(Transakcja transakcja) {

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

            oOS.writeObject(transakcja);
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

    public List<Transakcja> getWszystkieTransakcje() {

        List<Transakcja> transakcje = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        try {
            fIS = new FileInputStream(sciezkaPliku);
            oIS = new ObjectInputStream(fIS);

            while (fIS.available() != 0) {
                Transakcja t = (Transakcja) oIS.readObject();
                transakcje.add(t);
            }


            // End of file czyli nie mamy co wczytywać (w razie, gdy strumień jest pusty)
        } catch (EOFException e) {
            return transakcje;
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
        return transakcje;

    }

}
