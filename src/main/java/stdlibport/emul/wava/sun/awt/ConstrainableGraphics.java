package wava.sun.awt;

/**
 * This interface can be implemented on a Graphics object to allow
 * the lightweight component code to permanently install a rectangular
 * maximum clip that cannot be extended with setClip and which works in
 * conjunction with the hit() and getTransform() methods of Graphics2D
 * to make it appear as if there really was a component with these
 * dimensions.
 */
public interface ConstrainableGraphics {

    public void constrain(int x, int y, int w, int h);

}