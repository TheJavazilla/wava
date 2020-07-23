package java.awt.event;

import java.awt.Event;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.Arrays;

import wava.sun.awt.AWTAccessor;
//import wava.sun.security.util.SecurityConstants;

/**
 * The root event class for all component-level input events.
 *
 * Input events are delivered to listeners before they are
 * processed normally by the source where they originated.
 * This allows listeners and component subclasses to "consume"
 * the event so that the source will not process them in their
 * default manner.  For example, consuming mousePressed events
 * on a Button component will prevent the Button from being
 * activated.
 *
 * @author Carl Quinn
 *
 * @see KeyEvent
 * @see KeyAdapter
 * @see MouseEvent
 * @see MouseAdapter
 * @see MouseMotionAdapter
 */
public abstract class InputEvent extends ComponentEvent {

    public static final int SHIFT_MASK = Event.SHIFT_MASK;
    public static final int CTRL_MASK = Event.CTRL_MASK;
    public static final int META_MASK = Event.META_MASK;
    public static final int ALT_MASK = Event.ALT_MASK;

    /**
     * The AltGraph key modifier constant.
     */
    public static final int ALT_GRAPH_MASK = 1 << 5;

    /**
     * The Mouse Button1 modifier constant.
     * It is recommended that BUTTON1_DOWN_MASK be used instead.
     */
    public static final int BUTTON1_MASK = 1 << 4;

    /**
     * The Mouse Button2 modifier constant.
     * It is recommended that BUTTON2_DOWN_MASK be used instead.
     * Note that BUTTON2_MASK has the same value as ALT_MASK.
     */
    public static final int BUTTON2_MASK = Event.ALT_MASK;

    /**
     * The Mouse Button3 modifier constant.
     * It is recommended that BUTTON3_DOWN_MASK be used instead.
     * Note that BUTTON3_MASK has the same value as META_MASK.
     */
    public static final int BUTTON3_MASK = Event.META_MASK;

    /** The Shift key extended modifier constant. */
    public static final int SHIFT_DOWN_MASK = 1 << 6;

    /** The Control key extended modifier constant. */
    public static final int CTRL_DOWN_MASK = 1 << 7;

    /** The Meta key extended modifier constant. */
    public static final int META_DOWN_MASK = 1 << 8;

    /** The Alt key extended modifier constant. */
    public static final int ALT_DOWN_MASK = 1 << 9;

    /** The Mouse Buttons extended modifier constant. */
    public static final int BUTTON1_DOWN_MASK = 1 << 10;
    public static final int BUTTON2_DOWN_MASK = 1 << 11;
    public static final int BUTTON3_DOWN_MASK = 1 << 12;

    /**
     * The AltGraph key extended modifier constant.
     */
    public static final int ALT_GRAPH_DOWN_MASK = 1 << 13;

    /**
     * An array of extended modifiers for additional buttons.
     * @see getButtonDownMasks
     * There are twenty buttons fit into 4byte space. one more bit is reserved for FIRST_HIGH_BIT.
     */
    private static final int [] BUTTON_DOWN_MASK = new int [] { BUTTON1_DOWN_MASK, BUTTON2_DOWN_MASK, BUTTON3_DOWN_MASK, 1<<14, /*4th phisical button (this is not a wheel!)*/ 1<<15, /*(this is not a wheel!)*/
                                                               1<<16, 1<<17, 1<<18, 1<<19, 1<<20, 1<<21, 1<<22, 1<<23, 1<<24, 1<<25, 1<<26, 1<<27, 1<<28, 1<<29, 1<<30};

    /**
     * A method to access an array of extended modifiers for additional buttons.
     */
    private static int [] getButtonDownMasks(){
        return Arrays.copyOf(BUTTON_DOWN_MASK, BUTTON_DOWN_MASK.length);
    }

    public static int getMaskForButton(int button) {
        if (button <= 0 || button > BUTTON_DOWN_MASK.length)
            throw new IllegalArgumentException("button doesn\'t exist " + button);
        return BUTTON_DOWN_MASK[button - 1];
    }

    // the constant below MUST be updated if any extra modifier bits are to be added! in fact,
    // it is undesirable to add modifier bits to the same field as this may break applications see bug# 5066958
    static final int FIRST_HIGH_BIT = 1 << 31;

    static final int JDK_1_3_MODIFIERS = SHIFT_DOWN_MASK - 1;
    static final int HIGH_MODIFIERS = ~( FIRST_HIGH_BIT - 1 );

