package wava.sun.java2d;

public interface StateTrackable {

    public enum State {
        IMMUTABLE,
        STABLE,
        DYNAMIC,
        UNTRACKABLE,
    };

    public State getState();

    public StateTracker getStateTracker();

}