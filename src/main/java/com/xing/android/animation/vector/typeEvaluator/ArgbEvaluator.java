package com.xing.android.animation.vector.typeEvaluator;


import android.animation.TypeEvaluator;

import com.xing.android.animation.vector.draw.ArgbDraw.ArgbProperty;

/**
 * Created by zhaoxx on 15/12/26.
 */
public class ArgbEvaluator implements TypeEvaluator<ArgbProperty> {

    private ArgbProperty argbProperty = new ArgbProperty();

    @Override
    public ArgbProperty evaluate(float fraction, ArgbProperty startValue, ArgbProperty endValue) {
        argbProperty.alpha = (int) (startValue.alpha + (endValue.alpha - startValue.alpha) * fraction);
        argbProperty.red = (int) (startValue.red + (endValue.red - startValue.red) * fraction);
        argbProperty.green = (int) (startValue.green + (endValue.green - startValue.green) * fraction);
        argbProperty.blue = (int) (startValue.blue + (endValue.blue - startValue.blue) * fraction);
        return argbProperty;
    }
}
