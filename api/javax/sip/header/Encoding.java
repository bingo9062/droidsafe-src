package javax.sip.header;

// Droidsafe Imports
import java.text.ParseException;

public interface Encoding {
    String getEncoding();
    void setEncoding(String encoding) throws ParseException;
}
