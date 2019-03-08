package com.wd.emojiui.inter;

import android.view.animation.LinearInterpolator;

public class EMInterpolator extends LinearInterpolator {
    private float factor;
    public EMInterpolator(){
        this.factor = 0.5f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input)
                * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
