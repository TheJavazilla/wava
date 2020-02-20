package stdlibport.client.kekcroc;

import stdlibport.client.java.awt.Color;
import stdlibport.client.java.awt.Graphics;

import stdlibport.client.javax.swing.JPanel;

public class SplashScreen extends JPanel {

    private static final long serialVersionUID = 1L;

    public SplashScreen() {
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        float f = 58f;
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(f));
        String txt = "FUNGUS";
        g.drawString(txt, (getWidth()/2) - txt.length()*((int)f / 3), getHeight()/2);
        txt = "Presents...";
        f = 22f;
        g.setFont(g.getFont().deriveFont(f));
        g.drawString(txt, (getWidth()/2) - txt.length()*((int)f / 4), getHeight()/2+50);

        txt = "© 2020";
        f = 12f;
        g.setFont(g.getFont().deriveFont(f));
        g.drawString(txt, (getWidth()/2) - txt.length()*((int)f / 4), getHeight()-25);
    }

}