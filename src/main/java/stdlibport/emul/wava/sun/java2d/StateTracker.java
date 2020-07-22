package wava.sun.java2d;

public interface StateTracker {

    public StateTracker ALWAYS_CURRENT = new StateTracker(){public boolean isCurrent(){return true;}};
    public StateTracker NEVER_CURRENT =  new StateTracker(){public boolean isCurrent(){return false;}};

    public boolean isCurrent();

}