package java.net;

import java.applet.AudioClip;
import java.io.InputStream;

public class URL extends URI {

    public URL(String string) throws MalformedURLException {
        super(string);
    }

    public URL(Object object, String href) throws MalformedURLException {
        super(href);
        // TODO Auto-generated constructor stub
    }

    public URL(String protocol, String host, int port, String file) {
        super(protocol + host);
        // TODO Auto-generated constructor stub
    }

    public InputStream openStream() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getHost() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    public URLConnection openConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getDefaultPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getFile() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getProtocol() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getPath() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getAuthority() {
        // TODO Auto-generated method stub
        return null;
    }

    public String toExternalForm() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean sameFile(URL page) {
        // TODO Auto-generated method stub
        return false;
    }

    public String getRef() {
        // TODO Auto-generated method stub
        return null;
    }

    public AudioClip getContent() {
        // TODO Auto-generated method stub
        return null;
    }

}