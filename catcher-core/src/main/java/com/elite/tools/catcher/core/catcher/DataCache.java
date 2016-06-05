package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.constant.ContentVo;
import com.elite.tools.catcher.core.constant.PhoneDataVo;
import com.elite.tools.catcher.core.domain.Content;
import com.elite.tools.catcher.core.domain.PhoneData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by wjc133
 * DATE: 16/5/27
 * TIME: 下午11:24
 */
public class DataCache {
    private static List<String> errorAcct = Lists.newArrayList();
    private static Map<String, Content> contentCache = Maps.newHashMap();
    private static Map<String, ContentVo> resultCache = Maps.newHashMap();
    private static int total;

    private static Comparator<ContentVo> contentVoComparator = new Comparator<ContentVo>() {
        public int compare(ContentVo o1, ContentVo o2) {
            long acctId1 = NumberUtils.toLong(o1.getAcctId(), 0);
            long acctId2 = NumberUtils.toLong(o2.getAcctId(), 0);
            return (int) (acctId1 - acctId2);
        }
    };

    public static void addContent(Content content) {
        contentCache.put(content.getAcctId(), content);
    }

    public static void addPhoneData(String acctId, List<PhoneData> phoneDatas) {
        Content content = contentCache.get(acctId);
        if (content != null) {
            ContentVo vo = new ContentVo();
            vo.setAcctId(content.getAcctId());
            vo.setAcctName(content.getAcctName());
            vo.setAddressDetail(content.getAddressDetail());
            vo.setCompanyName(content.getCompanyName());
            vo.setRechargeDate(content.getRechargeDate());
            vo.setSiteUrl(content.getSiteUrl());

            List<PhoneDataVo> phoneDataVos = Lists.newArrayList();
            for (PhoneData phoneData : phoneDatas) {
                PhoneDataVo dataVo = new PhoneDataVo();
                dataVo.setEmail(phoneData.getEmail());
                dataVo.setMobilePhone(phoneData.getMobile());
                dataVo.setTelephone(phoneData.getPhone());
                dataVo.setName(phoneData.getContactName());
                phoneDataVos.add(dataVo);
            }
            vo.setPhoneDataVos(phoneDataVos);
            resultCache.put(acctId, vo);
        }
    }

    public static List<ContentVo> getResultList() {
        List<ContentVo> resultList = Lists.newArrayList(resultCache.values());
        Collections.sort(resultList, contentVoComparator);
        return resultList;
    }

    public static void addErrorAcct(String acctId) {
        errorAcct.add(acctId);
    }

    public static String getErrorAccts() {
        StringBuilder builder = new StringBuilder();
        for (String s : errorAcct) {
            builder.append(s).append(" ");
        }
        return builder.toString();
    }

    public static List<String> getErrorAcct() {
        return errorAcct;
    }

    public static void clearError() {
        errorAcct.clear();
    }

    public static int getErrorSize() {
        return errorAcct.size();
    }

    public static void reset() {
        errorAcct.clear();
        contentCache.clear();
        resultCache.clear();
    }

    public static void setTotal(int total) {
        DataCache.total = total;
    }

    public static int getTotal() {
        return total;
    }
}
