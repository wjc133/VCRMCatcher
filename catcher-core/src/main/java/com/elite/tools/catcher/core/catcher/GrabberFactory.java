package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.dispatcher.IndexDispatcher;
import com.elite.tools.catcher.core.domain.OperateInfo;
import com.elite.tools.soar.RequestQueue;

/**
 * Created by wjc133
 * Date: 2016/6/4
 * Time: 14:37
 * Description:
 */
public class GrabberFactory {
    private static OperateInfo info;
    private static RequestQueue queue;
    private static BaiduIndexGrabber baiduIndexGrabber;
    private static BaiduDetailGrabber baiduDetailGrabber;
    private static PageInfoGrabber pageInfoGrabber;
    private static BaiduDetailGrabber.OnItemFinishedListener onItemFinishedListener;

    public static void setOnItemFinishedListener(BaiduDetailGrabber.OnItemFinishedListener listener) {
        onItemFinishedListener = listener;
    }

    public static void setInfo(OperateInfo info) {
        GrabberFactory.info = info;
    }

    public static void setQueue(RequestQueue queue) {
        GrabberFactory.queue = queue;
    }

    public static BaiduIndexGrabber getBaiduIndexGrabber() {
        if (baiduIndexGrabber == null) {
            synchronized (GrabberFactory.class) {
                if (baiduIndexGrabber == null) {
                    baiduIndexGrabber = new BaiduIndexGrabber(queue, info);
                    IndexDispatcher dispatcher = new IndexDispatcher();
                    baiduIndexGrabber.setListener(dispatcher);
                    baiduIndexGrabber.setErrorListener(dispatcher);
                }
            }
        }
        return baiduIndexGrabber;
    }

    public static PageInfoGrabber getBaiduPageInfoGrabber() {
        if (pageInfoGrabber == null) {
            synchronized (GrabberFactory.class) {
                if (pageInfoGrabber == null) {
                    pageInfoGrabber = new PageInfoGrabber(queue, info);
                }
            }
        }
        return pageInfoGrabber;
    }

    public static BaiduDetailGrabber getBaiduDetailGrabber() {
        if (baiduDetailGrabber == null) {
            synchronized (GrabberFactory.class) {
                if (baiduDetailGrabber == null) {
                    baiduDetailGrabber = new BaiduDetailGrabber(queue, info);
                    baiduDetailGrabber.setListener(onItemFinishedListener);
                }
            }
        }
        return baiduDetailGrabber;
    }
}
