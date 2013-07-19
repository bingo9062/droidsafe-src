package android.os;

// Droidsafe Imports
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class ParcelFileDescriptor implements Parcelable {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.080 -0400", hash_original_field = "D118306A98C1E7C12AA828417A60A6F6", hash_generated_field = "58A1B3A123725E48931BD35C0EB2D4CB")

    private FileDescriptor mFileDescriptor;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.080 -0400", hash_original_field = "C15A1B081B66F38188E926501ED5F8A7", hash_generated_field = "BD690B26D5468868AFAF08CAFB003D2A")

    private boolean mClosed;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.080 -0400", hash_original_field = "7CE9BC3451B021C57B703C29E2527A74", hash_generated_field = "9AA0DA752DD4578B6A205C30B394DEF2")

    private ParcelFileDescriptor mParcelDescriptor;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.080 -0400", hash_original_method = "DB42A2A8BBE574C2950DCEEDDCE79B62", hash_generated_method = "0D84580F8C4180A00E40F7A16F093250")
    public  ParcelFileDescriptor(ParcelFileDescriptor descriptor) {
        super();
        mParcelDescriptor = descriptor;
        mFileDescriptor = mParcelDescriptor.mFileDescriptor;
        // ---------- Original Method ----------
        //mParcelDescriptor = descriptor;
        //mFileDescriptor = mParcelDescriptor.mFileDescriptor;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.081 -0400", hash_original_method = "5987A31E1ECCB1911B1BA6CC4EEF72A2", hash_generated_method = "DEA258586F6A251A1D9F6010DC08FD9D")
      ParcelFileDescriptor(FileDescriptor descriptor) {
        super();
        if(descriptor == null)        
        {
            NullPointerException var6CECE4DBF93816ADA1AE39F6AB5F20EC_269807703 = new NullPointerException("descriptor must not be null");
            var6CECE4DBF93816ADA1AE39F6AB5F20EC_269807703.addTaint(taint);
            throw var6CECE4DBF93816ADA1AE39F6AB5F20EC_269807703;
        } //End block
        mFileDescriptor = descriptor;
        mParcelDescriptor = null;
        // ---------- Original Method ----------
        //if (descriptor == null) {
            //throw new NullPointerException("descriptor must not be null");
        //}
        //mFileDescriptor = descriptor;
        //mParcelDescriptor = null;
    }

    
    public static ParcelFileDescriptor open(File file, int mode) throws FileNotFoundException {
        String path = file.getPath();
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(path);
            if ((mode&MODE_WRITE_ONLY) != 0) {
                security.checkWrite(path);
            }
        }
        if ((mode&MODE_READ_WRITE) == 0) {
            throw new IllegalArgumentException(
                    "Must specify MODE_READ_ONLY, MODE_WRITE_ONLY, or MODE_READ_WRITE");
        }
        FileDescriptor fd = Parcel.openFileDescriptor(path, mode);
        return fd != null ? new ParcelFileDescriptor(fd) : null;
    }

    
    public static ParcelFileDescriptor dup(FileDescriptor orig) throws IOException {
        FileDescriptor fd = Parcel.dupFileDescriptor(orig);
        return fd != null ? new ParcelFileDescriptor(fd) : null;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.082 -0400", hash_original_method = "474327607EE56B056BCE4043FE865D8B", hash_generated_method = "EAAF4F53D5D4D58454D7871F74679CC5")
    public ParcelFileDescriptor dup() throws IOException {
ParcelFileDescriptor var8F8003894920E6C8D79BF232DC1A425D_560875390 =         dup(getFileDescriptor());
        var8F8003894920E6C8D79BF232DC1A425D_560875390.addTaint(taint);
        return var8F8003894920E6C8D79BF232DC1A425D_560875390;
        // ---------- Original Method ----------
        //return dup(getFileDescriptor());
    }

    
    public static ParcelFileDescriptor fromFd(int fd) throws IOException {
        FileDescriptor fdesc = getFileDescriptorFromFd(fd);
        return new ParcelFileDescriptor(fdesc);
    }

    
    @DSModeled(DSC.SAFE)
    private static FileDescriptor getFileDescriptorFromFd(int fd) throws IOException {
    	return new FileDescriptor();
    }

    
    public static ParcelFileDescriptor adoptFd(int fd) {
        FileDescriptor fdesc = getFileDescriptorFromFdNoDup(fd);
        return new ParcelFileDescriptor(fdesc);
    }

    
    @DSModeled(DSC.SAFE)
    private static FileDescriptor getFileDescriptorFromFdNoDup(int fd) {
    	return new FileDescriptor();
    }

    
    @DSModeled(DSC.SPEC)
    public static ParcelFileDescriptor fromSocket(Socket socket) {
        FileDescriptor fd = socket.getFileDescriptor$();
        return fd != null ? new ParcelFileDescriptor(fd) : null;
    }

    
    public static ParcelFileDescriptor fromDatagramSocket(DatagramSocket datagramSocket) {
        FileDescriptor fd = datagramSocket.getFileDescriptor$();
        return fd != null ? new ParcelFileDescriptor(fd) : null;
    }

    
    public static ParcelFileDescriptor[] createPipe() throws IOException {
        FileDescriptor[] fds = new FileDescriptor[2];
        createPipeNative(fds);
        ParcelFileDescriptor[] pfds = new ParcelFileDescriptor[2];
        pfds[0] = new ParcelFileDescriptor(fds[0]);
        pfds[1] = new ParcelFileDescriptor(fds[1]);
        return pfds;
    }

    
    @DSModeled(DSC.SAFE)
    private static void createPipeNative(FileDescriptor[] outFds) throws IOException {
    }

    
    @Deprecated
    public static ParcelFileDescriptor fromData(byte[] data, String name) throws IOException {
        if (data == null) return null;
        MemoryFile file = new MemoryFile(name, data.length);
        if (data.length > 0) {
            file.writeBytes(data, 0, 0, data.length);
        }
        file.deactivate();
        FileDescriptor fd = file.getFileDescriptor();
        return fd != null ? new ParcelFileDescriptor(fd) : null;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.095 -0400", hash_original_method = "949276D2764A2C9DACACDE44898698C8", hash_generated_method = "D9E6793838F1D6323D2DA573332F0B76")
    public FileDescriptor getFileDescriptor() {
FileDescriptor varBF65C9190F4896E9BF5F66CF67037E9B_1769834910 =         mFileDescriptor;
        varBF65C9190F4896E9BF5F66CF67037E9B_1769834910.addTaint(taint);
        return varBF65C9190F4896E9BF5F66CF67037E9B_1769834910;
        // ---------- Original Method ----------
        //return mFileDescriptor;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.095 -0400", hash_original_method = "77F816B0B1C349FF3A8882E701737452", hash_generated_method = "C5689BD8FC686E969653EE5D42F2974E")
    public long getStatSize() {
        long var0F5264038205EDFB1AC05FBB0E8C5E94_1448066391 = getTaintLong();
        return var0F5264038205EDFB1AC05FBB0E8C5E94_1448066391;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.095 -0400", hash_original_method = "0F9A3950A7A9E5BB31D919F8BB19673E", hash_generated_method = "02F6F1D4E464EDB12A88E6F12D8C04E2")
    public long seekTo(long pos) {
        long var0F5264038205EDFB1AC05FBB0E8C5E94_75496503 = getTaintLong();
        return var0F5264038205EDFB1AC05FBB0E8C5E94_75496503;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.095 -0400", hash_original_method = "C747587E89EFBC650597F209F149B598", hash_generated_method = "A5F96240A769F2859EDCB25B833E8782")
    public int getFd() {
        if(mClosed)        
        {
            IllegalStateException varFC8456B24C14638E092CF6A7A56825A1_1697553032 = new IllegalStateException("Already closed");
            varFC8456B24C14638E092CF6A7A56825A1_1697553032.addTaint(taint);
            throw varFC8456B24C14638E092CF6A7A56825A1_1697553032;
        } //End block
        int var3476AB58648A22F39E53701B72707A3C_1718443262 = (getFdNative());
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1424696160 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1424696160;
        // ---------- Original Method ----------
        //if (mClosed) {
            //throw new IllegalStateException("Already closed");
        //}
        //return getFdNative();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.096 -0400", hash_original_method = "48F8D374A617845984C88F484B28408C", hash_generated_method = "A2787565C28B8BCDF7EB2F706C6BF8E0")
    private int getFdNative() {
        int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1508916302 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1508916302;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.096 -0400", hash_original_method = "AECA8304F263BE3AD120D5996456D31E", hash_generated_method = "CE7243A2D56E9AF0AAF8CA6AB2D73982")
    public int detachFd() {
        if(mClosed)        
        {
            IllegalStateException varFC8456B24C14638E092CF6A7A56825A1_1863571920 = new IllegalStateException("Already closed");
            varFC8456B24C14638E092CF6A7A56825A1_1863571920.addTaint(taint);
            throw varFC8456B24C14638E092CF6A7A56825A1_1863571920;
        } //End block
        if(mParcelDescriptor != null)        
        {
            int fd = mParcelDescriptor.detachFd();
            mClosed = true;
            int var36EBA1E1E343279857EA7F69A597324E_367853819 = (fd);
                        int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1058999505 = getTaintInt();
            return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1058999505;
        } //End block
        int fd = getFd();
        mClosed = true;
        Parcel.clearFileDescriptor(mFileDescriptor);
        int var36EBA1E1E343279857EA7F69A597324E_141561920 = (fd);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_986610411 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_986610411;
        // ---------- Original Method ----------
        //if (mClosed) {
            //throw new IllegalStateException("Already closed");
        //}
        //if (mParcelDescriptor != null) {
            //int fd = mParcelDescriptor.detachFd();
            //mClosed = true;
            //return fd;
        //}
        //int fd = getFd();
        //mClosed = true;
        //Parcel.clearFileDescriptor(mFileDescriptor);
        //return fd;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.096 -0400", hash_original_method = "39939000D7522F3E727F0E3243089AE5", hash_generated_method = "63241076498E671C9DEAB1B1F4F29A6F")
    public void close() throws IOException {
        synchronized
(this)        {
            if(mClosed)            
            return;
            mClosed = true;
        } //End block
        if(mParcelDescriptor != null)        
        {
            mParcelDescriptor.close();
        } //End block
        else
        {
            Parcel.closeFileDescriptor(mFileDescriptor);
        } //End block
        // ---------- Original Method ----------
        //synchronized (this) {
            //if (mClosed) return;
            //mClosed = true;
        //}
        //if (mParcelDescriptor != null) {
            //mParcelDescriptor.close();
        //} else {
            //Parcel.closeFileDescriptor(mFileDescriptor);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.096 -0400", hash_original_method = "BA9513A0FEF5B159A730B41F75EA216A", hash_generated_method = "308B8072C7B4A05A45101D678DA7A26D")
    @Override
    public String toString() {
String varD0E163889A7CDE1AC2BFE5012A2D59E4_1250576866 =         "{ParcelFileDescriptor: " + mFileDescriptor + "}";
        varD0E163889A7CDE1AC2BFE5012A2D59E4_1250576866.addTaint(taint);
        return varD0E163889A7CDE1AC2BFE5012A2D59E4_1250576866;
        // ---------- Original Method ----------
        //return "{ParcelFileDescriptor: " + mFileDescriptor + "}";
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.097 -0400", hash_original_method = "47AC631E45892E7E44A62B81BC7ACB91", hash_generated_method = "2C95303D396852E98F94E126879F65B0")
    @Override
    protected void finalize() throws Throwable {
        try 
        {
            if(!mClosed)            
            {
                close();
            } //End block
        } //End block
        finally 
        {
            super.finalize();
        } //End block
        // ---------- Original Method ----------
        //try {
            //if (!mClosed) {
                //close();
            //}
        //} finally {
            //super.finalize();
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.097 -0400", hash_original_method = "5C5A30224996A2D414D9176559E683A9", hash_generated_method = "F9DAE84573CBA3B55E066597D2013708")
    public int describeContents() {
        int var7D45AD2A24206A9DE492E2B68DB53120_1936450256 = (Parcelable.CONTENTS_FILE_DESCRIPTOR);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1705271951 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1705271951;
        // ---------- Original Method ----------
        //return Parcelable.CONTENTS_FILE_DESCRIPTOR;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.097 -0400", hash_original_method = "D9382402A3E15CFE2B7948813716CC7C", hash_generated_method = "E57BECB45166B7AA9A72A4222C2E2640")
    public void writeToParcel(Parcel out, int flags) {
        addTaint(flags);
        addTaint(out.getTaint());
        out.writeFileDescriptor(mFileDescriptor);
        if((flags&PARCELABLE_WRITE_RETURN_VALUE) != 0 && !mClosed)        
        {
            try 
            {
                close();
            } //End block
            catch (IOException e)
            {
            } //End block
        } //End block
        // ---------- Original Method ----------
        //out.writeFileDescriptor(mFileDescriptor);
        //if ((flags&PARCELABLE_WRITE_RETURN_VALUE) != 0 && !mClosed) {
            //try {
                //close();
            //} catch (IOException e) {
            //}
        //}
    }

    
    public static class AutoCloseInputStream extends FileInputStream {
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.097 -0400", hash_original_field = "E2307FD862BA74C9C9C26ACA0B7E5364", hash_generated_field = "AE959CCFA06A07F93FA2A8BEED883021")

        private ParcelFileDescriptor mFd;
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.097 -0400", hash_original_method = "E3D4E7E6DEAE42263A4A251652766597", hash_generated_method = "4971668D7654AFFF55B2DDD509DBD7E3")
        public  AutoCloseInputStream(ParcelFileDescriptor fd) {
            super(fd.getFileDescriptor());
            mFd = fd;
            // ---------- Original Method ----------
            //mFd = fd;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.097 -0400", hash_original_method = "3297019645A62F287411C53B25C33F9A", hash_generated_method = "64EED0A8F37058D0517D6328F7FEB62F")
        @Override
        public void close() throws IOException {
            try 
            {
                mFd.close();
            } //End block
            finally 
            {
                super.close();
            } //End block
            // ---------- Original Method ----------
            //try {
                //mFd.close();
            //} finally {
                //super.close();
            //}
        }

        
    }


    
    public static class AutoCloseOutputStream extends FileOutputStream {
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.097 -0400", hash_original_field = "E2307FD862BA74C9C9C26ACA0B7E5364", hash_generated_field = "AE959CCFA06A07F93FA2A8BEED883021")

        private ParcelFileDescriptor mFd;
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_method = "AC6B0F3765CD3D3DD6495992ADDCA299", hash_generated_method = "3519A8D63370D6208317BADE5496D9F6")
        public  AutoCloseOutputStream(ParcelFileDescriptor fd) {
            super(fd.getFileDescriptor());
            mFd = fd;
            // ---------- Original Method ----------
            //mFd = fd;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_method = "3297019645A62F287411C53B25C33F9A", hash_generated_method = "64EED0A8F37058D0517D6328F7FEB62F")
        @Override
        public void close() throws IOException {
            try 
            {
                mFd.close();
            } //End block
            finally 
            {
                super.close();
            } //End block
            // ---------- Original Method ----------
            //try {
                //mFd.close();
            //} finally {
                //super.close();
            //}
        }

        
    }


    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "BA2BAC1F18752DA00A4F6572A504B359", hash_generated_field = "3A425E25D2158592F4B71395B73ABA48")

    public static final int MODE_WORLD_READABLE = 0x00000001;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "17D2B2ED3DC4B3B97CA172345DAD8657", hash_generated_field = "F70FF863E9B85A3B3C75BCAF712387C6")

    public static final int MODE_WORLD_WRITEABLE = 0x00000002;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "315D150280C704F0621DA42B7EABEA77", hash_generated_field = "6B866752AF78022533961EAFEC48BF90")

    public static final int MODE_READ_ONLY = 0x10000000;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "264C60EDCCFAB2B0358041AC33FEEE6B", hash_generated_field = "EE8DCC6B27D46EC2ED14ABA75E7783AA")

    public static final int MODE_WRITE_ONLY = 0x20000000;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "48FA6B9BAC27C5E37C2AC15B73BEB0F5", hash_generated_field = "6494B31AC04BF207173A1DA805A4DC2B")

    public static final int MODE_READ_WRITE = 0x30000000;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "64F9E90F65FFACE62B2054906BA03800", hash_generated_field = "C6F7880235FE436C915681552C8587CE")

    public static final int MODE_CREATE = 0x08000000;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "7507DA721AE1A637B2BABAD93D9F8266", hash_generated_field = "2EC651F48C0C7F452AF49E37006FEB27")

    public static final int MODE_TRUNCATE = 0x04000000;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "8E4ACDF6D243F49EEB6EAAF3B5A6BCCA", hash_generated_field = "299AF71922C12BCCD82076F57034ABBF")

    public static final int MODE_APPEND = 0x02000000;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:27.098 -0400", hash_original_field = "195ED69EAF088F7FA956DC093526DC53", hash_generated_field = "DA358939EDE4BFAAAF0FF49DA63A85F3")

    public static final Parcelable.Creator<ParcelFileDescriptor> CREATOR
            = new Parcelable.Creator<ParcelFileDescriptor>() {
        public ParcelFileDescriptor createFromParcel(Parcel in) {
            return in.readFileDescriptor();
        }
        public ParcelFileDescriptor[] newArray(int size) {
            return new ParcelFileDescriptor[size];
        }
    };
    // orphaned legacy method
    public ParcelFileDescriptor() {
    	
    }
    
    // orphaned legacy method
    public ParcelFileDescriptor[] newArray(int size) {
            return new ParcelFileDescriptor[size];
        }
    
    // orphaned legacy method
    public ParcelFileDescriptor createFromParcel(Parcel in) {
            return in.readFileDescriptor();
        }
    
}

