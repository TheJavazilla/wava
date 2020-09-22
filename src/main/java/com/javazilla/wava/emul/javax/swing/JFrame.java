package javax.swing;

import com.google.gwt.user.client.ui.DialogBox;

public class JFrame extends JComponent {

    private JComponent contentPane;

    public JFrame() {
        this("Untitled");
    }

    public JFrame(String string) {
        super();
        gwt = new DialogBox();
        setTitle(string);
    }

    public void setTitle(String s) {
        ((DialogBox)gwt).setText(s);
    }

    public void setDefaultCloseOperation(int operation) {
        // TODO
    }

    public void setContentPane(JComponent p) {
        if (p instanceof JScrollPane) {
            // Fix scroll pane size
            ((JScrollPane)p).fixSize(this.getSize());
        }
        this.contentPane = p;
        ((DialogBox)gwt).setWidget(p.gwt);
        
    }

    public JComponent getContentPane() {
        return this.contentPane;
    }

    public void pack() {
        // TODO
        this.setSize(this.getContentPane().getSize());
    }

}