package java.awt;

import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;

import java.net.URL;
import javax.swing.JComponent;

public class Image extends JComponent {

    public Image(URL name) {
        com.google.gwt.user.client.ui.Image img = new com.google.gwt.user.client.ui.Image();
        this.gwt = img;
        img.setUrl(name);
        img.addErrorHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent e){
                // Failed to load image
            }
        });
    }

    private Image(com.google.gwt.user.client.ui.Image img) {
        this.gwt = img;
        img.addErrorHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent e){
                // Failed to load image
            }
        });
    }

    public Image getScaledInstance(int w, int h, int z) {
        com.google.gwt.user.client.ui.Image image = (com.google.gwt.user.client.ui.Image)gwt;

        image.setHeight(h + "px");
        image.setWidth(w + "px");

        return new Image(image);
    }

}
