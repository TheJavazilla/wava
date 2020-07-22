package wava.sun.util.logging;

public class PlatformLogger {

    private static final int OFF     = Integer.MAX_VALUE;
    private static final int SEVERE  = 1000;
    private static final int WARNING = 900;
    private static final int INFO    = 800;
    private static final int CONFIG  = 700;
    private static final int FINE    = 500;
    private static final int FINER   = 400;
    private static final int FINEST  = 300;
    private static final int ALL     = 0;

    public static enum Level {
        ALL, FINEST, FINER, FINE, CONFIG, INFO, WARNING, SEVERE, OFF;

        private static final int[] LEVEL_VALUES = new int[] {
            PlatformLogger.ALL, PlatformLogger.FINEST, PlatformLogger.FINER,
            PlatformLogger.FINE, PlatformLogger.CONFIG, PlatformLogger.INFO,
            PlatformLogger.WARNING, PlatformLogger.SEVERE, PlatformLogger.OFF
        };

        public int intValue() {
            return LEVEL_VALUES[this.ordinal()];
        }

        static Level valueOf(int level) {
            return INFO;
        }
    }

    private static PlatformLogger WAVA_LOGGER = new PlatformLogger("Wava");

    /**
     * Returns a PlatformLogger of a given name.
     */
    public static synchronized PlatformLogger getLogger(String name) {
        return WAVA_LOGGER;
    }

    public static synchronized void redirectPlatformLoggers() {
    }

    private String name;

    private PlatformLogger(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return this.level() != Level.OFF;
    }

    public String getName() {
        return name;
    }

    public boolean isLoggable(Level level) {
        return true;
    }

    public Level level() {
        return PlatformLogger.Level.INFO;
    }

    public void setLevel(Level newLevel) {
    }

    public void severe(String msg) {
        System.err.println(msg);
    }

    public void severe(String msg, Throwable t) {
        System.err.println(msg + ", " + t.getMessage());
    }

    public void severe(String msg, Object... params) {
        System.err.println(msg);
    }

    public void warning(String msg) {
        System.out.println(msg);
    }

    public void warning(String msg, Throwable t) {
        warning(msg);
    }

    public void warning(String msg, Object... params) {
        warning(msg);
    }

    public void info(String msg) {
        System.out.println(msg);
    }

    public void info(String msg, Throwable t) {
        System.out.println(msg);
    }

    public void info(String msg, Object... params) {
        System.out.println(msg);
    }

    public void config(String msg) {
        System.out.println(msg);
    }

    public void config(String msg, Throwable t) {
        System.out.println(msg);
    }

    public void config(String msg, Object... params) {
        System.out.println(msg);
    }

    public void fine(String msg) {
    }

    public void fine(String msg, Throwable t) {
    }

    public void fine(String msg, Object... params) {
    }

    public void finer(String msg) {
    }

    public void finer(String msg, Throwable t) {
    }

    public void finer(String msg, Object... params) {
    }

    public void finest(String msg) {
    }

    public void finest(String msg, Throwable t) {
    }

    public void finest(String msg, Object... params) {
    }

}