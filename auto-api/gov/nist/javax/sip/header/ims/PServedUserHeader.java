package gov.nist.javax.sip.header.ims;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;

public interface PServedUserHeader {

    public static final String NAME = "P-Served-User";

    public void setSessionCase(String sessionCase);

    public String getSessionCase();

    public void setRegistrationState(String registrationState);

    public String getRegistrationState();


}
