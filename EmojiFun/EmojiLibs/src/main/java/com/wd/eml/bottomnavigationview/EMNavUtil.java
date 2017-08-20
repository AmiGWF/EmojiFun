package com.wd.eml.bottomnavigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.VolumeProviderCompat;

import java.lang.reflect.Field;

/**
 * author : wudu
 * time : 2017/8/11
 * About NavigationView/BottomNavigationView Settings
 */

public class EMNavUtil {

    /**
     * 利用反射去掉BottomNavigationView菜单数量大于三个时出现的动画
     * 查看源码可知：private boolean mShiftingMode = true;
     *
     * @param navView
     */
    @VolumeProviderCompat.ControlType
    public static void disShiftingMode(BottomNavigationView navView) {
        try {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) navView.getChildAt(0);
            Field var0 = menuView.getClass().getDeclaredField("mShiftingMode");
            var0.setAccessible(true);
            var0.setBoolean(menuView, false);
            var0.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView var1 = (BottomNavigationItemView) menuView.getChildAt(i);
                var1.setShiftingMode(false);
                var1.setChecked(var1.getItemData().isChecked());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置BottomNavigationView图片和文字的颜色，选中和未选中
     * @param context
     * @param navView
     * @param normal
     * @param pressed
     */
    public static void setBottomNavMenuColor(Context context, BottomNavigationView navView, int normal, int pressed) {
        if (navView == null)  throw  new NullPointerException("NavigationView is Null");
        int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
        int[] color = new int[]{ContextCompat.getColor(context, normal), ContextCompat.getColor(context, pressed)};
        ColorStateList stateList = new ColorStateList(states, color);
        navView.setItemTextColor(stateList);
        navView.setItemIconTintList(stateList);
    }

    public static void setNavMenuColor(Context context, NavigationView navView, int normal, int pressed) {
        if (navView == null)  throw  new NullPointerException("NavigationView is Null");
        int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
        int[] color = new int[]{ContextCompat.getColor(context, normal), ContextCompat.getColor(context, pressed)};
        ColorStateList stateList = new ColorStateList(states, color);
        navView.setItemTextColor(stateList);
        navView.setItemIconTintList(stateList);
    }

}
