package javax.crypto;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import org.apache.harmony.crypto.internal.NullCipherSpi;

public class NullCipher extends Cipher {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:35.991 -0400", hash_original_method = "88CD20CBC668B0AA4FE357EBFA9D0A37", hash_generated_method = "37DC87A7C09DEC36BA8EDF962834C520")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public NullCipher() {
        super(new NullCipherSpi(), null, null);
        try 
        {
            this.init(Cipher.ENCRYPT_MODE, (Key)null, (SecureRandom)null);
        } //End block
        catch (InvalidKeyException e)
        { }
        // ---------- Original Method ----------
        //try {
            //this.init(Cipher.ENCRYPT_MODE, (Key)null, (SecureRandom)null);
        //} catch (InvalidKeyException e) {
        //}
    }

    
}

