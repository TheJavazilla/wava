package wava.sun.awt;

public interface SubRegionShowable {

    public void show(int x1, int y1, int x2, int y2);

    public boolean showIfNotLost(int x1, int y1, int x2, int y2);

}