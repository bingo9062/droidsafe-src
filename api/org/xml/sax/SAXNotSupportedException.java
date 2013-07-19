package org.xml.sax;

// Droidsafe Imports
import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class SAXNotSupportedException extends SAXException {
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:44.232 -0400", hash_original_method = "8145A6EACBA97C57F61472FF031DA600", hash_generated_method = "11202C211CE2C664DE0C9CBEED1CA1E9")
    public  SAXNotSupportedException() {
        // ---------- Original Method ----------
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:44.232 -0400", hash_original_method = "388A9CB995B8D4BAD9F29D89DBB3CE91", hash_generated_method = "B17E41F3178F5EDC71086B064EC367B5")
    public  SAXNotSupportedException(String message) {
        super(message);
        addTaint(message.getTaint());
        // ---------- Original Method ----------
    }

    
}

