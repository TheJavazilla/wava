package java.awt.event;

import java.awt.AWTEvent;
import java.awt.Event;
import java.lang.annotation.Native;

public class ActionEvent extends AWTEvent {

    public static final int SHIFT_MASK   = Event.SHIFT_MASK;
    public static final int CTRL_MASK    = Event.CTRL_MASK;
    public static final int META_MASK    = Event.META_MASK;
    public static final int ALT_MASK     = Event.ALT_MASK;
    public static final int ACTION_FIRST = 1001;
    public static final int ACTION_LAST  = 1001;

    /**
     * This event id indicates that a meaningful action occurred.
     */
    public static final int ACTION_PERFORMED    = ACTION_FIRST; //Event.ACTION_EVENT

    /**
     * The nonlocalized string that gives more details
     * of what actually caused the event.
     * This information is very specific to the component
     * that fired it.

     * @serial
     * @see #getActionCommand
     */
    String actionCommand;

    /**
     * Timestamp of when this event occurred. Because an ActionEvent is a high-
     * level, semantic event, the timestamp is typically the same as an
     * underlying InputEvent.
     *
     * @serial
     * @see #getWhen
     */
    long when;

    /**
     * This represents the key modifier that was selected,
     * and is used to determine the state of the selected key.
     * If no modifier has been selected it will default to
     * zero.
     *
     * @serial
     * @see #getModifiers
     */
    int modifiers;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = -7671078796273832149L;

    /**
     * Constructs an <code>ActionEvent</code> object.
     * <p>
     * This method throws an
     * <code>IllegalArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     * A <code>null</code> <code>command</code> string is legal,
     * but not recommended.
     *
     * @param source  The object that originated the event
     * @param id      An integer that identifies the event.
     *                     For information on allowable values, see
     *                     the class description for {@link ActionEvent}
     * @param command A string that may specify a command (possibly one
     *                of several) associated with the event
     * @throws IllegalArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getActionCommand()
     */
    public ActionEvent(Object source, int id, String command) {
        this(source, id, command, 0);
    }

    /**
     * Constructs an <code>ActionEvent</code> object with modifier keys.
     * <p>
     * This method throws an
     * <code>IllegalArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     * A <code>null</code> <code>command</code> string is legal,
     * but not recommended.
     *
     * @param source  The object that originated the event
     * @param id      An integer that identifies the event.
     *                     For information on allowable values, see
     *                     the class description for {@link ActionEvent}
     * @param command A string that may specify a command (possibly one
     *                of several) associated with the event
     * @param modifiers The modifier keys down during event
     *                  (shift, ctrl, alt, meta).
     *                  Passing negative parameter is not recommended.
     *                  Zero value means that no modifiers were passed
     * @throws IllegalArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getActionCommand()
     * @see #getModifiers()
     */
    public ActionEvent(Object source, int id, String command, int modifiers) {
        this(source, id, command, 0, modifiers);
    }

    /**
     * Constructs an <code>ActionEvent</code> object with the specified
     * modifier keys and timestamp.
     * <p>
     * This method throws an
     * <code>IllegalArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     * A <code>null</code> <code>command</code> string is legal,
     * but not recommended.
     *
     * @param source    The object that originated the event
     * @param id      An integer that identifies the event.
     *                     For information on allowable values, see
     *                     the class description for {@link ActionEvent}
     * @param command A string that may specify a command (possibly one
     *                of several) associated with the event
     * @param modifiers The modifier keys down during event
     *                  (shift, ctrl, alt, meta).
     *                  Passing negative parameter is not recommended.
     *                  Zero value means that no modifiers were passed
     * @param when   A long that gives the time the event occurred.
     *               Passing negative or zero value
     *               is not recommended
     * @throws IllegalArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getActionCommand()
     * @see #getModifiers()
     * @see #getWhen()
     *
     * @since 1.4
     */
    public ActionEvent(Object source, int id, String command, long when,
                       int modifiers) {
        super(source, id);
        this.actionCommand = command;
        this.when = when;
        this.modifiers = modifiers;
    }

    /**
     * Returns the command string associated with this action.
     * This string allows a "modal" component to specify one of several
     * commands, depending on its state. For example, a single button might
     * toggle between "show details" and "hide details". The source object
     * and the event would be the same in each case, but the command string
     * would identify the intended action.
     * <p>
     * Note that if a <code>null</code> command string was passed
     * to the constructor for this <code>ActionEvent</code>, this
     * this method returns <code>null</code>.
     *
     * @return the string identifying the command for this event
     */
    public String getActionCommand() {
        return actionCommand;
    }

    /**
     * Returns the timestamp of when this event occurred. Because an
     * ActionEvent is a high-level, semantic event, the timestamp is typically
     * the same as an underlying InputEvent.
     *
     * @return this event's timestamp
     * @since 1.4
     */
    public long getWhen() {
        return when;
    }

    /**
     * Returns the modifier keys held down during this action event.
     *
     * @return the bitwise-or of the modifier constants
     */
    public int getModifiers() {
        return modifiers;
    }

    /**
     * Returns a parameter string identifying this action event.
     * This method is useful for event-logging and for debugging.
     *
     * @return a string identifying the event and its associated command
     */
    public String paramString() {
        String typeStr;
        switch(id) {
          case ACTION_PERFORMED:
              typeStr = "ACTION_PERFORMED";
              break;
          default:
              typeStr = "unknown type";
        }
        return typeStr + ",cmd="+actionCommand+",when="+when+",modifiers="+ KeyEvent.getKeyModifiersText(modifiers);
    }

}