package stdlibport.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

import stdlibport.client.javax.swing.JButton;
import stdlibport.client.javax.swing.JFrame;
import stdlibport.client.javax.swing.JPanel;
import stdlibport.client.tv.fungus.IsaiahTv;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Desktop2Web implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        JFrame frame = new JFrame("A Swing JFrame");
        JPanel panel = new JPanel();

        panel.add(new JButton("Test"));
        frame.setContentPane(panel);
        //RootPanel.get().add(frame.gwt);
        DialogBox w = (DialogBox) new IsaiahTv().gwt;
        RootPanel.get().add(w);
        

        // Create a handler for the sendButton and nameField
        //class MyHandler implements ClickHandler, KeyUpHandler
    }
}
