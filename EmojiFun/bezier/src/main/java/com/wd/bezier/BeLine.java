package com.wd.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * author : wudu
 * time : 2017/8/28
 * Hi,Baby.
 */

public class BeLine extends View {
    Point point = new Point(100,100);

    public BeLine(Context context) {
        super(context);
    }

    public BeLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5f);

        Path path = new Path();
        path.moveTo(100,100);
        //path.quadTo(point.x,point.y,600,600);
        path.rQuadTo(point.x,point.y,600,600);
        canvas.drawPath(path,paint);
        canvas.drawPoint(point.x,point.y,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            point.x = (int) event.getX();
            point.y = (int) event.getY();
            postInvalidate();
        }

        return true;
    }
}
