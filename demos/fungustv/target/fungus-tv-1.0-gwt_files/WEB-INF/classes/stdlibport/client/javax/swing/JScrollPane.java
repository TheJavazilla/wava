package stdlibport.client.javax.swing;

import com.google.gwt.user.client.ui.ScrollPanel;

import stdlibport.client.java.awt.Color;
import stdlibport.client.java.awt.Dimension;

public class JScrollPane extends JComponent {

    public JScrollPane() {
        gwt = new ScrollPanel();
    }

    public JScrollPane(JComponent p) {
        this();
        ((ScrollPanel)gwt).setWidget(p.gwt);
    }

    public void fixSize(Dimension d) {
        this.gwt.setSize(d.width-2 + "px", d.height-2 + "px");
    }

    public void setBackground(Color white) {
        // TODO Auto-generated method stub
    }

    public JComponent add(JComponent j) {
        ((ScrollPanel)gwt).add(j.gwt);
        return j;
    }

}