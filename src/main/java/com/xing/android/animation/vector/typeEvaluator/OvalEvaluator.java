package com.xing.android.animation.vector.typeEvaluator;


import android.animation.TypeEvaluator;

import com.xing.android.animation.vector.draw.OvalDraw;

/**
 * Created by zhaoxx on 15/12/26.
 */
public class OvalEvaluator implements TypeEvaluator<OvalDraw.OvalProperty> {

    private OvalDraw.OvalProperty ovalProperty = new OvalDraw.OvalProperty();

    @Override
    public OvalDraw.OvalProperty evaluate(float fraction, OvalDraw.OvalProperty startValue, OvalDraw.OvalProperty endValue) {
        ovalProperty.centerPoint.x = startValue.centerPoint.x + (endValue.centerPoint.x - startValue.centerPoint.x) * fraction;
        ovalProperty.centerPoint.y = startValue.centerPoint.y + (endValue.centerPoint.y - startValue.centerPoint.y) * fraction;
        ovalProperty.radius.x = startValue.radius.x + (endValue.radius.x - startValue.radius.x) * fraction;
        ovalProperty.radius.y = startValue.radius.y + (endValue.radius.y - startValue.radius.y) * fraction;
        return ovalProperty;
    }
}
