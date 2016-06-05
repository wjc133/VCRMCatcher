package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.OperateInfo;
import com.elite.tools.catcher.core.domain.ServerResponse;
import com.elite.tools.soar.AuthFailureError;
import com.elite.tools.soar.Request;
import com.elite.tools.soar.RequestQueue;
import com.elite.tools.soar.Response;
import com.elite.tools.soar.toolbox.GsonRequest;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by df on 16/5/12.
 */
public class BaiduIndexGrabber {
    private final String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/listAccountByPage.do";
    private RequestQueue mQueue;
    private OperateInfo mInfo;
    private Response.Listener<ServerResponse> listener;
    private Response.ErrorListener errorListener;

    public BaiduIndexGrabber(RequestQueue queue, OperateInfo info) {
        this.mQueue = queue;
        this.mInfo = info;
    }

    public void setListener(Response.Listener<ServerResponse> listener) {
        this.listener = listener;
    }

    public void setErrorListener(Response.ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void grab(int totalPage) {
        for (int i = 1; i <= totalPage; i++) {
            GsonRequest<ServerResponse> request = new GsonRequest<ServerResponse>(Request.Method.POST,
                    url, ServerResponse.class, listener, errorListener) {
                @Override
                public Map<String, String> getCookies() {
                    Map<String, String> cookies = Maps.newHashMap();
                    cookies.put("__cas__id__309", mInfo.getCasId());
                    cookies.put("__cas__st__309", mInfo.getCasSt());
                    return cookies;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = Maps.newHashMap();
                    params.put("from", "trustv-ocrm-v2/account/acclist");
                    params.put("sid", "listAccountByPage");
                    params.put("orgId", "145");
                    params.put("operatorId", mInfo.getOperatorId());
                    params.put("pageNum", "1");
                    params.put("pageSize", "10");
                    params.put("isAjax", "true");
                    return params;
                }
            };
            mQueue.add(request);
        }
    }
}
