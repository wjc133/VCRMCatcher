package com.elite.tools.catcher.core.catcher;

import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by df on 16/5/12.
 */
class BaiduBasicGrabber {
    private final String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/listAccountByPage.do";

    String grab(String casId, String casSt, String pageNum, String operatorId) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            String cookie = "__cas__id__309=" + casId + ";__cas__st__309=" + casSt;
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.setHeader(new BasicHeader("Cookie", cookie));
            List<NameValuePair> params = Lists.newArrayList();
            params.add(new BasicNameValuePair("from", "trustv-ocrm-v2/account/acclist"));
            params.add(new BasicNameValuePair("sid", "listAccountByPage"));
            params.add(new BasicNameValuePair("orgId", "145"));
            params.add(new BasicNameValuePair("operatorId", operatorId));
            params.add(new BasicNameValuePair("pageNum", pageNum));
            params.add(new BasicNameValuePair("pageSize", "10"));
            params.add(new BasicNameValuePair("isAjax", "true"));
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            response = client.execute(post);
            int stateCode = response.getStatusLine().getStatusCode();
            if (stateCode == 200) {
                in = response.getEntity().getContent();
                byte[] data = getData(in);
                return new String(data, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private byte[] getData(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }
}
