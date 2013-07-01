package org.apache.harmony.security.x509;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BitString;
import org.apache.harmony.security.utils.Array;

public final class CertificateList {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.729 -0400", hash_original_field = "434A8DAF2056B5C3BDA6E59178AA316C", hash_generated_field = "77B3A284D6DF0EF4A47E1206333ED0FE")

    private TBSCertList tbsCertList;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.729 -0400", hash_original_field = "0C3D482A41621822B95C4386FD1F1FC2", hash_generated_field = "4CC78CEDF5C854EC0CED71E47BBFC813")

    private AlgorithmIdentifier signatureAlgorithm;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.729 -0400", hash_original_field = "A83E026224A6F552557EF9C7ABF67D37", hash_generated_field = "9AB01BF01A2AD28FB638C6788268D049")

    private byte[] signatureValue;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.729 -0400", hash_original_field = "84BEA1F0FD2CE16F7E562A9F06EF03D3", hash_generated_field = "ACB189C73E1A6432570001B3B9D3D516")

    private byte[] encoding;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.729 -0400", hash_original_method = "08126E4FD8546D7C66517E47FC438E4A", hash_generated_method = "83B6B6743FC51D8D2C6FB4B4C0C94E98")
    public  CertificateList(TBSCertList tbsCertList,
                       AlgorithmIdentifier signatureAlgorithm,
                       byte[] signatureValue) {
        this.tbsCertList = tbsCertList;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signatureValue = new byte[signatureValue.length];
        System.arraycopy(signatureValue, 0, this.signatureValue, 0,
                                                    signatureValue.length);
        // ---------- Original Method ----------
        //this.tbsCertList = tbsCertList;
        //this.signatureAlgorithm = signatureAlgorithm;
        //this.signatureValue = new byte[signatureValue.length];
        //System.arraycopy(signatureValue, 0, this.signatureValue, 0,
                                                    //signatureValue.length);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.729 -0400", hash_original_method = "296A8B306ED3B280093A8F830EEE1E41", hash_generated_method = "63416AF811C58DCF69F43E3F90E57D0F")
    private  CertificateList(TBSCertList tbsCertList,
                       AlgorithmIdentifier signatureAlgorithm,
                       byte[] signatureValue, byte[] encoding) {
        this(tbsCertList, signatureAlgorithm, signatureValue);
        this.encoding = encoding;
        addTaint(tbsCertList.getTaint());
        addTaint(signatureAlgorithm.getTaint());
        addTaint(signatureValue[0]);
        // ---------- Original Method ----------
        //this.encoding = encoding;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.730 -0400", hash_original_method = "C6096C9759CF7088E874A2A2D3801E56", hash_generated_method = "7486B4D364A36E5C3259A87535EB39B7")
    public TBSCertList getTbsCertList() {
        TBSCertList varB4EAC82CA7396A68D541C85D26508E83_451097700 = null; //Variable for return #1
        varB4EAC82CA7396A68D541C85D26508E83_451097700 = tbsCertList;
        varB4EAC82CA7396A68D541C85D26508E83_451097700.addTaint(getTaint()); //Add taint from parent
        return varB4EAC82CA7396A68D541C85D26508E83_451097700;
        // ---------- Original Method ----------
        //return tbsCertList;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.730 -0400", hash_original_method = "FD6792B7396143D84E51253527677292", hash_generated_method = "CE5DD0E55D4AFA2C748D9DA2028B9C70")
    public byte[] getSignatureValue() {
        byte[] result = new byte[signatureValue.length];
        System.arraycopy(signatureValue, 0, result, 0, signatureValue.length);
        byte[] var2F9C81BC6E497382285CD6B7A7E33DE1_386546161 = {getTaintByte()};
        return var2F9C81BC6E497382285CD6B7A7E33DE1_386546161;
        // ---------- Original Method ----------
        //byte[] result = new byte[signatureValue.length];
        //System.arraycopy(signatureValue, 0, result, 0, signatureValue.length);
        //return result;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.730 -0400", hash_original_method = "80C69E334C5CE31111385C23EECA9331", hash_generated_method = "CBEBF90756DA93D626B9D3F5B0E3AAD0")
    @Override
    public String toString() {
        String varB4EAC82CA7396A68D541C85D26508E83_1681596176 = null; //Variable for return #1
        StringBuilder result = new StringBuilder();
        tbsCertList.dumpValue(result);
        result.append("\nSignature Value:\n");
        result.append(Array.toString(signatureValue, ""));
        varB4EAC82CA7396A68D541C85D26508E83_1681596176 = result.toString();
        varB4EAC82CA7396A68D541C85D26508E83_1681596176.addTaint(getTaint()); //Add taint from parent
        return varB4EAC82CA7396A68D541C85D26508E83_1681596176;
        // ---------- Original Method ----------
        //StringBuilder result = new StringBuilder();
        //tbsCertList.dumpValue(result);
        //result.append("\nSignature Value:\n");
        //result.append(Array.toString(signatureValue, ""));
        //return result.toString();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.731 -0400", hash_original_method = "32DCE62651649D85CD3AB4D018999455", hash_generated_method = "324C85D226E914CA212C7D78C51FCFD1")
    public byte[] getEncoded() {
        {
            encoding = CertificateList.ASN1.encode(this);
        } //End block
        byte[] var2F9C81BC6E497382285CD6B7A7E33DE1_2077508312 = {getTaintByte()};
        return var2F9C81BC6E497382285CD6B7A7E33DE1_2077508312;
        // ---------- Original Method ----------
        //if (encoding == null) {
            //encoding = CertificateList.ASN1.encode(this);
        //}
        //return encoding;
    }

    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:34.731 -0400", hash_original_field = "736366582672C5EC0590C01C14D1FD1B", hash_generated_field = "CEEF377160D4527069F2AE9EE6F61908")

    public static final ASN1Sequence ASN1 =
        new ASN1Sequence(new ASN1Type[]
                {TBSCertList.ASN1, AlgorithmIdentifier.ASN1,
                    ASN1BitString.getInstance()}) {

        @Override protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new CertificateList(
                    (TBSCertList) values[0],
                    (AlgorithmIdentifier) values[1],
                    ((BitString) values[2]).bytes, 
                    in.getEncoded()
                    );
        }

        @Override protected void getValues(Object object, Object[] values) {
            CertificateList certificateList = (CertificateList) object;
            values[0] = certificateList.tbsCertList;
            values[1] = certificateList.signatureAlgorithm;
            values[2] = new BitString(certificateList.signatureValue, 0);
        }
    };
    /*
    // orphaned legacy method
    @Override protected void getValues(Object object, Object[] values) {
            CertificateList certificateList = (CertificateList) object;
            values[0] = certificateList.tbsCertList;
            values[1] = certificateList.signatureAlgorithm;
            values[2] = new BitString(certificateList.signatureValue, 0);
        }
    
    // orphaned legacy method
    @Override protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new CertificateList(
                    (TBSCertList) values[0],
                    (AlgorithmIdentifier) values[1],
                    ((BitString) values[2]).bytes, 
                    in.getEncoded()
                    );
        }
    
    */
}

