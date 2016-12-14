package com.xing.android.animation.vector.typeEvaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by zhaoxx on 15/12/15.
 */
public class PointFEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        pointF.x = startValue.x + (endValue.x - startValue.x) * fraction;
        pointF.y = startValue.y + (endValue.y - startValue.y) * fraction;
        return pointF;
    }
}
