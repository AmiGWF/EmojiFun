package com.wd.bezier;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * author : wudu
 * time : 2017/9/14
 * 贝塞尔三阶曲线
 * B(t) = P0*(1-t)^3 + 3P1*t(1-t)^2 + 3P2*t^2*(1-t) + P3*t^3
 * P0:起始点
 * P1:
 * P2:
 * P3:终点
 *
 *
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF pointF1,pointF2;

    public BezierEvaluator(PointF pointF1,PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float time, PointF startValue, PointF endValue) {
        float diff = 1.0f - time;
        PointF pointF = new PointF();
        pointF.x =
                startValue.x * diff * diff * diff +
                3 * pointF1.x * time * diff * diff +
                3 * pointF2.x * time * time * diff +
                endValue.x * time * time * time;

        pointF.y =
                startValue.y * diff * diff * diff +
                        3 * pointF1.y * time * diff * diff +
                        3 * pointF2.y * time * time * diff +
                        endValue.y * time * time * time;

        return pointF;
    }
}
