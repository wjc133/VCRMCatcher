package com.elite.tools.catcher.core.dispatcher;

import com.elite.tools.catcher.core.catcher.BaiduIndexGrabber;
import com.elite.tools.catcher.core.catcher.DataCache;
import com.elite.tools.catcher.core.catcher.GrabberFactory;
import com.elite.tools.catcher.core.domain.Data;
import com.elite.tools.catcher.core.domain.ServerResponse;
import com.elite.tools.soar.SoarError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjc133
 * Date: 2016/6/4
 * Time: 13:34
 * Description:
 */
public class PageDispatcher implements Dispatcher<ServerResponse> {
    private static final Logger LOG = LoggerFactory.getLogger(PageDispatcher.class);

    public void onErrorResponse(SoarError soarError) {
        LOG.error("request error:{}", soarError);
    }

    public void onResponse(ServerResponse serverResponse) {
        Data data = serverResponse.getData();
        if (data == null) {
            return;
        }
        BaiduIndexGrabber indexGrabber = GrabberFactory.getBaiduIndexGrabber();
        DataCache.setTotal(data.getTotal());
        indexGrabber.grab(data.getTotalPages());
    }
}
