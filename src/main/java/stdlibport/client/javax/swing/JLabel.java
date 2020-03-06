package stdlibport.client.javax.swing;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import stdlibport.client.java.awt.Font;
import stdlibport.client.java.awt.event.MouseEvent;
import stdlibport.client.java.awt.event.MouseListener;

public class JLabel extends JComponent {
    
    public Label l;
    private Image i;

    public JLabel(String string) {
        this();
        l.setText(string);
    }

    public JLabel(ImageIcon imageIcon) {
        this();
        setIcon(imageIcon);
    }

    public JLabel() {
        super();
        gwt = new HorizontalPanel();
        l = new Label();
        ((HorizontalPanel)gwt).add(l);
    }

    public String getText() {
        return l.getText();
    }

    public void setText(String text) {
        l.setText(text);
    }

    @Override
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

    @Override
    public Font getFont() {
        return Font.fromGWT(l.getElement().getStyle());
    }

    public void setIcon(ImageIcon imageIcon) {
        // TODO Auto-generated method stub
        ((HorizontalPanel)gwt).add(imageIcon.gwt);
        this.i = (Image)imageIcon.gwt;
    }

    @Override
    public void addMouseListener(MouseListener ml) {
        ClickHandler mh = new ClickHandler() {
            public void onClick(ClickEvent event) {
                ml.mouseClicked(MouseEvent.fromGWT(event));
              }
            };
        l.addClickHandler(mh);
        gwt.addHandler(mh, ClickEvent.getType());
        i.addClickHandler(mh);
    }

}