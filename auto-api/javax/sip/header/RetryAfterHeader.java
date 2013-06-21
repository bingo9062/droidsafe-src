package javax.sip.header;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.text.ParseException;
import javax.sip.InvalidArgumentException;

public interface RetryAfterHeader extends Header, Parameters {
    String NAME = "Retry-After";

    String getComment();
    void setComment(String comment) throws ParseException;
    boolean hasComment();
    void removeComment();

    int getDuration();
    void setDuration(int duration) throws InvalidArgumentException;
    void removeDuration();

    int getRetryAfter();
    void setRetryAfter(int retryAfter) throws InvalidArgumentException;
}
