package stdlibport.client.java.awt;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import stdlibport.client.javax.swing.JComponent;


public class BoxLayout implements LayoutManager {

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;

    public Widget gwt;

    public BoxLayout(JComponent jc, int axis) {
        if (axis == 0)
            gwt = new HorizontalPanel();
        if (axis == 1)
            gwt = new VerticalPanel();
    }

    @Override
    public Widget gwt() {
        return gwt;
    }

}
