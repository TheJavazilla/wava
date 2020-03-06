package com.fungus_soft;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame {

    public static void main(String[] args) {
        Calculator frame = new Calculator();
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setSize(frame.getWidth() - 12, frame.getHeight() + 73);
        frame.setVisible(true);
    }

    private static final long serialVersionUID = 1L;

    private JLabel display = new JLabel("0");
    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public Calculator() {
        JPanel p = new JPanel();
        p.add(display);

        JPanel panel = new JPanel();
        JPanel simple = new JPanel();
        simple.setLayout(new GridLayout(5, 4));

        String buttonLabels = "789/456*123-0.=+";

        ((JButton)simple.add(new JButton("C"))).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operator = "C";
                calculate(0);
            }
        });

        for (int i = 0; i < buttonLabels.length(); i++) {
            int i_final = i;
            JButton btn = ((JButton)simple.add(new JButton(buttonLabels.substring(i, i + 1))));
            char ch = btn.getText().charAt(0);
            if ('0' <= ch && ch <= '9' || ch == '.') {
                btn.setBackground(Color.WHITE);
            } else btn.setBackground(Color.LIGHT_GRAY);

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    actionPerformed(buttonLabels.substring(i_final, i_final + 1));
                }
            });
        }

        panel.add(simple);
        p.add(panel);
        setContentPane(p);
    }

    public void actionPerformed(String cmd) {
        if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
            if (calculating) display.setText(cmd);
            else display.setText(display.getText() + cmd);

            calculating = false;
        } else {
            if (calculating) {
                if (cmd.equals("-")) {
                    display.setText(cmd);
                    calculating = false;
                } else operator = cmd;
            } else {
                double x = Double.parseDouble(display.getText());
                calculate(x);
                operator = cmd;
                calculating = true;
            }
        }
    }

    private void calculate(double n) {
        switch (operator) {
            case "+":
                result += n;
                break;
            case "-":
                result -= n;
                break;
            case "*":
                result *= n;
                break;
            case "/":
                result /= n;
                  break;
            case "=":
                result = n;
                break;
            case "C":
                this.calculating = true;
                result = n;
                break;
        }
        display.setText("" + (String.valueOf(result).endsWith(".0") ? (int)result : result));
    }

 }