package org.xmlpull.v1;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;

public class XmlPullParserException extends Exception {
    protected Throwable detail;
    protected int row = -1;
    protected int column = -1;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:43.442 -0400", hash_original_method = "81C346207A813FD28C849879E7041B6E", hash_generated_method = "36F12EF6E025A3159A634C4665C3CC4E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public XmlPullParserException(String s) {
        super(s);
        dsTaint.addTaint(s);
        // ---------- Original Method ----------
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:43.442 -0400", hash_original_method = "28FAE29CB2E621B52838DE3161E10D49", hash_generated_method = "EEC82D8D0C6C8FAFAA00D162F7E44F1B")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public XmlPullParserException(String msg, XmlPullParser parser, Throwable chain) {
        super ((msg == null ? "" : msg+" ")
               + (parser == null ? "" : "(position:"+parser.getPositionDescription()+") ")
               + (chain == null ? "" : "caused by: "+chain));
        dsTaint.addTaint(chain.dsTaint);
        dsTaint.addTaint(parser.dsTaint);
        dsTaint.addTaint(msg);
        {
            this.row = parser.getLineNumber();
            this.column = parser.getColumnNumber();
        } //End block
        // ---------- Original Method ----------
        //if (parser != null) {
            //this.row = parser.getLineNumber();
            //this.column = parser.getColumnNumber();
        //}
        //this.detail = chain;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:43.442 -0400", hash_original_method = "94BA5080D380911C58CAC3733D4C69AF", hash_generated_method = "DEBAD014C99A0538921C2D076B9A47C8")
    @DSModeled(DSC.SAFE)
    public Throwable getDetail() {
        return (Throwable)dsTaint.getTaint();
        // ---------- Original Method ----------
        //return detail;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:43.442 -0400", hash_original_method = "0760FB4E162E5971E2E765B2D1FE0093", hash_generated_method = "CE2E861EB6244D7F2EC139C29F857F28")
    @DSModeled(DSC.SAFE)
    public int getLineNumber() {
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return row;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:43.442 -0400", hash_original_method = "52272B3AE5723072E22E93854866B927", hash_generated_method = "6E671AB4775078C336CCE1D5F67BE61F")
    @DSModeled(DSC.SAFE)
    public int getColumnNumber() {
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return column;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:43.442 -0400", hash_original_method = "5154476310B69FA819C717F88BC0BDF0", hash_generated_method = "BAFCA4B04999A280CFE0860DB3B9FE41")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void printStackTrace() {
        {
            super.printStackTrace();
        } //End block
        {
            {
                System.err.println(super.getMessage() + "; nested exception is:");
                detail.printStackTrace();
            } //End block
        } //End block
        // ---------- Original Method ----------
        //if (detail == null) {
            //super.printStackTrace();
        //} else {
            //synchronized(System.err) {
                //System.err.println(super.getMessage() + "; nested exception is:");
                //detail.printStackTrace();
            //}
        //}
    }

    
}

