package com.wd.ac;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.wd.bezier.FlowerLayout;
import com.wd.bezier.R;

/**
 * author : wudu
 * time : 2017/9/15
 * Hi,Baby.
 */

public class BezierActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_layout);
        final FlowerLayout left_flower = (FlowerLayout) findViewById(R.id.left_flower);
        left_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left_flower.addHeart();
            }
        });

        final FlowerLayout right_flower = (FlowerLayout) findViewById(R.id.right_flower);
        right_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right_flower.addHeart();
            }
        });

    }
}
