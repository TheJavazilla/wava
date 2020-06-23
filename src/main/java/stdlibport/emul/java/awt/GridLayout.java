package java.awt;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

public class GridLayout implements LayoutManager {
    
    private Grid gwt;

    public GridLayout(int i, int j) {
        this.gwt = new Grid(i, j);
        if (i == 0)((Grid)gwt).resizeRows(i + 2);
    }

    @Override
    public Widget gwt() {
        return gwt;
    }

}
