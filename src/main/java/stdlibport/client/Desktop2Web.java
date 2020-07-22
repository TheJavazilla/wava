package stdlibport.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

import javax.swing.JFrame;
import stdlibport.client.IsaiahTv;
import wava.awt.WebGraphics;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Desktop2Web implements EntryPoint {
 
    public int width = 600;
    public int height = 400;
    public String text = "Test";

    public Context2d context;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        JFrame frame = new IsaiahTv();
        
        Canvas html5 = Canvas.createIfSupported();
        html5.setHeight(height + "px");
        html5.setWidth(width + "px");
        html5.setCoordinateSpaceHeight(height);
        html5.setCoordinateSpaceWidth(width);

        DialogBox box = new DialogBox();
        box.setText("Java AWT Canvas Test");
        box.setWidget(html5);

        RootPanel.get("canvas").add(box);
        context = html5.getContext2d();

        final Timer timer = new Timer() {           
            @Override
            public void run() {
                drawSomethingNew();
            }
        };
        timer.scheduleRepeating(600);

        html5.addMouseDownHandler(new MouseDownHandler() {

            @Override
            public void onMouseDown(MouseDownEvent event) {
                text = event.getX() + "," + event.getY();
            }});

        WebGraphics g = new WebGraphics(context, frame);

        //final Timer awtDraw = new Timer() {           
        //    @Override public void run() {g.forceRepaint();}
        //};
        //awtDraw.scheduleRepeating(10);

        //DialogBox w = (DialogBox) frame.gwt;
        //RootPanel.get().add(w);
    }

    int lastX = -1;

    protected void drawSomethingNew() {
        context.setFillStyle("rgb(230,230,230)");
        context.fillRect(18, 18, 50, 40);
        context.setFillStyle("rgb(0,0,0)");
        context.fillText(text, 24, 30);
        context.fill();
    }

}