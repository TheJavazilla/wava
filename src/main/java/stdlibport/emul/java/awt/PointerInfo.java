// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception


package java.awt;

/**
 * A class that describes the pointer position.
 * It provides the {@code GraphicsDevice} where the pointer is and
 * the {@code Point} that represents the coordinates of the pointer.
 * <p>
 * Instances of this class should be obtained via
 * {@link MouseInfo#getPointerInfo}.
 * The {@code PointerInfo} instance is not updated dynamically as the mouse
 * moves. To get the updated location, you must call
 * {@link MouseInfo#getPointerInfo} again.
 *
 * @see MouseInfo#getPointerInfo
 * @author Roman Poborchiy
 * @since 1.5
 */
public class PointerInfo {

    private final GraphicsDevice device;
    private final Point location;

    /**
     * Package-private constructor to prevent instantiation.
     */
    PointerInfo(final GraphicsDevice device, final Point location) {
        this.device = device;
        this.location = location;
    }

    /**
     * Returns the {@code GraphicsDevice} where the mouse pointer was at the
     * moment this {@code PointerInfo} was created.
     *
     * @return {@code GraphicsDevice} corresponding to the pointer
     * @since 1.5
     */
    public GraphicsDevice getDevice() {
        return device;
    }

    /**
     * Returns the {@code Point} that represents the coordinates of the pointer
     * on the screen. See {@link MouseInfo#getPointerInfo} for more information
     * about coordinate calculation for multiscreen systems.
     *
     * @return coordinates of mouse pointer
     * @see MouseInfo
     * @see MouseInfo#getPointerInfo
     * @since 1.5
     */
    public Point getLocation() {
        return location;
    }
}
