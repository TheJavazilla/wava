package tv.fungustv;

import javax.swing.BoxLayout;

import java.io.IOException;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

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
            try {
                l.setIcon(new ImageIcon(ImageIO.read(new URL("https://i3.ytimg.com/vi/" + vid + "/default.jpg"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            this.add(l);
        }
    }

    public void playWeb(String path) {
        WebPlayer p = new WebPlayer(path);
        IsaiahTv.inst.setContentPane(p);
        IsaiahTv.inst.validate();
    }

}