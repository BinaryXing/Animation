package com.xing.android.animation.vector.draw;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.xing.android.animation.vector.typeEvaluator.LineEvaluator;
import com.xing.android.animation.vector.typeEvaluator.PointFEvaluator;
import com.xing.android.common.util.LogUtil;

/**
 * Created by zhaoxx on 15/12/7.
 */
public class LineDraw extends VectorDraw<LineDraw.LineProperty> {

    public static final String KEY_START_POINT = "startPoint";
    public static final String KEY_START_POINT_X = "startPointX";
    public static final String KEY_START_POINT_Y = "startPointY";
    public static final String KEY_STOP_POINT = "stopPoint";
    public static final String KEY_STOP_POINT_X = "stopPointX";
    public static final String KEY_STOP_POINT_Y = "stopPointY";

    public LineDraw(float startX, float startY, float stopX, float stopY, Paint p) {
        reset(startX, startY, stopX, stopY, p);
    }

    public LineDraw(PointF start, PointF stop, Paint p) {
        reset(start, stop, p);
    }

    public void reset(float startX, float startY, float stopX, float stopY, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        }
        mProperty.startPoint.x = startX;
        mProperty.startPoint.y = startY;
        mProperty.stopPoint.x = stopX;
        mProperty.stopPoint.y = stopY;
        mPaint = p;
        setDrawing(false);
    }

    public void reset(PointF start, PointF stop, Paint p) {
        if(p == null) {
            LogUtil.e(LOG_TAG, "reset:Paint is null");
            return;
        } else if(start == null) {
            LogUtil.e(LOG_TAG, "reset:start point is null");
            return;
        } else if(stop == null) {
            LogUtil.e(LOG_TAG, "reset:stop point is null");
            return;
        }
        mProperty.startPoint.x = start.x;
        mProperty.startPoint.y = start.y;
        mProperty.stopPoint.x = stop.x;
        mProperty.stopPoint.y = stop.y;
        mPaint = p;
        setDrawing(false);
    }

    @Override
    protected LineProperty newProperty() {
        return new LineProperty();
    }

    @Override
    protected TypeEvaluator<LineProperty> newTypeEvaluator() {
        return new LineEvaluator();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(mProperty.startPoint.x, mProperty.startPoint.y, mProperty.stopPoint.x, mProperty.stopPoint.y, mPaint);
    }

    /**
     * 创建startPoint动画,由于和startPointX,startPointY存在交叉,所以会删除对应的交叉动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param points
     * @return
     */
    public ObjectAnimator initStartPointAnimator(long duration, long delay, TimeInterpolator interpolator, PointF[] points) {
        return initStartPointAnimator(duration, delay, 0, 0, interpolator, points);
    }

    /**
     * 创建startPoint动画,由于和startPointX,startPointY存在交叉,所以会删除对应的交叉动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param points
     * @return
     */
    public ObjectAnimator initStartPointAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, PointF[] points) {
        if(points == null || points.length == 0) {
            LogUtil.e(LOG_TAG, "initStartPointAnimator:points is null");
            return null;
        }
        Object[] objects = points;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, KEY_START_POINT, new PointFEvaluator(), objects);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_START_POINT_X);
        removeAnimaotr(KEY_START_POINT_Y);
        mAnimatorMap.put(KEY_START_POINT, animator);
        return animator;
    }

    /**
     * 创建startPointX动画,会删除startPoint动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initStartPointXAnimator(long duration, long delay, TimeInterpolator interpolator, float... xs) {
        return initStartPointXAnimator(duration, delay, 0, 0, interpolator, xs);
    }

    /**
     * 创建startPointX动画,会删除startPoint动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initStartPointXAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... xs) {
        if(xs == null || xs.length == 0) {
            LogUtil.e(LOG_TAG, "initStartPointXAnimator:xs is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_START_POINT_X, xs);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_START_POINT);
        mAnimatorMap.put(KEY_START_POINT_X, animator);
        return animator;
    }

    /**
     * 创建startPointY动画,会删除startPoint动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initStartPointYAnimator(long duration, long delay, TimeInterpolator interpolator, float... ys) {
        return initStartPointYAnimator(duration, delay, 0, 0, interpolator, ys);
    }

    /**
     * 创建startPointY动画,会删除startPoint动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initStartPointYAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... ys) {
        if(ys == null || ys.length == 0) {
            LogUtil.e(LOG_TAG, "initStartPointYAnimator:ys is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_START_POINT_Y, ys);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_START_POINT);
        mAnimatorMap.put(KEY_START_POINT_Y, animator);
        return animator;
    }

    /**
     * 创建stopPoint动画,会删除stopPointX和stopPointY动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param pointFs
     * @return
     */
    public ObjectAnimator initStopPointAnimator(long duration, long delay, TimeInterpolator interpolator, PointF[] pointFs) {
        return initStopPointAnimator(duration, delay, 0, 0, interpolator, pointFs);
    }

    /**
     * 创建stopPoint动画,会删除stopPointX和stopPointY动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param pointFs
     * @return
     */
    public ObjectAnimator initStopPointAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, PointF[] pointFs) {
        if(pointFs == null || pointFs.length == 0) {
            LogUtil.e(LOG_TAG, "initStopPointAnimator:pointFs is null");
            return null;
        }
        Object[] objects = pointFs;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, KEY_STOP_POINT, new PointFEvaluator(), objects);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_STOP_POINT_X);
        removeAnimaotr(KEY_STOP_POINT_Y);
        mAnimatorMap.put(KEY_STOP_POINT, animator);
        return animator;
    }

    /**
     * 创建stopPointX动画,会删除stopPoint动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initStopPointXAnimator(long duration, long delay, TimeInterpolator interpolator, float... xs) {
        return initStopPointXAnimator(duration, delay, 0, 0, interpolator, xs);
    }

    /**
     * 创建stopPointX动画,会删除stopPoint动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param xs
     * @return
     */
    public ObjectAnimator initStopPointXAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... xs) {
        if(xs == null || xs.length == 0) {
            LogUtil.e(LOG_TAG, "initStopPointXAnimator:xs is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_STOP_POINT_X, xs);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_STOP_POINT);
        mAnimatorMap.put(KEY_STOP_POINT_X, animator);
        return animator;
    }

    /**
     * 创建stopPointY动画,会删除stopPoint动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initStopPointYAnimator(long duration, long delay, TimeInterpolator interpolator, float... ys) {
        return initStopPointYAnimator(duration, delay, 0, 0, interpolator, ys);
    }

    /**
     * 创建stopPointY动画,会删除stopPoint动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param ys
     * @return
     */
    public ObjectAnimator initStopPointYAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, float... ys) {
        if(ys == null || ys.length == 0) {
            LogUtil.e(LOG_TAG, "initStopPointYAnimator:ys is null");
            return null;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, KEY_STOP_POINT_Y, ys);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        removeAnimaotr(KEY_PROPERTY);
        removeAnimaotr(KEY_STOP_POINT);
        mAnimatorMap.put(KEY_STOP_POINT_Y, animator);
        return animator;
    }

    @Override
    public LineProperty getProperty() {
        return super.getProperty();
    }

    @Override
    public void setProperty(LineProperty property) {
        super.setProperty(property);
    }

    public PointF getStartPoint() {
        return mProperty.startPoint;
    }

    public void setStartPoint(PointF point) {
        mProperty.startPoint = point;
    }

    public float getStartPointX() {
        return mProperty.startPoint.x;
    }

    public void setStartPointX(float x) {
        mProperty.startPoint.x = x;
    }

    public float getStartPointY() {
        return mProperty.startPoint.y;
    }

    public void setStartPointY(float y) {
        mProperty.startPoint.y = y;
    }

    public PointF getStopPoint() {
        return mProperty.stopPoint;
    }

    public void setStopPoint(PointF point) {
        mProperty.stopPoint = point;
    }

    public float getStopPointX() {
        return mProperty.stopPoint.x;
    }

    public void setStopPointX(float x) {
        mProperty.stopPoint.x = x;
    }

    public float getStopPointY() {
        return mProperty.stopPoint.y;
    }

    public void setStopPointY(float y) {
        mProperty.stopPoint.y = y;
    }

    public static class LineProperty {
        public PointF startPoint = new PointF();
        public PointF stopPoint = new PointF();

        public LineProperty() {

        }

        public LineProperty(float startX, float startY, float stopX, float stopY) {
            startPoint.x = startX;
            startPoint.y = startY;
            stopPoint.x = stopX;
            stopPoint.y = stopY;
        }
    }
}
