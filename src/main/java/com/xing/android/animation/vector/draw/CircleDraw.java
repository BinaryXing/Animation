package com.xing.android.animation.vector.draw;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.xing.android.animation.vector.typeEvaluator.CircleEvaluator;
import com.xing.android.animation.vector.typeEvaluator.PointFEvaluator;
import com.xing.android.common.util.LogUtil;

/**
 * Created by zhaoxx on 15/12/15.
 */
public class CircleDraw extends VectorDraw<CircleDraw.CircleProperty> {

    public static final String KEY_CENTER_POINT = "centerPoint";
    public static final String KEY_CENTER_POINT_X = "centerPointX";
    public static final String KEY_CENTER_POINT_Y = "centerPointY";
    public static final String KEY_RADIUS = "radius";

    public CircleDraw(float centerX, float centerY, float radius, Paint p) {
        reset(centerX, centerY, radius, p);
    }

    public CircleDraw(PointF centerPoint, float radius, Paint p) {
        reset(centerPoint, radius, p);
    }

    public void reset(float centerX, float centerY, float radius, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        }
        mProperty.centerPoint.x = centerX;
        mProperty.centerPoint.y = centerY;
        mProperty.radius = radius;
        mPaint = p;
        setDrawing(false);
    }

    public void reset(PointF centerPoint, float radius, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        } else if(centerPoint == null) {
            LogUtil.e(LOG_TAG, "reset:center point is null");
            return;
        }
        mProperty.centerPoint.x = centerPoint.x;
        mProperty.centerPoint.y = centerPoint.y;
        mProperty.radius = radius;
        mPaint = p;
        setDrawing(false);
    }

    @Override
    protected CircleProperty newProperty() {
        return new CircleProperty();
    }

    @Override
    protected TypeEvaluator<CircleProperty> newTypeEvaluator() {
        return new CircleEvaluator();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mProperty.centerPoint.x, mProperty.centerPoint.y, mProperty.radius, mPaint);
    }

    /**
     * 创建centerPoint动画,会删除centerPointX,centerPointY动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param pointFs
     * @return
     */
    public ObjectAnimator initCenterPointAnimator(long duration, long delay, TimeInterpolator interpolator, PointF[] pointFs) {
        return initCenterPointAnimator(duration, delay, 0, 0, interpolator, pointFs);
    }

    /**
     * 创建centerPoint动画,会删除centerPointX,centerPointY动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param pointFs
     * @return
     */
    public ObjectAnimator initCenterPointAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, PointF[] pointFs) {
        if(pointFs == null || pointFs.length == 0) {
            LogUtil.e(LOG_TAG, "initCenterPointAnimator:pointFs is null");
            return null;
        }
        Object[] objects = pointFs;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, KEY_CENTER_POINT, new PointFEvaluator(), objects);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_CENTER_POINT_X);
        removeAnimaotr(KEY_CENTER_POINT_Y);
        mAnimatorMap.put(KEY_CENTER_POINT, animator);
        return animator;
    }

    /**
     * 创建centerPointX动画,会删除centerPoint动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initCenterPointXAnimator(long duration, long delay, TimeInterpolator interpolator, float... xs) {
        return initCenterPointXAnimator(duration, delay, 0, 0, interpolator, xs);
    }

    /**
     * 创建centerPointX动画,会删除centerPoint动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initCenterPointXAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... xs) {
        if(xs == null || xs.length == 0) {
            LogUtil.e(LOG_TAG, "initCenterPointXAnimator:xs is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_CENTER_POINT_X, xs);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_CENTER_POINT);
        mAnimatorMap.put(KEY_CENTER_POINT_X, animator);
        return animator;
    }

    /**
     *
     * @param duration
     * @param delay
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initCenterPointYAnimator(long duration, long delay, TimeInterpolator interpolator, float... ys) {
        return initCenterPointYAnimator(duration, delay, 0, 0, interpolator, ys);
    }

    /**
     * 创建centerPointY动画,会删除centerPoint动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initCenterPointYAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... ys) {
        if(ys == null || ys.length == 0) {
            LogUtil.e(LOG_TAG, "initCenterPointYAnimator:ys is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_CENTER_POINT_Y, ys);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_CENTER_POINT);
        mAnimatorMap.put(KEY_CENTER_POINT_Y, animator);
        return animator;
    }

    /**
     * 创建radius动画,会删除radiusX和radiusY动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param radius
     * @return
     */
    public ObjectAnimator initRadiusAnimator(long duration, long delay, TimeInterpolator interpolator, float... radius) {
        return initRadiusAnimator(duration, delay, 0, 0, interpolator, radius);
    }

    /**
     * 创建radius动画,会删除radiusX和radiusY动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param radius
     * @return
     */
    public ObjectAnimator initRadiusAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... radius) {
        if(radius == null || radius.length == 0) {
            LogUtil.e(LOG_TAG, "initRadiusAnimator:radius is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_RADIUS, radius);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        mAnimatorMap.put(KEY_RADIUS, animator);
        return animator;
    }

    @Override
    public CircleProperty getProperty() {
        return super.getProperty();
    }

    @Override
    public void setProperty(CircleProperty property) {
        super.setProperty(property);
    }

    public PointF getCenterPoint() {
        return mProperty.centerPoint;
    }

    public void setCenterPoint(PointF point) {
        mProperty.centerPoint = point;
    }

    public float getCenterPointX() {
        return mProperty.centerPoint.x;
    }

    public void setCenterPointX(float x) {
        mProperty.centerPoint.x = x;
    }

    public float getCenterPointY() {
        return mProperty.centerPoint.y;
    }

    public void setCenterPointY(float y) {
        mProperty.centerPoint.y = y;
    }

    public float getRadius() {
        return mProperty.radius;
    }

    public void setRadius(float r) {
        mProperty.radius = r;
    }

    public static class CircleProperty {
        public PointF centerPoint = new PointF();
        public float radius;
    }
}
