package stdlibport.client.tv.fungus;

import stdlibport.client.java.awt.Color;
import stdlibport.client.java.awt.Dimension;
import stdlibport.client.java.awt.Font;
import stdlibport.client.java.awt.Graphics;
import stdlibport.client.java.awt.GridLayout;
import stdlibport.client.java.awt.Image;
import stdlibport.client.java.awt.event.MouseAdapter;
import stdlibport.client.java.awt.event.MouseEvent;
import stdlibport.client.java.awt.image.BufferedImage;
import java.util.HashMap;

import com.google.gwt.dom.client.Style.Unit;

import stdlibport.client.javax.imageio.ImageIO;
import stdlibport.client.javax.swing.ImageIcon;
import stdlibport.client.javax.swing.JFrame;
import stdlibport.client.javax.swing.JLabel;
import stdlibport.client.javax.swing.JPanel;
import stdlibport.client.javax.swing.JScrollPane;
import stdlibport.client.javax.swing.Timer;
import stdlibport.client.tv.fungus.ShowPanel.ImageType;

import static stdlibport.client.tv.fungus.ShowPanel.ImageType.*;

public class IsaiahTv extends JFrame {

    public static IsaiahTv inst;
    public static String path = "F:\\";
    public int h=0,j=0;
    public static HashMap<String, BufferedImage> map = new HashMap<>();

    public boolean DEBUG = false;
    public static boolean loading = false;
    Color c = new Color(255, 255, 255);

    public IsaiahTv() {
        inst = this;
        JPanel menu = getMenu();
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(1024, 600));
        p.setBackground(Color.WHITE);

        JLabel name = (JLabel)p.add(new JLabel(new ImageIcon(image("title.png", true))));
        JLabel des = (JLabel)p.add(new JLabel("Portable Streaming on USB. Now on the web!"));
        des.setFont(des.getFont().deriveFont(24f));

        name.setVisible(false);
        des.setVisible(false);

        setContentPane(p);

        setDefaultCloseOperation(3);
        this.setSize(1024, 600);
        this.setLocationRelativeTo(null);
        setVisible(true);

        Timer t = new Timer(10, a -> {
            name.setVisible(true);
            name.setLocation((getWidth()/2)-350, getHeight()-(10+h));
            if (name.getLocation().y > (getHeight()/2)-(name.getSize().height+20)) {
                if (h>250)
                    h += 8;
                else if (h>150)
                    h += 14;
                else h+= 18;
            }
            if(j > 100) {
                des.setForeground(c);
                Color d = c;
                if (c.getRed() > 1) {
                    double s = Math.random();
                    int u = s < 0.7 ? 2 : 1;
                    d = c = new Color(c.getRed() - u, c.getGreen() - u, c.getBlue() - u);
                }
                des.setForeground(d);
                des.validate();
                des.setLocation((getWidth()/2)-200, (getHeight() - h) + 150);
                des.setVisible(true);
            }
            j++;
        });
        t.setInitialDelay(400);
        t.setRepeats(true);
        t.start();

        /*try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path + "\\videos\\output_free.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {}*/

        Timer timer = new Timer(DEBUG ? 200 : 8000, a -> {
            setContentPane(new JScrollPane(menu));
            validate();
        });

        timer.setRepeats(false);
        timer.start();

        setTitle("FungusTV by Isaiah");
    }

    public Image image(String s, boolean cl) {
        //try {
            //return cl ? ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(s)) : ImageIO.read(new File(s));
        //} catch (IOException e) {
        //    e.printStackTrace();
            //return null;
        //}
        return ImageIO.gwtDebug(s);
    }

    public JPanel getMenu() {
        JPanel p = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (loading) {
                    Font f = g.getFont();
                    g.setFont(f.deriveFont(68f));
                    g.setColor(new Color(10, 10, 10, 150));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.LIGHT_GRAY);

                    g.drawString("Loading...", getWidth()/2 - 130, getHeight()/2 - 32);
                    g.setFont(f);
                }
                g.drawString("(C) 2019 by Isaiah. Released under the Unlicence", getWidth() - 280, getHeight() - 12);
                repaint();
            }
        };
        GridLayout grid = new GridLayout(0,4);
        grid.gwt().getElement().getStyle().setPadding(6, Unit.PX);
        grid.gwt().getElement().getStyle().setPaddingRight(24, Unit.PX);
        p.setLayout(grid);
        
        //TitledBorder t = BorderFactory.createTitledBorder("Your viewing All Shows on FungusTV");
        //t.setTitleFont(t.getTitleFont().deriveFont(16f));
        //p.setBorder(BorderFactory.createCompoundBorder(t, BorderFactory.createEmptyBorder(8,10,10,10)));

        p.add(getShow("Scooby doo - Whats New Scooby Doo", dailymotion));
        p.add(getShow("Loney tunes", PublicDomain));
        p.add(getShow("New Looney Tunes", boomerang));
        p.add(getShow("The Sylvester & Tweety Mysteries", dailymotion));
        p.add(getShow("Buzz lightyear of Star Command"));
        p.add(getShow("Animanics", dailymotion));
        p.add(getShow("Tom & Jerry", boomerang));
        p.add(getShow("The Adventures of Teddy Ruxpin"));
        p.add(getShow("Ducktails", DisneyYT));
        p.add(getShow("AOK"));
        p.add(getShow("underdog"));
        p.add(getShow("Bikini Bottom Mysteries"));
        p.add(getShow("Road Runner", boomerang));
        p.add(getShow("Woody Woodpecker"));
        p.add(getShow("Arthur"));
        p.add(getShow("Spongebob"));
        p.add(getShow("Om Nom Stories"));
        return p;
    }

    public JLabel getShow(String showName) {
        return getShow(showName, none);
    }

    public JLabel getShow(String showName, ImageType type) {
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(ImageIO.gwtDebug("videos/" + showName + "/logo.png").getScaledInstance(192, 112, 0)));
        l.gwt.getElement().getStyle().setPaddingRight(24, Unit.PX);
        //l.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loading = true;
                    JPanel p = new ShowPanel(path + "\\videos\\" + showName, type);
                    setContentPane(new JScrollPane(p));
                    validate();
            }
        });
        return l;
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Loading...", getWidth()/2, getHeight()/2);
        super.paint(g);
        g.drawString("Loading...", getWidth()/2, getHeight()/2);
    }

    public static void main(String[] args) {
        if (args.length > 0)
            path = args[0];
        new IsaiahTv();
    }

}