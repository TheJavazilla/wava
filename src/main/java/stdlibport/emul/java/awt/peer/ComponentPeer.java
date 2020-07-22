package java.awt.peer;

import java.awt.*;
import java.awt.event.PaintEvent;
//import java.awt.event.PaintEvent;
//import java.awt.image.ColorModel;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
//import java.awt.image.VolatileImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;

//import wava.sun.awt.CausedFocusEvent;
//import wava.sun.java2d.pipe.Region;

/**
 * The peer interface for {@link Component}. This is the top level peer
 * interface for widgets and defines the bulk of methods for AWT component
 * peers. Most component peers have to implement this interface (via one
 * of the subinterfaces), except menu components, which implement
 * {@link MenuComponentPeer}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface ComponentPeer {

    public static final int SET_LOCATION = 1;
    public static final int SET_SIZE = 2;
    public static final int SET_BOUNDS = 3;
    public static final int SET_CLIENT_SIZE = 4;
    public static final int RESET_OPERATION = 5;
    public static final int NO_EMBEDDED_CHECK = (1 << 14);

    public static final int DEFAULT_OPERATION = SET_BOUNDS;

    boolean isObscured();

    boolean canDetermineObscurity();

    void setVisible(boolean v);

    void setEnabled(boolean e);

    void paint(Graphics g);

    void print(Graphics g);

    void setBounds(int x, int y, int width, int height, int op);

    void handleEvent(AWTEvent e);

    void coalescePaintEvent(PaintEvent e);

    Point getLocationOnScreen();

    Dimension getPreferredSize();

    Dimension getMinimumSize();

    ColorModel getColorModel();

    // TODO: Maybe change this to force Graphics2D, since many things will
    // break with plain Graphics nowadays.
    Graphics getGraphics();

    FontMetrics getFontMetrics(Font font);

    void dispose();

    void setForeground(Color c);

    void setBackground(Color c);

    void setFont(Font f);

    void updateCursorImmediately();

    //boolean requestFocus(Component lightweightChild, boolean temporary, boolean focusedWindowChangeAllowed, long time, CausedFocusEvent.Cause cause);

    boolean isFocusable();

    Image createImage(ImageProducer producer);

    // TODO: Maybe make that return a BufferedImage, because some stuff will
    // break if a different kind of image is returned.
    Image createImage(int width, int height);

    // TODO: Include capabilities here and fix Component#createVolatileImage
    VolatileImage createVolatileImage(int width, int height);

    boolean prepareImage(Image img, int w, int h, ImageObserver o);

    int checkImage(Image img, int w, int h, ImageObserver o);

    GraphicsConfiguration getGraphicsConfiguration();

    boolean handlesWheelScrolling();

    void createBuffers(int numBuffers, BufferCapabilities caps) throws AWTException;

    Image getBackBuffer();

    void flip(int x1, int y1, int x2, int y2, BufferCapabilities.FlipContents flipAction);

    void destroyBuffers();

    void reparent(ContainerPeer newContainer);

    boolean isReparentSupported();

    void layout();

    void applyShape(Object shape);

    void setZOrder(ComponentPeer above);

    boolean updateGraphicsData(GraphicsConfiguration gc);

}