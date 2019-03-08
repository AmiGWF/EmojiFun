package com.wd.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.wd.hencoder.R;

public class HenView6 extends View {
    private String mZHText = "自定义View方法查询手册";

    Paint paint = new Paint();
    Path path = new Path();

    public HenView6(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setTextSize(60);
        paint.setStrokeWidth(40);

        path.rLineTo(200, 0);
        path.rLineTo(-160, 120);
    }

    public HenView6(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //paint.setARGB(100,200,0,0);
        //paint.setColor(Color.parseColor("#889988"));
        //paint.setColor(Color.GREEN);
        //paint.setAlpha(100);
        //paint.setLetterSpacing(0.5f);

        //paint.setTextAlign(Paint.Align.CENTER);


        //mZHText = "雨水";
        //canvas.drawText(mZHText,200,200,paint);
        //paint.setTextLocale(Locale.TAIWAN);

        //paint.setTextScaleX(0.5f);

        //paint.setTypeface(Typeface.DEFAULT_BOLD);
//        Typeface typeface = Typeface.create(Typeface.SERIF,Typeface.BOLD_ITALIC);
//        paint.setTypeface(typeface);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(40);


//
//        paint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText(mZHText,200,200,paint);
//        paint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(mZHText,200,300,paint);
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText(mZHText,200,400,paint);
//
//
//
////        paint.setStrokeCap(Paint.Cap.BUTT);
//        canvas.drawLine(100,300,1000,300,paint);
//
//        canvas.drawCircle(500,600,200,paint);
//
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawLine(100,200,1000,200,paint);
//
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawLine(100,300,1000,300,paint);

//        canvas.save();
//
//        canvas.translate(100, 100);
////        paint.setStrokeJoin(Paint.Join.MITER);
//        paint.setStrokeMiter(4);
//        canvas.drawPath(path, paint);
//
//        canvas.translate(300, 0);
////        paint.setStrokeJoin(Paint.Join.BEVEL);
//        paint.setStrokeMiter(2);
//        canvas.drawPath(path, paint);
//
//        canvas.translate(300, 0);
////        paint.setStrokeJoin(Paint.Join.ROUND);
//        paint.setStrokeMiter(5);
//        canvas.drawPath(path, paint);
//
//        canvas.restore();

        paint.setTextSize(60);
        paint.setColor(Color.parseColor("#009688"));


//        Shader shader = new LinearGradient(300, 200, 700, 600, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        Shader shader2 = new LinearGradient(300, 200, 700, 600, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
        Shader shader3 = new LinearGradient(300, 200, 700, 600, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);

//        Shader shader = new RadialGradient(500, 420, 100, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        Shader shader2 = new RadialGradient(500, 420, 100, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
//        Shader shader3 = new RadialGradient(500, 420, 100, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);


        //Shader shader = new SweepGradient(500,420,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"));


//        canvas.drawCircle(250, 230, 180, paint);
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        Shader shader = new BitmapShader(bitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
//
//        canvas.translate(350,0);
//        canvas.scale(0.6f,0.6f);
//
//
//
//        canvas.drawBitmap(bitmap,350,120,paint);
//
//        canvas.translate(-200,800);
//        paint.setShader(shader3);
//



//        canvas.drawText("SRC_OVER",650,300,paint);
//
//        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        Shader shaderA = new BitmapShader(bitmapA,Shader.TileMode.REPEAT,Shader.TileMode.CLAMP);
//
//        Bitmap bitmapB = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
//        Shader shaderB = new BitmapShader(bitmapB,Shader.TileMode.REPEAT,Shader.TileMode.CLAMP);
//
//        //混合着色器
//        Shader shader = new ComposeShader(shaderA,shaderB,PorterDuff.Mode.SRC_OVER);
//
//        paint.setShader(shader);

//        canvas.drawText("加深绿色",300,150,paint);
//
        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        canvas.drawBitmap(bitmapA,0,100,paint);
//
//        LightingColorFilter colorFilter = new LightingColorFilter(Color.parseColor("#FFFFFF"),Color.parseColor("#22FF22"));

//        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.parseColor("#666666"),PorterDuff.Mode.SRC_OUT);


        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(colorArray);
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(bitmapA,500,100,paint);













//        canvas.drawCircle(400, 300, 200, paint);
//        paint.reset();

        canvas.restore();
    }

    float redValue = 1;
    float greenValue = 0;
    float blueValue = 0;
    float alphaValue = 0.5f;
    float[] colorArray=new float[]{
            redValue,0,0,0,0,
            0,greenValue,0,0,0,
            0,0,blueValue,0,0,
            0,0,0,alphaValue,0
    };

    }
