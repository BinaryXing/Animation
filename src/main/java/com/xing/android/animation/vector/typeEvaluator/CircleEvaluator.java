package com.xing.android.animation.vector.typeEvaluator;


import android.animation.TypeEvaluator;

import com.xing.android.animation.vector.draw.CircleDraw;

/**
 * Created by zhaoxx on 15/12/15.
 */
public class CircleEvaluator implements TypeEvaluator<CircleDraw.CircleProperty> {

    private CircleDraw.CircleProperty circleProperty = new CircleDraw.CircleProperty();

    @Override
    public CircleDraw.CircleProperty evaluate(float fraction, CircleDraw.CircleProperty startValue, CircleDraw.CircleProperty endValue) {
        circleProperty.centerPoint.x = startValue.centerPoint.x + (endValue.centerPoint.x - startValue.centerPoint.x) * fraction;
        circleProperty.centerPoint.y = startValue.centerPoint.y + (endValue.centerPoint.y - startValue.centerPoint.y) * fraction;
        circleProperty.radius = startValue.radius + (endValue.radius - startValue.radius) * fraction;
        return circleProperty;
    }
}
