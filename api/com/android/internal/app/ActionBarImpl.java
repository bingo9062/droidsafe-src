package com.android.internal.app;

// Droidsafe Imports
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.SpinnerAdapter;

import com.android.internal.view.menu.MenuBuilder;
import com.android.internal.view.menu.MenuPopupHelper;
import com.android.internal.view.menu.SubMenuBuilder;
import com.android.internal.widget.ActionBarContainer;
import com.android.internal.widget.ActionBarContextView;
import com.android.internal.widget.ActionBarView;
import com.android.internal.widget.ScrollingTabContainerView;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class ActionBarImpl extends ActionBar {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "51EF5995AD6B82C50AE546C1599EFFFA", hash_generated_field = "C458E619396054F78BC926FB81B4386D")

    private Context mContext;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "F5C37C4F8245D2D147B5F954C98598CD", hash_generated_field = "A1CC5C58DF7C0DCCCCFFCA493E0A62F0")

    private Context mThemedContext;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "C145D87741EB407D6B1FF715AD484119", hash_generated_field = "B0D69375984D6EE7DC93AC12DB4DFC55")

    private Activity mActivity;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "58806F2D99FD4DF33CF42E0C232738D7", hash_generated_field = "D9FAD0C1386FC50E3C837906841C2A70")

    private Dialog mDialog;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "E9D2C5D306B865C46CCA3CA1F1920C28", hash_generated_field = "3B5102E8A4555D5377D5546AD36E30EF")

    private ActionBarContainer mContainerView;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "42ED82821F0D805CE8C635AE635639B9", hash_generated_field = "435589A395F4655E845140B48CC5DD82")

    private ActionBarView mActionView;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "13165738652608F9EE0CDB1A8EC90D22", hash_generated_field = "E4495F4DA753977DA3DA6F75D457D470")

    private ActionBarContextView mContextView;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "1D151831F4C7E78E301ECA2DDA3CAED0", hash_generated_field = "4B2A718FB491B6DDCF6D6F12DBDA30A4")

    private ActionBarContainer mSplitView;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "9E8FE4D07A56D54AEE159583A4429FE8", hash_generated_field = "9EE0242EB014EEED0CD7F44EEE7EA48F")

    private View mContentView;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.749 -0400", hash_original_field = "79971B4F2D39C7D687D7922DF67CCF83", hash_generated_field = "4E98814F423997944D2E7DAA5264BF48")

    private ScrollingTabContainerView mTabScrollView;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "E0236F65D62F98CFEACE8B9791BC6DFE", hash_generated_field = "2DE8BE827BC107C008436B0209722BF6")

    private ArrayList<TabImpl> mTabs = new ArrayList<TabImpl>();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "7AB2B74DF2A193B0C68E51A8508488A0", hash_generated_field = "E2A3DD52B8E79318754411772B5ABC7F")

    private TabImpl mSelectedTab;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "428613F2A4EC86480847E5DA42526736", hash_generated_field = "2822997A4E0CF16ED36F9B5F7F7F88DD")

    private int mSavedTabPosition = INVALID_POSITION;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "3B382D379664B509F22D0EB5D96F2BA8", hash_generated_field = "C88A4C5DD17B0498252830DDAB04C8E1")

    ActionModeImpl mActionMode;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "5180A3EB7DC7F36C82A2BD53BB6E22F5", hash_generated_field = "BEDD9228BA62217EC5EC4E12ED60D452")

    ActionMode mDeferredDestroyActionMode;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "9546024210C24CA70960554BD24D9005", hash_generated_field = "DF2EF7E0E1A5263A29433A4282D9458F")

    ActionMode.Callback mDeferredModeDestroyCallback;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "44A3A545246AFAA5F22D3E94D1DC0DDD", hash_generated_field = "E1595EB11EBE3E17FC85A0C8B6399279")

    private boolean mLastMenuVisibility;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "2C9F8D0C1FC6546CBA36AF4EFC26A494", hash_generated_field = "11BF511A20694B14557F30E21D03698F")

    private ArrayList<OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<OnMenuVisibilityListener>();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "CBEE79C0E03F33BC4AED6CFBC2EB379F", hash_generated_field = "C12FFA93FDEE59EC3B20F05029DA9108")

    private int mContextDisplayMode;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "4084EE71243F09CF6499828ED0C843C4", hash_generated_field = "11F23D5F4B9A4FEB27E5A49E7106FEF0")

    private boolean mHasEmbeddedTabs;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.750 -0400", hash_original_field = "6781893E9FA50A9B7103FD3F674E566B", hash_generated_field = "26DE2B72977ADBD047A9853341F26F79")

    final Handler mHandler = new Handler();
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.751 -0400", hash_original_field = "85B61EC738F2056493BB5A07F48F6CE2", hash_generated_field = "E682310941753B94BE1FECAC9286C6E9")

    Runnable mTabSelector;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.751 -0400", hash_original_field = "70690A02AD9E3C259E20A2FF977496E4", hash_generated_field = "A15ADDA5892591962A8310C80A51790F")

    private Animator mCurrentShowAnim;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.751 -0400", hash_original_field = "010D01D139412B6E9660288D10EE556E", hash_generated_field = "6CA587E5D3B3F2540E397F3FD2FB959C")

    private Animator mCurrentModeAnim;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.751 -0400", hash_original_field = "890E564069855FDC1EF47F290C02A110", hash_generated_field = "C93BEC8818A2F937EE0E6D7461D0E0FD")

    private boolean mShowHideAnimationEnabled;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.751 -0400", hash_original_field = "E8024699D94A8BEFD0D705A9675F8CED", hash_generated_field = "B2D34DAB28A6988D84FE0BB74C5C7E9B")

    boolean mWasHiddenBeforeMode;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:14:18.715 -0400", hash_original_field = "FCB094484C39025804ED780EC0133250", hash_generated_field = "5D15DFDE7BDCFD7555F6EB75013038EC")

    final AnimatorListener mHideListener = new AnimatorListenerAdapter() {        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:14:18.715 -0400", hash_original_method = "F963BB61325C11416F39E483670ED211", hash_generated_method = "B390DFB722C7C46D4BDE52128B843793")
        @Override
        public void onAnimationEnd(Animator animation) {
            
            {
                mContentView.setTranslationY(0);
                mContainerView.setTranslationY(0);
            } 
            {
                mSplitView.setVisibility(View.GONE);
            } 
            mContainerView.setVisibility(View.GONE);
            mContainerView.setTransitioning(false);
            mCurrentShowAnim = null;
            completeDeferredDestroyActionMode();
            addTaint(animation.getTaint());
            
            
                
                
            
            
                
            
            
            
            
            
        }

        
};
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:14:18.716 -0400", hash_original_field = "D5E7E0178A4C43DE90969C434EEC1CD9", hash_generated_field = "694FC00EDEB7498761672131BAC2ADA2")

    final AnimatorListener mShowListener = new AnimatorListenerAdapter() {        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:14:18.716 -0400", hash_original_method = "CA861A0A44A2878B5B0D20FC92104D63", hash_generated_method = "EF2053B78441E1DD818B82BE72053BC0")
        @Override
        public void onAnimationEnd(Animator animation) {
            
            mCurrentShowAnim = null;
            mContainerView.requestLayout();
            addTaint(animation.getTaint());
            
            
            
        }

        
};
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.752 -0400", hash_original_method = "59F98BFDF3B79CD734A4E0F1D5C36E49", hash_generated_method = "9528A6BA30B9DF359C9B94DCBB572096")
    public  ActionBarImpl(Activity activity) {
        mActivity = activity;
        Window window = activity.getWindow();
        View decor = window.getDecorView();
        init(decor);
        if(!mActivity.getWindow().hasFeature(Window.FEATURE_ACTION_BAR_OVERLAY))        
        {
            mContentView = decor.findViewById(android.R.id.content);
        } //End block
        // ---------- Original Method ----------
        //mActivity = activity;
        //Window window = activity.getWindow();
        //View decor = window.getDecorView();
        //init(decor);
        //if (!mActivity.getWindow().hasFeature(Window.FEATURE_ACTION_BAR_OVERLAY)) {
            //mContentView = decor.findViewById(android.R.id.content);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.753 -0400", hash_original_method = "8749FCFE1A7B0A0EC82F09729A7EA1FF", hash_generated_method = "ABD82B745CE6FBD975A8B09752DAC6AC")
    public  ActionBarImpl(Dialog dialog) {
        mDialog = dialog;
        init(dialog.getWindow().getDecorView());
        // ---------- Original Method ----------
        //mDialog = dialog;
        //init(dialog.getWindow().getDecorView());
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.755 -0400", hash_original_method = "CD1B24EA492A78A5E57A77E5FDC89AEF", hash_generated_method = "E7F6F843474A01A095D7352737E84D2A")
    private void init(View decor) {
        mContext = decor.getContext();
        mActionView = (ActionBarView) decor.findViewById(com.android.internal.R.id.action_bar);
        mContextView = (ActionBarContextView) decor.findViewById(
                com.android.internal.R.id.action_context_bar);
        mContainerView = (ActionBarContainer) decor.findViewById(
                com.android.internal.R.id.action_bar_container);
        mSplitView = (ActionBarContainer) decor.findViewById(
                com.android.internal.R.id.split_action_bar);
        if(mActionView == null || mContextView == null || mContainerView == null)        
        {
            IllegalStateException var04ADD0942DEE4FEFC9473F223FB60472_735747707 = new IllegalStateException(getClass().getSimpleName() + " can only be used " +
                    "with a compatible window decor layout");
            var04ADD0942DEE4FEFC9473F223FB60472_735747707.addTaint(taint);
            throw var04ADD0942DEE4FEFC9473F223FB60472_735747707;
        } //End block
        mActionView.setContextView(mContextView);
        mContextDisplayMode = mActionView.isSplitActionBar() ?
                CONTEXT_DISPLAY_SPLIT : CONTEXT_DISPLAY_NORMAL;
        setHomeButtonEnabled(mContext.getApplicationInfo().targetSdkVersion <
                Build.VERSION_CODES.ICE_CREAM_SANDWICH);
        setHasEmbeddedTabs(mContext.getResources().getBoolean(
                com.android.internal.R.bool.action_bar_embed_tabs));
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.756 -0400", hash_original_method = "988D25E4BF24BB800BD6556DCD183591", hash_generated_method = "83FD7E7BDD52CD71A3F3A90195212CE1")
    public void onConfigurationChanged(Configuration newConfig) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(newConfig.getTaint());
        setHasEmbeddedTabs(mContext.getResources().getBoolean(
                com.android.internal.R.bool.action_bar_embed_tabs));
        // ---------- Original Method ----------
        //setHasEmbeddedTabs(mContext.getResources().getBoolean(
                //com.android.internal.R.bool.action_bar_embed_tabs));
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.757 -0400", hash_original_method = "05911004B37CFCA0E1E3075964BC3E46", hash_generated_method = "DC20FE7C9FA9D47ED4B1783DD986D3F5")
    private void setHasEmbeddedTabs(boolean hasEmbeddedTabs) {
        mHasEmbeddedTabs = hasEmbeddedTabs;
        if(!mHasEmbeddedTabs)        
        {
            mActionView.setEmbeddedTabView(null);
            mContainerView.setTabContainer(mTabScrollView);
        } //End block
        else
        {
            mContainerView.setTabContainer(null);
            mActionView.setEmbeddedTabView(mTabScrollView);
        } //End block
        final boolean isInTabMode = getNavigationMode() == NAVIGATION_MODE_TABS;
        if(mTabScrollView != null)        
        {
            mTabScrollView.setVisibility(isInTabMode ? View.VISIBLE : View.GONE);
        } //End block
        mActionView.setCollapsable(!mHasEmbeddedTabs && isInTabMode);
        // ---------- Original Method ----------
        //mHasEmbeddedTabs = hasEmbeddedTabs;
        //if (!mHasEmbeddedTabs) {
            //mActionView.setEmbeddedTabView(null);
            //mContainerView.setTabContainer(mTabScrollView);
        //} else {
            //mContainerView.setTabContainer(null);
            //mActionView.setEmbeddedTabView(mTabScrollView);
        //}
        //final boolean isInTabMode = getNavigationMode() == NAVIGATION_MODE_TABS;
        //if (mTabScrollView != null) {
            //mTabScrollView.setVisibility(isInTabMode ? View.VISIBLE : View.GONE);
        //}
        //mActionView.setCollapsable(!mHasEmbeddedTabs && isInTabMode);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.758 -0400", hash_original_method = "04F31515B176FCA9797E5A3B1AE2D925", hash_generated_method = "735A6D25E11E3FD56C47D92F64BCEB17")
    private void ensureTabsExist() {
        if(mTabScrollView != null)        
        {
            return;
        } //End block
        ScrollingTabContainerView tabScroller = new ScrollingTabContainerView(mContext);
        if(mHasEmbeddedTabs)        
        {
            tabScroller.setVisibility(View.VISIBLE);
            mActionView.setEmbeddedTabView(tabScroller);
        } //End block
        else
        {
            tabScroller.setVisibility(getNavigationMode() == NAVIGATION_MODE_TABS ?
                    View.VISIBLE : View.GONE);
            mContainerView.setTabContainer(tabScroller);
        } //End block
        mTabScrollView = tabScroller;
        // ---------- Original Method ----------
        //if (mTabScrollView != null) {
            //return;
        //}
        //ScrollingTabContainerView tabScroller = new ScrollingTabContainerView(mContext);
        //if (mHasEmbeddedTabs) {
            //tabScroller.setVisibility(View.VISIBLE);
            //mActionView.setEmbeddedTabView(tabScroller);
        //} else {
            //tabScroller.setVisibility(getNavigationMode() == NAVIGATION_MODE_TABS ?
                    //View.VISIBLE : View.GONE);
            //mContainerView.setTabContainer(tabScroller);
        //}
        //mTabScrollView = tabScroller;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.758 -0400", hash_original_method = "FB60553060C0CBA07AA4AE6F5834D888", hash_generated_method = "4E12F126D62355C6A72337333F15C042")
     void completeDeferredDestroyActionMode() {
        if(mDeferredModeDestroyCallback != null)        
        {
            mDeferredModeDestroyCallback.onDestroyActionMode(mDeferredDestroyActionMode);
            mDeferredDestroyActionMode = null;
            mDeferredModeDestroyCallback = null;
        } //End block
        // ---------- Original Method ----------
        //if (mDeferredModeDestroyCallback != null) {
            //mDeferredModeDestroyCallback.onDestroyActionMode(mDeferredDestroyActionMode);
            //mDeferredDestroyActionMode = null;
            //mDeferredModeDestroyCallback = null;
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.759 -0400", hash_original_method = "F176835B5C4C66FE158A96087EB7976E", hash_generated_method = "83CEF4615821F705745BD617E7656796")
    public void setShowHideAnimationEnabled(boolean enabled) {
        mShowHideAnimationEnabled = enabled;
        if(!enabled && mCurrentShowAnim != null)        
        {
            mCurrentShowAnim.end();
        } //End block
        // ---------- Original Method ----------
        //mShowHideAnimationEnabled = enabled;
        //if (!enabled && mCurrentShowAnim != null) {
            //mCurrentShowAnim.end();
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.759 -0400", hash_original_method = "432643F5D3E1C75318DA649F07995AE5", hash_generated_method = "B220ABC74954603998F554514B725847")
    public void addOnMenuVisibilityListener(OnMenuVisibilityListener listener) {
        addTaint(listener.getTaint());
        mMenuVisibilityListeners.add(listener);
        // ---------- Original Method ----------
        //mMenuVisibilityListeners.add(listener);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.759 -0400", hash_original_method = "602CC871D8636269B09601DAE53B0892", hash_generated_method = "A56F20ADC0A2C0ECBFCAFA2622B9B406")
    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener listener) {
        addTaint(listener.getTaint());
        mMenuVisibilityListeners.remove(listener);
        // ---------- Original Method ----------
        //mMenuVisibilityListeners.remove(listener);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.760 -0400", hash_original_method = "459C6E3BA33A1A612E205065B1C3E1DC", hash_generated_method = "FE100F6314B3DFC7979CA47AE323B422")
    public void dispatchMenuVisibilityChanged(boolean isVisible) {
        if(isVisible == mLastMenuVisibility)        
        {
            return;
        } //End block
        mLastMenuVisibility = isVisible;
        final int count = mMenuVisibilityListeners.size();
for(int i = 0;i < count;i++)
        {
            mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(isVisible);
        } //End block
        // ---------- Original Method ----------
        //if (isVisible == mLastMenuVisibility) {
            //return;
        //}
        //mLastMenuVisibility = isVisible;
        //final int count = mMenuVisibilityListeners.size();
        //for (int i = 0; i < count; i++) {
            //mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(isVisible);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.760 -0400", hash_original_method = "B07E8854D08E9349BCA465572774AD05", hash_generated_method = "678996C71ED6714E3D41D00FD1F413CF")
    @Override
    public void setCustomView(int resId) {
        addTaint(resId);
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(resId, mActionView, false));
        // ---------- Original Method ----------
        //setCustomView(LayoutInflater.from(getThemedContext()).inflate(resId, mActionView, false));
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.761 -0400", hash_original_method = "7B32C43910C34545BE6AF1945BAD78C2", hash_generated_method = "77CE2CE08EE695D2AB8BC51AB4FD0813")
    @Override
    public void setDisplayUseLogoEnabled(boolean useLogo) {
        addTaint(useLogo);
        setDisplayOptions(useLogo ? DISPLAY_USE_LOGO : 0, DISPLAY_USE_LOGO);
        // ---------- Original Method ----------
        //setDisplayOptions(useLogo ? DISPLAY_USE_LOGO : 0, DISPLAY_USE_LOGO);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.761 -0400", hash_original_method = "7F0843FA34DCBF9EB20E04642D9BA553", hash_generated_method = "03CA352696FF19982CED15E7F0D65ED0")
    @Override
    public void setDisplayShowHomeEnabled(boolean showHome) {
        addTaint(showHome);
        setDisplayOptions(showHome ? DISPLAY_SHOW_HOME : 0, DISPLAY_SHOW_HOME);
        // ---------- Original Method ----------
        //setDisplayOptions(showHome ? DISPLAY_SHOW_HOME : 0, DISPLAY_SHOW_HOME);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.761 -0400", hash_original_method = "F859D1EA93554E58B56470BC246367BB", hash_generated_method = "0A7DA6E1BE4ADEA7B38AB4C25AE4EBEA")
    @Override
    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        addTaint(showHomeAsUp);
        setDisplayOptions(showHomeAsUp ? DISPLAY_HOME_AS_UP : 0, DISPLAY_HOME_AS_UP);
        // ---------- Original Method ----------
        //setDisplayOptions(showHomeAsUp ? DISPLAY_HOME_AS_UP : 0, DISPLAY_HOME_AS_UP);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.761 -0400", hash_original_method = "6C96766F23C0B7039876B0F0E23B9AFF", hash_generated_method = "7D46AD92F520435BA75A36300C15EE73")
    @Override
    public void setDisplayShowTitleEnabled(boolean showTitle) {
        addTaint(showTitle);
        setDisplayOptions(showTitle ? DISPLAY_SHOW_TITLE : 0, DISPLAY_SHOW_TITLE);
        // ---------- Original Method ----------
        //setDisplayOptions(showTitle ? DISPLAY_SHOW_TITLE : 0, DISPLAY_SHOW_TITLE);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "C60EF70FF87399C557FC41651D29083C", hash_generated_method = "29571D07909F6796D1D19E869B045647")
    @Override
    public void setDisplayShowCustomEnabled(boolean showCustom) {
        addTaint(showCustom);
        setDisplayOptions(showCustom ? DISPLAY_SHOW_CUSTOM : 0, DISPLAY_SHOW_CUSTOM);
        // ---------- Original Method ----------
        //setDisplayOptions(showCustom ? DISPLAY_SHOW_CUSTOM : 0, DISPLAY_SHOW_CUSTOM);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "4D222159BA92CC275B5D5CB9ADE89E45", hash_generated_method = "43C9CE5A9E2E44AB155948A0E1AED300")
    @Override
    public void setHomeButtonEnabled(boolean enable) {
        addTaint(enable);
        mActionView.setHomeButtonEnabled(enable);
        // ---------- Original Method ----------
        //mActionView.setHomeButtonEnabled(enable);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "93A6ED10F8DC44A47D2A5E40CE348C6E", hash_generated_method = "6B348D97A0206C25661E48BFE19FBB3D")
    @Override
    public void setTitle(int resId) {
        addTaint(resId);
        setTitle(mContext.getString(resId));
        // ---------- Original Method ----------
        //setTitle(mContext.getString(resId));
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "28BA950963DD65E7A01A305F037657F9", hash_generated_method = "A64506F16C521D12174AFA66B8C2E3CA")
    @Override
    public void setSubtitle(int resId) {
        addTaint(resId);
        setSubtitle(mContext.getString(resId));
        // ---------- Original Method ----------
        //setSubtitle(mContext.getString(resId));
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "11EA30EC8C9A94D19D07ED1EA15B77B1", hash_generated_method = "5951982AE90F8BED86D45A488281DC7D")
    public void setSelectedNavigationItem(int position) {
        addTaint(position);
switch(mActionView.getNavigationMode()){
        case NAVIGATION_MODE_TABS:
        selectTab(mTabs.get(position));
        break;
        case NAVIGATION_MODE_LIST:
        mActionView.setDropdownSelectedPosition(position);
        break;
        default:
        IllegalStateException var3ADFCD5A7B057933B4DBF2BA9CE05C21_586475633 = new IllegalStateException(
                    "setSelectedNavigationIndex not valid for current navigation mode");
        var3ADFCD5A7B057933B4DBF2BA9CE05C21_586475633.addTaint(taint);
        throw var3ADFCD5A7B057933B4DBF2BA9CE05C21_586475633;
}
        // ---------- Original Method ----------
        //switch (mActionView.getNavigationMode()) {
        //case NAVIGATION_MODE_TABS:
            //selectTab(mTabs.get(position));
            //break;
        //case NAVIGATION_MODE_LIST:
            //mActionView.setDropdownSelectedPosition(position);
            //break;
        //default:
            //throw new IllegalStateException(
                    //"setSelectedNavigationIndex not valid for current navigation mode");
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "B97B28074DE5FCD367C21E021091BD45", hash_generated_method = "18F3DD1B438293814EC931E00EFDC066")
    public void removeAllTabs() {
        cleanupTabs();
        // ---------- Original Method ----------
        //cleanupTabs();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "62AE76DE88CDD8F4A1D9D70F05364203", hash_generated_method = "B9834C343C2B2072BCE58DD14DC7465B")
    private void cleanupTabs() {
        if(mSelectedTab != null)        
        {
            selectTab(null);
        } //End block
        mTabs.clear();
        if(mTabScrollView != null)        
        {
            mTabScrollView.removeAllTabs();
        } //End block
        mSavedTabPosition = INVALID_POSITION;
        // ---------- Original Method ----------
        //if (mSelectedTab != null) {
            //selectTab(null);
        //}
        //mTabs.clear();
        //if (mTabScrollView != null) {
            //mTabScrollView.removeAllTabs();
        //}
        //mSavedTabPosition = INVALID_POSITION;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.762 -0400", hash_original_method = "2496AA6E0C686AA709C048B4D9A978B1", hash_generated_method = "80231901242D0704C2BFDD2C7B2BB6C1")
    public void setTitle(CharSequence title) {
        addTaint(title.getTaint());
        mActionView.setTitle(title);
        // ---------- Original Method ----------
        //mActionView.setTitle(title);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "51EFE4022329006F8B97012193639D8C", hash_generated_method = "4C8CDD6A0D944F8C37B5A8FFA5D30F7C")
    public void setSubtitle(CharSequence subtitle) {
        addTaint(subtitle.getTaint());
        mActionView.setSubtitle(subtitle);
        // ---------- Original Method ----------
        //mActionView.setSubtitle(subtitle);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "3F11889B61BC98E428CD1B2A494BFC53", hash_generated_method = "35BDF7CD039A0AAD4C968677D1F7DAF0")
    public void setDisplayOptions(int options) {
        addTaint(options);
        mActionView.setDisplayOptions(options);
        // ---------- Original Method ----------
        //mActionView.setDisplayOptions(options);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "728BC8FE52686E77081ACD4BE860295D", hash_generated_method = "CCE23E42931E9B04708EDC15C9F3FB97")
    public void setDisplayOptions(int options, int mask) {
        addTaint(mask);
        addTaint(options);
        final int current = mActionView.getDisplayOptions();
        mActionView.setDisplayOptions((options & mask) | (current & ~mask));
        // ---------- Original Method ----------
        //final int current = mActionView.getDisplayOptions();
        //mActionView.setDisplayOptions((options & mask) | (current & ~mask));
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "61B3599899498272F50206755C75E2FF", hash_generated_method = "3B23A19E5E677BAB19A0341135356599")
    public void setBackgroundDrawable(Drawable d) {
        addTaint(d.getTaint());
        mContainerView.setPrimaryBackground(d);
        // ---------- Original Method ----------
        //mContainerView.setPrimaryBackground(d);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "71E64D08C4AF72E9E040B3394527D26B", hash_generated_method = "BC1A01EFF03762844B48926E3EFBBBC7")
    public void setStackedBackgroundDrawable(Drawable d) {
        addTaint(d.getTaint());
        mContainerView.setStackedBackground(d);
        // ---------- Original Method ----------
        //mContainerView.setStackedBackground(d);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "228BA16930C30CB95A53923FA275FB3B", hash_generated_method = "F6DB67722FCB849E378E3B706CB96EFD")
    public void setSplitBackgroundDrawable(Drawable d) {
        addTaint(d.getTaint());
        if(mSplitView != null)        
        {
            mSplitView.setSplitBackground(d);
        } //End block
        // ---------- Original Method ----------
        //if (mSplitView != null) {
            //mSplitView.setSplitBackground(d);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "1D010261269A6D78FBB7E8684C141376", hash_generated_method = "2E75307912A79E6CDB351CB0F355879D")
    public View getCustomView() {
View var64944311D4D6880069B86CC1F2B1C0E4_946057822 =         mActionView.getCustomNavigationView();
        var64944311D4D6880069B86CC1F2B1C0E4_946057822.addTaint(taint);
        return var64944311D4D6880069B86CC1F2B1C0E4_946057822;
        // ---------- Original Method ----------
        //return mActionView.getCustomNavigationView();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "2440C8680C5986DA6B98061A5444C3DF", hash_generated_method = "06FCB1C674E0C889EBFF2FBD454B25A9")
    public CharSequence getTitle() {
CharSequence var9F697EA9306D924AB79FA98C2CB4BD65_1564099676 =         mActionView.getTitle();
        var9F697EA9306D924AB79FA98C2CB4BD65_1564099676.addTaint(taint);
        return var9F697EA9306D924AB79FA98C2CB4BD65_1564099676;
        // ---------- Original Method ----------
        //return mActionView.getTitle();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.763 -0400", hash_original_method = "7C309AA3715DBCB025C148B6DD4FF98A", hash_generated_method = "3AFCD8EB2A5705320215A2889A306ACB")
    public CharSequence getSubtitle() {
CharSequence var49061D54F2B8280289A21FE9090C3B9B_840907939 =         mActionView.getSubtitle();
        var49061D54F2B8280289A21FE9090C3B9B_840907939.addTaint(taint);
        return var49061D54F2B8280289A21FE9090C3B9B_840907939;
        // ---------- Original Method ----------
        //return mActionView.getSubtitle();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.764 -0400", hash_original_method = "3CF23CB733C1A66BA695174D1D5C90F5", hash_generated_method = "29B7035FBC5DDD18CC57B40F66E07DB5")
    public int getNavigationMode() {
        int varA30E9441B8B9E1D55A44D3C3EF322213_808227939 = (mActionView.getNavigationMode());
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1531626425 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1531626425;
        // ---------- Original Method ----------
        //return mActionView.getNavigationMode();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.764 -0400", hash_original_method = "FB6F75E61CAD1294F319FE9A20EDC0AA", hash_generated_method = "2C1BA6972AF9CCD6CA9AC81640C19097")
    public int getDisplayOptions() {
        int var0C7296553C0E224D4BA4DAA88E5B7190_983333907 = (mActionView.getDisplayOptions());
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1101318422 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1101318422;
        // ---------- Original Method ----------
        //return mActionView.getDisplayOptions();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.764 -0400", hash_original_method = "6F680EB91F7327296F43077A9CFCB9CD", hash_generated_method = "B91B0E22D2D8A39FFC7B6C7694FF475F")
    public ActionMode startActionMode(ActionMode.Callback callback) {
        addTaint(callback.getTaint());
        boolean wasHidden = false;
        if(mActionMode != null)        
        {
            wasHidden = mWasHiddenBeforeMode;
            mActionMode.finish();
        } //End block
        mContextView.killMode();
        ActionModeImpl mode = new ActionModeImpl(callback);
        if(mode.dispatchOnCreate())        
        {
            mWasHiddenBeforeMode = !isShowing() || wasHidden;
            mode.invalidate();
            mContextView.initForMode(mode);
            animateToMode(true);
            if(mSplitView != null && mContextDisplayMode == CONTEXT_DISPLAY_SPLIT)            
            {
                mSplitView.setVisibility(View.VISIBLE);
            } //End block
            mContextView.sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
            mActionMode = mode;
ActionMode var39C6EC747783AFD94A86A7BFC763D859_1643307202 =             mode;
            var39C6EC747783AFD94A86A7BFC763D859_1643307202.addTaint(taint);
            return var39C6EC747783AFD94A86A7BFC763D859_1643307202;
        } //End block
ActionMode var540C13E9E156B687226421B24F2DF178_1472463163 =         null;
        var540C13E9E156B687226421B24F2DF178_1472463163.addTaint(taint);
        return var540C13E9E156B687226421B24F2DF178_1472463163;
        // ---------- Original Method ----------
        //boolean wasHidden = false;
        //if (mActionMode != null) {
            //wasHidden = mWasHiddenBeforeMode;
            //mActionMode.finish();
        //}
        //mContextView.killMode();
        //ActionModeImpl mode = new ActionModeImpl(callback);
        //if (mode.dispatchOnCreate()) {
            //mWasHiddenBeforeMode = !isShowing() || wasHidden;
            //mode.invalidate();
            //mContextView.initForMode(mode);
            //animateToMode(true);
            //if (mSplitView != null && mContextDisplayMode == CONTEXT_DISPLAY_SPLIT) {
                //mSplitView.setVisibility(View.VISIBLE);
            //}
            //mContextView.sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
            //mActionMode = mode;
            //return mode;
        //}
        //return null;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.764 -0400", hash_original_method = "4981E6F1136ACFE06B622EFDD1F32611", hash_generated_method = "23858E465B75E8EE0B28E9C52272C9FD")
    private void configureTab(Tab tab, int position) {
        addTaint(position);
        addTaint(tab.getTaint());
        final TabImpl tabi = (TabImpl) tab;
        final ActionBar.TabListener callback = tabi.getCallback();
        if(callback == null)        
        {
            IllegalStateException varA5DB84C4846DC3D1CE3654BEBEED4456_133791778 = new IllegalStateException("Action Bar Tab must have a Callback");
            varA5DB84C4846DC3D1CE3654BEBEED4456_133791778.addTaint(taint);
            throw varA5DB84C4846DC3D1CE3654BEBEED4456_133791778;
        } //End block
        tabi.setPosition(position);
        mTabs.add(position, tabi);
        final int count = mTabs.size();
for(int i = position + 1;i < count;i++)
        {
            mTabs.get(i).setPosition(i);
        } //End block
        // ---------- Original Method ----------
        //final TabImpl tabi = (TabImpl) tab;
        //final ActionBar.TabListener callback = tabi.getCallback();
        //if (callback == null) {
            //throw new IllegalStateException("Action Bar Tab must have a Callback");
        //}
        //tabi.setPosition(position);
        //mTabs.add(position, tabi);
        //final int count = mTabs.size();
        //for (int i = position + 1; i < count; i++) {
            //mTabs.get(i).setPosition(i);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.764 -0400", hash_original_method = "1EECF05398B0BE8AB00F4615B5278612", hash_generated_method = "66518086452A2412486B7BD9AC59E907")
    @Override
    public void addTab(Tab tab) {
        addTaint(tab.getTaint());
        addTab(tab, mTabs.isEmpty());
        // ---------- Original Method ----------
        //addTab(tab, mTabs.isEmpty());
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.764 -0400", hash_original_method = "654B3E709639F944F902BA5D9B821C89", hash_generated_method = "EAD6371773866052EE615718C212D85D")
    @Override
    public void addTab(Tab tab, int position) {
        addTaint(position);
        addTaint(tab.getTaint());
        addTab(tab, position, mTabs.isEmpty());
        // ---------- Original Method ----------
        //addTab(tab, position, mTabs.isEmpty());
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.765 -0400", hash_original_method = "89408631A5DFBB8696DA15288AA15329", hash_generated_method = "BD7147D6808DEEDBE37D5C996CD8AE73")
    @Override
    public void addTab(Tab tab, boolean setSelected) {
        addTaint(setSelected);
        addTaint(tab.getTaint());
        ensureTabsExist();
        mTabScrollView.addTab(tab, setSelected);
        configureTab(tab, mTabs.size());
        if(setSelected)        
        {
            selectTab(tab);
        } //End block
        // ---------- Original Method ----------
        //ensureTabsExist();
        //mTabScrollView.addTab(tab, setSelected);
        //configureTab(tab, mTabs.size());
        //if (setSelected) {
            //selectTab(tab);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.765 -0400", hash_original_method = "041C4C5A1F00C6CFE52123DF1D5CFA62", hash_generated_method = "60F4F3AAFBAFBE5A448D7F360D59B686")
    @Override
    public void addTab(Tab tab, int position, boolean setSelected) {
        addTaint(setSelected);
        addTaint(position);
        addTaint(tab.getTaint());
        ensureTabsExist();
        mTabScrollView.addTab(tab, position, setSelected);
        configureTab(tab, position);
        if(setSelected)        
        {
            selectTab(tab);
        } //End block
        // ---------- Original Method ----------
        //ensureTabsExist();
        //mTabScrollView.addTab(tab, position, setSelected);
        //configureTab(tab, position);
        //if (setSelected) {
            //selectTab(tab);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.765 -0400", hash_original_method = "086373AB3E22E85AEE50C2F735BD106D", hash_generated_method = "14B5994117E95FDEC44E6F068F3DAD79")
    @Override
    public Tab newTab() {
Tab varC3DEA72E72707DFD07E1CD1BC00A2C51_1363454911 =         new TabImpl();
        varC3DEA72E72707DFD07E1CD1BC00A2C51_1363454911.addTaint(taint);
        return varC3DEA72E72707DFD07E1CD1BC00A2C51_1363454911;
        // ---------- Original Method ----------
        //return new TabImpl();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.765 -0400", hash_original_method = "D97BFE7F24375737332EF4EB210BF330", hash_generated_method = "C5B7CB179830E6D14033CF6A4E922E69")
    @Override
    public void removeTab(Tab tab) {
        addTaint(tab.getTaint());
        removeTabAt(tab.getPosition());
        // ---------- Original Method ----------
        //removeTabAt(tab.getPosition());
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.765 -0400", hash_original_method = "B3645E4F4AB7E9BBDE0315AEF0157D54", hash_generated_method = "D9BB1173DA2D6FF907F1D909AB99572E")
    @Override
    public void removeTabAt(int position) {
        addTaint(position);
        if(mTabScrollView == null)        
        {
            return;
        } //End block
        int selectedTabPosition = mSelectedTab != null
                ? mSelectedTab.getPosition() : mSavedTabPosition;
        mTabScrollView.removeTabAt(position);
        TabImpl removedTab = mTabs.remove(position);
        if(removedTab != null)        
        {
            removedTab.setPosition(-1);
        } //End block
        final int newTabCount = mTabs.size();
for(int i = position;i < newTabCount;i++)
        {
            mTabs.get(i).setPosition(i);
        } //End block
        if(selectedTabPosition == position)        
        {
            selectTab(mTabs.isEmpty() ? null : mTabs.get(Math.max(0, position - 1)));
        } //End block
        // ---------- Original Method ----------
        //if (mTabScrollView == null) {
            //return;
        //}
        //int selectedTabPosition = mSelectedTab != null
                //? mSelectedTab.getPosition() : mSavedTabPosition;
        //mTabScrollView.removeTabAt(position);
        //TabImpl removedTab = mTabs.remove(position);
        //if (removedTab != null) {
            //removedTab.setPosition(-1);
        //}
        //final int newTabCount = mTabs.size();
        //for (int i = position; i < newTabCount; i++) {
            //mTabs.get(i).setPosition(i);
        //}
        //if (selectedTabPosition == position) {
            //selectTab(mTabs.isEmpty() ? null : mTabs.get(Math.max(0, position - 1)));
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.766 -0400", hash_original_method = "08E647315276EA6EFD361EFCDF66D68C", hash_generated_method = "C9E3CCB156A465A92272FE8FB6C9EAB7")
    @Override
    public void selectTab(Tab tab) {
        if(getNavigationMode() != NAVIGATION_MODE_TABS)        
        {
            mSavedTabPosition = tab != null ? tab.getPosition() : INVALID_POSITION;
            return;
        } //End block
        final FragmentTransaction trans = mActivity.getFragmentManager().beginTransaction()
                .disallowAddToBackStack();
        if(mSelectedTab == tab)        
        {
            if(mSelectedTab != null)            
            {
                mSelectedTab.getCallback().onTabReselected(mSelectedTab, trans);
                mTabScrollView.animateToTab(tab.getPosition());
            } //End block
        } //End block
        else
        {
            mTabScrollView.setTabSelected(tab != null ? tab.getPosition() : Tab.INVALID_POSITION);
            if(mSelectedTab != null)            
            {
                mSelectedTab.getCallback().onTabUnselected(mSelectedTab, trans);
            } //End block
            mSelectedTab = (TabImpl) tab;
            if(mSelectedTab != null)            
            {
                mSelectedTab.getCallback().onTabSelected(mSelectedTab, trans);
            } //End block
        } //End block
        if(!trans.isEmpty())        
        {
            trans.commit();
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.766 -0400", hash_original_method = "2AD2D5B38A8D730799170A69D020CCD7", hash_generated_method = "FF22FC3B0FEB148ECDCC34BECF1B8038")
    @Override
    public Tab getSelectedTab() {
Tab var9219C2C21C7D119E2F710096353412F3_274621857 =         mSelectedTab;
        var9219C2C21C7D119E2F710096353412F3_274621857.addTaint(taint);
        return var9219C2C21C7D119E2F710096353412F3_274621857;
        // ---------- Original Method ----------
        //return mSelectedTab;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.766 -0400", hash_original_method = "7329EA59D7EBE41DA23E0A3E55EEA1A2", hash_generated_method = "10BF16E557F47795AF05EB792193798F")
    @Override
    public int getHeight() {
        int varEB3F0EDA171605B90597449B198637CD_311375633 = (mContainerView.getHeight());
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_2041337204 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_2041337204;
        // ---------- Original Method ----------
        //return mContainerView.getHeight();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.766 -0400", hash_original_method = "546A4715540EEE6F49A6EAAEA56C241C", hash_generated_method = "9B6F5E1BBF9ED1EA79DF35779E1A9F3A")
    @Override
    public void show() {
        show(true);
        // ---------- Original Method ----------
        //show(true);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.767 -0400", hash_original_method = "B9AB02E6F73BE3423F7A7521B424A639", hash_generated_method = "16F89618A7F83118B97AC25034FF8F4F")
     void show(boolean markHiddenBeforeMode) {
        addTaint(markHiddenBeforeMode);
        if(mCurrentShowAnim != null)        
        {
            mCurrentShowAnim.end();
        } //End block
        if(mContainerView.getVisibility() == View.VISIBLE)        
        {
            if(markHiddenBeforeMode)            
            mWasHiddenBeforeMode = false;
            return;
        } //End block
        mContainerView.setVisibility(View.VISIBLE);
        if(mShowHideAnimationEnabled)        
        {
            mContainerView.setAlpha(0);
            AnimatorSet anim = new AnimatorSet();
            AnimatorSet.Builder b = anim.play(ObjectAnimator.ofFloat(mContainerView, "alpha", 1));
            if(mContentView != null)            
            {
                b.with(ObjectAnimator.ofFloat(mContentView, "translationY",
                        -mContainerView.getHeight(), 0));
                mContainerView.setTranslationY(-mContainerView.getHeight());
                b.with(ObjectAnimator.ofFloat(mContainerView, "translationY", 0));
            } //End block
            if(mSplitView != null && mContextDisplayMode == CONTEXT_DISPLAY_SPLIT)            
            {
                mSplitView.setAlpha(0);
                mSplitView.setVisibility(View.VISIBLE);
                b.with(ObjectAnimator.ofFloat(mSplitView, "alpha", 1));
            } //End block
            anim.addListener(mShowListener);
            mCurrentShowAnim = anim;
            anim.start();
        } //End block
        else
        {
            mContainerView.setAlpha(1);
            mContainerView.setTranslationY(0);
            mShowListener.onAnimationEnd(null);
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.768 -0400", hash_original_method = "F98E692B5353704914249A64080F4E16", hash_generated_method = "5AC3F8092073922F480BA99D58A22211")
    @Override
    public void hide() {
        if(mCurrentShowAnim != null)        
        {
            mCurrentShowAnim.end();
        } //End block
        if(mContainerView.getVisibility() == View.GONE)        
        {
            return;
        } //End block
        if(mShowHideAnimationEnabled)        
        {
            mContainerView.setAlpha(1);
            mContainerView.setTransitioning(true);
            AnimatorSet anim = new AnimatorSet();
            AnimatorSet.Builder b = anim.play(ObjectAnimator.ofFloat(mContainerView, "alpha", 0));
            if(mContentView != null)            
            {
                b.with(ObjectAnimator.ofFloat(mContentView, "translationY",
                        0, -mContainerView.getHeight()));
                b.with(ObjectAnimator.ofFloat(mContainerView, "translationY",
                        -mContainerView.getHeight()));
            } //End block
            if(mSplitView != null && mSplitView.getVisibility() == View.VISIBLE)            
            {
                mSplitView.setAlpha(1);
                b.with(ObjectAnimator.ofFloat(mSplitView, "alpha", 0));
            } //End block
            anim.addListener(mHideListener);
            mCurrentShowAnim = anim;
            anim.start();
        } //End block
        else
        {
            mHideListener.onAnimationEnd(null);
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.768 -0400", hash_original_method = "02F75CA35AA01FBE52A717B38FA758F7", hash_generated_method = "20A1479C62C9681D89B2A7F6C0B162AA")
    public boolean isShowing() {
        boolean varC722B40E5C7D28A0B5AB9F4E802FA7D1_661098830 = (mContainerView.getVisibility() == View.VISIBLE);
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1885296821 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1885296821;
        // ---------- Original Method ----------
        //return mContainerView.getVisibility() == View.VISIBLE;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.769 -0400", hash_original_method = "6F997D5B2915B28943A5CB4BD830D4B2", hash_generated_method = "6C0CCE7DB6FA54D966C039CF2372B5C7")
     void animateToMode(boolean toActionMode) {
        addTaint(toActionMode);
        if(toActionMode)        
        {
            show(false);
        } //End block
        if(mCurrentModeAnim != null)        
        {
            mCurrentModeAnim.end();
        } //End block
        mActionView.animateToVisibility(toActionMode ? View.GONE : View.VISIBLE);
        mContextView.animateToVisibility(toActionMode ? View.VISIBLE : View.GONE);
        if(mTabScrollView != null && !mActionView.hasEmbeddedTabs() && mActionView.isCollapsed())        
        {
            mTabScrollView.animateToVisibility(toActionMode ? View.GONE : View.VISIBLE);
        } //End block
        // ---------- Original Method ----------
        //if (toActionMode) {
            //show(false);
        //}
        //if (mCurrentModeAnim != null) {
            //mCurrentModeAnim.end();
        //}
        //mActionView.animateToVisibility(toActionMode ? View.GONE : View.VISIBLE);
        //mContextView.animateToVisibility(toActionMode ? View.VISIBLE : View.GONE);
        //if (mTabScrollView != null && !mActionView.hasEmbeddedTabs() && mActionView.isCollapsed()) {
            //mTabScrollView.animateToVisibility(toActionMode ? View.GONE : View.VISIBLE);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.769 -0400", hash_original_method = "3EA212821B302B1822414F8223429D93", hash_generated_method = "E29D5829EF947823785CDD21F7DA6545")
    public Context getThemedContext() {
        if(mThemedContext == null)        
        {
            TypedValue outValue = new TypedValue();
            Resources.Theme currentTheme = mContext.getTheme();
            currentTheme.resolveAttribute(com.android.internal.R.attr.actionBarWidgetTheme,
                    outValue, true);
            final int targetThemeRes = outValue.resourceId;
            if(targetThemeRes != 0 && mContext.getThemeResId() != targetThemeRes)            
            {
                mThemedContext = new ContextThemeWrapper(mContext, targetThemeRes);
            } //End block
            else
            {
                mThemedContext = mContext;
            } //End block
        } //End block
Context var43C70B6AAEE916E45207DC7D98ECA445_766903129 =         mThemedContext;
        var43C70B6AAEE916E45207DC7D98ECA445_766903129.addTaint(taint);
        return var43C70B6AAEE916E45207DC7D98ECA445_766903129;
        // ---------- Original Method ----------
        //if (mThemedContext == null) {
            //TypedValue outValue = new TypedValue();
            //Resources.Theme currentTheme = mContext.getTheme();
            //currentTheme.resolveAttribute(com.android.internal.R.attr.actionBarWidgetTheme,
                    //outValue, true);
            //final int targetThemeRes = outValue.resourceId;
            //if (targetThemeRes != 0 && mContext.getThemeResId() != targetThemeRes) {
                //mThemedContext = new ContextThemeWrapper(mContext, targetThemeRes);
            //} else {
                //mThemedContext = mContext;
            //}
        //}
        //return mThemedContext;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.769 -0400", hash_original_method = "82CB01F3A317C5518DAD7B810C7481F9", hash_generated_method = "06AF237D12B80E2D8F2743C49E6B95B2")
    @Override
    public void setCustomView(View view) {
        addTaint(view.getTaint());
        mActionView.setCustomNavigationView(view);
        // ---------- Original Method ----------
        //mActionView.setCustomNavigationView(view);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.769 -0400", hash_original_method = "D405B9340EEDF523A821CB828A77247A", hash_generated_method = "34814D23B6C0D1F0BE80F751380DEDBD")
    @Override
    public void setCustomView(View view, LayoutParams layoutParams) {
        addTaint(layoutParams.getTaint());
        addTaint(view.getTaint());
        view.setLayoutParams(layoutParams);
        mActionView.setCustomNavigationView(view);
        // ---------- Original Method ----------
        //view.setLayoutParams(layoutParams);
        //mActionView.setCustomNavigationView(view);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.769 -0400", hash_original_method = "DE3A042A48DE08AEC88EC1C0AD695D6F", hash_generated_method = "95C7ABF4D21905A70B738C0335BFBE78")
    @Override
    public void setListNavigationCallbacks(SpinnerAdapter adapter, OnNavigationListener callback) {
        addTaint(callback.getTaint());
        addTaint(adapter.getTaint());
        mActionView.setDropdownAdapter(adapter);
        mActionView.setCallback(callback);
        // ---------- Original Method ----------
        //mActionView.setDropdownAdapter(adapter);
        //mActionView.setCallback(callback);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.769 -0400", hash_original_method = "DA5FE7663B58F3C2464F93A06566CD8E", hash_generated_method = "A4E454F127A0707BE7CD732AE00FFE8F")
    @Override
    public int getSelectedNavigationIndex() {
switch(mActionView.getNavigationMode()){
        case NAVIGATION_MODE_TABS:
        int var8326A94419B538CA8BDFD7954EF1D2FA_762421445 = (mSelectedTab != null ? mSelectedTab.getPosition() : -1);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1970862264 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1970862264;
        case NAVIGATION_MODE_LIST:
        int var83966BCEAE262D9279AD49EF454DABF0_1478132355 = (mActionView.getDropdownSelectedPosition());
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1651120589 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1651120589;
        default:
        int var6BB61E3B7BCE0931DA574D19D1D82C88_111354902 = (-1);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_764417175 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_764417175;
}
        // ---------- Original Method ----------
        //switch (mActionView.getNavigationMode()) {
            //case NAVIGATION_MODE_TABS:
                //return mSelectedTab != null ? mSelectedTab.getPosition() : -1;
            //case NAVIGATION_MODE_LIST:
                //return mActionView.getDropdownSelectedPosition();
            //default:
                //return -1;
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.770 -0400", hash_original_method = "29C3EDF1B0696DD606C19CEF5DFCB27A", hash_generated_method = "B7BDD27C465B5CC0206EF11C691DCFF4")
    @Override
    public int getNavigationItemCount() {
switch(mActionView.getNavigationMode()){
        case NAVIGATION_MODE_TABS:
        int var0B7A407F37E5C2F2A9E39CCC31D4D665_1170348247 = (mTabs.size());
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1776974091 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1776974091;
        case NAVIGATION_MODE_LIST:
        SpinnerAdapter adapter = mActionView.getDropdownAdapter();
        int varF88377EC3348FC898F27B3A70BA68C13_1800505459 = (adapter != null ? adapter.getCount() : 0);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_367642026 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_367642026;
        default:
        int varCFCD208495D565EF66E7DFF9F98764DA_671797030 = (0);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1223457292 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1223457292;
}
        // ---------- Original Method ----------
        //switch (mActionView.getNavigationMode()) {
            //case NAVIGATION_MODE_TABS:
                //return mTabs.size();
            //case NAVIGATION_MODE_LIST:
                //SpinnerAdapter adapter = mActionView.getDropdownAdapter();
                //return adapter != null ? adapter.getCount() : 0;
            //default:
                //return 0;
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.770 -0400", hash_original_method = "44E873137D601CC44196E7945B5F3744", hash_generated_method = "282BB0B45B106DC8B00C581E2F6869D2")
    @Override
    public int getTabCount() {
        int var0B7A407F37E5C2F2A9E39CCC31D4D665_1960893606 = (mTabs.size());
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_628208346 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_628208346;
        // ---------- Original Method ----------
        //return mTabs.size();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.770 -0400", hash_original_method = "2CF769A940AAC419EB99620655E4C93A", hash_generated_method = "8E5EC1A027DF91D96737FB85785ABE87")
    @Override
    public void setNavigationMode(int mode) {
        addTaint(mode);
        final int oldMode = mActionView.getNavigationMode();
switch(oldMode){
        case NAVIGATION_MODE_TABS:
        mSavedTabPosition = getSelectedNavigationIndex();
        selectTab(null);
        mTabScrollView.setVisibility(View.GONE);
        break;
}        mActionView.setNavigationMode(mode);
switch(mode){
        case NAVIGATION_MODE_TABS:
        ensureTabsExist();
        mTabScrollView.setVisibility(View.VISIBLE);
        if(mSavedTabPosition != INVALID_POSITION)        
        {
            setSelectedNavigationItem(mSavedTabPosition);
            mSavedTabPosition = INVALID_POSITION;
        } //End block
        break;
}        mActionView.setCollapsable(mode == NAVIGATION_MODE_TABS && !mHasEmbeddedTabs);
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.770 -0400", hash_original_method = "389BF0B1EF97627FAF399E95ED0D1079", hash_generated_method = "1ED84212A9049DA2C156E255147FD0E5")
    @Override
    public Tab getTabAt(int index) {
        addTaint(index);
Tab var1B354487B7E90F4FE7AAB17F5B158656_1934058155 =         mTabs.get(index);
        var1B354487B7E90F4FE7AAB17F5B158656_1934058155.addTaint(taint);
        return var1B354487B7E90F4FE7AAB17F5B158656_1934058155;
        // ---------- Original Method ----------
        //return mTabs.get(index);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.770 -0400", hash_original_method = "246181C3E410F19AA68D711311C45E89", hash_generated_method = "423746E99ECDCB95F533C8BE8D6BFCFD")
    @Override
    public void setIcon(int resId) {
        addTaint(resId);
        mActionView.setIcon(resId);
        // ---------- Original Method ----------
        //mActionView.setIcon(resId);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_method = "976AFC7A654132AA465D788A8B684BD0", hash_generated_method = "9F42A64E5B95D8368A7DB2AB708FDB3C")
    @Override
    public void setIcon(Drawable icon) {
        addTaint(icon.getTaint());
        mActionView.setIcon(icon);
        // ---------- Original Method ----------
        //mActionView.setIcon(icon);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_method = "629A6907FC2030BF835EC3846EE4C4B9", hash_generated_method = "B04E3D821029E1699010EBD48960E678")
    @Override
    public void setLogo(int resId) {
        addTaint(resId);
        mActionView.setLogo(resId);
        // ---------- Original Method ----------
        //mActionView.setLogo(resId);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_method = "563E995ECF1A81A300841064D1D1EC89", hash_generated_method = "DE0C7A671F0AD2473F3D09275CB14BA8")
    @Override
    public void setLogo(Drawable logo) {
        addTaint(logo.getTaint());
        mActionView.setLogo(logo);
        // ---------- Original Method ----------
        //mActionView.setLogo(logo);
    }

    
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_field = "1804C5EC7AAE0B28B15CFDA061D25829", hash_generated_field = "8F251D8FB2618CB877D06AB4E966D630")

        private ActionMode.Callback mCallback;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_field = "CFE8F878843D1CA6A8D8B333030E59FA", hash_generated_field = "D444FB62DCB4C4F94C883BDD2D0F7012")

        private MenuBuilder mMenu;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_field = "0F85706FF7ACEA880EA803CB0E63A48D", hash_generated_field = "E98181B1669419FD7A308D60329FDF80")

        private WeakReference<View> mCustomView;
        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_method = "329ADB94B68F012647BA8B5F412D446E", hash_generated_method = "EB690C59C99DA9C6D91EA9F49BC255A4")
        public  ActionModeImpl(ActionMode.Callback callback) {
            mCallback = callback;
            mMenu = new MenuBuilder(getThemedContext())
                    .setDefaultShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            mMenu.setCallback(this);
            // ---------- Original Method ----------
            //mCallback = callback;
            //mMenu = new MenuBuilder(getThemedContext())
                    //.setDefaultShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            //mMenu.setCallback(this);
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_method = "51A0980A38EB9FD2F0CEA8B2697D7721", hash_generated_method = "113DD2037D67C1D0164A884320EDB131")
        @Override
        public MenuInflater getMenuInflater() {
MenuInflater varA43DA81708214873250337FF278D398C_1880768947 =             new MenuInflater(getThemedContext());
            varA43DA81708214873250337FF278D398C_1880768947.addTaint(taint);
            return varA43DA81708214873250337FF278D398C_1880768947;
            // ---------- Original Method ----------
            //return new MenuInflater(getThemedContext());
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.771 -0400", hash_original_method = "B2B8DF93D41CD214F77DE42BB625BB9F", hash_generated_method = "D752F7A338F47B01D2918C809D7C3001")
        @Override
        public Menu getMenu() {
Menu var10D851031FD6BA7C53B0186C7F1D9213_878873017 =             mMenu;
            var10D851031FD6BA7C53B0186C7F1D9213_878873017.addTaint(taint);
            return var10D851031FD6BA7C53B0186C7F1D9213_878873017;
            // ---------- Original Method ----------
            //return mMenu;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.772 -0400", hash_original_method = "55BDAC29AF3CEA463BE5A1AC1715A820", hash_generated_method = "503F2714757C75953FD76B0A42C6027D")
        @Override
        public void finish() {
            if(mActionMode != this)            
            {
                return;
            } //End block
            if(mWasHiddenBeforeMode)            
            {
                mDeferredDestroyActionMode = this;
                mDeferredModeDestroyCallback = mCallback;
            } //End block
            else
            {
                mCallback.onDestroyActionMode(this);
            } //End block
            mCallback = null;
            animateToMode(false);
            mContextView.closeMode();
            mActionView.sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
            mActionMode = null;
            if(mWasHiddenBeforeMode)            
            {
                hide();
            } //End block
            // ---------- Original Method ----------
            //if (mActionMode != this) {
                //return;
            //}
            //if (mWasHiddenBeforeMode) {
                //mDeferredDestroyActionMode = this;
                //mDeferredModeDestroyCallback = mCallback;
            //} else {
                //mCallback.onDestroyActionMode(this);
            //}
            //mCallback = null;
            //animateToMode(false);
            //mContextView.closeMode();
            //mActionView.sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
            //mActionMode = null;
            //if (mWasHiddenBeforeMode) {
                //hide();
            //}
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.772 -0400", hash_original_method = "AE3782F4A9FD5538AF0C8FC979CD15B1", hash_generated_method = "09CD291F58A645AF3A8E9CF913126DF5")
        @Override
        public void invalidate() {
            mMenu.stopDispatchingItemsChanged();
            try 
            {
                mCallback.onPrepareActionMode(this, mMenu);
            } //End block
            finally 
            {
                mMenu.startDispatchingItemsChanged();
            } //End block
            // ---------- Original Method ----------
            //mMenu.stopDispatchingItemsChanged();
            //try {
                //mCallback.onPrepareActionMode(this, mMenu);
            //} finally {
                //mMenu.startDispatchingItemsChanged();
            //}
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.772 -0400", hash_original_method = "73ABB077D09B369F1D81F6F51241E9AF", hash_generated_method = "42648F856761CF5F7860802E86ACC45A")
        public boolean dispatchOnCreate() {
            mMenu.stopDispatchingItemsChanged();
            try 
            {
                boolean varF1C88BDD71CAE111F59B32DE7A3CE40A_64772287 = (mCallback.onCreateActionMode(this, mMenu));
                                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1821489966 = getTaintBoolean();
                return var84E2C64F38F78BA3EA5C905AB5A2DA27_1821489966;
            } //End block
            finally 
            {
                mMenu.startDispatchingItemsChanged();
            } //End block
            // ---------- Original Method ----------
            //mMenu.stopDispatchingItemsChanged();
            //try {
                //return mCallback.onCreateActionMode(this, mMenu);
            //} finally {
                //mMenu.startDispatchingItemsChanged();
            //}
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.772 -0400", hash_original_method = "D05FE6927B0D6D37D4F8F8E54FD54AC2", hash_generated_method = "5D17C2BEE0021A167441AF485D9BBF01")
        @Override
        public void setCustomView(View view) {
            mContextView.setCustomView(view);
            mCustomView = new WeakReference<View>(view);
            // ---------- Original Method ----------
            //mContextView.setCustomView(view);
            //mCustomView = new WeakReference<View>(view);
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.772 -0400", hash_original_method = "EDCF93193B842CC10D4874A3847ED8B0", hash_generated_method = "0B481E4D7D00D46DF3473BC4C3600612")
        @Override
        public void setSubtitle(CharSequence subtitle) {
            addTaint(subtitle.getTaint());
            mContextView.setSubtitle(subtitle);
            // ---------- Original Method ----------
            //mContextView.setSubtitle(subtitle);
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.772 -0400", hash_original_method = "A400678911C36F7EF5F4C53636B8794A", hash_generated_method = "F236E381C71BD7CFB8F46FF718DAD380")
        @Override
        public void setTitle(CharSequence title) {
            addTaint(title.getTaint());
            mContextView.setTitle(title);
            // ---------- Original Method ----------
            //mContextView.setTitle(title);
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.772 -0400", hash_original_method = "47F5B9C8307FB4AF11C6A2812A14A860", hash_generated_method = "DDB782A097A230A52C943D779E143F37")
        @Override
        public void setTitle(int resId) {
            addTaint(resId);
            setTitle(mContext.getResources().getString(resId));
            // ---------- Original Method ----------
            //setTitle(mContext.getResources().getString(resId));
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.773 -0400", hash_original_method = "0B3678050067173199BE985BD486C4DF", hash_generated_method = "75C7A0750EFABC066454E1981F2746B1")
        @Override
        public void setSubtitle(int resId) {
            addTaint(resId);
            setSubtitle(mContext.getResources().getString(resId));
            // ---------- Original Method ----------
            //setSubtitle(mContext.getResources().getString(resId));
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.773 -0400", hash_original_method = "B7C7D06E8A777BBCD952F14E250A3EF1", hash_generated_method = "E076E187C65AED9C4BAB34C34265FF74")
        @Override
        public CharSequence getTitle() {
CharSequence var34D31794780ACEFCF0616B8B24AD4528_101836632 =             mContextView.getTitle();
            var34D31794780ACEFCF0616B8B24AD4528_101836632.addTaint(taint);
            return var34D31794780ACEFCF0616B8B24AD4528_101836632;
            // ---------- Original Method ----------
            //return mContextView.getTitle();
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.773 -0400", hash_original_method = "117FD7BAEF9CDB7B98041D8B7F2D9548", hash_generated_method = "3C9D26F3814825929A78B3F173A28D2C")
        @Override
        public CharSequence getSubtitle() {
CharSequence varC52A494AB21A52CC12DF26E45A90E06F_1612400593 =             mContextView.getSubtitle();
            varC52A494AB21A52CC12DF26E45A90E06F_1612400593.addTaint(taint);
            return varC52A494AB21A52CC12DF26E45A90E06F_1612400593;
            // ---------- Original Method ----------
            //return mContextView.getSubtitle();
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.773 -0400", hash_original_method = "6445ACEDBE83E3C2D5AD83B96CDE83BC", hash_generated_method = "0A5E4E21A15BDB2BC59382AAC65490A5")
        @Override
        public View getCustomView() {
View varECA05EFB052EFC69FA07832FA3F4A1D6_1603240573 =             mCustomView != null ? mCustomView.get() : null;
            varECA05EFB052EFC69FA07832FA3F4A1D6_1603240573.addTaint(taint);
            return varECA05EFB052EFC69FA07832FA3F4A1D6_1603240573;
            // ---------- Original Method ----------
            //return mCustomView != null ? mCustomView.get() : null;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.773 -0400", hash_original_method = "811C79E6E9EF223E39B6355E1D511C3E", hash_generated_method = "352404F2688D09D26CA1E0C5017269F2")
        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(item.getTaint());
            addTaint(menu.getTaint());
            if(mCallback != null)            
            {
                boolean var3FFB98B393CA4BB1089E3B773EEE7A13_1575290842 = (mCallback.onActionItemClicked(this, item));
                                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1948620133 = getTaintBoolean();
                return var84E2C64F38F78BA3EA5C905AB5A2DA27_1948620133;
            } //End block
            else
            {
                boolean var68934A3E9455FA72420237EB05902327_885116096 = (false);
                                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_947003468 = getTaintBoolean();
                return var84E2C64F38F78BA3EA5C905AB5A2DA27_947003468;
            } //End block
            // ---------- Original Method ----------
            //if (mCallback != null) {
                //return mCallback.onActionItemClicked(this, item);
            //} else {
                //return false;
            //}
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.773 -0400", hash_original_method = "FBD72BA67A5E56379A29D442239E7ED0", hash_generated_method = "8E80426A33290BFB819B8A6933C74555")
        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(allMenusAreClosing);
            addTaint(menu.getTaint());
            // ---------- Original Method ----------
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.773 -0400", hash_original_method = "7D886846B59E57EE3CE79C98862AC926", hash_generated_method = "164FE7D1F40F3A8694014B8604461AD1")
        public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(subMenu.getTaint());
            if(mCallback == null)            
            {
                boolean var68934A3E9455FA72420237EB05902327_92500405 = (false);
                                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_938560472 = getTaintBoolean();
                return var84E2C64F38F78BA3EA5C905AB5A2DA27_938560472;
            } //End block
            if(!subMenu.hasVisibleItems())            
            {
                boolean varB326B5062B2F0E69046810717534CB09_171305965 = (true);
                                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_484038936 = getTaintBoolean();
                return var84E2C64F38F78BA3EA5C905AB5A2DA27_484038936;
            } //End block
            new MenuPopupHelper(getThemedContext(), subMenu).show();
            boolean varB326B5062B2F0E69046810717534CB09_1376102094 = (true);
                        boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1900243333 = getTaintBoolean();
            return var84E2C64F38F78BA3EA5C905AB5A2DA27_1900243333;
            // ---------- Original Method ----------
            //if (mCallback == null) {
                //return false;
            //}
            //if (!subMenu.hasVisibleItems()) {
                //return true;
            //}
            //new MenuPopupHelper(getThemedContext(), subMenu).show();
            //return true;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "6B692B27380EC4C10CDFFF60DC2D0482", hash_generated_method = "2074465CD9A30183B1E5202C403E062E")
        public void onCloseSubMenu(SubMenuBuilder menu) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(menu.getTaint());
            // ---------- Original Method ----------
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "5641671D684852B976991F51D9F0AB3E", hash_generated_method = "82FA2BB4E2E298131994BE34001DFFC2")
        public void onMenuModeChange(MenuBuilder menu) {
            //DSFIXME:  CODE0009: Possible callback target function detected
            addTaint(menu.getTaint());
            if(mCallback == null)            
            {
                return;
            } //End block
            invalidate();
            mContextView.showOverflowMenu();
            // ---------- Original Method ----------
            //if (mCallback == null) {
                //return;
            //}
            //invalidate();
            //mContextView.showOverflowMenu();
        }

        
    }


    
    public class TabImpl extends ActionBar.Tab {
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_field = "1804C5EC7AAE0B28B15CFDA061D25829", hash_generated_field = "A287CCE930216100007A4CB68C5B72FA")

        private ActionBar.TabListener mCallback;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_field = "07496860B5C53FDD66588727158D5F73", hash_generated_field = "EE550655758D5D3DEE98B11CF176B67D")

        private Object mTag;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_field = "1A265556E59DF15CAEC4E55FB61E68C7", hash_generated_field = "FFB83F2A7C9A877B036AAEB7C21DDF6E")

        private Drawable mIcon;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_field = "39624508B9E3028D8A5DDA741D3E3DDE", hash_generated_field = "E579ED9F2DADADEB46C981CAFDEE5BA5")

        private CharSequence mText;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_field = "174B31BAC3CD311BC4B15E933A0A1C91", hash_generated_field = "F05F3F248129871C9794FE1C8A5F9774")

        private CharSequence mContentDesc;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_field = "63D25B66A694A0AFDF9B25838EF1ADAD", hash_generated_field = "C9775047302D5C5D2E659A086E1EFAE7")

        private int mPosition = -1;
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_field = "0F85706FF7ACEA880EA803CB0E63A48D", hash_generated_field = "58BF9FCCDFB47B7346A31DF060279244")

        private View mCustomView;
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "6BCFC14B7595CFA31D582BF6176C023E", hash_generated_method = "6BCFC14B7595CFA31D582BF6176C023E")
        public TabImpl ()
        {
            //Synthesized constructor
        }


                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "15C9157ADF1B79E512CAFF71050D7BD7", hash_generated_method = "09C31C354C24CFC11C17EFC92CEA155A")
        @Override
        public Object getTag() {
Object varD6A25044F8E609F6CD67330C1523D33D_1429553751 =             mTag;
            varD6A25044F8E609F6CD67330C1523D33D_1429553751.addTaint(taint);
            return varD6A25044F8E609F6CD67330C1523D33D_1429553751;
            // ---------- Original Method ----------
            //return mTag;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "BB14A4918D906598A88E3D6A8128EA77", hash_generated_method = "C9174688FEFFB7961A3EF1B2709FBC34")
        @Override
        public Tab setTag(Object tag) {
            mTag = tag;
Tab var72A74007B2BE62B849F475C7BDA4658B_490290376 =             this;
            var72A74007B2BE62B849F475C7BDA4658B_490290376.addTaint(taint);
            return var72A74007B2BE62B849F475C7BDA4658B_490290376;
            // ---------- Original Method ----------
            //mTag = tag;
            //return this;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "E24F48568604237F2413A93282202AA0", hash_generated_method = "E6E4924B9D2BCB0C350D8B04C62A6CAA")
        public ActionBar.TabListener getCallback() {
ActionBar.TabListener var31075088B2A2E9D45AA9874A2DA07598_535343453 =             mCallback;
            var31075088B2A2E9D45AA9874A2DA07598_535343453.addTaint(taint);
            return var31075088B2A2E9D45AA9874A2DA07598_535343453;
            // ---------- Original Method ----------
            //return mCallback;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "464280ABF8AEC5C77A1E9FBBDE9C172D", hash_generated_method = "B00313DC49915D0EFB58ACFD95218D34")
        @Override
        public Tab setTabListener(ActionBar.TabListener callback) {
            mCallback = callback;
Tab var72A74007B2BE62B849F475C7BDA4658B_1939138397 =             this;
            var72A74007B2BE62B849F475C7BDA4658B_1939138397.addTaint(taint);
            return var72A74007B2BE62B849F475C7BDA4658B_1939138397;
            // ---------- Original Method ----------
            //mCallback = callback;
            //return this;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.774 -0400", hash_original_method = "564891C70D3FD9FDA6B8DDF54A8A1C12", hash_generated_method = "1F0B050B9D20D2CECF1C0A670282D5F3")
        @Override
        public View getCustomView() {
View varC7562A89B84B6992F4B59CC6EA104F7F_265949711 =             mCustomView;
            varC7562A89B84B6992F4B59CC6EA104F7F_265949711.addTaint(taint);
            return varC7562A89B84B6992F4B59CC6EA104F7F_265949711;
            // ---------- Original Method ----------
            //return mCustomView;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "0927DBCB7C9D04ABD72A572D4EBE85DC", hash_generated_method = "6BE62C7DEB0F59AB858768E574713695")
        @Override
        public Tab setCustomView(View view) {
            mCustomView = view;
            if(mPosition >= 0)            
            {
                mTabScrollView.updateTab(mPosition);
            } //End block
Tab var72A74007B2BE62B849F475C7BDA4658B_419437310 =             this;
            var72A74007B2BE62B849F475C7BDA4658B_419437310.addTaint(taint);
            return var72A74007B2BE62B849F475C7BDA4658B_419437310;
            // ---------- Original Method ----------
            //mCustomView = view;
            //if (mPosition >= 0) {
                //mTabScrollView.updateTab(mPosition);
            //}
            //return this;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "AB0A301D41389C31F697384DEDEE9E45", hash_generated_method = "81E4630DA19F3E500BB1975F7199211D")
        @Override
        public Tab setCustomView(int layoutResId) {
            addTaint(layoutResId);
Tab var3AD958EEC155E2053A99346086309ED4_22321371 =             setCustomView(LayoutInflater.from(getThemedContext())
                    .inflate(layoutResId, null));
            var3AD958EEC155E2053A99346086309ED4_22321371.addTaint(taint);
            return var3AD958EEC155E2053A99346086309ED4_22321371;
            // ---------- Original Method ----------
            //return setCustomView(LayoutInflater.from(getThemedContext())
                    //.inflate(layoutResId, null));
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "ED78DC4ED6B2C6A65EDA3016CC1C1C4D", hash_generated_method = "5110205D25C3B5E42898550C39A7B8FB")
        @Override
        public Drawable getIcon() {
Drawable var664A3A1F0DCFF82F0A8D0B798A32C3B0_828159165 =             mIcon;
            var664A3A1F0DCFF82F0A8D0B798A32C3B0_828159165.addTaint(taint);
            return var664A3A1F0DCFF82F0A8D0B798A32C3B0_828159165;
            // ---------- Original Method ----------
            //return mIcon;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "88750DD9C7394A9CF6B657A7AF77F3EB", hash_generated_method = "46BBED1E561DE2F4D447D2E47AD29DFA")
        @Override
        public int getPosition() {
            int var2A02BDE12D932CD9DCA97106D487F8F5_1586266864 = (mPosition);
                        int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_906396341 = getTaintInt();
            return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_906396341;
            // ---------- Original Method ----------
            //return mPosition;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "3F4169F86E913801B39145F8B5F1CF4A", hash_generated_method = "9BE30D60DD8D509C2095B8721378C660")
        public void setPosition(int position) {
            mPosition = position;
            // ---------- Original Method ----------
            //mPosition = position;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "D3D56665E0CC0B43413FBFB4C720E96C", hash_generated_method = "69BEB0E1BADFF90878AAAD254F25B165")
        @Override
        public CharSequence getText() {
CharSequence var7F7ECB4B14362FFBA020956966B29A66_761596609 =             mText;
            var7F7ECB4B14362FFBA020956966B29A66_761596609.addTaint(taint);
            return var7F7ECB4B14362FFBA020956966B29A66_761596609;
            // ---------- Original Method ----------
            //return mText;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "E054EF134439DF27250AEE851BCF79C2", hash_generated_method = "CBA3D55F9B954B189B177B0B5E561BCB")
        @Override
        public Tab setIcon(Drawable icon) {
            mIcon = icon;
            if(mPosition >= 0)            
            {
                mTabScrollView.updateTab(mPosition);
            } //End block
Tab var72A74007B2BE62B849F475C7BDA4658B_1372201530 =             this;
            var72A74007B2BE62B849F475C7BDA4658B_1372201530.addTaint(taint);
            return var72A74007B2BE62B849F475C7BDA4658B_1372201530;
            // ---------- Original Method ----------
            //mIcon = icon;
            //if (mPosition >= 0) {
                //mTabScrollView.updateTab(mPosition);
            //}
            //return this;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.775 -0400", hash_original_method = "95CB6D2B1B424F33C131278F8466F7F6", hash_generated_method = "7283986A166C828F7EBC800FAB76E07C")
        @Override
        public Tab setIcon(int resId) {
            addTaint(resId);
Tab var2FBE166F87C3A0C6B3E33B0D28947E7C_739356642 =             setIcon(mContext.getResources().getDrawable(resId));
            var2FBE166F87C3A0C6B3E33B0D28947E7C_739356642.addTaint(taint);
            return var2FBE166F87C3A0C6B3E33B0D28947E7C_739356642;
            // ---------- Original Method ----------
            //return setIcon(mContext.getResources().getDrawable(resId));
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_method = "7912BF664F58A4FFF0C4F40EE3B2F065", hash_generated_method = "00EFEF1A9F9D55B1DFDAA237126B35B4")
        @Override
        public Tab setText(CharSequence text) {
            mText = text;
            if(mPosition >= 0)            
            {
                mTabScrollView.updateTab(mPosition);
            } //End block
Tab var72A74007B2BE62B849F475C7BDA4658B_364913361 =             this;
            var72A74007B2BE62B849F475C7BDA4658B_364913361.addTaint(taint);
            return var72A74007B2BE62B849F475C7BDA4658B_364913361;
            // ---------- Original Method ----------
            //mText = text;
            //if (mPosition >= 0) {
                //mTabScrollView.updateTab(mPosition);
            //}
            //return this;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_method = "593BCE41C6DE3623B8717D7BF5F8858F", hash_generated_method = "D2FB89913A90E95EEDBDA9F35FB31394")
        @Override
        public Tab setText(int resId) {
            addTaint(resId);
Tab varCCD965A46DE8A4609278F7D97BFC8760_130821558 =             setText(mContext.getResources().getText(resId));
            varCCD965A46DE8A4609278F7D97BFC8760_130821558.addTaint(taint);
            return varCCD965A46DE8A4609278F7D97BFC8760_130821558;
            // ---------- Original Method ----------
            //return setText(mContext.getResources().getText(resId));
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_method = "10821E84B4BC38351A9F8E96F9552B43", hash_generated_method = "5116FF2EF6D66BA582D4CB8F63A2B26D")
        @Override
        public void select() {
            selectTab(this);
            // ---------- Original Method ----------
            //selectTab(this);
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_method = "64BDEA3E085EE551443B27403AA215D4", hash_generated_method = "F6AFF23DF5CBDA25FAC7D6D911E62094")
        @Override
        public Tab setContentDescription(int resId) {
            addTaint(resId);
Tab var73646C06EB30D5640536C9B09C86769F_1499351456 =             setContentDescription(mContext.getResources().getText(resId));
            var73646C06EB30D5640536C9B09C86769F_1499351456.addTaint(taint);
            return var73646C06EB30D5640536C9B09C86769F_1499351456;
            // ---------- Original Method ----------
            //return setContentDescription(mContext.getResources().getText(resId));
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_method = "DB5D720F6039044C737BC09541B22E5C", hash_generated_method = "E4D21060A9AADEA42DCBC7DDADC5D20E")
        @Override
        public Tab setContentDescription(CharSequence contentDesc) {
            mContentDesc = contentDesc;
            if(mPosition >= 0)            
            {
                mTabScrollView.updateTab(mPosition);
            } //End block
Tab var72A74007B2BE62B849F475C7BDA4658B_876791241 =             this;
            var72A74007B2BE62B849F475C7BDA4658B_876791241.addTaint(taint);
            return var72A74007B2BE62B849F475C7BDA4658B_876791241;
            // ---------- Original Method ----------
            //mContentDesc = contentDesc;
            //if (mPosition >= 0) {
                //mTabScrollView.updateTab(mPosition);
            //}
            //return this;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_method = "637498C982C6D53C6EA2D802031901B7", hash_generated_method = "121AE1A6A7053FC30BD9C64C08ED8640")
        @Override
        public CharSequence getContentDescription() {
CharSequence var3DCB2933ED371B5C302E2A44FD22AF90_422962841 =             mContentDesc;
            var3DCB2933ED371B5C302E2A44FD22AF90_422962841.addTaint(taint);
            return var3DCB2933ED371B5C302E2A44FD22AF90_422962841;
            // ---------- Original Method ----------
            //return mContentDesc;
        }

        
    }


    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_field = "1DAB7A5A4134C7F1043C5C1922174FBD", hash_generated_field = "A902B2C04198EA61AF7D192A6DA0056B")

    private static final String TAG = "ActionBarImpl";
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_field = "F02C776F4DE519D379C4AB89E2307688", hash_generated_field = "EA2D03099DA79401859240172CAF107E")

    private static final int CONTEXT_DISPLAY_NORMAL = 0;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_field = "23048044AB5ACB8CF5850D95EA35E50F", hash_generated_field = "ABD10F36CA3AAC8E102F094B8BBAC3E9")

    private static final int CONTEXT_DISPLAY_SPLIT = 1;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:09.776 -0400", hash_original_field = "78D008574FF4877A72651C9D9100850E", hash_generated_field = "573F42E475AF4A1C9735B52864183D48")

    private static final int INVALID_POSITION = -1;
}

