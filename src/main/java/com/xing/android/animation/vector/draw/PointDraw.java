package com.xing.android.animation.vector.draw;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.xing.android.animation.vector.typeEvaluator.PointFEvaluator;
import com.xing.android.common.util.LogUtil;

/**
 * Created by zhaoxx on 15/12/7.
 */
public class PointDraw extends VectorDraw<PointF> {

    public static final String KEY_POINT_X = "pointX";
    public static final String KEY_POINT_Y = "pointY";

    public PointDraw(float x, float y, Paint p) {
        reset(x, y, p);
    }

    public PointDraw(PointF point, Paint p) {
        reset(point, p);
    }

    public void reset(float x, float y, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        }
        mProperty.x = x;
        mProperty.y = y;
        mPaint = p;
        setDrawing(false);
    }

    public void reset(PointF point, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        }
        if(point == null) {
            LogUtil.e(LOG_TAG, "reset:PointF is null");
            return;
        }
        mProperty.x = point.x;
        mProperty.y = point.y;
        mPaint = p;
        setDrawing(false);
    }

    @Override
    protected PointF newProperty() {
        return new PointF();
    }

    @Override
    protected TypeEvaluator<PointF> newTypeEvaluator() {
        return new PointFEvaluator();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPoint(mProperty.x, mProperty.y, mPaint);
    }

    /**
     * 创建pointX动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initPointXAnimator(long duration, long delay, TimeInterpolator interpolator, float... xs) {
        return initPointXAnimator(duration, delay, 0, 0, interpolator, xs);
    }

    /**
     * 创建pointX动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initPointXAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... xs) {
        if(xs == null || xs.length == 0) {
            LogUtil.e(LOG_TAG, "initPointXAnimator:xs is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_POINT_X, xs);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        mAnimatorMap.put(KEY_POINT_X, animator);
        return animator;
    }

    /**
     * 创建pointY动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initPointYAnimator(long duration, long delay, TimeInterpolator interpolator, float... ys) {
        return initPointYAnimator(duration, delay, 0, 0, interpolator, ys);
    }

    /**
     * 创建pointY动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initPointYAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... ys) {
        if(ys == null || ys.length == 0) {
            LogUtil.e(LOG_TAG, "initPointYAnimator:ys is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_POINT_Y, ys);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        mAnimatorMap.put(KEY_POINT_Y, animator);
        return animator;
    }

    @Override
    public PointF getProperty() {
        return super.getProperty();
    }

    @Override
    public void setProperty(PointF property) {
        super.setProperty(property);
    }

    public float getPointX() {
        return mProperty.x;
    }

    public void setPointX(int x) {
        mProperty.x = x;
    }

    public float getPointY() {
        return mProperty.y;
    }

    public void setPointY(float y) {
        mProperty.y = y;
    }
}
