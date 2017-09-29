package com.wd.eml.bottomsheetdialog.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.BottomSheetBehavior;

/**
 * author : wudu
 * time : 2017/8/22
 * Hi,Baby.
 */

public class BottomSheetDialogUtil {
    public static final String SAVED_STATE = "saved_behavior_state";

    public static void delayDismiss(final BottomSheetBehavior behavior) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        }, 300);
    }

    public static void saveState(Bundle outState, BottomSheetBehavior behavior) {
        if (outState != null) {
            outState.putInt(SAVED_STATE, behavior.getState());
        }
    }

    public static void restoreState(final Bundle savedInstanceState, final BottomSheetBehavior behavior) {
        if (savedInstanceState != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int state = savedInstanceState.getInt(SAVED_STATE);
                    if (state == BottomSheetBehavior.STATE_EXPANDED && behavior != null) {
                        behavior.setState(state);
                    }
                }
            }, 300);
        }
    }
}
