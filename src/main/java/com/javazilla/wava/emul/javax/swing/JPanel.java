package javax.swing;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Panel;

import java.awt.Color;
import java.awt.LayoutManager;

public class JPanel extends JComponent {

    public JPanel() {
        gwt = new FlowPanel();
    }

    public void setBackground(Color white) {
        // TODO Auto-generated method stub
    }

    int row = 0; int col = 0;
    public JComponent add(JComponent j) {
        if (gwt instanceof Grid) {
            Grid g = (Grid)gwt;
            if (col == g.getCellCount(row)) {
                row++;
                col = 0;
            }
            if (row == g.getRowCount())
                g.resizeRows(g.getRowCount() + 2);
            g.setWidget(row, col, j.gwt);
            col++;
        } else
        ((Panel)gwt).add(j.gwt);
        return j;
    }

    public void setLayout(LayoutManager layout) {
        gwt = layout.gwt();
    }

}