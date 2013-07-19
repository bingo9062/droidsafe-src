package org.apache.http.impl.cookie;

// Droidsafe Imports
import java.util.Date;

import org.apache.http.cookie.SetCookie2;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.576 -0400", hash_original_field = "809FFB37A1E25E47BFCCD448CD9CFC3D", hash_generated_field = "6A2B50CEA40AC67F646E005F8BAEDBCB")

    private String commentURL;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.576 -0400", hash_original_field = "47F06098D3034F593871B524CE4F7965", hash_generated_field = "10C002323FE5D85E96EB29FD69E0FF2A")

    private int[] ports;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.576 -0400", hash_original_field = "70F68BB2F748B85DE6526BFE236EA1E9", hash_generated_field = "33439C2B9FDD0CF9B54F1993B130A7B9")

    private boolean discard;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.576 -0400", hash_original_method = "B06BAED2E07D7F9218D98A385CC362B8", hash_generated_method = "4A126B79B154DB4D1F86BF2812224D99")
    public  BasicClientCookie2(final String name, final String value) {
        super(name, value);
        addTaint(value.getTaint());
        addTaint(name.getTaint());
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.577 -0400", hash_original_method = "95D16F8D855E74509F3FC69A633DD645", hash_generated_method = "4F40EB229A6ED072CF58B0676D3AE418")
    @Override
    public int[] getPorts() {
        int[] var0482C0EE1F941A10F770EBAE9C743287_340385865 = (this.ports);
                int[] varB4CCCA26F9DB9189C32F33E82D425CFB_900616658 = {getTaintInt()};
        return varB4CCCA26F9DB9189C32F33E82D425CFB_900616658;
        // ---------- Original Method ----------
        //return this.ports;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.577 -0400", hash_original_method = "764909B3E1563DDB6A65C5637CB4CC25", hash_generated_method = "C9B871E54B4C065A0B7E18343F865239")
    public void setPorts(final int[] ports) {
        this.ports = ports;
        // ---------- Original Method ----------
        //this.ports = ports;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.578 -0400", hash_original_method = "601AD280A8E13C6CCEB794F77493867D", hash_generated_method = "8B61EE55FA25E8BF31B6409BD62DC716")
    @Override
    public String getCommentURL() {
String var0A98D510D3863CB5F81697FCEFEEC4BF_758526515 =         this.commentURL;
        var0A98D510D3863CB5F81697FCEFEEC4BF_758526515.addTaint(taint);
        return var0A98D510D3863CB5F81697FCEFEEC4BF_758526515;
        // ---------- Original Method ----------
        //return this.commentURL;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.578 -0400", hash_original_method = "D3350C04B38359935C8E8B49E5A32164", hash_generated_method = "0459F7D7F51F9287B0A6EC8A909F9C50")
    public void setCommentURL(final String commentURL) {
        this.commentURL = commentURL;
        // ---------- Original Method ----------
        //this.commentURL = commentURL;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.578 -0400", hash_original_method = "9446CEDD4455A1D30E4774BBDD3B750C", hash_generated_method = "E6CDF344C6C1B9923E6E003A3B1B943D")
    public void setDiscard(boolean discard) {
        this.discard = discard;
        // ---------- Original Method ----------
        //this.discard = discard;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.579 -0400", hash_original_method = "417FDF5C1FC11A52F2E0DFC4D001D3E3", hash_generated_method = "B84CA5EE579DB64291C16DB5764733BC")
    @Override
    public boolean isPersistent() {
        boolean varF660732C49DEF912D98F766D807A5150_1210704244 = (!this.discard && super.isPersistent());
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1395062746 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1395062746;
        // ---------- Original Method ----------
        //return !this.discard && super.isPersistent();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.579 -0400", hash_original_method = "D5A8F73F69F1E942ED94DA98E7190BDB", hash_generated_method = "50AAAC264CB85152B18E43C558DB5695")
    @Override
    public boolean isExpired(final Date date) {
        addTaint(date.getTaint());
        boolean varD11525C03BBCE57F94E025FC39AF4EF5_1444107467 = (this.discard || super.isExpired(date));
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_665038069 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_665038069;
        // ---------- Original Method ----------
        //return this.discard || super.isExpired(date);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:35.579 -0400", hash_original_method = "A1E29AF284BD9497DEE668CED1078224", hash_generated_method = "0FEDF2B1E4562A9521B4D821C9A251B9")
    @Override
    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 clone = (BasicClientCookie2) super.clone();
        clone.ports = this.ports.clone();
Object var3DE52045BFD3C1BF3742F994ED6139AD_562053942 =         clone;
        var3DE52045BFD3C1BF3742F994ED6139AD_562053942.addTaint(taint);
        return var3DE52045BFD3C1BF3742F994ED6139AD_562053942;
        // ---------- Original Method ----------
        //BasicClientCookie2 clone = (BasicClientCookie2) super.clone();
        //clone.ports = this.ports.clone();
        //return clone;
    }

    
}

