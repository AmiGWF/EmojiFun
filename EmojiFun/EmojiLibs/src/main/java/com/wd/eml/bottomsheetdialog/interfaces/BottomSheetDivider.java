package com.wd.eml.bottomsheetdialog.interfaces;

import android.support.annotation.DrawableRes;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 */

public class BottomSheetDivider implements BottomSheetItem {
    private int mDividerBackground;

    public BottomSheetDivider(int mDividerBackground) {
        this.mDividerBackground = mDividerBackground;
    }

    public int getmDividerBackground() {
        return mDividerBackground;
    }

    @Override
    public String getText() {
        return "";
    }
}
