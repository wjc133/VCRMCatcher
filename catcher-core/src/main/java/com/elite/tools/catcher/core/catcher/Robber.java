package com.elite.tools.catcher.core.catcher;

/**
 * Created by wjc133
 * DATE: 16/6/3
 * TIME: 下午7:55
 * Command Role
 */
public interface Robber {
    void start();

    void stop();

    void pause();

    boolean isFinished();

    boolean isReady();

    boolean isGrabing();
}
