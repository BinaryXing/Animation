package com.xing.android.animation.vector.draw;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.xing.android.animation.vector.typeEvaluator.ArcEvaluator;
import com.xing.android.animation.vector.typeEvaluator.PointFEvaluator;
import com.xing.android.common.util.LogUtil;

/**
 * Created by zhaoxx on 15/12/7.
 */
public class ArcDraw extends VectorDraw<ArcDraw.ArcProperty> {

    public static final String KEY_CENTER_POINT = "centerPoint";
    public static final String KEY_CENTER_POINT_X = "centerPointX";
    public static final String KEY_CENTER_POINT_Y = "centerPointY";
    public static final String KEY_RADIUS = "radius";
    public static final String KEY_RADIUS_X = "radiusX";
    public static final String KEY_RADIUS_Y = "radiusY";
    public static final String KEY_ANGLE = "angle";
    public static final String KEY_ANGLE_START = "startAngle";
    public static final String KEY_ANGLE_SWEEP = "sweepAngle";

    public ArcDraw(float cx, float cy, float rx, float ry, float startA, float sweepA, boolean useCenter, Paint p) {
        reset(cx, cy, rx, ry, startA, sweepA, useCenter, p);
    }

    public ArcDraw(PointF centerPoint, PointF radius, PointF angle, boolean useCenter, Paint p) {
        reset(centerPoint, radius, angle, useCenter, p);
    }

