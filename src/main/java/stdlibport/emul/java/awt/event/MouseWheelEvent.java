package java.awt.event;

import java.awt.Component;

import java.lang.annotation.Native;

public class MouseWheelEvent extends MouseEvent {

    /**
     * Constant representing scrolling by "units" (like scrolling with the arrow keys)
     *
     * @see #getScrollType
     */
    @Native public static final int WHEEL_UNIT_SCROLL = 0;

    /**
     * Constant representing scrolling by a "block" (like scrolling with page-up, page-down keys)
     *
     * @see #getScrollType
     */
    @Native public static final int WHEEL_BLOCK_SCROLL = 1;

    /**
     * Indicates what sort of scrolling should take place in response to this
     * event, based on platform settings.  Legal values are:
     * <ul>
     * <li> WHEEL_UNIT_SCROLL
     * <li> WHEEL_BLOCK_SCROLL
     * </ul>
     *
     * @see #getScrollType
     */
    int scrollType;

    /**
     * Only valid for scrollType WHEEL_UNIT_SCROLL.
     * Indicates number of units that should be scrolled per
     * click of mouse wheel rotation, based on platform settings.
     *
     * @see #getScrollAmount
     * @see #getScrollType
     */
    int scrollAmount;

    /**
     * Indicates how far the mouse wheel was rotated.
     *
     * @see #getWheelRotation
     */
    int wheelRotation;

    /**
     * Indicates how far the mouse wheel was rotated.
     *
     * @see #getPreciseWheelRotation
     */
    double preciseWheelRotation;

    private static final long serialVersionUID = 6459879390515399677L;

    public MouseWheelEvent (Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger, int scrollType, int scrollAmount, int wheelRotation) {
        this(source, id, when, modifiers, x, y, 0, 0, clickCount, popupTrigger, scrollType, scrollAmount, wheelRotation);
    }

    public MouseWheelEvent(Component source, int id, long when, int modifiers, int x, int y, int xAbs, int yAbs, int clickCount, boolean popupTrigger, int scrollType, int scrollAmount, int wheelRotation) {
        this(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, scrollType, scrollAmount, wheelRotation, wheelRotation);
    }

    public MouseWheelEvent(Component source, int id, long when, int modifiers, int x, int y, int xAbs, int yAbs, int clickCount, boolean popupTrigger, int scrollType, int scrollAmount, int wheelRotation, double preciseWheelRotation) {
        super(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, MouseEvent.NOBUTTON);

        this.scrollType = scrollType;
        this.scrollAmount = scrollAmount;
        this.wheelRotation = wheelRotation;
        this.preciseWheelRotation = preciseWheelRotation;
    }

    public int getScrollType() {
        return scrollType;
    }

    public int getScrollAmount() {
        return scrollAmount;
    }

    public int getWheelRotation() {
        return wheelRotation;
    }

    public double getPreciseWheelRotation() {
        return preciseWheelRotation;
    }

    public int getUnitsToScroll() {
        return scrollAmount * wheelRotation;
    }

    /**
     * Returns a parameter string identifying this event. This method is useful for event-logging and for debugging.
     * @return a string identifying the event and its attributes
     */
    public String paramString() {
        String scrollTypeStr = null;

        if (getScrollType() == WHEEL_UNIT_SCROLL) scrollTypeStr = "WHEEL_UNIT_SCROLL";
        else if (getScrollType() == WHEEL_BLOCK_SCROLL) scrollTypeStr = "WHEEL_BLOCK_SCROLL";
        else scrollTypeStr = "unknown scroll type";

        return super.paramString()+",scrollType="+scrollTypeStr+ ",scrollAmount="+getScrollAmount()+",wheelRotation="+getWheelRotation()+",preciseWheelRotation="+getPreciseWheelRotation();
    }

}