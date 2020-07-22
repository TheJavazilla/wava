package wava.sun.awt;

/**
 * Listener interface so Java Plug-in can be notified
 * of changes in AWT modality
 */
public interface ModalityListener {

    public void modalityPushed(ModalityEvent ev);
    public void modalityPopped(ModalityEvent ev);

}