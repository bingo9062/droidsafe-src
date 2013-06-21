package java.nio.charset.spi;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class CharsetProvider {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:29.283 -0400", hash_original_method = "71B9A0846B394A5E85543A853F8CC262", hash_generated_method = "E9423E783CD0299C830EA4AD9E2C58C1")
    @DSModeled(DSC.SAFE)
    protected CharsetProvider() {
        // ---------- Original Method ----------
    }

    
    public abstract Iterator<Charset> charsets();

    
    public abstract Charset charsetForName(String charsetName);

    
}

