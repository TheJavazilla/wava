package stdlibport.client.kekcroc;

import stdlibport.client.java.awt.Color;
//import stdlibport.client.java.io.IOException;

import stdlibport.client.javax.imageio.ImageIO;
import stdlibport.client.javax.swing.ImageIcon;
import stdlibport.client.javax.swing.JButton;
import stdlibport.client.javax.swing.JLabel;
import stdlibport.client.javax.swing.JPanel;
import stdlibport.client.javax.swing.Timer;

//import jaco.mp3.player.MP3Player;

public class MainMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    public MainMenu() {
        //this.setLayout(null);
        this.setOpaque(true);
        setBackground(Color.BLACK);
        JLabel titleLbl = (JLabel)add(new JLabel("KEKCROC"));
        titleLbl.setFont(titleLbl.getFont().deriveFont(48f));
        titleLbl.setForeground(Color.YELLOW);
        //titleLbl.setBounds(277,40, 245,45);

        //try {
        //    JLabel croc = (JLabel)add(new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("croc.png")))));
           // croc.setBounds(256,125, 256,312);
        //} catch (IOException e1) {
        //    e1.printStackTrace();
       // }

        JButton plyBtn = (JButton)add(new JButton("Play"));
        //plyBtn.setBounds(338, 500, 120, 25);
        plyBtn.setVisible(false);
        //plyBtn.addActionListener(l -> Kekcroc.GAME.setContent(new LevelTitleScreen()));

        Timer timer = new Timer(350, e -> {
            //new MP3Player(getClass().getClassLoader().getResource("title.mp3")).play();
            plyBtn.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start(); 
    }

}