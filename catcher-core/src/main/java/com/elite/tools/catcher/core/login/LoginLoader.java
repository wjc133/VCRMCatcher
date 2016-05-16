package com.elite.tools.catcher.core.login;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by df on 16/5/13.
 */
public class LoginLoader {
    private static final String URL_CODE = "http://cas.baidu.com/?action=image2&appid=309";

    private byte[] getCode() {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet codeGetter = new HttpGet(URL_CODE);
            response = httpClient.execute(codeGetter);
            int stateCode = response.getStatusLine().getStatusCode();
            if (stateCode == 200) {
                in = response.getEntity().getContent();
                // TODO: 16/5/13 获取流数据
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
            if (httpClient != null) {
                try {
                    httpClient.close();
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

//    public boolean login(String username, String password, String code){
//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) ||
//                StringUtils.isEmpty(code)) {
//            return false;
//        }
//        CloseableHttpClient client=HttpClients.createDefault();
//        HttpPost post=new HttpPost();
//    }
}
