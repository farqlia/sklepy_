package serializacja;

public abstract class Serializator {

    public abstract void serializuj(Object[] objekty);

    public abstract Object[] deserializuj();
}
