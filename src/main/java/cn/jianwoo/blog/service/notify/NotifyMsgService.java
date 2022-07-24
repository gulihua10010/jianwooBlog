package cn.jianwoo.blog.service.notify;

import cn.jianwoo.blog.exception.JwBlogException;
import com.alibaba.fastjson.JSONObject;

/**
 * 消息发送接口
 *
 * @author gulihua
 * @date 2022-02-26 16:18
 */
public interface NotifyMsgService {


    /**
     * 邮件发送
     *
     * @param emailTplCode 邮件模板编码
     * @param param        参数
     * @param recipient    收信人
     * @return
     * @author gulihua
     */
    void doSend(String emailTplCode, JSONObject param, String recipient) throws JwBlogException;

    /**
     * 邮件发送
     *
     * @param emailTplCode 邮件模板编码
     * @param param        参数
     * @param recipient    收信人(可以多个)
     * @return
     * @author gulihua
     */
    void doSend(String emailTplCode, JSONObject param, String... recipient) throws JwBlogException;
}
