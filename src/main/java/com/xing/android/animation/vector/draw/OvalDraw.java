package com.xing.android.animation.vector.draw;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.xing.android.animation.vector.typeEvaluator.OvalEvaluator;
import com.xing.android.animation.vector.typeEvaluator.PointFEvaluator;
import com.xing.android.common.util.LogUtil;

/**
 * Created by zhaoxx on 15/12/26.
 */
public class OvalDraw extends VectorDraw<OvalDraw.OvalProperty> {

    public static final String KEY_CENTER_POINT = "centerPoint";
    public static final String KEY_CENTER_POINT_X = "centerPointX";
    public static final String KEY_CENTER_POINT_Y = "centerPointY";
    public static final String KEY_RADIUS = "radius";
    public static final String KEY_RADIUS_X = "radiusX";
    public static final String KEY_RADIUS_Y = "radiusY";

    public OvalDraw(float cx, float cy, float rx, float ry, Paint p) {
        reset(cx, cy, rx, ry, p);
    }

    public OvalDraw(PointF center, PointF radius, Paint p) {
        reset(center, radius, p);
    }

    public void reset(float cx, float cy, float rx, float ry, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        }
        mProperty.centerPoint.x = cx;
        mProperty.centerPoint.y = cy;
        mProperty.radius.x = rx;
        mProperty.radius.y = ry;
        mPaint = p;
        setDrawing(false);
    }

    public void reset(PointF center, PointF radius, Paint p) {
        if(center == null) {
            LogUtil.e(LOG_TAG, "reset:Center Point is null");
            return;
        } else if(radius == null) {
            LogUtil.e(LOG_TAG, "reset:Radius is null");
            return;
        } else if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        }
        mProperty.centerPoint.x = center.x;
        mProperty.centerPoint.y = center.y;
        mProperty.radius.x = radius.x;
        mProperty.radius.y = radius.y;
        mPaint = p;
        setDrawing(false);
    }

    @Override
    protected OvalProperty newProperty() {
        return new OvalProperty();
    }

    @Override
    protected TypeEvaluator newTypeEvaluator() {
        return new OvalEvaluator();
    }

    @Override
    public void draw(Canvas canvas) {
        RectF rectF = new RectF(mProperty.centerPoint.x - mProperty.radius.x, mProperty.centerPoint.y - mProperty.radius.y,
                mProperty.centerPoint.x + mProperty.radius.x, mProperty.centerPoint.y + mProperty.radius.y);
        canvas.drawOval(rectF, mPaint);
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
        return initCenterPointAnimator(duration, delay, -1, -1, interpolator, pointFs);
    }

    /**
     * 创建centerPoint动画,会删除centerPointX,centerPointY动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param points
     * @return
     */
    public ObjectAnimator initCenterPointAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, PointF[] points) {
        if(points == null || points.length == 0) {
            LogUtil.e(LOG_TAG, "initCenterPointAnimator:points is null");
            return null;
        }
        Object[] objects = points;
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
     * @param points
     * @return
     */
    public ObjectAnimator initRadiusAnimator(long duration, long delay, TimeInterpolator interpolator, PointF[] points) {
        return initRadiusAnimator(duration, delay, 0, 0, interpolator, points);
    }

    /**
     * 创建radius动画,会删除radiusX和radiusY动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param points
     * @return
     */
    public ObjectAnimator initRadiusAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, PointF[] points) {
        if(points == null || points.length == 0) {
            LogUtil.e(LOG_TAG, "initRadiusAnimator:points is null");
            return null;
        }
        Object[] objects = points;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, KEY_RADIUS, new PointFEvaluator(), objects);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_RADIUS_X);
        removeAnimaotr(KEY_RADIUS_Y);
        mAnimatorMap.put(KEY_RADIUS, animator);
        return animator;
    }

    /**
     * 创建radiusX动画,会删除radius动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param rxs
     * @return
     */
    public ObjectAnimator initRadiusXAnimator(long duration, long delay, TimeInterpolator interpolator, float... rxs) {
        return initRadiusXAnimator(duration, delay, 0, 0, interpolator, rxs);
    }

    /**
     * 创建radiusX动画,会删除radius动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param rxs
     * @return
     */
    public ObjectAnimator initRadiusXAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... rxs) {
        if(rxs == null || rxs.length == 0) {
            LogUtil.e(LOG_TAG, "initRadiusXAnimator:rxs is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_RADIUS_X, rxs);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_RADIUS);
        mAnimatorMap.put(KEY_RADIUS_X, animator);
        return animator;
    }

    /**
     * 创建radiusY动画,会删除radius动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param rys
     * @return
     */
    public ObjectAnimator initRadiusYAnimator(long duration, long delay, TimeInterpolator interpolator, float... rys) {
        return initRadiusYAnimator(duration, delay, 0, 0, interpolator, rys);
    }

    /**
     * 创建radiusY动画,会删除radius动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param rys
     * @return
     */
    public ObjectAnimator initRadiusYAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... rys) {
        if(rys == null || rys.length == 0) {
            LogUtil.e(LOG_TAG, "initRadiusYAnimator:rys is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_RADIUS_Y, rys);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_RADIUS);
        mAnimatorMap.put(KEY_RADIUS_Y, animator);
        return animator;
    }

    @Override
    public OvalProperty getProperty() {
        return super.getProperty();
    }

    @Override
    public void setProperty(OvalProperty property) {
        super.setProperty(property);
    }

    public PointF getCenterPoint() {
        return mProperty.centerPoint;
    }

    public void setCenterPoint(PointF p) {
        mProperty.centerPoint = p;
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

    public PointF getRadius() {
        return mProperty.radius;
    }

    public void setRadius(PointF p) {
        mProperty.radius = p;
    }

    public float getRadiusX() {
        return mProperty.radius.x;
    }

    public void setRadiusX(float x) {
        mProperty.radius.x = x;
    }

    public float getRadiusY() {
        return mProperty.radius.y;
    }

    public void setRaidusY(float y) {
        mProperty.radius.y = y;
    }

    public static class OvalProperty {
        public PointF centerPoint = new PointF();
        public PointF radius = new PointF();

        public OvalProperty() {

        }

        public OvalProperty(float centerX, float centerY, float radiusX, float radiusY) {
            centerPoint.x = centerX;
            centerPoint.y = centerY;
            radius.x = radiusX;
            radius.y = radiusY;
        }
    }
}
