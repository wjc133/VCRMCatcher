package com.elite.tools.catcher.core.catcher;

import com.elite.tools.catcher.core.domain.PhoneData;
import com.elite.tools.catcher.core.parse.JsonParser;
import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by df on 16/5/15.
 */
public class DetailCatcher {

    private final Pattern PATTERN_PHONE = Pattern.compile("(?<=data\\.dataList\\s=\\s).+(?=;)");
    private String url = "http://vcrm.baidu.com/ipage2/ipage/trustv-ocrm-v2/account/acctinfo.htm";

    public List<PhoneData> grab(String token, String acctId, String casId, String casSt) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            String cookie = "__cas__id__309=" + casId + ";__cas__st__309=" + casSt;
            client = HttpClients.createDefault();
//            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
//            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,1000);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
            HttpPost post = new HttpPost(url);
            post.setConfig(requestConfig);
            post.setHeader(new BasicHeader("Cookie", cookie));
            List<NameValuePair> params = Lists.newArrayList();
            params.add(new BasicNameValuePair("token", token));
            params.add(new BasicNameValuePair("isFrag", "true"));
            params.add(new BasicNameValuePair("acctId", acctId));
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            response = client.execute(post);
            int stateCode = response.getStatusLine().getStatusCode();
            if (stateCode == 200) {
                in = response.getEntity().getContent();
                byte[] data = getData(in);
                String responseHtml = new String(data, "UTF-8");
                return parseHtml(responseHtml);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DataCache.addErrorAcct(acctId);
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

    private List<PhoneData> parseHtml(String responseHtml) {
//        Document doc = Jsoup.parse(responseHtml);
//        doc.
        Matcher matcher = PATTERN_PHONE.matcher(responseHtml);
        if (matcher.find()) {
            String phoneStr = matcher.group();
            try {
                return JsonParser.getPhoneDatas(phoneStr);
            } catch (Exception e) {
                System.out.println("has an error: phoneStr=" + phoneStr);
                e.printStackTrace();
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
