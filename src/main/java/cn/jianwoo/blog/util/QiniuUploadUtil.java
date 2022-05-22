package cn.jianwoo.blog.util;

import cn.jianwoo.blog.exception.JwBlogException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author GuLihua
 * @Description
 * @date 2021-10-16 2:24
 */
@Component
@Slf4j
public class QiniuUploadUtil {
    @Value("${qiniuyun.access.key}")
    private String accessKey;
    @Value("${qiniuyun.secret.key}")
    private String secretKey;
    @Value("${qiniuyun.bucketname}")
    private String bucketname;
    @Value("${qiniuyun.domain}")
    private String domain;
    private Auth auth;
    private UploadManager uploadManager;

    @Bean
    @ConditionalOnMissingBean
    public UploadManager init() {
        auth = Auth.create(accessKey, secretKey);
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);

        //创建上传对象
        uploadManager = new UploadManager(c);
        return uploadManager;
    }

    private String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    /**
     * 七牛云上传文件
     *
     * @param filepath 本地文件全路径
     * @param context  CDN上下文
     * @param name     文件名
     * @return 七牛云cdn源路径
     * @author gulihua
     */
    public String upload(String filepath, String context, String name) throws JwBlogException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(filepath, context + "/" + name, getUpToken());
            //打印返回的信息
            log.info(">>>>Qiniuyun upload successfully, response: [{}]", res.bodyString());
            JSONObject object = (JSONObject) JSON.parse(res.bodyString());
            return domain.concat("/").concat((String) object.get("key"));
        } catch (QiniuException e) {
            Response response = e.response;
            // 请求失败时打印的异常的信息
            log.error(">>Qiniuyun upload failed, response = [{}]", response);
            log.error(">>Qiniuyun upload failed, e:\r\n", e);
            throw new JwBlogException("七牛云上传失败, 文件名:%s", filepath);

        }

    }


    /**
     * 七牛云上传文件字节数组
     *
     * @param bytes   文件字节数组
     * @param context CDN上下文
     * @param name    文件名
     * @return
     * @author gulihua
     */
    public String upload(byte[] bytes, String context, String name) throws JwBlogException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(bytes, context + "/" + name, getUpToken());
            //打印返回的信息
            log.info(">>>>Qiniuyun upload successfully, response: [{}]", res.bodyString());
            JSONObject object = (JSONObject) JSON.parse(res.bodyString());
            return domain.concat("/").concat((String) object.get("key"));
        } catch (QiniuException e) {
            e.printStackTrace();
            Response response = e.response;
            // 请求失败时打印的异常的信息
            log.error(">>Qiniuyun upload failed, response = [{}]", response);
            log.error(">>Qiniuyun upload failed, e:\r\n", e);
            throw new JwBlogException("七牛云上传失败, 文件名:%s", name);
        }
    }


}
