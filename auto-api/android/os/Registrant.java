package android.os;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class Registrant {
    WeakReference   refH;
    int             what;
    Object          userObj;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.127 -0400", hash_original_method = "5F497FB9D04E2FF153E80911709BB474", hash_generated_method = "E635902800E61EFE2C97AA8F35F6DF79")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public Registrant(Handler h, int what, Object obj) {
        dsTaint.addTaint(what);
        dsTaint.addTaint(obj.dsTaint);
        dsTaint.addTaint(h.dsTaint);
        refH = new WeakReference(h);
        // ---------- Original Method ----------
        //refH = new WeakReference(h);
        //this.what = what;
        //userObj = obj;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.128 -0400", hash_original_method = "AD65744D6793AC8F89861EE1586F2E60", hash_generated_method = "0652DDD73EE83AEB0035EB645B57E65A")
    @DSModeled(DSC.SAFE)
    public void clear() {
        refH = null;
        userObj = null;
        // ---------- Original Method ----------
        //refH = null;
        //userObj = null;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.128 -0400", hash_original_method = "4409C699F4CAB16C2D504E283EFD3CDE", hash_generated_method = "28D035BF35B005A395534D365D96D57D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void notifyRegistrant() {
        internalNotifyRegistrant (null, null);
        // ---------- Original Method ----------
        //internalNotifyRegistrant (null, null);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.128 -0400", hash_original_method = "64830107363F59A49C8D9CD8F53D1153", hash_generated_method = "7F856A798E019CE407EFB849A97E660A")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void notifyResult(Object result) {
        dsTaint.addTaint(result.dsTaint);
        internalNotifyRegistrant (result, null);
        // ---------- Original Method ----------
        //internalNotifyRegistrant (result, null);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.128 -0400", hash_original_method = "3375894D9F21481D987FA847F34F65EF", hash_generated_method = "F07E467BC0D3B44C1BE01144F1826C82")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void notifyException(Throwable exception) {
        dsTaint.addTaint(exception.dsTaint);
        internalNotifyRegistrant (null, exception);
        // ---------- Original Method ----------
        //internalNotifyRegistrant (null, exception);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.129 -0400", hash_original_method = "5E7A4559085AA819B0F18D8B5740229E", hash_generated_method = "B9FCD7EC15B6CD6A976D241C2329FF4D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void notifyRegistrant(AsyncResult ar) {
        dsTaint.addTaint(ar.dsTaint);
        internalNotifyRegistrant (ar.result, ar.exception);
        // ---------- Original Method ----------
        //internalNotifyRegistrant (ar.result, ar.exception);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.129 -0400", hash_original_method = "8E93682DAE1125F8892DB75F842EE54A", hash_generated_method = "40A0998585464A636938873389B0F881")
    //DSFIXME:  CODE0002: Requires DSC value to be set
     void internalNotifyRegistrant(Object result, Throwable exception) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(exception.dsTaint);
        Handler h;
        h = getHandler();
        {
            clear();
        } //End block
        {
            Message msg;
            msg = Message.obtain();
            msg.what = what;
            msg.obj = new AsyncResult(userObj, result, exception);
            h.sendMessage(msg);
        } //End block
        // ---------- Original Method ----------
        //Handler h = getHandler();
        //if (h == null) {
            //clear();
        //} else {
            //Message msg = Message.obtain();
            //msg.what = what;
            //msg.obj = new AsyncResult(userObj, result, exception);
            //h.sendMessage(msg);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.129 -0400", hash_original_method = "D348E31FC7F90016E841E35B51E50760", hash_generated_method = "01530BFE8F1153892F537ADAB40C3246")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public Message messageForRegistrant() {
        Handler h;
        h = getHandler();
        {
            clear();
        } //End block
        {
            Message msg;
            msg = h.obtainMessage();
            msg.what = what;
            msg.obj = userObj;
        } //End block
        return (Message)dsTaint.getTaint();
        // ---------- Original Method ----------
        //Handler h = getHandler();
        //if (h == null) {
            //clear();
            //return null;
        //} else {
            //Message msg = h.obtainMessage();
            //msg.what = what;
            //msg.obj = userObj;
            //return msg;
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:54.130 -0400", hash_original_method = "7FEDB520AB82A5F00001E1B2C02807FF", hash_generated_method = "1F70B0D96BA03A32229C5084AFC16375")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public Handler getHandler() {
        Handler var771D64C76F812E732ABA60743AEA56AE_808129411 = ((Handler) refH.get());
        return (Handler)dsTaint.getTaint();
        // ---------- Original Method ----------
        //if (refH == null)
            //return null;
        //return (Handler) refH.get();
    }

    
}

