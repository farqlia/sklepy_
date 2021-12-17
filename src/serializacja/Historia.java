package serializacja;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Historia<E> extends Serializator<E> {

    private static final long serialVersionUID = 97L;

    public Historia(String sciezkaFolderu, String nazwaPliku) {
        super(sciezkaFolderu, nazwaPliku);
    }

    public void serializuj(E obiekt) {
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
    public List<E> deserializuj() {

        List<E> obiekty = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        try {
            fIS = new FileInputStream(sciezkaPliku);
            oIS = new ObjectInputStream(fIS);

            while (fIS.available() != 0) {
                E t = (E) oIS.readObject();
                obiekty.add(t);
            }

        }
        catch (EOFException e){
            System.out.println("Pusty plik");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
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
        return obiekty;

    }

}
