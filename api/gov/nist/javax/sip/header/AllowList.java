package gov.nist.javax.sip.header;

// Droidsafe Imports
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.sip.header.AllowHeader;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class AllowList extends SIPHeaderList<Allow> {
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:32.787 -0400", hash_original_method = "F4CA7E921A2CAA269087CDE3B4673EDF", hash_generated_method = "D9C4828F0D645AEECBD56D2F64C1FA84")
    public  AllowList() {
        super(Allow.class, AllowHeader.NAME);
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:32.787 -0400", hash_original_method = "0478A1539FEF5A12FA1EE4755C128735", hash_generated_method = "5ADAC8ABC6C3432D43710BE89379131D")
    public Object clone() {
        AllowList retval = new AllowList();
        retval.clonehlist(this.hlist);
Object varF9E19AD6135C970F387F77C6F3DE4477_1945215458 =         retval;
        varF9E19AD6135C970F387F77C6F3DE4477_1945215458.addTaint(taint);
        return varF9E19AD6135C970F387F77C6F3DE4477_1945215458;
        // ---------- Original Method ----------
        //AllowList retval = new AllowList();
        //retval.clonehlist(this.hlist);
        //return retval;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:32.788 -0400", hash_original_method = "AEA3F358A5E0CA1930C1FE1C96E2D485", hash_generated_method = "3FC0AAA2DEFC2F65012987053BFA4576")
    public ListIterator<String> getMethods() {
        LinkedList<String> ll = new LinkedList<String> ();
for(Iterator<Allow> it = this.hlist.iterator();it.hasNext();)
        {
            Allow a = (Allow)it.next();
            ll.add(a.getMethod());
        } //End block
ListIterator<String> var7235968E19DD3F2C4738A491078B8CA6_1799830549 =         ll.listIterator();
        var7235968E19DD3F2C4738A491078B8CA6_1799830549.addTaint(taint);
        return var7235968E19DD3F2C4738A491078B8CA6_1799830549;
        // ---------- Original Method ----------
        //LinkedList<String> ll = new LinkedList<String> ();
        //for ( Iterator<Allow> it = this.hlist.iterator(); it.hasNext();) {
            //Allow a = (Allow)it.next();
            //ll.add(a.getMethod());
        //}
        //return ll.listIterator();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:32.788 -0400", hash_original_method = "6420AFA8DFEC18654E9A726650C49FF0", hash_generated_method = "121A0AF6D88CE71F847A81186C2C2266")
    public void setMethods(List<String> methods) throws ParseException {
        addTaint(methods.getTaint());
        ListIterator<String> it = methods.listIterator();
        while
(it.hasNext())        
        {
            Allow allow = new Allow();
            allow.setMethod((String) it.next());
            this.add(allow);
        } //End block
        // ---------- Original Method ----------
        //ListIterator<String> it = methods.listIterator();
        //while (it.hasNext()) {
            //Allow allow = new Allow();
            //allow.setMethod((String) it.next());
            //this.add(allow);
        //}
    }

    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:32.788 -0400", hash_original_field = "677FE0780DF95025943ABD130FD2DC12", hash_generated_field = "8863FD75AF45B1DB104BC3DD11CC5A05")

    private static final long serialVersionUID = -4699795429662562358L;
}

