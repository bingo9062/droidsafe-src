package android.graphics;

// Droidsafe Imports
import com.android.internal.util.ArrayUtils;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class TemporaryBuffer {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:11.182 -0400", hash_original_method = "4FF062759AAFC6722B3224BBA791BE93", hash_generated_method = "4FF062759AAFC6722B3224BBA791BE93")
    public TemporaryBuffer ()
    {
        //Synthesized constructor
    }


    public static char[] obtain(int len) {
        char[] buf;
        synchronized (TemporaryBuffer.class) {
            buf = sTemp;
            sTemp = null;
        }
        if (buf == null || buf.length < len) {
            buf = new char[ArrayUtils.idealCharArraySize(len)];
        }
        return buf;
    }

    
    @DSModeled(DSC.SAFE)
    public static void recycle(char[] temp) {
        if (temp.length > 1000) return;
        synchronized (TemporaryBuffer.class) {
            sTemp = temp;
        }
    }

    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:11.184 -0400", hash_original_field = "C736DCF0716493E46DC696537C472B83", hash_generated_field = "D5B55575C53803B48B8DF0B1F8C65A25")

    private static char[] sTemp = null;
}

