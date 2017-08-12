package com.wd.emoji.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.wd.emoji.R;

/**
 * author : wudu
 * time : 2017/8/10
 * Hi,Baby.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = "BaseAc:" + getClass().getSimpleName();
    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSDKVersion();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.ac_main_toolbar);
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
        ((View) t).setOnClickListener(this);
        return t;
    }

    @Override
    public void onClick(View view) {

    }
}
