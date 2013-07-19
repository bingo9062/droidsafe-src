package gov.nist.javax.sip.parser;

// Droidsafe Imports
import gov.nist.javax.sip.message.SIPMessage;

import java.text.ParseException;

public interface ParseExceptionListener {
    
    public void handleException(
        ParseException ex,
        SIPMessage sipMessage,
        Class headerClass,
        String headerText,
        String messageText)
        throws ParseException;
}
