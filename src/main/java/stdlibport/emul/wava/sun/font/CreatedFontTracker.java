package wava.sun.font;

import java.io.File;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import wava.sun.awt.AppContext;

public class CreatedFontTracker {

    public static final int MAX_FILE_SIZE = 32 * 1024 * 1024;
    public static final int MAX_TOTAL_BYTES = 10 * MAX_FILE_SIZE;

    static CreatedFontTracker tracker;
    int numBytes;

    public static synchronized CreatedFontTracker getTracker() {
        if (tracker == null)
            tracker = new CreatedFontTracker();
        return tracker;
    }

    private CreatedFontTracker() {
        numBytes = 0;
    }

    public synchronized int getNumBytes() {
        return numBytes;
    }

    public synchronized void addBytes(int sz) {
        numBytes += sz;
    }

    public synchronized void subBytes(int sz) {
        numBytes -= sz;
    }

    /**
     * Returns an AppContext-specific counting semaphore.
     */
    private static synchronized Semaphore getCS() {
        final AppContext appContext = AppContext.getAppContext();
        Semaphore cs = (Semaphore) appContext.get(CreatedFontTracker.class);
        if (cs == null) {
            // Make a semaphore with 5 permits that obeys the first-in first-out
            // granting of permits.
            cs = new Semaphore(5, true);
            appContext.put(CreatedFontTracker.class, cs);
        }
        return cs;
    }

    public boolean acquirePermit() throws InterruptedException {
        // This does a timed-out wait.
        return getCS().tryAcquire(120, TimeUnit.SECONDS);
    }

    public void releasePermit() {
        getCS().release();
    }

    public void add(File file) {
        TempFileDeletionHook.add(file);
    }

    public void set(File file, OutputStream os) {
        TempFileDeletionHook.set(file, os);
    }

    public void remove(File file) {
        TempFileDeletionHook.remove(file);
    }

    private static class TempFileDeletionHook {
        private static HashMap<File, OutputStream> files = new HashMap<>();

        private static Thread t = null;
        static void init() {
        }

        private TempFileDeletionHook() {}

        static synchronized void add(File file) {
            init();
            files.put(file, null);
        }

        static synchronized void set(File file, OutputStream os) {
            files.put(file, os);
        }

        static synchronized void remove(File file) {
            files.remove(file);
        }

        static synchronized void runHooks() {
        }
    }

}