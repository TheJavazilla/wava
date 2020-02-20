package stdlibport.client.java.awt.event;

import stdlibport.client.java.util.EventListener;

public interface MouseMotionListener extends EventListener {

    public void mouseDragged(MouseEvent e);

    public void mouseMoved(MouseEvent e);

}