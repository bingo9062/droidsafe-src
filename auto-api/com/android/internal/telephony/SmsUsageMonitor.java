package com.android.internal.telephony;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import android.content.ContentResolver;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SmsUsageMonitor {
    private int mCheckPeriod;
    private int mMaxAllowed;
    private HashMap<String, ArrayList<Long>> mSmsStamp =
            new HashMap<String, ArrayList<Long>>();
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.537 -0400", hash_original_method = "AA58276D63D2163182FD805D073EE67C", hash_generated_method = "DC4CB3C208476984B8DFDF0885903535")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public SmsUsageMonitor(ContentResolver resolver) {
        dsTaint.addTaint(resolver.dsTaint);
        mMaxAllowed = Settings.Secure.getInt(resolver,
                Settings.Secure.SMS_OUTGOING_CHECK_MAX_COUNT,
                DEFAULT_SMS_MAX_COUNT);
        mCheckPeriod = Settings.Secure.getInt(resolver,
                Settings.Secure.SMS_OUTGOING_CHECK_INTERVAL_MS,
                DEFAULT_SMS_CHECK_PERIOD);
        // ---------- Original Method ----------
        //mMaxAllowed = Settings.Secure.getInt(resolver,
                //Settings.Secure.SMS_OUTGOING_CHECK_MAX_COUNT,
                //DEFAULT_SMS_MAX_COUNT);
        //mCheckPeriod = Settings.Secure.getInt(resolver,
                //Settings.Secure.SMS_OUTGOING_CHECK_INTERVAL_MS,
                //DEFAULT_SMS_CHECK_PERIOD);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.538 -0400", hash_original_method = "7B2C29F4698952FF45E2699BBB5DEB62", hash_generated_method = "F5020B67D30B77B9A0B8E7C878C54BF3")
    //DSFIXME:  CODE0002: Requires DSC value to be set
     void dispose() {
        mSmsStamp.clear();
        // ---------- Original Method ----------
        //mSmsStamp.clear();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.539 -0400", hash_original_method = "EA1BFE3566BC11C4D089155AC6363183", hash_generated_method = "AB6406B8DDE6D3794387493C28465C78")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public boolean check(String appName, int smsWaiting) {
        dsTaint.addTaint(appName);
        dsTaint.addTaint(smsWaiting);
        {
            removeExpiredTimestamps();
            ArrayList<Long> sentList;
            sentList = mSmsStamp.get(appName);
            {
                sentList = new ArrayList<Long>();
                mSmsStamp.put(appName, sentList);
            } //End block
            boolean var091A67795636C5B7E20E47F6F889C23B_1365297283 = (isUnderLimit(sentList, smsWaiting));
        } //End block
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        //synchronized (mSmsStamp) {
            //removeExpiredTimestamps();
            //ArrayList<Long> sentList = mSmsStamp.get(appName);
            //if (sentList == null) {
                //sentList = new ArrayList<Long>();
                //mSmsStamp.put(appName, sentList);
            //}
            //return isUnderLimit(sentList, smsWaiting);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.539 -0400", hash_original_method = "0C89C3F43D481EBAFC32AEEAE6183BD3", hash_generated_method = "635D2BEF4283F1572E9A114FED3BD3FA")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void removeExpiredTimestamps() {
        long beginCheckPeriod;
        beginCheckPeriod = System.currentTimeMillis() - mCheckPeriod;
        {
            Iterator<Map.Entry<String, ArrayList<Long>>> iter;
            iter = mSmsStamp.entrySet().iterator();
            {
                boolean var8492AE2C695A56B730381A28B8BA3F6D_1467419288 = (iter.hasNext());
                {
                    Map.Entry<String, ArrayList<Long>> entry;
                    entry = iter.next();
                    ArrayList<Long> oldList;
                    oldList = entry.getValue();
                    {
                        boolean var6B058BCE74663B8CCC2D65D7403CD832_965930922 = (oldList.isEmpty() || oldList.get(oldList.size() - 1) < beginCheckPeriod);
                        {
                            iter.remove();
                        } //End block
                    } //End collapsed parenthetic
                } //End block
            } //End collapsed parenthetic
        } //End block
        // ---------- Original Method ----------
        //long beginCheckPeriod = System.currentTimeMillis() - mCheckPeriod;
        //synchronized (mSmsStamp) {
            //Iterator<Map.Entry<String, ArrayList<Long>>> iter = mSmsStamp.entrySet().iterator();
            //while (iter.hasNext()) {
                //Map.Entry<String, ArrayList<Long>> entry = iter.next();
                //ArrayList<Long> oldList = entry.getValue();
                //if (oldList.isEmpty() || oldList.get(oldList.size() - 1) < beginCheckPeriod) {
                    //iter.remove();
                //}
            //}
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.540 -0400", hash_original_method = "FED252455D0F59F9866280B84F72F407", hash_generated_method = "A99C8C8216E04F1E3C6256B9BE0F02A0")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private boolean isUnderLimit(ArrayList<Long> sent, int smsWaiting) {
        dsTaint.addTaint(sent.dsTaint);
        dsTaint.addTaint(smsWaiting);
        Long ct;
        ct = System.currentTimeMillis();
        long beginCheckPeriod;
        beginCheckPeriod = ct - mCheckPeriod;
        Log.d(TAG, "SMS send size=" + sent.size() + " time=" + ct);
        {
            boolean var5667D581C2F5DFCA02DEFCCDFE1CFB02_1898628633 = (!sent.isEmpty() && sent.get(0) < beginCheckPeriod);
            {
                sent.remove(0);
            } //End block
        } //End collapsed parenthetic
        {
            boolean varDFD6A4FA9327973346539BB265A37305_1670273906 = ((sent.size() + smsWaiting) <= mMaxAllowed);
            {
                {
                    int i;
                    i = 0;
                    {
                        sent.add(ct);
                    } //End block
                } //End collapsed parenthetic
            } //End block
        } //End collapsed parenthetic
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        //Long ct = System.currentTimeMillis();
        //long beginCheckPeriod = ct - mCheckPeriod;
        //Log.d(TAG, "SMS send size=" + sent.size() + " time=" + ct);
        //while (!sent.isEmpty() && sent.get(0) < beginCheckPeriod) {
            //sent.remove(0);
        //}
        //if ((sent.size() + smsWaiting) <= mMaxAllowed) {
            //for (int i = 0; i < smsWaiting; i++ ) {
                //sent.add(ct);
            //}
            //return true;
        //}
        //return false;
    }

    
    private static final String TAG = "SmsStorageMonitor";
    private static final int DEFAULT_SMS_CHECK_PERIOD = 3600000;
    private static final int DEFAULT_SMS_MAX_COUNT = 100;
}

