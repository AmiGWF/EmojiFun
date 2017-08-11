package com.wd.eml.utils;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * author : wudu
 * time : 2017/8/11
 * 利用反射去掉BottomNavigationView菜单数量大于三个时出现的动画
 * 查看源码可知：private boolean mShiftingMode = true;
 */

public class EMNavUtil {
    public static void disShiftingMode(BottomNavigationView navView){
        try {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) navView.getChildAt(0);
            Field var0 =  menuView.getClass().getDeclaredField("mShiftingMode");
            var0.setAccessible(true);
            var0.setBoolean(menuView,false);
            var0.setAccessible(false);
            for (int i = 0;i < menuView.getChildCount();i++){
                BottomNavigationItemView var1 = (BottomNavigationItemView) menuView.getChildAt(i);
                var1.setShiftingMode(false);
                var1.setChecked(var1.getItemData().isChecked());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
