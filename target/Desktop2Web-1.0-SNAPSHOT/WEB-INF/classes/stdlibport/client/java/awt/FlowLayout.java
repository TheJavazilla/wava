package stdlibport.client.java.awt;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class FlowLayout implements LayoutManager {

    private FlowPanel gwt;

    public FlowLayout() {
        this.gwt = new FlowPanel();
    }

    @Override
    public Widget gwt() {
        return gwt;
    }

}
