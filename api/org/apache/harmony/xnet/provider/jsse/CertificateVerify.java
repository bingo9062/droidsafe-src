package org.apache.harmony.xnet.provider.jsse;

// Droidsafe Imports
import java.io.IOException;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class CertificateVerify extends Message {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.010 -0400", hash_original_field = "85F856059502E932D8205DEEF6195328", hash_generated_field = "01A16F1FF859CB019178D18F23D7E606")

    byte[] signedHash;
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.011 -0400", hash_original_method = "F3A93A83C93F754D4846FCC8297217FD", hash_generated_method = "80B4C7BBCB19AD7DA3EBCCC9093A1710")
    public  CertificateVerify(byte[] hash) {
        if(hash == null || hash.length == 0)        
        {
            fatalAlert(AlertProtocol.INTERNAL_ERROR,
                    "INTERNAL ERROR: incorrect certificate verify hash");
        } //End block
        this.signedHash = hash;
        length = hash.length + 2;
        // ---------- Original Method ----------
        //if (hash == null || hash.length == 0) {
            //fatalAlert(AlertProtocol.INTERNAL_ERROR,
                    //"INTERNAL ERROR: incorrect certificate verify hash");
        //}
        //this.signedHash = hash;
        //length = hash.length + 2;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.012 -0400", hash_original_method = "C08A411708B1C45834DF498B4B831402", hash_generated_method = "1563C9EABE390A3F98EE0A0D08831534")
    public  CertificateVerify(HandshakeIODataStream in, int length) throws IOException {
        if(length == 0)        
        {
            fatalAlert(AlertProtocol.DECODE_ERROR,
                    "DECODE ERROR: incorrect CertificateVerify");
        } //End block
        else
        {
            if(in.readUint16() != length - 2)            
            {
                fatalAlert(AlertProtocol.DECODE_ERROR,
                        "DECODE ERROR: incorrect CertificateVerify");
            } //End block
            signedHash = in.read(length -2);
        } //End block
        this.length = length;
        // ---------- Original Method ----------
        //if (length == 0) {
            //fatalAlert(AlertProtocol.DECODE_ERROR,
                    //"DECODE ERROR: incorrect CertificateVerify");
        //} else {
            //if (in.readUint16() != length - 2) {
                //fatalAlert(AlertProtocol.DECODE_ERROR,
                        //"DECODE ERROR: incorrect CertificateVerify");
            //}
            //signedHash = in.read(length -2);
        //}
        //this.length = length;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.012 -0400", hash_original_method = "8645B529D351D3837595463E8D8CA061", hash_generated_method = "9D702605B3262E68B958372B36F6E95E")
    @Override
    public void send(HandshakeIODataStream out) {
        addTaint(out.getTaint());
        if(signedHash.length != 0)        
        {
            out.writeUint16(signedHash.length);
            out.write(signedHash);
        } //End block
        // ---------- Original Method ----------
        //if (signedHash.length != 0) {
            //out.writeUint16(signedHash.length);
            //out.write(signedHash);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.013 -0400", hash_original_method = "78FC60AEF6831C8195D1932C8B8AE728", hash_generated_method = "3F8B9733D7B400C64AB121DE2E2A9680")
    @Override
    public int getType() {
        int varFF41E4AA38F342E40E15A84F44950049_171213824 = (Handshake.CERTIFICATE_VERIFY);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_33419056 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_33419056;
        // ---------- Original Method ----------
        //return Handshake.CERTIFICATE_VERIFY;
    }

    
}

