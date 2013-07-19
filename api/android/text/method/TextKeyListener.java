package android.text.method;

// Droidsafe Imports
import java.lang.ref.WeakReference;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.System;
import android.text.Editable;
import android.text.InputType;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class TextKeyListener extends BaseKeyListener implements SpanWatcher {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.766 -0400", hash_original_field = "7B00CDF214A58D964DB1FD6D561A182A", hash_generated_field = "BEDDE5D3B36AFDA96122F6739A399D24")

    private Capitalize mAutoCap;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.766 -0400", hash_original_field = "3D18F8DF226A678B64C4D4C4D035A79A", hash_generated_field = "C101024EE87082E36D969AEA4683627E")

    private boolean mAutoText;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.766 -0400", hash_original_field = "4A65E4AFE2E6A8567B2303B5B414D0A2", hash_generated_field = "6926B19407F5E0ECC3F4CEC11E041C87")

    private int mPrefs;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.766 -0400", hash_original_field = "9977F77CE5547DA73F8036BC498B4EC9", hash_generated_field = "1D299557689B8C33CC4C3DF3BE9D5C3D")

    private boolean mPrefsInited;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.766 -0400", hash_original_field = "CF849E3C15214EFD093D4303B542BF44", hash_generated_field = "5D46AECEC0347C1327E8060B0CEDA4B8")

    private WeakReference<ContentResolver> mResolver;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.766 -0400", hash_original_field = "C61FA2E7C36AC7AB9C40A5C4B482DC3A", hash_generated_field = "94A6065FC7822989FE2C83137CC2E2A5")

    private TextKeyListener.SettingsObserver mObserver;
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.767 -0400", hash_original_method = "9DAB45081F29D4D9259EECD11A475A04", hash_generated_method = "B59FD9AE6465D27FF27D624847BFBF79")
    public  TextKeyListener(Capitalize cap, boolean autotext) {
        mAutoCap = cap;
        mAutoText = autotext;
        // ---------- Original Method ----------
        //mAutoCap = cap;
        //mAutoText = autotext;
    }

    
    @DSModeled(DSC.SAFE)
    public static TextKeyListener getInstance(boolean autotext,
                                              Capitalize cap) {
        int off = cap.ordinal() * 2 + (autotext ? 1 : 0);
        if (sInstance[off] == null) {
            sInstance[off] = new TextKeyListener(cap, autotext);
        }
        return sInstance[off];
    }

    
    @DSModeled(DSC.SAFE)
    public static TextKeyListener getInstance() {
        return getInstance(false, Capitalize.NONE);
    }

    
    @DSModeled(DSC.SAFE)
    public static boolean shouldCap(Capitalize cap, CharSequence cs, int off) {
        int i;
        char c;
        if (cap == Capitalize.NONE) {
            return false;
        }
        if (cap == Capitalize.CHARACTERS) {
            return true;
        }
        return TextUtils.getCapsMode(cs, off, cap == Capitalize.WORDS
                ? TextUtils.CAP_MODE_WORDS : TextUtils.CAP_MODE_SENTENCES)
                != 0;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.768 -0400", hash_original_method = "77B762D33BA1CBC4DB995B196C907EDB", hash_generated_method = "343B96D4791E2645932A072364003223")
    public int getInputType() {
        int varE5795ACE23B9034FFE40B80EEAF10896_1853026153 = (makeTextContentType(mAutoCap, mAutoText));
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_2001673185 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_2001673185;
        // ---------- Original Method ----------
        //return makeTextContentType(mAutoCap, mAutoText);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.768 -0400", hash_original_method = "81E68D18E6AAF9C62286CED7FE623E2E", hash_generated_method = "398E9A9FCAA2BC592C9B2057389139E8")
    @Override
    public boolean onKeyDown(View view, Editable content,
                             int keyCode, KeyEvent event) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(event.getTaint());
        addTaint(keyCode);
        addTaint(content.getTaint());
        addTaint(view.getTaint());
        KeyListener im = getKeyListener(event);
        boolean varE92B09078859DFBD8A16398028A99ADE_428271089 = (im.onKeyDown(view, content, keyCode, event));
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1736695410 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1736695410;
        // ---------- Original Method ----------
        //KeyListener im = getKeyListener(event);
        //return im.onKeyDown(view, content, keyCode, event);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.769 -0400", hash_original_method = "4D169ED96CD6963312694BD71629C37D", hash_generated_method = "F8F3A8B7C45FF0F0B2D84F735DA51759")
    @Override
    public boolean onKeyUp(View view, Editable content,
                           int keyCode, KeyEvent event) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(event.getTaint());
        addTaint(keyCode);
        addTaint(content.getTaint());
        addTaint(view.getTaint());
        KeyListener im = getKeyListener(event);
        boolean varBFFB108E7789BC1BF35A0041C3FFB84D_606011253 = (im.onKeyUp(view, content, keyCode, event));
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_761463677 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_761463677;
        // ---------- Original Method ----------
        //KeyListener im = getKeyListener(event);
        //return im.onKeyUp(view, content, keyCode, event);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.769 -0400", hash_original_method = "BC51D61DF5BB87B519CDA007F0E35AD4", hash_generated_method = "40A99D4CAEB56827E1B95E6C3E5AACEB")
    @Override
    public boolean onKeyOther(View view, Editable content, KeyEvent event) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(event.getTaint());
        addTaint(content.getTaint());
        addTaint(view.getTaint());
        KeyListener im = getKeyListener(event);
        boolean varF2FB1CE65289185766496DC9850A55E3_2079318740 = (im.onKeyOther(view, content, event));
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1179836869 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1179836869;
        // ---------- Original Method ----------
        //KeyListener im = getKeyListener(event);
        //return im.onKeyOther(view, content, event);
    }

    
    @DSModeled(DSC.SAFE)
    public static void clear(Editable e) {
        e.clear();
        e.removeSpan(ACTIVE);
        e.removeSpan(CAPPED);
        e.removeSpan(INHIBIT_REPLACEMENT);
        e.removeSpan(LAST_TYPED);
        QwertyKeyListener.Replaced[] repl = e.getSpans(0, e.length(),
                                   QwertyKeyListener.Replaced.class);
        final int count = repl.length;
        for (int i = 0; i < count; i++) {
            e.removeSpan(repl[i]);
        }
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.770 -0400", hash_original_method = "A7E02D03E9AB7CF614DD169102D03FAF", hash_generated_method = "0F24BCA6C9426B0673BF35264E746E85")
    public void onSpanAdded(Spannable s, Object what, int start, int end) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(end);
        addTaint(start);
        addTaint(what.getTaint());
        addTaint(s.getTaint());
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.771 -0400", hash_original_method = "2C716670C272F54C9EB4064D90DB3C4E", hash_generated_method = "A2BA261E44A26E34D865E62CBB58DEB1")
    public void onSpanRemoved(Spannable s, Object what, int start, int end) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(end);
        addTaint(start);
        addTaint(what.getTaint());
        addTaint(s.getTaint());
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.771 -0400", hash_original_method = "C422051A9042B505708BBCABB42F0EAF", hash_generated_method = "3CD49F4BF16AE643BAFBBC1E2F302189")
    public void onSpanChanged(Spannable s, Object what, int start, int end,
                              int st, int en) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(en);
        addTaint(st);
        addTaint(end);
        addTaint(start);
        addTaint(what.getTaint());
        addTaint(s.getTaint());
        if(what == Selection.SELECTION_END)        
        {
            s.removeSpan(ACTIVE);
        } //End block
        // ---------- Original Method ----------
        //if (what == Selection.SELECTION_END) {
            //s.removeSpan(ACTIVE);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.772 -0400", hash_original_method = "AD20E29A89E7D41F4894A2F9AC99B4E0", hash_generated_method = "A3D05C6848BB2B4D166D84ED65392CD4")
    private KeyListener getKeyListener(KeyEvent event) {
        addTaint(event.getTaint());
        KeyCharacterMap kmap = event.getKeyCharacterMap();
        int kind = kmap.getKeyboardType();
        if(kind == KeyCharacterMap.ALPHA)        
        {
KeyListener varEC9542826F72021F43C1BC564C67087E_1996041662 =             QwertyKeyListener.getInstance(mAutoText, mAutoCap);
            varEC9542826F72021F43C1BC564C67087E_1996041662.addTaint(taint);
            return varEC9542826F72021F43C1BC564C67087E_1996041662;
        } //End block
        else
        if(kind == KeyCharacterMap.NUMERIC)        
        {
KeyListener varD2422122DDAB9FBC76E4C119E6A05778_1904994287 =             MultiTapKeyListener.getInstance(mAutoText, mAutoCap);
            varD2422122DDAB9FBC76E4C119E6A05778_1904994287.addTaint(taint);
            return varD2422122DDAB9FBC76E4C119E6A05778_1904994287;
        } //End block
        else
        if(kind == KeyCharacterMap.FULL
                || kind == KeyCharacterMap.SPECIAL_FUNCTION)        
        {
KeyListener varBFB68DF5F567971D155FF362828158DA_620042586 =             QwertyKeyListener.getInstanceForFullKeyboard();
            varBFB68DF5F567971D155FF362828158DA_620042586.addTaint(taint);
            return varBFB68DF5F567971D155FF362828158DA_620042586;
        } //End block
KeyListener var65A324ADC921227A148ADF9FB0E0762B_1739274097 =         NullKeyListener.getInstance();
        var65A324ADC921227A148ADF9FB0E0762B_1739274097.addTaint(taint);
        return var65A324ADC921227A148ADF9FB0E0762B_1739274097;
        // ---------- Original Method ----------
        //KeyCharacterMap kmap = event.getKeyCharacterMap();
        //int kind = kmap.getKeyboardType();
        //if (kind == KeyCharacterMap.ALPHA) {
            //return QwertyKeyListener.getInstance(mAutoText, mAutoCap);
        //} else if (kind == KeyCharacterMap.NUMERIC) {
            //return MultiTapKeyListener.getInstance(mAutoText, mAutoCap);
        //} else if (kind == KeyCharacterMap.FULL
                //|| kind == KeyCharacterMap.SPECIAL_FUNCTION) {
            //return QwertyKeyListener.getInstanceForFullKeyboard();
        //}
        //return NullKeyListener.getInstance();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.773 -0400", hash_original_method = "565C1A1138C97E5AB635581DD94969A4", hash_generated_method = "11D58F3C237B7BB9C589A619775D05A6")
    public void release() {
        if(mResolver != null)        
        {
            final ContentResolver contentResolver = mResolver.get();
            if(contentResolver != null)            
            {
                contentResolver.unregisterContentObserver(mObserver);
                mResolver.clear();
            } //End block
            mObserver = null;
            mResolver = null;
            mPrefsInited = false;
        } //End block
        // ---------- Original Method ----------
        //if (mResolver != null) {
            //final ContentResolver contentResolver = mResolver.get();
            //if (contentResolver != null) {
                //contentResolver.unregisterContentObserver(mObserver);
                //mResolver.clear();
            //}
            //mObserver = null;
            //mResolver = null;
            //mPrefsInited = false;
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.773 -0400", hash_original_method = "083A24B7721BE4E40A2817E04F6CDAB7", hash_generated_method = "00AB2605517572F6562DBB762AE5F875")
    private void initPrefs(Context context) {
        addTaint(context.getTaint());
        final ContentResolver contentResolver = context.getContentResolver();
        mResolver = new WeakReference<ContentResolver>(contentResolver);
        if(mObserver == null)        
        {
            mObserver = new SettingsObserver();
            contentResolver.registerContentObserver(Settings.System.CONTENT_URI, true, mObserver);
        } //End block
        updatePrefs(contentResolver);
        mPrefsInited = true;
        // ---------- Original Method ----------
        //final ContentResolver contentResolver = context.getContentResolver();
        //mResolver = new WeakReference<ContentResolver>(contentResolver);
        //if (mObserver == null) {
            //mObserver = new SettingsObserver();
            //contentResolver.registerContentObserver(Settings.System.CONTENT_URI, true, mObserver);
        //}
        //updatePrefs(contentResolver);
        //mPrefsInited = true;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.774 -0400", hash_original_method = "4FB87B933010A67FA68BED5325D02530", hash_generated_method = "522ADEEB7422FF7A8B03B3141DD0B48B")
    private void updatePrefs(ContentResolver resolver) {
        addTaint(resolver.getTaint());
        boolean cap = System.getInt(resolver, System.TEXT_AUTO_CAPS, 1) > 0;
        boolean text = System.getInt(resolver, System.TEXT_AUTO_REPLACE, 1) > 0;
        boolean period = System.getInt(resolver, System.TEXT_AUTO_PUNCTUATE, 1) > 0;
        boolean pw = System.getInt(resolver, System.TEXT_SHOW_PASSWORD, 1) > 0;
        mPrefs = (cap ? AUTO_CAP : 0) |
                 (text ? AUTO_TEXT : 0) |
                 (period ? AUTO_PERIOD : 0) |
                 (pw ? SHOW_PASSWORD : 0);
        // ---------- Original Method ----------
        //boolean cap = System.getInt(resolver, System.TEXT_AUTO_CAPS, 1) > 0;
        //boolean text = System.getInt(resolver, System.TEXT_AUTO_REPLACE, 1) > 0;
        //boolean period = System.getInt(resolver, System.TEXT_AUTO_PUNCTUATE, 1) > 0;
        //boolean pw = System.getInt(resolver, System.TEXT_SHOW_PASSWORD, 1) > 0;
        //mPrefs = (cap ? AUTO_CAP : 0) |
                 //(text ? AUTO_TEXT : 0) |
                 //(period ? AUTO_PERIOD : 0) |
                 //(pw ? SHOW_PASSWORD : 0);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.774 -0400", hash_original_method = "27BF28A81EBEDB0A58E981BB8A0B8A16", hash_generated_method = "B7CAC911E488EDA3AE047B988539EAEF")
     int getPrefs(Context context) {
        addTaint(context.getTaint());
        synchronized
(this)        {
            if(!mPrefsInited || mResolver.get() == null)            
            {
                initPrefs(context);
            } //End block
        } //End block
        int var4A65E4AFE2E6A8567B2303B5B414D0A2_1206290308 = (mPrefs);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1292956307 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1292956307;
        // ---------- Original Method ----------
        //synchronized (this) {
            //if (!mPrefsInited || mResolver.get() == null) {
                //initPrefs(context);
            //}
        //}
        //return mPrefs;
    }

    
    public enum Capitalize {
        NONE, SENTENCES, WORDS, CHARACTERS,
    }

    
    private static class NullKeyListener implements KeyListener {
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.774 -0400", hash_original_method = "F358F3B966187D3EEEEBD171D03A4696", hash_generated_method = "F358F3B966187D3EEEEBD171D03A4696")
        public NullKeyListener ()
        {
            //Synthesized constructor
        }


                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.775 -0400", hash_original_method = "579E66F4F4B0748C979BD80ED5F12401", hash_generated_method = "B0C66F93B73C193C3B1733033583C345")
        public int getInputType() {
            int var24CC70A2FB503E33350D5132CA8C5E73_1992912732 = (InputType.TYPE_NULL);
                        int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1278622370 = getTaintInt();
            return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1278622370;
            // ---------- Original Method ----------
            //return InputType.TYPE_NULL;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.775 -0400", hash_original_method = "3A5E70FDE36A1D45C2BCBEA410D24514", hash_generated_method = "FC1B35941C7D59ED94ADD5B61842FDAE")
        public boolean onKeyDown(View view, Editable content,
                                 int keyCode, KeyEvent event) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(event.getTaint());
            addTaint(keyCode);
            addTaint(content.getTaint());
            addTaint(view.getTaint());
            boolean var68934A3E9455FA72420237EB05902327_458095058 = (false);
                        boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_2127346812 = getTaintBoolean();
            return var84E2C64F38F78BA3EA5C905AB5A2DA27_2127346812;
            // ---------- Original Method ----------
            //return false;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.775 -0400", hash_original_method = "1D7ECDFDE217EC70CAE84F2DF1B7B165", hash_generated_method = "95DB3D793DF754FF4D7D33EC5FA6FE30")
        public boolean onKeyUp(View view, Editable content, int keyCode,
                                        KeyEvent event) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(event.getTaint());
            addTaint(keyCode);
            addTaint(content.getTaint());
            addTaint(view.getTaint());
            boolean var68934A3E9455FA72420237EB05902327_497462049 = (false);
                        boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_828252119 = getTaintBoolean();
            return var84E2C64F38F78BA3EA5C905AB5A2DA27_828252119;
            // ---------- Original Method ----------
            //return false;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.775 -0400", hash_original_method = "2352E2665ECFCE8217C303C63497A70F", hash_generated_method = "797C933B082311A09D7D61B8399D3E63")
        public boolean onKeyOther(View view, Editable content, KeyEvent event) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(event.getTaint());
            addTaint(content.getTaint());
            addTaint(view.getTaint());
            boolean var68934A3E9455FA72420237EB05902327_555702112 = (false);
                        boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1811046960 = getTaintBoolean();
            return var84E2C64F38F78BA3EA5C905AB5A2DA27_1811046960;
            // ---------- Original Method ----------
            //return false;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.775 -0400", hash_original_method = "7A4583B5F6FBB25B3AFA5ED32AFA7AE1", hash_generated_method = "2A9E6826A21A98831EDA8D144A2B75C8")
        public void clearMetaKeyState(View view, Editable content, int states) {
            addTaint(states);
            addTaint(content.getTaint());
            addTaint(view.getTaint());
            // ---------- Original Method ----------
        }

        
        public static NullKeyListener getInstance() {
            if (sInstance != null)
                return sInstance;
            sInstance = new NullKeyListener();
            return sInstance;
        }

        
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.775 -0400", hash_original_field = "06E23A628CBDEAB09E91C69789BB1974", hash_generated_field = "DDEE448534DDF1827F49C320D91A9424")

        private static NullKeyListener sInstance;
    }


    
    private class SettingsObserver extends ContentObserver {
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.775 -0400", hash_original_method = "3A67C3C9DA9AFE8F3EB6304BC772D062", hash_generated_method = "EDD4606589505CE8A23486C182973460")
        public  SettingsObserver() {
            super(new Handler());
            // ---------- Original Method ----------
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_method = "9B798686FD4009B7C6E0CF4CD0BD37F7", hash_generated_method = "5073F2AD1F06237249533E56FDD623DE")
        @Override
        public void onChange(boolean selfChange) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(selfChange);
            if(mResolver != null)            
            {
                final ContentResolver contentResolver = mResolver.get();
                if(contentResolver == null)                
                {
                    mPrefsInited = false;
                } //End block
                else
                {
                    updatePrefs(contentResolver);
                } //End block
            } //End block
            else
            {
                mPrefsInited = false;
            } //End block
            // ---------- Original Method ----------
            //if (mResolver != null) {
                //final ContentResolver contentResolver = mResolver.get();
                //if (contentResolver == null) {
                    //mPrefsInited = false;
                //} else {
                    //updatePrefs(contentResolver);
                //}
            //} else {
                //mPrefsInited = false;
            //}
        }

        
    }


    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "9258D9C5B5D004A481333BEC5D25DADD", hash_generated_field = "2C8F1064E829F05C17C9AD853633A8D2")

    private static TextKeyListener[] sInstance = new TextKeyListener[Capitalize.values().length * 2];
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "D7E62BE07D36065BBAE7FCF76BD042F0", hash_generated_field = "C7C6152808A642DF24CDDBE9C0C44FB8")

    static final Object ACTIVE = new NoCopySpan.Concrete();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "CF1FCDC699B0BABB0FF36519F5BA35A2", hash_generated_field = "4534209FB212FC97D916347B1E619FAF")

    static final Object CAPPED = new NoCopySpan.Concrete();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "8F441AE1C3A141D347EC7210C51AABA9", hash_generated_field = "03A8D7254F9C3831689DC1883FAC45ED")

    static final Object INHIBIT_REPLACEMENT = new NoCopySpan.Concrete();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "2FC62B3E0CE00FD717FA9EE885D9A2FF", hash_generated_field = "14991993D4613839CACE1341F63244AB")

    static final Object LAST_TYPED = new NoCopySpan.Concrete();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "1DF7B80DEB34DE88F5EE38227854DBB6", hash_generated_field = "3BD9B820858A620A3188F9E94EAB91D5")

    static final int AUTO_CAP = 1;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "A2BC4196F6C7F0E3DA52F4C2E0EBBCB6", hash_generated_field = "6794BDCF24212BCF37790B73A597BB47")

    static final int AUTO_TEXT = 2;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "0988C732F6B13B5D29A128A2F4B08B03", hash_generated_field = "CAA762C7FC8B8D1FEFBD15F8D9F5F986")

    static final int AUTO_PERIOD = 4;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.776 -0400", hash_original_field = "5401DB338DB4F4AAF2C8F0CCF7EF68B4", hash_generated_field = "FEF3F36C40B66E3713ABD6008B964639")

    static final int SHOW_PASSWORD = 8;
}

