package wava.sun.awt;

import java.awt.event.WindowEvent;

public interface WindowClosingListener {

    RuntimeException windowClosingNotify(WindowEvent event);
    RuntimeException windowClosingDelivered(WindowEvent event);

}