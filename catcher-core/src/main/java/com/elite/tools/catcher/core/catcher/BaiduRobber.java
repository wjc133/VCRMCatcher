package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.constant.BaiduRobberStatus;
import com.elite.tools.catcher.core.manager.HttpManager;
import com.elite.tools.soar.RequestQueue;

/**
 * Created by wjc133
 * DATE: 16/6/4
 * TIME: 上午11:15
 */
public class BaiduRobber implements Robber {
    private BaiduRobberStatus mStatus;
    private RequestQueue mQueue;

    public BaiduRobber() {
        init();
    }

    private void init() {
        mStatus = BaiduRobberStatus.READY;
        mQueue = HttpManager.INSTANCE.getRequestQueue();
    }

    public void start() {
        if (mStatus != BaiduRobberStatus.READY) {
            throw new IllegalStateException("Robber must be inited before start");
        }
        mStatus = BaiduRobberStatus.START;

    }

    public void stop() {
        mStatus = BaiduRobberStatus.STOP;

    }

    public void pause() {

    }

    public boolean isFinished() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public boolean isGrabing() {
        return false;
    }

    interface OnStatusChangedListener {
        void onGrabReady();

        void onGrabStart();

        void onGrabFinish();
    }
}
