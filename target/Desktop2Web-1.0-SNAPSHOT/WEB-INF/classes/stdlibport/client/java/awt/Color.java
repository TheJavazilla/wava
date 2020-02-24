package stdlibport.client.java.awt;

public class Color implements Paint {
    

    public final static Color white     = new Color(255, 255, 255);
    public final static Color WHITE = white;


    public final static Color lightGray = new Color(192, 192, 192);
    public final static Color LIGHT_GRAY = lightGray;


    public final static Color gray      = new Color(128, 128, 128);
    public final static Color GRAY = gray;


    public final static Color darkGray  = new Color(64, 64, 64);
    public final static Color DARK_GRAY = darkGray;


    public final static Color black     = new Color(0, 0, 0);
    public final static Color BLACK = black;


    public final static Color red       = new Color(255, 0, 0);
    public final static Color RED = red;


    public final static Color pink      = new Color(255, 175, 175);
    public final static Color PINK = pink;


    public final static Color orange    = new Color(255, 200, 0);
    public final static Color ORANGE = orange;

    /**
     * The color yellow.  In the default sRGB space.
     */
    public final static Color yellow    = new Color(255, 255, 0);

    /**
     * The color yellow.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color YELLOW = yellow;

    /**
     * The color green.  In the default sRGB space.
     */
    public final static Color green     = new Color(0, 255, 0);

    /**
     * The color green.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color GREEN = green;

    /**
     * The color magenta.  In the default sRGB space.
     */
    public final static Color magenta   = new Color(255, 0, 255);

    /**
     * The color magenta.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color MAGENTA = magenta;

    /**
     * The color cyan.  In the default sRGB space.
     */
    public final static Color cyan      = new Color(0, 255, 255);

    /**
     * The color cyan.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color CYAN = cyan;

    /**
     * The color blue.  In the default sRGB space.
     */
    public final static Color blue      = new Color(0, 0, 255);

    /**
     * The color blue.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color BLUE = blue;

    @SuppressWarnings("unused")
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

    @Override
    public int getTransparency() {
        return a;
    }

    public int getRed() {
        return r;
    }
    
    public int getGreen() {
        return r;
    }
    
    public int getBlue() {
        return r;
    }

}
