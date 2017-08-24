package com.wd.eml.bottomsheetdialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wd.eml.R;
import com.wd.eml.bottomsheetdialog.adapter.EMBottomSheetAdapterBuilder;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItemClickListener;

/**
 * author : wudu
 * time : 2017/8/18
 */

public class EMBottomSheetBuilder {
    //MODE
    public static final int MODE_LIST = 0;
    public static final int MODE_GRID = 1;

    @StyleRes
    private int mTheme;
    private int mode;
    private Menu mMenu;
    private Context mContext;

    //RESOURCE
    private int mDividerColor;
    @DrawableRes
    private int mBackgroundDrawable;
    private int mBackgroundColor;

    private int mTintColor = -1;
    private int mTitleTextColor;
    private int mItemTextColor;

    private BottomSheetItemClickListener bottomSheetItemClickListener;
    private EMBottomSheetAdapterBuilder adapterBuilder;
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout appBarLayout;

    private boolean mDelayedDismiss = true;
    private boolean mExpandOnStart = false;




    public EMBottomSheetBuilder(Context context) {
        this(context,0);
    }

    public EMBottomSheetBuilder(Context context, @StyleRes int theme) {
        this.mContext = context;
        this.mTheme = theme;
        adapterBuilder  = new EMBottomSheetAdapterBuilder(context);
    }

    public EMBottomSheetBuilder(Context context, CoordinatorLayout coordinatorLayout) {
        this(context,0,coordinatorLayout);
    }

    public EMBottomSheetBuilder(Context context, @StyleRes int theme, CoordinatorLayout coordinatorLayout) {
        this.mContext = context;
        this.mTheme = theme;
        this.mCoordinatorLayout = coordinatorLayout;
        adapterBuilder  = new EMBottomSheetAdapterBuilder(context);
    }

    public EMBottomSheetBuilder setMode(@IntRange(from = 0, to = 1) int mode) {
        if(mode != MODE_LIST && mode != MODE_GRID){
            throw new IllegalArgumentException("Mode must be one of BottomSheetBuilder.MODE_LIST" +
                    " or BottomSheetBuilder.MODE_GRID");
        }
        this.mode = mode;
        adapterBuilder.setMode(mode);
        return this;
    }

    public EMBottomSheetBuilder setMenus(@MenuRes int menu) {
        this.mMenu = new MenuBuilder(mContext);
        new SupportMenuInflater(mContext).inflate(menu, mMenu);
        return setMenus(mMenu);
    }

    public EMBottomSheetBuilder setMenus(Menu menu) {
        this.mMenu = menu;
        adapterBuilder.setMenu(menu);
        return this;
    }

    //ADD TITLE ITEM
    public EMBottomSheetBuilder addTitleItem(@StringRes int titleId){
        return addTitleItem(mContext.getString(titleId));
    }

    public EMBottomSheetBuilder addTitleItem(String title){
        return addTitleItem(title,0);
    }

    public EMBottomSheetBuilder addTitleItem(String title, @ColorInt int textColor) {
        return addTitleItem(title,textColor,0);
    }

    public EMBottomSheetBuilder addTitleItem(String title, @ColorInt int textColor, @DrawableRes  int icon) {
        return addTitleItem(title,textColor,0,icon);
    }

    public EMBottomSheetBuilder addTitleItem(String title, @ColorInt int textColor,@ColorInt int titleBackground,@DrawableRes int icon) {
        if (mode == MODE_GRID) {
            throw new IllegalStateException("You can't add a title with MODE_GRID. " +
                    "Use MODE_LIST instead");
        }
        adapterBuilder.addTitleItem(title,textColor,titleBackground,icon);
        return this;
    }

    //ADD DIVIDER ITEM
    public EMBottomSheetBuilder addDividerItem(@ColorInt int color) {
        if (mode == EMBottomSheetBuilder.MODE_GRID) {
            throw new IllegalStateException("You can't add a divider with MODE_GRID. " +
                    "Use MODE_LIST instead");
        }
        if(color != 0){
            this.mDividerColor = color;
        }
        adapterBuilder.addDividerItem(mDividerColor);
        return this;
    }

    public EMBottomSheetBuilder setDividerColor(@ColorInt int color) {
        this.mDividerColor = color;
        return this;
    }

    //ADD ITEM
    public EMBottomSheetBuilder addItem(int id, String title) {
        return addItem(id,title,0);
    }

    public EMBottomSheetBuilder addItem(int id, @StringRes int titleId) {
        return addItem(id,mContext.getString(titleId),0);
    }

    public EMBottomSheetBuilder addItem(int id, @StringRes int titleId,@DrawableRes int icon) {
        return addItem(id,mContext.getString(titleId),icon);
    }

    public EMBottomSheetBuilder addItem(int id, String title,@DrawableRes int icon) {
        return addItem(id,title,0,icon);
    }

    public EMBottomSheetBuilder addItem(int id, int titleId, @ColorInt int textColor,@DrawableRes int icon) {
        return addItem(id,mContext.getString(titleId),textColor,icon);
    }

    public EMBottomSheetBuilder addItem(int id, String title, @ColorInt int textColor,@DrawableRes int icon) {
        return addItem(id,title,textColor,icon,0);
    }


    public EMBottomSheetBuilder addItem(int id, String title, @ColorInt int textColor, @DrawableRes int icon,@ColorInt int itemBackground) {
        addItem(id,title,textColor,icon,itemBackground,0);
        return this;
    }

    public EMBottomSheetBuilder addItem(int id, String title, @ColorInt int textColor,
                                        @DrawableRes int icon,@ColorInt int itemBackground, @ColorInt int tintColor) {
        adapterBuilder.addItem(id,title,textColor,icon,itemBackground,tintColor);
        return this;
    }

