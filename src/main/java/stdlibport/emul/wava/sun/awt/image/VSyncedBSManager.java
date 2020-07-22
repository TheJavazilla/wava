package wava.sun.awt.image;

import java.awt.image.BufferStrategy;
import java.lang.ref.WeakReference;

/**
 * Manages v-synced buffer strategies.
 */
public abstract class VSyncedBSManager {

    private static VSyncedBSManager theInstance;

    private static final boolean vSyncLimit = false;
        //Boolean.valueOf((String)java.security.AccessController.doPrivileged(
        //        new wava.sun.security.action.GetPropertyAction(
        //            "wava.sun.java2d.vsynclimit", "true")));

    private static VSyncedBSManager getInstance(boolean create) {
        if (theInstance == null && create) {
            theInstance =
                vSyncLimit ? new SingleVSyncedBSMgr() : new NoLimitVSyncBSMgr();
        }
        return theInstance;
    }

    abstract boolean checkAllowed(BufferStrategy bs);
    abstract void relinquishVsync(BufferStrategy bs);

    /**
     * Returns true if the buffer strategy is allowed to be created
     * v-synced.
     *
     * @return true if the bs is allowed to be v-synced, false otherwise
     */
    public static boolean vsyncAllowed(BufferStrategy bs) {
        VSyncedBSManager bsm = getInstance(true);
        return bsm.checkAllowed(bs);
    }

    /**
     * Lets the manager know that this buffer strategy is no longer interested
     * in being v-synced.
     */
    public static synchronized void releaseVsync(BufferStrategy bs) {
        VSyncedBSManager bsm = getInstance(false);
        if (bsm != null) {
            bsm.relinquishVsync(bs);
        }
    }

    /**
     * An instance of the manager which allows any buffer strategy to be
     * v-synced.
     */
    private static final class NoLimitVSyncBSMgr extends VSyncedBSManager {
        @Override
        boolean checkAllowed(BufferStrategy bs) {
            return true;
        }

        @Override
        void relinquishVsync(BufferStrategy bs) {
        }
    }

    /**
     * An instance of the manager which allows only one buffer strategy to
     * be v-synced at any give moment in the vm.
     */
    private static final class SingleVSyncedBSMgr extends VSyncedBSManager {
        private WeakReference<BufferStrategy> strategy;

        @Override
        public synchronized boolean checkAllowed(BufferStrategy bs) {
            if (strategy != null) {
                BufferStrategy current = strategy.get();
                if (current != null) {
                    return (current == bs);
                }
            }
            strategy = new WeakReference<BufferStrategy>(bs);
            return true;
        }

        @Override
        public synchronized void relinquishVsync(BufferStrategy bs) {
            if (strategy != null) {
                BufferStrategy b = strategy.get();
                if (b == bs) {
                    strategy.clear();
                    strategy = null;
                }
            }
        }
    }

}