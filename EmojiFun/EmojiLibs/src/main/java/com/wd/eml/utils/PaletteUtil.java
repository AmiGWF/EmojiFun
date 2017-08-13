package com.wd.eml.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.graphics.Palette;

/**
 * Author : wudu
 * Time : 2017/8/13.
 * Tips :提供方法
 * 1.传递Bitmap和CallBack
 * 2.传递ImageID和CallBack
 * <p>
 * Palette.Swatch s = p.getVibrantSwatch();       //获取到充满活力的这种色调
 * Palette.Swatch s = p.getDarkVibrantSwatch();    //获取充满活力的黑
 * Palette.Swatch s = p.getLightVibrantSwatch();   //获取充满活力的亮
 * Palette.Swatch s = p.getMutedSwatch();           //获取柔和的色调
 * Palette.Swatch s = p.getDarkMutedSwatch();      //获取柔和的黑
 * Palette.Swatch s = p.getLightMutedSwatch();    //获取柔和的亮
 */

public class PaletteUtil implements Palette.PaletteAsyncListener {
    private static class PaletteUtilSingleton {
        public static PaletteUtil INSTANCE = new PaletteUtil();
    }

    public static PaletteUtil getIntance() {
        return PaletteUtilSingleton.INSTANCE;
    }


    private PaletteCallBack callBack;

    public synchronized void init(Bitmap bitmap, PaletteCallBack paletteCallBack) {
        if (bitmap == null) throw new NullPointerException("bitmap is null");
        Palette.from(bitmap).generate(PaletteUtil.this);
        this.callBack = paletteCallBack;
    }


    public synchronized void init(Resources resources, int resourcesId, PaletteCallBack paletteCallBack) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resourcesId);
        Palette.from(bitmap).generate(PaletteUtil.this);
        this.callBack = paletteCallBack;
    }


    @Override
    public void onGenerated(Palette palette) {
        Palette.Swatch swatch = null;
        if (palette.getVibrantSwatch() != null) {
            swatch = palette.getVibrantSwatch();
        } else if (palette.getLightVibrantSwatch() != null) {
            swatch = palette.getLightVibrantSwatch();
        }
        if (swatch == null)
            throw new NullPointerException("Method PaletteUtil.onGenerated palette.getVibrantSwatch is null");

        callBack.onCallBack(palette);

        callBack.onCallBack(swatch.getRgb(), swatch.getTitleTextColor());
    }


    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    public int setColorDeepened(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    /**
     * 颜色变浅处理
     *
     * @param RGBValues
     * @return
     */
    public int setColorLighter(int RGBValues) {
        int red = RGBValues >> 16 & 0xff;
        int green = RGBValues >> 8 & 0xff;
        int blue = RGBValues & 0xff;
        if (red == 0) {
            red = 10;
        }
        if (green == 0) {
            green = 10;
        }
        if (blue == 0) {
            blue = 10;
        }
        red = (int) Math.floor(red * (1 + 0.1));
        green = (int) Math.floor(green * (1 + 0.1));
        blue = (int) Math.floor(blue * (1 + 0.1));
        return Color.rgb(red, green, blue);
    }

    public interface PaletteCallBack {
        void onCallBack(Palette palette);

        void onCallBack(int rgb, int titleColor);
    }


}
