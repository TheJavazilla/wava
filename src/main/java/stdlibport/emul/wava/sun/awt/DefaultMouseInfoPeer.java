package wava.sun.awt;

import java.awt.Point;
import java.awt.Window;
import java.awt.peer.MouseInfoPeer;

public class DefaultMouseInfoPeer implements MouseInfoPeer {

    /**
     * Package-private constructor to prevent instantiation.
     */
    DefaultMouseInfoPeer() {
    }

    public native int fillPointWithCoords(Point point);

    public native boolean isWindowUnderMouse(Window w);

}