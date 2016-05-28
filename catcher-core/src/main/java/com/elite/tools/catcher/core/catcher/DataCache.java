package com.elite.tools.catcher.core.catcher;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wjc133
 * DATE: 16/5/27
 * TIME: 下午11:24
 */
public class DataCache {
    private static List<String> errorAcct = Lists.newArrayList();

    public static void addErrorAcct(String acctId) {
        errorAcct.add(acctId);
    }

    public static String getErrorAccts() {
        StringBuffer buffer = new StringBuffer();
        for (String s : errorAcct) {
            buffer.append(s).append(" ");
        }
        return buffer.toString();
    }
}
