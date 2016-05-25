package com.elite.tools.catcher.core.manager;

import com.elite.tools.soar.RequestQueue;
import com.elite.tools.soar.toolbox.Soar;

/**
 * Created by wjc133
 * DATE: 16/5/20
 * TIME: 下午7:06
 */
public enum HttpTaskManager {
    INSTANCE;

    private final RequestQueue mQueue = Soar.newRequestQueue();

    public void asyncExecTask() {


    }

    public void syncExecTask() {

    }
}
