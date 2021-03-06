package com.wd.eml.bottomsheetdialog.adapter;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import com.wd.eml.R;
import com.wd.eml.bottomsheetdialog.EMBottomSheetBuilder;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetDivider;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetHeader;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItem;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItemClickListener;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetMenuItem;
import com.wd.eml.utils.EMLog;

import java.lang.reflect.Method;
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
    private boolean isFromMenu;

    private int mode;

    private int mTitles;

    public EMBottomSheetAdapterBuilder(Context mContext) {
        this.mContext = mContext;
        sheetItemList = new ArrayList<>();
    }

    public void setMenu(Menu menu) {
        this.mMenu = menu;
        setMenuIconEnable(mMenu);
        isFromMenu = true;
    }

    private void setMenuIconEnable(Menu menu){
        try {
            Class clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMode(int mode) {
        this.mode = mode;
    }


    /**
     * addDividerItem
     **/
    public void addDividerItem(int dividerBackground) {
        if(sheetItemList != null && sheetItemList.size() > 0){
            sheetItemList.add(new BottomSheetDivider(dividerBackground));
        }
    }

    /**
     * addTitleItem
     */
    public void addTitleItem(String title, @ColorInt int textColor, @ColorInt int textBackground, @DrawableRes int
            icon) {
        sheetItemList.add(new BottomSheetHeader(title, textColor, textBackground, icon));
    }

    /**
     * addItem
     **/
    public void addItem(int id, String title,  @ColorInt int textColor,@DrawableRes int icon, @ColorInt int
            itemBackground, @ColorInt int tintColor) {
        if (mMenu == null) {
            mMenu = new MenuBuilder(mContext);
        }
        MenuItem menuItem = mMenu.add(Menu.NONE, id, Menu.NONE, title);
        if (icon != 0) {
            menuItem.setIcon(icon);
        }
        sheetItemList.add(new BottomSheetMenuItem(menuItem, textColor, itemBackground, tintColor));
    }


    public List<BottomSheetItem> getSheetItemList() {
        return sheetItemList;
    }

    /**
     * CREATE BOTTOMSHEETDIALOG VIEW
     */
    public View createSheetView(int titleTextColor, int itemTextColor, int titleBackground, int itemBackground, int
            itemBackgroundDrawable, int tintColor, int dividerBackground, BottomSheetItemClickListener
            bottomSheetItemClickListener) {
        //如果是外界传进来的menu 就直接创建item
        if (isFromMenu) {
            List<BottomSheetItem> menuList = createAdapterItems(titleTextColor, itemTextColor, titleBackground, itemBackground,
                    tintColor, dividerBackground);
            sheetItemList.addAll(menuList);
        }

        EMLog.d("sheetItemList size = "+sheetItemList.size());
        for (int i = 0;i< sheetItemList.size();i++){
            BottomSheetItem item = sheetItemList.get(i);
            if(item instanceof BottomSheetHeader){
                if(titleTextColor != 0 && ((BottomSheetHeader) item).getTextColor() !=0){

                }
            }
        }

        //如果不是即使用默认的xml
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View sheetDialog = mode == EMBottomSheetBuilder.MODE_LIST ? inflater.inflate(R.layout
                .bottomsheetdialog_def_list, null) : inflater.inflate(R.layout.bottomsheetdialog_def_grid, null);

        final RecyclerView recyclerView = (RecyclerView) sheetDialog.findViewById(R.id.bottomsheetdialog_recycleview);
        recyclerView.setHasFixedSize(true);

        if (itemBackgroundDrawable != 0) {
            sheetDialog.setBackgroundResource(itemBackgroundDrawable);
        } else if (itemBackground != 0) {
            sheetDialog.setBackgroundColor(itemBackground);
        }

        //IF JUST HAS ONLY ONE TITLE ITEM && IT'S THE FIRST ITEM,SHOW IN LIST
//        if (mTitles == 1 && mode == EMBottomSheetBuilder.MODE_LIST) {
//            BottomSheetItem titleSheetItem = sheetItemList.get(0);
//            View titleHeader = sheetDialog.findViewById(R.id.bottomsheetdialog_header);
//            titleHeader.setVisibility(View.VISIBLE);
//            ImageView titleIcon = (ImageView) sheetDialog.findViewById(R.id.sheet_header_icon);
//            TextView titleText = (TextView) sheetDialog.findViewById(R.id.sheet_header_text);
//            if (titleSheetItem instanceof BottomSheetHeader) {
//                titleText.setText(titleSheetItem.getText());
//                if (titleTextColor != 0) {
//                    titleText.setTextColor(titleTextColor);
//                }
//
//                if(titleBackground != 0){
//                    titleHeader.setBackgroundColor(titleBackground);
//                }
//
//                if(((BottomSheetHeader) titleSheetItem).getIcon() != 0){
//                    titleIcon.setImageResource(((BottomSheetHeader) titleSheetItem).getIcon());
//                    titleIcon.setVisibility(View.VISIBLE);
//                }else{
//                    titleIcon.setVisibility(View.GONE);
//                }
//                //WHY--BECAUSE HEADER TITLE SHOW
//                sheetItemList.remove(0);
//            }
//        }


        //SET RECYCLE ADAPTER
        final EMBottomSheetItemAdapter itemAdapter = new EMBottomSheetItemAdapter(mode, sheetItemList,
                bottomSheetItemClickListener);
        if (mode == EMBottomSheetBuilder.MODE_LIST) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(itemAdapter);
        } else if (mode == EMBottomSheetBuilder.MODE_GRID) {
            final int columns = mContext.getResources().getInteger(R.integer.bottomsheet_grid_columns);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, columns);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    float gridMargin = mContext.getResources().getDimensionPixelSize(R.dimen.grid_horizontal_margin_24);
                    itemAdapter.setmGridItemWidth((int) ((recyclerView.getWidth() - (columns - 1) * gridMargin) /
                            columns));
                    recyclerView.setAdapter(itemAdapter);
                }
            });

        } else {
            throw new IllegalArgumentException("The mode isn't allow,must use MODE_LIST or MODE_GRID");
        }

        return sheetDialog;
    }


    /**
     * CREATE BOTTOMSHEETDIALOG SINGLE ITEM
     */
    public List<BottomSheetItem> createAdapterItems(int titleTextColor, int itemTextColor, int titleBackground, int
            itemBackground, int tintColor, int dividerBackground) {
        List<BottomSheetItem> bottomSheetItems = new ArrayList<>();
        mTitles = 0;

        //子菜单
        boolean addedSubMenu = false;

        if (mMenu != null && mMenu.size() > 0) {
            for (int i = 0; i < mMenu.size(); i++) {
                MenuItem menuItem = mMenu.getItem(i);
                if (menuItem.isVisible()) {
                    if (menuItem.hasSubMenu()) {
                        SubMenu subMenu = menuItem.getSubMenu();
                        EMLog.d("submeu =  "+subMenu);

                        //1.grid没有子菜单，
                        if (mode == EMBottomSheetBuilder.MODE_GRID)
                            throw new IllegalArgumentException("MODE_GRID can't has submenus,Use MODE_LIST instead");

                        //2.是否有头部,可以有多个头部
                        EMLog.d("menuitem.title  =  "+menuItem.getTitle());
                        if (!TextUtils.isEmpty(menuItem.getTitle())) {
                            if (titleBackground != 0) {
                                bottomSheetItems.add(new BottomSheetHeader(menuItem.getTitle().toString(),
                                        titleTextColor));
                            } else {
                                bottomSheetItems.add(new BottomSheetHeader(menuItem.getTitle().toString(),
                                        titleTextColor, titleBackground, 0));
                            }

                            mTitles++;
                        }

                        //divider不能在第一行,并且需要添加了view
                        if (i != 0 && addedSubMenu) {
                            bottomSheetItems.add(new BottomSheetDivider(dividerBackground));
                        }

                        //3.添加item
                        if (subMenu != null) {
                            for (int j = 0; j < subMenu.size(); j++) {
                                EMLog.d("subMenu.getItem(j)  = "+subMenu.getItem(j));
                                MenuItem subMenuItem = subMenu.getItem(j);
                                if (subMenuItem.isVisible()) {
                                    bottomSheetItems.add(new BottomSheetMenuItem(subMenuItem, itemTextColor,
                                            itemBackground, tintColor));
                                    addedSubMenu = true;
                                }
                            }
                        }
                    } else {
                        bottomSheetItems.add(new BottomSheetMenuItem(menuItem, itemTextColor, itemBackground,
                                tintColor));
                    }
                }
            }
        }
        return bottomSheetItems;
    }
}
