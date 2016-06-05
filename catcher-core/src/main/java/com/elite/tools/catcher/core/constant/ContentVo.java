package com.elite.tools.catcher.core.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by df on 16/5/12.
 */
public class ContentVo {
    private String acctId;   //账户ID
    private String companyName;   //公司名称
    private String siteUrl;  //网址
    private String rechargeDate;   //费用截止日期
    private String acctName;  //账户名称
    private String addressDetail;  //详细地址
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

    public String getPhoneDatas() {
        StringBuilder builder = new StringBuilder();
        if (phoneDataVos == null) {
            return null;
        }
        for (PhoneDataVo phoneDataVo : phoneDataVos) {
            String name = phoneDataVo.getName();
            if (StringUtils.isEmpty(name)) {
                continue;
            }
            builder.append(name).append(" ");
            builder.append(phoneDataVo.getMobilePhone()).append(" ");
            builder.append(phoneDataVo.getTelephone()).append(" ");
            builder.append(phoneDataVo.getEmail()).append("  ");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "ContentVo{" +
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
