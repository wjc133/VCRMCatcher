package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.OperateInfo;
import com.elite.tools.catcher.core.domain.ServerResponse;
import com.elite.tools.soar.AuthFailureError;
import com.elite.tools.soar.Request;
import com.elite.tools.soar.RequestQueue;
import com.elite.tools.soar.Response;
import com.elite.tools.soar.toolbox.GsonRequest;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by wjc133
 * Date: 2016/6/4
 * Time: 12:24
 * Description:
 */
public class PageInfoGrabber {
    private final String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/listAccountByPage.do";
    private RequestQueue mQueue;
    private OperateInfo operateInfo;

    private Response.ErrorListener errorListener;
    private Response.Listener<ServerResponse> listener;

    private static final Logger LOG = LoggerFactory.getLogger(PageInfoGrabber.class);

    public PageInfoGrabber(RequestQueue queue, OperateInfo info) {
        this.mQueue = queue;
        this.operateInfo = info;
    }

    public void setListener(Response.Listener<ServerResponse> listener) {
        this.listener = listener;
    }

    public void setErrorListener(Response.ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void getTotalPage() {
        if (mQueue == null || operateInfo == null) {
            LOG.warn("has empty queue or info.");
            return;
        }
        GsonRequest<ServerResponse> request = new GsonRequest<ServerResponse>(Request.Method.POST,
                url, ServerResponse.class, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = Maps.newHashMap();
                params.put("from", "trustv-ocrm-v2/account/acclist");
                params.put("sid", "listAccountByPage");
                params.put("orgId", "145");
                params.put("operatorId", operateInfo.getOperatorId());
                params.put("pageNum", "1");
                params.put("pageSize", "10");
                params.put("isAjax", "true");
                return params;
            }

            @Override
            public Map<String, String> getCookies() {
                Map<String, String> cookies = Maps.newHashMap();
                cookies.put("__cas__id__309", operateInfo.getCasId());
                cookies.put("__cas__st__309", operateInfo.getCasSt());
                return cookies;
            }
        };
        mQueue.add(request);
    }

}
