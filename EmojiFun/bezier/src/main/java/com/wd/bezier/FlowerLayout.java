package com.wd.bezier;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * author : wudu
 * time : 2017/9/13
 * 点赞送爱心
 */

public class FlowerLayout extends RelativeLayout {
    private Context mContext;
    private int mWidth,mHeight;
    private int viewWidth,viewHeight;
    private Random random = new Random();
    //images
    private Drawable[] drawables;
    //image params
    private RelativeLayout.LayoutParams params;

    //插值器
    private Interpolator linear = new LinearInterpolator();//线性
    private Interpolator accelerate = new AccelerateInterpolator();//加速
    private Interpolator decelerate = new DecelerateInterpolator();//减速
    private Interpolator accelerateDecelerate = new AccelerateDecelerateInterpolator();//先加速后减速
    private Interpolator[] interpolators;



    public FlowerLayout(Context context) {
        super(context);

    }

    public FlowerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext  = context;
        init();
    }

    private void init() {
        drawables = new Drawable[3];
        drawables[0] = getResources().getDrawable(R.drawable.pl_blue);
        drawables[1] = getResources().getDrawable(R.drawable.pl_red);
        drawables[2] = getResources().getDrawable(R.drawable.pl_yellow);

        viewWidth = drawables[0].getIntrinsicWidth();
        viewHeight = drawables[0].getIntrinsicHeight();

        params = new RelativeLayout.LayoutParams(viewWidth,viewHeight);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL,TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,TRUE);

        interpolators = new Interpolator[4];
        interpolators[0] = linear;
        interpolators[1] = accelerate;
        interpolators[2] = decelerate;
        interpolators[3] = accelerateDecelerate;
    }

    public void addHeart(){
        ImageView imageView = new ImageView(mContext);
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        imageView.setLayoutParams(params);
        addView(imageView);

        Animator animator = getAnimator(imageView);
        animator.addListener(new AnimatorEndListener(imageView));
        animator.start();
    }

    private Animator getAnimator(View view){
        AnimatorSet animatorSet = getAnimatorSet(view);
        ValueAnimator valueAnimator = getBezierValueAnimator(view);

        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playSequentially(animatorSet);
        finalSet.playSequentially(animatorSet,valueAnimator);
        finalSet.setInterpolator(interpolators[random.nextInt(4)]);
        finalSet.setTarget(view);
        return finalSet;
    }

    private AnimatorSet getAnimatorSet(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.2f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(alpha, scaleX, scaleY);
        animatorSet.setTarget(view);
        return animatorSet;
    }

    /**
     * 获取三阶贝塞尔曲线的中间两个点
     */
    private PointF getPointF(int scale) {
        PointF pointF = new PointF();
        //减去100是为控制x轴的活动返回，全屏时不会碰到左右两边，数值随意
        pointF.x = random.nextInt((mWidth - 100));
        //使用scale是为了让起始点在终点的下面，前提是起始点的scale大于终点的scale,但是随机数不一定每次都会大于
        pointF.y = random.nextInt((mHeight - 100)) / scale;
        return pointF;
    }

    private ValueAnimator getBezierValueAnimator(View view){
        BezierEvaluator bezierEvaluator = new BezierEvaluator(getPointF(2),getPointF(1));

        //传入起点和终点
        ValueAnimator valueAnimator = ValueAnimator.ofObject(
                bezierEvaluator,
                new PointF((mWidth - viewWidth)/2,mHeight - viewHeight),
                new PointF(random.nextInt(getWidth()),0));
        valueAnimator.addUpdateListener(new BezierUpdateListener(view));
        valueAnimator.setTarget(view);
        valueAnimator.setDuration(3000);
        return valueAnimator;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    class BezierUpdateListener implements ValueAnimator.AnimatorUpdateListener{
        private View view;

        public BezierUpdateListener(View view) {
            this.view = view;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            //获取贝塞尔曲线计算出的x、y赋值给view，使爱心动起来
            PointF pointF = (PointF) valueAnimator.getAnimatedValue();
            view.setX(pointF.x);
            view.setY(pointF.y);
            view.setAlpha(1 - valueAnimator.getAnimatedFraction());
        }
    }

    class AnimatorEndListener extends AnimatorListenerAdapter {
        private View view;

        public AnimatorEndListener(View view) {
            this.view = view;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView(view);
        }
    }
}
