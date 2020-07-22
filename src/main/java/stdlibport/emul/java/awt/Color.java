package java.awt;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

public class Color implements Paint {
    

    public final static Color white = new Color(255, 255, 255);
    public final static Color WHITE = white;


    public final static Color lightGray  = new Color(192, 192, 192);
    public final static Color LIGHT_GRAY = lightGray;


    public final static Color gray = new Color(128, 128, 128);
    public final static Color GRAY = gray;


    public final static Color darkGray  = new Color(64, 64, 64);
    public final static Color DARK_GRAY = darkGray;


    public final static Color black = new Color(0, 0, 0);
    public final static Color BLACK = black;


    public final static Color red = new Color(255, 0, 0);
    public final static Color RED = red;


    public final static Color pink = new Color(255, 175, 175);
    public final static Color PINK = pink;


    public final static Color orange = new Color(255, 200, 0);
    public final static Color ORANGE = orange;


    public final static Color yellow = new Color(255, 255, 0);
    public final static Color YELLOW = yellow;


    public final static Color green = new Color(0, 255, 0);
    public final static Color GREEN = green;


    public final static Color magenta = new Color(255, 0, 255);
    public final static Color MAGENTA = magenta;


    public final static Color cyan = new Color(0, 255, 255);
    public final static Color CYAN = cyan;


    public final static Color blue = new Color(0, 0, 255);
    public final static Color BLUE = blue;

    public int value;
    private int r, g, b, a;

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(int bgArgb, boolean bgHasAlpha) {
        // TODO Auto-generated constructor stub
    }

    public Color(int rgb) {
        // TODO Auto-generated constructor stub
    }

    public Color(float r, float g, float b) {
        this((int)r, (int)g, (int)b);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getTransparency() {
        return a;
    }

    public int getRed() {
        return r;
    }
    
    public int getGreen() {
        return g;
    }
    
    public int getBlue() {
        return b;
    }

    public int getAlpha() {
        return a;
    }

    public int getRGB() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PaintContext createContext(ColorModel deviceColorModel, Rectangle devR, Rectangle2D bounds2d,
            AffineTransform cloneTransform, RenderingHints renderingHints) {
        // TODO Auto-generated method stub
        return null;
    }

    public Color brighter() {
        // TODO Auto-generated method stub
        return null;
    }

    public Color darker() {
        // TODO Auto-generated method stub
        return null;
    }

    public static Color decode(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    public static float[] RGBtoHSB(int red2, int green2, int blue2, Object object) {
        // TODO Auto-generated method stub
        return null;
    }

    public static int HSBtoRGB(float f, float h, float i) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static Color getHSBColor(float hOffset, float sOffset, float bOffset) {
        // TODO Auto-generated method stub
        return null;
    }

}
