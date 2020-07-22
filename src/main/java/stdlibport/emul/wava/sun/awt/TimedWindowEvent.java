package wava.sun.awt;

import java.awt.event.WindowEvent;
import java.awt.Window;

public class TimedWindowEvent extends WindowEvent {

    private long time;

    public long getWhen() {
        return time;
    }

    public TimedWindowEvent(Window source, int id, Window opposite, long time) {
        super(source, id, opposite);
        this.time = time;
    }

    public TimedWindowEvent(Window source, int id, Window opposite, int oldState, int newState, long time) {
        super(source, id, opposite, oldState, newState);
        this.time = time;
    }

}