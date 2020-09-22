package javax.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer {
    
    public com.google.gwt.user.client.Timer gwt;
    private int i;
    private boolean repeats;

    public Timer(int i, ActionListener al) {
        this.i = i;
        this.repeats = true;
        gwt = new com.google.gwt.user.client.Timer() {
            @Override
            public void run() {
                al.actionPerformed(new ActionEvent());
            }            
        };

    }

    public boolean isRunning() {
        return gwt.isRunning();
    }

    public void stop() {
        gwt.cancel();
    }

    public void setInitialDelay(int i) {
        // TODO Auto-generated method stub
    }

    public void setRepeats(boolean b) {
        this.repeats = b;
    }

    public void start() {
        if (repeats)
            gwt.scheduleRepeating(i);
        else
            gwt.schedule(i);
    }

}
