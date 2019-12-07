package stdlibport.client.javax.swing;

import com.google.gwt.user.client.ui.Button;

public class JButton extends JComponent {

    public JButton(String string) {
        gwt = new Button();
        ((Button)gwt).setText(string);
    }

}
