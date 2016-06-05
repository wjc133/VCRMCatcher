package com.elite.tools.catcher.client.bootstrap;

import com.elite.tools.catcher.core.manager.HttpManager;
import com.elite.tools.soar.toolbox.Soar;

/**
 * Created by wjc133
 * DATE: 16/6/4
 * TIME: 上午11:52
 */
public class ApplicationConfig {
    public static void init() {
        HttpManager.INSTANCE.setRequestQueue(Soar.newRequestQueue());
    }

    public static void showUI() {

    }
}
