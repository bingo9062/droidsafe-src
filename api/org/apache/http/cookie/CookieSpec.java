package org.apache.http.cookie;

// Droidsafe Imports
import java.util.List;

import org.apache.http.Header;

public interface CookieSpec {    

    
    int getVersion();
    
    
    List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException;

    
    void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException;
    
    
    boolean match(Cookie cookie, CookieOrigin origin);

    
    List<Header> formatCookies(List<Cookie> cookies);

    
    Header getVersionHeader();
    
}
