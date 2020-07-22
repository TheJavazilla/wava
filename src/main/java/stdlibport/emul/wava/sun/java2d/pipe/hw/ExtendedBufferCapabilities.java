package wava.sun.java2d.pipe.hw;

import java.awt.BufferCapabilities;
import java.awt.ImageCapabilities;

public class ExtendedBufferCapabilities extends BufferCapabilities {

    public static enum VSyncType {
        VSYNC_DEFAULT(0),
        VSYNC_ON(1),
        VSYNC_OFF(2);

        public int id() {
            return id;
        }

        private VSyncType(int id) {
            this.id = id;
        }
        private int id;
    }

    private VSyncType vsync;

    public ExtendedBufferCapabilities(BufferCapabilities caps) {
        super(caps.getFrontBufferCapabilities(), caps.getBackBufferCapabilities(), caps.getFlipContents());
        this.vsync = VSyncType.VSYNC_DEFAULT;
    }

    public ExtendedBufferCapabilities(ImageCapabilities front, ImageCapabilities back, FlipContents flip) {
        super(front, back, flip);
        this.vsync = VSyncType.VSYNC_DEFAULT;
    }

    public ExtendedBufferCapabilities(ImageCapabilities front, ImageCapabilities back, FlipContents flip, VSyncType t) {
        super(front, back, flip);
        this.vsync = t;
    }

    public ExtendedBufferCapabilities(BufferCapabilities caps, VSyncType t) {
        super(caps.getFrontBufferCapabilities(), caps.getBackBufferCapabilities(), caps.getFlipContents());
        this.vsync = t;
    }

    public ExtendedBufferCapabilities derive(VSyncType t) {
        return new ExtendedBufferCapabilities(this, t);
    }

    public VSyncType getVSync() {
        return vsync;
    }

    @Override
    public final boolean isPageFlipping() {
        return true;
    }

}