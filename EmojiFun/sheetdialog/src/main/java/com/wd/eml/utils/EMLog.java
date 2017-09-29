package com.wd.eml.utils;


import android.util.Log;

/**
 * author : wudu
 * time : 2017/8/16
 * Hi,Baby.
 */

public class EMLog {
    private static final String TAG = "EMLOG";

    private static boolean DEBUG = true;

    public static void setDebug(boolean DEBUG) {
        EMLog.DEBUG = DEBUG;
    }

    public static void i(Object mes) {
        if (DEBUG) {
            Log.i(TAG, ""+mes);
        }
    }

    public static void d(Object mes) {
        if (DEBUG) {
            Log.d(TAG, ""+mes);
        }
    }

    public static void e(Object mes) {
        if (DEBUG) {
            Log.e(TAG, ""+mes);
        }
    }
}
