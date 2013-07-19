package org.bouncycastle.jce;

// Droidsafe Imports
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Strings;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class PKCS10CertificationRequest extends CertificationRequest {
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.249 -0400", hash_original_method = "3A41672F51C7647865E5A6F21420932E", hash_generated_method = "FF24829872F3AA31786B4DCB8CC254E0")
    public  PKCS10CertificationRequest(
        byte[]  bytes) {
        super(toDERSequence(bytes));
        addTaint(bytes[0]);
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.249 -0400", hash_original_method = "E80E9113023CB84AD6D915B7A5F597A7", hash_generated_method = "4ABCE1760383597C1301750287ADF3F7")
    public  PKCS10CertificationRequest(
        ASN1Sequence  sequence) {
        super(sequence);
        addTaint(sequence.getTaint());
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.250 -0400", hash_original_method = "DCC234DC4AA32AD53B9F45C5CD92E5AA", hash_generated_method = "D6258080ABBCD93337111577476C7AD9")
    public  PKCS10CertificationRequest(
        String              signatureAlgorithm,
        X509Name            subject,
        PublicKey           key,
        ASN1Set             attributes,
        PrivateKey          signingKey) throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException, SignatureException {
        this(signatureAlgorithm, subject, key, attributes, signingKey, BouncyCastleProvider.PROVIDER_NAME);
        addTaint(signingKey.getTaint());
        addTaint(attributes.getTaint());
        addTaint(key.getTaint());
        addTaint(subject.getTaint());
        addTaint(signatureAlgorithm.getTaint());
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.250 -0400", hash_original_method = "8331D41600BE6136ABFAAFB9EC7881F5", hash_generated_method = "D6D6AE127A2EA6FF1A02E4C3D7F97044")
    public  PKCS10CertificationRequest(
        String              signatureAlgorithm,
        X500Principal       subject,
        PublicKey           key,
        ASN1Set             attributes,
        PrivateKey          signingKey) throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException, SignatureException {
        this(signatureAlgorithm, convertName(subject), key, attributes, signingKey, BouncyCastleProvider.PROVIDER_NAME);
        addTaint(signingKey.getTaint());
        addTaint(attributes.getTaint());
        addTaint(key.getTaint());
        addTaint(subject.getTaint());
        addTaint(signatureAlgorithm.getTaint());
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.251 -0400", hash_original_method = "47F27C3C5B072C1921CEA8CD2618865D", hash_generated_method = "6FDB8329B6328664E5F47F239FB24DF0")
    public  PKCS10CertificationRequest(
        String              signatureAlgorithm,
        X500Principal       subject,
        PublicKey           key,
        ASN1Set             attributes,
        PrivateKey          signingKey,
        String              provider) throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException, SignatureException {
        this(signatureAlgorithm, convertName(subject), key, attributes, signingKey, provider);
        addTaint(provider.getTaint());
        addTaint(signingKey.getTaint());
        addTaint(attributes.getTaint());
        addTaint(key.getTaint());
        addTaint(subject.getTaint());
        addTaint(signatureAlgorithm.getTaint());
        // ---------- Original Method ----------
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.253 -0400", hash_original_method = "9B8C4723941705C4AF87CD12244595BD", hash_generated_method = "74C86B98FE1BABF9D67598B0C39B0570")
    public  PKCS10CertificationRequest(
        String              signatureAlgorithm,
        X509Name            subject,
        PublicKey           key,
        ASN1Set             attributes,
        PrivateKey          signingKey,
        String              provider) throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException, SignatureException {
        addTaint(provider.getTaint());
        addTaint(signingKey.getTaint());
        addTaint(attributes.getTaint());
        addTaint(key.getTaint());
        addTaint(subject.getTaint());
        addTaint(signatureAlgorithm.getTaint());
        String algorithmName = Strings.toUpperCase(signatureAlgorithm);
        DERObjectIdentifier sigOID = (DERObjectIdentifier)algorithms.get(algorithmName);
        if(sigOID == null)        
        {
            try 
            {
                sigOID = new DERObjectIdentifier(algorithmName);
            } //End block
            catch (Exception e)
            {
                IllegalArgumentException varB18074A21B8B08497C53127CA4DD90CB_769871989 = new IllegalArgumentException("Unknown signature type requested");
                varB18074A21B8B08497C53127CA4DD90CB_769871989.addTaint(taint);
                throw varB18074A21B8B08497C53127CA4DD90CB_769871989;
            } //End block
        } //End block
        if(subject == null)        
        {
            IllegalArgumentException varF7E6557D5CD0EEE16A97877333591AF9_2131166133 = new IllegalArgumentException("subject must not be null");
            varF7E6557D5CD0EEE16A97877333591AF9_2131166133.addTaint(taint);
            throw varF7E6557D5CD0EEE16A97877333591AF9_2131166133;
        } //End block
        if(key == null)        
        {
            IllegalArgumentException var7FA406A36111F20A2D59C5557337A99E_969321336 = new IllegalArgumentException("public key must not be null");
            var7FA406A36111F20A2D59C5557337A99E_969321336.addTaint(taint);
            throw var7FA406A36111F20A2D59C5557337A99E_969321336;
        } //End block
        if(noParams.contains(sigOID))        
        {
            this.sigAlgId = new AlgorithmIdentifier(sigOID);
        } //End block
        else
        if(params.containsKey(algorithmName))        
        {
            this.sigAlgId = new AlgorithmIdentifier(sigOID, (DEREncodable)params.get(algorithmName));
        } //End block
        else
        {
            this.sigAlgId = new AlgorithmIdentifier(sigOID, DERNull.INSTANCE);
        } //End block
        try 
        {
            ASN1Sequence seq = (ASN1Sequence)ASN1Object.fromByteArray(key.getEncoded());
            this.reqInfo = new CertificationRequestInfo(subject, new SubjectPublicKeyInfo(seq), attributes);
        } //End block
        catch (IOException e)
        {
            IllegalArgumentException var19AFF07EBF0D285E6200B15847475545_1866682171 = new IllegalArgumentException("can't encode public key");
            var19AFF07EBF0D285E6200B15847475545_1866682171.addTaint(taint);
            throw var19AFF07EBF0D285E6200B15847475545_1866682171;
        } //End block
        Signature sig;
        if(provider == null)        
        {
            sig = Signature.getInstance(signatureAlgorithm);
        } //End block
        else
        {
            sig = Signature.getInstance(signatureAlgorithm, provider);
        } //End block
        sig.initSign(signingKey);
        try 
        {
            sig.update(reqInfo.getEncoded(ASN1Encodable.DER));
        } //End block
        catch (Exception e)
        {
            IllegalArgumentException var28DBE1BCED21F9B7B371B30A4EA3167D_1827584261 = new IllegalArgumentException("exception encoding TBS cert request - " + e);
            var28DBE1BCED21F9B7B371B30A4EA3167D_1827584261.addTaint(taint);
            throw var28DBE1BCED21F9B7B371B30A4EA3167D_1827584261;
        } //End block
        this.sigBits = new DERBitString(sig.sign());
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSModeled(DSC.SAFE)
    private static RSASSAPSSparams creatPSSParams(AlgorithmIdentifier hashAlgId, int saltSize) {
        return new RSASSAPSSparams(
            hashAlgId,
            new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, hashAlgId),
            new DERInteger(saltSize),
            new DERInteger(1));
    }

    
    @DSModeled(DSC.SAFE)
    private static ASN1Sequence toDERSequence(
        byte[]  bytes) {
        try
        {
            ASN1InputStream         dIn = new ASN1InputStream(bytes);
            return (ASN1Sequence)dIn.readObject();
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("badly encoded request");
        }
    }

    
    @DSModeled(DSC.SAFE)
    private static X509Name convertName(
        X500Principal    name) {
        try
        {
            return new X509Principal(name.getEncoded());
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("can't convert name");
        }
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.255 -0400", hash_original_method = "43324B511CA49EBF5AC84C5892E16660", hash_generated_method = "35DBBE56CC6D0DFD5865FE4851463AF4")
    public PublicKey getPublicKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
PublicKey varC3D0D1C56178EDDD777C8C53EF7F0CE9_103346534 =         getPublicKey(BouncyCastleProvider.PROVIDER_NAME);
        varC3D0D1C56178EDDD777C8C53EF7F0CE9_103346534.addTaint(taint);
        return varC3D0D1C56178EDDD777C8C53EF7F0CE9_103346534;
        // ---------- Original Method ----------
        //return getPublicKey(BouncyCastleProvider.PROVIDER_NAME);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.256 -0400", hash_original_method = "B8875DFEFA5CEF760F6C887EECDEC741", hash_generated_method = "ADC68574CB07494A2E0419883103099A")
    public PublicKey getPublicKey(
        String  provider) throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException {
        addTaint(provider.getTaint());
        SubjectPublicKeyInfo subjectPKInfo = reqInfo.getSubjectPublicKeyInfo();
        X509EncodedKeySpec xspec = new X509EncodedKeySpec(new DERBitString(subjectPKInfo).getBytes());
        AlgorithmIdentifier keyAlg = subjectPKInfo.getAlgorithmId();
        try 
        {
            try 
            {
                if(provider == null)                
                {
PublicKey var41DC99F01DD21ADDF0ABED61A14977D8_799186848 =                     KeyFactory.getInstance(keyAlg.getObjectId().getId()).generatePublic(xspec);
                    var41DC99F01DD21ADDF0ABED61A14977D8_799186848.addTaint(taint);
                    return var41DC99F01DD21ADDF0ABED61A14977D8_799186848;
                } //End block
                else
                {
PublicKey varEBB5C620BE73D7C58524D5E2E4FDA5D2_692558561 =                     KeyFactory.getInstance(keyAlg.getObjectId().getId(), provider).generatePublic(xspec);
                    varEBB5C620BE73D7C58524D5E2E4FDA5D2_692558561.addTaint(taint);
                    return varEBB5C620BE73D7C58524D5E2E4FDA5D2_692558561;
                } //End block
            } //End block
            catch (NoSuchAlgorithmException e)
            {
                if(keyAlgorithms.get(keyAlg.getObjectId()) != null)                
                {
                    String keyAlgorithm = (String)keyAlgorithms.get(keyAlg.getObjectId());
                    if(provider == null)                    
                    {
PublicKey var819053A5996B66C1588BC8CB811D91A6_924516791 =                         KeyFactory.getInstance(keyAlgorithm).generatePublic(xspec);
                        var819053A5996B66C1588BC8CB811D91A6_924516791.addTaint(taint);
                        return var819053A5996B66C1588BC8CB811D91A6_924516791;
                    } //End block
                    else
                    {
PublicKey var1984EAB4E1C296EE1F73B3B0EE5AB623_2100554869 =                         KeyFactory.getInstance(keyAlgorithm, provider).generatePublic(xspec);
                        var1984EAB4E1C296EE1F73B3B0EE5AB623_2100554869.addTaint(taint);
                        return var1984EAB4E1C296EE1F73B3B0EE5AB623_2100554869;
                    } //End block
                } //End block
                e.addTaint(taint);
                throw e;
            } //End block
        } //End block
        catch (InvalidKeySpecException e)
        {
            InvalidKeyException varB47E630CF91EC6A257E24F063B634927_635991839 = new InvalidKeyException("error decoding public key");
            varB47E630CF91EC6A257E24F063B634927_635991839.addTaint(taint);
            throw varB47E630CF91EC6A257E24F063B634927_635991839;
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.256 -0400", hash_original_method = "76B4A35CDD087589FE1D86D8EB63F14C", hash_generated_method = "E71B53D0E06CCB66C4C0E0856F141A0D")
    public boolean verify() throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException, SignatureException {
        boolean varDFDA2B739D43284084054634F274B202_855472976 = (verify(BouncyCastleProvider.PROVIDER_NAME));
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1091076498 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1091076498;
        // ---------- Original Method ----------
        //return verify(BouncyCastleProvider.PROVIDER_NAME);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.257 -0400", hash_original_method = "EEEA1D6F83C84A4A1EBDD2FD7850BFFE", hash_generated_method = "27339F45B910255DDC2F521BF4EAE835")
    public boolean verify(
        String provider) throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException, SignatureException {
        addTaint(provider.getTaint());
        boolean var870A8DDE759B8F2968C7A4D4A7FE5FFC_1053145651 = (verify(this.getPublicKey(provider), provider));
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1633320468 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1633320468;
        // ---------- Original Method ----------
        //return verify(this.getPublicKey(provider), provider);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.258 -0400", hash_original_method = "23EDAE0C7E13C4B3F88052C2DB8B64F6", hash_generated_method = "8320E5446476AA6523E2561488742C80")
    public boolean verify(
        PublicKey pubKey,
        String provider) throws NoSuchAlgorithmException, NoSuchProviderException,
                InvalidKeyException, SignatureException {
        addTaint(provider.getTaint());
        addTaint(pubKey.getTaint());
        Signature sig;
        try 
        {
            if(provider == null)            
            {
                sig = Signature.getInstance(getSignatureName(sigAlgId));
            } //End block
            else
            {
                sig = Signature.getInstance(getSignatureName(sigAlgId), provider);
            } //End block
        } //End block
        catch (NoSuchAlgorithmException e)
        {
            if(oids.get(sigAlgId.getObjectId()) != null)            
            {
                String signatureAlgorithm = (String)oids.get(sigAlgId.getObjectId());
                if(provider == null)                
                {
                    sig = Signature.getInstance(signatureAlgorithm);
                } //End block
                else
                {
                    sig = Signature.getInstance(signatureAlgorithm, provider);
                } //End block
            } //End block
            else
            {
                e.addTaint(taint);
                throw e;
            } //End block
        } //End block
        setSignatureParameters(sig, sigAlgId.getParameters());
        sig.initVerify(pubKey);
        try 
        {
            sig.update(reqInfo.getEncoded(ASN1Encodable.DER));
        } //End block
        catch (Exception e)
        {
            SignatureException var50132ED7A0D04A650E1E6822352B6B4F_2022522685 = new SignatureException("exception encoding TBS cert request - " + e);
            var50132ED7A0D04A650E1E6822352B6B4F_2022522685.addTaint(taint);
            throw var50132ED7A0D04A650E1E6822352B6B4F_2022522685;
        } //End block
        boolean var55FC8C227B44C450556E137AA201B486_963644722 = (sig.verify(sigBits.getBytes()));
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_996900828 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_996900828;
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.258 -0400", hash_original_method = "FFCAA10D74329AA15B8FE83335744F82", hash_generated_method = "8BD4939D28737F166DA1C5DEEBDFEBAF")
    public byte[] getEncoded() {
        try 
        {
            byte[] var75679B9D6E0C3BD65FFE938E240A5B3B_10092271 = (this.getEncoded(ASN1Encodable.DER));
                        byte[] var2F9C81BC6E497382285CD6B7A7E33DE1_1975824700 = {getTaintByte()};
            return var2F9C81BC6E497382285CD6B7A7E33DE1_1975824700;
        } //End block
        catch (IOException e)
        {
            RuntimeException var7731B7FAF9DB516E410EE38D728795B2_400152887 = new RuntimeException(e.toString());
            var7731B7FAF9DB516E410EE38D728795B2_400152887.addTaint(taint);
            throw var7731B7FAF9DB516E410EE38D728795B2_400152887;
        } //End block
        // ---------- Original Method ----------
        //try
        //{
            //return this.getEncoded(ASN1Encodable.DER);
        //}
        //catch (IOException e)
        //{
            //throw new RuntimeException(e.toString());
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.259 -0400", hash_original_method = "572BCE240DBAD7E2FCA6B5178E7F9E48", hash_generated_method = "ECA18CCBBB9FF4A7FC3B8B40F1D1D462")
    private void setSignatureParameters(
        Signature signature,
        DEREncodable params) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        addTaint(params.getTaint());
        addTaint(signature.getTaint());
        if(params != null && !DERNull.INSTANCE.equals(params))        
        {
            AlgorithmParameters sigParams = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
            try 
            {
                sigParams.init(params.getDERObject().getDEREncoded());
            } //End block
            catch (IOException e)
            {
                SignatureException var4C6E34B998119F08550B28F7354D74CB_1275589053 = new SignatureException("IOException decoding parameters: " + e.getMessage());
                var4C6E34B998119F08550B28F7354D74CB_1275589053.addTaint(taint);
                throw var4C6E34B998119F08550B28F7354D74CB_1275589053;
            } //End block
            if(signature.getAlgorithm().endsWith("MGF1"))            
            {
                try 
                {
                    signature.setParameter(sigParams.getParameterSpec(PSSParameterSpec.class));
                } //End block
                catch (GeneralSecurityException e)
                {
                    SignatureException var1A8C4267253B70F3D11993580B705F98_237323191 = new SignatureException("Exception extracting parameters: " + e.getMessage());
                    var1A8C4267253B70F3D11993580B705F98_237323191.addTaint(taint);
                    throw var1A8C4267253B70F3D11993580B705F98_237323191;
                } //End block
            } //End block
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSModeled(DSC.SAFE)
    static String getSignatureName(
        AlgorithmIdentifier sigAlgId) {
        DEREncodable params = sigAlgId.getParameters();
        if (params != null && !DERNull.INSTANCE.equals(params))
        {
            if (sigAlgId.getObjectId().equals(PKCSObjectIdentifiers.id_RSASSA_PSS))
            {
                RSASSAPSSparams rsaParams = RSASSAPSSparams.getInstance(params);
                return getDigestAlgName(rsaParams.getHashAlgorithm().getObjectId()) + "withRSAandMGF1";
            }
        }
        return sigAlgId.getObjectId().getId();
    }

    
    private static String getDigestAlgName(
        DERObjectIdentifier digestAlgOID) {
        if (PKCSObjectIdentifiers.md5.equals(digestAlgOID))
        {
            return "MD5";
        }
        else if (OIWObjectIdentifiers.idSHA1.equals(digestAlgOID))
        {
            return "SHA1";
        }
        else if (NISTObjectIdentifiers.id_sha256.equals(digestAlgOID))
        {
            return "SHA256";
        }
        else if (NISTObjectIdentifiers.id_sha384.equals(digestAlgOID))
        {
            return "SHA384";
        }
        else if (NISTObjectIdentifiers.id_sha512.equals(digestAlgOID))
        {
            return "SHA512";
        }
        else
        {
            return digestAlgOID.getId();            
        }
    }

    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.260 -0400", hash_original_field = "B39C3BE7546FA73A752B6C68B346E2B1", hash_generated_field = "2A7E0957EAD856B3315504481686A7DE")

    private static Hashtable algorithms = new Hashtable();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.260 -0400", hash_original_field = "0D433ED6AA05239AA7FFA603AE52EAF9", hash_generated_field = "3580C2237188FBF88703AFC814D06B98")

    private static Hashtable params = new Hashtable();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.260 -0400", hash_original_field = "A13D3CE5CEDBFFEA1E23C07F3E99C13F", hash_generated_field = "C8FBF8B644E3B7297D2EC131F85F7B39")

    private static Hashtable keyAlgorithms = new Hashtable();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.260 -0400", hash_original_field = "E4322C1573762E02D6AC3A68C442E016", hash_generated_field = "6D958D15EBDB3005FA7CB66D7A014B22")

    private static Hashtable oids = new Hashtable();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.260 -0400", hash_original_field = "9599D5E70EEA113DAC00F7EA2B8AD147", hash_generated_field = "E222E546E7C245483C60E7B45F19B3A8")

    private static Set noParams = new HashSet();
    static {
        algorithms.put("MD5WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("MD5WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("RSAWITHMD5", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("SHA1WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA1WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA256WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA256WITHRSA", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA384WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA384WITHRSA", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA512WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA512WITHRSA", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA1WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA256WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA384WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA512WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("RSAWITHSHA1", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA1WITHDSA", new DERObjectIdentifier("1.2.840.10040.4.3"));
        algorithms.put("DSAWITHSHA1", new DERObjectIdentifier("1.2.840.10040.4.3"));
        algorithms.put("SHA256WITHDSA", NISTObjectIdentifiers.dsa_with_sha256);
        algorithms.put("SHA384WITHDSA", NISTObjectIdentifiers.dsa_with_sha384);
        algorithms.put("SHA512WITHDSA", NISTObjectIdentifiers.dsa_with_sha512);
        algorithms.put("SHA1WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("SHA256WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA256);
        algorithms.put("SHA384WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA384);
        algorithms.put("SHA512WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA512);
        algorithms.put("ECDSAWITHSHA1", X9ObjectIdentifiers.ecdsa_with_SHA1);
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        oids.put(new DERObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        oids.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        oids.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
        keyAlgorithms.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        keyAlgorithms.put(X9ObjectIdentifiers.id_dsa, "DSA");
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA1);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA256);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA384);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA512);
        noParams.add(X9ObjectIdentifiers.id_dsa_with_sha1);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha256);
        AlgorithmIdentifier sha1AlgId = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        params.put("SHA1WITHRSAANDMGF1", creatPSSParams(sha1AlgId, 20));
        AlgorithmIdentifier sha256AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        params.put("SHA256WITHRSAANDMGF1", creatPSSParams(sha256AlgId, 32));
        AlgorithmIdentifier sha384AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE);
        params.put("SHA384WITHRSAANDMGF1", creatPSSParams(sha384AlgId, 48));
        AlgorithmIdentifier sha512AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE);
        params.put("SHA512WITHRSAANDMGF1", creatPSSParams(sha512AlgId, 64));
    }
    
}

