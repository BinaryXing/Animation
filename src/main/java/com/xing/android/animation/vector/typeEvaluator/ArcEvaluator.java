package com.xing.android.animation.vector.typeEvaluator;

import android.animation.TypeEvaluator;

import com.xing.android.animation.vector.draw.ArcDraw;

/**
 * Created by zhaoxx on 15/12/15.
 */
public class ArcEvaluator implements TypeEvaluator<ArcDraw.ArcProperty> {

    private ArcDraw.ArcProperty arcProperty = new ArcDraw.ArcProperty();

    @Override
    public ArcDraw.ArcProperty evaluate(float fraction, ArcDraw.ArcProperty startValue, ArcDraw.ArcProperty endValue) {
        arcProperty.centerPoint.x = startValue.centerPoint.x + (endValue.centerPoint.x - startValue.centerPoint.x) * fraction;
        arcProperty.centerPoint.y = startValue.centerPoint.y + (endValue.centerPoint.y - startValue.centerPoint.y) * fraction;
        arcProperty.radius.x = startValue.radius.x + (endValue.radius.x - startValue.angle.x) * fraction;
        arcProperty.radius.y = startValue.radius.y + (endValue.radius.y - startValue.radius.y) * fraction;
        arcProperty.angle.x = startValue.angle.x + (endValue.angle.x - startValue.angle.x) * fraction;
        arcProperty.angle.y = startValue.angle.y + (endValue.angle.y - startValue.angle.y) * fraction;
        arcProperty.useCenter = startValue.useCenter;
        return arcProperty;
    }
}
