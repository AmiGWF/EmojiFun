package com.wd.eml.bottomsheetdialog.adapter;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.Menu;

import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetHeader;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItem;

import java.util.ArrayList;
import java.util.List;

/**
 * author : wudu
 * time : 2017/8/18
 * Hi,Baby.
 */

public class EMBottomSheetAdapterBuilder {
    private Context mContext;

    private List<BottomSheetItem> sheetItemList;

    private Menu mMenu;

    private int mode;

    public EMBottomSheetAdapterBuilder(Context mContext) {
        this.mContext = mContext;
        sheetItemList = new ArrayList<>();
    }

    public void setMenu(Menu menu) {
        this.mMenu = menu;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void addTitleItem(String title, int color) {
        addTitleItem(title, color, 0);
    }

    public void addTitleItem(String title, int color, int icon) {
        sheetItemList.add(new BottomSheetHeader(title, color, icon));
    }

    private void addItem(int id, String title) {
        sheetItemList.add(new BottomSheetItem() {
            @Override
            public String getText() {
                return null;
            }
        })
    }

    private void addItem(int id, @StringRes int titleId) {
    }

    private void addItem(int id, String title, @ColorRes int titleColor) {

    }

    private void addItem(int id, int titleId, @ColorRes int titleColor) {

    }

    private void addItemWithIcon(int id, String title, @DrawableRes int icon) {

    }

    private void addItemWithIcon(int id, @StringRes int titleId, @DrawableRes int icon) {

    }


}
