package com.elite.tools.catcher.core.domain;

import java.util.List;

/**
 * Created by wjc133
 * Date: 2016/6/4
 * Time: 15:10
 * Description:
 */
public class AcctInfo {
    private String acctId;   //账户ID
    private String companyName;   //公司名称
    private String siteUrl;  //网址
    private String rechargeDate;   //费用截止日期
    private String acctName;  //账户名称
    private String addressDetail;  //详细地址
    private List<PhoneData> phoneDataVos; //Phone信息


    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public List<PhoneData> getPhoneDataVos() {
        return phoneDataVos;
    }

    public void setPhoneDataVos(List<PhoneData> phoneDataVos) {
        this.phoneDataVos = phoneDataVos;
    }

    @Override
    public String toString() {
        return "AcctInfo{" +
                "acctId='" + acctId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", rechargeDate='" + rechargeDate + '\'' +
                ", acctName='" + acctName + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", phoneDataVos=" + phoneDataVos +
                '}';
    }
}
