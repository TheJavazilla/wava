package stdlibport.client.javax.imageio;

import stdlibport.client.java.io.File;
import java.io.IOException;
import java.io.InputStream;

import stdlibport.client.java.awt.Image;

public class ImageIO {

    public static Image read(File file) throws IOException {
        return null;
    }

    public static Image read(InputStream resourceAsStream) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    public static Image gwtDebug(String name) {
        return new Image(name);
    }

}
