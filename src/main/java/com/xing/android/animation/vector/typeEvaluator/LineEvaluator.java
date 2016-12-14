package com.xing.android.animation.vector.typeEvaluator;


import android.animation.TypeEvaluator;

import com.xing.android.animation.vector.draw.LineDraw;

/**
 * Created by zhaoxx on 15/12/15.
 */
public class LineEvaluator implements TypeEvaluator<LineDraw.LineProperty> {

    private LineDraw.LineProperty lineProperty = new LineDraw.LineProperty();

    @Override
    public LineDraw.LineProperty evaluate(float fraction, LineDraw.LineProperty startValue, LineDraw.LineProperty endValue) {
        lineProperty.startPoint.x = startValue.startPoint.x + (endValue.startPoint.x - startValue.startPoint.x) * fraction;
        lineProperty.startPoint.y = startValue.startPoint.y + (endValue.startPoint.y - startValue.startPoint.y) * fraction;
        lineProperty.stopPoint.x = startValue.stopPoint.x + (endValue.stopPoint.x - startValue.stopPoint.x) * fraction;
        lineProperty.stopPoint.y = startValue.stopPoint.y + (endValue.stopPoint.y - startValue.stopPoint.y) * fraction;
        return lineProperty;
    }
}
