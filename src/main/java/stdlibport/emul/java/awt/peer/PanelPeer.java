// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception
package java.awt.peer;

/**
 * The peer interface for {@link Panel}. This is a subinterface of
 * ContainerPeer and does not declare any additional methods because a Panel
 * is just that, a concrete Container.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface PanelPeer extends ContainerPeer {
}
