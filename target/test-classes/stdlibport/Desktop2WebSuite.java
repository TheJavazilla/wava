package stdlibport;

import stdlibport.client.Desktop2WebTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class Desktop2WebSuite extends GWTTestSuite {
    public static Test suite() {
        TestSuite suite = new TestSuite("Tests for Desktop2Web");
        suite.addTestSuite(Desktop2WebTest.class);
        return suite;
    }
}
