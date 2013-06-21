package java.security.cert;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.util.Set;

public interface X509Extension {

    
    public Set<String> getCriticalExtensionOIDs();

    
    public byte[] getExtensionValue(String oid);

    
    public Set<String> getNonCriticalExtensionOIDs();

    
    public boolean hasUnsupportedCriticalExtension();
}
