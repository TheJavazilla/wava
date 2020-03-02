package tv.fungustv;

import com.codebrig.journey.JourneyBrowserView;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WebPlayer extends JPanel {

    public WebPlayer(String url) {
        JourneyBrowserView browser = new JourneyBrowserView(url);

        this.setSize(IsaiahTv.inst.getSize());
        Dimension d = IsaiahTv.inst.getSize();
        JButton back = new JButton("<--");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel p = IsaiahTv.inst.getMenu();
                IsaiahTv.inst.setContentPane(new JScrollPane(p));
                IsaiahTv.inst.validate();
            }
        });
        add(back);
        browser.setSize(d.width - 1, d.height - back.getSize().height - 36);
        add(browser);
    }

}