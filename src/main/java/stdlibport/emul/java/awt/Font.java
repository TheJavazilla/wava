package java.awt;

import com.google.gwt.dom.client.Style;

public class Font {
    
    /*
     * Constants to be used for logical font family names.
     */
    public static final String DIALOG       = "Dialog";
    public static final String DIALOG_INPUT = "DialogInput";
    public static final String SANS_SERIF   = "SansSerif";
    public static final String SERIF        = "Serif";
    public static final String MONOSPACED   = "Monospaced";

    /*
     * Constants to be used for styles. Can be combined to mix styles.
     */
    public static final int PLAIN   = 0;
    public static final int BOLD    = 1;
    public static final int ITALIC  = 2;

    public static final int ROMAN_BASELINE   = 0;
    public static final int CENTER_BASELINE  = 1;
    public static final int HANGING_BASELINE = 2;

    public static final int TRUETYPE_FONT = 0;
    public static final int TYPE1_FONT    = 1;

    protected String name;
    protected int style;
    protected int size;
    protected float pointSize;

    public Font(String name, int style, int size) {
        this.name = name;
        this.style = style;
        this.size = size;
        this.pointSize = size;
    }

    private Font(String name, int style, float sizePts) {
        this.name = name;
        this.style = style;
        this.size = (int) sizePts;
        this.pointSize = sizePts;
    }

    protected Font(Font font) {
        this.name = font.name;
        this.style = font.style;
        this.size = font.size;
        this.pointSize = font.pointSize;
    }

    public Font deriveFont(float f) {
        return new Font(name, style, f);
    }

    public static Font fromGWT(Style gwt) {
        String style = gwt.getFontStyle().trim();
        int styl = 0;
        if (style.equals("normal"))
            styl = 0;
        if (style.equalsIgnoreCase("italic") || style.equalsIgnoreCase("oblique"))
            styl = 2;

        String weight = gwt.getFontWeight().trim();
        if (weight.equalsIgnoreCase("bold"))
            styl = 1;

        String size = gwt.getFontSize().trim();
        int siz = 0;
        if (size.contains("em")) {
            double d = Double.valueOf(size.replace("em", ""));
            siz = (int)(d * 16); // TODO: better EM to PX
        }
        if (size.contains("px"))
            siz = Double.valueOf(size.replace("px", "")).intValue();

        Font f = new Font("Arial", styl, siz);
        return f;
    }

    public int getSize() {
        return size;
    }

    public int getStyle() {
        return style;
    }

}