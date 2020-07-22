// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception

package java.awt;

/**
 * Thrown when a serious Abstract Window Toolkit error has occurred.
 *
 * @author      Arthur van Hoff
 */
public class AWTError extends Error {

    /*
     * JDK 1.1 serialVersionUID
     */
     private static final long serialVersionUID = -1819846354050686206L;

    /**
     * Constructs an instance of <code>AWTError</code> with the specified
     * detail message.
     * @param   msg   the detail message.
     * @since   JDK1.0
     */
    public AWTError(String msg) {
        super(msg);
    }
}
