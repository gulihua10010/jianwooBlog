package cn.jianwoo.blog.util;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.StatusCode;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-27 16:33
 */
@Slf4j
public class HttpClientUtil {

    public static BaseResponseDto doPost(String url, String username, String password, JSON param) {

        Integer responseCode = -1;
        String res = null;
        CloseableHttpClient httpClient = buildTLS11HttpClient();
        log.info(">>Do Post url: " + url);
        log.info(">>Do Post request: " + param);
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
                    .setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            StringEntity requestEntity = new StringEntity(param.toJSONString(), "utf-8");

            String auth = "Basic " + new BASE64Encoder().encode((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Accept", "application/json");
            httpPost.addHeader("Authorization", auth);
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost);

            responseCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            log.error("HttpClientUtil.doPost exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    log.error("HttpClientUtil.doPost exec failed, e: \r\n", e);

                }
            }
        }
        log.info(">> Do Post responseCode: " + responseCode);
        log.info(">>Do Post response: " + res);
        return buildHttpResponse(responseCode, res);
    }


    public static BaseResponseDto doPost(String url, JSON param) {

        Integer responseCode = -1;
        String res = null;
        CloseableHttpClient httpClient = buildTLS11HttpClient();
        log.info(">>Do Post url: " + url);
        log.info(">>Do Post request: " + param);
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
                    .setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            StringEntity requestEntity = new StringEntity(param.toJSONString(), "utf-8");

            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost);

            responseCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            log.error("HttpClientUtil.doPost exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    log.error("HttpClientUtil.doPost exec failed, e: \r\n", e);

                }
            }
        }
        log.info(">> Do Post responseCode: " + responseCode);
        log.info(">>Do Post response: " + res);
        return buildHttpResponse(responseCode, res);

    }

    public static BaseResponseDto doPost(String url, Map<String, String> paramMap) {

        Integer responseCode = -1;
        String res = null;
        CloseableHttpClient httpClient = buildTLS11HttpClient();
        log.info(">>Do Post url: " + url);
        log.info(">>Do Post request: " + JSON.toJSONString(paramMap));
        CloseableHttpResponse response = null;
        List<NameValuePair> params = new ArrayList<>();
        if (paramMap.size() > 0) {
            Set<String> keySet = paramMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = paramMap.get(key);
                params.add(new BasicNameValuePair(key, value));
            }
        }

        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
                    .setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            StringEntity requestEntity = new UrlEncodedFormEntity(params, "UTF-8");

            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost);

            responseCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            log.error("HttpClientUtil.doPost exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    log.error("HttpClientUtil.doPost exec failed, e: \r\n", e);

                }
            }
        }
        log.info(">> Do Post responseCode: " + responseCode);
        log.info(">>Do Post response: " + res);
        return buildHttpResponse(responseCode, res);

    }


    public static BaseResponseDto doGet(String url, String username, String password) {
        log.info(">>Do GET url: " + url);
        Integer responseCode = -1;
        CloseableHttpClient httpClient = buildTLS11HttpClient();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
                    .setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpGet.setConfig(requestConfig);
            httpGet.setHeader("Content-type", "application/json");
            httpGet.setHeader("Accept", "application/json");
            httpGet.addHeader("Authorization",
                    "Basic " + new BASE64Encoder().encode((username + ":" + password).getBytes(StandardCharsets.UTF_8)));
            response = httpClient.execute(httpGet);
            if (response != null) {
                responseCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                entityStr = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    log.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
                }
            }
        }
        log.info(">> Do GET responseCode: " + responseCode);
        log.info(">> Do GET response: " + entityStr);

        return buildHttpResponse(responseCode, entityStr);

    }


    public static BaseResponseDto doGet(String url) {
        log.info(">>Do GET url: " + url);
        Integer responseCode = -1;
        CloseableHttpClient httpClient = buildTLS11HttpClient();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
                    .setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpGet.setConfig(requestConfig);
            httpGet.setHeader("Content-type", "application/json");
            httpGet.setHeader("Accept", "application/json");
            response = httpClient.execute(httpGet);
            if (response != null) {
                responseCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                entityStr = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    log.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
                }
            }
        }
        log.info(">> Do GET responseCode: " + responseCode);
        log.info(">> Do GET response: " + entityStr);

        return buildHttpResponse(responseCode, entityStr);

    }


    private static CloseableHttpClient buildTLS11HttpClient() {
        SSLContext sslContext = SSLContexts.createDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.2"}, null,
                new NoopHostnameVerifier());
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    private static BaseResponseDto buildHttpResponse(Integer code, String res) {
        String status = code == 200 ? StatusCode.SUCCESS.getStatus() : StatusCode.FAILED.getStatus();
        return BaseResponseDto.buildResponse(status, res);

    }

}
