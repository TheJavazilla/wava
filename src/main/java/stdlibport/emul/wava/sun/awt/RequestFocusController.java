package wava.sun.awt;

import java.awt.Component;

public interface RequestFocusController {

    public boolean acceptRequestFocus(Component from, Component to, boolean temporary, boolean focusedWindowChangeAllowed, CausedFocusEvent.Cause cause);

}