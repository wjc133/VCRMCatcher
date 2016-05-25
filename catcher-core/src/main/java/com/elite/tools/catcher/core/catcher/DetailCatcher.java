package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.PhoneData;
import com.elite.tools.catcher.core.manager.HttpTaskManager;
import com.elite.tools.catcher.core.parse.JsonParser;
import com.elite.tools.soar.AuthFailureError;
import com.elite.tools.soar.Request;
import com.elite.tools.soar.RequestQueue;
import com.elite.tools.soar.toolbox.GsonRequest;
import com.elite.tools.soar.toolbox.RequestFuture;
import com.elite.tools.soar.toolbox.StringRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by df on 16/5/15.
 */
public class DetailCatcher {

    private final Pattern PATTERN_PHONE = Pattern.compile("(?<=data\\.dataList\\s=\\s).+(?=;)");
    private String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/account/acctinfo.htm";
    private RequestQueue mQueue = HttpTaskManager.INSTANCE.getmQueue();

    public List<PhoneData> grab(final String token, final String acctId, String casId, String casSt) {
        final String cookie = "__cas__id__309=" + casId + ";__cas__st__309=" + casSt;
        RequestFuture<String> future = RequestFuture.newFuture();
        StringRequest request = new StringRequest(Request.Method.POST, url, future, future) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = Maps.newHashMap();
                params.put("token", token);
                params.put("isFrag", "true");
                params.put("acctId", acctId);
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
            String response = future.get();
            return parseHtml(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<PhoneData> parseHtml(String responseHtml) {
        Matcher matcher = PATTERN_PHONE.matcher(responseHtml);
        if (matcher.find()) {
            String phoneStr = matcher.group();
            try {
                return JsonParser.getPhoneDatas(phoneStr);
            } catch (Exception e) {
                System.out.println("has an error: phoneStr=" + phoneStr);
                e.printStackTrace();
            }
        }
        return null;
    }
}
