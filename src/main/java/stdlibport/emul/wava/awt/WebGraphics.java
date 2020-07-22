package wava.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

/**
 * Graphics2D replacement for GWT
 */
public class WebGraphics extends Graphics {

    private final Context2d context;
    private final Component component;

    public WebGraphics(Context2d context) {
        this.context = context;
        this.component = null;
    }

    public WebGraphics(Context2d context, Component component) {
        this.context = context;
        this.component = component;
    }

    @Override
    public void forceRepaint() {
        if (null != component) component.repaint();
    }

    @Override
    public Context2d getContext2d() {
        return context;
    }

    @Override
    public void translate(int x, int y) {
        context.translate(x, y);
    }

    private String convert(Color color, float alpha) {
        return "rgba(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ", " + alpha + ")";
    }

    @Override
    public void setColor(Color color) {
        if (color != null) {
            float alpha = color.getAlpha() / 255f;
            String colorString = convert(color, alpha);
            context.setStrokeStyle(colorString);
        } else System.out.println("Ignoring null-Color");
    }

    @Override
    public void setFillColor(Color color) {
        if (color == null)
            return;

        float alpha = (float) color.getAlpha() / 255f;
        context.setFillStyle(convert(color, alpha));
    }

    @Override
    public void setStroke(BasicStroke stroke) {
        if (stroke != null) {
            // TODO support for stroke pattern as soon ie is support stroke patterns
            if (stroke.getDashArray() != null)
                System.err.println("Ignoring stroke pattern. They aren't suppoted yet");

            context.setMiterLimit(stroke.getMiterLimit());
            context.setLineWidth(stroke.getLineWidth());

            switch (stroke.getLineJoin()){
                case BasicStroke.JOIN_BEVEL :
                    context.setLineJoin(LineJoin.BEVEL);
                    break;
                case BasicStroke.JOIN_MITER :
                    context.setLineJoin(LineJoin.MITER);
                    break;
                case BasicStroke.JOIN_ROUND :
                    context.setLineJoin(LineJoin.ROUND);
                    break;
                default :
                    System.err.println("unknown line join type");
                    throw new IllegalArgumentException("illegal line join value");
            }

            switch (stroke.getEndCap()){
                case BasicStroke.CAP_BUTT :
                    context.setLineCap(LineCap.BUTT);
                    break;
                case BasicStroke.CAP_ROUND :
                    context.setLineCap(LineCap.ROUND);
                    break;
                case BasicStroke.CAP_SQUARE :
                    context.setLineCap(LineCap.SQUARE);
                    break;
                default :
                    System.err.println("unknown line cap type");
                    throw new IllegalArgumentException("illegal line cap value");
                }
        }
    }

    @Override
    public void draw(Shape shape) {
        if (shape != null) {
            path(shape);
            context.stroke();
        } else System.err.println("dreckdreckdreck");
    }

    @Override
    public void fill(Shape shape) {
        if (shape == null)
            return;

        path(shape);
        context.fill();
    }

    private void path(Shape shape) {
        path(shape.getPathIterator(null));
    }

    private void path(PathIterator i) {
        float[] coords = new float[6];
        context.beginPath();
        while (!i.isDone()) {
            int segment = i.currentSegment(coords);
            if (segment == PathIterator.SEG_MOVETO) {
                context.moveTo(coords[0], coords[1]);
            } else if (segment == PathIterator.SEG_LINETO) {
                context.lineTo(coords[0], coords[1]);
            } else if (segment == PathIterator.SEG_QUADTO) {
                context.quadraticCurveTo(coords[0], coords[1], coords[2], coords[3]);
            } else if (segment == PathIterator.SEG_CUBICTO) {
                context.bezierCurveTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
            } else if (segment == PathIterator.SEG_CLOSE) {
                context.closePath();
            } else throw new RuntimeException("Unknown Segment " + segment);
        i.next();
      }
    }

  //  @Override
  //  public void setFont(Font font) {
//      if (font != null && font.getFontName() != null)
//        context.setFont(font.getFontName());
  //  }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        // TODO validate, intersec, null
        this.setClip(x, y, width, height);
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        context.beginPath();
        context.moveTo(x, y);
        context.lineTo(x + width, y);
        context.lineTo(x + width, y + height);
        context.lineTo(x, y + height);
        context.lineTo(x, y);
        context.closePath();
        context.clip();
    }

    @Override
    public void setClip(Shape clip) {
        path(clip);
        context.clip();
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        context.clearRect(x, y, width, height);
    }

    @Override
    public void drawString(String str, int x, int y) {
        context.strokeText(str, x, y);
    }

    @Override
    public void drawChars(char data[], int offset, int length, int x, int y) {
        drawString(new String(data, offset, length), x, y);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2) {
        context.beginPath();
        context.moveTo(x, y);
        context.lineTo(x2, y2);
        context.stroke();
    }

    @Override
    public Font getFont() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFont(Font font) {
        // TODO Auto-generated method stub
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        context.fillRect(x, y, width, height);
    }

    @Override
    public Shape getClip() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean drawImage(Image image, int x, int y, ImageObserver b) {
        // TODO Auto-generated method stub
        return false;
    }

    public void constrain(int i, int j, int k, int l) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean drawImage(Image image, int x, int y, int width, int height, ImageObserver b) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public FontMetrics getFontMetrics() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setXORMode(Color background) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPaintMode() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Graphics create() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rectangle getClipBounds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
            ImageObserver observer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
            Color bgcolor, ImageObserver observer) {
        // TODO Auto-generated method stub
        return false;
    }

}