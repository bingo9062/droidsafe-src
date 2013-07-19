package gov.nist.javax.sip;

// Droidsafe Imports
import javax.sip.ServerTransaction;

public interface ServerTransactionExt extends ServerTransaction, TransactionExt {
    
    public ServerTransaction getCanceledInviteTransaction();
}
