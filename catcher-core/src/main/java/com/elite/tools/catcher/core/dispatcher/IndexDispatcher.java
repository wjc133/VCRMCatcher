package com.elite.tools.catcher.core.dispatcher;

import com.elite.tools.catcher.core.catcher.BaiduDetailGrabber;
import com.elite.tools.catcher.core.catcher.DataCache;
import com.elite.tools.catcher.core.catcher.GrabberFactory;
import com.elite.tools.catcher.core.domain.Content;
import com.elite.tools.catcher.core.domain.ServerResponse;
import com.elite.tools.soar.SoarError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by wjc133
 * Date: 2016/6/4
 * Time: 14:46
 * Description:
 */
public class IndexDispatcher implements Dispatcher<ServerResponse> {
    private static final Logger LOG = LoggerFactory.getLogger(PageDispatcher.class);

    public void onErrorResponse(SoarError soarError) {
        LOG.error("request error:{}", soarError);
    }

    public void onResponse(ServerResponse serverResponse) {
        if (serverResponse == null || serverResponse.getData() == null) {
            return;
        }
        List<Content> contents = serverResponse.getData().getContent();
        BaiduDetailGrabber grabber = GrabberFactory.getBaiduDetailGrabber();
        for (Content content : contents) {
            DataCache.addContent(content);
            grabber.grab(content.getAcctId());
        }
    }
}
