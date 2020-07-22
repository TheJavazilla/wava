package wava.sun.awt;

import java.awt.event.FocusEvent;
import java.awt.Component;

/**
 * This class represents FocusEvents with a known "cause" - reason why this event happened. It can
 * be mouse press, traversal, activation, and so on - all causes are described as Cause enum. The
 * event with the cause can be constructed in two ways - explicitly through constructor of
 * CausedFocusEvent class or implicitly, by calling appropriate requestFocusXXX method with "cause"
 * parameter. The default cause is UNKNOWN.
 */
@SuppressWarnings("serial")
public class CausedFocusEvent extends FocusEvent {

    public enum Cause {
        UNKNOWN,
        MOUSE_EVENT,
        TRAVERSAL,
        TRAVERSAL_UP,
        TRAVERSAL_DOWN,
        TRAVERSAL_FORWARD,
        TRAVERSAL_BACKWARD,
        MANUAL_REQUEST,
        AUTOMATIC_TRAVERSE,
        ROLLBACK,
        NATIVE_SYSTEM,
        ACTIVATION,
        CLEAR_GLOBAL_FOCUS_OWNER,
        RETARGETED
    };

    private final Cause cause;

    public Cause getCause() {
        return cause;
    }

    public String toString() {
        return "java.awt.FocusEvent[" + super.paramString() + ",cause=" + cause + "] on " + getSource();
    }

    public CausedFocusEvent(Component source, int id, boolean temporary, Component opposite, Cause cause) {
        super(source, id, temporary, opposite);
        if (cause == null)
            cause = Cause.UNKNOWN;

        this.cause = cause;
    }

    /**
     * Retargets the original focus event to the new target.  If the
     * original focus event is CausedFocusEvent, it remains such and
     * cause is copied.  Otherwise, new CausedFocusEvent is created,
     * with cause as RETARGETED.
     * @return retargeted event, or null if e is null
     */
    public static FocusEvent retarget(FocusEvent e, Component newSource) {
        if (e == null) return null;

        return new CausedFocusEvent(newSource, e.getID(), e.isTemporary(), e.getOppositeComponent(),
                                    (e instanceof CausedFocusEvent) ? ((CausedFocusEvent)e).getCause() : Cause.RETARGETED);
    }

}