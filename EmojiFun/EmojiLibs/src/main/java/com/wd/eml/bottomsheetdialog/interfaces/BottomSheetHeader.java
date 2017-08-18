package com.wd.eml.bottomsheetdialog.interfaces;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 */

public class BottomSheetHeader implements BottomSheetItem {
    private String mText;

    @ColorInt
    private int mTextColor;

    @DrawableRes
    private int mIcon;

    public BottomSheetHeader(String mText, int mTextColor) {
        this(mText, mTextColor, 0);
    }

    public BottomSheetHeader(String mText, int mTextColor, int mIcon) {
        this.mText = mText;
        this.mTextColor = mTextColor;
        this.mIcon = mIcon;
    }

    @Override
    public String getText() {
        return mText;
    }
}
