package com.android.internal.telephony;

// Droidsafe Imports
import droidsafe.annotations.DSGenerator;

public class IccException extends Exception {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:17.037 -0400", hash_original_method = "689A2E1E1574DEF078CD10C2BDEC6D21", hash_generated_method = "EA32E671BE005D4FE327F22565483AE0")
    public  IccException() {
        // ---------- Original Method ----------
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:17.038 -0400", hash_original_method = "C5281D49FF40E844F0E7519DDA3715E7", hash_generated_method = "AB51AF11B8B9F7927FA6FF6AF7D04DF7")
    public  IccException(String s) {
        super(s);
        addTaint(s.getTaint());
        // ---------- Original Method ----------
    }

    
}

