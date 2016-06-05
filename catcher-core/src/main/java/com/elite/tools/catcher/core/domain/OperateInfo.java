package com.elite.tools.catcher.core.domain;

/**
 * Created by wjc133
 * Date: 2016/6/4
 * Time: 12:43
 * Description:
 */
public class OperateInfo {
    private String operatorId;
    private String casId;
    private String casSt;
    private String token;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getCasId() {
        return casId;
    }

    public void setCasId(String casId) {
        this.casId = casId;
    }

    public String getCasSt() {
        return casSt;
    }

    public void setCasSt(String casSt) {
        this.casSt = casSt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
