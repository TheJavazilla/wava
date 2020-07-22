package wava.sun.awt;

import java.awt.*;

public interface FwDispatcher {

    boolean isDispatchThread();
    void scheduleDispatch(Runnable r);
    SecondaryLoop createSecondaryLoop();

}