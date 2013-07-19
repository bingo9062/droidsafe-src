package org.bouncycastle.asn1.x509;

// Droidsafe Imports
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class V2Form extends ASN1Encodable {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.987 -0400", hash_original_field = "FBB85711FD06EA9038A0499C78C0F229", hash_generated_field = "27D5E6F2AF34AE0B03A90D9446FD0637")

    GeneralNames issuerName;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.987 -0400", hash_original_field = "C3C2EC65CAD3747AA642E945B7F46D09", hash_generated_field = "BFE641AC9AE66327CCBC4859503216E4")

    IssuerSerial baseCertificateID;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.987 -0400", hash_original_field = "035CD4986BAFC5E11EA88A19D8C042C6", hash_generated_field = "0DFE757401959316759693C6B709966B")

    ObjectDigestInfo objectDigestInfo;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.987 -0400", hash_original_method = "58484080C9CD52E375DC7B3D30D61BD6", hash_generated_method = "F65B38D63267FE602DE1A3694A5C6E35")
    public  V2Form(
        GeneralNames    issuerName) {
        this.issuerName = issuerName;
        // ---------- Original Method ----------
        //this.issuerName = issuerName;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.988 -0400", hash_original_method = "A1182A2D0637CA133E8A6FD666F8BDBF", hash_generated_method = "1E6B2E7209864E218BC9063D7A5CE19B")
    public  V2Form(
        ASN1Sequence seq) {
        if(seq.size() > 3)        
        {
            IllegalArgumentException varA2072CF614C7B8C7696DD5A02DBCCE9C_997403160 = new IllegalArgumentException("Bad sequence size: " + seq.size());
            varA2072CF614C7B8C7696DD5A02DBCCE9C_997403160.addTaint(taint);
            throw varA2072CF614C7B8C7696DD5A02DBCCE9C_997403160;
        } //End block
        int index = 0;
        if(!(seq.getObjectAt(0) instanceof ASN1TaggedObject))        
        {
            index++;
            this.issuerName = GeneralNames.getInstance(seq.getObjectAt(0));
        } //End block
for(int i = index;i != seq.size();i++)
        {
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(seq.getObjectAt(i));
            if(o.getTagNo() == 0)            
            {
                baseCertificateID = IssuerSerial.getInstance(o, false);
            } //End block
            else
            if(o.getTagNo() == 1)            
            {
                objectDigestInfo = ObjectDigestInfo.getInstance(o, false);
            } //End block
            else
            {
                IllegalArgumentException varFD6FEEE1E1E2BFC14EB475E44DFD6000_291267796 = new IllegalArgumentException("Bad tag number: "
                        + o.getTagNo());
                varFD6FEEE1E1E2BFC14EB475E44DFD6000_291267796.addTaint(taint);
                throw varFD6FEEE1E1E2BFC14EB475E44DFD6000_291267796;
            } //End block
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    public static V2Form getInstance(
        ASN1TaggedObject obj,
        boolean          explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    
    public static V2Form getInstance(
        Object  obj) {
        if (obj == null || obj instanceof V2Form)
        {
            return (V2Form)obj;
        }
        else if (obj instanceof ASN1Sequence)
        {
            return new V2Form((ASN1Sequence)obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.990 -0400", hash_original_method = "55884F30CB8A32C82BBC3BF8E06EF566", hash_generated_method = "7D4B225C7E8281C7B3FA2627373F21D5")
    public GeneralNames getIssuerName() {
GeneralNames var45766B0DF0D0B85E6E779A59D6C70FDF_1304033239 =         issuerName;
        var45766B0DF0D0B85E6E779A59D6C70FDF_1304033239.addTaint(taint);
        return var45766B0DF0D0B85E6E779A59D6C70FDF_1304033239;
        // ---------- Original Method ----------
        //return issuerName;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.990 -0400", hash_original_method = "0056368CFDBAD05E126455DF61F0B2E7", hash_generated_method = "65C01F827C08AC68D53558F9D3E8930F")
    public IssuerSerial getBaseCertificateID() {
IssuerSerial var3F2CF66981EEEACD456AAF29D2BB1816_1144101541 =         baseCertificateID;
        var3F2CF66981EEEACD456AAF29D2BB1816_1144101541.addTaint(taint);
        return var3F2CF66981EEEACD456AAF29D2BB1816_1144101541;
        // ---------- Original Method ----------
        //return baseCertificateID;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.990 -0400", hash_original_method = "BF432F04B889EC14E618C49342356E7A", hash_generated_method = "C9805648FF01EA8004812A97A2742908")
    public ObjectDigestInfo getObjectDigestInfo() {
ObjectDigestInfo var8645E6823C16465BE8DE456BAC146566_138178326 =         objectDigestInfo;
        var8645E6823C16465BE8DE456BAC146566_138178326.addTaint(taint);
        return var8645E6823C16465BE8DE456BAC146566_138178326;
        // ---------- Original Method ----------
        //return objectDigestInfo;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:39.991 -0400", hash_original_method = "136E61B408FA241B2CCB57655BDD036B", hash_generated_method = "F7115EE3CE0329AD63ADCAFAFD6FCB76")
    public DERObject toASN1Object() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(issuerName != null)        
        {
            v.add(issuerName);
        } //End block
        if(baseCertificateID != null)        
        {
            v.add(new DERTaggedObject(false, 0, baseCertificateID));
        } //End block
        if(objectDigestInfo != null)        
        {
            v.add(new DERTaggedObject(false, 1, objectDigestInfo));
        } //End block
DERObject var0B338F106E3279986C87B595B0F4A439_1246917782 =         new DERSequence(v);
        var0B338F106E3279986C87B595B0F4A439_1246917782.addTaint(taint);
        return var0B338F106E3279986C87B595B0F4A439_1246917782;
        // ---------- Original Method ----------
        //ASN1EncodableVector  v = new ASN1EncodableVector();
        //if (issuerName != null)
        //{
            //v.add(issuerName);
        //}
        //if (baseCertificateID != null)
        //{
            //v.add(new DERTaggedObject(false, 0, baseCertificateID));
        //}
        //if (objectDigestInfo != null)
        //{
            //v.add(new DERTaggedObject(false, 1, objectDigestInfo));
        //}
        //return new DERSequence(v);
    }

    
}

