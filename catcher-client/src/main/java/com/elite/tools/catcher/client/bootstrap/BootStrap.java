package com.elite.tools.catcher.client.bootstrap;

import com.elite.tools.catcher.client.domain.ContentVo;
import com.elite.tools.catcher.client.domain.PhoneDataVo;
import com.elite.tools.catcher.client.mapper.PhoneDataMapper;
import com.elite.tools.catcher.core.catcher.InfoGetter;
import com.elite.tools.catcher.core.domain.Content;
import com.elite.tools.catcher.core.domain.PhoneData;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by df on 16/5/15.
 */
public class BootStrap {
    public static void main(String[] args) {
        if (args == null || args.length != 3) {
            System.out.println("illegal params");
            System.exit(0);
        }
        String casId = args[0];
        String casSt = args[1];
        String token = args[2];
        InfoGetter infoGetter = new InfoGetter(casId, casSt, token);
        List<Content> contents = infoGetter.getIndexInfo();
        if (contents != null && contents.size() > 0) {
            List<ContentVo> contentVos = Lists.newArrayList();
            for (Content content : contents) {
                ContentVo contentVo = new ContentVo();
                contentVo.setAcctId(content.getAcctId());
                contentVo.setCompanyName(content.getCompanyName());
                contentVo.setRechargeDate(content.getRechargeDate());
                contentVo.setSiteUrl(content.getSiteUrl());

                List<PhoneData> phoneDatas = infoGetter.getDetile(content.getAcctId());
                List<PhoneDataVo> phoneDataVos = PhoneDataMapper.INSTANCE.bosToVos(phoneDatas);
                contentVo.setPhoneDataVos(phoneDataVos);
                contentVos.add(contentVo);
            }
            System.out.println(toJson(contentVos));
        } else {
            System.out.println("no data!");
        }
    }

    private static String toJson(List<ContentVo> contentVos) {
        Gson gson = new Gson();
        return gson.toJson(contentVos);
    }
}
