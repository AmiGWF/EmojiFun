package com.wd.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class HenView4 extends View {
    Paint paint = new Paint();
    Path path = new Path();

    public HenView4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HenView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);


        //画扇形
        paint.setColor(Color.GRAY);
        canvas.drawArc(200,200,820,820,0,17,true,paint);

        paint.setColor(Color.GREEN);
        canvas.drawArc(200,200,820,820,20,37,true,paint);

        paint.setColor(Color.BLUE);
        canvas.drawArc(200,200,820,820,60,110,true,paint);

        paint.setColor(Color.RED);
        canvas.drawArc(180,180,800,800,170,120,true,paint);

        paint.setColor(Color.YELLOW);
        canvas.drawArc(200,200,820,820,290,47,true,paint);

        paint.setColor(Color.parseColor("#001199"));
        canvas.drawArc(200,200,820,820,340,17,true,paint);

        //画线
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(5);
        path.moveTo(730,720);
        path.rLineTo(100,100);
        path.rLineTo(100,0);
        canvas.drawPath(path,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(36);
        canvas.drawText("绿色",835,800,paint);





    }
}
