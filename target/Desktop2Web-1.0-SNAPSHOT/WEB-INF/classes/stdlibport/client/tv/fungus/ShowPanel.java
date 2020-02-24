package stdlibport.client.tv.fungus;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ScrollPanel;

import stdlibport.client.java.awt.BoxLayout;
import stdlibport.client.java.awt.Dimension;
import stdlibport.client.java.awt.event.MouseAdapter;
import stdlibport.client.java.awt.event.MouseEvent;
import stdlibport.client.javax.swing.JButton;
import stdlibport.client.javax.swing.JPanel;
import stdlibport.client.javax.swing.JScrollPane;

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

            ((Button)l.gwt).addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    if (!vid.equalsIgnoreCase("_null_"))
                        playWeb("https://www.youtube.com/embed/" + vid + "?autoplay=1");
                    else playWeb(path);
                }
            });
            l.setSize(850, 90);

            Button a = ((Button)l.gwt);
            a.setHTML(z + l.getText());
            a.getElement().getStyle().setTextAlign(TextAlign.LEFT);
            a.getElement().getStyle().setMargin(4, Unit.PX);
            a.getElement().getStyle().setBackgroundImage("https://i3.ytimg.com/vi/" + vid + "/default.jpg");
            a.getElement().getStyle().setProperty("background", "url('https://i3.ytimg.com/vi/" + vid + "/default.jpg') no-repeat");

            this.add(l);
        }
        ScrollPanel sp = (ScrollPanel) (this.gwt = new ScrollPanel(this.gwt));
        Dimension d = IsaiahTv.inst.getSize();
        sp.setSize(d.width-2 + "px", d.height-2 + "px");
    }

    public void playWeb(String path) {
        WebPlayer p = new WebPlayer(path);
        IsaiahTv.inst.setContentPane(p);
        IsaiahTv.inst.validate();
    }

}