package com.wd.eml.bottomsheetdialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 *
 * 1.布局
 * 有title，文字
 * 有title，图片+文字
 * 有title，图片+文字，list布局形式
 * 有title，图片+文字，grid布局形式
 *
 */

public class EMBottomSheetDialog extends BottomSheetDialog {

    public EMBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public EMBottomSheetDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutHeight();
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
