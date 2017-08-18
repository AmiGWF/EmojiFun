package com.wd.eml.bottomsheetdialog.interfaces;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.MenuItem;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 */

public class BottomSheetMenuItem implements BottomSheetItem {
    @ColorInt
    private int mTextColor;
    //着色
    @ColorInt
    private int mTintColor;
    @DrawableRes
    private int mItemBackground;

    private int mItemId;
    private String mItemText;
    private Drawable mItemIcon;
    private MenuItem mMenuItem;

    public BottomSheetMenuItem(MenuItem mMenuItem, @ColorRes int mTextColor,  int mItemBackground, @ColorRes int mTintColor) {
        this.mItemId = mItemId;
        this.mItemText = mItemText;
        this.mItemIcon = mItemIcon;
        this.mMenuItem = mMenuItem;
        this.mBackground = mBackground;
    }

    @DrawableRes
    private int mBackground;


    @Override
    public String getText() {
        return null;
    }
}
