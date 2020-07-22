// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception

package java.awt.peer;

import java.awt.Dimension;
import java.awt.SystemTray;

/**
 * The peer interface for {@link SystemTray}. This doesn't need to be
 * implemented if {@link SystemTray#isSupported()} returns false.
 */
public interface SystemTrayPeer {

    /**
     * Returns the size of the system tray icon.
     *
     * @return the size of the system tray icon
     *
     * @see SystemTray#getTrayIconSize()
     */
    Dimension getTrayIconSize();
}
