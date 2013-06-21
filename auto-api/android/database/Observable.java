package android.database;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.util.ArrayList;

public abstract class Observable<T> {
    protected ArrayList<T> mObservers = new ArrayList<T>();
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:46.208 -0400", hash_original_method = "5E773864FBF02F62D708D93F9DBCDF62", hash_generated_method = "5E773864FBF02F62D708D93F9DBCDF62")
        public Observable ()
    {
    }


    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:46.208 -0400", hash_original_method = "E1D85670162EF30644779B34B74FE79F", hash_generated_method = "60DF4334AB32F1FD21CD8CB763935615")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void registerObserver(T observer) {
        //DSFIXME: CODE0010: Possible callback registration function detected
        dsTaint.addTaint(observer.dsTaint);
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("The observer is null.");
        } //End block
        {
            {
                boolean varB1C365CA1474DBF7A76FDBEB30F97622_86801711 = (mObservers.contains(observer));
                {
                    if (DroidSafeAndroidRuntime.control) throw new IllegalStateException("Observer " + observer + " is already registered.");
                } //End block
            } //End collapsed parenthetic
            mObservers.add(observer);
        } //End block
        // ---------- Original Method ----------
        //if (observer == null) {
            //throw new IllegalArgumentException("The observer is null.");
        //}
        //synchronized(mObservers) {
            //if (mObservers.contains(observer)) {
                //throw new IllegalStateException("Observer " + observer + " is already registered.");
            //}
            //mObservers.add(observer);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:46.209 -0400", hash_original_method = "5FAF0F37EA51171D6350539680C2708B", hash_generated_method = "8DFFD96CD2E81A77244F6D5C81A77C67")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void unregisterObserver(T observer) {
        dsTaint.addTaint(observer.dsTaint);
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("The observer is null.");
        } //End block
        {
            int index;
            index = mObservers.indexOf(observer);
            {
                if (DroidSafeAndroidRuntime.control) throw new IllegalStateException("Observer " + observer + " was not registered.");
            } //End block
            mObservers.remove(index);
        } //End block
        // ---------- Original Method ----------
        //if (observer == null) {
            //throw new IllegalArgumentException("The observer is null.");
        //}
        //synchronized(mObservers) {
            //int index = mObservers.indexOf(observer);
            //if (index == -1) {
                //throw new IllegalStateException("Observer " + observer + " was not registered.");
            //}
            //mObservers.remove(index);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:39:46.209 -0400", hash_original_method = "68DB825828DE5D87C3617DBC5932D86C", hash_generated_method = "15DCF18EAD87632038A03531805C5E0F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void unregisterAll() {
        {
            mObservers.clear();
        } //End block
        // ---------- Original Method ----------
        //synchronized(mObservers) {
            //mObservers.clear();
        //}
    }

    
}

