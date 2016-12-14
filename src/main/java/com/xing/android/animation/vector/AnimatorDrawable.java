package com.xing.android.animation.vector;

import android.animation.Animator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import com.xing.android.animation.vector.draw.VectorDraw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoxx on 15/12/7.
 */
public class AnimatorDrawable extends Drawable {

    private final int MSG_INVALIDATE = 0;

    private final long DEFAULT_ANIMATOR_FRAME_DELAY = 10;

    /**
     * 整个Drawable的动画
     */
    private Animator mAnimator;
    /**
     * 分拆出来的VectorDraw的list
     */
    private List<VectorDraw> mVectorDrawList = new ArrayList<VectorDraw>();

    /**
     * 是否整个动画还在运行,如果结束,则不发送MSG_INVALIDATE进行更新
     */
    private boolean isRunning = false;
    /**
     * 回调
     */
    private AnimatorDrawableListener mListener;

    private long mAnimatorFrameDelay = DEFAULT_ANIMATOR_FRAME_DELAY;
    /**
     * 动画监听,主要用于设置isRunning
     */
    private Animator.AnimatorListener listener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            isRunning = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            isRunning = false;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            isRunning = false;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            isRunning = true;
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_INVALIDATE:
                    invalidateSelf();
                    break;
            }
        }
    };

    @Override
    public void draw(Canvas canvas) {
        if(mListener != null) {
            mListener.preDraw(canvas, getBounds());
        }
        for(VectorDraw vectorDraw : mVectorDrawList) {
            if(vectorDraw != null && vectorDraw.isDrawing()) {
                vectorDraw.draw(canvas);
            }
        }
        if(isRunning) {
            mHandler.sendEmptyMessageDelayed(MSG_INVALIDATE, mAnimatorFrameDelay);
        }
        if(mListener != null) {
            mListener.postDraw(canvas, getBounds());
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if(mListener != null) {
            mListener.onBoundsChange(bounds);
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setVectorDrawList(List<VectorDraw> list) {
        this.mVectorDrawList.addAll(list);
    }

    public void setAnimator(Animator animator) {
        mAnimator = animator;
        if(mAnimator != null) {
            mAnimator.addListener(listener);
        }
    }

    public void clear() {
        mVectorDrawList.clear();
        mAnimator = null;
    }

    public void setDrawableListener(AnimatorDrawableListener listener) {
        mListener = listener;
    }

    public void setAnimatorFrameDelay(long delay) {
        mAnimatorFrameDelay = delay;
    }

    public void start() {
        if(mAnimator != null) {
            mAnimator.start();
            mHandler.sendEmptyMessage(MSG_INVALIDATE);
        }
    }

    /**
     * 结束整个动画,需要注意的是,这里结束的是当前正在运行的动画,如果未开始的动画,属性的值是不会变成endValue
     */
    public void end() {
        if(mAnimator != null) {
            mAnimator.end();
        }
    }

    /**
     * 取消整个动画,当前运行的动画的属性值会停留在cancel时的值
     */
    public void cancel() {
        if(mAnimator != null) {
            mAnimator.cancel();
        }
    }

    /**
     * 用于单次刷新Drawable
     */
    public void refresh() {
        mHandler.sendEmptyMessage(MSG_INVALIDATE);
    }

    /**
     * 用于Activity的onDestory时,把MSG全都remove掉,避免内存泄露
     */
    public void onDestory() {
        end();
        mHandler.removeMessages(MSG_INVALIDATE);
    }

    public interface AnimatorDrawableListener {
        void onBoundsChange(Rect rect);
        void preDraw(Canvas canvas, Rect bounds);
        void postDraw(Canvas canvas, Rect bounds);
    }
}
