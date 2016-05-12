package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.ServerResponse;
import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.util.List;

/**
 * Created by df on 16/5/12.
 */
public class BaiduIndexRobber implements Robber {
    private final String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/listAccountByPage.do";
    private final String cookie="__cas__id__309=20306630;__cas__st__309=95442247888330400a4a8b6d4fc12c18365e170c2a40e966de80806ddf8008e9baddf72a9265c75d30091f94";
    public ServerResponse grab() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post=new HttpPost(url);
        post.setHeader(new BasicHeader("Cookie",cookie));
        List<NameValuePair> params= Lists.newArrayList();
        return null;
    }
}
