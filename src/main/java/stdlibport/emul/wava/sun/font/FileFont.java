package wava.sun.font;

import java.awt.FontFormatException;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import wava.sun.java2d.Disposer;
import wava.sun.java2d.DisposerRecord;

public abstract class FileFont extends PhysicalFont {

    protected boolean useJavaRasterizer = true;

    /* I/O and file operations are always synchronized on the font
     * object. Two threads can be accessing the font and retrieving
     * information, and synchronized only to the extent that filesystem
     * operations require.
     * A limited number of files can be open at a time, to limit the
     * absorption of file descriptors. If a file needs to be opened
     * when there are none free, then the synchronization of all I/O
     * ensures that any in progress operation will complete before some
     * other thread closes the descriptor in order to allocate another one.
     */
    // NB consider using a RAF. FIS has finalize method so may take a
    // little longer to be GC'd. We don't use this stream at all anyway.
    // In fact why increase the size of a FileFont object if the stream
    // isn't needed ..
    //protected FileInputStream stream;
    //protected FileChannel channel;
    protected int fileSize;

    protected FontScaler scaler;

    /* The following variables are used, (and in the case of the arrays,
     * only initialised) for select fonts where a native scaler may be
     * used to get glyph images and metrics.
     * glyphToCharMap is filled in on the fly and used to do a reverse
     * lookup when a FileFont needs to get the charcode back from a glyph
     * code so it can re-map via a NativeGlyphMapper to get a native glyph.
     * This isn't a big hit in time, since a boolean test is sufficient
     * to choose the usual default path, nor in memory for fonts which take
     * the native path, since fonts have contiguous zero-based glyph indexes,
     * and these obviously do all exist in the font.
     */
    protected boolean checkedNatives;
    protected boolean useNatives;
    protected NativeFont[] nativeFonts;
    protected char[] glyphToCharMap;
    /*
     * @throws FontFormatException - if the font can't be opened
     */
    FileFont(String platname, Object nativeNames)
        throws FontFormatException {

        super(platname, nativeNames);
    }

    FontStrike createStrike(FontStrikeDesc desc) {
        if (!checkedNatives) {
           checkUseNatives();
        }
        return new FileFontStrike(this, desc);
    }

    protected boolean checkUseNatives() {
        checkedNatives = true;
        return useNatives;
    }

    /* This method needs to be accessible to FontManager if there is
     * file pool management. It may be a no-op.
     */
    protected abstract void close();


    /*
     * This is the public interface. The subclasses need to implement
     * this. The returned block may be longer than the requested length.
     */
    abstract ByteBuffer readBlock(int offset, int length);

    public boolean canDoStyle(int style) {
        return true;
    }

    void setFileToRemove(File file, CreatedFontTracker tracker) {
        Disposer.addObjectRecord(this,
                         new CreatedFontFileDisposerRecord(file, tracker));
    }

    // MACOSX begin -- Make this static so that we can pass in CFont
    static void setFileToRemove(Object font, File file, CreatedFontTracker tracker) {
        Disposer.addObjectRecord(font,
                         new CreatedFontFileDisposerRecord(file, tracker));
    }
    // MACOSX - end

