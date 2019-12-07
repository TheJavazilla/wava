package stdlibport.client.java.awt;

public class Color implements Paint {
    
    /**
     * The color white.  In the default sRGB space.
     */
    public final static Color white     = new Color(255, 255, 255);

    /**
     * The color white.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color WHITE = white;

    /**
     * The color light gray.  In the default sRGB space.
     */
    public final static Color lightGray = new Color(192, 192, 192);

    /**
     * The color light gray.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color LIGHT_GRAY = lightGray;

    /**
     * The color gray.  In the default sRGB space.
     */
    public final static Color gray      = new Color(128, 128, 128);

    /**
     * The color gray.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color GRAY = gray;

    /**
     * The color dark gray.  In the default sRGB space.
     */
    public final static Color darkGray  = new Color(64, 64, 64);

    /**
     * The color dark gray.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color DARK_GRAY = darkGray;

    /**
     * The color black.  In the default sRGB space.
     */
    public final static Color black     = new Color(0, 0, 0);

    /**
     * The color black.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color BLACK = black;

    /**
     * The color red.  In the default sRGB space.
     */
    public final static Color red       = new Color(255, 0, 0);

    /**
     * The color red.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color RED = red;

    /**
     * The color pink.  In the default sRGB space.
     */
    public final static Color pink      = new Color(255, 175, 175);

    /**
     * The color pink.  In the default sRGB space.
     * @since 1.4
     */
    public final static Color PINK = pink;

    /**
     * The color orange.  In the default sRGB space.
     */
    public final static Color orange    = new Color(255, 200, 0);

    /**
     * The color orange.  In the default sRGB space.
     * @since 1.4
     */
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


    private int r, g, b;
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        // TODO Auto-generated constructor stub
    }


    public Color(int i, int j, int k, int l) {
        this (i, j, k); // TODO: l
    }

    @Override
    public int getTransparency() {
        // TODO Auto-generated method stub
        return 0;
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
