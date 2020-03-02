package stdlibport.client.com.codebrig.journey;

import com.google.gwt.user.client.ui.Frame;

import stdlibport.client.javax.swing.JComponent;

public class JourneyBrowserView extends JComponent {

    public JourneyBrowserView() {
        this.gwt = new Frame();
    }

    public JourneyBrowserView(String initialUrl) {
        this();
        ((Frame)this.gwt).setUrl(initialUrl);
        ((Frame)this.gwt).getElement().setAttribute("allowfullscreen", "");
    }

}
