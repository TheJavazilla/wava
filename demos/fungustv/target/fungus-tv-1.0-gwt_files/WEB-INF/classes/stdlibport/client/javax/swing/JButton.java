package stdlibport.client.javax.swing;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

import stdlibport.client.java.awt.Dimension;
import stdlibport.client.java.awt.Insets;

public class JButton extends JComponent {

    public JButton(String string) {
        gwt = new Button();
        ((Button)gwt).setText(string);
    }

    public String getText() {
        return ((Button)gwt).getText();
    }

    public void setText(String string) {
        ((Button)gwt).setHTML(string);
    }

    public void setIcon(ImageIcon icon) {
        ((Button)gwt).getElement().getStyle().setProperty("background", "url('" + ((Image) (icon.getImage().gwt)).getUrl() + "') no-repeat");
    }

    public void setHorizontalAlignment(int align) {
        Style s = ((Button)gwt).getElement().getStyle();
        switch (align) {
            case 0:
                s.setTextAlign(TextAlign.CENTER);
                break;
            case 2:
                s.setTextAlign(TextAlign.LEFT);
                break;
            case 4:
                s.setTextAlign(TextAlign.RIGHT);
                break;
        }
    }

    public void setMargin(Insets i) {
        Style btn = ((Button)gwt).getElement().getStyle();
        btn.setMarginLeft(i.left, Unit.PX);
        btn.setMarginRight(i.right, Unit.PX);
        btn.setMarginTop(i.top, Unit.PX);
        btn.setMarginBottom(i.bottom, Unit.PX);
    }

}