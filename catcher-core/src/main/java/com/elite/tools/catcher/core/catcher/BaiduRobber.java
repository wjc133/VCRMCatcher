package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.constant.BaiduRobberStatus;
import com.elite.tools.catcher.core.dispatcher.PageDispatcher;
import com.elite.tools.catcher.core.domain.OperateInfo;
import com.elite.tools.catcher.core.manager.HttpManager;
import com.elite.tools.soar.Request;
import com.elite.tools.soar.RequestQueue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wjc133
 * DATE: 16/6/4
 * TIME: 上午11:15
 */
public class BaiduRobber implements Robber, BaiduDetailGrabber.OnItemFinishedListener {
    private BaiduRobberStatus mStatus;
    private int total = -1;
    private AtomicInteger counter = new AtomicInteger(0);
    private OnItemFinishedListener listener;
    private OnStatusChangedListener onStatusChangedListener;

    public void setOnItemFinishedListener(OnItemFinishedListener listener) {
        this.listener = listener;
    }

    public void setOnStatusChangedListener(OnStatusChangedListener listener) {
        this.onStatusChangedListener = listener;
    }

    public void init(OperateInfo info) {
        GrabberFactory.setQueue(HttpManager.INSTANCE.getRequestQueue());
        GrabberFactory.setInfo(info);
        GrabberFactory.setOnItemFinishedListener(this);
        mStatus = BaiduRobberStatus.READY;
        onStatusChangedListener.onGrabReady();
    }

    public void start() {
//        if (mStatus != BaiduRobberStatus.READY) {
//            throw new IllegalStateException("Robber must be inited before start");
//        }
        mStatus = BaiduRobberStatus.START;
        onStatusChangedListener.onGrabStart();
        PageInfoGrabber baiduPageInfoGrabber = GrabberFactory.getBaiduPageInfoGrabber();
        PageDispatcher pageDispatcher = new PageDispatcher();
        baiduPageInfoGrabber.setListener(pageDispatcher);
        baiduPageInfoGrabber.setErrorListener(pageDispatcher);
        baiduPageInfoGrabber.getTotalPage();
    }

    public void stop() {
        mStatus = BaiduRobberStatus.STOP;
        HttpManager.INSTANCE.getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            public boolean apply(Request<?> request) {
                return !request.isCanceled();
            }
        });
    }

    public void pause() {
        mStatus = BaiduRobberStatus.PAUSE;
    }

    public boolean isFinished() {
        return mStatus.equals(BaiduRobberStatus.FINISH);
    }

    public boolean isReady() {
        return mStatus.equals(BaiduRobberStatus.READY);
    }

    public boolean isGrabing() {
        return mStatus.equals(BaiduRobberStatus.START);
    }

    public boolean isStopped() {
        return mStatus.equals(BaiduRobberStatus.STOP);
    }

    public void onItemFinished(String acctId) {
        int i = counter.incrementAndGet();
        if (total <= 0) {
            total = DataCache.getTotal();
        }
        if (total > 0) {
            listener.onItemFinished(i, total);
        }
        if (i == total) {
            onStatusChangedListener.onGrabFinish();
            mStatus = BaiduRobberStatus.FINISH;
        }
    }

    public interface OnStatusChangedListener {
        void onGrabReady();

        void onGrabStart();

        void onGrabFinish();
    }

    public interface OnItemFinishedListener {
        void onItemFinished(int position, int total);
    }

}
