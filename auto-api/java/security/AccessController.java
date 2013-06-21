package java.security;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;

public final class AccessController {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:29.292 -0400", hash_original_method = "933F644DAAB02DBBD976309C952EDFF1", hash_generated_method = "413E2414F33FF227172B34BB7BB4830A")
    @DSModeled(DSC.SAFE)
    private AccessController() {
        // ---------- Original Method ----------
    }

    
        public static <T> T doPrivileged(PrivilegedAction<T> action) {
        return action.run();
    }

    
        public static <T> T doPrivileged(PrivilegedAction<T> action, AccessControlContext context) {
        return action.run();
    }

    
        public static <T> T doPrivileged(PrivilegedExceptionAction<T> action) throws PrivilegedActionException {
        try {
            return action.run();
        } catch (RuntimeException e) {
            throw e; 
        } catch (Exception e) {
            throw new PrivilegedActionException(e);
        }
    }

    
        public static <T> T doPrivileged(PrivilegedExceptionAction<T> action, AccessControlContext context) throws PrivilegedActionException {
        return doPrivileged(action);
    }

    
        public static <T> T doPrivilegedWithCombiner(PrivilegedAction<T> action) {
        return action.run();
    }

    
        public static <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> action) throws PrivilegedActionException {
        return doPrivileged(action);
    }

    
        public static void checkPermission(Permission permission) throws AccessControlException {
    }

    
        public static AccessControlContext getContext() {
        return new AccessControlContext(null);
    }

    
}

