package android.database;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class DataSetObservable extends Observable<DataSetObserver> {

	@DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    public DataSetObservable() {
		super();
		//Does not exist in original model
	}
    /**
     * Invokes onChanged on each observer. Called when the data set being observed has
     * changed, and which when read contains the new state of the data.
     */
    @DSComment("callback will be invoked")
    @DSSpec(DSCat.CALLBACK_INVOKE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.895 -0500", hash_original_method = "FCDAC37DCBE00C8E72E2289D698E0B3F", hash_generated_method = "BB4CEAAC9B3708D7274C54F7C0920423")
    
public void notifyChanged() {
        synchronized(mObservers) {
            // since onChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
    }

    /**
     * Invokes onInvalidated on each observer. Called when the data set being monitored
     * has changed such that it is no longer valid.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.897 -0500", hash_original_method = "C67632C7194D1964FA22740DFB1AA62E", hash_generated_method = "0E4653112D6DFC40DF909F8C8F059089")
    
public void notifyInvalidated() {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onInvalidated();
            }
        }
    }

    @DSVerified
    @DSSafe(DSCat.ANDROID_CALLBACK)
    // orphaned legacy method
    @Override
	public void registerObserver(DataSetObserver observer) {
        observer.onChanged();
        observer.onInvalidated();
        mObservers.add(0, observer);
    }
    
}
