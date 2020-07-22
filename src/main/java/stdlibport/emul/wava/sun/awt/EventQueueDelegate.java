package wava.sun.awt;

import java.awt.AWTEvent;
import java.awt.EventQueue;

public class EventQueueDelegate {

    private static final Object EVENT_QUEUE_DELEGATE_KEY = new StringBuilder("EventQueueDelegate.Delegate");

    public static void setDelegate(Delegate delegate) {
        AppContext.getAppContext().put(EVENT_QUEUE_DELEGATE_KEY, delegate);
    }

    public static Delegate getDelegate() {
        return (Delegate) AppContext.getAppContext().get(EVENT_QUEUE_DELEGATE_KEY);
    }

    public interface Delegate {
        public AWTEvent getNextEvent(EventQueue eventQueue) throws InterruptedException;

        public Object beforeDispatch(AWTEvent event) throws InterruptedException;

        public void afterDispatch(AWTEvent event, Object handle) throws InterruptedException;
    }
}