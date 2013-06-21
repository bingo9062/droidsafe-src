package java.security.interfaces;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.math.BigInteger;
import java.security.PrivateKey;

public interface ECPrivateKey extends PrivateKey, ECKey {

    
    public static final long serialVersionUID = -7896394956925609184L;

    
    public BigInteger getS();
}
