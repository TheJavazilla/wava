// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception
package java.awt.peer;

import java.awt.Label;

/**
 * The peer interface for {@link Label}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface LabelPeer extends ComponentPeer {

    /**
     * Sets the text to be displayed on the label.
     *
     * @param label the text to be displayed on the label
     *
     * @see Label#setText
     */
    void setText(String label);

    /**
     * Sets the alignment of the label text.
     *
     * @param alignment the alignment of the label text
     *
     * @see Label#setAlignment(int)
     * @see Label#CENTER
     * @see Label#RIGHT
     * @see Label#LEFT
     */
    void setAlignment(int alignment);
}
