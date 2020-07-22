package wava.sun.awt;

import java.awt.AWTException;
import java.awt.Window;
import java.awt.im.spi.InputMethodDescriptor;
import java.util.Locale;
import wava.sun.awt.im.InputContext;


public interface InputMethodSupport {

    InputMethodDescriptor getInputMethodAdapterDescriptor() throws AWTException;
    Window createInputMethodWindow(String title, InputContext context);
    boolean enableInputMethodsForTextComponent();
    Locale getDefaultKeyboardLocale();

}