package tv.fungus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import tv.fungus.shows.*;

public class IsaiahTv extends JFrame {

    public static IsaiahTv inst;
    public static String path = "F:\\";
    private Timer t;

    public boolean DEBUG = false;
    private JPanel p;

    public IsaiahTv() {
        inst = this;
        JPanel menu = getMenu();
        JPanel p = new JPanel();

        p.setPreferredSize(new Dimension(1024, 600));
        p.setBackground(Color.WHITE);

        JLabel name = (JLabel)p.add(new JLabel("FungusTV"));
        JLabel des = (JLabel)p.add(new JLabel("Free Streaming Made Possible"));

        name.setFont(name.getFont().deriveFont(114f));
        des.setFont(des.getFont().deriveFont(32f));

        des.setVisible(false);

        setContentPane(p);

        setDefaultCloseOperation(3);
        this.setSize(1054, 640);
        this.setLocationRelativeTo(null);
        setVisible(true);
        
        Timer locFix = new Timer(10, a -> name.setLocation((getWidth()/2)-name.getSize().width/2-4, (getHeight()/2)-(name.getSize().height+20)));
        locFix.start();

        t = new Timer(10, a -> {
            if (locFix.isRunning())
                locFix.stop();

            name.setLocation((getWidth()/2)-(250), (getHeight()/2)-(114+20));

            des.setForeground(Color.BLACK);
            des.setLocation(getWidth()/2 - 215, getHeight()/2);
            des.setVisible(true);
        });
        t.setInitialDelay(500);
        t.setRepeats(true);
        t.start();

        Timer timer = new Timer(4000, a -> {
            setContentPane(new JScrollPane(menu));
            validate();
        });
        timer.setRepeats(false);
        timer.start();

        setTitle("FungusTV - Free Streaming Made Possible");
    }

    public Image image(String s, boolean cl) {
        return ImageIO.read(new URL(s));
    }

    public JPanel getMenu() {
        p = new JPanel();
        p.setLayout(new GridLayout(0,5));
        //TitledBorder t = BorderFactory.createTitledBorder("All Shows");
        //t.setTitleFont(t.getTitleFont().deriveFont(14f));
        //p.setBorder(BorderFactory.createCompoundBorder(t, BorderFactory.createEmptyBorder(12,8,32,8)));

        p.add(getShow("New Looney Tunes", new LooneyTunes()));
        p.add(getShow("Tom & Jerry", new TomAndJerry()));
        //p.add(getShow("The Adventures of Teddy Ruxpin"));
        p.add(getShow("AOK", new AOK()));
        p.add(getShow("Bikini Bottom Mysteries", new BikiniBottomMysteries()));
        //p.add(getShow("Road Runner", boomerang));
        p.add(getShow("Woody Woodpecker", new WoodyWoodpecker()));
        p.add(getShow("Arthur", new Arthur()));
        p.add(getShow("Spongebob", new Spongebob()));
        p.add(getShow("Om Nom Stories", new OmNom()));
        p.add(getShow("Pink Panther", new PinkPanther()));
        //p.add(getShow("Sylvester & Tweety"));
        p.add(getShow("Studio C", new StudioC()));
        p.add(getShow("Adam Ruins Everything", new AdamRuinsEverything()));
        p.add(getShow("Infowars", new Infowars()));
        p.add(getShow("Two Cents", new TwoCents()));
        p.add(getShow("Oggy", new Oggy()));
        p.add(getShow("Forensic Files", new ForensicFiles()));
        p.add(getShow("Unsolved Mysteries", new UnsolvedMysteries()));
        p.add(getShow("Heman", new Heman()));
        p.add(getShow("Batman", new Batman()));
        //p.add(getShow("Achmed Saves America"));
        p.add(getShow("LGR Thrifts", new LGRThrifts()));
        p.add(getShow("Techquickie", new Techquickie()));
        p.add(getShow("Cheddar Explains", new CheddarExplains()));
        //p.add(getShow("Toy Story"));
        repaint();
        return p;
    }

    public JLabel getShow(String showName, Show s) {
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon( ImageIO.read(new URL("videos/"  + showName + "/logo.png")).getScaledInstance(192, 112, 0) ));
        l.setBorder(BorderFactory.createEmptyBorder(5, 5, 8, 5));
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel p = new ShowPanel(path + "\\videos\\" + showName, s);
                setContentPane(new JScrollPane(p));
                validate();
            }
        });
        l.setSize(192, 112);
        l.setMaximumSize(new Dimension(200,130));
        return l;
    }

    public static void main(String[] args) {
        if (args.length > 0)
            path = args[0];
        new IsaiahTv();
    }

}