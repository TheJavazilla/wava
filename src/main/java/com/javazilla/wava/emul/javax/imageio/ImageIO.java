package javax.imageio;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.io.InputStream;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageIO {

    public static BufferedImage read(File file) throws IOException {
        return null;
    }

    public static Image read(InputStream resourceAsStream) throws IOException {
        return null;
    }

    public static BufferedImage read(URL url) throws IOException {
        // TODO Auto-generated method stub
        if (url == null)
            throw new IOException();
        return new BufferedImage(url);
    }

    public static void write(BufferedImage bi, String string, File fi) {
        // TODO Auto-generated method stub
    }

}