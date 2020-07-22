// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception

package java.awt;

import wava.sun.util.logging.PlatformLogger;

abstract class AttributeValue {
    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.AttributeValue");
    private final int value;
    private final String[] names;

    protected AttributeValue(int value, String[] names) {
        if (log.isLoggable(PlatformLogger.Level.FINEST)) {
            log.finest("value = " + value + ", names = " + names);
        }

        if (log.isLoggable(PlatformLogger.Level.FINER)) {
            if ((value < 0) || (names == null) || (value >= names.length)) {
                log.finer("Assertion failed");
            }
        }
        this.value = value;
        this.names = names;
    }
    // This hashCode is used by the sun.awt implementation as an array
    // index.
    public int hashCode() {
        return value;
    }
    public String toString() {
        return names[value];
    }
}
