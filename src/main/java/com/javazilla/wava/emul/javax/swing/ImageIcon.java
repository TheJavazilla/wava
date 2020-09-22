package javax.swing;

import java.awt.Image;

public class ImageIcon extends JComponent {

    private Image img;
    public ImageIcon(Image image) {
        this.gwt = image.gwt;
        this.img = image;
    }

    public Image getImage() {
        return img;
    }

}
