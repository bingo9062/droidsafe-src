package android.util;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

import droidsafe.helpers.DSUtils;

public class FloatMath {
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static float floor(float value) {
        return value;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static float ceil(float value) {
        return value;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static float sin(float angle) {
        return angle;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static float cos(float angle) {
        return angle;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static float sqrt(float value) {
        return value;
    }

    /** Prevents instantiation. */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:25.639 -0500", hash_original_method = "1F13790E730E0CCC2AC5BFBAA0385051", hash_generated_method = "D298F494DD520E539E8C56F1D85453BE")
    
private FloatMath() {}
    
}
