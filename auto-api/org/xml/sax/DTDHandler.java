package org.xml.sax;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;

public interface DTDHandler {


    
    public abstract void notationDecl (String name,
                       String publicId,
                       String systemId)
    throws SAXException;


    
    public abstract void unparsedEntityDecl (String name,
                         String publicId,
                         String systemId,
                         String notationName)
    throws SAXException;

}
