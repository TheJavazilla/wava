package javax.swing.border;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

public interface Border {

    /**
     * Paints the border for the specified component with the specified
     * position and size.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    void paintBorder(Component c, Graphics g, int x, int y, int width, int height);

    /**
     * Returns the insets of the border.
     * @param c the component for which this border insets value applies
     */
    Insets getBorderInsets(Component c);

    /**
     * Returns whether or not the border is opaque.  If the border
     * is opaque, it is responsible for filling in it's own
     * background when painting.
     */
    boolean isBorderOpaque();

}