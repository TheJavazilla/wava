package javax.imageio;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FilePermission;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageReaderWriterSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.spi.ImageOutputStreamSpi;
import javax.imageio.spi.ImageTranscoderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import wava.sun.awt.AppContext;
//import wava.sun.security.action.GetPropertyAction;

/**
 * A class containing static convenience methods for locating
 * <code>ImageReader</code>s and <code>ImageWriter</code>s, and
 * performing simple encoding and decoding.
 */
public final class ImageIO {

    private static final IIORegistry theRegistry = IIORegistry.getDefaultInstance();

    private ImageIO() {}

    public static void scanForPlugins() {
        theRegistry.registerApplicationClasspathSpis();
    }

    // ImageInputStreams

    /**
     * A class to hold information about caching.  Each
     * <code>ThreadGroup</code> will have its own copy
     * via the <code>AppContext</code> mechanism.
     */
    static class CacheInfo {
        boolean useCache = true;
        File cacheDirectory = null;
        Boolean hasPermission = null;

        public CacheInfo() {}

        public boolean getUseCache() {
            return useCache;
        }

        public void setUseCache(boolean useCache) {
            this.useCache = useCache;
        }

        public File getCacheDirectory() {
            return cacheDirectory;
        }

        public void setCacheDirectory(File cacheDirectory) {
            this.cacheDirectory = cacheDirectory;
        }

        public Boolean getHasPermission() {
            return hasPermission;
        }

        public void setHasPermission(Boolean hasPermission) {
            this.hasPermission = hasPermission;
        }
    }

    /**
     * Returns the <code>CacheInfo</code> object associated with this
     * <code>ThreadGroup</code>.
     */
    private static synchronized CacheInfo getCacheInfo() {
        AppContext context = AppContext.getAppContext();
        CacheInfo info = (CacheInfo)context.get(CacheInfo.class);
        if (info == null) {
            info = new CacheInfo();
            context.put(CacheInfo.class, info);
        }
        return info;
    }

    /**
     * Returns the default temporary (cache) directory as defined by the
     * java.io.tmpdir system property.
     */
    private static String getTempDir() {
        //GetPropertyAction a = new GetPropertyAction("java.io.tmpdir");
        // TODO: WAVA
        return "temp";
    }

    /**
     * Determines whether the caller has write access to the cache
     * directory, stores the result in the <code>CacheInfo</code> object,
     * and returns the decision.  This method helps to prevent mysterious
     * SecurityExceptions to be thrown when this convenience class is used
     * in an applet, for example.
     */
    private static boolean hasCachePermission() {
        Boolean hasPermission = getCacheInfo().getHasPermission();

        if (hasPermission != null) {
            return hasPermission.booleanValue();
        } else {
            try {
                SecurityManager security = System.getSecurityManager();
                if (security != null) {
                    File cachedir = getCacheDirectory();
                    String cachepath;

                    if (cachedir != null) {
                        cachepath = cachedir.getPath();
                    } else {
                        cachepath = getTempDir();

                        if (cachepath == null || cachepath.isEmpty()) {
                            getCacheInfo().setHasPermission(Boolean.FALSE);
                            return false;
                        }
                    }

                    // we have to check whether we can read, write,
                    // and delete cache files.
                    // So, compose cache file path and check it.
                    String filepath = cachepath;
                    if (!filepath.endsWith(File.separator))
                        filepath += File.separator;
                    filepath += "*";

                    security.checkPermission(new FilePermission(filepath, "read, write, delete"));
                }
            } catch (SecurityException e) {
                getCacheInfo().setHasPermission(Boolean.FALSE);
                return false;
            }

            getCacheInfo().setHasPermission(Boolean.TRUE);
            return true;
        }
    }

