
package wava.sun.java2d.pipe;

import java.awt.Rectangle;
import java.awt.Shape;
import wava.sun.java2d.SunGraphics2D;

public interface CompositePipe {
    public Object startSequence(SunGraphics2D sg, Shape s, Rectangle dev,
                                int[] abox);

    public boolean needTile(Object context, int x, int y, int w, int h);

    public void renderPathTile(Object context,
                               byte[] atile, int offset, int tilesize,
                               int x, int y, int w, int h);

    public void skipTile(Object context, int x, int y);

    public void endSequence(Object context);
}
