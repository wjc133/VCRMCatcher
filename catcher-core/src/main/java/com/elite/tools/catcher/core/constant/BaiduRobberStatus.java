package com.elite.tools.catcher.core.constant;

import com.google.common.collect.Maps;

import java.util.EnumSet;
import java.util.Map;

/**
 * Created by wjc133
 * DATE: 16/6/3
 * TIME: 下午8:01
 */
public enum BaiduRobberStatus {
    READY(101, "ready"),
    START(102, "start"),
    PAUSE(103, "pause"),
    STOP(104, "stop"),
    FINISH(105, "finish"),
    UNKNOWN(999, "unknown");


    private Integer code;
    private static Map<Integer, BaiduRobberStatus> statusMap = Maps.newHashMap();
    private String message;

    static {
        for (BaiduRobberStatus status : EnumSet.allOf(BaiduRobberStatus.class)) {
            statusMap.put(status.code, status);
        }
    }

    static {
        for (BaiduRobberStatus status : EnumSet.allOf(BaiduRobberStatus.class)) {
            statusMap.put(status.code, status);
        }
    }

    BaiduRobberStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaiduRobberStatus valueOf(Integer code) {
        BaiduRobberStatus frameType = statusMap.get(code);
        if (frameType != null) {
            return frameType;
        }
        return UNKNOWN;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }

    public boolean compare(Integer c) {
        return c != null && code.equals(c);
    }
}
