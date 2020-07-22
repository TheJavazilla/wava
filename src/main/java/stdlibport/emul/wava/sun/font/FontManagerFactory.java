package wava.sun.font;

import java.security.AccessController;
import java.security.PrivilegedAction;


/**
 * Factory class used to retrieve a valid FontManager instance for the current
 * platform.
 *
 * A default implementation is given for Linux, Solaris and Windows.
 * You can alter the behaviour of the {@link #getInstance()} method by setting
 * the {@code wava.sun.font.fontmanager} property. For example:
 * {@code wava.sun.font.fontmanager=wava.sun.awt.X11FontManager}
 */
public final class FontManagerFactory {

    /** Our singleton instance. */
    private static FontManager instance = null;

    private static final String DEFAULT_CLASS;
    static {
        if (FontUtilities.isWindows) {
            DEFAULT_CLASS = "wava.sun.awt.Win32FontManager";
        } else if (FontUtilities.isMacOSX) {
            DEFAULT_CLASS = "wava.sun.font.CFontManager";
            } else {
            DEFAULT_CLASS = "wava.sun.awt.X11FontManager";
            }
    }

    /**
     * Get a valid FontManager implementation for the current platform.
     *
     * @return a valid FontManager instance for the current platform
     */
    public static synchronized FontManager getInstance() {

        if (instance != null) {
            return instance;
        }

        AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                try {
                    String fmClassName =
                            System.getProperty("wava.sun.font.fontmanager",
                                               DEFAULT_CLASS);
                    ClassLoader cl = ClassLoader.getSystemClassLoader();
                    Class fmClass = Class.forName(fmClassName, true, cl);
                    instance = (FontManager) fmClass.newInstance();
                } catch (ClassNotFoundException |
                         InstantiationException |
                         IllegalAccessException ex) {
                    throw new InternalError(ex);

                }
                return null;
            }
        });

        return instance;
    }
}
