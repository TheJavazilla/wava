// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception
package java.awt.peer;

import java.awt.Dimension;
import java.awt.TextField;

/**
 * The peer interface for {@link TextField}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface TextFieldPeer extends TextComponentPeer {

    /**
     * Sets the echo character.
     *
     * @param echoChar the echo character to set
     *
     * @see TextField#getEchoChar()
     */
    void setEchoChar(char echoChar);

    /**
     * Returns the preferred size of the text field with the specified number
     * of columns.
     *
     * @param columns the number of columns
     *
     * @return the preferred size of the text field
     *
     * @see TextField#getPreferredSize(int)
     */
    Dimension getPreferredSize(int columns);

    /**
     * Returns the minimum size of the text field with the specified number
     * of columns.
     *
     * @param columns the number of columns
     *
     * @return the minimum size of the text field
     *
     * @see TextField#getMinimumSize(int)
     */
    Dimension getMinimumSize(int columns);

}
