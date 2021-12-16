package serializacja;

import sklepy.FirmaDostawcza;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Klasa serializująca i deserializująca obiekty: różni się od klasy
// Historia tym, że nie można 'appendować' do pliku w trakcie działania
// programu
public class Serializator<E> {

    String nazwaPliku;

    public Serializator(String nazwaPliku){
        this.nazwaPliku = nazwaPliku;
    }

    public void stworzPlik() {
        try {
            File file = new File(nazwaPliku);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serializuj(List<E> obiekty){

        stworzPlik();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            objectOutputStream.writeObject(obiekty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")        //
    public List<E> deserializuj(){
        List<E> list = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            Object zdeserializowanaLista = objectInputStream.readObject();
            list = (List<E>) zdeserializowanaLista;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