    /**
     * The input event's Time stamp in UTC format.  The time stamp
     * indicates when the input event was created.
     *
     * @see #getWhen()
     */
    long when;

    /**
     * The state of the modifier mask at the time the input
     * event was fired.
     * @see #getModifiers()
     * @see #getModifiersEx()
     * @see java.awt.event.KeyEvent
     * @see java.awt.event.MouseEvent
     */
    int modifiers;

    /*
     * A flag that indicates that this instance can be used to access
     * the system clipboard.
     */
    private transient boolean canAccessSystemClipboard;

    static {
        /* ensure that the necessary native libraries are loaded */
        NativeLibLoader.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) initIDs();

        AWTAccessor.setInputEventAccessor(new AWTAccessor.InputEventAccessor() {public int[] getButtonDownMasks() {return InputEvent.getButtonDownMasks();}});
    }

    /** Initialize JNI field and method IDs for fields that may be accessed from C. */
    private static native void initIDs();

    InputEvent(Component source, int id, long when, int modifiers) {
        super(source, id);
        this.when = when;
        this.modifiers = modifiers;
        canAccessSystemClipboard = canAccessSystemClipboard();
    }

    private boolean canAccessSystemClipboard() {
        boolean b = false;
        if (!GraphicsEnvironment.isHeadless()) b = true;

        return b;
    }

    public boolean isShiftDown() {
        return (modifiers & SHIFT_MASK) != 0;
    }

    public boolean isControlDown() {
        return (modifiers & CTRL_MASK) != 0;
    }

    public boolean isMetaDown() {
        return (modifiers & META_MASK) != 0;
    }

    public boolean isAltDown() {
        return (modifiers & ALT_MASK) != 0;
    }

    public boolean isAltGraphDown() {
        return (modifiers & ALT_GRAPH_MASK) != 0;
    }

    public long getWhen() {
        return when;
    }

    public int getModifiers() {
        return modifiers & (JDK_1_3_MODIFIERS | HIGH_MODIFIERS);
    }

    public int getModifiersEx() {
        return modifiers & ~JDK_1_3_MODIFIERS;
    }

    /**
     * Consumes this event so that it will not be processed in the default manner by the source which originated it.
     */
    public void consume() {
        consumed = true;
    }

    /**
     * Returns whether or not this event has been consumed.
     * @see #consume
     */
    public boolean isConsumed() {
        return consumed;
    }

    static final long serialVersionUID = -2482525981698309786L;

    /**
     * Returns a String describing the extended modifier keys and
     * mouse buttons, such as "Shift", "Button1", or "Ctrl+Shift".
     * These strings can be localized by changing the
     * <code>awt.properties</code> file.
     * <p>
     * Note that passing negative parameter is incorrect,
     * and will cause the returning an unspecified string.
     * Zero parameter means that no modifiers were passed and will
     * cause the returning an empty string.
     *
     * @param modifiers a modifier mask describing the extended modifier keys and mouse buttons for the event
     * @return a text description of the combination of extended
     *         modifier keys and mouse buttons that were held down
     *         during the event.
     */
    public static String getModifiersExText(int modifiers) {
        StringBuilder buf = new StringBuilder();
        if ((modifiers & InputEvent.META_DOWN_MASK) != 0) {
            buf.append(Toolkit.getProperty("AWT.meta", "Meta"));
            buf.append("+");
        }
        if ((modifiers & InputEvent.CTRL_DOWN_MASK) != 0) {
            buf.append(Toolkit.getProperty("AWT.control", "Ctrl"));
            buf.append("+");
        }
        if ((modifiers & InputEvent.ALT_DOWN_MASK) != 0) {
            buf.append(Toolkit.getProperty("AWT.alt", "Alt"));
            buf.append("+");
        }
        if ((modifiers & InputEvent.SHIFT_DOWN_MASK) != 0) {
            buf.append(Toolkit.getProperty("AWT.shift", "Shift"));
            buf.append("+");
        }
        if ((modifiers & InputEvent.ALT_GRAPH_DOWN_MASK) != 0) {
            buf.append(Toolkit.getProperty("AWT.altGraph", "Alt Graph"));
            buf.append("+");
        }

        int buttonNumber = 1;
        for (int mask : InputEvent.BUTTON_DOWN_MASK){
            if ((modifiers & mask) != 0) {
                buf.append(Toolkit.getProperty("AWT.button"+buttonNumber, "Button"+buttonNumber));
                buf.append("+");
            }
            buttonNumber++;
        }
        if (buf.length() > 0)
            buf.setLength(buf.length()-1); // remove trailing '+'
        return buf.toString();
    }

}