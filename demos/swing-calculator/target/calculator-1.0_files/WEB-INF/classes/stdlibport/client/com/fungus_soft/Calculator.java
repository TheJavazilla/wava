package stdlibport.client. com.fungus_soft;

import stdlibport.client.java.awt.Color;
import stdlibport.client.java.awt.GridLayout;
import stdlibport.client.java.awt.event.MouseAdapter;
import stdlibport.client.java.awt.event.MouseEvent;

import stdlibport.client.javax.swing.JButton;
import stdlibport.client.javax.swing.JFrame;
import stdlibport.client.javax.swing.JLabel;
import stdlibport.client.javax.swing.JPanel;

public class Calculator extends JFrame {

    public static void main(String[] args) {
        Calculator frame = new Calculator();
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setSize(frame.getWidth() + 42, frame.getHeight() + 73);
        frame.setVisible(true);
    }

    private static final long serialVersionUID = 1L;

    private JLabel display = new JLabel("0");
    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public Calculator() {
        JPanel p = new JPanel();
        //p.setLayout(new BorderLayout());

        p.add(display);

        JPanel panel = new JPanel();
        JPanel simple = new JPanel();
        simple.setLayout(new GridLayout(5, 4));

        String buttonLabels = "789/456*123-0.=+";

        ((JButton)simple.add(new JButton("C"))).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operator = "C";
                calculate(Double.parseDouble(display.getText()));
            }
        });

        JButton off = ((JButton)simple.add(new JButton("X")));
        off.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operator = "X";
                calculate(Double.parseDouble(display.getText()));
            }
        });
        off.setBackground(Color.RED);
        for (int i = 0; i < buttonLabels.length(); i++) {
            int i_final = i;
            ((JButton)simple.add(new JButton(buttonLabels.substring(i, i + 1)))).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    actionPerformed(buttonLabels.substring(i_final, i_final + 1));
                }
            });//.addActionListener(this);
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
            case "\u221A":
                result = Math.sqrt(n);
                break;
            case "C":
                result = 0;
                break;
        }
        display.setText("" + result);
    }

 }
