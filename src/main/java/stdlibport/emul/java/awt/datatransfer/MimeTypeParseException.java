// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception

package java.awt.datatransfer;


/**
 *    A class to encapsulate MimeType parsing related exceptions
 *
 * @serial exclude
 * @since 1.3
 */
public class MimeTypeParseException extends Exception {

    // use serialVersionUID from JDK 1.2.2 for interoperability
    private static final long serialVersionUID = -5604407764691570741L;

    /**
     * Constructs a MimeTypeParseException with no specified detail message.
     */
    public MimeTypeParseException() {
        super();
    }

    /**
     * Constructs a MimeTypeParseException with the specified detail message.
     *
     * @param   s   the detail message.
     */
    public MimeTypeParseException(String s) {
        super(s);
    }
} // class MimeTypeParseException
