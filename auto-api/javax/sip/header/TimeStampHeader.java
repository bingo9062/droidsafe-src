package javax.sip.header;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import javax.sip.InvalidArgumentException;

public interface TimeStampHeader extends Header {
    String NAME = "Timestamp";

    float getDelay();
    void setDelay(float delay) throws InvalidArgumentException;
    boolean hasDelay();
    void removeDelay();

    long getTime();
    void setTime(long timeStamp) throws InvalidArgumentException;

    int getTimeDelay();
    void setTimeDelay(int delay) throws InvalidArgumentException;

    float getTimeStamp();
    void setTimeStamp(float timeStamp) throws InvalidArgumentException;
}
