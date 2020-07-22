package java.net;

import com.google.gwt.safehtml.shared.SafeUri;

public class URI implements SafeUri {

    public String str;

    public URI(String string) {
        this.str = string;
    }

    public URI(String scheme, String string, Object path, Object fragment) throws URISyntaxException {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public String asString() {
        return str;
    }

    public String getScheme() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getPath() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getFragment() {
        // TODO Auto-generated method stub
        return null;
    }

    public URL toURL() throws MalformedURLException {
        // TODO Auto-generated method stub
        return null;
    }

    public String normalize() {
        // TODO Auto-generated method stub
        return null;
    }

}