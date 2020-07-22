package wava.sun.java2d;

import wava.sun.java2d.StateTrackable.State;
import static wava.sun.java2d.StateTrackable.State.*;

public final class StateTrackableDelegate implements StateTrackable {

    public final static StateTrackableDelegate UNTRACKABLE_DELEGATE = new StateTrackableDelegate(UNTRACKABLE);

    public final static StateTrackableDelegate IMMUTABLE_DELEGATE = new StateTrackableDelegate(IMMUTABLE);

    public static StateTrackableDelegate createInstance(State state) {
        switch (state) {
            case UNTRACKABLE:
                return UNTRACKABLE_DELEGATE;
            case STABLE:
                return new StateTrackableDelegate(STABLE);
            case DYNAMIC:
                return new StateTrackableDelegate(DYNAMIC);
            case IMMUTABLE:
                return IMMUTABLE_DELEGATE;
            default:
                throw new InternalError("unknown state");
        }
    }

    private State theState;
    StateTracker theTracker;
    private int numDynamicAgents;

    private StateTrackableDelegate(State state) {
        this.theState = state;
    }

    public State getState() {
        return theState;
    }

    public synchronized StateTracker getStateTracker() {
        StateTracker st = theTracker;
        if (st == null) {
            switch (theState) {
            case IMMUTABLE:
                st = StateTracker.ALWAYS_CURRENT;
                break;
            case STABLE:
                st = new StateTracker() {
                    public boolean isCurrent() {
                        return (theTracker == this);
                    }
                };
                break;
            case DYNAMIC:
                // We return the NEVER_CURRENT tracker, but that is just temporary while we are in the DYNAMIC state. NO BREAK
            case UNTRACKABLE:
                st = StateTracker.NEVER_CURRENT;
                break;
            }
            theTracker = st;
        }
        return st;
    }

    public synchronized void setImmutable() {
        if (theState == UNTRACKABLE || theState == DYNAMIC)
            throw new IllegalStateException("UNTRACKABLE or DYNAMIC objects cannot become IMMUTABLE");
        theState = IMMUTABLE;
        theTracker = null;
    }

    public synchronized void setUntrackable() {
        if (theState == IMMUTABLE)
            throw new IllegalStateException("IMMUTABLE objects cannot become UNTRACKABLE");
        theState = UNTRACKABLE;
        theTracker = null;
    }

    public synchronized void addDynamicAgent() {
        if (theState == IMMUTABLE)
            throw new IllegalStateException("Cannot change state from IMMUTABLE");

        ++numDynamicAgents;
        if (theState == STABLE) {
            theState = DYNAMIC;
            theTracker = null;
        }
    }

    protected synchronized void removeDynamicAgent() {
        if (--numDynamicAgents == 0 && theState == DYNAMIC) {
            theState = STABLE;
            theTracker = null;
        }
    }

    public final void markDirty() {
        theTracker = null;
    }

}