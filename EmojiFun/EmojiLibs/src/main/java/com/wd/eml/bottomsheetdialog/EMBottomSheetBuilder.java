package com.wd.eml.bottomsheetdialog;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.MenuRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;

import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItemClickListener;

/**
 * author : wudu
 * time : 2017/8/18
 * <p>
 * 1.布局
 * 有title，文字
 * 有title，图片+文字
 * 有title，图片+文字，list布局形式
 * 有title，图片+文字，grid布局形式
 * <p>
 * 构造方案
 * 直接是context，使用默认的
 * 自带theme
 * 外围布局是CoordinatorLayout
 */

public class EMBottomSheetBuilder {
    //MODE
    private static final int MODE_LIST = 0;
    private static final int MODE_GRID = 1;
    //RES
    @StyleRes
    private int mTheme;
    //BACKGROUND
    @DrawableRes
    private int mBackgroundDrawable;
    @DrawableRes
    private int mItemDrawable;
    //COLOR
    private int mBackgroundColor;
    private int mDividerColor;
    private int mTitleTextColor;
    private int mItemTextColor;
    private int mItemColor;
    //LAYOUT
    private CoordinatorLayout mCoorLayout;
    private Menu mMenu;
    private Context mContext;
    //LISTENER
    private BottomSheetItemClickListener bottomSheetItemClickListener;


    public EMBottomSheetBuilder(Context context) {
        this(context,0);
    }

    public EMBottomSheetBuilder(Context context, @StyleRes int theme) {
        this.mContext = context;
        this.mTheme = theme;
    }

    public EMBottomSheetBuilder(Context context, CoordinatorLayout coordinatorLayout) {
       this(context,0,coordinatorLayout);
    }

    public EMBottomSheetBuilder(Context context, @StyleRes int theme, CoordinatorLayout coordinatorLayout) {
        this.mContext = context;
        this.mTheme = theme;
        this.mCoorLayout = coordinatorLayout;
    }


    public EMBottomSheetBuilder setMode(@IntRange(from = 0, to = 1) int mode) {
        return this;
    }

    public EMBottomSheetBuilder setMenus(@MenuRes int menu) {
        this.mMenu = new MenuBuilder(mContext);
        new SupportMenuInflater(mContext).inflate(menu, mMenu);
        return setMenus(mMenu);
    }

    public EMBottomSheetBuilder setMenus(Menu menu) {
        this.mMenu = menu;
        return this;
    }

    //DRAWABLE
    public EMBottomSheetBuilder setBackgroundDrawable(@DrawableRes int drawable) {
        this.mBackgroundDrawable = drawable;
        return this;
    }

    public EMBottomSheetBuilder setItemDrawable(@DrawableRes int drawable) {
        this.mItemDrawable = drawable;
        return this;
    }

    //COLOR
    public EMBottomSheetBuilder setBackgroundColor(@ColorInt int color) {
        this.mBackgroundColor = color;
        return this;
    }

    public EMBottomSheetBuilder setItemColor(@ColorInt int color) {
        this.mItemColor = color;
        return this;
    }

    public EMBottomSheetBuilder setItemTextColor(@ColorInt int color) {
        this.mItemTextColor = color;
        return this;
    }

    public EMBottomSheetBuilder setTitleTextColor(@ColorInt int color) {
        this.mTitleTextColor = color;
        return this;
    }

    public EMBottomSheetBuilder setDividerColor(@ColorInt int color) {
        this.mDividerColor = color;
        return this;
    }


}
