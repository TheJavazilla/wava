package com.javazilla.wava.client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;

import com.google.gwt.canvas.dom.client.Context2d;


public interface Graphics extends java.awt.Graphics {

  /**
   * Forces a Repaint of the Component
   */
  public abstract void forceRepaint();

  /**
   * @return the HTML5-Canvas-Context2d Element
   */
  public abstract Context2d getContext2d();

  public abstract void translate(int x, int y);

  /**
   * Sets the Color to Fill/Stroke
   * 
   * @param color the new color
   */
  public abstract void setColor(Color color);

  /**
   * sets the line style
   * 
   * @param stroke the style definition
   * 
   * @throws IllegalArgumentException if stroke contains an pattern for lines - dashArray and
   *           dashPhase aren't supported
   */
  public abstract void setStroke(BasicStroke stroke);

  /**
   * Strokes a shape
   * 
   * @param shape the shape to stroke
   */
  public abstract void draw(Shape shape);

  /**
   * Fills a shape
   * 
   * @param shape the shape to fill
   */
  public abstract void fill(Shape shape);

//  /**
//   * Sets this graphics context's font to the specified font. All subsequent text operations using
//   * this graphics context use this font. A null argument is silently ignored.
//   * 
//   * @param font the font.
//   */
//  public abstract void setFont(Font font);

  /**
   * Intersects the current clip with the specified rectangle. The resulting clipping area is the
   * intersection of the current clipping area and the specified rectangle. If there is no current
   * clipping area, either because the clip has never been set, or the clip has been cleared using
   * <code>setClip(null)</code>, the specified rectangle becomes the new clip. This method sets the
   * user clip, which is independent of the clipping associated with device bounds and window
   * visibility. This method can only be used to make the current clip smaller. To set the current
   * clip larger, use any of the setClip methods. Rendering operations have no effect outside of the
   * clipping area.
   * 
   * @param x the x coordinate of the rectangle to intersect the clip with
   * @param y the y coordinate of the rectangle to intersect the clip with
   * @param width the width of the rectangle to intersect the clip with
   * @param height the height of the rectangle to intersect the clip with
   * @see #setClip(int, int, int, int)
   * @see #setClip(Shape)
   */
  public abstract void clipRect(int x, int y, int width, int height);

  /**
   * Sets the current clip to the rectangle specified by the given coordinates. This method sets the
   * user clip, which is independent of the clipping associated with device bounds and window
   * visibility. Rendering operations have no effect outside of the clipping area.
   * 
   * @param x the <i>x</i> coordinate of the new clip rectangle.
   * @param y the <i>y</i> coordinate of the new clip rectangle.
   * @param width the width of the new clip rectangle.
   * @param height the height of the new clip rectangle.
   */
  public abstract void setClip(int x, int y, int width, int height);

  /**
   * Sets the current clipping area to an arbitrary clip shape. Not all objects that implement the
   * <code>Shape</code> interface can be used to set the clip. The only <code>Shape</code> objects
   * that are guaranteed to be supported are <code>Shape</code> objects that are obtained via the
   * <code>getClip</code> method and via <code>Rectangle</code> objects. This method sets the user
   * clip, which is independent of the clipping associated with device bounds and window visibility.
   * 
   * @param clip the <code>Shape</code> to use to set the clip
   */
  public abstract void setClip(Shape clip);

  /**
   * Clears the specified rectangle by filling it with the background color of the current drawing
   * surface. This operation does not use the current paint mode.
   * <p>
   * Beginning with Java&nbsp;1.1, the background color of offscreen images may be system dependent.
   * Applications should use <code>setColor</code> followed by <code>fillRect</code> to ensure that
   * an offscreen image is cleared to a specific color.
   * 
   * @param x the <i>x</i> coordinate of the rectangle to clear.
   * @param y the <i>y</i> coordinate of the rectangle to clear.
   * @param width the width of the rectangle to clear.
   * @param height the height of the rectangle to clear.
   */
  public abstract void clearRect(int x, int y, int width, int height);

  /**
   * Draws the text given by the specified string, using this graphics context's current font and
   * color. The baseline of the leftmost character is at position (<i>x</i>,&nbsp;<i>y</i>) in this
   * graphics context's coordinate system.
   * 
   * @param str the string to be drawn.
   * @param x the <i>x</i> coordinate.
   * @param y the <i>y</i> coordinate.
   * @throws NullPointerException if <code>str</code> is <code>null</code>.
   */
  public abstract void drawString(String str, int x, int y);

  /**
   * Draws the text given by the specified character array, using this graphics context's current
   * font and color. The baseline of the first character is at position (<i>x</i>,&nbsp;<i>y</i>) in
   * this graphics context's coordinate system.
   * 
   * @param data the array of characters to be drawn
   * @param offset the start offset in the data
   * @param length the number of characters to be drawn
   * @param x the <i>x</i> coordinate of the baseline of the text
   * @param y the <i>y</i> coordinate of the baseline of the text
   * @throws NullPointerException if <code>data</code> is <code>null</code>.
   * @throws IndexOutOfBoundsException if <code>offset</code> or <code>length</code>is less than
   *           zero, or <code>offset+length</code> is greater than the length of the
   *           <code>data</code> array.
   */
  public abstract void drawChars(char data[], int offset, int length, int x, int y);

  public abstract void drawLine(double x, double y, double x2, double y2);

  void setFillColor(Color color);

//  /**
//   * Draws the text given by the specified byte array, using this graphics context's current font
//   * and color. The baseline of the first character is at position (<i>x</i>,&nbsp;<i>y</i>) in this
//   * graphics context's coordinate system.
//   * <p>
//   * Use of this method is not recommended as each byte is interpreted as a Unicode code point in
//   * the range 0 to 255, and so can only be used to draw Latin characters in that range.
//   * 
//   * @param data the data to be drawn
//   * @param offset the start offset in the data
//   * @param length the number of bytes that are drawn
//   * @param x the <i>x</i> coordinate of the baseline of the text
//   * @param y the <i>y</i> coordinate of the baseline of the text
//   * @throws NullPointerException if <code>data</code> is <code>null</code>.
//   * @throws IndexOutOfBoundsException if <code>offset</code> or <code>length</code>is less than
//   *           zero, or <code>offset+length</code> is greater than the length of the
//   *           <code>data</code> array.
//   * @see java.awt.Graphics#drawChars
//   * @see java.awt.Graphics#drawString
//   */
//  public abstract void drawBytes(byte data[], int offset, int length, int x, int y);

}