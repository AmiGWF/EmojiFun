package com.wd.emojiui.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;


/**
 * author : wudu
 * time : 2017/8/10
 * Hi,Baby.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = "BaseAc:" + getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSDKVersion();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }


    /**
     * SET STATUSBAR PROPERTY BY SDK VERSION
     */
    private void setSDKVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View
                        .SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }

    /**
     * SET TOOLBAR/STATUSBAR COLOR
     */
    public void setStatusBar() {
    }


    /**
     * FIND VIEW BY ID CAST TO T
     */
    @SuppressWarnings("unchecked")
    public <T> T findView(int id) {
        return (T) findViewById(id);
    }

    /**
     * CAST AND SET CLICK LISTENER
     */
    public <T> T setClick(int id) {
        T t = findView(id);
        ((View) t).setOnClickListener(BaseActivity.this);
        return t;
    }

    @Override
    public void onClick(View view) {

    }
}
