package stdlibport.client.javax.swing;

import stdlibport.client.java.awt.event.MouseEvent;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

import stdlibport.client.java.awt.Dimension;
import stdlibport.client.java.awt.Font;
import stdlibport.client.java.awt.event.MouseListener;
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
    
    public void setMinimumSize(Dimension dimension) {
        setSize(dimension); // TODO
    }

    public void setMaximumSize(Dimension dimension) {
        setSize(dimension); // TODO
    }

    public void setPreferedSize(Dimension dimension) {
        setSize(dimension); // TODO
    }
    
    public Dimension getSize() {
        return new Dimension(width, height);
    }

    public Dimension getMaximumSize() {
        return new Dimension(width, height);
    }

    public Dimension getMinimumSize() {
        return new Dimension(width, height);
    }

    public Dimension getPreferedSize() {
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
        gwt.getElement().getStyle().setProperty("background", "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")");
        gwt.getElement().getStyle().setBackgroundColor("rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")");
    }

    public void addMouseListener(MouseListener ml) {
        ClickHandler mh = new ClickHandler() {
            public void onClick(ClickEvent event) {
                ml.mouseClicked(MouseEvent.fromGWT(event));
              }
            };
        // TODO better handlers
        if (gwt instanceof Button)
            ((Button)gwt).addClickHandler(mh);
        else gwt.addHandler(mh, ClickEvent.getType());
    }

    public Font getFont() {
        return Font.fromGWT(gwt.getElement().getStyle());
    }

    public void setFont(Font font) {
        int size = font.getSize();
        int style = font.getStyle();
        Style s = gwt.getElement().getStyle();
        s.setFontSize(size, Unit.PX);
        switch (style) {
            case 0: // PLAIN
                s.setFontStyle(FontStyle.NORMAL);
                s.setFontWeight(FontWeight.NORMAL);
                break;
            case 1: // BOLD
                s.setFontWeight(FontWeight.BOLD);
                break;
            case 2: // ITALIC
                s.setFontStyle(FontStyle.ITALIC);
                break;
        }
    }

    public void setOpaque(boolean b) {
        // TODO Auto-generated method stub
    }

}