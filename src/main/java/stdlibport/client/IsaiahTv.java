package stdlibport.client;

import stdlibport.client.javax.swing.JFrame;
import stdlibport.client.javax.swing.JLabel;
import stdlibport.client.javax.swing.JPanel;

public class IsaiahTv extends JFrame {

    public IsaiahTv() {
        JPanel p = new JPanel();
        p.add(new JLabel("test JLabel"));
        setContentPane(p);
    }

}