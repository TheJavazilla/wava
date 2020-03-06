package stdlibport.client.java.awt;

public class Insets {

    public int bottom, left, right, top;

    public Insets(int top, int left, int bottom, int right) {
        set(top, left, bottom, right);
    }

    public void set(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

}