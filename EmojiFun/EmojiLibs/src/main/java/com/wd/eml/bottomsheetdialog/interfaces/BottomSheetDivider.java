package com.wd.eml.bottomsheetdialog.interfaces;

import android.support.annotation.DrawableRes;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 */

public class BottomSheetDivider implements BottomSheetItem {
    @DrawableRes
    private int mDividerDrawable;

    public BottomSheetDivider(int mDividerDrawable) {
        this.mDividerDrawable = mDividerDrawable;
    }

    public int getmDividerDrawable() {
        return mDividerDrawable;
    }

    @Override
    public String getText() {
        return "";
    }
}
