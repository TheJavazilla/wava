package stdlibport.client.tv.fungus;

import com.google.gwt.user.client.ui.Frame;

import stdlibport.client.java.awt.Dimension;
import stdlibport.client.java.awt.event.MouseAdapter;
import stdlibport.client.java.awt.event.MouseEvent;
import stdlibport.client.javax.swing.JButton;
import stdlibport.client.javax.swing.JPanel;
import stdlibport.client.javax.swing.JScrollPane;

public class WebPlayer extends JPanel {

    public WebPlayer(String url) {
        JPanel p = new JPanel();

        p.gwt = new Frame();
        ((Frame)p.gwt).setUrl(url);
        //((Frame)p.gwt).getElement().setAttribute("allowfullscreen", "");

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
        p.setSize(d.width - 1, d.height - back.getSize().height - 36);
        add(p);
    }

}