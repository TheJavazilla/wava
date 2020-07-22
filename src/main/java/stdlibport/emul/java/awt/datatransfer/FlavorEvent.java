// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception
package java.awt.datatransfer;

import java.util.EventObject;


/**
 * <code>FlavorEvent</code> is used to notify interested parties
 * that available {@link DataFlavor}s have changed in the
 * {@link Clipboard} (the event source).
 *
 * @see FlavorListener
 *
 * @author Alexander Gerasimov
 * @since 1.5
 */
public class FlavorEvent extends EventObject {
    /**
     * Constructs a <code>FlavorEvent</code> object.
     *
     * @param source  the <code>Clipboard</code> that is the source of the event
     *
     * @throws IllegalArgumentException if the {@code source} is {@code null}
     */
    public FlavorEvent(Clipboard source) {
        super(source);
    }
}
