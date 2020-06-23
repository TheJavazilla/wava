package stdlibport.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

import javax.swing.JFrame;
import stdlibport.client.IsaiahTv;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Desktop2Web implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        JFrame frame = new IsaiahTv();

        DialogBox w = (DialogBox) frame.gwt;
        RootPanel.get().add(w);
    }

}