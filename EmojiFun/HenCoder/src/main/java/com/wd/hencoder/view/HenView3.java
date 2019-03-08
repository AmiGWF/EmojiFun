package com.wd.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class HenView3 extends View {
    Paint paint = new Paint();

    public HenView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HenView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.parseColor("#778800"));
        paint.setAntiAlias(true);


        //canvas.drawCircle(300, 300, 200, paint);


        //canvas.drawRect(600, 600, 400,400, paint);


        //canvas.drawLine(600, 800, 400,400, paint);

        //canvas.drawOval(100, 1200, 800,800, paint);

        //canvas.drawArc(100, 1600, 200,500,20,100,false,paint);

        canvas.drawArc(200, 600, 800, 1200, 0, 140, false, paint); // 绘制弧形

        canvas.drawArc(200, 200, 800, 800, 0, -90, true, paint); // 绘制扇形

        canvas.drawRoundRect(100,100,500,300,20,20,paint);
    }
}
