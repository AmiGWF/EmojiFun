package com.wd.hencoder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorFilterActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private TextView red_tv,green_tv,blue_tv,alpha_tv;
    private SeekBar red_seekbar, green_seekbar, blue_seekbar, alpha_seekbar;
    private ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorfilter);
        initView();
    }

    private void initView() {
        red_seekbar = findViewById(R.id.red_seekbar);
        green_seekbar = findViewById(R.id.green_seekbar);
        blue_seekbar = findViewById(R.id.blue_seekbar);
        alpha_seekbar = findViewById(R.id.alpha_seekbar);

        red_seekbar.setOnSeekBarChangeListener(this);
        green_seekbar.setOnSeekBarChangeListener(this);
        blue_seekbar.setOnSeekBarChangeListener(this);
        alpha_seekbar.setOnSeekBarChangeListener(this);

        image2 = findViewById(R.id.color_image2);

        red_tv = findViewById(R.id.red_tv);
        green_tv = findViewById(R.id.green_tv);
        blue_tv = findViewById(R.id.blue_tv);
        alpha_tv = findViewById(R.id.alpha_tv);

        setColorFilter();
    }

    private Canvas canvas;
    private Bitmap copyBitmap;
    private Bitmap baseBitmap;
    private Paint paint;
    private float red, green, blue, alpha;

    private void setColorFilter() {
        baseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        copyBitmap = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), baseBitmap.getConfig());

        canvas = new Canvas(copyBitmap);
        paint = new Paint();

        float colorArrays[] = new float[]{
                1, 0, 0, 0, red,
                0, 1, 0, 0, green,
                0, 0, 1, 0, blue,
                0, 0, 0, 1, alpha
        };
//        ColorMatrix colorMatrix = new ColorMatrix(colorArrays);

        ColorMatrix colorMatrix = new ColorMatrix();
        //修改色调：第一个参数可传入0、1、2，分别表示对红、绿、蓝进行处理
        //colorMatrix.setRotate(0,50);
        colorMatrix.setRotate(1,100);
//        colorMatrix.setRotate(2,20);

        //修改亮度
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix2.setScale(5,9,8,1);

        //修改饱和度
        ColorMatrix colorMatrix3 = new ColorMatrix();
        colorMatrix3.setSaturation(5);

        //混合多重效果
        ColorMatrix mMatrix=new ColorMatrix();
        mMatrix.postConcat(colorMatrix);
        mMatrix.postConcat(colorMatrix2);
        mMatrix.postConcat(colorMatrix3);

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(mMatrix);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);
        image2.setImageBitmap(copyBitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
//            float count = seekBar.getProgress() / 50f;//因为使得拖动条的取值为0f-2f，符合矩阵中每个元素的取值
            float count = seekBar.getProgress();//偏移量
            int i = seekBar.getId();
            if (i == R.id.red_seekbar) {
                this.red = count;
            } else if (i == R.id.green_seekbar) {
                this.green = count;
            } else if (i == R.id.blue_seekbar) {
                this.blue = count;
            } else if (i == R.id.alpha_seekbar) {
                this.alpha = count;
            }


            red_tv.setText("红色："+red);
            green_tv.setText("绿色："+green);
            blue_tv.setText("蓝色："+blue);
            alpha_tv.setText("透明度："+alpha);

            setColorFilter();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
