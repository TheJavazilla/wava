/*
 * Decompiled with CFR 0.145.
 * from https://files.fungus-soft.com/cs-class/TickTackToe.exe
 */
package com.fungus_soft;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TickTackToe extends JFrame {

    public static TickTackToe inst;
    public int turn = 0;
    private JPanel btns;
    private Random r = new Random();
    private boolean useAi = false;

    public TickTackToe() {
        inst = this;
        this.setDefaultCloseOperation(3);
        this.btns = new JPanel();
        this.btns.setLayout(new GridLayout(3, 3));
        this.btns.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Object[] options = new String[]{"One Player (against Computer)", "Two player"};
        this.useAi = JOptionPane.showOptionDialog(null, "Pick your opponent", "TickTackToe", 0, 1, null, options, null) == 0;
        for (int i = 0; i < 9; ++i) {
            JButton b = (JButton)this.btns.add(new JButton());
            b.addActionListener(a -> {
                if (b.getText().length() <= 0) {
                    b.setText(this.turn == 0 ? "X" : "O");
                    String gameOver = this.gameOver();
                    if (!gameOver.equalsIgnoreCase("no")) {
                        int ch = JOptionPane.showConfirmDialog(null, String.valueOf(gameOver) + " has won the game!\nPlay again?\n", "Game over", 0);
                        if (ch == 0) {
                            for (Component c : this.btns.getComponents())
                                ((JButton)c).setText("");
                        }
                    } else {
                        int ch;
                        int tie = 0;

                        for (Component c : this.btns.getComponents()) {
                            if (((JButton)c).getText().length() <= 0) continue;
                            ++tie;
                        }
                        if (tie >= this.btns.getComponentCount() && (ch = JOptionPane.showConfirmDialog(null, "Tied game!\nPlay again?\n", "Game over", 0)) == 0)
                            for (Component c2 : this.btns.getComponents())
                                ((JButton)c2).setText("");

                    }
                    int n = this.turn = this.turn == 0 ? 1 : 0;
                    if (this.useAi && this.turn == 1)
                        this.aiMove();
                }
            });
        }
        JPanel p = new JPanel();
        p.add(this.btns);
        this.setTitle("TickTackToe");
        this.setContentPane(p);
        this.setSize(400, 420);
        this.setLocationRelativeTo(null);
    }

    public void aiMove() {
        this.random().doClick();
    }

    private JButton random() {
        JButton btn = (JButton)this.btns.getComponents()[this.r.nextInt(this.btns.getComponents().length)];
        if (btn.getText().length() <= 0)
            return btn;

        return this.random();
    }

    public String gameOver() {
        if (this.check(0, 1, 2) || this.check(0, 4, 8) || this.check(0, 3, 6) || this.check(2, 5, 8) || this.check(3, 4, 5) || this.check(6, 7, 8) || this.check(6, 4, 2) || this.check(1, 4, 7))
            return this.turn == 0 ? "X" : "O";
        return "no";
    }

    private boolean check(int a, int b, int c) {
        String d = ((JButton)this.btns.getComponents()[a]).getText();
        String e = ((JButton)this.btns.getComponents()[b]).getText();
        String f = ((JButton)this.btns.getComponents()[c]).getText();
        if (d.length() <= 0 || e.length() <= 0 || f.length() <= 0)
            return false;
        return d.equalsIgnoreCase(e) && e.equalsIgnoreCase(f);
    }

    public static void main(String[] args) {
        TickTackToe frame = new TickTackToe();
        frame.setVisible(true);
    }

}