    /**
     * Sets a flag indicating whether a disk-based cache file should
     * be used when creating <code>ImageInputStream</code>s and
     * <code>ImageOutputStream</code>s.
     *
     * <p> When reading from a standard <code>InputStream</code>, it
     * may be necessary to save previously read information in a cache
     * since the underlying stream does not allow data to be re-read.
     * Similarly, when writing to a standard
     * <code>OutputStream</code>, a cache may be used to allow a
     * previously written value to be changed before flushing it to
     * the final destination.
     *
     * <p> The cache may reside in main memory or on disk.  Setting
     * this flag to <code>false</code> disallows the use of disk for
     * future streams, which may be advantageous when working with
     * small images, as the overhead of creating and destroying files
     * is removed.
     *
     * <p> On startup, the value is set to <code>true</code>.
     *
     * @param useCache a <code>boolean</code> indicating whether a
     * cache file should be used, in cases where it is optional.
     *
     * @see #getUseCache
     */
    public static void setUseCache(boolean useCache) {
        getCacheInfo().setUseCache(useCache);
    }

    /**
     * Returns the current value set by <code>setUseCache</code>, or
     * <code>true</code> if no explicit setting has been made.
     *
     * @return true if a disk-based cache may be used for
     * <code>ImageInputStream</code>s and
     * <code>ImageOutputStream</code>s.
     *
     * @see #setUseCache
     */
    public static boolean getUseCache() {
        return getCacheInfo().getUseCache();
    }

    /**
     * Sets the directory where cache files are to be created.  A
     * value of <code>null</code> indicates that the system-dependent
     * default temporary-file directory is to be used.  If
     * <code>getUseCache</code> returns false, this value is ignored.
     *
     * @param cacheDirectory a <code>File</code> specifying a directory.
     *
     * @see File#createTempFile(String, String, File)
     *
     * @exception SecurityException if the security manager denies
     * access to the directory.
     * @exception IllegalArgumentException if <code>cacheDir</code> is
     * non-<code>null</code> but is not a directory.
     *
     * @see #getCacheDirectory
     */
    public static void setCacheDirectory(File cacheDirectory) {
        if ((cacheDirectory != null) && !(cacheDirectory.isDirectory())) {
            throw new IllegalArgumentException("Not a directory!");
        }
        getCacheInfo().setCacheDirectory(cacheDirectory);
        getCacheInfo().setHasPermission(null);
    }

    /**
     * Returns the current value set by
     * <code>setCacheDirectory</code>, or <code>null</code> if no
     * explicit setting has been made.
     *
     * @return a <code>File</code> indicating the directory where
     * cache files will be created, or <code>null</code> to indicate
     * the system-dependent default temporary-file directory.
     *
     * @see #setCacheDirectory
     */
    public static File getCacheDirectory() {
        return getCacheInfo().getCacheDirectory();
    }

