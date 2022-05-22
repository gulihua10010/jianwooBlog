package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.exception.JwBlogException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-06 16:40
 */
public interface MsgBaseService {

    /**
     * 创建消息
     *
     * @param busiSceneCode 业务场景编码
     * @param loginId       登录id
     * @param msgData       消息数据
     * @param bizId       业务ID主键
     * @return
     * @author gulihua
     */
    void doCreateMsg(String busiSceneCode, String loginId, JSONObject msgData, String bizId) throws JwBlogException;


    /**
     * 查询消息详情
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    MessageProfile getMessageProfile(Long oid) throws JwBlogException;


}
