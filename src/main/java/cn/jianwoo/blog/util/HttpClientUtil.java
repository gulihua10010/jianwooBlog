package cn.jianwoo.blog.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-27 16:33
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static String doPost(String url, String username, String password, JSON param) {

        Integer responseCode = -1;
        String res = null;
        CloseableHttpClient httpClient = buildTLS11HttpClient();
        logger.info(">>Do Post url: \n" + url);
        logger.info(">>Do Post request: \n" + param);
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
            logger.error("HttpClientUtil.doPost exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("HttpClientUtil.doPost exec failed, e: \r\n", e);

                }
            }
        }
        logger.info(">> Do GET responseCode: \n" + responseCode);
        logger.info(">>Do Post response: \n" + res);
        return res;
    }


    private static String doPost(String url, JSON param) {

        Integer responseCode = -1;
        String res = null;
        CloseableHttpClient httpClient = buildTLS11HttpClient();
        logger.info(">>Do Post url: \n" + url);
        logger.info(">>Do Post request: \n" + param);
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
            logger.error("HttpClientUtil.doPost exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("HttpClientUtil.doPost exec failed, e: \r\n", e);

                }
            }
        }
        logger.info(">> Do GET responseCode: \n" + responseCode);
        logger.info(">>Do Post response: \n" + res);
        return res;
    }


    public static String doGet(String url, String username, String password) {
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
            logger.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
                }
            }
        }
        logger.info(">> Do GET responseCode: \n" + responseCode);
        logger.info(">> Do GET response: \n" + entityStr);

        return entityStr;
    }


    public static String doGet(String url) {
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
            logger.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("HttpClientUtil.doGet exec failed, e: \r\n", e);
                }
            }
        }
        logger.info(">> Do GET responseCode: \n" + responseCode);
        logger.info(">> Do GET response: \n" + entityStr);

        return entityStr;
    }


    private static CloseableHttpClient buildTLS11HttpClient() {
        SSLContext sslContext = SSLContexts.createDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.2"}, null,
                new NoopHostnameVerifier());
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

}