    /**
     * CREATE BOTTOMSHEET,MUST APPLY COORDINATORLAYOUT
     */
    public View createSheetView(){

        if(mMenu == null && adapterBuilder.getSheetItemList().isEmpty()){
            throw new IllegalStateException("You need to provide at least one Menu " +
                    "or an item with addItem");
        }

        if (mCoordinatorLayout == null) {
            throw new IllegalStateException("You need to provide a coordinatorLayout" +
                    "so the view can be placed on it");
        }

        View bottomSheet = adapterBuilder.createSheetView(mTitleTextColor,mItemTextColor,mTitleTextColor,
                mBackgroundColor,mBackgroundDrawable,mTintColor,mDividerColor,bottomSheetItemClickListener);

        ViewCompat.setElevation(bottomSheet, mContext.getResources()
                .getDimensionPixelSize(R.dimen.bottomsheet_elevation));

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            bottomSheet.findViewById(R.id.fakeShadow).setVisibility(View.GONE);
//        }

        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.setBehavior(new BottomSheetBehavior());

        //FOR LANDSPACE SCREEN
        if (mContext.getResources().getBoolean(R.bool.tablet_landscape)) {
            layoutParams.width = mContext.getResources()
                    .getDimensionPixelSize(R.dimen.bottomsheet_width);
        }

        mCoordinatorLayout.addView(bottomSheet,layoutParams);
        mCoordinatorLayout.postInvalidate();
        return bottomSheet;
    }


    public EMBottomSheetDialog createSheetDialog(){
        if(mMenu == null && adapterBuilder.getSheetItemList().isEmpty()){
            throw new IllegalStateException("You need to provide at least one Menu " +
                    "or an item with addItem");
        }

        EMBottomSheetDialog dialog  = mTheme == 0 ?
                new EMBottomSheetDialog(mContext,R.style.BottomSheetBuilder_DialogStyle)
                :new EMBottomSheetDialog(mContext,mTheme);

        if (mTheme != 0) {
            setupThemeColors(mContext.obtainStyledAttributes(mTheme, new int[]{
                    R.attr.bottomSheetBuilderBackgroundColor,
                    R.attr.bottomSheetBuilderItemTextColor,
                    R.attr.bottomSheetBuilderTitleTextColor}));
        } else {
            setupThemeColors(mContext.getTheme().obtainStyledAttributes(new int[]{
                    R.attr.bottomSheetBuilderBackgroundColor,
                    R.attr.bottomSheetBuilderItemTextColor,
                    R.attr.bottomSheetBuilderTitleTextColor,}));
        }

        View sheetViewDialog = adapterBuilder.createSheetView(mTitleTextColor,mItemTextColor,
                mTitleTextColor,mBackgroundColor,mBackgroundDrawable,
                mTintColor,mDividerColor,dialog);

        //sheetViewDialog.findViewById(R.id.fakeShadow).setVisibility(View.GONE);
        dialog.setAppBarLayout(appBarLayout);
        dialog.setmExpandOnStart(mExpandOnStart);
        dialog.setmDelayDismiss(mDelayedDismiss);
        dialog.setBottomSheetItemClickListener(bottomSheetItemClickListener);

        //TABLE LANDSPACE
        if(mContext.getResources().getBoolean(R.bool.tablet_landscape)){
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mContext.getResources().getDimensionPixelSize(R.dimen.bottomsheet_width),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setContentView(sheetViewDialog,params);
            dialog.setContentView(sheetViewDialog);
        }else{
            dialog.setContentView(sheetViewDialog);
        }

        return  dialog;
    }

    @SuppressWarnings("ResourceType")
    private void setupThemeColors(TypedArray typedArray) {
        int backgroundRes = typedArray.getResourceId(0, mBackgroundColor);
        int textRes = typedArray.getResourceId(1, mItemTextColor);
        int titleRes = typedArray.getResourceId(2, mTitleTextColor);

        if (backgroundRes != mBackgroundColor) {
            mBackgroundColor = ContextCompat.getColor(mContext, backgroundRes);
        }

        if (titleRes != mTitleTextColor) {
            mTitleTextColor = ContextCompat.getColor(mContext, titleRes);
        }

        if (textRes != mItemTextColor) {
            mItemTextColor = ContextCompat.getColor(mContext, textRes);
        }

        typedArray.recycle();
    }


    //SET
    public EMBottomSheetBuilder setBackgroundDrawable(@DrawableRes int drawable) {
        this.mBackgroundDrawable = drawable;
        return this;
    }

    public EMBottomSheetBuilder setBackgroundColor(@ColorInt int color) {
        this.mBackgroundColor = color;
        return this;
    }
    public EMBottomSheetBuilder setBackgroundColorResource(@ColorRes int background) {
        mBackgroundColor = ResourcesCompat.getColor(mContext.getResources(), background,
                mContext.getTheme());
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

    public EMBottomSheetBuilder setTintColor(int mTintColor) {
        this.mTintColor = mTintColor;
        return this;
    }

    public EMBottomSheetBuilder setDelayedDismiss(boolean mDelayedDismiss) {
        this.mDelayedDismiss = mDelayedDismiss;
        return this;
    }

    public EMBottomSheetBuilder setExpandOnStart(boolean mExpandOnStart) {
        this.mExpandOnStart = mExpandOnStart;
        return this;
    }

    public EMBottomSheetBuilder setAppBarLayout(AppBarLayout appBarLayout) {
        this.appBarLayout = appBarLayout;
        return this;
    }

    public EMBottomSheetBuilder setBottomSheetItemClickListener(BottomSheetItemClickListener bottomSheetItemClickListener) {
        this.bottomSheetItemClickListener = bottomSheetItemClickListener;
        return this;
    }
}
