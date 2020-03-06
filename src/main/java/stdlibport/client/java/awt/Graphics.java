package stdlibport.client.java.awt;

public interface Graphics {

    public void drawString(String string, int x, int y);

    public Font getFont();

    public void setFont(Font font);

    public void setColor(Color color);

    public void fillRect(int x, int y, int width, int height);

}