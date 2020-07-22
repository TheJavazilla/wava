package wava.sun.font;

public class FontManagerNativeLibrary {
    /*static {
        java.security.AccessController.doPrivileged(new java.security.PrivilegedAction() {
            public Object run() {
               // REMIND do we really have to load awt here?
               System.loadLibrary("awt");
               if (FontUtilities.isOpenJDK &&
                   System.getProperty("os.name").startsWith("Windows")) {
                   System.loadLibrary("freetype");
               }
               System.loadLibrary("fontmanager");

               return null;
            }
        });
    }*/

    /*
     * Call this method to ensure libraries are loaded.
     *
     * Method acts as trigger to ensure this class is loaded
     * (and therefore initializer code is executed).
     * Actual loading is performed by static initializer.
     * (no need to execute doPrivilledged block more than once)
     */
    public static void load() {}
}
