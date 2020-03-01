package stdlibport.client.javax.swing;

import com.google.gwt.user.client.ui.Button;

import stdlibport.client.java.awt.Dimension;

public class JButton extends JComponent {

    public JButton(String string) {
        gwt = new Button();
        ((Button)gwt).setText(string);
    }

    public String getText() {
        return ((Button)gwt).getText();
    }

    public void setText(String string) {
        ((Button)gwt).setText(string);
    }

    public void setIcon(ImageIcon icon) {
        // TODO Auto-generated method stub
        
    }

    public void setHorizontalAlignment(String left) {
        // TODO Auto-generated method stub
        
    }

}