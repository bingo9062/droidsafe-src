package android.emoji;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import android.graphics.Bitmap;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;

public final class EmojiFactory {
    private int sCacheSize = 100;
    private int mNativeEmojiFactory;
    private String mName;
    private Map<Integer, WeakReference<Bitmap>> mCache;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.016 -0400", hash_original_method = "3CFB061344A54165CC98223BF316C16E", hash_generated_method = "B48D086D0C64EA23B876F1F408776C63")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private EmojiFactory(int nativeEmojiFactory, String name) {
        dsTaint.addTaint(nativeEmojiFactory);
        dsTaint.addTaint(name);
        mCache = new CustomLinkedHashMap<Integer, WeakReference<Bitmap>>();
        // ---------- Original Method ----------
        //mNativeEmojiFactory = nativeEmojiFactory;
        //mName = name;
        //mCache = new CustomLinkedHashMap<Integer, WeakReference<Bitmap>>();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.016 -0400", hash_original_method = "32C84A5952A8F243E533AA978C471609", hash_generated_method = "1022AB339BCE8F38D8D3090647D24799")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    protected void finalize() throws Throwable {
        try 
        {
            nativeDestructor(mNativeEmojiFactory);
        } //End block
        finally 
        {
            super.finalize();
        } //End block
        // ---------- Original Method ----------
        //try {
            //nativeDestructor(mNativeEmojiFactory);
        //} finally {
            //super.finalize();
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.017 -0400", hash_original_method = "61A4CE5E0B45509C63F06179C920C9C2", hash_generated_method = "BEE67348FE6739C5DE0D3DF16D5DB580")
    @DSModeled(DSC.SAFE)
    public String name() {
        return dsTaint.getTaintString();
        // ---------- Original Method ----------
        //return mName;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.017 -0400", hash_original_method = "51D3837892087BC1526F97486EB96A82", hash_generated_method = "53983D7E9FFCEC0F11B838C032977B8E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public synchronized Bitmap getBitmapFromAndroidPua(int pua) {
        dsTaint.addTaint(pua);
        WeakReference<Bitmap> cache;
        cache = mCache.get(pua);
        {
            Bitmap ret;
            ret = nativeGetBitmapFromAndroidPua(mNativeEmojiFactory, pua);
            {
                mCache.put(pua, new WeakReference<Bitmap>(ret));
            } //End block
        } //End block
        {
            Bitmap tmp;
            tmp = cache.get();
            {
                Bitmap ret;
                ret = nativeGetBitmapFromAndroidPua(mNativeEmojiFactory, pua);
                mCache.put(pua, new WeakReference<Bitmap>(ret));
            } //End block
        } //End block
        return (Bitmap)dsTaint.getTaint();
        // ---------- Original Method ----------
        //WeakReference<Bitmap> cache = mCache.get(pua);
        //if (cache == null) {
            //Bitmap ret = nativeGetBitmapFromAndroidPua(mNativeEmojiFactory, pua);
            //if (ret != null) {
               //mCache.put(pua, new WeakReference<Bitmap>(ret));
            //}
            //return ret;
        //} else {
            //Bitmap tmp = cache.get();
            //if (tmp == null) {
                //Bitmap ret = nativeGetBitmapFromAndroidPua(mNativeEmojiFactory, pua);
                //mCache.put(pua, new WeakReference<Bitmap>(ret));
                //return ret;
            //} else {
                //return tmp;
            //}
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.017 -0400", hash_original_method = "9A7284FF37A5214E9B7F9311E2EF3E6C", hash_generated_method = "BFC0008385E5AA9E37E148A0353543A6")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public synchronized Bitmap getBitmapFromVendorSpecificSjis(char sjis) {
        dsTaint.addTaint(sjis);
        Bitmap varCA8CEB83AA8EA8E2575DEAAD2AA611F7_178476039 = (getBitmapFromAndroidPua(getAndroidPuaFromVendorSpecificSjis(sjis)));
        return (Bitmap)dsTaint.getTaint();
        // ---------- Original Method ----------
        //return getBitmapFromAndroidPua(getAndroidPuaFromVendorSpecificSjis(sjis));
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.018 -0400", hash_original_method = "F5F3B81D8E941B288B2A41FCC9367619", hash_generated_method = "A0ADA5BA71867D902E1A521BF1B2231C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public synchronized Bitmap getBitmapFromVendorSpecificPua(int vsp) {
        dsTaint.addTaint(vsp);
        Bitmap var6F7C48042B60FF94F4F57B0EE542334F_577314157 = (getBitmapFromAndroidPua(getAndroidPuaFromVendorSpecificPua(vsp)));
        return (Bitmap)dsTaint.getTaint();
        // ---------- Original Method ----------
        //return getBitmapFromAndroidPua(getAndroidPuaFromVendorSpecificPua(vsp));
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.018 -0400", hash_original_method = "5E0818C254D368AF2876C64915580604", hash_generated_method = "D03EEDD8974BE180E5748AEE82294B6E")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public int getAndroidPuaFromVendorSpecificSjis(char sjis) {
        dsTaint.addTaint(sjis);
        int var74C2D334EFE3998285C80D7E5E8A8BE7_1552126492 = (nativeGetAndroidPuaFromVendorSpecificSjis(mNativeEmojiFactory, sjis));
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return nativeGetAndroidPuaFromVendorSpecificSjis(mNativeEmojiFactory, sjis);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.018 -0400", hash_original_method = "CAA9928E1DDFC9E6AF0D5DC613844580", hash_generated_method = "E743EEDB4FDEE16A08C8848BE80F8D2C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public int getVendorSpecificSjisFromAndroidPua(int pua) {
        dsTaint.addTaint(pua);
        int varD1B8764BBE902D599DA4F2D19767BBAC_1733123657 = (nativeGetVendorSpecificSjisFromAndroidPua(mNativeEmojiFactory, pua));
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return nativeGetVendorSpecificSjisFromAndroidPua(mNativeEmojiFactory, pua);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.019 -0400", hash_original_method = "BF155C8450604BCD58870D68939F5C48", hash_generated_method = "4E87F4C61330B994C37DC237627DA27C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public int getAndroidPuaFromVendorSpecificPua(int vsp) {
        dsTaint.addTaint(vsp);
        int var70F5CE10206AFB73309604A98CB9FD21_1630513957 = (nativeGetAndroidPuaFromVendorSpecificPua(mNativeEmojiFactory, vsp));
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return nativeGetAndroidPuaFromVendorSpecificPua(mNativeEmojiFactory, vsp);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.020 -0400", hash_original_method = "332632D7825438D0B8DBF43F7E76A339", hash_generated_method = "3D186661BF7A9DE2FE0938A39274D8EF")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public String getAndroidPuaFromVendorSpecificPua(String vspString) {
        dsTaint.addTaint(vspString);
        int minVsp;
        minVsp = nativeGetMinimumVendorSpecificPua(mNativeEmojiFactory);
        int maxVsp;
        maxVsp = nativeGetMaximumVendorSpecificPua(mNativeEmojiFactory);
        int len;
        len = vspString.length();
        int[] codePoints;
        codePoints = new int[vspString.codePointCount(0, len)];
        int new_len;
        new_len = 0;
        {
            int i;
            i = 0;
            i = vspString.offsetByCodePoints(i, 1);
            {
                int codePoint;
                codePoint = vspString.codePointAt(i);
                {
                    int newCodePoint;
                    newCodePoint = getAndroidPuaFromVendorSpecificPua(codePoint);
                    {
                        codePoints[new_len] = newCodePoint;
                    } //End block
                } //End block
                codePoints[new_len] = codePoint;
            } //End block
        } //End collapsed parenthetic
        String var680B4B2ECED1E9EEFDE52DF18D8C48BF_276449641 = (new String(codePoints, 0, new_len));
        return dsTaint.getTaintString();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.021 -0400", hash_original_method = "B08EB100AB82E54ADADC386CF8B33D33", hash_generated_method = "847CABA5347F98487A61CCCBF515FC36")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public int getVendorSpecificPuaFromAndroidPua(int pua) {
        dsTaint.addTaint(pua);
        int varD354C24A4941A2811A9CD1AB35D65E36_1590959307 = (nativeGetVendorSpecificPuaFromAndroidPua(mNativeEmojiFactory, pua));
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return nativeGetVendorSpecificPuaFromAndroidPua(mNativeEmojiFactory, pua);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.022 -0400", hash_original_method = "6085ED2713FAD0096DF7C4BD4C86F8DD", hash_generated_method = "A184D8E90797AC3212F9AA8771FCEDCE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public String getVendorSpecificPuaFromAndroidPua(String puaString) {
        dsTaint.addTaint(puaString);
        int minVsp;
        minVsp = nativeGetMinimumAndroidPua(mNativeEmojiFactory);
        int maxVsp;
        maxVsp = nativeGetMaximumAndroidPua(mNativeEmojiFactory);
        int len;
        len = puaString.length();
        int[] codePoints;
        codePoints = new int[puaString.codePointCount(0, len)];
        int new_len;
        new_len = 0;
        {
            int i;
            i = 0;
            i = puaString.offsetByCodePoints(i, 1);
            {
                int codePoint;
                codePoint = puaString.codePointAt(i);
                {
                    int newCodePoint;
                    newCodePoint = getVendorSpecificPuaFromAndroidPua(codePoint);
                    {
                        codePoints[new_len] = newCodePoint;
                    } //End block
                } //End block
                codePoints[new_len] = codePoint;
            } //End block
        } //End collapsed parenthetic
        String var680B4B2ECED1E9EEFDE52DF18D8C48BF_2007125986 = (new String(codePoints, 0, new_len));
        return dsTaint.getTaintString();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        public static EmojiFactory newInstance(String class_name) {
        //DSFIXME:  CODE0012: Native static method requires manual modeling
    }

    
        public static EmojiFactory newAvailableInstance() {
        //DSFIXME:  CODE0012: Native static method requires manual modeling
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.022 -0400", hash_original_method = "E94B589829434939D487652724460608", hash_generated_method = "B7877E1F92299E4A83466646E7C5FEC5")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public int getMinimumAndroidPua() {
        int varCB479F1E511B763D23ECFD817A95C207_476735622 = (nativeGetMinimumAndroidPua(mNativeEmojiFactory));
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return nativeGetMinimumAndroidPua(mNativeEmojiFactory);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.023 -0400", hash_original_method = "84EA08D07D32F9FA087FFC75B38A56E8", hash_generated_method = "B9D77CD62F1BED69E5494296C6C93D0C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public int getMaximumAndroidPua() {
        int var2FC718F3AE897ED82051F97F3361EB32_1808609090 = (nativeGetMaximumAndroidPua(mNativeEmojiFactory));
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return nativeGetMaximumAndroidPua(mNativeEmojiFactory);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.023 -0400", hash_original_method = "996F154EA43AB1D5A4C151D656C37CF1", hash_generated_method = "70F0FC26A0A16766B85870AA005E7903")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void nativeDestructor(int factory) {
        dsTaint.addTaint(factory);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.023 -0400", hash_original_method = "3AA105C3169886A719F2BF7A3237E4A3", hash_generated_method = "C7E0C17D013432EDDB80D9FF9B953280")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private Bitmap nativeGetBitmapFromAndroidPua(int nativeEmojiFactory, int AndroidPua) {
        dsTaint.addTaint(AndroidPua);
        dsTaint.addTaint(nativeEmojiFactory);
        return (Bitmap)dsTaint.getTaint();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.023 -0400", hash_original_method = "3A1554F06CC9CCAF25F7BBE93248CE83", hash_generated_method = "FAD04ECADC55A4DA50287E601379B3CE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetAndroidPuaFromVendorSpecificSjis(int nativeEmojiFactory,
            char sjis) {
        dsTaint.addTaint(sjis);
        dsTaint.addTaint(nativeEmojiFactory);
        return dsTaint.getTaintInt();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.023 -0400", hash_original_method = "C302C3634C2FA6E6CA4CF2CF66D12101", hash_generated_method = "10021A9C8D0AF2B0165D7BCEF33A22B1")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetVendorSpecificSjisFromAndroidPua(int nativeEmojiFactory,
            int pua) {
        dsTaint.addTaint(nativeEmojiFactory);
        dsTaint.addTaint(pua);
        return dsTaint.getTaintInt();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.024 -0400", hash_original_method = "75FE7601C9BA865A32982CD0867F654C", hash_generated_method = "6FA95966CE611F24F04B8D89C6EA1264")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetAndroidPuaFromVendorSpecificPua(int nativeEmojiFactory,
            int vsp) {
        dsTaint.addTaint(nativeEmojiFactory);
        dsTaint.addTaint(vsp);
        return dsTaint.getTaintInt();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.024 -0400", hash_original_method = "B406416ABF41BEA242E5A40B8BA7386A", hash_generated_method = "71F0E55DA612B43A9A84DC280E351345")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetVendorSpecificPuaFromAndroidPua(int nativeEmojiFactory,
            int pua) {
        dsTaint.addTaint(nativeEmojiFactory);
        dsTaint.addTaint(pua);
        return dsTaint.getTaintInt();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.024 -0400", hash_original_method = "5A805426DAFA5C3640F25323B1C3B8F5", hash_generated_method = "2E111CA235A651F06663FB51D308F3B4")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetMaximumVendorSpecificPua(int nativeEmojiFactory) {
        dsTaint.addTaint(nativeEmojiFactory);
        return dsTaint.getTaintInt();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.024 -0400", hash_original_method = "0FF4320BBE7657B90285375F65F4DF9D", hash_generated_method = "52BF6CBE6C4F5099C56084EECE348BED")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetMinimumVendorSpecificPua(int nativeEmojiFactory) {
        dsTaint.addTaint(nativeEmojiFactory);
        return dsTaint.getTaintInt();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.024 -0400", hash_original_method = "800100FBB9205E441349161A70ADF047", hash_generated_method = "F38C44803B9CFA5D16660F8588FAED1D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetMaximumAndroidPua(int nativeEmojiFactory) {
        dsTaint.addTaint(nativeEmojiFactory);
        return dsTaint.getTaintInt();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.025 -0400", hash_original_method = "5875EE70D8D47117F3D06FA307D98BB5", hash_generated_method = "84A4330056F4F56F482EC27EC57CA634")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private int nativeGetMinimumAndroidPua(int nativeEmojiFactory) {
        dsTaint.addTaint(nativeEmojiFactory);
        return dsTaint.getTaintInt();
    }

    
    private class CustomLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.025 -0400", hash_original_method = "EFD1513E9BFBC9B14636F3CC7AC41B6C", hash_generated_method = "19A9879B12E07C26C0FDCF805FDF3C6D")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        public CustomLinkedHashMap() {
            super(16, 0.75f, true);
            // ---------- Original Method ----------
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:47.025 -0400", hash_original_method = "A5B2DC9A6DACFA347C16D8B42A67BD03", hash_generated_method = "75C3ADFA8F4708E923091D7CC6050485")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            dsTaint.addTaint(eldest.dsTaint);
            boolean varC11A82D981A81510AE8FF14A739DFE21_1753212110 = (size() > sCacheSize);
            return dsTaint.getTaintBoolean();
            // ---------- Original Method ----------
            //return size() > sCacheSize;
        }

        
    }


    
}

