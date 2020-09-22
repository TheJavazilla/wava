package com.javazilla.wava.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

import java.awt.Component;


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

        Component frame = new IsaiahTv();
        
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

        WebGraphics g = new WebGraphics(context, frame);
        html5.addMouseDownHandler(new MouseDownHandler() {

            @Override
            public void onMouseDown(MouseDownEvent event) {
                text = event.getX() + "," + event.getY();
            }});

        final Timer awtDraw = new Timer() {           
            @Override public void run() {
                
                g.forceRepaint();
            }
        };
        awtDraw.scheduleRepeating(10);

        //final Timer awtDraw = new Timer() {           
        //    @Override public void run() {g.forceRepaint();}
        //};
        //awtDraw.scheduleRepeating(10);

        //DialogBox w = (DialogBox) frame.gwt;
        //RootPanel.get().add(w);
    }

}