    /* This is called when a font scaler is determined to
     * be unusable (ie bad).
     * We want to replace current scaler with NullFontScaler, so
     * we never try to use same font scaler again.
     * Scaler native resources could have already been disposed
     * or they will be eventually by Java2D disposer.
     * However, it should be safe to call dispose() explicitly here.
     *
     * For safety we also invalidate all strike's scaler context.
     * So, in case they cache pointer to native scaler
     * it will not ever be used.
     *
     * It also appears desirable to remove all the entries from the
     * cache so no other code will pick them up. But we can't just
     * 'delete' them as code may be using them. And simply dropping
     * the reference to the cache will make the reference objects
     * unreachable and so they will not get disposed.
     * Since a strike may hold (via java arrays) native pointers to many
     * rasterised glyphs, this would be a memory leak.
     * The solution is :
     * - to move all the entries to another map where they
     *   are no longer locatable
     * - update FontStrikeDisposer to be able to distinguish which
     * map they are held in via a boolean flag
     * Since this isn't expected to be anything other than an extremely
     * rare maybe it is not worth doing this last part.
     */
    synchronized void deregisterFontAndClearStrikeCache() {
        SunFontManager fm = SunFontManager.getInstance();
        fm.deRegisterBadFont(this);

        for (Reference strikeRef : strikeCache.values()) {
            if (strikeRef != null) {
                /* NB we know these are all FileFontStrike instances
                 * because the cache is on this FileFont
                 */
                FileFontStrike strike = (FileFontStrike)strikeRef.get();
                if (strike != null && strike.pScalerContext != 0L) {
                    scaler.invalidateScalerContext(strike.pScalerContext);
                }
            }
        }
        if (scaler != null) {
            scaler.disposeScaler();
        }
        scaler = FontScaler.getNullScaler();
    }

    StrikeMetrics getFontMetrics(long pScalerContext) {
        try {
            return getScaler().getFontMetrics(pScalerContext);
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            return getFontMetrics(pScalerContext);
        }
    }

    float getGlyphAdvance(long pScalerContext, int glyphCode) {
        try {
            return getScaler().getGlyphAdvance(pScalerContext, glyphCode);
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            return getGlyphAdvance(pScalerContext, glyphCode);
        }
    }

    void getGlyphMetrics(long pScalerContext, int glyphCode, Point2D.Float metrics) {
        try {
            getScaler().getGlyphMetrics(pScalerContext, glyphCode, metrics);
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            getGlyphMetrics(pScalerContext, glyphCode, metrics);
        }
    }

    long getGlyphImage(long pScalerContext, int glyphCode) {
        try {
            return getScaler().getGlyphImage(pScalerContext, glyphCode);
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            return getGlyphImage(pScalerContext, glyphCode);
        }
    }

    Rectangle2D.Float getGlyphOutlineBounds(long pScalerContext, int glyphCode) {
        try {
            return getScaler().getGlyphOutlineBounds(pScalerContext, glyphCode);
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            return getGlyphOutlineBounds(pScalerContext, glyphCode);
        }
    }

    GeneralPath getGlyphOutline(long pScalerContext, int glyphCode, float x, float y) {
        try {
            return getScaler().getGlyphOutline(pScalerContext, glyphCode, x, y);
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            return getGlyphOutline(pScalerContext, glyphCode, x, y);
        }
    }

    GeneralPath getGlyphVectorOutline(long pScalerContext, int[] glyphs, int numGlyphs, float x, float y) {
        try {
            return getScaler().getGlyphVectorOutline(pScalerContext, glyphs, numGlyphs, x, y);
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            return getGlyphVectorOutline(pScalerContext, glyphs, numGlyphs, x, y);
        }
    }

    /* T1 & TT implementation differ so this method is abstract.
       NB: null should not be returned here! */
    protected abstract FontScaler getScaler();

    protected long getUnitsPerEm() {
        return getScaler().getUnitsPerEm();
    }

    private static class CreatedFontFileDisposerRecord
        implements DisposerRecord {

        File fontFile = null;
        CreatedFontTracker tracker;

        private CreatedFontFileDisposerRecord(File file,
                                              CreatedFontTracker tracker) {
            fontFile = file;
            this.tracker = tracker;
        }

        public void dispose() {
            java.security.AccessController.doPrivileged(
                 new java.security.PrivilegedAction() {
                      public Object run() {
                          if (fontFile != null) {
                              try {
                                  if (tracker != null)
                                      tracker.subBytes((int)fontFile.length());

                                  fontFile.delete();
                                  SunFontManager.getInstance().tmpFontFiles.remove(fontFile);
                              } catch (Exception e) {
                              }
                          }
                          return null;
                      }
            });
        }
    }

    protected String getPublicFileName() {
        return platName;
    }

}