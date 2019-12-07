package stdlibport.client.javax.swing;

import com.google.gwt.user.client.ui.ScrollPanel;

import stdlibport.client.java.awt.Color;

public class JScrollPane extends JComponent {

    public JScrollPane() {
        gwt = new ScrollPanel();
    }

    public JScrollPane(JPanel p) {
        this();
        ((ScrollPanel)gwt).setWidget(p.gwt);
    }

    public void setBackground(Color white) {
        // TODO Auto-generated method stub
    }

    public JComponent add(JComponent j) {
        ((ScrollPanel)gwt).add(j.gwt);
        return j;
    }

}