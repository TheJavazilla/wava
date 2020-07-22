package wava.sun.net.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLPermission;
import java.security.Permission;

/**
 * URL Utility class.
 */
public class URLUtil {

    public static String urlNoFragString(URL url) {
        StringBuilder strForm = new StringBuilder();

        String protocol = url.getProtocol();
        if (protocol != null) {
            /* protocol is compared case-insensitive, so convert to lowercase */
            protocol = protocol.toLowerCase();
            strForm.append(protocol);
            strForm.append("://");
        }

        String host = url.getHost();
        if (host != null) {
            /* host is compared case-insensitive, so convert to lowercase */
            host = host.toLowerCase();
            strForm.append(host);

            int port = url.getPort();
            if (port == -1) {
                /* if no port is specificed then use the protocols
                 * default, if there is one */
                port = url.getDefaultPort();
            }
            if (port != -1) {
                strForm.append(":").append(port);
            }
        }

        String file = url.getFile();
        if (file != null) {
            strForm.append(file);
        }

        return strForm.toString();
    }

    public static Permission getConnectPermission(URL url) throws IOException {
        String urlStringLowerCase = url.toString().toLowerCase();
        if (urlStringLowerCase.startsWith("http:") || urlStringLowerCase.startsWith("https:")) {
            return getURLConnectPermission(url);
        } else if (urlStringLowerCase.startsWith("jar:http:") || urlStringLowerCase.startsWith("jar:https:")) {
            String urlString = url.toString();
            int bangPos = urlString.indexOf("!/");
            urlString = urlString.substring(4, bangPos > -1 ? bangPos : urlString.length());
            URL u = new URL(urlString);
            return getURLConnectPermission(u);
            // If protocol is HTTP or HTTPS than use URLPermission object
        } else {
            return url.openConnection().getPermission();
        }
    }

    private static Permission getURLConnectPermission(URL url) {
        String urlString = url.getProtocol() + "://" + url.getAuthority() + url.getPath();
        return new URLPermission(urlString);
    }

}