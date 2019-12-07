package stdlibport.client.javax.swing;

import stdlibport.client.java.awt.event.MouseAdapter;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

import stdlibport.client.java.awt.Dimension;
import stdlibport.client.java.awt.Point;
import stdlibport.client.javax.swing.border.Border;
import stdlibport.client.java.awt.Color;
import stdlibport.client.java.awt.Component;

public class JComponent extends Component {

    protected int width,height;
    public Widget gwt;
    
    public void setPreferredSize(Dimension d) {
        gwt.setSize(d.width + "px", d.height + "px");
    }

    public void setSize(Dimension d) {
        width = d.width;
        height = d.height;
        gwt.setSize(d.width + "px", d.height + "px");
    }

    public void setSize(int w, int h) {
        width = w;
        height = h;
        gwt.setSize(w + "px", h + "px");
    }
    
    public Dimension getSize() {
        return new Dimension(width, height);
    }

    public void setLocationRelativeTo(JComponent jc) {
        // TODO
    }

    public void setLocation(int x, int y) {
        gwt.getElement().getStyle().setPosition(Position.ABSOLUTE);
        gwt.getElement().getStyle().setTop(y, Unit.PX);
        gwt.getElement().getStyle().setLeft(x, Unit.PX);
        // TODO
    }

    public Point getLocation() {
        // TODO
        String t = gwt.getElement().getStyle().getTop().replace("px", "");
        String l = gwt.getElement().getStyle().getLeft().replace("px", "");
        if (t.length() > 0 && l.length() > 0)
            return new Point(Double.valueOf(l).intValue(), Double.valueOf(t).intValue());
        else
        return new Point(0,0); // TODO
    }
    
    public void setVisible(boolean b) {
        gwt.setVisible(b);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void validate() {
        // TODO
    }

    public void repaint() {
        // TODO
    }

    public void setBorder(Border border) {
        // TODO Auto-generated method stub
    }

    public void setForeground(Color c) {
        gwt.getElement().getStyle().setColor("rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")");
    }
    
    public void setBackground(Color c) {
        gwt.getElement().getStyle().setBackgroundColor("rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")");
    }

    public void addMouseListener(MouseAdapter mouseAdapter) {
        // TODO Auto-generated method stub
    }

}