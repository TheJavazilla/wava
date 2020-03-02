package stdlibport.client.tv.fungus;

import com.google.gwt.user.client.ui.ScrollPanel;

import stdlibport.client.java.awt.BoxLayout;
import stdlibport.client.java.awt.Dimension;
import stdlibport.client.java.awt.Insets;
import stdlibport.client.java.awt.event.MouseAdapter;
import stdlibport.client.java.awt.event.MouseEvent;
import stdlibport.client.java.net.URL;
import stdlibport.client.javax.imageio.ImageIO;
import stdlibport.client.javax.swing.ImageIcon;
import stdlibport.client.javax.swing.JButton;
import stdlibport.client.javax.swing.JPanel;
import stdlibport.client.javax.swing.JScrollPane;
import stdlibport.client.javax.swing.SwingConstants;

public class ShowPanel extends JPanel {

    public ShowPanel(String s, Show show) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton b = new JButton("<-- Back");
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel p = IsaiahTv.inst.getMenu();
                IsaiahTv.inst.setContentPane(new JScrollPane(p));
                IsaiahTv.inst.validate();
            }
        });

        this.add(b);

        for (String fz : show.getEpisodes()) {
            JButton l = new JButton(fz.split("=")[1].trim());
            String z = "&nbsp;";
            for (int i = 0; i < 32; i++)
                z += "&nbsp;";

            String path = fz.split("=")[0].trim();
            String vid = path.contains("youtu") ?
                             path.contains("embed") ? path.substring(path.indexOf("embed") + "embed".length() + 1) : path.substring(path.indexOf("?v=") + "?v=".length())
                                     : "_null_";

            l.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!vid.equalsIgnoreCase("_null_"))
                        playWeb("https://www.youtube.com/embed/" + vid + "?autoplay=1");
                    else playWeb(path);
                }
            });
            l.setSize(850, 90);

            l.setText(z + l.getText());
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.setMargin(new Insets(4, 4, 4, 4));
            l.setIcon(new ImageIcon(ImageIO.read(new URL("https://i3.ytimg.com/vi/" + vid + "/default.jpg"))));

            this.add(l);
        }
        //ScrollPanel sp = (ScrollPanel) (this.gwt = new ScrollPanel(this.gwt));
        //Dimension d = IsaiahTv.inst.getSize();
        //sp.setSize(d.width-2 + "px", d.height-2 + "px");
    }

    public void playWeb(String path) {
        WebPlayer p = new WebPlayer(path);
        IsaiahTv.inst.setContentPane(p);
        IsaiahTv.inst.validate();
    }

}