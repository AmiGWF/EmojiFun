package com.wd.eml.bottomsheetdialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
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
    public static final int MODE_LIST = 0;
    public static final int MODE_GRID = 1;
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
    private int mTintColor;
    private int mTitleTextColor;
    private int mItemTextColor;
    private int mItemBackgroundColor;
    //LAYOUT
    private CoordinatorLayout mCoorLayout;
    private int mode;
    private Menu mMenu;
    private Context mContext;
    //LISTENER
    private BottomSheetItemClickListener bottomSheetItemClickListener;
    private EMBottomSheetAdapterBuilder adapterBuilder;
    private CoordinatorLayout coordinatorLayout;

    private boolean mDelayedDismiss = true;
    private boolean mExpandOnStart = false;
    private AppBarLayout appBarLayout;

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
        this.mCoorLayout = coordinatorLayout;
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

    public EMBottomSheetBuilder setItemBackgroundColor(@ColorInt int color) {
        this.mItemBackgroundColor = color;
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

    //ADD DIVIDER ITEM
    public EMBottomSheetBuilder addDividerItem() {
        if (mode == EMBottomSheetBuilder.MODE_GRID) {
            throw new IllegalStateException("You can't add a divider with MODE_GRID. " +
                    "Use MODE_LIST instead");
        }
        adapterBuilder.addDividerItem(mDividerColor);
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

    public EMBottomSheetBuilder addTitleItem(String title, @ColorInt int textColor,@ColorInt int textBackground,@DrawableRes int icon) {
        if (mode == MODE_GRID) {
            throw new IllegalStateException("You can't add a title with MODE_GRID. " +
                    "Use MODE_LIST instead");
        }
        adapterBuilder.addTitleItem(title,textColor,textBackground,icon);
        return this;
    }


    //ADD ITEM
    public EMBottomSheetBuilder addItem(int id, String title) {
        return addItemWithIcon(id,title,0);
    }

    public EMBottomSheetBuilder addItem(int id, @StringRes int titleId) {
        return addItemWithIcon(id,mContext.getString(titleId),0);
    }

    public EMBottomSheetBuilder addItem(int id, int titleId, @ColorInt int textColor) {
        return addItemWithIcon(id,mContext.getString(titleId),0,textColor,0,0);
    }

    public EMBottomSheetBuilder addItem(int id, String title, @ColorInt int textColor) {
        return addItemWithIcon(id,title,0,textColor,0,0);
    }

    public EMBottomSheetBuilder addItemWithIcon(int id, String title, @DrawableRes int icon) {
        return addItemWithIcon(id,title,icon,0,0,0);
    }

    public EMBottomSheetBuilder addItemWithIcon(int id, @StringRes int titleId, @DrawableRes int icon) {
        return addItemWithIcon(id,mContext.getString(titleId),icon,0,0,0);
    }

    public EMBottomSheetBuilder addItemWithIcon(int id, @StringRes int titleId, @DrawableRes int icon,
                                @ColorInt int textColor, @ColorInt int itemBackground, @ColorInt int tintColor) {
        return addItemWithIcon(id,mContext.getString(titleId),icon,textColor,itemBackground,tintColor);
    }

    public EMBottomSheetBuilder addItemWithIcon(int id, String title, @DrawableRes int icon,
                                @ColorInt int textColor, @ColorInt int itemBackground, @ColorInt int tintColor) {
        adapterBuilder.addItem(id,title,icon,textColor,itemBackground,tintColor);
        return this;
    }


    /**
     * CREATE BOTTOMSHEET,MUST APPLY COORDINATORLAYOUT
     */
    public View createBottomSheet(){

        if(mMenu == null && adapterBuilder.getSheetItemList().isEmpty()){
                throw new IllegalStateException("You need to provide at least one Menu " +
                        "or an item with addItem");
        }

        if (coordinatorLayout == null) {
                throw new IllegalStateException("You need to provide a coordinatorLayout" +
                        "so the view can be placed on it");
        }

        View bottomSheet = adapterBuilder.createSheetView(mTitleTextColor,mItemTextColor,mTitleTextColor,
                mBackgroundColor,mBackgroundDrawable,mTintColor,mDividerColor,bottomSheetItemClickListener);

//        ViewCompat.setElevation(sheet, mContext.getResources()
//                .getDimensionPixelSize(R.dimen.bottomsheet_elevation));
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            sheet.findViewById(R.id.fakeShadow).setVisibility(View.GONE);
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

        coordinatorLayout.addView(bottomSheet,layoutParams);
        coordinatorLayout.postInvalidate();
        return bottomSheet;
    }


    public EMBottomSheetDialog createBottomSheetMenuDialog(){
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
}
