package stdlibport.client.javax.swing;

import stdlibport.client.javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import stdlibport.client.java.awt.Font;

public class JLabel extends JComponent {
    
    private Label l;
    //private Image i;

    public JLabel(String string) {
        this();
        l.setText(string);
    }

    public JLabel(ImageIcon imageIcon) {
        // TODO Auto-generated constructor stub
        this();
        setIcon(imageIcon);
    }

    public JLabel() {
        super();
        gwt = new HorizontalPanel();
        l = new Label();
        l.getElement().getStyle().getFontSize();
        //i = new Image();
        ((HorizontalPanel)gwt).add(l);
        //((HorizontalPanel)gwt).add(i);
    }

    public Font getFont() {
        return Font.fromGWT(l.getElement().getStyle());
    }

    public void setFont(Font font) {
        int size = font.getSize();
        int style = font.getStyle();
        Style s = l.getElement().getStyle();
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

    public void setIcon(ImageIcon imageIcon) {
        // TODO Auto-generated method stub
        ((HorizontalPanel)gwt).add(imageIcon.gwt);
    }

}