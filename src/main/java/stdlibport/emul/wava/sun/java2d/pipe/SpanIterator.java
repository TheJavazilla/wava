package wava.sun.java2d.pipe;

/**
 * This interface defines a general method for iterating through the
 * rectangular "spans" that represent the interior of a filled path.
 * <p>
 * There can be many kinds of span iterators used in the rendering
 * pipeline, the most basic being an iterator that scan converts a
 * path defined by any PathIterator, or an nested iterator which
 * intersects another iterator's spans with a clip region.
 * Other iterators can be created for scan converting some of the
 * primitive shapes more explicitly for speed or quality.
 *
 * @author Jim Graham
 */
public interface SpanIterator {

    public void getPathBox(int pathbox[]);

    public void intersectClipBox(int lox, int loy, int hix, int hiy);

    public boolean nextSpan(int spanbox[]);

    public void skipDownTo(int y);

    public long getNativeIterator();

}