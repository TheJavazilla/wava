package stdlibport.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

import stdlibport.client.javax.swing.JFrame;
import stdlibport.client.tv.fungus.IsaiahTv;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Desktop2Web implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        JFrame frame = new stdlibport.client.com.fungus_soft.Calculator();

        DialogBox w = (DialogBox) frame.gwt;
        RootPanel.get().add(w);
    }

}
