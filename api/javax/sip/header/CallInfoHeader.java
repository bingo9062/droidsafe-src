package javax.sip.header;

// Droidsafe Imports
import javax.sip.address.URI;

public interface CallInfoHeader extends Header, Parameters {
    String NAME = "Call-Info";

    URI getInfo();
    void setInfo(URI info);

    String getPurpose();
    void setPurpose(String purpose);
}
