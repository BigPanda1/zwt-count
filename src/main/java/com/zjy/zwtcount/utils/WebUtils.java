package com.zjy.zwtcount.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjy.zwtcount.entity.EnterpriseAppealDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class WebUtils {
    private static Logger log = LoggerFactory.getLogger(WebUtils.class);

    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final int DEFAULT_CONNECT_TIMEOUT = 5000;
    public static final int DEFAULT_READ_TIMEOUT = 5000;

    public static void main(String[] args) {
        Map<String,Object> headerMap = new HashMap<String,Object>();
        long timestamp = System.currentTimeMillis()/1000;
        String random = UUID.randomUUID().toString();
        headerMap.put("x-tif-paasid","shb001");
        headerMap.put("x-tif-signature", Sha256Utils.getSHA256(timestamp+"EoJSPRxU0YcmKctkEwlOg0n36cod55qR"+random+timestamp));
        headerMap.put("x-tif-timestamp",timestamp);
        headerMap.put("x-tif-nonce", random);
        log.info(String.format("请求中小企业诉求，请求头：%s", JSON.toJSONString(headerMap)));
        EnterpriseAppealDto dto =new EnterpriseAppealDto();
        dto.setRyzAccessToken("tif:yrz:userdata:1c2rkbint1scmc3df8214103f47cc9c4d9159044d85a8");
        dto.setRyzAccessTokenSwitch("shenzhen_access_token_permissions");
        String doPost = doPost("https://bs-test.digitalgd.com.cn/ebus/tif/sso/connect/page/oneoffcode2yrzuserinfo", headerMap, JSONObject.parseObject(JSONObject.toJSONString(dto)));
        System.out.println(doPost);
    }

    /**
     * @param url  接口url
     * @param json JSONObject 对象
     * @return
     */
    public static String doPost(String url, JSONObject json) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            String string = EntityUtils.toString(res.getEntity());
            return string;
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                result = EntityUtils.toString(res.getEntity());// 返回json格式：
//                return result;
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return null;
    }
    /**
     * @param url  接口url
     * @param json JSONObject 对象
     * @return
     */
    public static String doPostPlan(String url, JSONObject json) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("text/plain");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            String string = EntityUtils.toString(res.getEntity());
            return string;
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                result = EntityUtils.toString(res.getEntity());// 返回json格式：
//                return result;
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return null;
    }
    /**
     * @param url  接口url
     * @param json JSONObject 对象
     * @return
     */
    public static String doPostGBK(String url, JSONObject json) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("gbk");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(res.getEntity());// 返回json格式：
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * @param url  接口url
     * @param json JSONObject 对象
     * @return
     */
    public static String doPost(String url,Map<String,Object> headerMap, JSONObject json) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            if(headerMap != null){
                for (Map.Entry<String,Object> entry: headerMap.entrySet()) {
                    post.addHeader(entry.getKey(),entry.getValue().toString());
                }
            }
            HttpResponse res = httpclient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(res.getEntity());// 返回json格式：
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String doGet(String url) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        try {
            HttpResponse res = httpclient.execute(httpGet);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(res.getEntity());// 返回json格式：
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

//    public static String httpPostJSON(String url, JSONObject json) {
//        try {
//            Map<String, String> headers = new HashMap<String, String>();
//            headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_JSON);
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setConfig(HttpConnectionManager.initQuestConfig());
//            for (Map.Entry<String, String> e : headers.entrySet()) {
//                httpPost.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
//            }
//            if (StringUtils.isNotBlank(json.toString())) {
//                httpPost.setEntity(new StringEntity(json.toString(), Constants.ENCODING));
//            }
//            PoolingHttpClientConnectionManager httpClientConnectionManager = HttpConnectionManager.createConnManager();
//            HttpConnectionManager.setParam(httpClientConnectionManager);
//            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager).build();
//            HttpResponse res = httpClient.execute(httpPost);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                return EntityUtils.toString(res.getEntity());// 返回json格式：
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    public static String httpGetJSON(String url) {
//        try {
//            Map<String, String> headers = new HashMap<String, String>();
//            headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_JSON);
//            HttpGet httpGet = new HttpGet(url);
//            httpGet.setConfig(HttpConnectionManager.initQuestConfig());
//            for (Map.Entry<String, String> e : headers.entrySet()) {
//                httpGet.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
//            }
//            PoolingHttpClientConnectionManager httpClientConnectionManager = HttpConnectionManager.createConnManager();
//            HttpConnectionManager.setParam(httpClientConnectionManager);
//            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager).build();
//            HttpResponse res = httpClient.execute(httpGet);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                return EntityUtils.toString(res.getEntity());// 返回json格式：
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }

}
