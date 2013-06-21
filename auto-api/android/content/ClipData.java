package android.content;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ClipData implements Parcelable {
    ClipDescription mClipDescription;
    Bitmap mIcon;
    ArrayList<Item> mItems = new ArrayList<Item>();
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.020 -0400", hash_original_method = "EBF505A176A1691C0AFF349A48F88B72", hash_generated_method = "22891209506E587A77B1B9B469F1F111")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public ClipData(CharSequence label, String[] mimeTypes, Item item) {
        dsTaint.addTaint(item.dsTaint);
        dsTaint.addTaint(label);
        dsTaint.addTaint(mimeTypes[0]);
        mClipDescription = new ClipDescription(label, mimeTypes);
        {
            if (DroidSafeAndroidRuntime.control) throw new NullPointerException("item is null");
        } //End block
        mIcon = null;
        mItems.add(item);
        // ---------- Original Method ----------
        //mClipDescription = new ClipDescription(label, mimeTypes);
        //if (item == null) {
            //throw new NullPointerException("item is null");
        //}
        //mIcon = null;
        //mItems.add(item);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.020 -0400", hash_original_method = "07F73711C3AF49FFEB328A8FE4F43045", hash_generated_method = "470A0BB1AA2256B7B9703E7799B63F39")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public ClipData(ClipDescription description, Item item) {
        dsTaint.addTaint(description.dsTaint);
        dsTaint.addTaint(item.dsTaint);
        {
            if (DroidSafeAndroidRuntime.control) throw new NullPointerException("item is null");
        } //End block
        mIcon = null;
        mItems.add(item);
        // ---------- Original Method ----------
        //mClipDescription = description;
        //if (item == null) {
            //throw new NullPointerException("item is null");
        //}
        //mIcon = null;
        //mItems.add(item);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.021 -0400", hash_original_method = "58D4E89221B1347651A83B7DDCBD7718", hash_generated_method = "94412E492D164A9FBB766EC1A3341B97")
    //DSFIXME:  CODE0002: Requires DSC value to be set
     ClipData(Parcel in) {
        dsTaint.addTaint(in.dsTaint);
        mClipDescription = new ClipDescription(in);
        {
            boolean var25D67F28E4887DDC152DCB9726EAB4D3_795432815 = (in.readInt() != 0);
            {
                mIcon = Bitmap.CREATOR.createFromParcel(in);
            } //End block
            {
                mIcon = null;
            } //End block
        } //End collapsed parenthetic
        int N;
        N = in.readInt();
        {
            int i;
            i = 0;
            {
                CharSequence text;
                text = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
                Intent intent;
                boolean var331417C59A822E59FB0B216D2F29CB47_116072823 = (in.readInt() != 0);
                intent = Intent.CREATOR.createFromParcel(in);
                intent = null;
                Uri uri;
                boolean var331417C59A822E59FB0B216D2F29CB47_37064575 = (in.readInt() != 0);
                uri = Uri.CREATOR.createFromParcel(in);
                uri = null;
                mItems.add(new Item(text, intent, uri));
            } //End block
        } //End collapsed parenthetic
        // ---------- Original Method ----------
        //mClipDescription = new ClipDescription(in);
        //if (in.readInt() != 0) {
            //mIcon = Bitmap.CREATOR.createFromParcel(in);
        //} else {
            //mIcon = null;
        //}
        //final int N = in.readInt();
        //for (int i=0; i<N; i++) {
            //CharSequence text = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
            //Intent intent = in.readInt() != 0 ? Intent.CREATOR.createFromParcel(in) : null;
            //Uri uri = in.readInt() != 0 ? Uri.CREATOR.createFromParcel(in) : null;
            //mItems.add(new Item(text, intent, uri));
        //}
    }

    
        static public ClipData newPlainText(CharSequence label, CharSequence text) {
        Item item = new Item(text);
        return new ClipData(label, MIMETYPES_TEXT_PLAIN, item);
    }

    
        static public ClipData newIntent(CharSequence label, Intent intent) {
        Item item = new Item(intent);
        return new ClipData(label, MIMETYPES_TEXT_INTENT, item);
    }

    
        static public ClipData newUri(ContentResolver resolver, CharSequence label,
            Uri uri) {
        Item item = new Item(uri);
        String[] mimeTypes = null;
        if ("content".equals(uri.getScheme())) {
            String realType = resolver.getType(uri);
            mimeTypes = resolver.getStreamTypes(uri, "*/*");
            if (mimeTypes == null) {
                if (realType != null) {
                    mimeTypes = new String[] { realType, ClipDescription.MIMETYPE_TEXT_URILIST };
                }
            } else {
                String[] tmp = new String[mimeTypes.length + (realType != null ? 2 : 1)];
                int i = 0;
                if (realType != null) {
                    tmp[0] = realType;
                    i++;
                }
                System.arraycopy(mimeTypes, 0, tmp, i, mimeTypes.length);
                tmp[i + mimeTypes.length] = ClipDescription.MIMETYPE_TEXT_URILIST;
                mimeTypes = tmp;
            }
        }
        if (mimeTypes == null) {
            mimeTypes = MIMETYPES_TEXT_URILIST;
        }
        return new ClipData(label, mimeTypes, item);
    }

    
        static public ClipData newRawUri(CharSequence label, Uri uri) {
        Item item = new Item(uri);
        return new ClipData(label, MIMETYPES_TEXT_URILIST, item);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.022 -0400", hash_original_method = "8475A7793CA47249207DFD30E601781C", hash_generated_method = "6FB27BFBC23A551A7D2306438ECE71F4")
    @DSModeled(DSC.SAFE)
    public ClipDescription getDescription() {
        return (ClipDescription)dsTaint.getTaint();
        // ---------- Original Method ----------
        //return mClipDescription;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.030 -0400", hash_original_method = "150F076539D3E4866582D984173D26A6", hash_generated_method = "852D859BD0258D168D3E7F11BF4F3064")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void addItem(Item item) {
        dsTaint.addTaint(item.dsTaint);
        {
            if (DroidSafeAndroidRuntime.control) throw new NullPointerException("item is null");
        } //End block
        mItems.add(item);
        // ---------- Original Method ----------
        //if (item == null) {
            //throw new NullPointerException("item is null");
        //}
        //mItems.add(item);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.030 -0400", hash_original_method = "229D9D1026C0301FD9A4AD50AC984F17", hash_generated_method = "154B39A9C04DD5A4892EAC598806270F")
    @DSModeled(DSC.SAFE)
    public Bitmap getIcon() {
        return (Bitmap)dsTaint.getTaint();
        // ---------- Original Method ----------
        //return mIcon;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.031 -0400", hash_original_method = "2908203B9FEFAA3035EA88685E6DC23A", hash_generated_method = "0B2D6A1C7AA45F3D8E3CD96AF8FB6386")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public int getItemCount() {
        int var903E593E570CF8A7196E435EA30221CF_948879250 = (mItems.size());
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return mItems.size();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.031 -0400", hash_original_method = "79B4F12EE34F43C8036D233ADFF72DDF", hash_generated_method = "3E145EDFDBA3FA52C74622EC6C146B0B")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public Item getItemAt(int index) {
        dsTaint.addTaint(index);
        Item var50B6C60F66EE15A22205325C098ECD35_135996790 = (mItems.get(index));
        return (Item)dsTaint.getTaint();
        // ---------- Original Method ----------
        //return mItems.get(index);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.031 -0400", hash_original_method = "00F8174F9E89D0C972FA6D3F19742382", hash_generated_method = "0DB50BC0CCE4711867E95DEC1B2117C7")
    @DSModeled(DSC.SAFE)
    @Override
    public int describeContents() {
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return 0;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.032 -0400", hash_original_method = "0C9FB932279AD72A7EC79D2538384FC0", hash_generated_method = "20D8E021FC7880C21F3125F1FE20D2D6")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dsTaint.addTaint(flags);
        dsTaint.addTaint(dest.dsTaint);
        mClipDescription.writeToParcel(dest, flags);
        {
            dest.writeInt(1);
            mIcon.writeToParcel(dest, flags);
        } //End block
        {
            dest.writeInt(0);
        } //End block
        int N;
        N = mItems.size();
        dest.writeInt(N);
        {
            int i;
            i = 0;
            {
                Item item;
                item = mItems.get(i);
                TextUtils.writeToParcel(item.mText, dest, flags);
                {
                    dest.writeInt(1);
                    item.mIntent.writeToParcel(dest, flags);
                } //End block
                {
                    dest.writeInt(0);
                } //End block
                {
                    dest.writeInt(1);
                    item.mUri.writeToParcel(dest, flags);
                } //End block
                {
                    dest.writeInt(0);
                } //End block
            } //End block
        } //End collapsed parenthetic
        // ---------- Original Method ----------
        //mClipDescription.writeToParcel(dest, flags);
        //if (mIcon != null) {
            //dest.writeInt(1);
            //mIcon.writeToParcel(dest, flags);
        //} else {
            //dest.writeInt(0);
        //}
        //final int N = mItems.size();
        //dest.writeInt(N);
        //for (int i=0; i<N; i++) {
            //Item item = mItems.get(i);
            //TextUtils.writeToParcel(item.mText, dest, flags);
            //if (item.mIntent != null) {
                //dest.writeInt(1);
                //item.mIntent.writeToParcel(dest, flags);
            //} else {
                //dest.writeInt(0);
            //}
            //if (item.mUri != null) {
                //dest.writeInt(1);
                //item.mUri.writeToParcel(dest, flags);
            //} else {
                //dest.writeInt(0);
            //}
        //}
    }

    
    public static class Item {
        CharSequence mText;
        Intent mIntent;
        Uri mUri;
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.032 -0400", hash_original_method = "4A009EFCCF51C2EA640445D87C84D900", hash_generated_method = "A2BB0811C4A13F5A276D8662C3F78975")
        @DSModeled(DSC.SAFE)
        public Item(CharSequence text) {
            dsTaint.addTaint(text);
            mIntent = null;
            mUri = null;
            // ---------- Original Method ----------
            //mText = text;
            //mIntent = null;
            //mUri = null;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.033 -0400", hash_original_method = "11EC393E75756EFF910C83D0B79625D8", hash_generated_method = "C8B4E6BA578C05F33693B875C1FB0CF6")
        @DSModeled(DSC.SPEC)
        public Item(Intent intent) {
            dsTaint.addTaint(intent.dsTaint);
            mText = null;
            mUri = null;
            // ---------- Original Method ----------
            //mText = null;
            //mIntent = intent;
            //mUri = null;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.033 -0400", hash_original_method = "C0C0DA6EE7D207BF80500DD7F8FC5437", hash_generated_method = "C48522224D8C099879752ABAAACADD8F")
        @DSModeled(DSC.SPEC)
        public Item(Uri uri) {
            dsTaint.addTaint(uri.dsTaint);
            mText = null;
            mIntent = null;
            // ---------- Original Method ----------
            //mText = null;
            //mIntent = null;
            //mUri = uri;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.034 -0400", hash_original_method = "40EC9D6AF0E3564A5C6CD681E96DEE48", hash_generated_method = "AD8D56FAE21B716D4D2F9A94D9D7F2C6")
        @DSModeled(DSC.SPEC)
        public Item(CharSequence text, Intent intent, Uri uri) {
            dsTaint.addTaint(text);
            dsTaint.addTaint(uri.dsTaint);
            dsTaint.addTaint(intent.dsTaint);
            // ---------- Original Method ----------
            //mText = text;
            //mIntent = intent;
            //mUri = uri;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.035 -0400", hash_original_method = "D3D56665E0CC0B43413FBFB4C720E96C", hash_generated_method = "0EE8DAE8D11C9C0940FB60F3DE0E7055")
        @DSModeled(DSC.SAFE)
        public CharSequence getText() {
            return dsTaint.getTaintString();
            // ---------- Original Method ----------
            //return mText;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.036 -0400", hash_original_method = "AD027B7B58A4A2F151CC138FB7B23244", hash_generated_method = "22770DE3BF38836C28544CA3834587CE")
        @DSModeled(DSC.SPEC)
        public Intent getIntent() {
            return (Intent)dsTaint.getTaint();
            // ---------- Original Method ----------
            //return mIntent;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.037 -0400", hash_original_method = "225259AA593B6A59F476A2C569F1B075", hash_generated_method = "CA157FF276D4C63A050BB248CD0D94B8")
        @DSModeled(DSC.SPEC)
        public Uri getUri() {
            return (Uri)dsTaint.getTaint();
            // ---------- Original Method ----------
            //return mUri;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.038 -0400", hash_original_method = "2A1795F61A3E14A1308A3A7CD55951FC", hash_generated_method = "10439932C18A5EADA0AD949349CCE58D")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        public CharSequence coerceToText(Context context) {
            dsTaint.addTaint(context.dsTaint);
            {
                FileInputStream stream;
                stream = null;
                try 
                {
                    AssetFileDescriptor descr;
                    descr = context.getContentResolver()
                            .openTypedAssetFileDescriptor(mUri, "text/*", null);
                    stream = descr.createInputStream();
                    InputStreamReader reader;
                    reader = new InputStreamReader(stream, "UTF-8");
                    StringBuilder builder;
                    builder = new StringBuilder(128);
                    char[] buffer;
                    buffer = new char[8192];
                    int len;
                    {
                        boolean var23C869020A3A67DDAE5E9A347BAC4416_1031159418 = ((len=reader.read(buffer)) > 0);
                        {
                            builder.append(buffer, 0, len);
                        } //End block
                    } //End collapsed parenthetic
                    CharSequence var1F59284C411F9419B065CDD234D02411_1951077217 = (builder.toString());
                } //End block
                catch (FileNotFoundException e)
                { }
                catch (IOException e)
                {
                    CharSequence var06CA5ED4FBDCBE4BE16F532D9B33FBEF_1868947229 = (e.toString());
                } //End block
                finally 
                {
                    {
                        try 
                        {
                            stream.close();
                        } //End block
                        catch (IOException e)
                        { }
                    } //End block
                } //End block
                CharSequence var9672E488737864E5D560CF8261D3DE51_1339546763 = (mUri.toString());
            } //End block
            {
                CharSequence varBBEDEEBA998A1B73C99F78C431323EB9_1540554381 = (mIntent.toUri(Intent.URI_INTENT_SCHEME));
            } //End block
            return dsTaint.getTaintString();
            // ---------- Original Method ----------
            // Original Method Too Long, Refer to Original Implementation
        }

        
    }


    
    static final String[] MIMETYPES_TEXT_PLAIN = new String[] {
        ClipDescription.MIMETYPE_TEXT_PLAIN };
    static final String[] MIMETYPES_TEXT_URILIST = new String[] {
        ClipDescription.MIMETYPE_TEXT_URILIST };
    static final String[] MIMETYPES_TEXT_INTENT = new String[] {
        ClipDescription.MIMETYPE_TEXT_INTENT };
    public static final Parcelable.Creator<ClipData> CREATOR = new Parcelable.Creator<ClipData>() {        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.038 -0400", hash_original_method = "E9B77AA38C2D4F67ACBCF421C777825E", hash_generated_method = "E7909EFB6E0DDD1147BC13C006E98B15")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        public ClipData createFromParcel(Parcel source) {
            dsTaint.addTaint(source.dsTaint);
            ClipData var30C2F5A2BB880F14FB20AD2A93979A42_827546202 = (new ClipData(source));
            return (ClipData)dsTaint.getTaint();
            // ---------- Original Method ----------
            //return new ClipData(source);
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:40.039 -0400", hash_original_method = "4F6E120BA30B443AED50CCA81CAD8061", hash_generated_method = "12FF12E8E73D8A85B19FB8FEDF9FBB31")
        //DSFIXME:  CODE0002: Requires DSC value to be set
        public ClipData[] newArray(int size) {
            dsTaint.addTaint(size);
            ClipData[] var5DF48C00D1111A8BF6B051A951CC6DC7_253958968 = (new ClipData[size]);
            return (ClipData[])dsTaint.getTaint();
            // ---------- Original Method ----------
            //return new ClipData[size];
        }

        
}; //Transformed anonymous class
}

