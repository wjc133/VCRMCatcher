package com.elite.tools.catcher.core.manager;

import com.elite.tools.soar.RequestQueue;
import com.elite.tools.soar.toolbox.Soar;

/**
 * Created by wjc133
 * DATE: 16/5/28
 * TIME: 下午2:04
 */
public enum HttpManager {
    INSTANCE;

    private RequestQueue requestQueue = Soar.newRequestQueue();

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }
}
