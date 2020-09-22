package com.javazilla.wava.client;

import java.awt.Component;
import java.awt.Graphics;

public class IsaiahTv extends Component {

    public IsaiahTv() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Test of AWT drawString", 50, 40);
    }

}