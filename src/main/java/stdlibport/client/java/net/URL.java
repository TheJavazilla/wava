package stdlibport.client.java.net;

public class URL {
    
    public String str;

    public URL(String string) {
        this.str = string;
    }

    @Override
    public String toString() {
        return str;
    }

}