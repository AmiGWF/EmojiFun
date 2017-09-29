package com.wd.eml.bottomsheetdialog.interfaces;

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

    public int getDividerBackground() {
        return mDividerBackground;
    }

    @Override
    public String getText() {
        return "";
    }
}
