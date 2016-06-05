package com.elite.tools.catcher.core.dispatcher;

import com.elite.tools.soar.Response;

/**
 * Created by wjc133
 * Date: 2016/6/4
 * Time: 13:33
 * Description:
 */
public interface Dispatcher<T> extends Response.Listener<T>, Response.ErrorListener {
}
