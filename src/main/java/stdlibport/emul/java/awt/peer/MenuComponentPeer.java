// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception
package java.awt.peer;

import java.awt.Font;
import java.awt.MenuComponent;

/**
 * The base interface for all kinds of menu components. This is used by
 * {@link MenuComponent}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface MenuComponentPeer {

    /**
     * Disposes the menu component.
     *
     * @see MenuComponent#removeNotify()
     */
    void dispose();

    /**
     * Sets the font for the menu component.
     *
     * @param f the font to use for the menu component
     *
     * @see MenuComponent#setFont(Font)
     */
    void setFont(Font f);
}
