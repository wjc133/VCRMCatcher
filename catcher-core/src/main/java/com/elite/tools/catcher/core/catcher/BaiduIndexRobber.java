package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.ServerResponse;
import com.elite.tools.catcher.core.manager.HttpTaskManager;
import com.elite.tools.soar.AuthFailureError;
import com.elite.tools.soar.Request;
import com.elite.tools.soar.RequestQueue;
import com.elite.tools.soar.toolbox.GsonRequest;
import com.elite.tools.soar.toolbox.RequestFuture;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by df on 16/5/12.
 */
public class BaiduIndexRobber {
    private final String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/listAccountByPage.do";
    private RequestQueue mQueue = HttpTaskManager.INSTANCE.getmQueue();

    public ServerResponse grab(String casId, String casSt, final String pageNum, final String operatorId) {
        final String cookie = "__cas__id__309=" + casId + ";__cas__st__309=" + casSt;
        RequestFuture<ServerResponse> future = RequestFuture.newFuture();
        GsonRequest<ServerResponse> request = new GsonRequest<ServerResponse>(Request.Method.POST, url, ServerResponse.class,
                future, future) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = Maps.newHashMap();
                params.put("from", "trustv-ocrm-v2/account/acclist");
                params.put("sid", "listAccountByPage");
                params.put("orgId", "145");
                params.put("operatorId", operatorId);
                params.put("pageNum", pageNum);
                params.put("pageSize", "10");
                params.put("isAjax", "true");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = Maps.newHashMap();
                headers.put("Cookie", cookie);
                return headers;
            }
        };
        mQueue.add(request);
        try {
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
