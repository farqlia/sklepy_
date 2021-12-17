package serializacja;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializatorZwykly<E> extends Serializator<E> {

    private static final long serialVersionUID = 99L;

    public SerializatorZwykly(String sciezkaFolderu, String nazwaPliku) {
        super(sciezkaFolderu, nazwaPliku);
    }

    public void serializuj(List<E> obiekty){

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(sciezkaPliku))) {
            objectOutputStream.writeObject(obiekty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<E> deserializuj(){
        List<E> list = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(sciezkaPliku))) {
            Object zdeserializowanaLista = objectInputStream.readObject();
            list = (List<E>) zdeserializowanaLista;
        } catch (EOFException e){
            System.out.println("Pusty plik");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
