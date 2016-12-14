package com.xing.android.animation.vector.draw;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;

import com.xing.android.common.util.LogUtil;

import java.util.HashMap;

/**
 * 1.VectorDraw可能有多个"属性"需要"动画";
 * 2.VectorDraw支持多个ObjectAnimator作用于多个"属性",但是在duration比较短的时候,会出现多个"属性"的值在时间上
 * 的不同步,所以duration比较短的时候,尽量用一个ObjectAnimator作用于多个"属性",这时可能需要借助于MultiInterpolator
 * 不同时间段采用不同的Interpolator;
 * @see com.xing.android.animation.vector.interpolator.MultiInterpolator
 * 3.VectorDraw以及子类中的initXXX用来创建动画,返回ObjectAnimator用于在业务层设置参数或者设置监听,如果要多个
 * VectorDraw集合成AnimatorSet,需要采用getAnimatorSet()返回的ObjectAnimator
 * @see #getAnimatorSet(long, TimeInterpolator, Animator.AnimatorListener)
 * 4.该类及子类不能混淆,不然属性动画的get,set方法失效
 * Created by zhaoxx on 15/12/7.
 */
public abstract class VectorDraw<T> {

    public static final String KEY_PROPERTY = "property";
    public static final String KEY_PAINT = "paint";

    protected final String LOG_TAG = this.getClass().getSimpleName();

    /**
     * 用来保存该VectorDraw属性
     */
    protected T mProperty = newProperty();
    /**
     * 画笔
     */
    protected Paint mPaint;
    /**
     * 由于整个Drawable是画出来的,该值用来表明是否需要画该VectorDraw
     */
    protected boolean isDrawing = false;
    /**
     * 如果该VectorDraw的动画结束,是否继续画(按照动画结束的值)
     */
    protected boolean isDrawingIfEnd = true;
    /**
     * 用来保存某个动画值对应的动画,确保某个值只有一个动画
     */
    protected HashMap<String, Animator> mAnimatorMap = new HashMap<String, Animator>();

    protected final Animator.AnimatorListener mDefaultListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            isDrawing = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if(!isDrawingIfEnd) {
               isDrawing = false;
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    /**
     * 创建属性对象
     * @return
     */
    protected abstract T newProperty();

    protected abstract TypeEvaluator<T> newTypeEvaluator();

    /**
     * 在画布上画出该VectorDraw
     * @param canvas
     */
    public abstract void draw(Canvas canvas);

    /**
     * 该VectorDraw是否处于动画中
     * @see Animator#isRunning()
     * @return
     */
    public boolean isRunning() {
        for(Animator animator : mAnimatorMap.values()) {
            if(animator != null && animator.isRunning()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否需要画该VectorDraw
     * @return
     */
    public boolean isDrawing() {
        return isDrawing;
    }

    /**
     * 设置isDrawing
     * @param value
     */
    public void setDrawing(boolean value) {
        isDrawing = value;
    }

    /**
     * 动画结束之后,是否继续画该VectorDraw
     * @return
     */
    public boolean isDrawingIfEnd() {
        return isDrawingIfEnd;
    }

    /**
     * 设置isDrawingIfEnd
     * @param value
     */
    public void setDrawingIfEnd(boolean value) {
        isDrawingIfEnd = value;
    }

    /**
     * 创建属性动画(不包括画笔)
     * @param duration
     * @param delay
     * @param interpolator
     * @param properties
     * @return 返回该VectorDraw的整个动画(不包括画笔动画)
     */
    public ObjectAnimator initPropertyAnimator(long duration, long delay, TimeInterpolator interpolator, T[] properties) {
        return initPropertyAnimator(duration, delay, 0, 0, interpolator, properties);
    }

    /**
     * 创建属性动画(不包括画笔)
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param properties
     * @return
     */
    public ObjectAnimator initPropertyAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, T[] properties) {
        if(properties == null || properties.length == 0) {
            LogUtil.e(LOG_TAG, "initPropertyAnimator:properties is null");
            return null;
        }
        Object[] objects = properties;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, KEY_PROPERTY, newTypeEvaluator(), objects);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        Animator paintAnimator = null;
        if(mAnimatorMap.containsKey(KEY_PAINT)) {
            paintAnimator = mAnimatorMap.get(KEY_PAINT);
        }
        mAnimatorMap.clear();
        mAnimatorMap.put(KEY_PROPERTY, animator);
        if(paintAnimator != null) {
            mAnimatorMap.put(KEY_PAINT, paintAnimator);
        }
        return animator;
    }

    /**
     * 创建画笔动画
     * @param duration
     * @param delay
     * @param interpolator
     * @param typeEvaluator
     * @param paints
     * @return
     */
    public ObjectAnimator initPaintAnimator(long duration, long delay, TimeInterpolator interpolator, TypeEvaluator<Paint> typeEvaluator, Paint[] paints) {
        return initPaintAnimator(duration, delay, 0, 0, interpolator, typeEvaluator, paints);
    }

    /**
     * 创建画笔动画
     * @param duration
     * @param delay
     * @param repeatCount
     * @param repeatMode
     * @param interpolator
     * @param typeEvaluator 画笔的动画需要业务层自己定制TypeEvaluator
     * @param paints
     * @return
     */
    public ObjectAnimator initPaintAnimator(long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator, TypeEvaluator<Paint> typeEvaluator, Paint... paints) {
        if(paints == null || paints.length == 0) {
            LogUtil.e(LOG_TAG, "initPaintAnimator:paints is null");
            return null;
        } else if(typeEvaluator == null) {
            LogUtil.e(LOG_TAG, "initPaintAnimator:typeEvaluator is null");
            return null;
        }
        Object[] objects = paints;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, KEY_PAINT, typeEvaluator, objects);
        setAnimatorParam(animator, duration, delay, repeatCount, repeatMode, interpolator);
        mAnimatorMap.put(KEY_PAINT, animator);
        return animator;
    }

    /**
     * 获取该VectorDraw的AnimatorSet
     * @param duration
     * @param interpolator
     * @param listener
     * @return
     */
    public AnimatorSet getAnimatorSet(long duration, TimeInterpolator interpolator, Animator.AnimatorListener listener) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(mAnimatorMap.values());
        if(duration > 0) {
            set.setDuration(duration);
        }
        if(interpolator != null) {
            set.setInterpolator(interpolator);
        }
        set.addListener(mDefaultListener);
        if(listener != null) {
            set.addListener(listener);
        }
        return set;
    }

