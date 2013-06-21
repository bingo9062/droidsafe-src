package com.android.internal.telephony;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import static com.android.internal.telephony.RILConstants.*;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UNKNOWN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSUPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.os.PowerManager.WakeLock;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.gsm.SmsBroadcastConfigInfo;
import com.android.internal.telephony.gsm.SuppServiceNotification;
import com.android.internal.telephony.cdma.CdmaCallWaitingNotification;
import com.android.internal.telephony.cdma.CdmaInformationRecords;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

class RILRequest {
    int mSerial;
    int mRequest;
    Message mResult;
    Parcel mp;
    RILRequest mNext;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.030 -0400", hash_original_method = "9C4E8F6E2A10EF2C06F20638ED2CBC6D", hash_generated_method = "560681FC445F3861C142757487DA3B91")
    @DSModeled(DSC.SAFE)
    private RILRequest() {
        // ---------- Original Method ----------
    }

    
        static RILRequest obtain(int request, Message result) {
        RILRequest rr = null;
        synchronized(sPoolSync) {
            if (sPool != null) {
                rr = sPool;
                sPool = rr.mNext;
                rr.mNext = null;
                sPoolSize--;
            }
        }
        if (rr == null) {
            rr = new RILRequest();
        }
        synchronized(sSerialMonitor) {
            rr.mSerial = sNextSerial++;
        }
        rr.mRequest = request;
        rr.mResult = result;
        rr.mp = Parcel.obtain();
        if (result != null && result.getTarget() == null) {
            throw new NullPointerException("Message target must not be null");
        }
        rr.mp.writeInt(request);
        rr.mp.writeInt(rr.mSerial);
        return rr;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.030 -0400", hash_original_method = "E7EE736E24A2BE53AC107C249FFB0880", hash_generated_method = "2CC4C29F04564704D254356C451809E3")
    //DSFIXME:  CODE0002: Requires DSC value to be set
     void release() {
        {
            {
                this.mNext = sPool;
                sPool = this;
                mResult = null;
            } //End block
        } //End block
        // ---------- Original Method ----------
        //synchronized (sPoolSync) {
            //if (sPoolSize < MAX_POOL_SIZE) {
                //this.mNext = sPool;
                //sPool = this;
                //sPoolSize++;
                //mResult = null;
            //}
        //}
    }

    
        static void resetSerial() {
        synchronized(sSerialMonitor) {
            sNextSerial = 0;
        }
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.031 -0400", hash_original_method = "9C13ECBB4C9B57CD7FC06025C6091C8F", hash_generated_method = "AE50C07F1077D683EFE400E26F012651")
    //DSFIXME:  CODE0002: Requires DSC value to be set
     String serialString() {
        StringBuilder sb;
        sb = new StringBuilder(8);
        String sn;
        sn = Integer.toString(mSerial);
        sb.append('[');
        {
            int i, s;
            i = 0;
            s = sn.length();
            {
                sb.append('0');
            } //End block
        } //End collapsed parenthetic
        sb.append(sn);
        sb.append(']');
        String var806458D832AB974D230FEE4CBBDBD390_395617987 = (sb.toString());
        return dsTaint.getTaintString();
        // ---------- Original Method ----------
        //StringBuilder sb = new StringBuilder(8);
        //String sn;
        //sn = Integer.toString(mSerial);
        //sb.append('[');
        //for (int i = 0, s = sn.length() ; i < 4 - s; i++) {
            //sb.append('0');
        //}
        //sb.append(sn);
        //sb.append(']');
        //return sb.toString();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.031 -0400", hash_original_method = "DAF6125A6A3BAA8EA4AAF17920DB80E6", hash_generated_method = "D6B5C2F4DCE4FDF567ADF3576F62B807")
    //DSFIXME:  CODE0002: Requires DSC value to be set
     void onError(int error, Object ret) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        dsTaint.addTaint(ret.dsTaint);
        dsTaint.addTaint(error);
        CommandException ex;
        ex = CommandException.fromRilErrno(error);
        Log.d(LOG_TAG, serialString() + "< "
            + RIL.requestToString(mRequest)
            + " error: " + ex);
        {
            AsyncResult.forMessage(mResult, ret, ex);
            mResult.sendToTarget();
        } //End block
        {
            mp.recycle();
            mp = null;
        } //End block
        // ---------- Original Method ----------
        //CommandException ex;
        //ex = CommandException.fromRilErrno(error);
        //if (RIL.RILJ_LOGD) Log.d(LOG_TAG, serialString() + "< "
            //+ RIL.requestToString(mRequest)
            //+ " error: " + ex);
        //if (mResult != null) {
            //AsyncResult.forMessage(mResult, ret, ex);
            //mResult.sendToTarget();
        //}
        //if (mp != null) {
            //mp.recycle();
            //mp = null;
        //}
    }

    
    static final String LOG_TAG = "RILJ";
    static int sNextSerial = 0;
    static Object sSerialMonitor = new Object();
    private static Object sPoolSync = new Object();
    private static RILRequest sPool = null;
    private static int sPoolSize = 0;
    private static final int MAX_POOL_SIZE = 4;
}

public final class RIL extends BaseCommands implements CommandsInterface {
    LocalSocket mSocket;
    HandlerThread mSenderThread;
    RILSender mSender;
    Thread mReceiverThread;
    RILReceiver mReceiver;
    WakeLock mWakeLock;
    int mWakeLockTimeout;
    int mRequestMessagesPending;
    int mRequestMessagesWaiting;
    ArrayList<RILRequest> mRequestsList = new ArrayList<RILRequest>();
    Object     mLastNITZTimeInfo;
    BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.031 -0400", hash_original_method = "2E7815AD99D182A9CFAEBA30A64F6122", hash_generated_method = "23564A6F6266496DBEA41F43528F0488")
        @DSModeled(DSC.SPEC)
        @Override
        public void onReceive(Context context, Intent intent) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            dsTaint.addTaint(context.dsTaint);
            dsTaint.addTaint(intent.dsTaint);
            {
                boolean var8C358EA74D49A7EAFB8AA331D6B03438_94087495 = (intent.getAction().equals(Intent.ACTION_SCREEN_ON));
                {
                    sendScreenState(true);
                } //End block
                {
                    boolean varD09851821BF78B9C6FDD532B0303A63D_1769449973 = (intent.getAction().equals(Intent.ACTION_SCREEN_OFF));
                    {
                        sendScreenState(false);
                    } //End block
                } //End collapsed parenthetic
            } //End collapsed parenthetic
            // ---------- Original Method ----------
            //if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                //sendScreenState(true);
            //} else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                //sendScreenState(false);
            //} else {
                //Log.w(LOG_TAG, "RIL received unexpected Intent: " + intent.getAction());
            //}
        }

        
}; //Transformed anonymous class
    private int mSetPreferredNetworkType;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.032 -0400", hash_original_method = "57415555D3C32D9CF1C37CD62128114E", hash_generated_method = "21E9E9C8139475BFA68819EFE3C5E8CB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public RIL(Context context, int preferredNetworkType, int cdmaSubscription) {
        super(context);
        dsTaint.addTaint(preferredNetworkType);
        dsTaint.addTaint(context.dsTaint);
        dsTaint.addTaint(cdmaSubscription);
        {
            riljLog("RIL(context, preferredNetworkType=" + preferredNetworkType +
                    " cdmaSubscription=" + cdmaSubscription + ")");
        } //End block
        mCdmaSubscription  = cdmaSubscription;
        mPreferredNetworkType = preferredNetworkType;
        mPhoneType = RILConstants.NO_PHONE;
        PowerManager pm;
        pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, LOG_TAG);
        mWakeLock.setReferenceCounted(false);
        mWakeLockTimeout = SystemProperties.getInt(TelephonyProperties.PROPERTY_WAKE_LOCK_TIMEOUT,
                DEFAULT_WAKE_LOCK_TIMEOUT);
        mRequestMessagesPending = 0;
        mRequestMessagesWaiting = 0;
        mSenderThread = new HandlerThread("RILSender");
        mSenderThread.start();
        Looper looper;
        looper = mSenderThread.getLooper();
        mSender = new RILSender(looper);
        ConnectivityManager cm;
        cm = (ConnectivityManager)context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        {
            boolean varD774D9E55CA6214CD6B06FC22CB353B1_690542259 = (cm.isNetworkSupported(ConnectivityManager.TYPE_MOBILE) == false);
            {
                riljLog("Not starting RILReceiver: wifi-only");
            } //End block
            {
                riljLog("Starting RILReceiver");
                mReceiver = new RILReceiver();
                mReceiverThread = new Thread(mReceiver, "RILReceiver");
                mReceiverThread.start();
                IntentFilter filter;
                filter = new IntentFilter();
                filter.addAction(Intent.ACTION_SCREEN_ON);
                filter.addAction(Intent.ACTION_SCREEN_OFF);
                context.registerReceiver(mIntentReceiver, filter);
            } //End block
        } //End collapsed parenthetic
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        private static int readRilMessage(InputStream is, byte[] buffer) throws IOException {
        int countRead;
        int offset;
        int remaining;
        int messageLength;
        offset = 0;
        remaining = 4;
        do {
            countRead = is.read(buffer, offset, remaining);
            if (countRead < 0 ) {
                Log.e(LOG_TAG, "Hit EOS reading message length");
                return -1;
            }
            offset += countRead;
            remaining -= countRead;
        } while (remaining > 0);
        messageLength = ((buffer[0] & 0xff) << 24)
                | ((buffer[1] & 0xff) << 16)
                | ((buffer[2] & 0xff) << 8)
                | (buffer[3] & 0xff);
        offset = 0;
        remaining = messageLength;
        do {
            countRead = is.read(buffer, offset, remaining);
            if (countRead < 0 ) {
                Log.e(LOG_TAG, "Hit EOS reading message.  messageLength=" + messageLength
                        + " remaining=" + remaining);
                return -1;
            }
            offset += countRead;
            remaining -= countRead;
        } while (remaining > 0);
        return messageLength;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.032 -0400", hash_original_method = "9505EC70575F3F081B82639224A26C6E", hash_generated_method = "3E72664476C039D0D41A5F8552DE8414")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void setOnNITZTime(Handler h, int what, Object obj) {
        dsTaint.addTaint(what);
        dsTaint.addTaint(obj.dsTaint);
        dsTaint.addTaint(h.dsTaint);
        super.setOnNITZTime(h, what, obj);
        {
            mNITZTimeRegistrant
                .notifyRegistrant(
                    new AsyncResult (null, mLastNITZTimeInfo, null));
            mLastNITZTimeInfo = null;
        } //End block
        // ---------- Original Method ----------
        //super.setOnNITZTime(h, what, obj);
        //if (mLastNITZTimeInfo != null) {
            //mNITZTimeRegistrant
                //.notifyRegistrant(
                    //new AsyncResult (null, mLastNITZTimeInfo, null));
            //mLastNITZTimeInfo = null;
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.033 -0400", hash_original_method = "9A7EB00CD2404F3C288A75EFD9E4A220", hash_generated_method = "02CBE8BDEE2A4C132DEF9CEFAEE81703")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getIccCardStatus(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_SIM_STATUS, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GET_SIM_STATUS, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.033 -0400", hash_original_method = "A64490CEA7617DBF7C16959DD443F320", hash_generated_method = "DC9A8D1BA83E1D9C22E8C78CF35C39D6")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPin(String pin, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(pin);
        supplyIccPinForApp(pin, null, result);
        // ---------- Original Method ----------
        //supplyIccPinForApp(pin, null, result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.033 -0400", hash_original_method = "292CEACE366769BDA48EB6300D49BD27", hash_generated_method = "79F1DAA0392696E61B4E92B1363C3457")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPinForApp(String pin, String aid, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(pin);
        dsTaint.addTaint(aid);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PIN, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(2);
        rr.mp.writeString(pin);
        rr.mp.writeString(aid);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PIN, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(2);
        //rr.mp.writeString(pin);
        //rr.mp.writeString(aid);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.033 -0400", hash_original_method = "EDD0A15F8BC2322B300440A8148B03FB", hash_generated_method = "169A8F28694063AB347E6B6C5E3E28EE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPuk(String puk, String newPin, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(puk);
        dsTaint.addTaint(newPin);
        supplyIccPukForApp(puk, newPin, null, result);
        // ---------- Original Method ----------
        //supplyIccPukForApp(puk, newPin, null, result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.034 -0400", hash_original_method = "1780B25BC715F62B7E7B76DC76BDB7F8", hash_generated_method = "1CCB90EC4C904E5790179562E351EDF7")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPukForApp(String puk, String newPin, String aid, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(puk);
        dsTaint.addTaint(aid);
        dsTaint.addTaint(newPin);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PUK, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(3);
        rr.mp.writeString(puk);
        rr.mp.writeString(newPin);
        rr.mp.writeString(aid);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PUK, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(3);
        //rr.mp.writeString(puk);
        //rr.mp.writeString(newPin);
        //rr.mp.writeString(aid);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.034 -0400", hash_original_method = "8076AE95CA0491033B7A3B1FDB27A00D", hash_generated_method = "9ED9A129DA15AAD8B32629BB6C9C5CE7")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPin2(String pin, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(pin);
        supplyIccPin2ForApp(pin, null, result);
        // ---------- Original Method ----------
        //supplyIccPin2ForApp(pin, null, result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.034 -0400", hash_original_method = "2364DF46EB4F312709574B2C726226B0", hash_generated_method = "B57EE26AF60A129627BA23B286ECE20C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPin2ForApp(String pin, String aid, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(pin);
        dsTaint.addTaint(aid);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PIN2, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(2);
        rr.mp.writeString(pin);
        rr.mp.writeString(aid);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PIN2, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(2);
        //rr.mp.writeString(pin);
        //rr.mp.writeString(aid);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.034 -0400", hash_original_method = "02C50C8BAA9C120A4383EA48E4CE9EF9", hash_generated_method = "03DD256467BE543390208E75CE04E24E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPuk2(String puk2, String newPin2, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(puk2);
        dsTaint.addTaint(newPin2);
        supplyIccPuk2ForApp(puk2, newPin2, null, result);
        // ---------- Original Method ----------
        //supplyIccPuk2ForApp(puk2, newPin2, null, result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.035 -0400", hash_original_method = "16881D0ACCF987B6F55F1477C7A8AF2E", hash_generated_method = "6D1BDC26B9443A6F3B9C49F67E0B2B42")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void supplyIccPuk2ForApp(String puk, String newPin2, String aid, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(newPin2);
        dsTaint.addTaint(puk);
        dsTaint.addTaint(aid);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PUK2, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(3);
        rr.mp.writeString(puk);
        rr.mp.writeString(newPin2);
        rr.mp.writeString(aid);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PUK2, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(3);
        //rr.mp.writeString(puk);
        //rr.mp.writeString(newPin2);
        //rr.mp.writeString(aid);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.035 -0400", hash_original_method = "E5801ABF4ED976E8B47A9DC4E0EF9FFC", hash_generated_method = "311A68F9DABFBB0922DDE7EE6FBEEEF6")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void changeIccPin(String oldPin, String newPin, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(oldPin);
        dsTaint.addTaint(newPin);
        changeIccPinForApp(oldPin, newPin, null, result);
        // ---------- Original Method ----------
        //changeIccPinForApp(oldPin, newPin, null, result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.035 -0400", hash_original_method = "FB6D60EEFAF19AC3A68522374AFE5A34", hash_generated_method = "9453B3B26FB7EEE2831FB372B449B011")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void changeIccPinForApp(String oldPin, String newPin, String aid, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(aid);
        dsTaint.addTaint(oldPin);
        dsTaint.addTaint(newPin);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CHANGE_SIM_PIN, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(3);
        rr.mp.writeString(oldPin);
        rr.mp.writeString(newPin);
        rr.mp.writeString(aid);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CHANGE_SIM_PIN, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(3);
        //rr.mp.writeString(oldPin);
        //rr.mp.writeString(newPin);
        //rr.mp.writeString(aid);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.035 -0400", hash_original_method = "09C1A469DD89C445C72DC064DAF532C6", hash_generated_method = "7596E2D0915F22258ACF76C73AFDFCC8")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void changeIccPin2(String oldPin2, String newPin2, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(newPin2);
        dsTaint.addTaint(oldPin2);
        changeIccPin2ForApp(oldPin2, newPin2, null, result);
        // ---------- Original Method ----------
        //changeIccPin2ForApp(oldPin2, newPin2, null, result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.036 -0400", hash_original_method = "5CD184F5005892F6A2376DA12B8940E7", hash_generated_method = "7B22699E67065145F5D8A86FC05D77AD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void changeIccPin2ForApp(String oldPin2, String newPin2, String aid, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(newPin2);
        dsTaint.addTaint(aid);
        dsTaint.addTaint(oldPin2);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CHANGE_SIM_PIN2, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(3);
        rr.mp.writeString(oldPin2);
        rr.mp.writeString(newPin2);
        rr.mp.writeString(aid);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CHANGE_SIM_PIN2, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(3);
        //rr.mp.writeString(oldPin2);
        //rr.mp.writeString(newPin2);
        //rr.mp.writeString(aid);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.036 -0400", hash_original_method = "DCA3B69D42E061A95FB4470FAF19888E", hash_generated_method = "54FCFC902738D5D1F49904C548D03275")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void changeBarringPassword(String facility, String oldPwd, String newPwd, Message result) {
        dsTaint.addTaint(newPwd);
        dsTaint.addTaint(oldPwd);
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(facility);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CHANGE_BARRING_PASSWORD, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(3);
        rr.mp.writeString(facility);
        rr.mp.writeString(oldPwd);
        rr.mp.writeString(newPwd);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CHANGE_BARRING_PASSWORD, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(3);
        //rr.mp.writeString(facility);
        //rr.mp.writeString(oldPwd);
        //rr.mp.writeString(newPwd);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.036 -0400", hash_original_method = "FD9166E53C98E732567F842FC6265121", hash_generated_method = "D899D459C8E7707E0321D55381F5B311")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void supplyNetworkDepersonalization(String netpin, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(netpin);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ENTER_NETWORK_DEPERSONALIZATION, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(1);
        rr.mp.writeString(netpin);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_NETWORK_DEPERSONALIZATION, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(1);
        //rr.mp.writeString(netpin);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.036 -0400", hash_original_method = "6F2937ACBD34EE8D8FEF57D35663AFCC", hash_generated_method = "27542A38695F627A78F1A4D9CAAF9478")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getCurrentCalls(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_CURRENT_CALLS, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GET_CURRENT_CALLS, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.037 -0400", hash_original_method = "A1D311E0B227CABB8D0707A1650659D2", hash_generated_method = "05EA635D6C5CCAE39971D93F7444FB4C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Deprecated
    public void getPDPContextList(Message result) {
        dsTaint.addTaint(result.dsTaint);
        getDataCallList(result);
        // ---------- Original Method ----------
        //getDataCallList(result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.037 -0400", hash_original_method = "68CF653DB375A3C63D89D09DBB57244C", hash_generated_method = "A48A948F788234E64432AEE9A3096C98")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getDataCallList(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DATA_CALL_LIST, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_DATA_CALL_LIST, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.037 -0400", hash_original_method = "85029AD0E77EFB7E7AAB9535BAB079D4", hash_generated_method = "0842B10E11569D079B7D6D23934A175D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void dial(String address, int clirMode, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(address);
        dsTaint.addTaint(clirMode);
        dial(address, clirMode, null, result);
        // ---------- Original Method ----------
        //dial(address, clirMode, null, result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.037 -0400", hash_original_method = "2937351315DD22A0DBB0EBEECB6FBD52", hash_generated_method = "093CCD33F7ABA6AA1F82939623D54C48")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void dial(String address, int clirMode, UUSInfo uusInfo, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(uusInfo.dsTaint);
        dsTaint.addTaint(address);
        dsTaint.addTaint(clirMode);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DIAL, result);
        rr.mp.writeString(address);
        rr.mp.writeInt(clirMode);
        rr.mp.writeInt(0);
        {
            rr.mp.writeInt(0);
        } //End block
        {
            rr.mp.writeInt(1);
            rr.mp.writeInt(uusInfo.getType());
            rr.mp.writeInt(uusInfo.getDcs());
            rr.mp.writeByteArray(uusInfo.getUserData());
        } //End block
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_DIAL, result);
        //rr.mp.writeString(address);
        //rr.mp.writeInt(clirMode);
        //rr.mp.writeInt(0);
        //if (uusInfo == null) {
            //rr.mp.writeInt(0); 
        //} else {
            //rr.mp.writeInt(1); 
            //rr.mp.writeInt(uusInfo.getType());
            //rr.mp.writeInt(uusInfo.getDcs());
            //rr.mp.writeByteArray(uusInfo.getUserData());
        //}
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.038 -0400", hash_original_method = "DCABA764531B1065D0038AAAD031BA27", hash_generated_method = "5B2D7F22F14E5E0E2D182C5D04F6EC48")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getIMSI(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_IMSI, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GET_IMSI, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.038 -0400", hash_original_method = "4D0F2CCF9960CC448C13E6CB3F3B5DE4", hash_generated_method = "6B8145F8D45F2099AE26F202FFB22667")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getIMEI(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_IMEI, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GET_IMEI, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.038 -0400", hash_original_method = "149F0748B48493B8EA1EFEBB8389549A", hash_generated_method = "019B77DFD3AEFDDAC36C1D9092DFFB89")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getIMEISV(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_IMEISV, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GET_IMEISV, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.038 -0400", hash_original_method = "7905AFC17F454606777953D509A8D53A", hash_generated_method = "04D6E1B335E55296AFAEDB45742013EE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void hangupConnection(int gsmIndex, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(gsmIndex);
        riljLog("hangupConnection: gsmIndex=" + gsmIndex);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_HANGUP, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest) + " " +
                gsmIndex);
        rr.mp.writeInt(1);
        rr.mp.writeInt(gsmIndex);
        send(rr);
        // ---------- Original Method ----------
        //if (RILJ_LOGD) riljLog("hangupConnection: gsmIndex=" + gsmIndex);
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_HANGUP, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest) + " " +
                //gsmIndex);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(gsmIndex);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.039 -0400", hash_original_method = "85440FD9AE077A395C6F94573FB9B35A", hash_generated_method = "1D3DB2E612AD1469F9557F575A2E61D0")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void hangupWaitingOrBackground(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND,
                                        result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND,
                                        //result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.039 -0400", hash_original_method = "7CE301AE0A34FEB3B57BD0350A2D5E6A", hash_generated_method = "D841F16FAB640D58952D2690148FE3CD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void hangupForegroundResumeBackground(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(
                        RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND,
                                        result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(
                        //RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND,
                                        //result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.039 -0400", hash_original_method = "D308C1E0C4F8D94B5EF5B2D9D0D36F66", hash_generated_method = "26885C5E939FD505F06D810D385A490B")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void switchWaitingOrHoldingAndActive(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(
                        RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE,
                                        result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(
                        //RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE,
                                        //result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.039 -0400", hash_original_method = "E472C7A7FA5FA1FA68AAF1D140E0BD67", hash_generated_method = "33D27AC3CA579BDEFDC8DD227523A9AE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void conference(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CONFERENCE, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_CONFERENCE, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.041 -0400", hash_original_method = "B803BD13961A7A2F16F3119AE2CBAC6F", hash_generated_method = "2E39FF21B43FD44AE77D0206B0D415EA")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setPreferredVoicePrivacy(boolean enable, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(enable);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE,
                result);
        rr.mp.writeInt(1);
        rr.mp.writeInt(enable ? 1:0);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE,
                //result);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(enable ? 1:0);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.041 -0400", hash_original_method = "3E434E6C0C8E17A9191C0565807B19D8", hash_generated_method = "62FBC4AA2BAE05AAB6ACF9D2C86332F3")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getPreferredVoicePrivacy(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE,
                result);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE,
                //result);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.041 -0400", hash_original_method = "4B8D613904DEDF72212F0EB9A1BF349B", hash_generated_method = "31AD7D13EA3DDDC18A6F7BEE359042EF")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void separateConnection(int gsmIndex, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(gsmIndex);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SEPARATE_CONNECTION, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                            + " " + gsmIndex);
        rr.mp.writeInt(1);
        rr.mp.writeInt(gsmIndex);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SEPARATE_CONNECTION, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                            //+ " " + gsmIndex);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(gsmIndex);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.042 -0400", hash_original_method = "8CB5CF57551B01694681D1FE4B325AA7", hash_generated_method = "8DC013E3E13A9CFD2BDE047D73CC7A43")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void acceptCall(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ANSWER, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_ANSWER, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.042 -0400", hash_original_method = "2E92E83F2C082778F7F377F259918641", hash_generated_method = "66A9E235D4DAF4F007243E18193BDF9C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void rejectCall(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_UDUB, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_UDUB, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.042 -0400", hash_original_method = "4490A3E422842C7946255FFDE06D02C6", hash_generated_method = "2502692D616B1668407C091F37D5A62A")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void explicitCallTransfer(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_EXPLICIT_CALL_TRANSFER, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_EXPLICIT_CALL_TRANSFER, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.042 -0400", hash_original_method = "286D2CDF105911EDAA78CDFFAADE2FB4", hash_generated_method = "EE94E426207BBC3D28F635400B9B8531")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getLastCallFailCause(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_LAST_CALL_FAIL_CAUSE, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_LAST_CALL_FAIL_CAUSE, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.043 -0400", hash_original_method = "E839391FA5F4E8371F0D2466E996CFDA", hash_generated_method = "6AC0ED2B9F49C21F9ABCC502E4817A10")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getLastPdpFailCause(Message result) {
        dsTaint.addTaint(result.dsTaint);
        getLastDataCallFailCause (result);
        // ---------- Original Method ----------
        //getLastDataCallFailCause (result);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.043 -0400", hash_original_method = "367089EF39FE579C6BCEE97FCB3E914B", hash_generated_method = "08F1E475260EAB7137B67CB47D7C11B6")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getLastDataCallFailCause(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.043 -0400", hash_original_method = "350AC1F658DA4E0097EF1D14A3117454", hash_generated_method = "A9E5E77109987A54457D1A66C5EECB32")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setMute(boolean enableMute, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(enableMute);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_MUTE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                            + " " + enableMute);
        rr.mp.writeInt(1);
        rr.mp.writeInt(enableMute ? 1 : 0);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_MUTE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                            //+ " " + enableMute);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(enableMute ? 1 : 0);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.043 -0400", hash_original_method = "B42AC6EC10626260A88AA941D9E9F704", hash_generated_method = "9CE4053EBC52B51A8ADBC61323D5E33E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getMute(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_MUTE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_GET_MUTE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.043 -0400", hash_original_method = "16BB78D78D9B012F64B70344D11CBBD0", hash_generated_method = "55B61486587D702FD25DEA8360E8F44F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getSignalStrength(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SIGNAL_STRENGTH, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SIGNAL_STRENGTH, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.044 -0400", hash_original_method = "CD1D773750E577C45A4986B8D31A3404", hash_generated_method = "6A3A4D77C8CC7B8EFAE2032A686C2E87")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getVoiceRegistrationState(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_VOICE_REGISTRATION_STATE, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_VOICE_REGISTRATION_STATE, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.044 -0400", hash_original_method = "A42E908B0E0EAE7E9677D8B8370610F9", hash_generated_method = "2A2792FE74E6A2D6823ED3C9974DA2FE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getDataRegistrationState(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DATA_REGISTRATION_STATE, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_DATA_REGISTRATION_STATE, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.044 -0400", hash_original_method = "F3EF4D956FA5FB9BDF6C62381BC62F41", hash_generated_method = "D17C17EC79A4C14BFDFDFF52151327B9")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getOperator(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_OPERATOR, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_OPERATOR, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.044 -0400", hash_original_method = "3D70E9C0BFE2429BE8FC0BF7A1D59B4A", hash_generated_method = "CB9921411F85B972E67A88835A734597")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendDtmf(char c, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(c);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DTMF, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeString(Character.toString(c));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_DTMF, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeString(Character.toString(c));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.045 -0400", hash_original_method = "BAA741C14C9F73BEA8C72D4011D64DC0", hash_generated_method = "4C787C30337DD3B52238B424E3B86AF9")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void startDtmf(char c, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(c);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DTMF_START, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeString(Character.toString(c));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_DTMF_START, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeString(Character.toString(c));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.045 -0400", hash_original_method = "C4D329464DB2A188FDA1049FC1B5C820", hash_generated_method = "518320E2156FEE883F1AEBBAC7A88737")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void stopDtmf(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DTMF_STOP, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_DTMF_STOP, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.046 -0400", hash_original_method = "AF9E26B6734BDEC93AE44BA0DF8E2C9A", hash_generated_method = "39BD49878C7B0D0E328508E30E7B7661")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendBurstDtmf(String dtmfString, int on, int off, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(off);
        dsTaint.addTaint(on);
        dsTaint.addTaint(dtmfString);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_BURST_DTMF, result);
        rr.mp.writeInt(3);
        rr.mp.writeString(dtmfString);
        rr.mp.writeString(Integer.toString(on));
        rr.mp.writeString(Integer.toString(off));
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " : " + dtmfString);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_BURST_DTMF, result);
        //rr.mp.writeInt(3);
        //rr.mp.writeString(dtmfString);
        //rr.mp.writeString(Integer.toString(on));
        //rr.mp.writeString(Integer.toString(off));
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " : " + dtmfString);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.047 -0400", hash_original_method = "6216EB8701D12EFA1EABB572C0722209", hash_generated_method = "91963146266D7BA756C7CECA2B7B5DBB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendSMS(String smscPDU, String pdu, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(pdu);
        dsTaint.addTaint(smscPDU);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SEND_SMS, result);
        rr.mp.writeInt(2);
        rr.mp.writeString(smscPDU);
        rr.mp.writeString(pdu);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SEND_SMS, result);
        //rr.mp.writeInt(2);
        //rr.mp.writeString(smscPDU);
        //rr.mp.writeString(pdu);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.048 -0400", hash_original_method = "B6146A457115D22EC1FC450C9E047949", hash_generated_method = "4B8B880F1C8E647A9E808BF5137A429A")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendCdmaSms(byte[] pdu, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(pdu[0]);
        int address_nbr_of_digits;
        int subaddr_nbr_of_digits;
        int bearerDataLength;
        ByteArrayInputStream bais;
        bais = new ByteArrayInputStream(pdu);
        DataInputStream dis;
        dis = new DataInputStream(bais);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_SEND_SMS, result);
        try 
        {
            rr.mp.writeInt(dis.readInt());
            rr.mp.writeByte((byte) dis.readInt());
            rr.mp.writeInt(dis.readInt());
            rr.mp.writeInt(dis.read());
            rr.mp.writeInt(dis.read());
            rr.mp.writeInt(dis.read());
            rr.mp.writeInt(dis.read());
            address_nbr_of_digits = (byte) dis.read();
            rr.mp.writeByte((byte) address_nbr_of_digits);
            {
                int i;
                i = 0;
                {
                    rr.mp.writeByte(dis.readByte());
                } //End block
            } //End collapsed parenthetic
            rr.mp.writeInt(dis.read());
            rr.mp.writeByte((byte) dis.read());
            subaddr_nbr_of_digits = (byte) dis.read();
            rr.mp.writeByte((byte) subaddr_nbr_of_digits);
            {
                int i;
                i = 0;
                {
                    rr.mp.writeByte(dis.readByte());
                } //End block
            } //End collapsed parenthetic
            bearerDataLength = dis.read();
            rr.mp.writeInt(bearerDataLength);
            {
                int i;
                i = 0;
                {
                    rr.mp.writeByte(dis.readByte());
                } //End block
            } //End collapsed parenthetic
        } //End block
        catch (IOException ex)
        {
            riljLog("sendSmsCdma: conversion from input stream to object failed: "
                    + ex);
        } //End block
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.048 -0400", hash_original_method = "33AA1BF497C1DD92CFF4D18BA36EE77C", hash_generated_method = "2F7E4805A9ED5D3F9DE0A36D7F8B4684")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void deleteSmsOnSim(int index, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(index);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DELETE_SMS_ON_SIM,
                response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(index);
        {
            riljLog(rr.serialString() + "> "
                    + requestToString(rr.mRequest)
                    + " " + index);
        } //End block
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_DELETE_SMS_ON_SIM,
                //response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(index);
        //if (false) {
            //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                    //+ requestToString(rr.mRequest)
                    //+ " " + index);
        //}
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.048 -0400", hash_original_method = "AADBC281C1547D2CD40AF49A4F15439D", hash_generated_method = "2F9149942E9BB15FF45E6A3971650379")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void deleteSmsOnRuim(int index, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(index);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM,
                response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(index);
        {
            riljLog(rr.serialString() + "> "
                    + requestToString(rr.mRequest)
                    + " " + index);
        } //End block
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM,
                //response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(index);
        //if (false) {
            //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                    //+ requestToString(rr.mRequest)
                    //+ " " + index);
        //}
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.048 -0400", hash_original_method = "371A972FE084C98FCAE13C1996429D8F", hash_generated_method = "853CE3B4FE4325F1AE7E4B60614FD173")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void writeSmsToSim(int status, String smsc, String pdu, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(status);
        dsTaint.addTaint(pdu);
        dsTaint.addTaint(smsc);
        status = translateStatus(status);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_WRITE_SMS_TO_SIM,
                response);
        rr.mp.writeInt(status);
        rr.mp.writeString(pdu);
        rr.mp.writeString(smsc);
        {
            riljLog(rr.serialString() + "> "
                    + requestToString(rr.mRequest)
                    + " " + status);
        } //End block
        send(rr);
        // ---------- Original Method ----------
        //status = translateStatus(status);
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_WRITE_SMS_TO_SIM,
                //response);
        //rr.mp.writeInt(status);
        //rr.mp.writeString(pdu);
        //rr.mp.writeString(smsc);
        //if (false) {
            //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                    //+ requestToString(rr.mRequest)
                    //+ " " + status);
        //}
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.049 -0400", hash_original_method = "05C70D9FCCFAC2D0EDAAC7E4EA19DFB0", hash_generated_method = "FF57062F92C164BD1890FEC4F54023BC")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void writeSmsToRuim(int status, String pdu, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(status);
        dsTaint.addTaint(pdu);
        status = translateStatus(status);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM,
                response);
        rr.mp.writeInt(status);
        rr.mp.writeString(pdu);
        {
            riljLog(rr.serialString() + "> "
                    + requestToString(rr.mRequest)
                    + " " + status);
        } //End block
        send(rr);
        // ---------- Original Method ----------
        //status = translateStatus(status);
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM,
                //response);
        //rr.mp.writeInt(status);
        //rr.mp.writeString(pdu);
        //if (false) {
            //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                    //+ requestToString(rr.mRequest)
                    //+ " " + status);
        //}
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.049 -0400", hash_original_method = "0D77FBD1C1F349CB6A533B80F899FC6F", hash_generated_method = "2116548F7A98DE839E0E9FAD6211AB6D")
    @DSModeled(DSC.SAFE)
    private int translateStatus(int status) {
        dsTaint.addTaint(status);
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //switch(status & 0x7) {
            //case SmsManager.STATUS_ON_ICC_READ:
                //return 1;
            //case SmsManager.STATUS_ON_ICC_UNREAD:
                //return 0;
            //case SmsManager.STATUS_ON_ICC_SENT:
                //return 3;
            //case SmsManager.STATUS_ON_ICC_UNSENT:
                //return 2;
        //}
        //return 1;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.050 -0400", hash_original_method = "9997727B0C7061EF37C7F5F20BA491DC", hash_generated_method = "153C9A539EC823C93E04521E6EB5DBE9")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setupDataCall(String radioTechnology, String profile, String apn,
            String user, String password, String authType, String protocol,
            Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(protocol);
        dsTaint.addTaint(authType);
        dsTaint.addTaint(apn);
        dsTaint.addTaint(password);
        dsTaint.addTaint(user);
        dsTaint.addTaint(profile);
        dsTaint.addTaint(radioTechnology);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SETUP_DATA_CALL, result);
        rr.mp.writeInt(7);
        rr.mp.writeString(radioTechnology);
        rr.mp.writeString(profile);
        rr.mp.writeString(apn);
        rr.mp.writeString(user);
        rr.mp.writeString(password);
        rr.mp.writeString(authType);
        rr.mp.writeString(protocol);
        riljLog(rr.serialString() + "> "
                + requestToString(rr.mRequest) + " " + radioTechnology + " "
                + profile + " " + apn + " " + user + " "
                + password + " " + authType + " " + protocol);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SETUP_DATA_CALL, result);
        //rr.mp.writeInt(7);
        //rr.mp.writeString(radioTechnology);
        //rr.mp.writeString(profile);
        //rr.mp.writeString(apn);
        //rr.mp.writeString(user);
        //rr.mp.writeString(password);
        //rr.mp.writeString(authType);
        //rr.mp.writeString(protocol);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                //+ requestToString(rr.mRequest) + " " + radioTechnology + " "
                //+ profile + " " + apn + " " + user + " "
                //+ password + " " + authType + " " + protocol);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.050 -0400", hash_original_method = "7ACA8E6FBBE8E5A758B0F14E8B7A1BD0", hash_generated_method = "18B81BFB479273F144A6010E9B8550CC")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void deactivateDataCall(int cid, int reason, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(reason);
        dsTaint.addTaint(cid);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DEACTIVATE_DATA_CALL, result);
        rr.mp.writeInt(2);
        rr.mp.writeString(Integer.toString(cid));
        rr.mp.writeString(Integer.toString(reason));
        riljLog(rr.serialString() + "> " +
                requestToString(rr.mRequest) + " " + cid + " " + reason);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_DEACTIVATE_DATA_CALL, result);
        //rr.mp.writeInt(2);
        //rr.mp.writeString(Integer.toString(cid));
        //rr.mp.writeString(Integer.toString(reason));
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " +
                //requestToString(rr.mRequest) + " " + cid + " " + reason);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.050 -0400", hash_original_method = "EB783AFA41C6889D4897D81BF3D72432", hash_generated_method = "AD430EA6031A88CE9A586DC13AFFBE87")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setRadioPower(boolean on, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(on);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_RADIO_POWER, result);
        rr.mp.writeInt(1);
        rr.mp.writeInt(on ? 1 : 0);
        {
            riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + (on ? " on" : " off"));
        } //End block
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_RADIO_POWER, result);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(on ? 1 : 0);
        //if (RILJ_LOGD) {
            //riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    //+ (on ? " on" : " off"));
        //}
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.051 -0400", hash_original_method = "20525E4C07E2BDDC8598F4A8A0A24AAC", hash_generated_method = "EC803665DAE569B02909D180987B80C1")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setSuppServiceNotifications(boolean enable, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(enable);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION, result);
        rr.mp.writeInt(1);
        rr.mp.writeInt(enable ? 1 : 0);
        riljLog(rr.serialString() + "> "
                + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION, result);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(enable ? 1 : 0);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                //+ requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.051 -0400", hash_original_method = "2FD7CAC0F0B66293984C61A06F9D6871", hash_generated_method = "CE510015CE3F07E8BB2D5C0FBA3DAB85")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void acknowledgeLastIncomingGsmSms(boolean success, int cause, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(cause);
        dsTaint.addTaint(success);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SMS_ACKNOWLEDGE, result);
        rr.mp.writeInt(2);
        rr.mp.writeInt(success ? 1 : 0);
        rr.mp.writeInt(cause);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " " + success + " " + cause);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SMS_ACKNOWLEDGE, result);
        //rr.mp.writeInt(2);
        //rr.mp.writeInt(success ? 1 : 0);
        //rr.mp.writeInt(cause);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " " + success + " " + cause);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.051 -0400", hash_original_method = "7D64BD1B5FFD5E98E91826BF607F7EF6", hash_generated_method = "530400B7769CCDD99B17AE03FACBA7EE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void acknowledgeLastIncomingCdmaSms(boolean success, int cause, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(cause);
        dsTaint.addTaint(success);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE, result);
        rr.mp.writeInt(success ? 0 : 1);
        rr.mp.writeInt(cause);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " " + success + " " + cause);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE, result);
        //rr.mp.writeInt(success ? 0 : 1);
        //rr.mp.writeInt(cause);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " " + success + " " + cause);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.051 -0400", hash_original_method = "64AA0D67C6CE837D613809948FDF7791", hash_generated_method = "B74999D77C29E455753A4F536102DCBA")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void acknowledgeIncomingGsmSmsWithPdu(boolean success, String ackPdu, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(success);
        dsTaint.addTaint(ackPdu);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU, result);
        rr.mp.writeInt(2);
        rr.mp.writeString(success ? "1" : "0");
        rr.mp.writeString(ackPdu);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + ' ' + success + " [" + ackPdu + ']');
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU, result);
        //rr.mp.writeInt(2);
        //rr.mp.writeString(success ? "1" : "0");
        //rr.mp.writeString(ackPdu);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ ' ' + success + " [" + ackPdu + ']');
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.052 -0400", hash_original_method = "59EE7F242CC2ECDC31CC3317E2625FB0", hash_generated_method = "B83FCD3A6D9FF0CF35EDBC2B2FC7D55C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void iccIO(int command, int fileid, String path, int p1, int p2, int p3,
            String data, String pin2, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(p3);
        dsTaint.addTaint(pin2);
        dsTaint.addTaint(p2);
        dsTaint.addTaint(p1);
        dsTaint.addTaint(data);
        dsTaint.addTaint(path);
        dsTaint.addTaint(command);
        dsTaint.addTaint(fileid);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SIM_IO, result);
        rr.mp.writeInt(command);
        rr.mp.writeInt(fileid);
        rr.mp.writeString(path);
        rr.mp.writeInt(p1);
        rr.mp.writeInt(p2);
        rr.mp.writeInt(p3);
        rr.mp.writeString(data);
        rr.mp.writeString(pin2);
        riljLog(rr.serialString() + "> iccIO: " + requestToString(rr.mRequest)
                + " 0x" + Integer.toHexString(command)
                + " 0x" + Integer.toHexString(fileid) + " "
                + " path: " + path + ","
                + p1 + "," + p2 + "," + p3);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SIM_IO, result);
        //rr.mp.writeInt(command);
        //rr.mp.writeInt(fileid);
        //rr.mp.writeString(path);
        //rr.mp.writeInt(p1);
        //rr.mp.writeInt(p2);
        //rr.mp.writeInt(p3);
        //rr.mp.writeString(data);
        //rr.mp.writeString(pin2);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> iccIO: " + requestToString(rr.mRequest)
                //+ " 0x" + Integer.toHexString(command)
                //+ " 0x" + Integer.toHexString(fileid) + " "
                //+ " path: " + path + ","
                //+ p1 + "," + p2 + "," + p3);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.052 -0400", hash_original_method = "ED6C155DF5FB4724355C53F41C941076", hash_generated_method = "8EF5E8C86A549A288AD38AF99A681F52")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getCLIR(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_CLIR, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_GET_CLIR, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.053 -0400", hash_original_method = "19860D4829B0277968FE7219CA94272C", hash_generated_method = "A2299DAE81B31B8AA0F777AA01DD2085")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setCLIR(int clirMode, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(clirMode);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_CLIR, result);
        rr.mp.writeInt(1);
        rr.mp.writeInt(clirMode);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + " " + clirMode);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_CLIR, result);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(clirMode);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    //+ " " + clirMode);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.053 -0400", hash_original_method = "32FF52B6F1E399D1F262392EAB262972", hash_generated_method = "1FB299969F4951DE11A6BAE32D7BA2B3")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void queryCallWaiting(int serviceClass, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_QUERY_CALL_WAITING, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(serviceClass);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + " " + serviceClass);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_QUERY_CALL_WAITING, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(serviceClass);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    //+ " " + serviceClass);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.053 -0400", hash_original_method = "5A203D1E3F2536DB5285EBA696BDFC7A", hash_generated_method = "2AACE6FE9E7AE97DC0E4341E31FC3F56")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setCallWaiting(boolean enable, int serviceClass, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        dsTaint.addTaint(enable);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_CALL_WAITING, response);
        rr.mp.writeInt(2);
        rr.mp.writeInt(enable ? 1 : 0);
        rr.mp.writeInt(serviceClass);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " " + enable + ", " + serviceClass);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_CALL_WAITING, response);
        //rr.mp.writeInt(2);
        //rr.mp.writeInt(enable ? 1 : 0);
        //rr.mp.writeInt(serviceClass);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " " + enable + ", " + serviceClass);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.053 -0400", hash_original_method = "B2CF07F1A50F7E09BFE7FA86DAEC0935", hash_generated_method = "C6AFF6723680F2EBB83067E0079B6668")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setNetworkSelectionModeAutomatic(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC,
                                    response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC,
                                    //response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.054 -0400", hash_original_method = "D59960D2C4E94DFBAD71EBB3A94610C2", hash_generated_method = "5C88016F1E70839677C2911EE3F6F0F6")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setNetworkSelectionModeManual(String operatorNumeric, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(operatorNumeric);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL,
                                    response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + " " + operatorNumeric);
        rr.mp.writeString(operatorNumeric);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL,
                                    //response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    //+ " " + operatorNumeric);
        //rr.mp.writeString(operatorNumeric);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.054 -0400", hash_original_method = "7D16D9750A543F65AAA01DDCEE296D12", hash_generated_method = "F41458FBE555B57E7C9526B723A4A01C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getNetworkSelectionMode(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE,
                                    response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE,
                                    //response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.054 -0400", hash_original_method = "A28AFA8A6050502E50A0794217CD9CA3", hash_generated_method = "0DBB29A74992CA09913A23CE57117C24")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getAvailableNetworks(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_QUERY_AVAILABLE_NETWORKS,
                                    response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_QUERY_AVAILABLE_NETWORKS,
                                    //response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.055 -0400", hash_original_method = "BAE7513A4D74D7F41628BC2CA56E49A7", hash_generated_method = "63FFCB2377CA27A5D6B7E2EE529CFCA5")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setCallForward(int action, int cfReason, int serviceClass,
                String number, int timeSeconds, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        dsTaint.addTaint(timeSeconds);
        dsTaint.addTaint(cfReason);
        dsTaint.addTaint(action);
        dsTaint.addTaint(number);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_CALL_FORWARD, response);
        rr.mp.writeInt(action);
        rr.mp.writeInt(cfReason);
        rr.mp.writeInt(serviceClass);
        rr.mp.writeInt(PhoneNumberUtils.toaFromString(number));
        rr.mp.writeString(number);
        rr.mp.writeInt (timeSeconds);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + " " + action + " " + cfReason + " " + serviceClass
                    + timeSeconds);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_CALL_FORWARD, response);
        //rr.mp.writeInt(action);
        //rr.mp.writeInt(cfReason);
        //rr.mp.writeInt(serviceClass);
        //rr.mp.writeInt(PhoneNumberUtils.toaFromString(number));
        //rr.mp.writeString(number);
        //rr.mp.writeInt (timeSeconds);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    //+ " " + action + " " + cfReason + " " + serviceClass
                    //+ timeSeconds);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.056 -0400", hash_original_method = "23037026F2CF508E3AB593A615459BEF", hash_generated_method = "07F5BB754F521C3CFCE91E8BBD003230")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void queryCallForwardStatus(int cfReason, int serviceClass,
                String number, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        dsTaint.addTaint(cfReason);
        dsTaint.addTaint(number);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_QUERY_CALL_FORWARD_STATUS, response);
        rr.mp.writeInt(2);
        rr.mp.writeInt(cfReason);
        rr.mp.writeInt(serviceClass);
        rr.mp.writeInt(PhoneNumberUtils.toaFromString(number));
        rr.mp.writeString(number);
        rr.mp.writeInt (0);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " " + cfReason + " " + serviceClass);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
            //= RILRequest.obtain(RIL_REQUEST_QUERY_CALL_FORWARD_STATUS, response);
        //rr.mp.writeInt(2);
        //rr.mp.writeInt(cfReason);
        //rr.mp.writeInt(serviceClass);
        //rr.mp.writeInt(PhoneNumberUtils.toaFromString(number));
        //rr.mp.writeString(number);
        //rr.mp.writeInt (0);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " " + cfReason + " " + serviceClass);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.057 -0400", hash_original_method = "51655E0BCF7B1E2F86E04B0432A13A10", hash_generated_method = "F06E95C932FCC3A85C6E25EA2BD63277")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void queryCLIP(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_QUERY_CLIP, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
            //= RILRequest.obtain(RIL_REQUEST_QUERY_CLIP, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.057 -0400", hash_original_method = "A7E1CFA2EA980EF525A71B63D23749C6", hash_generated_method = "FFC98E4433EDB3BA8E0188DD96BF863D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getBasebandVersion(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_BASEBAND_VERSION, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_BASEBAND_VERSION, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.057 -0400", hash_original_method = "E2D6CDE0F4A2E0F1E247B2E96BC06629", hash_generated_method = "437F6DB5F947D1E8C2BF3526E033ADA1")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void queryFacilityLock(String facility, String password, int serviceClass,
                            Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        dsTaint.addTaint(facility);
        dsTaint.addTaint(password);
        queryFacilityLockForApp(facility, password, serviceClass, null, response);
        // ---------- Original Method ----------
        //queryFacilityLockForApp(facility, password, serviceClass, null, response);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.058 -0400", hash_original_method = "B68C5E6FDA45200A6E5673701837774F", hash_generated_method = "FFEF23503925AE9E4ABEB6E9C21BC4B0")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void queryFacilityLockForApp(String facility, String password, int serviceClass, String appId,
                            Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        dsTaint.addTaint(appId);
        dsTaint.addTaint(facility);
        dsTaint.addTaint(password);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_QUERY_FACILITY_LOCK, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(4);
        rr.mp.writeString(facility);
        rr.mp.writeString(password);
        rr.mp.writeString(Integer.toString(serviceClass));
        rr.mp.writeString(appId);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_QUERY_FACILITY_LOCK, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(4);
        //rr.mp.writeString(facility);
        //rr.mp.writeString(password);
        //rr.mp.writeString(Integer.toString(serviceClass));
        //rr.mp.writeString(appId);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.058 -0400", hash_original_method = "AF27A0F06A4AEF0B8CE967EE20FD6D5A", hash_generated_method = "9363A88D61F2617EFDE4138969851CE8")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void setFacilityLock(String facility, boolean lockState, String password,
                        int serviceClass, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        dsTaint.addTaint(facility);
        dsTaint.addTaint(password);
        dsTaint.addTaint(lockState);
        setFacilityLockForApp(facility, lockState, password, serviceClass, null, response);
        // ---------- Original Method ----------
        //setFacilityLockForApp(facility, lockState, password, serviceClass, null, response);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.058 -0400", hash_original_method = "99FAE35221FB0E541022587B483A8F41", hash_generated_method = "58712990A698CF349094C05D97BA6686")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void setFacilityLockForApp(String facility, boolean lockState, String password,
                        int serviceClass, String appId, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(serviceClass);
        dsTaint.addTaint(appId);
        dsTaint.addTaint(facility);
        dsTaint.addTaint(password);
        dsTaint.addTaint(lockState);
        String lockString;
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_FACILITY_LOCK, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeInt(5);
        rr.mp.writeString(facility);
        lockString = (lockState)?"1":"0";
        rr.mp.writeString(lockString);
        rr.mp.writeString(password);
        rr.mp.writeString(Integer.toString(serviceClass));
        rr.mp.writeString(appId);
        send(rr);
        // ---------- Original Method ----------
        //String lockString;
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_FACILITY_LOCK, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeInt(5);
        //rr.mp.writeString(facility);
        //lockString = (lockState)?"1":"0";
        //rr.mp.writeString(lockString);
        //rr.mp.writeString(password);
        //rr.mp.writeString(Integer.toString(serviceClass));
        //rr.mp.writeString(appId);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.058 -0400", hash_original_method = "B3D73089A364D1181DC1E2D229325D6D", hash_generated_method = "62DDED0020E0A7A0E50C30CBF3B2EE2A")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendUSSD(String ussdString, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(ussdString);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SEND_USSD, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                            + " " + ussdString);
        rr.mp.writeString(ussdString);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SEND_USSD, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                            //+ " " + ussdString);
        //rr.mp.writeString(ussdString);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.059 -0400", hash_original_method = "08754E29DFB7112567CD0DC9F54BCD3D", hash_generated_method = "03257AB8F783F166EB194B77B7EBF5DB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void cancelPendingUssd(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CANCEL_USSD, response);
        riljLog(rr.serialString()
                + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_CANCEL_USSD, response);
        //if (RILJ_LOGD) riljLog(rr.serialString()
                //+ "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.059 -0400", hash_original_method = "5EED3CE7F239827AE459A68B7A5F6205", hash_generated_method = "2D1BA48414D465D99AD1B681EA583901")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void resetRadio(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_RESET_RADIO, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_RESET_RADIO, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.059 -0400", hash_original_method = "CC2053517147AA26411A18AF4FB47931", hash_generated_method = "741A93C02C89A50C01B9B8DD8FE6DF88")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void invokeOemRilRequestRaw(byte[] data, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(data[0]);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_OEM_HOOK_RAW, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
               + "[" + IccUtils.bytesToHexString(data) + "]");
        rr.mp.writeByteArray(data);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_OEM_HOOK_RAW, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
               //+ "[" + IccUtils.bytesToHexString(data) + "]");
        //rr.mp.writeByteArray(data);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.059 -0400", hash_original_method = "30F3E98808998C1043AF97B2AEFF80ED", hash_generated_method = "536255BD39A317B8B153B71256924D4F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void invokeOemRilRequestStrings(String[] strings, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(strings[0]);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_OEM_HOOK_STRINGS, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeStringArray(strings);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_OEM_HOOK_STRINGS, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeStringArray(strings);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.060 -0400", hash_original_method = "0483D811AD0184EC240C471A09F163C0", hash_generated_method = "E9253A092908AF1DF3AB6C49AF8715DD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setBandMode(int bandMode, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(bandMode);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_BAND_MODE, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(bandMode);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                 + " " + bandMode);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_SET_BAND_MODE, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(bandMode);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                 //+ " " + bandMode);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.060 -0400", hash_original_method = "3559A91588B2C9F9707F9FC4DC3F7539", hash_generated_method = "36ABC5EEFA885EDE0FDC9C49186235C6")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void queryAvailableBandMode(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE,
                response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr
                //= RILRequest.obtain(RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE,
                //response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.060 -0400", hash_original_method = "E6DDFD05E0997BF7790CA803E711C2EC", hash_generated_method = "BCF5465B9F26359FFBE1779594601FA2")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendTerminalResponse(String contents, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(contents);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeString(contents);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeString(contents);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.061 -0400", hash_original_method = "AF8E8A7CD3B08412D1DCD73CAE0ACCA0", hash_generated_method = "C6466714A3848449AC5A9B1D0E650838")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendEnvelope(String contents, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(contents);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        rr.mp.writeString(contents);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //rr.mp.writeString(contents);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.061 -0400", hash_original_method = "A3C6115C92419E7DB4E4439F1BAFD013", hash_generated_method = "545BD192D84522C48095938C1A82B39B")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendEnvelopeWithStatus(String contents, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(contents);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + '[' + contents + ']');
        rr.mp.writeString(contents);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ '[' + contents + ']');
        //rr.mp.writeString(contents);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.061 -0400", hash_original_method = "0FD1EE2A52540A4F80ECA30E950318D2", hash_generated_method = "6A7C781DC78E256D9095C85C978B8C48")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void handleCallSetupRequestFromSim(
            boolean accept, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(accept);
        RILRequest rr;
        rr = RILRequest.obtain(
            RILConstants.RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM,
            response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        int[] param;
        param = new int[1];
        param[0] = accept ? 1 : 0;
        rr.mp.writeIntArray(param);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
            //RILConstants.RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM,
            //response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //int[] param = new int[1];
        //param[0] = accept ? 1 : 0;
        //rr.mp.writeIntArray(param);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.061 -0400", hash_original_method = "E0470661D17E36A9D9FCCA070C28B88C", hash_generated_method = "8F3EBD836485C251BFD8B926999FF50B")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void setCurrentPreferredNetworkType() {
        riljLog("setCurrentPreferredNetworkType: " + mSetPreferredNetworkType);
        setPreferredNetworkType(mSetPreferredNetworkType, null);
        // ---------- Original Method ----------
        //if (RILJ_LOGD) riljLog("setCurrentPreferredNetworkType: " + mSetPreferredNetworkType);
        //setPreferredNetworkType(mSetPreferredNetworkType, null);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.062 -0400", hash_original_method = "CD05D01D2353BF09784B2C892638713C", hash_generated_method = "F643C59B54168FF11D8954A9D7DDBF40")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setPreferredNetworkType(int networkType , Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(networkType);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(networkType);
        mPreferredNetworkType = networkType;
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " : " + networkType);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(networkType);
        //mSetPreferredNetworkType = networkType;
        //mPreferredNetworkType = networkType;
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " : " + networkType);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.062 -0400", hash_original_method = "9630540DB0E1A382F252246374543CFF", hash_generated_method = "AF2B1EAB6590424DB22A0748DC8FEC6E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getPreferredNetworkType(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.062 -0400", hash_original_method = "EA7CB0793F40AD8E91B9E82268500DF5", hash_generated_method = "6919CDB09312695A47501875D1F5A14E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getNeighboringCids(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_GET_NEIGHBORING_CELL_IDS, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_GET_NEIGHBORING_CELL_IDS, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.063 -0400", hash_original_method = "29FCAB1F96F7A6DF63D13BAE634B9C42", hash_generated_method = "D715489C5717CB7B4053CEE174DC937F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setLocationUpdates(boolean enable, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(enable);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_LOCATION_UPDATES, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(enable ? 1 : 0);
        riljLog(rr.serialString() + "> "
                + requestToString(rr.mRequest) + ": " + enable);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_SET_LOCATION_UPDATES, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(enable ? 1 : 0);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                //+ requestToString(rr.mRequest) + ": " + enable);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.063 -0400", hash_original_method = "7C3C5691348423704F3F89A5A1253262", hash_generated_method = "EE94431C22BE42477A69AB13B8060DED")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getSmscAddress(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GET_SMSC_ADDRESS, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GET_SMSC_ADDRESS, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.063 -0400", hash_original_method = "8030AF8483F275DADFE9FBCDFA4401B2", hash_generated_method = "D268CA476ADCB489F54224048C06928F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setSmscAddress(String address, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(address);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SET_SMSC_ADDRESS, result);
        rr.mp.writeString(address);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " : " + address);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_SET_SMSC_ADDRESS, result);
        //rr.mp.writeString(address);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " : " + address);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.063 -0400", hash_original_method = "E14FF49FE633CFCC7BBD49EA04EF5648", hash_generated_method = "03F01E234E7D6495B89ED41D31A05724")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void reportSmsMemoryStatus(boolean available, Message result) {
        dsTaint.addTaint(result.dsTaint);
        dsTaint.addTaint(available);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_REPORT_SMS_MEMORY_STATUS, result);
        rr.mp.writeInt(1);
        rr.mp.writeInt(available ? 1 : 0);
        riljLog(rr.serialString() + "> "
                + requestToString(rr.mRequest) + ": " + available);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_REPORT_SMS_MEMORY_STATUS, result);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(available ? 1 : 0);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                //+ requestToString(rr.mRequest) + ": " + available);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.064 -0400", hash_original_method = "00A5515B81A4CB5336CA56505ADAAD61", hash_generated_method = "64DE247B81BC4CA9AFD0ACEA9DC5CB51")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void reportStkServiceIsRunning(Message result) {
        dsTaint.addTaint(result.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING, result);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING, result);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.064 -0400", hash_original_method = "8D8904D0E740DF21294F215C1D1D7109", hash_generated_method = "0C790F6F8898F75481A2D381EE895BF5")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getGsmBroadcastConfig(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GSM_GET_BROADCAST_CONFIG, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GSM_GET_BROADCAST_CONFIG, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.064 -0400", hash_original_method = "052EA3DC72AD24FB98DF1CE5007E33C5", hash_generated_method = "72C5F9785B22EC17AD9B7132C6BF719E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setGsmBroadcastConfig(SmsBroadcastConfigInfo[] config, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(config[0].dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GSM_SET_BROADCAST_CONFIG, response);
        int numOfConfig;
        numOfConfig = config.length;
        rr.mp.writeInt(numOfConfig);
        {
            int i;
            i = 0;
            {
                rr.mp.writeInt(config[i].getFromServiceId());
                rr.mp.writeInt(config[i].getToServiceId());
                rr.mp.writeInt(config[i].getFromCodeScheme());
                rr.mp.writeInt(config[i].getToCodeScheme());
                rr.mp.writeInt(config[i].isSelected() ? 1 : 0);
            } //End block
        } //End collapsed parenthetic
        {
            riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + " with " + numOfConfig + " configs : ");
            {
                int i;
                i = 0;
                {
                    riljLog(config[i].toString());
                } //End block
            } //End collapsed parenthetic
        } //End block
        send(rr);
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.065 -0400", hash_original_method = "A31C3FA8B485D8364A045A89E34CE9F1", hash_generated_method = "C7766A5A209F8459AA00B60CFBBD9184")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setGsmBroadcastActivation(boolean activate, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(activate);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_GSM_BROADCAST_ACTIVATION, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(activate ? 0 : 1);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_GSM_BROADCAST_ACTIVATION, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(activate ? 0 : 1);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.065 -0400", hash_original_method = "98C8C2D826445A3B0F1B6AA20E13AA85", hash_generated_method = "22A08659674DAE8E20E54E6C28227A7B")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void sendScreenState(boolean on) {
        dsTaint.addTaint(on);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_SCREEN_STATE, null);
        rr.mp.writeInt(1);
        rr.mp.writeInt(on ? 1 : 0);
        riljLog(rr.serialString()
                + "> " + requestToString(rr.mRequest) + ": " + on);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_SCREEN_STATE, null);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(on ? 1 : 0);
        //if (RILJ_LOGD) riljLog(rr.serialString()
                //+ "> " + requestToString(rr.mRequest) + ": " + on);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.065 -0400", hash_original_method = "BB835229E5EA50CDBB0B126232F0F168", hash_generated_method = "D18433B9DE179811653284B06AFFE308")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    protected void onRadioAvailable() {
        //DSFIXME:  CODE0009: Possible callback target function detected
        sendScreenState(true);
        // ---------- Original Method ----------
        //sendScreenState(true);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.066 -0400", hash_original_method = "160781DFD510822CA161AF254F33803E", hash_generated_method = "381BDACA5AB701908C963D7D65616DD7")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private RadioState getRadioStateFromInt(int stateInt) {
        dsTaint.addTaint(stateInt);
        RadioState state;
        //Begin case 0 
        state = RadioState.RADIO_OFF;
        //End case 0 
        //Begin case 1 
        state = RadioState.RADIO_UNAVAILABLE;
        //End case 1 
        //Begin case 2 
        state = RadioState.SIM_NOT_READY;
        //End case 2 
        //Begin case 3 
        state = RadioState.SIM_LOCKED_OR_ABSENT;
        //End case 3 
        //Begin case 4 
        state = RadioState.SIM_READY;
        //End case 4 
        //Begin case 5 
        state = RadioState.RUIM_NOT_READY;
        //End case 5 
        //Begin case 6 
        state = RadioState.RUIM_READY;
        //End case 6 
        //Begin case 7 
        state = RadioState.RUIM_LOCKED_OR_ABSENT;
        //End case 7 
        //Begin case 8 
        state = RadioState.NV_NOT_READY;
        //End case 8 
        //Begin case 9 
        state = RadioState.NV_READY;
        //End case 9 
        //Begin case default 
        if (DroidSafeAndroidRuntime.control) throw new RuntimeException(
                            "Unrecognized RIL_RadioState: " + stateInt);
        //End case default 
        return (RadioState)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.066 -0400", hash_original_method = "63485FEB05CEDE30346AAC1338BBCCD0", hash_generated_method = "7525C931642E9FC85C28CCFCF4BE822E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void switchToRadioState(RadioState newState) {
        dsTaint.addTaint(newState.dsTaint);
        setRadioState(newState);
        // ---------- Original Method ----------
        //setRadioState(newState);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.066 -0400", hash_original_method = "80B68145386C06F39CF9C77A11CB4F6E", hash_generated_method = "D20AAC8AAD15FFE2B35A5089E4A4BB71")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void acquireWakeLock() {
        {
            mWakeLock.acquire();
            mSender.removeMessages(EVENT_WAKE_LOCK_TIMEOUT);
            Message msg;
            msg = mSender.obtainMessage(EVENT_WAKE_LOCK_TIMEOUT);
            mSender.sendMessageDelayed(msg, mWakeLockTimeout);
        } //End block
        // ---------- Original Method ----------
        //synchronized (mWakeLock) {
            //mWakeLock.acquire();
            //mRequestMessagesPending++;
            //mSender.removeMessages(EVENT_WAKE_LOCK_TIMEOUT);
            //Message msg = mSender.obtainMessage(EVENT_WAKE_LOCK_TIMEOUT);
            //mSender.sendMessageDelayed(msg, mWakeLockTimeout);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.067 -0400", hash_original_method = "F7B2E2DA8A26C192DB4FA2BF8451999B", hash_generated_method = "CF3034AD7B7FC15B46FBFF9877AC3C5C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void releaseWakeLockIfDone() {
        {
            {
                boolean var89EBFD9F809D519C007EEC04CB5812D0_1875329810 = (mWakeLock.isHeld() &&
                (mRequestMessagesPending == 0) &&
                (mRequestMessagesWaiting == 0));
                {
                    mSender.removeMessages(EVENT_WAKE_LOCK_TIMEOUT);
                    mWakeLock.release();
                } //End block
            } //End collapsed parenthetic
        } //End block
        // ---------- Original Method ----------
        //synchronized (mWakeLock) {
            //if (mWakeLock.isHeld() &&
                //(mRequestMessagesPending == 0) &&
                //(mRequestMessagesWaiting == 0)) {
                //mSender.removeMessages(EVENT_WAKE_LOCK_TIMEOUT);
                //mWakeLock.release();
            //}
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.067 -0400", hash_original_method = "035841781AADB837D6EAD23CCB5C67B0", hash_generated_method = "B827EB260D503E0B433840AB3B6A6051")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void send(RILRequest rr) {
        dsTaint.addTaint(rr.dsTaint);
        Message msg;
        {
            rr.onError(RADIO_NOT_AVAILABLE, null);
            rr.release();
        } //End block
        msg = mSender.obtainMessage(EVENT_SEND, rr);
        acquireWakeLock();
        msg.sendToTarget();
        // ---------- Original Method ----------
        //Message msg;
        //if (mSocket == null) {
            //rr.onError(RADIO_NOT_AVAILABLE, null);
            //rr.release();
            //return;
        //}
        //msg = mSender.obtainMessage(EVENT_SEND, rr);
        //acquireWakeLock();
        //msg.sendToTarget();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.067 -0400", hash_original_method = "BE5BB24392C97184A4E1C8B58214F114", hash_generated_method = "4423298794EA83F4E98732E72BCC23DE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void processResponse(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int type;
        type = p.readInt();
        {
            processUnsolicited (p);
        } //End block
        {
            processSolicited (p);
        } //End block
        releaseWakeLockIfDone();
        // ---------- Original Method ----------
        //int type;
        //type = p.readInt();
        //if (type == RESPONSE_UNSOLICITED) {
            //processUnsolicited (p);
        //} else if (type == RESPONSE_SOLICITED) {
            //processSolicited (p);
        //}
        //releaseWakeLockIfDone();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.068 -0400", hash_original_method = "BC68EE684C76422271CAFE5D885C13B0", hash_generated_method = "4CEA635F1E3E3A2679EBE3DE7F761804")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void clearRequestsList(int error, boolean loggable) {
        dsTaint.addTaint(error);
        dsTaint.addTaint(loggable);
        RILRequest rr;
        {
            int count;
            count = mRequestsList.size();
            {
                Log.d(LOG_TAG, "WAKE_LOCK_TIMEOUT " +
                        " mReqPending=" + mRequestMessagesPending +
                        " mRequestList=" + count);
            } //End block
            {
                int i;
                i = 0;
                {
                    rr = mRequestsList.get(i);
                    {
                        Log.d(LOG_TAG, i + ": [" + rr.mSerial + "] " +
                            requestToString(rr.mRequest));
                    } //End block
                    rr.onError(error, null);
                    rr.release();
                } //End block
            } //End collapsed parenthetic
            mRequestsList.clear();
            mRequestMessagesWaiting = 0;
        } //End block
        // ---------- Original Method ----------
        //RILRequest rr;
        //synchronized (mRequestsList) {
            //int count = mRequestsList.size();
            //if (RILJ_LOGD && loggable) {
                //Log.d(LOG_TAG, "WAKE_LOCK_TIMEOUT " +
                        //" mReqPending=" + mRequestMessagesPending +
                        //" mRequestList=" + count);
            //}
            //for (int i = 0; i < count ; i++) {
                //rr = mRequestsList.get(i);
                //if (RILJ_LOGD && loggable) {
                    //Log.d(LOG_TAG, i + ": [" + rr.mSerial + "] " +
                            //requestToString(rr.mRequest));
                //}
                //rr.onError(error, null);
                //rr.release();
            //}
            //mRequestsList.clear();
            //mRequestMessagesWaiting = 0;
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.068 -0400", hash_original_method = "821C18746C2BF0250268E9897F34563E", hash_generated_method = "CCA879CEAF21783E5BC9EFE929DB53D2")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private RILRequest findAndRemoveRequestFromList(int serial) {
        dsTaint.addTaint(serial);
        {
            {
                int i, s;
                i = 0;
                s = mRequestsList.size();
                {
                    RILRequest rr;
                    rr = mRequestsList.get(i);
                    {
                        mRequestsList.remove(i);
                    } //End block
                } //End block
            } //End collapsed parenthetic
        } //End block
        return (RILRequest)dsTaint.getTaint();
        // ---------- Original Method ----------
        //synchronized (mRequestsList) {
            //for (int i = 0, s = mRequestsList.size() ; i < s ; i++) {
                //RILRequest rr = mRequestsList.get(i);
                //if (rr.mSerial == serial) {
                    //mRequestsList.remove(i);
                    //if (mRequestMessagesWaiting > 0)
                        //mRequestMessagesWaiting--;
                    //return rr;
                //}
            //}
        //}
        //return null;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.072 -0400", hash_original_method = "E53F353584D1FC1676A6EAA90642D430", hash_generated_method = "4CFDADE94B8B6AA69F99A74452F23016")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void processSolicited(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int serial, error;
        boolean found;
        found = false;
        serial = p.readInt();
        error = p.readInt();
        RILRequest rr;
        rr = findAndRemoveRequestFromList(serial);
        Object ret;
        ret = null;
        {
            boolean varC99D962AF06CF99AD34CE6AAAD8ADD1D_172302793 = (error == 0 || p.dataAvail() > 0);
            {
                try 
                {
                    //Begin case RIL_REQUEST_GET_SIM_STATUS 
                    ret =  responseIccCardStatus(p);
                    //End case RIL_REQUEST_GET_SIM_STATUS 
                    //Begin case RIL_REQUEST_ENTER_SIM_PIN 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_ENTER_SIM_PIN 
                    //Begin case RIL_REQUEST_ENTER_SIM_PUK 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_ENTER_SIM_PUK 
                    //Begin case RIL_REQUEST_ENTER_SIM_PIN2 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_ENTER_SIM_PIN2 
                    //Begin case RIL_REQUEST_ENTER_SIM_PUK2 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_ENTER_SIM_PUK2 
                    //Begin case RIL_REQUEST_CHANGE_SIM_PIN 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_CHANGE_SIM_PIN 
                    //Begin case RIL_REQUEST_CHANGE_SIM_PIN2 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_CHANGE_SIM_PIN2 
                    //Begin case RIL_REQUEST_ENTER_NETWORK_DEPERSONALIZATION 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_ENTER_NETWORK_DEPERSONALIZATION 
                    //Begin case RIL_REQUEST_GET_CURRENT_CALLS 
                    ret =  responseCallList(p);
                    //End case RIL_REQUEST_GET_CURRENT_CALLS 
                    //Begin case RIL_REQUEST_DIAL 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_DIAL 
                    //Begin case RIL_REQUEST_GET_IMSI 
                    ret =  responseString(p);
                    //End case RIL_REQUEST_GET_IMSI 
                    //Begin case RIL_REQUEST_HANGUP 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_HANGUP 
                    //Begin case RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND 
                    //Begin case RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND 
                    //Begin case RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE 
                    //Begin case RIL_REQUEST_CONFERENCE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CONFERENCE 
                    //Begin case RIL_REQUEST_UDUB 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_UDUB 
                    //Begin case RIL_REQUEST_LAST_CALL_FAIL_CAUSE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_LAST_CALL_FAIL_CAUSE 
                    //Begin case RIL_REQUEST_SIGNAL_STRENGTH 
                    ret =  responseSignalStrength(p);
                    //End case RIL_REQUEST_SIGNAL_STRENGTH 
                    //Begin case RIL_REQUEST_VOICE_REGISTRATION_STATE 
                    ret =  responseStrings(p);
                    //End case RIL_REQUEST_VOICE_REGISTRATION_STATE 
                    //Begin case RIL_REQUEST_DATA_REGISTRATION_STATE 
                    ret =  responseStrings(p);
                    //End case RIL_REQUEST_DATA_REGISTRATION_STATE 
                    //Begin case RIL_REQUEST_OPERATOR 
                    ret =  responseStrings(p);
                    //End case RIL_REQUEST_OPERATOR 
                    //Begin case RIL_REQUEST_RADIO_POWER 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_RADIO_POWER 
                    //Begin case RIL_REQUEST_DTMF 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_DTMF 
                    //Begin case RIL_REQUEST_SEND_SMS 
                    ret =  responseSMS(p);
                    //End case RIL_REQUEST_SEND_SMS 
                    //Begin case RIL_REQUEST_SEND_SMS_EXPECT_MORE 
                    ret =  responseSMS(p);
                    //End case RIL_REQUEST_SEND_SMS_EXPECT_MORE 
                    //Begin case RIL_REQUEST_SETUP_DATA_CALL 
                    ret =  responseSetupDataCall(p);
                    //End case RIL_REQUEST_SETUP_DATA_CALL 
                    //Begin case RIL_REQUEST_SIM_IO 
                    ret =  responseICC_IO(p);
                    //End case RIL_REQUEST_SIM_IO 
                    //Begin case RIL_REQUEST_SEND_USSD 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SEND_USSD 
                    //Begin case RIL_REQUEST_CANCEL_USSD 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CANCEL_USSD 
                    //Begin case RIL_REQUEST_GET_CLIR 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_GET_CLIR 
                    //Begin case RIL_REQUEST_SET_CLIR 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_CLIR 
                    //Begin case RIL_REQUEST_QUERY_CALL_FORWARD_STATUS 
                    ret =  responseCallForward(p);
                    //End case RIL_REQUEST_QUERY_CALL_FORWARD_STATUS 
                    //Begin case RIL_REQUEST_SET_CALL_FORWARD 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_CALL_FORWARD 
                    //Begin case RIL_REQUEST_QUERY_CALL_WAITING 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_QUERY_CALL_WAITING 
                    //Begin case RIL_REQUEST_SET_CALL_WAITING 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_CALL_WAITING 
                    //Begin case RIL_REQUEST_SMS_ACKNOWLEDGE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SMS_ACKNOWLEDGE 
                    //Begin case RIL_REQUEST_GET_IMEI 
                    ret =  responseString(p);
                    //End case RIL_REQUEST_GET_IMEI 
                    //Begin case RIL_REQUEST_GET_IMEISV 
                    ret =  responseString(p);
                    //End case RIL_REQUEST_GET_IMEISV 
                    //Begin case RIL_REQUEST_ANSWER 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_ANSWER 
                    //Begin case RIL_REQUEST_DEACTIVATE_DATA_CALL 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_DEACTIVATE_DATA_CALL 
                    //Begin case RIL_REQUEST_QUERY_FACILITY_LOCK 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_QUERY_FACILITY_LOCK 
                    //Begin case RIL_REQUEST_SET_FACILITY_LOCK 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_SET_FACILITY_LOCK 
                    //Begin case RIL_REQUEST_CHANGE_BARRING_PASSWORD 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CHANGE_BARRING_PASSWORD 
                    //Begin case RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE 
                    //Begin case RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC 
                    //Begin case RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL 
                    //Begin case RIL_REQUEST_QUERY_AVAILABLE_NETWORKS 
                    ret =  responseOperatorInfos(p);
                    //End case RIL_REQUEST_QUERY_AVAILABLE_NETWORKS 
                    //Begin case RIL_REQUEST_DTMF_START 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_DTMF_START 
                    //Begin case RIL_REQUEST_DTMF_STOP 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_DTMF_STOP 
                    //Begin case RIL_REQUEST_BASEBAND_VERSION 
                    ret =  responseString(p);
                    //End case RIL_REQUEST_BASEBAND_VERSION 
                    //Begin case RIL_REQUEST_SEPARATE_CONNECTION 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SEPARATE_CONNECTION 
                    //Begin case RIL_REQUEST_SET_MUTE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_MUTE 
                    //Begin case RIL_REQUEST_GET_MUTE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_GET_MUTE 
                    //Begin case RIL_REQUEST_QUERY_CLIP 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_QUERY_CLIP 
                    //Begin case RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE 
                    //Begin case RIL_REQUEST_DATA_CALL_LIST 
                    ret =  responseDataCallList(p);
                    //End case RIL_REQUEST_DATA_CALL_LIST 
                    //Begin case RIL_REQUEST_RESET_RADIO 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_RESET_RADIO 
                    //Begin case RIL_REQUEST_OEM_HOOK_RAW 
                    ret =  responseRaw(p);
                    //End case RIL_REQUEST_OEM_HOOK_RAW 
                    //Begin case RIL_REQUEST_OEM_HOOK_STRINGS 
                    ret =  responseStrings(p);
                    //End case RIL_REQUEST_OEM_HOOK_STRINGS 
                    //Begin case RIL_REQUEST_SCREEN_STATE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SCREEN_STATE 
                    //Begin case RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION 
                    //Begin case RIL_REQUEST_WRITE_SMS_TO_SIM 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_WRITE_SMS_TO_SIM 
                    //Begin case RIL_REQUEST_DELETE_SMS_ON_SIM 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_DELETE_SMS_ON_SIM 
                    //Begin case RIL_REQUEST_SET_BAND_MODE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_BAND_MODE 
                    //Begin case RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE 
                    //Begin case RIL_REQUEST_STK_GET_PROFILE 
                    ret =  responseString(p);
                    //End case RIL_REQUEST_STK_GET_PROFILE 
                    //Begin case RIL_REQUEST_STK_SET_PROFILE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_STK_SET_PROFILE 
                    //Begin case RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND 
                    ret =  responseString(p);
                    //End case RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND 
                    //Begin case RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE 
                    //Begin case RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM 
                    //Begin case RIL_REQUEST_EXPLICIT_CALL_TRANSFER 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_EXPLICIT_CALL_TRANSFER 
                    //Begin case RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE 
                    //Begin case RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE 
                    ret =  responseGetPreferredNetworkType(p);
                    //End case RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE 
                    //Begin case RIL_REQUEST_GET_NEIGHBORING_CELL_IDS 
                    ret = responseCellList(p);
                    //End case RIL_REQUEST_GET_NEIGHBORING_CELL_IDS 
                    //Begin case RIL_REQUEST_SET_LOCATION_UPDATES 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_LOCATION_UPDATES 
                    //Begin case RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE 
                    //Begin case RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE 
                    //Begin case RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE 
                    //Begin case RIL_REQUEST_SET_TTY_MODE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_SET_TTY_MODE 
                    //Begin case RIL_REQUEST_QUERY_TTY_MODE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_QUERY_TTY_MODE 
                    //Begin case RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE 
                    //Begin case RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE 
                    //Begin case RIL_REQUEST_CDMA_FLASH 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_FLASH 
                    //Begin case RIL_REQUEST_CDMA_BURST_DTMF 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_BURST_DTMF 
                    //Begin case RIL_REQUEST_CDMA_SEND_SMS 
                    ret =  responseSMS(p);
                    //End case RIL_REQUEST_CDMA_SEND_SMS 
                    //Begin case RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE 
                    //Begin case RIL_REQUEST_GSM_GET_BROADCAST_CONFIG 
                    ret =  responseGmsBroadcastConfig(p);
                    //End case RIL_REQUEST_GSM_GET_BROADCAST_CONFIG 
                    //Begin case RIL_REQUEST_GSM_SET_BROADCAST_CONFIG 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_GSM_SET_BROADCAST_CONFIG 
                    //Begin case RIL_REQUEST_GSM_BROADCAST_ACTIVATION 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_GSM_BROADCAST_ACTIVATION 
                    //Begin case RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG 
                    ret =  responseCdmaBroadcastConfig(p);
                    //End case RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG 
                    //Begin case RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG 
                    //Begin case RIL_REQUEST_CDMA_BROADCAST_ACTIVATION 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_BROADCAST_ACTIVATION 
                    //Begin case RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY 
                    //Begin case RIL_REQUEST_CDMA_SUBSCRIPTION 
                    ret =  responseStrings(p);
                    //End case RIL_REQUEST_CDMA_SUBSCRIPTION 
                    //Begin case RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM 
                    //Begin case RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM 
                    ret =  responseVoid(p);
                    //End case RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM 
                    //Begin case RIL_REQUEST_DEVICE_IDENTITY 
                    ret =  responseStrings(p);
                    //End case RIL_REQUEST_DEVICE_IDENTITY 
                    //Begin case RIL_REQUEST_GET_SMSC_ADDRESS 
                    ret = responseString(p);
                    //End case RIL_REQUEST_GET_SMSC_ADDRESS 
                    //Begin case RIL_REQUEST_SET_SMSC_ADDRESS 
                    ret = responseVoid(p);
                    //End case RIL_REQUEST_SET_SMSC_ADDRESS 
                    //Begin case RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE 
                    ret = responseVoid(p);
                    //End case RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE 
                    //Begin case RIL_REQUEST_REPORT_SMS_MEMORY_STATUS 
                    ret = responseVoid(p);
                    //End case RIL_REQUEST_REPORT_SMS_MEMORY_STATUS 
                    //Begin case RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING 
                    ret = responseVoid(p);
                    //End case RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING 
                    //Begin case RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE 
                    ret =  responseInts(p);
                    //End case RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE 
                    //Begin case RIL_REQUEST_ISIM_AUTHENTICATION 
                    ret =  responseString(p);
                    //End case RIL_REQUEST_ISIM_AUTHENTICATION 
                    //Begin case RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU 
                    ret = responseVoid(p);
                    //End case RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU 
                    //Begin case RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS 
                    ret = responseICC_IO(p);
                    //End case RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS 
                    //Begin case default 
                    if (DroidSafeAndroidRuntime.control) throw new RuntimeException("Unrecognized solicited response: " + rr.mRequest);
                    //End case default 
                } //End block
                catch (Throwable tr)
                {
                    {
                        AsyncResult.forMessage(rr.mResult, null, tr);
                        rr.mResult.sendToTarget();
                    } //End block
                    rr.release();
                } //End block
            } //End block
        } //End collapsed parenthetic
        {
            rr.onError(error, ret);
            rr.release();
        } //End block
        riljLog(rr.serialString() + "< " + requestToString(rr.mRequest)
            + " " + retToString(rr.mRequest, ret));
        {
            AsyncResult.forMessage(rr.mResult, ret, null);
            rr.mResult.sendToTarget();
        } //End block
        rr.release();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.090 -0400", hash_original_method = "8A94A1215BBA17FF474D4F8406941ACC", hash_generated_method = "5BEC1781F6D527FCB2A9BCCF09C3D185")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private String retToString(int req, Object ret) {
        dsTaint.addTaint(ret.dsTaint);
        dsTaint.addTaint(req);
        StringBuilder sb;
        String s;
        int length;
        {
            int[] intArray;
            intArray = (int[]) ret;
            length = intArray.length;
            sb = new StringBuilder("{");
            {
                int i;
                i = 0;
                sb.append(intArray[i++]);
                {
                    sb.append(", ").append(intArray[i++]);
                } //End block
            } //End block
            sb.append("}");
            s = sb.toString();
        } //End block
        {
            String[] strings;
            strings = (String[]) ret;
            length = strings.length;
            sb = new StringBuilder("{");
            {
                int i;
                i = 0;
                sb.append(strings[i++]);
                {
                    sb.append(", ").append(strings[i++]);
                } //End block
            } //End block
            sb.append("}");
            s = sb.toString();
        } //End block
        {
            ArrayList<DriverCall> calls;
            calls = (ArrayList<DriverCall>) ret;
            sb = new StringBuilder(" ");
            {
                Iterator<DriverCall> var4B2037772A81856F80258F9F44AA331B_973212159 = (calls).iterator();
                var4B2037772A81856F80258F9F44AA331B_973212159.hasNext();
                DriverCall dc = var4B2037772A81856F80258F9F44AA331B_973212159.next();
                {
                    sb.append("[").append(dc).append("] ");
                } //End block
            } //End collapsed parenthetic
            s = sb.toString();
        } //End block
        {
            ArrayList<NeighboringCellInfo> cells;
            cells = (ArrayList<NeighboringCellInfo>) ret;
            sb = new StringBuilder(" ");
            {
                Iterator<NeighboringCellInfo> varA3884DD0459ACF5B7D7CFE2B0AD6D134_1839911215 = (cells).iterator();
                varA3884DD0459ACF5B7D7CFE2B0AD6D134_1839911215.hasNext();
                NeighboringCellInfo cell = varA3884DD0459ACF5B7D7CFE2B0AD6D134_1839911215.next();
                {
                    sb.append(cell).append(" ");
                } //End block
            } //End collapsed parenthetic
            s = sb.toString();
        } //End block
        {
            s = ret.toString();
        } //End block
        return dsTaint.getTaintString();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.166 -0400", hash_original_method = "0DD0C2CAC9DFFC256536DE8141109DEC", hash_generated_method = "389C5DA93C0E96A673F275505031E968")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void processUnsolicited(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int response;
        Object ret;
        response = p.readInt();
        try 
        {
            //Begin case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
            ret =  responseVoid(p);
            //End case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
            //Begin case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED 
            ret =  responseVoid(p);
            //End case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED 
            //Begin case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED 
            ret =  responseVoid(p);
            //End case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED 
            //Begin case RIL_UNSOL_RESPONSE_NEW_SMS 
            ret =  responseString(p);
            //End case RIL_UNSOL_RESPONSE_NEW_SMS 
            //Begin case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT 
            ret =  responseString(p);
            //End case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT 
            //Begin case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
            ret =  responseInts(p);
            //End case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
            //Begin case RIL_UNSOL_ON_USSD 
            ret =  responseStrings(p);
            //End case RIL_UNSOL_ON_USSD 
            //Begin case RIL_UNSOL_NITZ_TIME_RECEIVED 
            ret =  responseString(p);
            //End case RIL_UNSOL_NITZ_TIME_RECEIVED 
            //Begin case RIL_UNSOL_SIGNAL_STRENGTH 
            ret = responseSignalStrength(p);
            //End case RIL_UNSOL_SIGNAL_STRENGTH 
            //Begin case RIL_UNSOL_DATA_CALL_LIST_CHANGED 
            ret = responseDataCallList(p);
            //End case RIL_UNSOL_DATA_CALL_LIST_CHANGED 
            //Begin case RIL_UNSOL_SUPP_SVC_NOTIFICATION 
            ret = responseSuppServiceNotification(p);
            //End case RIL_UNSOL_SUPP_SVC_NOTIFICATION 
            //Begin case RIL_UNSOL_STK_SESSION_END 
            ret = responseVoid(p);
            //End case RIL_UNSOL_STK_SESSION_END 
            //Begin case RIL_UNSOL_STK_PROACTIVE_COMMAND 
            ret = responseString(p);
            //End case RIL_UNSOL_STK_PROACTIVE_COMMAND 
            //Begin case RIL_UNSOL_STK_EVENT_NOTIFY 
            ret = responseString(p);
            //End case RIL_UNSOL_STK_EVENT_NOTIFY 
            //Begin case RIL_UNSOL_STK_CALL_SETUP 
            ret = responseInts(p);
            //End case RIL_UNSOL_STK_CALL_SETUP 
            //Begin case RIL_UNSOL_SIM_SMS_STORAGE_FULL 
            ret =  responseVoid(p);
            //End case RIL_UNSOL_SIM_SMS_STORAGE_FULL 
            //Begin case RIL_UNSOL_SIM_REFRESH 
            ret =  responseInts(p);
            //End case RIL_UNSOL_SIM_REFRESH 
            //Begin case RIL_UNSOL_CALL_RING 
            ret =  responseCallRing(p);
            //End case RIL_UNSOL_CALL_RING 
            //Begin case RIL_UNSOL_RESTRICTED_STATE_CHANGED 
            ret = responseInts(p);
            //End case RIL_UNSOL_RESTRICTED_STATE_CHANGED 
            //Begin case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED 
            ret =  responseVoid(p);
            //End case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED 
            //Begin case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
            ret =  responseCdmaSms(p);
            //End case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
            //Begin case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS 
            ret =  responseRaw(p);
            //End case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS 
            //Begin case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL 
            ret =  responseVoid(p);
            //End case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL 
            //Begin case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE 
            ret = responseVoid(p);
            //End case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE 
            //Begin case RIL_UNSOL_CDMA_CALL_WAITING 
            ret = responseCdmaCallWaiting(p);
            //End case RIL_UNSOL_CDMA_CALL_WAITING 
            //Begin case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS 
            ret = responseInts(p);
            //End case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS 
            //Begin case RIL_UNSOL_CDMA_INFO_REC 
            ret = responseCdmaInformationRecord(p);
            //End case RIL_UNSOL_CDMA_INFO_REC 
            //Begin case RIL_UNSOL_OEM_HOOK_RAW 
            ret = responseRaw(p);
            //End case RIL_UNSOL_OEM_HOOK_RAW 
            //Begin case RIL_UNSOL_RINGBACK_TONE 
            ret = responseInts(p);
            //End case RIL_UNSOL_RINGBACK_TONE 
            //Begin case RIL_UNSOL_RESEND_INCALL_MUTE 
            ret = responseVoid(p);
            //End case RIL_UNSOL_RESEND_INCALL_MUTE 
            //Begin case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED 
            ret = responseInts(p);
            //End case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED 
            //Begin case RIL_UNSOl_CDMA_PRL_CHANGED 
            ret = responseInts(p);
            //End case RIL_UNSOl_CDMA_PRL_CHANGED 
            //Begin case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE 
            ret = responseVoid(p);
            //End case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE 
            //Begin case RIL_UNSOL_RIL_CONNECTED 
            ret = responseInts(p);
            //End case RIL_UNSOL_RIL_CONNECTED 
            //Begin case default 
            if (DroidSafeAndroidRuntime.control) throw new RuntimeException("Unrecognized unsol response: " + response);
            //End case default 
        } //End block
        catch (Throwable tr)
        { }
        //Begin case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
        RadioState newState;
        newState = getRadioStateFromInt(p.readInt());
        //End case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
        unsljLogMore(response, newState.toString());
        //End case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
        switchToRadioState(newState);
        //End case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED 
        unsljLog(response);
        //End case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED 
        mCallStateRegistrants
                    .notifyRegistrants(new AsyncResult(null, null, null));
        //End case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED 
        unsljLog(response);
        //End case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED 
        mVoiceNetworkStateRegistrants
                    .notifyRegistrants(new AsyncResult(null, null, null));
        //End case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_NEW_SMS 
        {
            unsljLog(response);
            String a[];
            a = new String[2];
            a[1] = (String)ret;
            SmsMessage sms;
            sms = SmsMessage.newFromCMT(a);
            {
                mGsmSmsRegistrant
                        .notifyRegistrant(new AsyncResult(null, sms, null));
            } //End block
        } //End block
        //End case RIL_UNSOL_RESPONSE_NEW_SMS 
        //Begin case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT 
        //Begin case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT 
        {
            mSmsStatusRegistrant.notifyRegistrant(
                            new AsyncResult(null, ret, null));
        } //End block
        //End case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT 
        //Begin case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
        //Begin case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
        int[] smsIndex;
        smsIndex = (int[])ret;
        //End case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
        //Begin case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
        {
            {
                mSmsOnSimRegistrant.
                                notifyRegistrant(new AsyncResult(null, smsIndex, null));
            } //End block
        } //End block
        {
            riljLog(" NEW_SMS_ON_SIM ERROR with wrong length "
                            + smsIndex.length);
        } //End block
        //End case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM 
        //Begin case RIL_UNSOL_ON_USSD 
        String[] resp;
        resp = (String[])ret;
        //End case RIL_UNSOL_ON_USSD 
        //Begin case RIL_UNSOL_ON_USSD 
        {
            resp = new String[2];
            resp[0] = ((String[])ret)[0];
            resp[1] = null;
        } //End block
        //End case RIL_UNSOL_ON_USSD 
        //Begin case RIL_UNSOL_ON_USSD 
        unsljLogMore(response, resp[0]);
        //End case RIL_UNSOL_ON_USSD 
        //Begin case RIL_UNSOL_ON_USSD 
        {
            mUSSDRegistrant.notifyRegistrant(
                        new AsyncResult (null, resp, null));
        } //End block
        //End case RIL_UNSOL_ON_USSD 
        //Begin case RIL_UNSOL_NITZ_TIME_RECEIVED 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_NITZ_TIME_RECEIVED 
        //Begin case RIL_UNSOL_NITZ_TIME_RECEIVED 
        long nitzReceiveTime;
        nitzReceiveTime = p.readLong();
        //End case RIL_UNSOL_NITZ_TIME_RECEIVED 
        //Begin case RIL_UNSOL_NITZ_TIME_RECEIVED 
        Object[] result;
        result = new Object[2];
        //End case RIL_UNSOL_NITZ_TIME_RECEIVED 
        //Begin case RIL_UNSOL_NITZ_TIME_RECEIVED 
        result[0] = ret;
        //End case RIL_UNSOL_NITZ_TIME_RECEIVED 
        //Begin case RIL_UNSOL_NITZ_TIME_RECEIVED 
        result[1] = Long.valueOf(nitzReceiveTime);
        //End case RIL_UNSOL_NITZ_TIME_RECEIVED 
        //Begin case RIL_UNSOL_NITZ_TIME_RECEIVED 
        {
            mNITZTimeRegistrant
                        .notifyRegistrant(new AsyncResult (null, result, null));
        } //End block
        {
            mLastNITZTimeInfo = result;
        } //End block
        //End case RIL_UNSOL_NITZ_TIME_RECEIVED 
        //Begin case RIL_UNSOL_SIGNAL_STRENGTH 
        unsljLogvRet(response, ret);
        //End case RIL_UNSOL_SIGNAL_STRENGTH 
        //Begin case RIL_UNSOL_SIGNAL_STRENGTH 
        {
            mSignalStrengthRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_SIGNAL_STRENGTH 
        //Begin case RIL_UNSOL_DATA_CALL_LIST_CHANGED 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_DATA_CALL_LIST_CHANGED 
        //Begin case RIL_UNSOL_DATA_CALL_LIST_CHANGED 
        mDataNetworkStateRegistrants.notifyRegistrants(new AsyncResult(null, ret, null));
        //End case RIL_UNSOL_DATA_CALL_LIST_CHANGED 
        //Begin case RIL_UNSOL_SUPP_SVC_NOTIFICATION 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_SUPP_SVC_NOTIFICATION 
        //Begin case RIL_UNSOL_SUPP_SVC_NOTIFICATION 
        {
            mSsnRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_SUPP_SVC_NOTIFICATION 
        //Begin case RIL_UNSOL_STK_SESSION_END 
        unsljLog(response);
        //End case RIL_UNSOL_STK_SESSION_END 
        //Begin case RIL_UNSOL_STK_SESSION_END 
        {
            mCatSessionEndRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_STK_SESSION_END 
        //Begin case RIL_UNSOL_STK_PROACTIVE_COMMAND 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_STK_PROACTIVE_COMMAND 
        //Begin case RIL_UNSOL_STK_PROACTIVE_COMMAND 
        {
            mCatProCmdRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_STK_PROACTIVE_COMMAND 
        //Begin case RIL_UNSOL_STK_EVENT_NOTIFY 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_STK_EVENT_NOTIFY 
        //Begin case RIL_UNSOL_STK_EVENT_NOTIFY 
        {
            mCatEventRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_STK_EVENT_NOTIFY 
        //Begin case RIL_UNSOL_STK_CALL_SETUP 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_STK_CALL_SETUP 
        //Begin case RIL_UNSOL_STK_CALL_SETUP 
        {
            mCatCallSetUpRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_STK_CALL_SETUP 
        //Begin case RIL_UNSOL_SIM_SMS_STORAGE_FULL 
        unsljLog(response);
        //End case RIL_UNSOL_SIM_SMS_STORAGE_FULL 
        //Begin case RIL_UNSOL_SIM_SMS_STORAGE_FULL 
        {
            mIccSmsFullRegistrant.notifyRegistrant();
        } //End block
        //End case RIL_UNSOL_SIM_SMS_STORAGE_FULL 
        //Begin case RIL_UNSOL_SIM_REFRESH 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_SIM_REFRESH 
        //Begin case RIL_UNSOL_SIM_REFRESH 
        {
            mIccRefreshRegistrants.notifyRegistrants(
                            new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_SIM_REFRESH 
        //Begin case RIL_UNSOL_CALL_RING 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_CALL_RING 
        //Begin case RIL_UNSOL_CALL_RING 
        {
            mRingRegistrant.notifyRegistrant(
                            new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_CALL_RING 
        //Begin case RIL_UNSOL_RESTRICTED_STATE_CHANGED 
        unsljLogvRet(response, ret);
        //End case RIL_UNSOL_RESTRICTED_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESTRICTED_STATE_CHANGED 
        {
            mRestrictedStateRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_RESTRICTED_STATE_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED 
        unsljLog(response);
        //End case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED 
        {
            mIccStatusChangedRegistrants.notifyRegistrants();
        } //End block
        //End case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED 
        //Begin case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
        unsljLog(response);
        //End case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
        //Begin case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
        SmsMessage sms;
        sms = (SmsMessage) ret;
        //End case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
        //Begin case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
        {
            mCdmaSmsRegistrant
                        .notifyRegistrant(new AsyncResult(null, sms, null));
        } //End block
        //End case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS 
        //Begin case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS 
        unsljLog(response);
        //End case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS 
        //Begin case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS 
        {
            mGsmBroadcastSmsRegistrant
                        .notifyRegistrant(new AsyncResult(null, ret, null));
        } //End block
        //End case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS 
        //Begin case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL 
        unsljLog(response);
        //End case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL 
        //Begin case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL 
        {
            mIccSmsFullRegistrant.notifyRegistrant();
        } //End block
        //End case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL 
        //Begin case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE 
        unsljLog(response);
        //End case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE 
        //Begin case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE 
        {
            mEmergencyCallbackModeRegistrant.notifyRegistrant();
        } //End block
        //End case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE 
        //Begin case RIL_UNSOL_CDMA_CALL_WAITING 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_CDMA_CALL_WAITING 
        //Begin case RIL_UNSOL_CDMA_CALL_WAITING 
        {
            mCallWaitingInfoRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_CDMA_CALL_WAITING 
        //Begin case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS 
        //Begin case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS 
        {
            mOtaProvisionRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS 
        //Begin case RIL_UNSOL_CDMA_INFO_REC 
        ArrayList<CdmaInformationRecords> listInfoRecs;
        //End case RIL_UNSOL_CDMA_INFO_REC 
        //Begin case RIL_UNSOL_CDMA_INFO_REC 
        try 
        {
            listInfoRecs = (ArrayList<CdmaInformationRecords>)ret;
        } //End block
        catch (ClassCastException e)
        { }
        //End case RIL_UNSOL_CDMA_INFO_REC 
        //Begin case RIL_UNSOL_CDMA_INFO_REC 
        {
            Iterator<CdmaInformationRecords> var31392388BAFC4C31423B73000D3019F0_280060337 = (listInfoRecs).iterator();
            var31392388BAFC4C31423B73000D3019F0_280060337.hasNext();
            CdmaInformationRecords rec = var31392388BAFC4C31423B73000D3019F0_280060337.next();
            {
                unsljLogRet(response, rec);
                notifyRegistrantsCdmaInfoRec(rec);
            } //End block
        } //End collapsed parenthetic
        //End case RIL_UNSOL_CDMA_INFO_REC 
        //Begin case RIL_UNSOL_OEM_HOOK_RAW 
        unsljLogvRet(response, IccUtils.bytesToHexString((byte[])ret));
        //End case RIL_UNSOL_OEM_HOOK_RAW 
        //Begin case RIL_UNSOL_OEM_HOOK_RAW 
        {
            mUnsolOemHookRawRegistrant.notifyRegistrant(new AsyncResult(null, ret, null));
        } //End block
        //End case RIL_UNSOL_OEM_HOOK_RAW 
        //Begin case RIL_UNSOL_RINGBACK_TONE 
        unsljLogvRet(response, ret);
        //End case RIL_UNSOL_RINGBACK_TONE 
        //Begin case RIL_UNSOL_RINGBACK_TONE 
        {
            boolean playtone;
            playtone = (((int[])ret)[0] == 1);
            mRingbackToneRegistrants.notifyRegistrants(
                                        new AsyncResult (null, playtone, null));
        } //End block
        //End case RIL_UNSOL_RINGBACK_TONE 
        //Begin case RIL_UNSOL_RESEND_INCALL_MUTE 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_RESEND_INCALL_MUTE 
        //Begin case RIL_UNSOL_RESEND_INCALL_MUTE 
        {
            mResendIncallMuteRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_RESEND_INCALL_MUTE 
        //Begin case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED 
        //Begin case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED 
        {
            mCdmaSubscriptionChangedRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED 
        //Begin case RIL_UNSOl_CDMA_PRL_CHANGED 
        unsljLogRet(response, ret);
        //End case RIL_UNSOl_CDMA_PRL_CHANGED 
        //Begin case RIL_UNSOl_CDMA_PRL_CHANGED 
        {
            mCdmaPrlChangedRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
        } //End block
        //End case RIL_UNSOl_CDMA_PRL_CHANGED 
        //Begin case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE 
        unsljLogRet(response, ret);
        //End case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE 
        //Begin case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE 
        {
            mExitEmergencyCallbackModeRegistrants.notifyRegistrants(
                                        new AsyncResult (null, null, null));
        } //End block
        //End case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE 
        //Begin case RIL_UNSOL_RIL_CONNECTED 
        {
            unsljLogRet(response, ret);
            setRadioPower(false, null);
            setPreferredNetworkType(mPreferredNetworkType, null);
            setCdmaSubscriptionSource(mCdmaSubscription, null);
            notifyRegistrantsRilConnectionChanged(((int[])ret)[0]);
        } //End block
        //End case RIL_UNSOL_RIL_CONNECTED 
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.167 -0400", hash_original_method = "B856EBB2256F144A73FE56175A9B6A36", hash_generated_method = "76BB77490E9BB1B181AA8D46BB3E6E72")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void notifyRegistrantsRilConnectionChanged(int rilVer) {
        dsTaint.addTaint(rilVer);
        mRilVersion = rilVer;
        {
            mRilConnectedRegistrants.notifyRegistrants(
                                new AsyncResult (null, new Integer(rilVer), null));
        } //End block
        // ---------- Original Method ----------
        //mRilVersion = rilVer;
        //if (mRilConnectedRegistrants != null) {
            //mRilConnectedRegistrants.notifyRegistrants(
                                //new AsyncResult (null, new Integer(rilVer), null));
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.168 -0400", hash_original_method = "2C7447093749DDBD708485E3CB5D7194", hash_generated_method = "6A6F883BB06A7ABD344423F6B88D27EB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseInts(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int numInts;
        int response[];
        numInts = p.readInt();
        response = new int[numInts];
        {
            int i;
            i = 0;
            {
                response[i] = p.readInt();
            } //End block
        } //End collapsed parenthetic
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int numInts;
        //int response[];
        //numInts = p.readInt();
        //response = new int[numInts];
        //for (int i = 0 ; i < numInts ; i++) {
            //response[i] = p.readInt();
        //}
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.168 -0400", hash_original_method = "F83E9E0330A999E5D5277EA606DEC7ED", hash_generated_method = "7919781646BDA542DE3A423377032CA0")
    @DSModeled(DSC.SAFE)
    private Object responseVoid(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //return null;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.169 -0400", hash_original_method = "A9CCF034900EFF9E845FDB7DB22E08D2", hash_generated_method = "0B120DBE201F107408B40E483E10A115")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseCallForward(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int numInfos;
        CallForwardInfo infos[];
        numInfos = p.readInt();
        infos = new CallForwardInfo[numInfos];
        {
            int i;
            i = 0;
            {
                infos[i] = new CallForwardInfo();
                infos[i].status = p.readInt();
                infos[i].reason = p.readInt();
                infos[i].serviceClass = p.readInt();
                infos[i].toa = p.readInt();
                infos[i].number = p.readString();
                infos[i].timeSeconds = p.readInt();
            } //End block
        } //End collapsed parenthetic
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int numInfos;
        //CallForwardInfo infos[];
        //numInfos = p.readInt();
        //infos = new CallForwardInfo[numInfos];
        //for (int i = 0 ; i < numInfos ; i++) {
            //infos[i] = new CallForwardInfo();
            //infos[i].status = p.readInt();
            //infos[i].reason = p.readInt();
            //infos[i].serviceClass = p.readInt();
            //infos[i].toa = p.readInt();
            //infos[i].number = p.readString();
            //infos[i].timeSeconds = p.readInt();
        //}
        //return infos;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.169 -0400", hash_original_method = "6CA2F91918640E9DE536352B73E673A3", hash_generated_method = "2DF602B25B88CD050926A5D77EC9DB2D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseSuppServiceNotification(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        SuppServiceNotification notification;
        notification = new SuppServiceNotification();
        notification.notificationType = p.readInt();
        notification.code = p.readInt();
        notification.index = p.readInt();
        notification.type = p.readInt();
        notification.number = p.readString();
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //SuppServiceNotification notification = new SuppServiceNotification();
        //notification.notificationType = p.readInt();
        //notification.code = p.readInt();
        //notification.index = p.readInt();
        //notification.type = p.readInt();
        //notification.number = p.readString();
        //return notification;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.170 -0400", hash_original_method = "F3FE5FFB00EF9163091B0B3C9957ECDA", hash_generated_method = "6B3EA16048BB6F986941C572890AA0B0")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseCdmaSms(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        SmsMessage sms;
        sms = SmsMessage.newFromParcel(p);
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //SmsMessage sms;
        //sms = SmsMessage.newFromParcel(p);
        //return sms;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.170 -0400", hash_original_method = "E706FBE3176786F101EC24273C477E8B", hash_generated_method = "0BBAAE2D6D60B0A0508B7E12A5415553")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseString(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        String response;
        response = p.readString();
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //String response;
        //response = p.readString();
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.171 -0400", hash_original_method = "D52365FD6A67C03CB48CBBF364BA22AF", hash_generated_method = "44FB9F02D2187E672F00D3A1A7FF4E55")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseStrings(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int num;
        String response[];
        response = p.readStringArray();
        {
            num = p.readInt();
            response = new String[num];
            {
                int i;
                i = 0;
                {
                    response[i] = p.readString();
                } //End block
            } //End collapsed parenthetic
        } //End block
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int num;
        //String response[];
        //response = p.readStringArray();
        //if (false) {
            //num = p.readInt();
            //response = new String[num];
            //for (int i = 0; i < num; i++) {
                //response[i] = p.readString();
            //}
        //}
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.171 -0400", hash_original_method = "B7DD4FAF5DCF16E3869A5E71E7C7982F", hash_generated_method = "912DA28CA1153FFB6BC0C65A7F51CE14")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseRaw(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int num;
        byte response[];
        response = p.createByteArray();
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int num;
        //byte response[];
        //response = p.createByteArray();
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.171 -0400", hash_original_method = "6A2331BAD13DEAF24E14B96082F25F22", hash_generated_method = "F7EBBAFB1E5A09A1B621338019473BDB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseSMS(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int messageRef, errorCode;
        String ackPDU;
        messageRef = p.readInt();
        ackPDU = p.readString();
        errorCode = p.readInt();
        SmsResponse response;
        response = new SmsResponse(messageRef, ackPDU, errorCode);
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int messageRef, errorCode;
        //String ackPDU;
        //messageRef = p.readInt();
        //ackPDU = p.readString();
        //errorCode = p.readInt();
        //SmsResponse response = new SmsResponse(messageRef, ackPDU, errorCode);
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.172 -0400", hash_original_method = "36285902341CFAECFF4B1CF0D454D382", hash_generated_method = "649D785B2B8003225C2DE3D1A5F4A275")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseICC_IO(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int sw1, sw2;
        byte data[];
        data = null;
        Message ret;
        sw1 = p.readInt();
        sw2 = p.readInt();
        String s;
        s = p.readString();
        riljLog("< iccIO: "
                + " 0x" + Integer.toHexString(sw1)
                + " 0x" + Integer.toHexString(sw2) + " "
                + s);
        Object var413AC4F2D944EB5161FEAD2F56EEF453_856093520 = (new IccIoResult(sw1, sw2, s));
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int sw1, sw2;
        //byte data[] = null;
        //Message ret;
        //sw1 = p.readInt();
        //sw2 = p.readInt();
        //String s = p.readString();
        //if (RILJ_LOGV) riljLog("< iccIO: "
                //+ " 0x" + Integer.toHexString(sw1)
                //+ " 0x" + Integer.toHexString(sw2) + " "
                //+ s);
        //return new IccIoResult(sw1, sw2, s);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.173 -0400", hash_original_method = "D5683AC0AFFFFAC7278B155F54988C15", hash_generated_method = "4402213C811F22BF175829B14D3AEAC4")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseIccCardStatus(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        IccCardApplication ca;
        IccCardStatus status;
        status = new IccCardStatus();
        status.setCardState(p.readInt());
        status.setUniversalPinState(p.readInt());
        status.setGsmUmtsSubscriptionAppIndex(p.readInt());
        status.setCdmaSubscriptionAppIndex(p.readInt());
        status.setImsSubscriptionAppIndex(p.readInt());
        int numApplications;
        numApplications = p.readInt();
        {
            numApplications = IccCardStatus.CARD_MAX_APPS;
        } //End block
        status.setNumApplications(numApplications);
        {
            int i;
            i = 0;
            {
                ca = new IccCardApplication();
                ca.app_type       = ca.AppTypeFromRILInt(p.readInt());
                ca.app_state      = ca.AppStateFromRILInt(p.readInt());
                ca.perso_substate = ca.PersoSubstateFromRILInt(p.readInt());
                ca.aid            = p.readString();
                ca.app_label      = p.readString();
                ca.pin1_replaced  = p.readInt();
                ca.pin1           = ca.PinStateFromRILInt(p.readInt());
                ca.pin2           = ca.PinStateFromRILInt(p.readInt());
                status.addApplication(ca);
            } //End block
        } //End collapsed parenthetic
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.174 -0400", hash_original_method = "26536CEB9BA98624BF6B84456E63F303", hash_generated_method = "59FC292F8FEC8C51554A266DEA51D1F5")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseCallList(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int num;
        int voiceSettings;
        ArrayList<DriverCall> response;
        DriverCall dc;
        num = p.readInt();
        response = new ArrayList<DriverCall>(num);
        {
            int i;
            i = 0;
            {
                dc = new DriverCall();
                dc.state = DriverCall.stateFromCLCC(p.readInt());
                dc.index = p.readInt();
                dc.TOA = p.readInt();
                dc.isMpty = (0 != p.readInt());
                dc.isMT = (0 != p.readInt());
                dc.als = p.readInt();
                voiceSettings = p.readInt();
                dc.isVoice = (0 == voiceSettings) ? false : true;
                dc.isVoicePrivacy = (0 != p.readInt());
                dc.number = p.readString();
                int np;
                np = p.readInt();
                dc.numberPresentation = DriverCall.presentationFromCLIP(np);
                dc.name = p.readString();
                dc.namePresentation = p.readInt();
                int uusInfoPresent;
                uusInfoPresent = p.readInt();
                {
                    dc.uusInfo = new UUSInfo();
                    dc.uusInfo.setType(p.readInt());
                    dc.uusInfo.setDcs(p.readInt());
                    byte[] userData;
                    userData = p.createByteArray();
                    dc.uusInfo.setUserData(userData);
                    riljLogv(String.format("Incoming UUS : type=%d, dcs=%d, length=%d",
                                dc.uusInfo.getType(), dc.uusInfo.getDcs(),
                                dc.uusInfo.getUserData().length));
                    riljLogv("Incoming UUS : data (string)="
                        + new String(dc.uusInfo.getUserData()));
                    riljLogv("Incoming UUS : data (hex): "
                        + IccUtils.bytesToHexString(dc.uusInfo.getUserData()));
                } //End block
                {
                    riljLogv("Incoming UUS : NOT present!");
                } //End block
                dc.number = PhoneNumberUtils.stringFromStringAndTOA(dc.number, dc.TOA);
                response.add(dc);
                {
                    mVoicePrivacyOnRegistrants.notifyRegistrants();
                    riljLog("InCall VoicePrivacy is enabled");
                } //End block
                {
                    mVoicePrivacyOffRegistrants.notifyRegistrants();
                    riljLog("InCall VoicePrivacy is disabled");
                } //End block
            } //End block
        } //End collapsed parenthetic
        Collections.sort(response);
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.175 -0400", hash_original_method = "ED5CAFCBF47B1330CD2566A0B897CA0C", hash_generated_method = "359E1FDB6A5F418C384FFF53938D5BD4")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private DataCallState getDataCallState(Parcel p, int version) {
        dsTaint.addTaint(p.dsTaint);
        dsTaint.addTaint(version);
        DataCallState dataCall;
        dataCall = new DataCallState();
        dataCall.version = version;
        {
            dataCall.cid = p.readInt();
            dataCall.active = p.readInt();
            dataCall.type = p.readString();
            String addresses;
            addresses = p.readString();
            {
                boolean varE215C54D9705E023ED235AD30A4A0970_367077620 = (!TextUtils.isEmpty(addresses));
                {
                    dataCall.addresses = addresses.split(" ");
                } //End block
            } //End collapsed parenthetic
        } //End block
        {
            dataCall.status = p.readInt();
            dataCall.suggestedRetryTime = p.readInt();
            dataCall.cid = p.readInt();
            dataCall.active = p.readInt();
            dataCall.type = p.readString();
            dataCall.ifname = p.readString();
            {
                boolean var9C097F15414DD096BEEBECEC45CBC3B3_243586274 = ((dataCall.status == DataConnection.FailCause.NONE.getErrorCode()) &&
                    TextUtils.isEmpty(dataCall.ifname));
                {
                    if (DroidSafeAndroidRuntime.control) throw new RuntimeException("getDataCallState, no ifname");
                } //End block
            } //End collapsed parenthetic
            String addresses;
            addresses = p.readString();
            {
                boolean varE215C54D9705E023ED235AD30A4A0970_1223673769 = (!TextUtils.isEmpty(addresses));
                {
                    dataCall.addresses = addresses.split(" ");
                } //End block
            } //End collapsed parenthetic
            String dnses;
            dnses = p.readString();
            {
                boolean var22967F124DA8C753464DD7656A6CF5D6_393644785 = (!TextUtils.isEmpty(dnses));
                {
                    dataCall.dnses = dnses.split(" ");
                } //End block
            } //End collapsed parenthetic
            String gateways;
            gateways = p.readString();
            {
                boolean var68C55CDE386BBAC0DE45A0309B2750DE_642633504 = (!TextUtils.isEmpty(gateways));
                {
                    dataCall.gateways = gateways.split(" ");
                } //End block
            } //End collapsed parenthetic
        } //End block
        return (DataCallState)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.176 -0400", hash_original_method = "123D49A321BB97EFA9843423F7545450", hash_generated_method = "7A71E2644D344674C2A256084615B14F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseDataCallList(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        ArrayList<DataCallState> response;
        int ver;
        ver = p.readInt();
        int num;
        num = p.readInt();
        riljLog("responseDataCallList ver=" + ver + " num=" + num);
        response = new ArrayList<DataCallState>(num);
        {
            int i;
            i = 0;
            {
                response.add(getDataCallState(p, ver));
            } //End block
        } //End collapsed parenthetic
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //ArrayList<DataCallState> response;
        //int ver = p.readInt();
        //int num = p.readInt();
        //riljLog("responseDataCallList ver=" + ver + " num=" + num);
        //response = new ArrayList<DataCallState>(num);
        //for (int i = 0; i < num; i++) {
            //response.add(getDataCallState(p, ver));
        //}
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.177 -0400", hash_original_method = "6E11D362E553C380CF6ADBFD5304CD05", hash_generated_method = "1A20BDB215B0FAD0DF8B1D13CB3F28D9")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseSetupDataCall(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int ver;
        ver = p.readInt();
        int num;
        num = p.readInt();
        riljLog("responseSetupDataCall ver=" + ver + " num=" + num);
        DataCallState dataCall;
        {
            dataCall = new DataCallState();
            dataCall.version = ver;
            dataCall.cid = Integer.parseInt(p.readString());
            dataCall.ifname = p.readString();
            {
                boolean varFCAE9407C997419D11FB63DAA27B5891_1950629210 = (TextUtils.isEmpty(dataCall.ifname));
                {
                    if (DroidSafeAndroidRuntime.control) throw new RuntimeException(
                        "RIL_REQUEST_SETUP_DATA_CALL response, no ifname");
                } //End block
            } //End collapsed parenthetic
            String addresses;
            addresses = p.readString();
            {
                boolean varE215C54D9705E023ED235AD30A4A0970_1173184254 = (!TextUtils.isEmpty(addresses));
                {
                    dataCall.addresses = addresses.split(" ");
                } //End block
            } //End collapsed parenthetic
            {
                String dnses;
                dnses = p.readString();
                riljLog("responseSetupDataCall got dnses=" + dnses);
                {
                    boolean varA216814F255A7125BD1D54E6A9B9165D_432110295 = (!TextUtils.isEmpty(dnses));
                    {
                        dataCall.dnses = dnses.split(" ");
                    } //End block
                } //End collapsed parenthetic
            } //End block
            {
                String gateways;
                gateways = p.readString();
                riljLog("responseSetupDataCall got gateways=" + gateways);
                {
                    boolean var851B476C40C2CFC5CF415FF73D1F92EC_1259054945 = (!TextUtils.isEmpty(gateways));
                    {
                        dataCall.gateways = gateways.split(" ");
                    } //End block
                } //End collapsed parenthetic
            } //End block
        } //End block
        {
            {
                if (DroidSafeAndroidRuntime.control) throw new RuntimeException(
                        "RIL_REQUEST_SETUP_DATA_CALL response expecting 1 RIL_Data_Call_response_v5"
                        + " got " + num);
            } //End block
            dataCall = getDataCallState(p, ver);
        } //End block
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.179 -0400", hash_original_method = "F2D5C4032BE6024B6126759412E50A01", hash_generated_method = "5A8BC953D41B9B4406F11E135A0BB53A")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseOperatorInfos(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        String strings[];
        strings = (String [])responseStrings(p);
        ArrayList<OperatorInfo> ret;
        {
            if (DroidSafeAndroidRuntime.control) throw new RuntimeException(
                "RIL_REQUEST_QUERY_AVAILABLE_NETWORKS: invalid response. Got "
                + strings.length + " strings, expected multible of 4");
        } //End block
        ret = new ArrayList<OperatorInfo>(strings.length / 4);
        {
            int i;
            i = 0;
            i += 4;
            {
                ret.add (
                new OperatorInfo(
                    strings[i+0],
                    strings[i+1],
                    strings[i+2],
                    strings[i+3]));
            } //End block
        } //End collapsed parenthetic
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //String strings[] = (String [])responseStrings(p);
        //ArrayList<OperatorInfo> ret;
        //if (strings.length % 4 != 0) {
            //throw new RuntimeException(
                //"RIL_REQUEST_QUERY_AVAILABLE_NETWORKS: invalid response. Got "
                //+ strings.length + " strings, expected multible of 4");
        //}
        //ret = new ArrayList<OperatorInfo>(strings.length / 4);
        //for (int i = 0 ; i < strings.length ; i += 4) {
            //ret.add (
                //new OperatorInfo(
                    //strings[i+0],
                    //strings[i+1],
                    //strings[i+2],
                    //strings[i+3]));
        //}
        //return ret;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.180 -0400", hash_original_method = "E4123D5DA30749CB6D4FFE47F8C7BEAC", hash_generated_method = "5E83B73C02DDA0B3903CA0256FDB9A2E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseCellList(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int num, rssi;
        String location;
        ArrayList<NeighboringCellInfo> response;
        NeighboringCellInfo cell;
        num = p.readInt();
        response = new ArrayList<NeighboringCellInfo>();
        String radioString;
        radioString = SystemProperties.get(
               TelephonyProperties.PROPERTY_DATA_NETWORK_TYPE, "unknown");
        int radioType;
        {
            boolean var968A11328ED5DD3D9A4D0157DF90DD59_1207609191 = (radioString.equals("GPRS"));
            {
                radioType = NETWORK_TYPE_GPRS;
            } //End block
            {
                boolean varA1A3CAAABF34B78222EF834ECB712EBE_300965087 = (radioString.equals("EDGE"));
                {
                    radioType = NETWORK_TYPE_EDGE;
                } //End block
                {
                    boolean var281246F88355BADDC459CFA9135E481F_1098310332 = (radioString.equals("UMTS"));
                    {
                        radioType = NETWORK_TYPE_UMTS;
                    } //End block
                    {
                        boolean var3EAFEEE374799CED3C653463F8D1F789_228995903 = (radioString.equals("HSDPA"));
                        {
                            radioType = NETWORK_TYPE_HSDPA;
                        } //End block
                        {
                            boolean varA89604559FBE3A92D87A3C7FCC5B22A2_1974110710 = (radioString.equals("HSUPA"));
                            {
                                radioType = NETWORK_TYPE_HSUPA;
                            } //End block
                            {
                                boolean varA181BEC579269E2127B9ABCD5D79305A_1471826148 = (radioString.equals("HSPA"));
                                {
                                    radioType = NETWORK_TYPE_HSPA;
                                } //End block
                                {
                                    radioType = NETWORK_TYPE_UNKNOWN;
                                } //End block
                            } //End collapsed parenthetic
                        } //End collapsed parenthetic
                    } //End collapsed parenthetic
                } //End collapsed parenthetic
            } //End collapsed parenthetic
        } //End collapsed parenthetic
        {
            {
                int i;
                i = 0;
                {
                    rssi = p.readInt();
                    location = p.readString();
                    cell = new NeighboringCellInfo(rssi, location, radioType);
                    response.add(cell);
                } //End block
            } //End collapsed parenthetic
        } //End block
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.182 -0400", hash_original_method = "225C4A262B1740AD55FF47639121D5EF", hash_generated_method = "0E269623CCCF4199B824C11FC52BC9D3")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseGetPreferredNetworkType(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int [] response;
        response = (int[]) responseInts(p);
        {
            mPreferredNetworkType = response[0];
        } //End block
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int [] response = (int[]) responseInts(p);
        //if (response.length >= 1) {
           //mPreferredNetworkType = response[0];
       //}
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.183 -0400", hash_original_method = "C459166A8880B065FE8A1B083AFDAF89", hash_generated_method = "EB19A76E36FCC78383FE7E6D889A515D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseGmsBroadcastConfig(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int num;
        ArrayList<SmsBroadcastConfigInfo> response;
        SmsBroadcastConfigInfo info;
        num = p.readInt();
        response = new ArrayList<SmsBroadcastConfigInfo>(num);
        {
            int i;
            i = 0;
            {
                int fromId;
                fromId = p.readInt();
                int toId;
                toId = p.readInt();
                int fromScheme;
                fromScheme = p.readInt();
                int toScheme;
                toScheme = p.readInt();
                boolean selected;
                selected = (p.readInt() == 1);
                info = new SmsBroadcastConfigInfo(fromId, toId, fromScheme,
                    toScheme, selected);
                response.add(info);
            } //End block
        } //End collapsed parenthetic
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int num;
        //ArrayList<SmsBroadcastConfigInfo> response;
        //SmsBroadcastConfigInfo info;
        //num = p.readInt();
        //response = new ArrayList<SmsBroadcastConfigInfo>(num);
        //for (int i = 0; i < num; i++) {
            //int fromId = p.readInt();
            //int toId = p.readInt();
            //int fromScheme = p.readInt();
            //int toScheme = p.readInt();
            //boolean selected = (p.readInt() == 1);
            //info = new SmsBroadcastConfigInfo(fromId, toId, fromScheme,
                    //toScheme, selected);
            //response.add(info);
        //}
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.183 -0400", hash_original_method = "55259F8FDA4B0C41A34B6184E02D7C2F", hash_generated_method = "CEA0E2CA331FAB45E38F1E2FFDC29A84")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseCdmaBroadcastConfig(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int numServiceCategories;
        int response[];
        numServiceCategories = p.readInt();
        {
            int numInts;
            numInts = CDMA_BROADCAST_SMS_NO_OF_SERVICE_CATEGORIES * CDMA_BSI_NO_OF_INTS_STRUCT + 1;
            response = new int[numInts];
            response[0] = CDMA_BROADCAST_SMS_NO_OF_SERVICE_CATEGORIES;
            {
                int i;
                i = 1;
                i += CDMA_BSI_NO_OF_INTS_STRUCT;
                {
                    response[i + 0] = i / CDMA_BSI_NO_OF_INTS_STRUCT;
                    response[i + 1] = 1;
                    response[i + 2] = 0;
                } //End block
            } //End collapsed parenthetic
        } //End block
        {
            int numInts;
            numInts = (numServiceCategories * CDMA_BSI_NO_OF_INTS_STRUCT) + 1;
            response = new int[numInts];
            response[0] = numServiceCategories;
            {
                int i;
                i = 1;
                {
                    response[i] = p.readInt();
                } //End block
            } //End collapsed parenthetic
        } //End block
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.184 -0400", hash_original_method = "90CED6467BA07D7B46A1F95705E3AC70", hash_generated_method = "20651773747CF412CC38B527481D0BFB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseSignalStrength(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int numInts;
        numInts = 12;
        int response[];
        response = new int[numInts];
        {
            int i;
            i = 0;
            {
                response[i] = p.readInt();
            } //End block
        } //End collapsed parenthetic
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int numInts = 12;
        //int response[];
        //response = new int[numInts];
        //for (int i = 0 ; i < numInts ; i++) {
            //response[i] = p.readInt();
        //}
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.185 -0400", hash_original_method = "07F57EA5026D76F851755C6714CD7CB2", hash_generated_method = "A239FFB93EFABB094CAD2E23A7ABE924")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private ArrayList<CdmaInformationRecords> responseCdmaInformationRecord(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        int numberOfInfoRecs;
        ArrayList<CdmaInformationRecords> response;
        numberOfInfoRecs = p.readInt();
        response = new ArrayList<CdmaInformationRecords>(numberOfInfoRecs);
        {
            int i;
            i = 0;
            {
                CdmaInformationRecords InfoRec;
                InfoRec = new CdmaInformationRecords(p);
                response.add(InfoRec);
            } //End block
        } //End collapsed parenthetic
        return (ArrayList<CdmaInformationRecords>)dsTaint.getTaint();
        // ---------- Original Method ----------
        //int numberOfInfoRecs;
        //ArrayList<CdmaInformationRecords> response;
        //numberOfInfoRecs = p.readInt();
        //response = new ArrayList<CdmaInformationRecords>(numberOfInfoRecs);
        //for (int i = 0; i < numberOfInfoRecs; i++) {
            //CdmaInformationRecords InfoRec = new CdmaInformationRecords(p);
            //response.add(InfoRec);
        //}
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.186 -0400", hash_original_method = "C7CB5D07451D489A78BF82365C5A0B27", hash_generated_method = "3B6C72DF262505DC46DAC21DF81A49FF")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseCdmaCallWaiting(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        CdmaCallWaitingNotification notification;
        notification = new CdmaCallWaitingNotification();
        notification.number = p.readString();
        notification.numberPresentation = notification.presentationFromCLIP(p.readInt());
        notification.name = p.readString();
        notification.namePresentation = notification.numberPresentation;
        notification.isPresent = p.readInt();
        notification.signalType = p.readInt();
        notification.alertPitch = p.readInt();
        notification.signal = p.readInt();
        notification.numberType = p.readInt();
        notification.numberPlan = p.readInt();
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //CdmaCallWaitingNotification notification = new CdmaCallWaitingNotification();
        //notification.number = p.readString();
        //notification.numberPresentation = notification.presentationFromCLIP(p.readInt());
        //notification.name = p.readString();
        //notification.namePresentation = notification.numberPresentation;
        //notification.isPresent = p.readInt();
        //notification.signalType = p.readInt();
        //notification.alertPitch = p.readInt();
        //notification.signal = p.readInt();
        //notification.numberType = p.readInt();
        //notification.numberPlan = p.readInt();
        //return notification;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.186 -0400", hash_original_method = "F4EF6B3A014077C64691F0B8D26D4AED", hash_generated_method = "525B13BF0A1B5B00B46E49CCF33207FD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Object responseCallRing(Parcel p) {
        dsTaint.addTaint(p.dsTaint);
        char response[];
        response = new char[4];
        response[0] = (char) p.readInt();
        response[1] = (char) p.readInt();
        response[2] = (char) p.readInt();
        response[3] = (char) p.readInt();
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //char response[] = new char[4];
        //response[0] = (char) p.readInt();
        //response[1] = (char) p.readInt();
        //response[2] = (char) p.readInt();
        //response[3] = (char) p.readInt();
        //return response;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.187 -0400", hash_original_method = "04666C2E99832FCFAFCF388A68EF0610", hash_generated_method = "CF2AEFD8D8FF2C53D58F431E8B6D8F61")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void notifyRegistrantsCdmaInfoRec(CdmaInformationRecords infoRec) {
        dsTaint.addTaint(infoRec.dsTaint);
        int response;
        response = RIL_UNSOL_CDMA_INFO_REC;
        {
            {
                unsljLogRet(response, infoRec.record);
                mDisplayInfoRegistrants.notifyRegistrants(
                        new AsyncResult (null, infoRec.record, null));
            } //End block
        } //End block
        {
            {
                unsljLogRet(response, infoRec.record);
                mSignalInfoRegistrants.notifyRegistrants(
                        new AsyncResult (null, infoRec.record, null));
            } //End block
        } //End block
        {
            {
                unsljLogRet(response, infoRec.record);
                mNumberInfoRegistrants.notifyRegistrants(
                        new AsyncResult (null, infoRec.record, null));
            } //End block
        } //End block
        {
            {
                unsljLogRet(response, infoRec.record);
                mRedirNumInfoRegistrants.notifyRegistrants(
                        new AsyncResult (null, infoRec.record, null));
            } //End block
        } //End block
        {
            {
                unsljLogRet(response, infoRec.record);
                mLineControlInfoRegistrants.notifyRegistrants(
                        new AsyncResult (null, infoRec.record, null));
            } //End block
        } //End block
        {
            {
                unsljLogRet(response, infoRec.record);
                mT53ClirInfoRegistrants.notifyRegistrants(
                        new AsyncResult (null, infoRec.record, null));
            } //End block
        } //End block
        {
            {
                unsljLogRet(response, infoRec.record);
                mT53AudCntrlInfoRegistrants.notifyRegistrants(
                       new AsyncResult (null, infoRec.record, null));
            } //End block
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        static String requestToString(int request) {
        switch(request) {
            case RIL_REQUEST_GET_SIM_STATUS: return "GET_SIM_STATUS";
            case RIL_REQUEST_ENTER_SIM_PIN: return "ENTER_SIM_PIN";
            case RIL_REQUEST_ENTER_SIM_PUK: return "ENTER_SIM_PUK";
            case RIL_REQUEST_ENTER_SIM_PIN2: return "ENTER_SIM_PIN2";
            case RIL_REQUEST_ENTER_SIM_PUK2: return "ENTER_SIM_PUK2";
            case RIL_REQUEST_CHANGE_SIM_PIN: return "CHANGE_SIM_PIN";
            case RIL_REQUEST_CHANGE_SIM_PIN2: return "CHANGE_SIM_PIN2";
            case RIL_REQUEST_ENTER_NETWORK_DEPERSONALIZATION: return "ENTER_NETWORK_DEPERSONALIZATION";
            case RIL_REQUEST_GET_CURRENT_CALLS: return "GET_CURRENT_CALLS";
            case RIL_REQUEST_DIAL: return "DIAL";
            case RIL_REQUEST_GET_IMSI: return "GET_IMSI";
            case RIL_REQUEST_HANGUP: return "HANGUP";
            case RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND: return "HANGUP_WAITING_OR_BACKGROUND";
            case RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND: return "HANGUP_FOREGROUND_RESUME_BACKGROUND";
            case RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE: return "REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE";
            case RIL_REQUEST_CONFERENCE: return "CONFERENCE";
            case RIL_REQUEST_UDUB: return "UDUB";
            case RIL_REQUEST_LAST_CALL_FAIL_CAUSE: return "LAST_CALL_FAIL_CAUSE";
            case RIL_REQUEST_SIGNAL_STRENGTH: return "SIGNAL_STRENGTH";
            case RIL_REQUEST_VOICE_REGISTRATION_STATE: return "VOICE_REGISTRATION_STATE";
            case RIL_REQUEST_DATA_REGISTRATION_STATE: return "DATA_REGISTRATION_STATE";
            case RIL_REQUEST_OPERATOR: return "OPERATOR";
            case RIL_REQUEST_RADIO_POWER: return "RADIO_POWER";
            case RIL_REQUEST_DTMF: return "DTMF";
            case RIL_REQUEST_SEND_SMS: return "SEND_SMS";
            case RIL_REQUEST_SEND_SMS_EXPECT_MORE: return "SEND_SMS_EXPECT_MORE";
            case RIL_REQUEST_SETUP_DATA_CALL: return "SETUP_DATA_CALL";
            case RIL_REQUEST_SIM_IO: return "SIM_IO";
            case RIL_REQUEST_SEND_USSD: return "SEND_USSD";
            case RIL_REQUEST_CANCEL_USSD: return "CANCEL_USSD";
            case RIL_REQUEST_GET_CLIR: return "GET_CLIR";
            case RIL_REQUEST_SET_CLIR: return "SET_CLIR";
            case RIL_REQUEST_QUERY_CALL_FORWARD_STATUS: return "QUERY_CALL_FORWARD_STATUS";
            case RIL_REQUEST_SET_CALL_FORWARD: return "SET_CALL_FORWARD";
            case RIL_REQUEST_QUERY_CALL_WAITING: return "QUERY_CALL_WAITING";
            case RIL_REQUEST_SET_CALL_WAITING: return "SET_CALL_WAITING";
            case RIL_REQUEST_SMS_ACKNOWLEDGE: return "SMS_ACKNOWLEDGE";
            case RIL_REQUEST_GET_IMEI: return "GET_IMEI";
            case RIL_REQUEST_GET_IMEISV: return "GET_IMEISV";
            case RIL_REQUEST_ANSWER: return "ANSWER";
            case RIL_REQUEST_DEACTIVATE_DATA_CALL: return "DEACTIVATE_DATA_CALL";
            case RIL_REQUEST_QUERY_FACILITY_LOCK: return "QUERY_FACILITY_LOCK";
            case RIL_REQUEST_SET_FACILITY_LOCK: return "SET_FACILITY_LOCK";
            case RIL_REQUEST_CHANGE_BARRING_PASSWORD: return "CHANGE_BARRING_PASSWORD";
            case RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE: return "QUERY_NETWORK_SELECTION_MODE";
            case RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC: return "SET_NETWORK_SELECTION_AUTOMATIC";
            case RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL: return "SET_NETWORK_SELECTION_MANUAL";
            case RIL_REQUEST_QUERY_AVAILABLE_NETWORKS : return "QUERY_AVAILABLE_NETWORKS ";
            case RIL_REQUEST_DTMF_START: return "DTMF_START";
            case RIL_REQUEST_DTMF_STOP: return "DTMF_STOP";
            case RIL_REQUEST_BASEBAND_VERSION: return "BASEBAND_VERSION";
            case RIL_REQUEST_SEPARATE_CONNECTION: return "SEPARATE_CONNECTION";
            case RIL_REQUEST_SET_MUTE: return "SET_MUTE";
            case RIL_REQUEST_GET_MUTE: return "GET_MUTE";
            case RIL_REQUEST_QUERY_CLIP: return "QUERY_CLIP";
            case RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE: return "LAST_DATA_CALL_FAIL_CAUSE";
            case RIL_REQUEST_DATA_CALL_LIST: return "DATA_CALL_LIST";
            case RIL_REQUEST_RESET_RADIO: return "RESET_RADIO";
            case RIL_REQUEST_OEM_HOOK_RAW: return "OEM_HOOK_RAW";
            case RIL_REQUEST_OEM_HOOK_STRINGS: return "OEM_HOOK_STRINGS";
            case RIL_REQUEST_SCREEN_STATE: return "SCREEN_STATE";
            case RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION: return "SET_SUPP_SVC_NOTIFICATION";
            case RIL_REQUEST_WRITE_SMS_TO_SIM: return "WRITE_SMS_TO_SIM";
            case RIL_REQUEST_DELETE_SMS_ON_SIM: return "DELETE_SMS_ON_SIM";
            case RIL_REQUEST_SET_BAND_MODE: return "SET_BAND_MODE";
            case RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE: return "QUERY_AVAILABLE_BAND_MODE";
            case RIL_REQUEST_STK_GET_PROFILE: return "REQUEST_STK_GET_PROFILE";
            case RIL_REQUEST_STK_SET_PROFILE: return "REQUEST_STK_SET_PROFILE";
            case RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND: return "REQUEST_STK_SEND_ENVELOPE_COMMAND";
            case RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE: return "REQUEST_STK_SEND_TERMINAL_RESPONSE";
            case RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM: return "REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM";
            case RIL_REQUEST_EXPLICIT_CALL_TRANSFER: return "REQUEST_EXPLICIT_CALL_TRANSFER";
            case RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE: return "REQUEST_SET_PREFERRED_NETWORK_TYPE";
            case RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE: return "REQUEST_GET_PREFERRED_NETWORK_TYPE";
            case RIL_REQUEST_GET_NEIGHBORING_CELL_IDS: return "REQUEST_GET_NEIGHBORING_CELL_IDS";
            case RIL_REQUEST_SET_LOCATION_UPDATES: return "REQUEST_SET_LOCATION_UPDATES";
            case RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE: return "RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE";
            case RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE: return "RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE";
            case RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE: return "RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE";
            case RIL_REQUEST_SET_TTY_MODE: return "RIL_REQUEST_SET_TTY_MODE";
            case RIL_REQUEST_QUERY_TTY_MODE: return "RIL_REQUEST_QUERY_TTY_MODE";
            case RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE: return "RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE";
            case RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE: return "RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE";
            case RIL_REQUEST_CDMA_FLASH: return "RIL_REQUEST_CDMA_FLASH";
            case RIL_REQUEST_CDMA_BURST_DTMF: return "RIL_REQUEST_CDMA_BURST_DTMF";
            case RIL_REQUEST_CDMA_SEND_SMS: return "RIL_REQUEST_CDMA_SEND_SMS";
            case RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE: return "RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE";
            case RIL_REQUEST_GSM_GET_BROADCAST_CONFIG: return "RIL_REQUEST_GSM_GET_BROADCAST_CONFIG";
            case RIL_REQUEST_GSM_SET_BROADCAST_CONFIG: return "RIL_REQUEST_GSM_SET_BROADCAST_CONFIG";
            case RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG: return "RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG";
            case RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG: return "RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG";
            case RIL_REQUEST_GSM_BROADCAST_ACTIVATION: return "RIL_REQUEST_GSM_BROADCAST_ACTIVATION";
            case RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY: return "RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY";
            case RIL_REQUEST_CDMA_BROADCAST_ACTIVATION: return "RIL_REQUEST_CDMA_BROADCAST_ACTIVATION";
            case RIL_REQUEST_CDMA_SUBSCRIPTION: return "RIL_REQUEST_CDMA_SUBSCRIPTION";
            case RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM: return "RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM";
            case RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM: return "RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM";
            case RIL_REQUEST_DEVICE_IDENTITY: return "RIL_REQUEST_DEVICE_IDENTITY";
            case RIL_REQUEST_GET_SMSC_ADDRESS: return "RIL_REQUEST_GET_SMSC_ADDRESS";
            case RIL_REQUEST_SET_SMSC_ADDRESS: return "RIL_REQUEST_SET_SMSC_ADDRESS";
            case RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE: return "REQUEST_EXIT_EMERGENCY_CALLBACK_MODE";
            case RIL_REQUEST_REPORT_SMS_MEMORY_STATUS: return "RIL_REQUEST_REPORT_SMS_MEMORY_STATUS";
            case RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING: return "RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING";
            case RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE: return "RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE";
            case RIL_REQUEST_ISIM_AUTHENTICATION: return "RIL_REQUEST_ISIM_AUTHENTICATION";
            case RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU: return "RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU";
            case RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS: return "RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS";
            default: return "<unknown request>";
        }
    }

    
        static String responseToString(int request) {
        switch(request) {
            case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED: return "UNSOL_RESPONSE_RADIO_STATE_CHANGED";
            case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED: return "UNSOL_RESPONSE_CALL_STATE_CHANGED";
            case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED: return "UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED";
            case RIL_UNSOL_RESPONSE_NEW_SMS: return "UNSOL_RESPONSE_NEW_SMS";
            case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT: return "UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT";
            case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM: return "UNSOL_RESPONSE_NEW_SMS_ON_SIM";
            case RIL_UNSOL_ON_USSD: return "UNSOL_ON_USSD";
            case RIL_UNSOL_ON_USSD_REQUEST: return "UNSOL_ON_USSD_REQUEST";
            case RIL_UNSOL_NITZ_TIME_RECEIVED: return "UNSOL_NITZ_TIME_RECEIVED";
            case RIL_UNSOL_SIGNAL_STRENGTH: return "UNSOL_SIGNAL_STRENGTH";
            case RIL_UNSOL_DATA_CALL_LIST_CHANGED: return "UNSOL_DATA_CALL_LIST_CHANGED";
            case RIL_UNSOL_SUPP_SVC_NOTIFICATION: return "UNSOL_SUPP_SVC_NOTIFICATION";
            case RIL_UNSOL_STK_SESSION_END: return "UNSOL_STK_SESSION_END";
            case RIL_UNSOL_STK_PROACTIVE_COMMAND: return "UNSOL_STK_PROACTIVE_COMMAND";
            case RIL_UNSOL_STK_EVENT_NOTIFY: return "UNSOL_STK_EVENT_NOTIFY";
            case RIL_UNSOL_STK_CALL_SETUP: return "UNSOL_STK_CALL_SETUP";
            case RIL_UNSOL_SIM_SMS_STORAGE_FULL: return "UNSOL_SIM_SMS_STORAGE_FULL";
            case RIL_UNSOL_SIM_REFRESH: return "UNSOL_SIM_REFRESH";
            case RIL_UNSOL_CALL_RING: return "UNSOL_CALL_RING";
            case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED: return "UNSOL_RESPONSE_SIM_STATUS_CHANGED";
            case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS: return "UNSOL_RESPONSE_CDMA_NEW_SMS";
            case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS: return "UNSOL_RESPONSE_NEW_BROADCAST_SMS";
            case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL: return "UNSOL_CDMA_RUIM_SMS_STORAGE_FULL";
            case RIL_UNSOL_RESTRICTED_STATE_CHANGED: return "UNSOL_RESTRICTED_STATE_CHANGED";
            case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE: return "UNSOL_ENTER_EMERGENCY_CALLBACK_MODE";
            case RIL_UNSOL_CDMA_CALL_WAITING: return "UNSOL_CDMA_CALL_WAITING";
            case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS: return "UNSOL_CDMA_OTA_PROVISION_STATUS";
            case RIL_UNSOL_CDMA_INFO_REC: return "UNSOL_CDMA_INFO_REC";
            case RIL_UNSOL_OEM_HOOK_RAW: return "UNSOL_OEM_HOOK_RAW";
            case RIL_UNSOL_RINGBACK_TONE: return "UNSOL_RINGBACK_TONG";
            case RIL_UNSOL_RESEND_INCALL_MUTE: return "UNSOL_RESEND_INCALL_MUTE";
            case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED: return "CDMA_SUBSCRIPTION_SOURCE_CHANGED";
            case RIL_UNSOl_CDMA_PRL_CHANGED: return "UNSOL_CDMA_PRL_CHANGED";
            case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE: return "UNSOL_EXIT_EMERGENCY_CALLBACK_MODE";
            case RIL_UNSOL_RIL_CONNECTED: return "UNSOL_RIL_CONNECTED";
            default: return "<unknown reponse>";
        }
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.188 -0400", hash_original_method = "513B160CE13530A75C92D2BB2C156FD7", hash_generated_method = "7F007F77C63BCCA79C3A9746733BA20F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void riljLog(String msg) {
        dsTaint.addTaint(msg);
        Log.d(LOG_TAG, msg);
        // ---------- Original Method ----------
        //Log.d(LOG_TAG, msg);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.188 -0400", hash_original_method = "709360DE622013B25C1B037A3416C3B6", hash_generated_method = "C687FB946272BF38D0937E1D5F641FB1")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void riljLogv(String msg) {
        dsTaint.addTaint(msg);
        // ---------- Original Method ----------
        //Log.v(LOG_TAG, msg);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.188 -0400", hash_original_method = "2B7AB7C6A9DFCD91149097CF23FA21DF", hash_generated_method = "ED10B857471DB52580143C98814ED703")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void unsljLog(int response) {
        dsTaint.addTaint(response);
        riljLog("[UNSL]< " + responseToString(response));
        // ---------- Original Method ----------
        //riljLog("[UNSL]< " + responseToString(response));
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.188 -0400", hash_original_method = "B9D4DA0AB06082664757422C0E23F4B2", hash_generated_method = "F81250B2E47FCD24B556FA2BEB72BA3D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void unsljLogMore(int response, String more) {
        dsTaint.addTaint(response);
        dsTaint.addTaint(more);
        riljLog("[UNSL]< " + responseToString(response) + " " + more);
        // ---------- Original Method ----------
        //riljLog("[UNSL]< " + responseToString(response) + " " + more);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.188 -0400", hash_original_method = "02F7DA800ECB6E455062B2AF08D2856E", hash_generated_method = "43C4BA2B137118B564F5FF0D1A89B95E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void unsljLogRet(int response, Object ret) {
        dsTaint.addTaint(ret.dsTaint);
        dsTaint.addTaint(response);
        riljLog("[UNSL]< " + responseToString(response) + " " + retToString(response, ret));
        // ---------- Original Method ----------
        //riljLog("[UNSL]< " + responseToString(response) + " " + retToString(response, ret));
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.189 -0400", hash_original_method = "5479F9B9AF9D3C3FE7229BC789506755", hash_generated_method = "4DA721D73274DE9C73703612C432551B")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void unsljLogvRet(int response, Object ret) {
        dsTaint.addTaint(ret.dsTaint);
        dsTaint.addTaint(response);
        riljLogv("[UNSL]< " + responseToString(response) + " " + retToString(response, ret));
        // ---------- Original Method ----------
        //riljLogv("[UNSL]< " + responseToString(response) + " " + retToString(response, ret));
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.189 -0400", hash_original_method = "B8DB86D0D3DD2A6C87C6DBBEA17396A3", hash_generated_method = "B42EB0BEC5FA30E82BB2684761EBD5CA")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getDeviceIdentity(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_DEVICE_IDENTITY, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_DEVICE_IDENTITY, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.189 -0400", hash_original_method = "2F4B55F10DAACCDACB48694F0AD6C5FC", hash_generated_method = "9021759A57A720C1C0DC6AABCAF9E3C8")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getCDMASubscription(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_SUBSCRIPTION, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_SUBSCRIPTION, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.189 -0400", hash_original_method = "CD84ED40ADC2BEFF960D6D373762CCB2", hash_generated_method = "224F4A50AE40C07F72F9AFA6195D34EB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void setPhoneType(int phoneType) {
        dsTaint.addTaint(phoneType);
        riljLog("setPhoneType=" + phoneType + " old value=" + mPhoneType);
        mPhoneType = phoneType;
        // ---------- Original Method ----------
        //if (RILJ_LOGD) riljLog("setPhoneType=" + phoneType + " old value=" + mPhoneType);
        //mPhoneType = phoneType;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.190 -0400", hash_original_method = "849E6CD9CCE8F1ECF8BC959270B499A0", hash_generated_method = "36F987B9344B1FFBE71A18B1D24E4866")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void queryCdmaRoamingPreference(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.190 -0400", hash_original_method = "BC2979EAC0CBA90D634290B385917086", hash_generated_method = "DBA75433A165D34AD2FB5D03D2A3890F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setCdmaRoamingPreference(int cdmaRoamingType, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(cdmaRoamingType);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(cdmaRoamingType);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " : " + cdmaRoamingType);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(cdmaRoamingType);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " : " + cdmaRoamingType);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.190 -0400", hash_original_method = "5B737D91D0E47D0010D2976568B23A82", hash_generated_method = "99030955C307EA3A721956C882E54E49")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setCdmaSubscriptionSource(int cdmaSubscription , Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(cdmaSubscription);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(cdmaSubscription);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " : " + cdmaSubscription);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(cdmaSubscription);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " : " + cdmaSubscription);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.190 -0400", hash_original_method = "5EEECA9C4DAFB6EB7CE2420CACC9812C", hash_generated_method = "FEF682BD2DAB76A6FD4A2FCF4B914597")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void getCdmaSubscriptionSource(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.191 -0400", hash_original_method = "116710C0DB0DC531DE694B6BC2F4AE2B", hash_generated_method = "A848715BB24A8346565AFBCD948E96B1")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void queryTTYMode(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_QUERY_TTY_MODE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_QUERY_TTY_MODE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.191 -0400", hash_original_method = "0B5DA23B67CE8A881960D39226001892", hash_generated_method = "1702E2400E2422764FC2FFF3A8D2918E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setTTYMode(int ttyMode, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(ttyMode);
        RILRequest rr;
        rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_SET_TTY_MODE, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(ttyMode);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " : " + ttyMode);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(
                //RILConstants.RIL_REQUEST_SET_TTY_MODE, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(ttyMode);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " : " + ttyMode);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.191 -0400", hash_original_method = "9DB0667959C6184F19426DF0FB910315", hash_generated_method = "15DC7FFDD1AA26A243EE0B69999FFA04")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void sendCDMAFeatureCode(String FeatureCode, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(FeatureCode);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_FLASH, response);
        rr.mp.writeString(FeatureCode);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " : " + FeatureCode);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_FLASH, response);
        //rr.mp.writeString(FeatureCode);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                //+ " : " + FeatureCode);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.191 -0400", hash_original_method = "EB6E132144573DF781138A585850ED0A", hash_generated_method = "429E934CB81000D4EA38896F7FD3A439")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void getCdmaBroadcastConfig(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG, response);
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG, response);
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.192 -0400", hash_original_method = "7AE57418607D61F0BA79FD2FF4F728AF", hash_generated_method = "8C874988173B3632A3111EA86FEE331C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setCdmaBroadcastConfig(int[] configValuesArray, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(configValuesArray[0]);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG, response);
        {
            int i;
            i = 0;
            {
                rr.mp.writeInt(configValuesArray[i]);
            } //End block
        } //End collapsed parenthetic
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG, response);
        //for(int i = 0; i < configValuesArray.length; i++) {
            //rr.mp.writeInt(configValuesArray[i]);
        //}
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.192 -0400", hash_original_method = "41C22C7BD9DE14E15F4803649E353969", hash_generated_method = "8E037F3D337BB50C458ECE0A1E11F3A5")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setCdmaBroadcastActivation(boolean activate, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(activate);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_CDMA_BROADCAST_ACTIVATION, response);
        rr.mp.writeInt(1);
        rr.mp.writeInt(activate ? 0 :1);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_CDMA_BROADCAST_ACTIVATION, response);
        //rr.mp.writeInt(1);
        //rr.mp.writeInt(activate ? 0 :1);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.192 -0400", hash_original_method = "82BAE0E7FF72D1A409D61F03DD8EFEA2", hash_generated_method = "A77C1E1762A4EE2E3913E628B491BB5C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void exitEmergencyCallbackMode(Message response) {
        dsTaint.addTaint(response.dsTaint);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE, response);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE, response);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.193 -0400", hash_original_method = "381EF137384490D69DA50E927DA4C209", hash_generated_method = "A6D5751947876A0210EC2B61E6628F18")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void requestIsimAuthentication(String nonce, Message response) {
        dsTaint.addTaint(response.dsTaint);
        dsTaint.addTaint(nonce);
        RILRequest rr;
        rr = RILRequest.obtain(RIL_REQUEST_ISIM_AUTHENTICATION, response);
        rr.mp.writeString(nonce);
        riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
        // ---------- Original Method ----------
        //RILRequest rr = RILRequest.obtain(RIL_REQUEST_ISIM_AUTHENTICATION, response);
        //rr.mp.writeString(nonce);
        //if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));
        //send(rr);
    }

    
    class RILSender extends Handler implements Runnable {
        byte[] dataLength = new byte[4];
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.201 -0400", hash_original_method = "4D17817439BCC34837C575257CF9147F", hash_generated_method = "6A540CA81B8FD17093B213A0163A21E0")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        public RILSender(Looper looper) {
            super(looper);
            dsTaint.addTaint(looper.dsTaint);
            // ---------- Original Method ----------
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.202 -0400", hash_original_method = "941C644B96F3E7EE75FAD0CC47E0EEC2", hash_generated_method = "A2E5AE58616EECC7176440E5139E1684")
        @DSModeled(DSC.SAFE)
        public void run() {
            // ---------- Original Method ----------
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.205 -0400", hash_original_method = "D25DB5A43D9B976C2A2C198EC34CFB08", hash_generated_method = "E0E0B6DA083354B62A6E0DD26A1184D8")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        @Override
        public void handleMessage(Message msg) {
            dsTaint.addTaint(msg.dsTaint);
            RILRequest rr;
            rr = (RILRequest)(msg.obj);
            RILRequest req;
            req = null;
            //Begin case EVENT_SEND 
            boolean alreadySubtracted;
            alreadySubtracted = false;
            //End case EVENT_SEND 
            //Begin case EVENT_SEND 
            try 
            {
                LocalSocket s;
                s = mSocket;
                {
                    rr.onError(RADIO_NOT_AVAILABLE, null);
                    rr.release();
                    alreadySubtracted = true;
                } //End block
                {
                    mRequestsList.add(rr);
                } //End block
                alreadySubtracted = true;
                byte[] data;
                data = rr.mp.marshall();
                rr.mp.recycle();
                rr.mp = null;
                {
                    if (DroidSafeAndroidRuntime.control) throw new RuntimeException(
                                    "Parcel larger than max bytes allowed! "
                                                          + data.length);
                } //End block
                dataLength[0] = dataLength[1] = 0;
                dataLength[2] = (byte)((data.length >> 8) & 0xff);
                dataLength[3] = (byte)((data.length) & 0xff);
                s.getOutputStream().write(dataLength);
                s.getOutputStream().write(data);
            } //End block
            catch (IOException ex)
            {
                req = findAndRemoveRequestFromList(rr.mSerial);
                {
                    rr.onError(RADIO_NOT_AVAILABLE, null);
                    rr.release();
                } //End block
            } //End block
            catch (RuntimeException exc)
            {
                req = findAndRemoveRequestFromList(rr.mSerial);
                {
                    rr.onError(GENERIC_FAILURE, null);
                    rr.release();
                } //End block
            } //End block
            finally 
            {
                releaseWakeLockIfDone();
            } //End block
            //End case EVENT_SEND 
            //Begin case EVENT_WAKE_LOCK_TIMEOUT 
            {
                {
                    boolean var9A4CAC6617AE0A733634FB069C49B2EA_168939696 = (mWakeLock.isHeld());
                    {
                        {
                            Log.d(LOG_TAG, "NOTE: mReqWaiting is NOT 0 but"
                                        + mRequestMessagesWaiting + " at TIMEOUT, reset!"
                                        + " There still msg waitng for response");
                            mRequestMessagesWaiting = 0;
                            {
                                {
                                    int count;
                                    count = mRequestsList.size();
                                    Log.d(LOG_TAG, "WAKE_LOCK_TIMEOUT " +
                                                " mRequestList=" + count);
                                    {
                                        int i;
                                        i = 0;
                                        {
                                            rr = mRequestsList.get(i);
                                            Log.d(LOG_TAG, i + ": [" + rr.mSerial + "] "
                                                    + requestToString(rr.mRequest));
                                        } //End block
                                    } //End collapsed parenthetic
                                } //End block
                            } //End block
                        } //End block
                        {
                            mRequestMessagesPending = 0;
                        } //End block
                        mWakeLock.release();
                    } //End block
                } //End collapsed parenthetic
            } //End block
            //End case EVENT_WAKE_LOCK_TIMEOUT 
            // ---------- Original Method ----------
            // Original Method Too Long, Refer to Original Implementation
        }

        
    }


    
    class RILReceiver implements Runnable {
        byte[] buffer;
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.205 -0400", hash_original_method = "AEB7D4EC0C0D3B29B57F76734A093487", hash_generated_method = "832D356F5AA3BF59D3700679E90409AE")
        //DSFIXME:  CODE0002: Requires DSC value to be set
         RILReceiver() {
            buffer = new byte[RIL_MAX_COMMAND_BYTES];
            // ---------- Original Method ----------
            //buffer = new byte[RIL_MAX_COMMAND_BYTES];
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:12.206 -0400", hash_original_method = "3E25FFC0D00CEB4B0CF0C518A51DB777", hash_generated_method = "4DEEBD6C6C260383678C8A3641CF1D6D")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        public void run() {
            int retryCount;
            retryCount = 0;
            try 
            {
                {
                    LocalSocket s;
                    s = null;
                    LocalSocketAddress l;
                    try 
                    {
                        s = new LocalSocket();
                        l = new LocalSocketAddress(SOCKET_NAME_RIL,
                            LocalSocketAddress.Namespace.RESERVED);
                        s.connect(l);
                    } //End block
                    catch (IOException ex)
                    {
                        try 
                        {
                            {
                                s.close();
                            } //End block
                        } //End block
                        catch (IOException ex2)
                        { }
                        try 
                        {
                            Thread.sleep(SOCKET_OPEN_RETRY_MILLIS);
                        } //End block
                        catch (InterruptedException er)
                        { }
                    } //End block
                    retryCount = 0;
                    mSocket = s;
                    int length;
                    length = 0;
                    try 
                    {
                        InputStream is;
                        is = mSocket.getInputStream();
                        {
                            Parcel p;
                            length = readRilMessage(is, buffer);
                            p = Parcel.obtain();
                            p.unmarshall(buffer, 0, length);
                            p.setDataPosition(0);
                            processResponse(p);
                            p.recycle();
                        } //End block
                    } //End block
                    catch (java.io.IOException ex)
                    { }
                    catch (Throwable tr)
                    { }
                    setRadioState (RadioState.RADIO_UNAVAILABLE);
                    try 
                    {
                        mSocket.close();
                    } //End block
                    catch (IOException ex)
                    { }
                    mSocket = null;
                    RILRequest.resetSerial();
                    clearRequestsList(RADIO_NOT_AVAILABLE, false);
                } //End block
            } //End block
            catch (Throwable tr)
            { }
            notifyRegistrantsRilConnectionChanged(-1);
            // ---------- Original Method ----------
            // Original Method Too Long, Refer to Original Implementation
        }

        
    }


    
    static final String LOG_TAG = "RILJ";
    static final boolean RILJ_LOGD = true;
    static final boolean RILJ_LOGV = false;
    private static final int DEFAULT_WAKE_LOCK_TIMEOUT = 60000;
    static final int EVENT_SEND                 = 1;
    static final int EVENT_WAKE_LOCK_TIMEOUT    = 2;
    static final int RIL_MAX_COMMAND_BYTES = (8 * 1024);
    static final int RESPONSE_SOLICITED = 0;
    static final int RESPONSE_UNSOLICITED = 1;
    static final String SOCKET_NAME_RIL = "rild";
    static final int SOCKET_OPEN_RETRY_MILLIS = 4 * 1000;
    private static final int CDMA_BSI_NO_OF_INTS_STRUCT = 3;
    private static final int CDMA_BROADCAST_SMS_NO_OF_SERVICE_CATEGORIES = 31;
}

