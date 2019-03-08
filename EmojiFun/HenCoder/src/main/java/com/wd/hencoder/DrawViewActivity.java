package com.wd.hencoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrawViewActivity extends Activity implements View.OnClickListener {
    //圆形、复杂图形、直方图、扇形图
    private View view1, view2, view3, view4;
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);
        initView();
    }

    private void initView() {
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    private void viewGone() {
        view1.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        view4.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        viewGone();
        int i = v.getId();
        if (i == R.id.btn1) {
            view1.setVisibility(View.VISIBLE);

        } else if (i == R.id.btn2) {
            view2.setVisibility(View.VISIBLE);

        } else if (i == R.id.btn3) {
            view3.setVisibility(View.VISIBLE);

        } else if (i == R.id.btn4) {
            view4.setVisibility(View.VISIBLE);

        }

    }
}
