package com.wd.eml.bottomsheetdialog.interfaces;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 */

public class BottomSheetMenuItem implements BottomSheetItem {
    @ColorInt
    private int mTextColor;
    @ColorInt
    private int mTintColor;
    private int mItemBackground;

    private int mItemId;
    private String mItemText;
    private Drawable mItemIcon;
    private MenuItem mMenuItem;

    public BottomSheetMenuItem(MenuItem menuItem, @ColorInt int textColor, int itemBackground, @ColorInt int tintColor) {
        this.mMenuItem = menuItem;
        this.mTextColor = textColor;
        this.mItemBackground = itemBackground;
        this.mTintColor = tintColor;

        mItemText = menuItem.getTitle().toString();
        mItemId = menuItem.getItemId();
        mItemIcon = menuItem.getIcon();

        if (tintColor != -1) {
            mItemIcon = DrawableCompat.wrap(mItemIcon);
            DrawableCompat.setTint(mItemIcon, mTintColor);
        }
    }


    public int getTextColor() {
        return mTextColor;
    }

    public int getTintColor() {
        return mTintColor;
    }

    public int getItemBackground() {
        return mItemBackground;
    }

    public int getItemId() {
        return mItemId;
    }

    public Drawable getItemIcon() {
        return mItemIcon;
    }

    public MenuItem getMenuItem() {
        return mMenuItem;
    }

    @Override
    public String getText() {
        return mItemText;
    }
}
