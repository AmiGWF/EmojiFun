package com.wd.eml.bottomsheetdialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.wd.eml.R;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItemClickListener;
import com.wd.eml.bottomsheetdialog.utils.BottomSheetDialogUtil;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 *
 */

public class EMBottomSheetDialog extends BottomSheetDialog implements BottomSheetItemClickListener{
    private BottomSheetBehavior behavior;
    private BottomSheetBehavior.BottomSheetCallback callback;
    private AppBarLayout appBarLayout;
    OnCancelListener cancelListener;
    BottomSheetItemClickListener bottomSheetItemClickListener;

    boolean mClicked;
    boolean mRequestExpand;
    boolean mRequestCancel;
    boolean mRequestDismiss;
    private boolean mExpandOnStart;
    private boolean mDelayDismiss;

    public EMBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public EMBottomSheetDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setLayoutHeight();
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FrameLayout bottomSheet = (FrameLayout) findViewById(R.id.design_bottom_sheet);
        if(bottomSheet != null){
            behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setBottomSheetCallback(bottomSheetCallback);
            behavior.setSkipCollapsed(true);

            //IS LANDSPACE
            if(getContext().getResources().getBoolean(R.bool.tablet_landscape)){
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
                params.width = getContext().getResources().getDimensionPixelSize(R.dimen.bottomsheet_width);
                bottomSheet.setLayoutParams(params);
            }

            //Make sure the sheet doesn't overlap the appbar
            if(appBarLayout != null){
                if(appBarLayout.getHeight() == 0){
                    appBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            applyAppbarMargin(bottomSheet);
                        }
                    });
                }else{
                    applyAppbarMargin(bottomSheet);
                }
            }

            //FIX PEEKHEIGHT
            if(getContext().getResources().getBoolean(R.bool.landscape)){
                fixLandspacePeekHeight(bottomSheet);
            }

            //EXPAND
            if(mExpandOnStart){
                bottomSheet.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        if(behavior.getState() == BottomSheetBehavior.STATE_SETTLING && mRequestExpand){
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                                bottomSheet.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }else{
                                bottomSheet.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            }
                        }
                        mRequestExpand = true;
                    }
                });
            }
        }
    }

    @Override
    public void onBottomSheetItemClick(MenuItem item) {
        if(!mClicked){
            if(behavior != null){
                if(mDelayDismiss){
                    BottomSheetDialogUtil.delayDismiss(behavior);
                }else{
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }

            if(bottomSheetItemClickListener != null){
                bottomSheetItemClickListener.onBottomSheetItemClick(item);
            }
            mClicked = true;
        }
    }

    @Override
    public void setOnCancelListener(OnCancelListener listener) {
        super.setOnCancelListener(listener);
        this.cancelListener = listener;
    }

    @Override
    public void cancel() {
        mRequestCancel = true;
        super.cancel();
    }

    @Override
    public void dismiss() {
        mRequestDismiss = true;
        if(mRequestCancel){
            dismissWithAnimation();
        }else{
            super.dismiss();
        }
    }


    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(callback != null){
                callback.onStateChanged(bottomSheet,newState);
            }

            if(newState == BottomSheetBehavior.STATE_HIDDEN){
                behavior.setBottomSheetCallback(null);
                try {
                    EMBottomSheetDialog.super.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(!mClicked && ! mRequestDismiss && ! mRequestCancel && cancelListener != null){
                    cancelListener.onCancel(EMBottomSheetDialog.this);
                }
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            if(callback != null){
                callback.onSlide(bottomSheet,slideOffset);
            }
        }
    };

    private void dismissWithAnimation() {
        if(behavior != null){
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    private void fixLandspacePeekHeight(final View sheet){
        //on landspace ,we shouldn't use the 16:9 keyline alignment
        sheet.post(new Runnable() {
            @Override
            public void run() {
                behavior.setPeekHeight(sheet.getHeight() / 2);
            }
        });
    }

    private void applyAppbarMargin(View sheet){
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) sheet.getLayoutParams();
        params.topMargin = appBarLayout.getHeight();
        sheet.setLayoutParams(params);
    }


    public void setCallback(BottomSheetBehavior.BottomSheetCallback callback) {
        this.callback = callback;
    }

    public void setAppBarLayout(AppBarLayout appBarLayout) {
        this.appBarLayout = appBarLayout;
    }

    public void setmExpandOnStart(boolean mExpandOnStart) {
        this.mExpandOnStart = mExpandOnStart;
    }

    public void setmDelayDismiss(boolean mDelayDismiss) {
        this.mDelayDismiss = mDelayDismiss;
    }

    public void setBottomSheetItemClickListener(BottomSheetItemClickListener bottomSheetItemClickListener) {
        this.bottomSheetItemClickListener = bottomSheetItemClickListener;
    }

    public BottomSheetBehavior getBehavior() {
        return behavior;
    }

    /**
     * UPDATE LAYOUT HEIGHT
     */
    private void setLayoutHeight() {
        int screenHeight = getScreenHeight(getOwnerActivity());
        int statusBarHeight = getStatusBarHeight(getContext());
        int dialogHeight = screenHeight - statusBarHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams
                .MATCH_PARENT : dialogHeight);
    }

    private int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    private int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


}
