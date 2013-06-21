package javax.sip.header;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.text.ParseException;

public interface OrganizationHeader extends Header {
    String NAME = "Organization";

    String getOrganization();
    void setOrganization(String organization) throws ParseException;
}
