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
    private int mBackground;

    @DrawableRes
    private int mIcon;


    public BottomSheetHeader(String text, @ColorInt int textColor) {
        this(text, textColor, 0);
    }

    public BottomSheetHeader(String text, @ColorInt int textColor, @DrawableRes int mIcon) {
        this(text, textColor, 0, mIcon);
    }

    public BottomSheetHeader(String text, @ColorInt int textColor, @ColorInt int background, @DrawableRes int icon) {
        this.mText = text;
        this.mTextColor = textColor;
        this.mIcon = icon;
        this.mBackground = background;
    }

    @Override
    public String getText() {
        return mText;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getBackground() {
        return mBackground;
    }

    public int getIcon() {
        return mIcon;
    }
}
