package wava.sun.java2d.pipe;

import wava.sun.java2d.SunGraphics2D;

public interface PixelDrawPipe {

    public void drawLine(SunGraphics2D sg, int x1, int y1, int x2, int y2);

    public void drawRect(SunGraphics2D sg, int x, int y, int width, int height);

    public void drawRoundRect(SunGraphics2D sg, int x, int y, int width, int height, int arcWidth, int arcHeight);

    public void drawOval(SunGraphics2D sg, int x, int y, int width, int height);

    public void drawArc(SunGraphics2D sg, int x, int y, int width, int height, int startAngle, int arcAngle);

    public void drawPolyline(SunGraphics2D sg, int xPoints[], int yPoints[], int nPoints);

    public void drawPolygon(SunGraphics2D sg, int xPoints[], int yPoints[], int nPoints);

}