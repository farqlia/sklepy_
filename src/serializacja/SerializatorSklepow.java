package serializacja;

import sklepy.Sklep;

import java.io.*;
import java.util.ArrayList;

public class SerializatorSklepow {
    final private String nazwaPliku = "sklepy.ser";

    public void serializujSklepy(final Sklep[] sklepy) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            objectOutputStream.writeObject(sklepy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sklep[] deserializujSklepy() {
        Sklep[] sklepy = new Sklep[0];
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            Object s = objectInputStream.readObject();
            sklepy = (Sklep[]) s;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sklepy;
    }
}
