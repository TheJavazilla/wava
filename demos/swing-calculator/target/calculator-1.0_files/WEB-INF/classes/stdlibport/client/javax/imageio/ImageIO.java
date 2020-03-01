package stdlibport.client.javax.imageio;

import stdlibport.client.java.io.File;
import stdlibport.client.java.net.URL;

import java.io.IOException;
import java.io.InputStream;

import stdlibport.client.java.awt.Image;
import stdlibport.client.java.awt.image.BufferedImage;

public class ImageIO {

    public static BufferedImage read(File file) throws IOException {
        return null;
    }

    public static Image read(InputStream resourceAsStream) throws IOException {
        return null;
    }

    public static BufferedImage gwtDebug(String name) {
        return new BufferedImage(name);
    }

    public static BufferedImage read(URL url) {
        // TODO Auto-generated method stub
        return new BufferedImage(url.toString());
    }

    public static void write(BufferedImage bi, String string, File fi) {
        // TODO Auto-generated method stub
    }

}