    /**
     * Returns an <code>ImageInputStream</code> that will take its
     * input from the given <code>Object</code>.  The set of
     * <code>ImageInputStreamSpi</code>s registered with the
     * <code>IIORegistry</code> class is queried and the first one
     * that is able to take input from the supplied object is used to
     * create the returned <code>ImageInputStream</code>.  If no
     * suitable <code>ImageInputStreamSpi</code> exists,
     * <code>null</code> is returned.
     *
     * <p> The current cache settings from <code>getUseCache</code>and
     * <code>getCacheDirectory</code> will be used to control caching.
     *
     * @param input an <code>Object</code> to be used as an input
     * source, such as a <code>File</code>, readable
     * <code>RandomAccessFile</code>, or <code>InputStream</code>.
     *
     * @return an <code>ImageInputStream</code>, or <code>null</code>.
     *
     * @exception IllegalArgumentException if <code>input</code>
     * is <code>null</code>.
     * @exception IOException if a cache file is needed but cannot be
     * created.
     *
     * @see javax.imageio.spi.ImageInputStreamSpi
     */
    public static ImageInputStream createImageInputStream(Object input)
        throws IOException {
        if (input == null) {
            throw new IllegalArgumentException("input == null!");
        }

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageInputStreamSpi.class,
                                                   true);
        } catch (IllegalArgumentException e) {
            return null;
        }

        boolean usecache = getUseCache() && hasCachePermission();

        while (iter.hasNext()) {
            ImageInputStreamSpi spi = (ImageInputStreamSpi)iter.next();
            if (spi.getInputClass().isInstance(input)) {
                try {
                    return spi.createInputStreamInstance(input, usecache, getCacheDirectory());
                } catch (IOException e) {
                    throw new IIOException("Can't create cache file!", e);
                }
            }
        }

        return null;
    }

    // ImageOutputStreams

    /**
     * Returns an <code>ImageOutputStream</code> that will send its
     * output to the given <code>Object</code>.  The set of
     * <code>ImageOutputStreamSpi</code>s registered with the
     * <code>IIORegistry</code> class is queried and the first one
     * that is able to send output from the supplied object is used to
     * create the returned <code>ImageOutputStream</code>.  If no
     * suitable <code>ImageOutputStreamSpi</code> exists,
     * <code>null</code> is returned.
     *
     * <p> The current cache settings from <code>getUseCache</code>and
     * <code>getCacheDirectory</code> will be used to control caching.
     *
     * @param output an <code>Object</code> to be used as an output
     * destination, such as a <code>File</code>, writable
     * <code>RandomAccessFile</code>, or <code>OutputStream</code>.
     *
     * @return an <code>ImageOutputStream</code>, or
     * <code>null</code>.
     *
     * @exception IllegalArgumentException if <code>output</code> is
     * <code>null</code>.
     * @exception IOException if a cache file is needed but cannot be
     * created.
     *
     * @see javax.imageio.spi.ImageOutputStreamSpi
     */
    public static ImageOutputStream createImageOutputStream(Object output)
        throws IOException {
        if (output == null) {
            throw new IllegalArgumentException("output == null!");
        }

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageOutputStreamSpi.class, true);
        } catch (IllegalArgumentException e) {
            return null;
        }

        boolean usecache = getUseCache() && hasCachePermission();

        while (iter.hasNext()) {
            ImageOutputStreamSpi spi = (ImageOutputStreamSpi)iter.next();
            if (spi.getOutputClass().isInstance(output)) {
                try {
                    return spi.createOutputStreamInstance(output,
                                                          usecache,
                                                          getCacheDirectory());
                } catch (IOException e) {
                    throw new IIOException("Can't create cache file!", e);
                }
            }
        }

        return null;
    }

    private static enum SpiInfo {
        FORMAT_NAMES {
            @Override
            String[] info(ImageReaderWriterSpi spi) {
                return spi.getFormatNames();
            }
        },
        MIME_TYPES {
            @Override
            String[] info(ImageReaderWriterSpi spi) {
                return spi.getMIMETypes();
            }
        },
        FILE_SUFFIXES {
            @Override
            String[] info(ImageReaderWriterSpi spi) {
                return spi.getFileSuffixes();
            }
        };

        abstract String[] info(ImageReaderWriterSpi spi);
    }

    private static <S extends ImageReaderWriterSpi> String[] getReaderWriterInfo(Class<S> spiClass, SpiInfo spiInfo) {
        // Ensure category is present
        Iterator<S> iter;
        try {
            iter = theRegistry.getServiceProviders(spiClass, true);
        } catch (IllegalArgumentException e) {
            return new String[0];
        }

        HashSet<String> s = new HashSet<String>();
        while (iter.hasNext()) {
            ImageReaderWriterSpi spi = iter.next();
            Collections.addAll(s, spiInfo.info(spi));
        }

        return s.toArray(new String[s.size()]);
    }

    // Readers

    /**
     * Returns an array of <code>String</code>s listing all of the
     * informal format names understood by the current set of registered readers.
     */
    public static String[] getReaderFormatNames() {
        return getReaderWriterInfo(ImageReaderSpi.class, SpiInfo.FORMAT_NAMES);
    }

    /**
     * Returns an array of <code>String</code>s listing all of the
     * MIME types understood by the current set of registered readers.
     */
    public static String[] getReaderMIMETypes() {
        return getReaderWriterInfo(ImageReaderSpi.class, SpiInfo.MIME_TYPES);
    }

    /**
     * Returns an array of <code>String</code>s listing all of the
     * file suffixes associated with the formats understood
     * by the current set of registered readers.
     */
    public static String[] getReaderFileSuffixes() {
        return getReaderWriterInfo(ImageReaderSpi.class, SpiInfo.FILE_SUFFIXES);
    }

    static class ImageReaderIterator implements Iterator<ImageReader> {
        // Contains ImageReaderSpis
        public Iterator iter;

        public ImageReaderIterator(Iterator iter) {
            this.iter = iter;
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public ImageReader next() {
            ImageReaderSpi spi = null;
            try {
                spi = (ImageReaderSpi)iter.next();
                return spi.createReaderInstance();
            } catch (IOException e) {
                // Deregister the spi in this case, but only as
                // an ImageReaderSpi
                theRegistry.deregisterServiceProvider(spi, ImageReaderSpi.class);
            }
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    static class CanDecodeInputFilter implements ServiceRegistry.Filter {
        Object input;

        public CanDecodeInputFilter(Object input) {
            this.input = input;
        }

        public boolean filter(Object elt) {
            try {
                ImageReaderSpi spi = (ImageReaderSpi)elt;
                ImageInputStream stream = null;
                if (input instanceof ImageInputStream)
                    stream = (ImageInputStream)input;

                // Perform mark/reset as a defensive measure even though plug-ins are supposed to take care of it.
                boolean canDecode = false;
                if (stream != null)
                    stream.mark();

                canDecode = spi.canDecodeInput(input);
                if (stream != null)
                    stream.reset();

                return canDecode;
            } catch (IOException e) {
                return false;
            }
        }
    }

    static class CanEncodeImageAndFormatFilter implements ServiceRegistry.Filter {

        ImageTypeSpecifier type;
        String formatName;

        public CanEncodeImageAndFormatFilter(ImageTypeSpecifier type, String formatName) {
            this.type = type;
            this.formatName = formatName;
        }

        public boolean filter(Object elt) {
            ImageWriterSpi spi = (ImageWriterSpi)elt;
            return Arrays.asList(spi.getFormatNames()).contains(formatName) && spi.canEncodeImage(type);
        }
    }

    static class ContainsFilter implements ServiceRegistry.Filter {

        Method method;
        String name;

        // method returns an array of Strings
        public ContainsFilter(Method method, String name) {
            this.method = method;
            this.name = name;
        }

        public boolean filter(Object elt) {
            try {
                return contains((String[])method.invoke(elt), name);
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static Iterator<ImageReader> getImageReaders(Object input) {
        if (input == null)
            throw new IllegalArgumentException("input == null!");

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageReaderSpi.class, new CanDecodeInputFilter(input), true);
        } catch (IllegalArgumentException e) {return Collections.emptyIterator();}

        return new ImageReaderIterator(iter);
    }

    private static Method readerFormatNamesMethod;
    private static Method readerFileSuffixesMethod;
    private static Method readerMIMETypesMethod;
    private static Method writerFormatNamesMethod;
    private static Method writerFileSuffixesMethod;
    private static Method writerMIMETypesMethod;

    static {
        try {
            readerFormatNamesMethod = ImageReaderSpi.class.getMethod("getFormatNames");
            readerFileSuffixesMethod = ImageReaderSpi.class.getMethod("getFileSuffixes");
            readerMIMETypesMethod = ImageReaderSpi.class.getMethod("getMIMETypes");

            writerFormatNamesMethod = ImageWriterSpi.class.getMethod("getFormatNames");
            writerFileSuffixesMethod = ImageWriterSpi.class.getMethod("getFileSuffixes");
            writerMIMETypesMethod = ImageWriterSpi.class.getMethod("getMIMETypes");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Iterator<ImageReader> getImageReadersByFormatName(String formatName) {
        if (formatName == null)
            throw new IllegalArgumentException("formatName == null!");

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageReaderSpi.class, new ContainsFilter(readerFormatNamesMethod,formatName), true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }
        return new ImageReaderIterator(iter);
    }

    public static Iterator<ImageReader> getImageReadersBySuffix(String fileSuffix){
        if (fileSuffix == null)
            throw new IllegalArgumentException("fileSuffix == null!");

        // Ensure category is present
        Iterator iter;
        try {
            iter = theRegistry.getServiceProviders(ImageReaderSpi.class, new ContainsFilter(readerFileSuffixesMethod,fileSuffix),true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }
        return new ImageReaderIterator(iter);
    }

    public static Iterator<ImageReader> getImageReadersByMIMEType(String MIMEType) {
        if (MIMEType == null)
            throw new IllegalArgumentException("MIMEType == null!");

        // Ensure category is present
        Iterator iter;
        try {
            iter = theRegistry.getServiceProviders(ImageReaderSpi.class, new ContainsFilter(readerMIMETypesMethod, MIMEType), true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }
        return new ImageReaderIterator(iter);
    }

    // Writers

    /**
     * Returns an array of <code>String</code>s listing all of the
     * informal format names understood by the current set of registered
     * writers.
     *
     * @return an array of <code>String</code>s.
     */
    public static String[] getWriterFormatNames() {
        return getReaderWriterInfo(ImageWriterSpi.class, SpiInfo.FORMAT_NAMES);
    }

    /**
     * Returns an array of <code>String</code>s listing all of the
     * MIME types understood by the current set of registered
     * writers.
     *
     * @return an array of <code>String</code>s.
     */
    public static String[] getWriterMIMETypes() {
        return getReaderWriterInfo(ImageWriterSpi.class, SpiInfo.MIME_TYPES);
    }

    /**
     * Returns an array of <code>String</code>s listing all of the
     * file suffixes associated with the formats understood
     * by the current set of registered writers.
     *
     * @return an array of <code>String</code>s.
     */
    public static String[] getWriterFileSuffixes() {
        return getReaderWriterInfo(ImageWriterSpi.class, SpiInfo.FILE_SUFFIXES);
    }

    static class ImageWriterIterator implements Iterator<ImageWriter> {
        // Contains ImageWriterSpis
        public Iterator iter;

        public ImageWriterIterator(Iterator iter) {
            this.iter = iter;
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public ImageWriter next() {
            ImageWriterSpi spi = null;
            try {
                spi = (ImageWriterSpi)iter.next();
                return spi.createWriterInstance();
            } catch (IOException e) {
                // Deregister the spi in this case, but only as a writerSpi
                theRegistry.deregisterServiceProvider(spi, ImageWriterSpi.class);
            }
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static boolean contains(String[] names, String name) {
        for (int i = 0; i < names.length; i++)
            if (name.equalsIgnoreCase(names[i]))
                return true;
        return false;
    }

    public static Iterator<ImageWriter> getImageWritersByFormatName(String formatName) {
        if (formatName == null)
            throw new IllegalArgumentException("formatName == null!");

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageWriterSpi.class, new ContainsFilter(writerFormatNamesMethod, formatName),true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }
        return new ImageWriterIterator(iter);
    }

    public static Iterator<ImageWriter> getImageWritersBySuffix(String fileSuffix) {
        if (fileSuffix == null)
            throw new IllegalArgumentException("fileSuffix == null!");

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageWriterSpi.class, new ContainsFilter(writerFileSuffixesMethod,fileSuffix),true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }
        return new ImageWriterIterator(iter);
    }

    public static Iterator<ImageWriter> getImageWritersByMIMEType(String MIMEType) {
        if (MIMEType == null)
            throw new IllegalArgumentException("MIMEType == null!");
        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageWriterSpi.class, new ContainsFilter(writerMIMETypesMethod,MIMEType), true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }
        return new ImageWriterIterator(iter);
    }

    public static ImageWriter getImageWriter(ImageReader reader) {
        if (reader == null)
            throw new IllegalArgumentException("reader == null!");

        ImageReaderSpi readerSpi = reader.getOriginatingProvider();
        if (readerSpi == null) {
            Iterator readerSpiIter;
            // Ensure category is present
            try {
                readerSpiIter = theRegistry.getServiceProviders(ImageReaderSpi.class,false);
            } catch (IllegalArgumentException e) {return null;}

            while (readerSpiIter.hasNext()) {
                ImageReaderSpi temp = (ImageReaderSpi) readerSpiIter.next();
                if (temp.isOwnReader(reader)) {
                    readerSpi = temp;
                    break;
                }
            }
            if (readerSpi == null)
                return null;
        }

        String[] writerNames = readerSpi.getImageWriterSpiNames();
        if (writerNames == null)
            return null;

        Class writerSpiClass = null;
        try {
            writerSpiClass = Class.forName(writerNames[0], true, ClassLoader.getSystemClassLoader());
        } catch (ClassNotFoundException e){return null;}

        ImageWriterSpi writerSpi = (ImageWriterSpi) theRegistry.getServiceProviderByClass(writerSpiClass);
        if (writerSpi == null)
            return null;

        try {
            return writerSpi.createWriterInstance();
        } catch (IOException e) {
            // Deregister the spi in this case, but only as a writerSpi
            theRegistry.deregisterServiceProvider(writerSpi,ImageWriterSpi.class);
            return null;
        }
    }

    public static ImageReader getImageReader(ImageWriter writer) {
        if (writer == null)
            throw new IllegalArgumentException("writer == null!");

        ImageWriterSpi writerSpi = writer.getOriginatingProvider();
        if (writerSpi == null) {
            Iterator writerSpiIter;
            // Ensure category is present
            try {
                writerSpiIter = theRegistry.getServiceProviders(ImageWriterSpi.class,false);
            } catch (IllegalArgumentException e){return null;}

            while (writerSpiIter.hasNext()) {
                ImageWriterSpi temp = (ImageWriterSpi) writerSpiIter.next();
                if (temp.isOwnWriter(writer)) {
                    writerSpi = temp;
                    break;
                }
            }
            if (writerSpi == null)
                return null;
        }

        String[] readerNames = writerSpi.getImageReaderSpiNames();
        if (readerNames == null)
            return null;

        Class readerSpiClass = null;
        try {
            readerSpiClass = Class.forName(readerNames[0], true, ClassLoader.getSystemClassLoader());
        } catch (ClassNotFoundException e) {
            return null;
        }

        ImageReaderSpi readerSpi = (ImageReaderSpi) theRegistry.getServiceProviderByClass(readerSpiClass);
        if (readerSpi == null)
            return null;

        try {
            return readerSpi.createReaderInstance();
        } catch (IOException e) {
            // Deregister the spi in this case, but only as a readerSpi
            theRegistry.deregisterServiceProvider(readerSpi, ImageReaderSpi.class);
            return null;
        }
    }

    public static Iterator<ImageWriter> getImageWriters(ImageTypeSpecifier type, String formatName) {
        if (type == null)
            throw new IllegalArgumentException("type == null!");
        if (formatName == null)
            throw new IllegalArgumentException("formatName == null!");

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageWriterSpi.class, new CanEncodeImageAndFormatFilter(type, formatName), true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }

        return new ImageWriterIterator(iter);
    }

    static class ImageTranscoderIterator implements Iterator<ImageTranscoder> {
        // Contains ImageTranscoderSpis
        public Iterator iter;

        public ImageTranscoderIterator(Iterator iter) {
            this.iter = iter;
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public ImageTranscoder next() {
            ImageTranscoderSpi spi = null;
            spi = (ImageTranscoderSpi)iter.next();
            return spi.createTranscoderInstance();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    static class TranscoderFilter implements ServiceRegistry.Filter {

        String readerSpiName;
        String writerSpiName;

        public TranscoderFilter(ImageReaderSpi readerSpi, ImageWriterSpi writerSpi) {
            this.readerSpiName = readerSpi.getClass().getName();
            this.writerSpiName = writerSpi.getClass().getName();
        }

        public boolean filter(Object elt) {
            ImageTranscoderSpi spi = (ImageTranscoderSpi)elt;
            String readerName = spi.getReaderServiceProviderName();
            String writerName = spi.getWriterServiceProviderName();
            return (readerName.equals(readerSpiName) && writerName.equals(writerSpiName));
        }
    }

    public static Iterator<ImageTranscoder> getImageTranscoders(ImageReader reader, ImageWriter writer) {
        if (reader == null)
            throw new IllegalArgumentException("reader == null!");

        if (writer == null)
            throw new IllegalArgumentException("writer == null!");

        ImageReaderSpi readerSpi = reader.getOriginatingProvider();
        ImageWriterSpi writerSpi = writer.getOriginatingProvider();
        ServiceRegistry.Filter filter = new TranscoderFilter(readerSpi, writerSpi);

        Iterator iter;
        // Ensure category is present
        try {
            iter = theRegistry.getServiceProviders(ImageTranscoderSpi.class, filter, true);
        } catch (IllegalArgumentException e) {
            return Collections.emptyIterator();
        }
        return new ImageTranscoderIterator(iter);
    }

    // All-in-one methods


    public static BufferedImage read(File input) throws IOException {
        return null;
    }

    public static BufferedImage read(InputStream input) throws IOException {
        if (input == null)
            throw new IllegalArgumentException("input == null!");

        ImageInputStream stream = createImageInputStream(input);
        if (stream == null)
            throw new IIOException("Can't create an ImageInputStream!");

        BufferedImage bi = read(stream);
        if (bi == null)
            stream.close();
        return bi;
    }

    public static BufferedImage read(URL input) throws IOException {
        return null;
    }

    public static BufferedImage read(ImageInputStream stream) throws IOException {
        if (stream == null)
            throw new IllegalArgumentException("stream == null!");

        Iterator iter = getImageReaders(stream);
        if (!iter.hasNext())
            return null;
        ImageReader reader = (ImageReader)iter.next();
        ImageReadParam param = reader.getDefaultReadParam();
        reader.setInput(stream, true, true);
        BufferedImage bi;
        try {
            bi = reader.read(0, param);
        } finally {
            reader.dispose();
            stream.close();
        }
        return bi;
    }


    public static boolean write(RenderedImage im, String formatName, ImageOutputStream output) throws IOException {
        return doWrite(im, getWriter(im, formatName), output);
    }

    public static boolean write(RenderedImage im, String formatName, File output) throws IOException {
        return false;
    }

    public static boolean write(RenderedImage im, String formatName, OutputStream output) throws IOException {
        return false;
    }

    private static ImageWriter getWriter(RenderedImage im, String formatName) {
        ImageTypeSpecifier type = ImageTypeSpecifier.createFromRenderedImage(im);
        Iterator<ImageWriter> iter = getImageWriters(type, formatName);

        if (iter.hasNext())
            return iter.next();
        else return null;
    }

    /**
     * Writes image to output stream  using given image writer.
     */
    private static boolean doWrite(RenderedImage im, ImageWriter writer, ImageOutputStream output) throws IOException {
        if (writer == null)
            return false;

        writer.setOutput(output);
        try {
            writer.write(im);
        } finally {
            writer.dispose();
            output.flush();
        }
        return true;
    }

}