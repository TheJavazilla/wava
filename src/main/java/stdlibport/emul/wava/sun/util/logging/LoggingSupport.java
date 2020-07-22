package wava.sun.util.logging;

public class LoggingSupport {

    public static boolean isAvailable() {
        return true;
    }

    public static java.util.List<String> getLoggerNames() {
        return null;
    }

    public static String getLoggerLevel(String loggerName) {
        return "ALL";
    }

    public static void setLoggerLevel(String loggerName, String levelName) {
    }

    public static String getParentLoggerName(String loggerName) {
        return "wava";
    }

    public static Object getLogger(String name) {
        return PlatformLogger.getLogger("wava");
    }

    public static Object getLevel(Object logger) {
        return null;
    }

    public static void setLevel(Object logger, Object newLevel) {
    }

    public static boolean isLoggable(Object logger, Object level) {
        return false;
    }

    public static void log(Object logger, Object level, String msg) {
    }

    public static void log(Object logger, Object level, String msg, Throwable t) {
    }

    public static void log(Object logger, Object level, String msg, Object... params) {
    }

    public static Object parseLevel(String levelName) {
        return "ALL";
    }

    public static String getLevelName(Object level) {
        return "ALL";
    }

    public static int getLevelValue(Object level) {
        return 0;
    }

    private static final String DEFAULT_FORMAT = "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n";
    public static String getSimpleFormat() {
        return DEFAULT_FORMAT;
    }

}
