package com.elite.tools.catcher.core.manager;

import com.elite.tools.soar.Request;
import com.elite.tools.soar.RequestQueue;
import com.elite.tools.soar.toolbox.GsonRequest;
import com.elite.tools.soar.toolbox.Soar;

import java.util.Map;

/**
 * Created by wjc133
 * DATE: 16/5/20
 * TIME: 下午7:06
 */
public enum HttpTaskManager {
    INSTANCE;

    private final RequestQueue mQueue = Soar.newRequestQueue();

    public RequestQueue getmQueue() {
        return mQueue;
    }

    public void asyncExecTask() {


    }

    public <T> T syncExecTask(String url, int method, Map<String,String> params,Class<T> clz) {
//        Request request=new GsonRequ
        return null;
    }
}
