package com.xing.android.animation.vector.interpolator;

import android.view.animation.Interpolator;

import com.xing.android.common.util.LogUtil;

/**
 * 一个动画可以分段差值计算,至少两个阶段,每个阶段可以用用单独的插值器,但是插值器返回的值需在0-1之间
 * Created by zhaoxx on 15/12/10.
 */
public class MultiInterpolator implements Interpolator {

    protected final String LOG_TAG = this.getClass().getSimpleName();

    protected int mStageCount;
    protected int mTotalWeight;
    protected int[] mStageWeights;
    protected Interpolator[] mStageInterpolators;

    /**
     * @param weights 各段动画时间在总时间中的权重
     * @param interpolators 各段动画的插值器,如果为null,则默认线性插值
     */
    public MultiInterpolator(int[] weights, Interpolator[] interpolators) {
        if(weights == null) {
            LogUtil.e(LOG_TAG, "weights is null");
            return;
        } else if(weights.length < 2) {
            LogUtil.e(LOG_TAG, "invalid length = " + weights.length);
            return;
        }
        mStageWeights = weights;
        mStageInterpolators = interpolators;
        mStageCount = mStageWeights.length;
        for(int i : mStageWeights) {
            mTotalWeight += i;
        }
    }

    @Override
    public float getInterpolation(float input) {
        int currentStageWeight = 0;
        float result = 0f;
        for(int i = 0 ; i < mStageCount ; i++) {
            currentStageWeight += mStageWeights[i];
            if(input < (float)currentStageWeight / mTotalWeight) {
                result = (float) i / mStageCount;
                float currentInput = (input - (float)(currentStageWeight - mStageWeights[i]) / mTotalWeight) * mTotalWeight / mStageWeights[i];
                if(mStageInterpolators != null && i < mStageInterpolators.length && mStageInterpolators[i] != null) {
                    currentInput = mStageInterpolators[i].getInterpolation(currentInput);
                }
                result += currentInput / mStageCount;
                break;
            }
        }
        return result;
    }
}
