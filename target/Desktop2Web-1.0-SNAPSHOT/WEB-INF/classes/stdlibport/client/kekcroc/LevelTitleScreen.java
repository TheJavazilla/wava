package stdlibport.client.kekcroc;

import stdlibport.client.java.awt.Color;
import stdlibport.client.java.awt.Graphics;

import stdlibport.client.javax.swing.JPanel;

public class LevelTitleScreen extends JPanel {

    private static final long serialVersionUID = 1L;

    public LevelTitleScreen() {
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        float f = 48f;
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(f));
        String txt = "Level 1";
        g.drawString(txt, (getWidth()/2) - txt.length()*((int)f / 4), getHeight()/2);

        txt = "This Kekcroc game is © 2020 under GPLv3. Based on the 4chan meme";
        f = 12f;
        g.setFont(g.getFont().deriveFont(f));
        g.drawString(txt, (getWidth()/2) - txt.length()*((int)f / 4), getHeight()-22);
    }

}