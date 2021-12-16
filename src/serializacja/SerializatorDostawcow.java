package serializacja;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sklepy.FirmaDostawcza;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class SerializatorDostawcow extends Serializator {
    final private String nazwaPliku = "dostawcy.ser";

    public void serializuj(final Object[] objekty) {

        FirmaDostawcza[] dostawcy = (FirmaDostawcza[]) objekty;
        
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            objectOutputStream.writeObject(dostawcy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FirmaDostawcza[] deserializuj() {
        FirmaDostawcza[] dostawcy = new FirmaDostawcza[0];
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            Object s = objectInputStream.readObject();
            dostawcy = (FirmaDostawcza[]) s;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dostawcy;
    }
}
