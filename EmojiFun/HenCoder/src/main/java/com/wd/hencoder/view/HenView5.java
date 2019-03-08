package com.wd.hencoder.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;


/**
 * https://hencoder.com/ui-1-3/
 *
 * HenCoder Android 开发进阶：自定义 View 1-3 drawText() 文字的绘制
 */
public class HenView5 extends View {
    Paint paint = new Paint();
    Path path = new Path();

    public HenView5(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HenView5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.parseColor("#888888"));
        paint.setTextSize(60);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        //普通文字绘制
        String text =  "Hello Hencoder";
        //设置字体
        paint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(text,100,100,paint);

        //加删除线
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setStrikeThruText(true);
        canvas.drawText(text,100,200,paint);

        //加下划线
        paint.setTypeface(Typeface.SERIF);
        paint.setUnderlineText(true);
        canvas.drawText(text,100,300,paint);

        //文字按照path路径绘制
        path.addCircle(200,600,100,Path.Direction.CW);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path,paint);
        canvas.drawTextOnPath(text,path,0,0,paint);

        //文字换行
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(40);
        String text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        String text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz";
        StaticLayout staticLayout = new StaticLayout(text1,textPaint,600,Layout.Alignment.ALIGN_NORMAL,1,0,true);
        StaticLayout staticLayout2 = new StaticLayout(text2,textPaint,600,Layout.Alignment.ALIGN_NORMAL,1,0,true);
        canvas.save();
        canvas.translate(50,1000);
        staticLayout.draw(canvas);
        canvas.translate(0,200);
        staticLayout2.draw(canvas);
        canvas.restore();

        //根据不同语言显示文字
        String text3 = "今夜有雨，开车请谨慎，安全第一！";
        paint.reset();
        paint.setTextSize(50);
        paint.setTextLocale(Locale.CHINA);
        canvas.drawText(text3,400,500,paint);

        paint.setTextLocale(Locale.TAIWAN);
        canvas.drawText(text3,400,600,paint);

        paint.setTextLocale(Locale.JAPAN);
        canvas.drawText(text3,400,700,paint);

        //没有效果？？
        String text4 = "今夜有雨，今夜有雨，今夜有雨，今夜有雨";
        LocaleList localeList = new LocaleList(Locale.CHINA,Locale.CHINESE,Locale.TAIWAN, Locale.JAPAN);
        paint.setTextLocales(localeList);
        canvas.drawText(text4,10,800,paint);






    }
}
