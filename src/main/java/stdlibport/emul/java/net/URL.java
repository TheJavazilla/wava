package java.net;

import com.google.gwt.safehtml.shared.SafeUri;

public class URL implements SafeUri {
    
    public String str;

    public URL(String string) {
        this.str = string;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public String asString() {
        return str;
    }

}