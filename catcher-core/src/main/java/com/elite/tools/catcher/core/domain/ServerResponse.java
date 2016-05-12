package com.elite.tools.catcher.core.domain;

/**
 * Created by df on 16/5/12.
 */
public class ServerResponse {
    private int status;
    private Data data;
    private String statusInfo;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", data=" + data +
                ", statusInfo='" + statusInfo + '\'' +
                '}';
    }
}
