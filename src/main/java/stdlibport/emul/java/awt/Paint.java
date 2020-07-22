package java.awt;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

public interface Paint extends Transparency {

    PaintContext createContext(ColorModel deviceColorModel, Rectangle devR, Rectangle2D bounds2d,
            AffineTransform cloneTransform, RenderingHints renderingHints);

    // TODO

}