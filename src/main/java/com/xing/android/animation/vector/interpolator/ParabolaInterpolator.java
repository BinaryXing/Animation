package com.xing.android.animation.vector.interpolator;

import android.view.animation.Interpolator;

/**
 * Created by zhaoxx on 15/12/25.
 */
public class ParabolaInterpolator implements Interpolator {
    private float mFactor;

    public ParabolaInterpolator(float factor) {
        if(factor > 0) {
            mFactor = factor;
        }
    }

    @Override
    public float getInterpolation(float input) {
        if(input < 0.5f){
            return (float) Math.pow(input * 2, 2 * mFactor) / 2;
        } else {
            return (float)(1.0f - Math.pow((1.0f - input) * 2, 2 * mFactor) / 2);
        }
    }
}
