package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.OperateInfo;
import com.elite.tools.catcher.core.domain.PhoneData;
import com.elite.tools.catcher.core.parse.JsonParser;
import com.elite.tools.soar.*;
import com.elite.tools.soar.toolbox.StringRequest;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by df on 16/5/15.
 */
public class BaiduDetailGrabber {

    private final Pattern PATTERN_PHONE = Pattern.compile("(?<=data\\.dataList\\s=\\s).+(?=;)");
    private String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/account/acctinfo.htm";

    private RequestQueue mQueue;
    private OperateInfo mInfo;

    private OnItemFinishedListener listener;

    private static final Logger LOG = LoggerFactory.getLogger(BaiduDetailGrabber.class);

    public BaiduDetailGrabber(RequestQueue queue, OperateInfo info) {
        this.mQueue = queue;
        this.mInfo = info;
    }

    public void setListener(OnItemFinishedListener listener) {
        this.listener = listener;
    }

    public void grab(final String acctId) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String s) {
                List<PhoneData> phoneDatas = parseHtml(s);
                DataCache.addPhoneData(acctId, phoneDatas);
                listener.onItemFinished(acctId);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(SoarError soarError) {
                DataCache.addErrorAcct(acctId);
                LOG.error("has an error:{}", soarError);
            }
        }) {
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
                params.put("token", mInfo.getToken());
                params.put("isFrag", "true");
                params.put("acctId", acctId);
                return params;
            }
        };
        mQueue.add(request);
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

    public interface OnItemFinishedListener {
        void onItemFinished(String acctId);
    }
}
