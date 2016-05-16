package com.elite.tools.catcher.client.domain;

import java.util.List;

/**
 * Created by df on 16/5/12.
 */
public class ContentVo {
    private String acctId;   //账户ID
    private String companyName;   //公司名称
    private String siteUrl;  //网址
    private String rechargeDate;   //费用截止日期
    private List<PhoneDataVo> phoneDataVos; //Phone信息

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

    public List<PhoneDataVo> getPhoneDataVos() {
        return phoneDataVos;
    }

    public void setPhoneDataVos(List<PhoneDataVo> phoneDataVos) {
        this.phoneDataVos = phoneDataVos;
    }

    @Override
    public String toString() {
        return "ContentVo{" +
                "acctId='" + acctId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", rechargeDate='" + rechargeDate + '\'' +
                '}';
    }
}