    public void reset(float cx, float cy, float rx, float ry, float startA, float sweepA, boolean useCenter, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        }
        mProperty.centerPoint.x = cx;
        mProperty.centerPoint.y = cy;
        mProperty.radius.x = rx;
        mProperty.radius.y = ry;
        mProperty.angle.x = startA;
        mProperty.angle.y = sweepA;
        mProperty.useCenter = useCenter;
        mPaint = p;
        setDrawing(false);
    }

    public void reset(PointF centerPoint, PointF radius, PointF angle, boolean useCenter, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        } else if(centerPoint == null) {
            LogUtil.e(LOG_TAG, "reset:Center Point is null");
            return;
        } else if(radius == null) {
            LogUtil.e(LOG_TAG, "reset:Radius is null");
            return;
        } else if(angle == null) {
            LogUtil.e(LOG_TAG, "reset:Angle is null");
            return;
        }
        mProperty.centerPoint.x = centerPoint.x;
        mProperty.centerPoint.y = centerPoint.y;
        mProperty.radius.x = radius.x;
        mProperty.radius.y = radius.y;
        mProperty.angle.x = angle.x;
        mProperty.angle.y = angle.y;
        mProperty.useCenter = useCenter;
        mPaint = p;
        setDrawing(false);
    }

    @Override
    protected ArcProperty newProperty() {
        return new ArcProperty();
    }

    @Override
    protected TypeEvaluator<ArcProperty> newTypeEvaluator() {
        return new ArcEvaluator();
    }

    @Override
    public void draw(Canvas canvas) {
        RectF rectF = new RectF(mProperty.centerPoint.x - mProperty.radius.x, mProperty.centerPoint.y - mProperty.radius.y,
                mProperty.centerPoint.x + mProperty.radius.x, mProperty.centerPoint.y + mProperty.radius.y);
        canvas.drawArc(rectF, mProperty.angle.x, mProperty.angle.y, mProperty.useCenter, mPaint);
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

    /**
     * 创建angle动画,会删除startAngle,sweepAngle动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param pointFs
     * @return
     */
    public ObjectAnimator initAngleAnimator(long duration, long delay, TimeInterpolator interpolator, PointF[] pointFs) {
        return initAngleAnimator(duration, delay, 0, 0, interpolator, pointFs);
    }

    /**
     * 创建angle动画,会删除startAngle,sweepAngle动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param pointFs
     * @return
     */
    public ObjectAnimator initAngleAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, PointF[] pointFs) {
        if(pointFs == null || pointFs.length == 0) {
            LogUtil.e(LOG_TAG, "initAngleAnimator:pointFs is null");
            return null;
        }
        Object[] objects = pointFs;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, KEY_ANGLE, new PointFEvaluator(), objects);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_ANGLE_START);
        removeAnimaotr(KEY_ANGLE_SWEEP);
        mAnimatorMap.put(KEY_ANGLE, animator);
        return animator;
    }

    /**
     * 创建startAngle动画,会删除angle动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param angles
     * @return
     */
    public ObjectAnimator initStartAngleAnimator(long duration, long delay, TimeInterpolator interpolator, float... angles) {
        return initStartAngleAnimator(duration, delay, 0, 0, interpolator, angles);
    }

    /**
     * 创建startAngle动画,会删除angle动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param ds
     * @return
     */
    public ObjectAnimator initStartAngleAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... ds) {
        if(ds == null || ds.length == 0) {
            LogUtil.e(LOG_TAG, "initStartAngleAnimator:ds is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_ANGLE_START, ds);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_ANGLE);
        mAnimatorMap.put(KEY_ANGLE_START, animator);
        return animator;
    }

    /**
     * 创建sweepAngle动画,会删除angle动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param angles
     * @return
     */
    public ObjectAnimator initSweepAngleAnimator(long duration, long delay, TimeInterpolator interpolator, float... angles) {
        return initSweepAngleAnimator(duration, delay, 0, 0, interpolator, angles);
    }

    /**
     * 创建sweepAngle动画,会删除angle动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param ds
     * @return
     */
    public ObjectAnimator initSweepAngleAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... ds) {
        if(ds == null || ds.length == 0) {
            LogUtil.e(LOG_TAG, "initSweepAngleAnimator:ds is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_ANGLE_SWEEP, ds);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_ANGLE);
        mAnimatorMap.put(KEY_ANGLE_SWEEP, animator);
        return animator;
    }

    @Override
    public ArcProperty getProperty() {
        return super.getProperty();
    }

    @Override
    public void setProperty(ArcProperty property) {
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

    public PointF getRadius() {
        return mProperty.radius;
    }

    public void setRadius(PointF point) {
        mProperty.radius = point;
    }

    public float getRadiusX() {
        return mProperty.radius.x;
    }

    public void setRadiusX(float radius) {
        mProperty.radius.x = radius;
    }

    public float getRadiusY() {
        return mProperty.radius.y;
    }

    public void setRadiusY(float radius) {
        mProperty.radius.y = radius;
    }

    public PointF getAngle() {
        return mProperty.angle;
    }

    public void setAngle(PointF point) {
        mProperty.angle = point;
    }

    public float getStartAngle() {
        return mProperty.angle.x;
    }

    public void setStartAngle(float angle) {
        mProperty.angle.x = angle;
    }

    public float getSweepAngle() {
        return mProperty.angle.y;
    }

    public void setSweepAngle(float angle) {
        mProperty.angle.y = angle;
    }

    public boolean getUseCenter() {
        return mProperty.useCenter;
    }

    public void setUseCenter(boolean value) {
        mProperty.useCenter = value;
    }

    public static class ArcProperty {
        /**
         * 中心点
         */
        public PointF centerPoint = new PointF();
        /**
         * 半径,x代表x轴半径,y代表y轴半径
         */
        public PointF radius = new PointF();
        /**
         * 角度,x代表start角度,y代表sweep角度
         */
        public PointF angle = new PointF();
        /**
         * 是否连接中点点
         */
        public boolean useCenter = false;

        public ArcProperty(){

        }

        public ArcProperty(float cx, float cy, float rx, float ry, float start, float sweep, boolean value) {
            centerPoint.x = cx;
            centerPoint.y = cy;
            radius.x = rx;
            radius.y = ry;
            angle.x = start;
            angle.y = sweep;
            useCenter = value;
        }
    }
}
