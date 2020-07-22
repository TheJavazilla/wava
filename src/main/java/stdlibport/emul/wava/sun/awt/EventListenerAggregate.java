package wava.sun.awt;

import java.lang.reflect.Array;
import java.util.EventListener;

public class EventListenerAggregate {

    private EventListener[] listenerList;

    public EventListenerAggregate(Class<? extends EventListener> listenerClass) {
        if (listenerClass == null) {
            throw new NullPointerException("listener class is null");
        }

        listenerList = (EventListener[])Array.newInstance(listenerClass, 0);
    }

    private Class<?> getListenerClass() {
        return listenerList.getClass().getComponentType();
    }

    public synchronized void add(EventListener listener) {
        Class<?> listenerClass = getListenerClass();

        if (!listenerClass.isInstance(listener))
            throw new ClassCastException("listener " + listener + " is not an instance of listener class " + listenerClass);

        EventListener[] tmp = (EventListener[])Array.newInstance(listenerClass, listenerList.length + 1);
        System.arraycopy(listenerList, 0, tmp, 0, listenerList.length);
        tmp[listenerList.length] = listener;
        listenerList = tmp;
    }

    public synchronized boolean remove(EventListener listener) {
        Class<?> listenerClass = getListenerClass();

        if (!listenerClass.isInstance(listener))
            throw new ClassCastException("listener " + listener + " is not an instance of listener class " + listenerClass);

        for (int i = 0; i < listenerList.length; i++) {
            if (listenerList[i].equals(listener)) {
                EventListener[] tmp = (EventListener[])Array.newInstance(listenerClass, listenerList.length - 1);
                System.arraycopy(listenerList, 0, tmp, 0, i);
                System.arraycopy(listenerList, i + 1, tmp, i, listenerList.length - i - 1);
                listenerList = tmp;

                return true;
            }
        }

        return false;
    }

    public synchronized EventListener[] getListenersInternal() {
        return listenerList;
    }

    public synchronized EventListener[] getListenersCopy() {
        return (listenerList.length == 0) ? listenerList : listenerList.clone();
    }

    public synchronized int size() {
        return listenerList.length;
    }

    public synchronized boolean isEmpty() {
        return listenerList.length == 0;
    }

}