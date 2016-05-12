package com.elite.tools.catcher.core.domain;

/**
 * Created by df on 16/5/12.
 */
public class Content {
//    "newTradeNameLevel1": "电子电工",
//            "newTradeNameLevel2": "电机设备",
//            "newTradeNameLevel3": "电动机",
//            "operatorId": "",
//            "vstarTypeName": "真实性加Ｖ",
//            "rechargeDate": "2016-11-09",
//            "acctId": 319337,
//            "cityName": "广州",
//            "agentId": "",
//            "provinceName": "广东",
//            "companyName": "广州经济技术开发区湘潭机电维修部",
//            "newTradeIdLevel3": "",
//            "addressDetail": "广州市黄埔区广江路398号广州冶炼厂内",
//            "newTradeIdLevel1": "",
//            "newTradeIdLevel2": "",
//            "vstarStatusName": "生效",
//            "contacts": "",
//            "siteUrl": "http://www.xt-motor.com",
//            "operatorName": "",
//            "agentName": "",
//            "acctName": "xt-motor1",
//            "assignStatus": "",
//            "acctStatus": "账户正常生效"
    private String newTradeNameLevel1;   //行业一级分类
    private String newTradeNameLevel2;   //行业二级分类
    private String newTradeNameLevel3;   //行业三级分类
    private String operatorId;   //待定...
    private String vstarTypeName; //加V方式
    private String rechargeDate;   //费用截止日期
    private String acctId;   //账户ID
    private String cityName;  //城市名称
    private String agentId;
    private String provinceName;  //省份
    private String companyName;   //公司名称
    private String newTradeIdLevel1;
    private String newTradeIdLevel2;
    private String newTradeIdLevel3;
    private String addressDetail;  //详细地址
    private String vstarStatusName;  //加V状态
    private String contacts;
    private String siteUrl;  //网址
    private String operatorName;
    private String agentName;
    private String acctName;  //账户名称
    private String assignStatus;
    private String acctStatus;  //账户状态


    public String getNewTradeNameLevel1() {
        return newTradeNameLevel1;
    }

    public void setNewTradeNameLevel1(String newTradeNameLevel1) {
        this.newTradeNameLevel1 = newTradeNameLevel1;
    }

    public String getNewTradeNameLevel2() {
        return newTradeNameLevel2;
    }

    public void setNewTradeNameLevel2(String newTradeNameLevel2) {
        this.newTradeNameLevel2 = newTradeNameLevel2;
    }

    public String getNewTradeNameLevel3() {
        return newTradeNameLevel3;
    }

    public void setNewTradeNameLevel3(String newTradeNameLevel3) {
        this.newTradeNameLevel3 = newTradeNameLevel3;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getVstarTypeName() {
        return vstarTypeName;
    }

    public void setVstarTypeName(String vstarTypeName) {
        this.vstarTypeName = vstarTypeName;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNewTradeIdLevel1() {
        return newTradeIdLevel1;
    }

    public void setNewTradeIdLevel1(String newTradeIdLevel1) {
        this.newTradeIdLevel1 = newTradeIdLevel1;
    }

    public String getNewTradeIdLevel2() {
        return newTradeIdLevel2;
    }

    public void setNewTradeIdLevel2(String newTradeIdLevel2) {
        this.newTradeIdLevel2 = newTradeIdLevel2;
    }

    public String getNewTradeIdLevel3() {
        return newTradeIdLevel3;
    }

    public void setNewTradeIdLevel3(String newTradeIdLevel3) {
        this.newTradeIdLevel3 = newTradeIdLevel3;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getVstarStatusName() {
        return vstarStatusName;
    }

    public void setVstarStatusName(String vstarStatusName) {
        this.vstarStatusName = vstarStatusName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(String assignStatus) {
        this.assignStatus = assignStatus;
    }

    public String getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(String acctStatus) {
        this.acctStatus = acctStatus;
    }

    @Override
    public String toString() {
        return "Content{" +
                "newTradeNameLevel1='" + newTradeNameLevel1 + '\'' +
                ", newTradeNameLevel2='" + newTradeNameLevel2 + '\'' +
                ", newTradeNameLevel3='" + newTradeNameLevel3 + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", vstarTypeName='" + vstarTypeName + '\'' +
                ", rechargeDate='" + rechargeDate + '\'' +
                ", acctId='" + acctId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", agentId='" + agentId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", newTradeIdLevel1='" + newTradeIdLevel1 + '\'' +
                ", newTradeIdLevel2='" + newTradeIdLevel2 + '\'' +
                ", newTradeIdLevel3='" + newTradeIdLevel3 + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", vstarStatusName='" + vstarStatusName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", agentName='" + agentName + '\'' +
                ", acctName='" + acctName + '\'' +
                ", assignStatus='" + assignStatus + '\'' +
                ", acctStatus='" + acctStatus + '\'' +
                '}';
    }
}
