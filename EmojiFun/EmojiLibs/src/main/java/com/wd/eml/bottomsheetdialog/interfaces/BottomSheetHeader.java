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

    @ColorInt
    private int mTextBackground;

    @DrawableRes
    private int mIcon;



    public BottomSheetHeader(String mText, @ColorInt int mTextColor) {
        this(mText, mTextColor, -1);
    }

    public BottomSheetHeader(String mText, @ColorInt int mTextColor, @DrawableRes int mIcon) {
       this(mText,mTextColor,-1,mIcon);
    }

    public BottomSheetHeader(String mText, @ColorInt int mTextColor, @ColorInt int mTextBackground,@DrawableRes int mIcon) {
        this.mText = mText;
        this.mTextColor = mTextColor;
        this.mIcon = mIcon;
        this.mTextBackground = mTextBackground;
    }

    @Override
    public String getText() {
        return mText;
    }

    public int getmTextColor() {
        return mTextColor;
    }

    public int getmTextBackground() {
        return mTextBackground;
    }

    public int getmIcon() {
        return mIcon;
    }
}
