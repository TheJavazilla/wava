package wava.sun.awt;

import java.util.EventListener;

public interface DisplayChangedListener extends EventListener {

    public void displayChanged();

    public void paletteChanged();

}