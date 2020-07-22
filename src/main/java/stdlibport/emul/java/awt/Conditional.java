// Copyright (c) 2020 Fungus Software & Oracle under GPLv2 only with classpath exception

package java.awt;

/**
 * Conditional is used by the EventDispatchThread's message pumps to
 * determine if a given pump should continue to run, or should instead exit
 * and yield control to the parent pump.
 *
 * @author David Mendenhall
 */
interface Conditional {
    boolean evaluate();
}
