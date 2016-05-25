package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.Content;
import com.elite.tools.catcher.core.domain.Data;
import com.elite.tools.catcher.core.domain.PhoneData;
import com.elite.tools.catcher.core.domain.ServerResponse;
import com.elite.tools.catcher.core.parse.JsonParser;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by df on 16/5/15.
 */
public class InfoGetter {
    private BaiduIndexRobber indexRobber = new BaiduIndexRobber();
    private DetailCatcher detailCatcher = new DetailCatcher();
    private String casId;
    private String casSt;
    private String token;
    private String operatorId;

    public InfoGetter(String casId, String casSt, String token, String operatorId) {
        this.casId = casId;
        this.casSt = casSt;
        this.token = token;
        this.operatorId = operatorId;
    }

    public List<Content> getIndexInfo() {
        List<Content> list = Lists.newLinkedList();
        int total = -1;
        int currentPage = 1;
        do {
            ServerResponse response = indexRobber.grab(casId, casSt, String.valueOf(currentPage), operatorId);
            if (response != null) {
                Data data = response.getData();
                if (data != null) {
                    if (total == -1) {
                        total = data.getTotalPages();
                    }
                    currentPage++;
                    list.addAll(data.getContent());
                }
            }
        } while (currentPage <= total);
        return list;
    }

    public List<PhoneData> getDetile(String acctId) {
        return detailCatcher.grab(token, acctId, casId, casSt);
    }

}
