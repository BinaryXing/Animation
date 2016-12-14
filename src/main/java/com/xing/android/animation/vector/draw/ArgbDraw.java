package com.xing.android.animation.vector.draw;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Canvas;

import com.xing.android.animation.vector.draw.ArgbDraw.ArgbProperty;
import com.xing.android.animation.vector.typeEvaluator.ArgbEvaluator;
import com.xing.android.common.util.LogUtil;

/**
 * Created by zhaoxx on 15/12/26.
 */
public class ArgbDraw extends VectorDraw<ArgbProperty> {

    public static final String KEY_ALPHA = "alpha";
    public static final String KEY_RED = "red";
    public static final String KEY_GREEN = "green";
    public static final String KEY_BLUE = "blue";

    public ArgbDraw(int a, int r, int g, int b) {
        reset(a, r, g, b);
    }

    public void reset(int a, int r, int g, int b) {
        mProperty.alpha = convertColorValue(a);
        mProperty.red = convertColorValue(r);
        mProperty.green = convertColorValue(g);
        mProperty.blue = convertColorValue(b);
        setDrawing(false);
    }

    /**
     * 创建Alpha动画
     * @param duraiton
     * @param delay
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initAlphaAnimator(long duraiton, long delay, TimeInterpolator interpolator, int[] as) {
        return initAlphaAnimator(duraiton, delay, -1, -1, interpolator, as);
    }

    /**
     * 创建Alpha动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initAlphaAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, int[] as) {
        if(as == null || as.length == 0) {
            LogUtil.e(LOG_TAG, "initAlphaAnimator:Alpha is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofInt(this, KEY_ALPHA, as);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        mAnimatorMap.put(KEY_ALPHA, animator);
        return animator;
    }

    /**
     * 创建Red动画
     * @param duraiton
     * @param delay
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initRedAnimator(long duraiton, long delay, TimeInterpolator interpolator, int[] as) {
        return initRedAnimator(duraiton, delay, -1, -1, interpolator, as);
    }

    /**
     * 创建Red动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initRedAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, int[] as) {
        if(as == null || as.length == 0) {
            LogUtil.e(LOG_TAG, "initRedAnimator:Red is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofInt(this, KEY_RED, as);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        mAnimatorMap.put(KEY_RED, animator);
        return animator;
    }

    /**
     * 创建Green动画
     * @param duraiton
     * @param delay
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initGreenAnimator(long duraiton, long delay, TimeInterpolator interpolator, int[] as) {
        return initGreenAnimator(duraiton, delay, -1, -1, interpolator, as);
    }

    /**
     * 创建Green动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initGreenAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, int[] as) {
        if(as == null || as.length == 0) {
            LogUtil.e(LOG_TAG, "initGreenAnimator:Green is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofInt(this, KEY_GREEN, as);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        mAnimatorMap.put(KEY_GREEN, animator);
        return animator;
    }

    /**
     * 创建Blue动画
     * @param duraiton
     * @param delay
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initBlueAnimator(long duraiton, long delay, TimeInterpolator interpolator, int[] as) {
        return initBlueAnimator(duraiton, delay, -1, -1, interpolator, as);
    }

    /**
     * 创建Blue动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param as
     * @return
     */
    public ObjectAnimator initBlueAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, int[] as) {
        if(as == null || as.length == 0) {
            LogUtil.e(LOG_TAG, "initBlueAnimator:Blue is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofInt(this, KEY_BLUE, as);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        mAnimatorMap.put(KEY_BLUE, animator);
        return animator;
    }

    @Override
    protected ArgbProperty newProperty() {
        return new ArgbProperty();
    }

    @Override
    protected TypeEvaluator newTypeEvaluator() {
        return new ArgbEvaluator();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawARGB(mProperty.alpha, mProperty.red, mProperty.green, mProperty.blue);
    }

    @Override
    public ArgbProperty getProperty() {
        return super.getProperty();
    }

    @Override
    public void setProperty(ArgbProperty property) {
        super.setProperty(property);
    }

    public int getAlpha() {
        return mProperty.alpha;
    }

    public void setAlpha(int a) {
        mProperty.alpha = convertColorValue(a);
    }

    public int getRed() {
        return mProperty.red;
    }

    public void setRed(int r) {
        mProperty.red = convertColorValue(r);
    }

    public int getGreen() {
        return mProperty.green;
    }

    public void setGreen(int g) {
        mProperty.green = convertColorValue(g);
    }

    public int getBlue() {
        return mProperty.blue;
    }

    public void setBlue(int b) {
        mProperty.blue = convertColorValue(b);
    }

    public static class ArgbProperty {
        public int alpha;
        public int red;
        public int green;
        public int blue;

        public ArgbProperty() {

        }

        public ArgbProperty(int a, int r, int g, int b) {
            alpha = convertColorValue(a);
            red = convertColorValue(r);
            green = convertColorValue(g);
            blue = convertColorValue(b);
        }


    }

    private static int convertColorValue(int value) {
        if(value < 0) {
            return 0;
        } else if(value < 256) {
            return value;
        } else {
            return 255;
        }
    }
}
