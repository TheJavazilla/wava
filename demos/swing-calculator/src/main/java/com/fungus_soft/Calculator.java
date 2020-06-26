package com.fungus_soft;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Calculator extends JFrame {

    public static void main(String[] args) {
        Calculator frame = new Calculator();
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setSize(frame.getWidth() - 12, frame.getHeight() + 73);
        frame.setVisible(true);
    }

private static final long serialVersionUID = 1L;

    private JLabel display;
    private float result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public Calculator() {
        display = new JLabel("0");
        result = 0;
        operator = "=";
        calculating = true;

        JPanel p = new JPanel();
        p.add(display);
        display.setBorder(BorderFactory.createEmptyBorder(12,2,12,18));
        //display.setFont(display.getFont().deriveFont(16f));
        p.setBorder(BorderFactory.createEmptyBorder(14,10,10,10));
        display.setBackground(Color.WHITE);
        display.setOpaque(true);

        JPanel simple = new JPanel();
        simple.setBorder(BorderFactory.createEmptyBorder(14,0,0,0));
        simple.setLayout(new GridLayout(5, 4));

        String buttonLabels = "789/456*123-0.=+";

        ((JButton)simple.add(new JButton("C"))).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operator = "C";
                calculate(0);
            }
        });

        ((JButton)simple.add(new JButton("+-"))).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operator = "neg";
                calculate(Float.parseFloat(display.getText()));
            }
        });

        ((JButton)simple.add(new JButton("+-"))).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operator = "<-";
                calculate(Float.parseFloat(display.getText()));
            }
        });
        simple.add(new JButton(""));

        for (int i = 0; i < buttonLabels.length(); i++) {
            int i_final = i;
            JButton btn = ((JButton)simple.add(new JButton(buttonLabels.substring(i, i + 1))));
            char ch = btn.getText().charAt(0);
            if ('0' <= ch && ch <= '9' || ch == '.') 
                btn.setBackground(Color.WHITE);

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    actionPerformed(buttonLabels.substring(i_final, i_final + 1));
                }
            });
        }

        //for (Component c : simple.getComponents())
        //    c.setFont(c.getFont().deriveFont(14f));

        p.add(simple);
        setContentPane(p);
    }

    public void actionPerformed(String cmd) {
        if (('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9') || cmd.equals(".") || cmd.equals("neg")) {
            if (calculating) display.setText(cmd);
            else display.setText(display.getText() + cmd);

            calculating = false;
        } else {
            if (calculating) {
                operator = cmd;
            } else {
                float x = Float.parseFloat(display.getText());
                calculate(x);
                operator = cmd;
                calculating = true;
            }
        }
    }

    private void calculate(float n) {
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
            case "neg": {
                this.calculating = true;
                if (Float.parseFloat(display.getText()) > 0) {
                    display.setText("-" + n);
                } else {
                    display.setText(("" + n).replace("-", ""));
                }
                result = Float.parseFloat(display.getText());
                n = result;
                break;
            }
        }
        if (String.valueOf(result).endsWith(".0"))
            display.setText("" + (int)result);
        else display.setText("" + result);
    }


    /*private static final long serialVersionUID = 1L;

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
    }*/

 }