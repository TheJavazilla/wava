package wava.sun.java2d.pipe;

import java.awt.Shape;
import wava.sun.java2d.SunGraphics2D;

/**
 * This interface defines the set of calls that pipeline objects
 * can use to pass on responsibility for drawing generic Shape
 * objects.
 */
public interface ShapeDrawPipe {

    public void draw(SunGraphics2D sg, Shape s);
    public void fill(SunGraphics2D sg, Shape s);

}