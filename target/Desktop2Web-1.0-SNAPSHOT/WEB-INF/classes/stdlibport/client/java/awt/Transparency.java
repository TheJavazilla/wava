package stdlibport.client.java.awt;

/**
 * The <code>Transparency</code> interface defines the common transparency
 * modes for implementing classes.
 */
public interface Transparency {

    public final static int OPAQUE = 1;

    public final static int BITMASK = 2;

    public final static int TRANSLUCENT = 3;

    public int getTransparency();
}