    /**
     * 设置动画参数
     * @param animator 要设置的动画
     * @param duration 动画时长
     * @param delay 动画延时
     * @param interpolator 动画插值器
     */
    protected void setAnimatorParam(Animator animator, long duration, long delay, TimeInterpolator interpolator) {
        if(animator == null) {
            return;
        }
        if(duration > 0) {
            animator.setDuration(duration);
        }
        if(delay > 0) {
            animator.setStartDelay(delay);
        }
        if(interpolator != null) {
            animator.setInterpolator(interpolator);
        }
    }

    /**
     * 设置动画参数,用于设置ValueAnimator
     * @param animator 要设置的动画
     * @param duration 动画时长
     * @param delay 动画延时
     * @param interpolator 动画插值器
     * @param repeatCount 重复次数,默认是0,要注意-1,-1是无限重复
     *                    @see ValueAnimator#INFINITE
     * @param repeatMode 重复模式
     *                   @see ValueAnimator#RESTART
     *                   @see ValueAnimator#REVERSE
     */
    protected void setAnimatorParam(ValueAnimator animator, long duration, long delay, int repeatCount, int repeatMode, TimeInterpolator interpolator) {
        if(animator == null) {
            return;
        }
        setAnimatorParam(animator, duration, delay, interpolator);
        if(repeatCount >= 0 || repeatCount == ValueAnimator.INFINITE) {
            animator.setRepeatCount(repeatCount);
        }
        if(repeatMode > 0) {
            animator.setRepeatMode(repeatMode);
        }
    }

    /**
     * 根据Key删除对应的动画,
     * 某些动画会存在交叉,比如point是pointX和pointY的集合,所以设置point的动画,会删除pointX和pointY,
     * 同样,设置pointX/pointY,也会删除point的动画
     * @param key
     */
    protected void removeAnimaotr(String key) {
        if(TextUtils.isEmpty(key)) {
            return;
        }
        if(mAnimatorMap.containsKey(key)) {
            mAnimatorMap.remove(key);
        }
    }

    /**
     * 取消动画
     */
    public void cancel() {
        for(Animator animator : mAnimatorMap.values()) {
            if(animator != null) {
                animator.cancel();
            }
        }
    }

    /**
     * 结束动画
     */
    public void end() {
        for(Animator animator : mAnimatorMap.values()) {
            if(animator != null) {
                animator.end();
            }
        }
    }

    /**
     * 设置循环次数
     * @param count
     */
    public void setRepeatCount(int count) {
        if(count >= 0 || count == ValueAnimator.INFINITE) {
            for(Animator animator : mAnimatorMap.values()) {
                if(animator != null && animator instanceof ValueAnimator) {
                    ((ValueAnimator) animator).setRepeatCount(count);
                }
            }
        }
    }

    public T getProperty() {
        return mProperty;
    }

    public void setProperty(T property) {
        mProperty = property;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }
}
