package com.wd.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class HenView2 extends View {
    Paint paint = new Paint();

    public HenView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HenView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        String[] colors = {"#F44330","#FFC107","#009688","#9C27B0"};

        int[] data = {30,40,50,120};

        RectF rectF = new RectF(-300,-300,300,300);

        int startAngle = -90;

        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(30);

        canvas.save();

        canvas.translate(400,500);
        paint2.setColor(Color.parseColor("#EEEEEE"));
        canvas.drawCircle(0,0,300,paint2);

        for(int i=0;i<data.length;i++){
            paint2.setColor(Color.parseColor(colors[i]));
            canvas.drawArc(rectF,startAngle,data[i],false,paint2);
            startAngle +=data[i];
        }


        //画文字
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.BLACK);
        paint2.setTextSize(48);
        canvas.drawText("1242calories",-150,0,paint2);
        canvas.drawText("burned",-150,50,paint2);


    }
}
