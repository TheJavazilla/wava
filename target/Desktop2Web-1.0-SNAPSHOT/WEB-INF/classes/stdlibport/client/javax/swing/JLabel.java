package stdlibport.client.javax.swing;

import stdlibport.client.java.awt.event.MouseEvent;
import stdlibport.client.java.awt.event.MouseListener;
import stdlibport.client.javax.swing.ImageIcon;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class JLabel extends JComponent {
    
    public Label l;
    private Image i;

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
        //i = new Image();
        ((HorizontalPanel)gwt).add(l);
        //((HorizontalPanel)gwt).add(i);
    }
    
    public void setFontSize(int size) {
        l.getElement().getStyle().setFontSize(size, Unit.PX);
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