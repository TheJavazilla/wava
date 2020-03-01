package stdlibport.client.kekcroc;

import stdlibport.client.javax.swing.JComponent;
import stdlibport.client.javax.swing.JFrame;
import stdlibport.client.javax.swing.Timer;

public class Kekcroc extends JFrame {

    private static final long serialVersionUID = 1L;
    public static Kekcroc GAME;

    public Kekcroc() {
        GAME = this;
        setDefaultCloseOperation(3);
        //setSize(800,600);
        //setLocationRelativeTo(null);
        //setResizable(false);
        setTitle("Kekcroc (PC demo)");

        //setContentPane(new SplashScreen());
        //Timer timer = new Timer(3200, e -> {
            setContentPane(new MainMenu());
        //    validate();
        //});
        //timer.setRepeats(false);
        //timer.start(); 

        setVisible(true);
    }
    
    public void setContent(JComponent c) {
        setContentPane(c);
        validate();
    }

    public static void main(String[] args) {
        new Kekcroc();
    }

}