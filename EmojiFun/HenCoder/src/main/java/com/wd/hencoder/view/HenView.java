package com.wd.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.wd.hencoder.R;

public class HenView extends View {
    Paint paint = new Paint();

    public HenView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//
//        //实心圆
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(300, 300, 200, paint);
//
//        //空心圆
//        paint.setStrokeWidth(20f);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(300,800,200,paint);
//
//        //嵌套圆环
//        paint.setColor(Color.parseColor("#000190"));
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(300,1400,200,paint);
//
//        paint.setColor(Color.parseColor("#779987"));
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(300,1400,150,paint);
//
//        paint.setColor(Color.parseColor("#779900"));
//        canvas.drawCircle(300,1400,100,paint);


//        //线性渐变
//        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"), Color.parseColor("#26E891"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawRect(100, 100, 500,500, paint);
//        //渐变色的文字
//        paint.setTextSize(56);
//        canvas.drawText("HenrCode",200,200,paint);
//
//        //辐射渐变
//        Shader shader1 = new RadialGradient(300,700,120,Color.parseColor("#228892"),Color.parseColor("#AA00FF"),Shader.TileMode.REPEAT);
//        paint.setShader(shader1);
////        canvas.drawCircle(300,700,250,paint);
//        canvas.drawRect(0,200,700,1000,paint);
//
//        //扫描渐变
//        Shader shader2 = new SweepGradient(300,1000,Color.parseColor("#ff8811"),Color.parseColor("#358520"));
//        paint.setShader(shader2);
//        canvas.drawCircle(300,1000,300,paint);
//
//        //图片着色
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bfx);
//        Shader shader3 = new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//        paint.setShader(shader3);
//        canvas.drawCircle(600,300,300,paint);
//
//        //混合着色--没效果
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.bfx);
//        Shader shader4 = new BitmapShader(bitmap1,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.qq);
//        Shader shader5 = new BitmapShader(bitmap2,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//
//        Shader shader6 = new ComposeShader(shader4,shader5,PorterDuff.Mode.SRC_OVER);
//        paint.setShader(shader6);
//        canvas.drawCircle(200,200,700,paint);

        //模拟光照
//        ColorFilter colorFilter = new LightingColorFilter(0xff22ff,0xAAAA00);
//        paint.setColorFilter(colorFilter);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bfx);
//        canvas.drawBitmap(bitmap,50,50,paint);


//        ColorFilter colorFilter1 = new PorterDuffColorFilter(Color.parseColor("#22220011"),PorterDuff.Mode.SRC_IN);
//        paint.setColorFilter(colorFilter1);
//        canvas.drawBitmap(bitmap,0,200,paint);

//        int saved = canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG);
//        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bfx);
//        canvas.drawBitmap(bitmap,0,0,paint);
//        paint.setXfermode(xfermode);
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.em_night_icon);
//        canvas.drawBitmap(bitmap2,0,0,paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(saved);

        //画线条
        paint.setColor(Color.parseColor("#AA8890"));
        paint.setStrokeWidth(30);
//        //平头
//        paint.setStrokeJoin(Paint.Join.BEVEL);
//        canvas.drawLine(50,20,500,20,paint);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        //圆头
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        canvas.drawLine(50,100,500,100,paint);
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        //尖头
//        paint.setStrokeJoin(Paint.Join.MITER);
//        canvas.drawLine(50,200,500,200,paint);

//        //折线-无效
//        Path path = new Path();
//        paint.setStyle(Paint.Style.STROKE);
//
//        //尖头-无效
//        paint.setStrokeJoin(Paint.Join.BEVEL);
//        path.lineTo(600,100);
//        path.lineTo(50,400);
//        canvas.drawPath(path,paint);
//
//
//        //平头-无效
//        paint.setStrokeJoin(Paint.Join.MITER);
//        path.moveTo(0,450);
//        path.lineTo(600,450);
//        path.lineTo(50,700);
//        canvas.drawPath(path,paint);
//
//
//        //圆头-无效
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        path.moveTo(0,900);
//        path.lineTo(600,900);
//        path.lineTo(50,1200);
//        canvas.drawPath(path,paint);

//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.em_night_icon);
//        paint.setFilterBitmap(true);
//        canvas.drawBitmap(bitmap2,20,20,paint);

        //setPathEffect
        Path path = new Path();
        path.lineTo(60,500);
        path.rLineTo(200,-200);
        path.rLineTo(260,600);
        path.rLineTo(500,-500);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        //1.拐角变圆角,参数表示圆角的半径
        PathEffect pathEffect = new CornerPathEffect(50);
        paint.setPathEffect(pathEffect);

        //2.线条随机偏离，轮廓变得乱七八糟,该方式将原本的线条变成线段来进行绘制，第一个参数表示拼接线段的长度，第二个参数表示偏移量
        PathEffect pathEffect1 = new DiscretePathEffect(50,10);
        paint.setPathEffect(pathEffect1);

        //3.使用虚线进行绘制，第一个参数数组表示虚线的格式，必须是偶数个，按照[划线长度、空白长度]来绘制，第二个参数是偏移量
        PathEffect pathEffect2 = new DashPathEffect(new float[]{50,5,20,5},0);
        paint.setPathEffect(pathEffect2);

        //4.使用path来绘制虚线，也就是说用其他类型的path来代替虚线，可以是三角形，圆形等
        Path path1 = new Path();
        //小圆点
        path1.addCircle(10,10,4,Path.Direction.CCW);
        PathEffect pathEffect3 = new PathDashPathEffect(path1,50,0,PathDashPathEffect.Style.MORPH);
        paint.setPathEffect(pathEffect3);

        //4.组合效果
        PathEffect pathEffect4 = new SumPathEffect(pathEffect1,pathEffect3);
        paint.setPathEffect(pathEffect4);

        //5.组合效果，不过是先对目标path使用patheffect，然后再对这个改变之后的path使用另一个patheffect

        PathEffect pathEffect5 = new ComposePathEffect(pathEffect,pathEffect2);
        paint.setPathEffect(pathEffect5);

        canvas.drawPath(path,paint);


        //设置下层模糊阴影效果
        paint.setShadowLayer(1,0,0,Color.BLUE);
        paint.setTextSize(88);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("Hencoder",200,1200,paint);

        //设置上层模糊效果
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ks);
        paint.setMaskFilter(new BlurMaskFilter(50,BlurMaskFilter.Blur.OUTER));
        canvas.drawBitmap(bitmap,100,1200,paint);

        //浮雕效果
        paint.setMaskFilter(new EmbossMaskFilter(new float[]{0,10,10},0.2f,20,50));
        canvas.drawBitmap(bitmap,500,1200,paint);






    }
}
