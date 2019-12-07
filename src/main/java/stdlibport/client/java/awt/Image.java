package stdlibport.client.java.awt;

import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;

import stdlibport.client.javax.swing.JComponent;

public class Image extends JComponent {

    public Image(String name) {
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

    public Image(com.google.gwt.user.client.ui.Image img) {
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

        int originalHeight = image.getOffsetHeight();
        int originalWidth = image.getOffsetWidth();
        //if (originalHeight > originalWidth) {
            image.setHeight(h + "px");
        //} else {
            image.setWidth(w + "px");
        //}

        return new Image(image);
    }

}
