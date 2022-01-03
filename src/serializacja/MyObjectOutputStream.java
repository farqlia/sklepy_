package serializacja;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {

    MyObjectOutputStream(OutputStream o) throws IOException {
        super(o);
    }

    @Override
    protected void writeStreamHeader() {
    }
}

