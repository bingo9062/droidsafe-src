package org.xml.sax;

// Droidsafe Imports
import java.io.IOException;

public interface EntityResolver {


    
    public abstract InputSource resolveEntity (String publicId,
                           String systemId)
    throws SAXException, IOException;

}
