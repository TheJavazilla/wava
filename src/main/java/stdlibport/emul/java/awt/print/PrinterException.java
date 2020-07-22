// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception

package java.awt.print;

/**
 * The <code>PrinterException</code> class and its subclasses are used
 * to indicate that an exceptional condition has occurred in the print
 * system.
 */

public class PrinterException extends Exception {

    /**
     * Constructs a new <code>PrinterException</code> object
     * without a detail message.
     */
    public PrinterException() {

    }

    /**
     * Constructs a new <code>PrinterException</code> object
     * with the specified detail message.
     * @param msg the message to generate when a
     * <code>PrinterException</code> is thrown
     */
    public PrinterException(String msg) {
        super(msg);
    }
}
