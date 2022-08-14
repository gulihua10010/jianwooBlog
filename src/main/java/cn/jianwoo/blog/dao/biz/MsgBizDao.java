package cn.jianwoo.blog.dao.biz;

import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-08-02 18:37
 */
public interface MsgBizDao {


    /**
     * 页面定时获取最新消息(首页)
     *
     * @param limit     消息条数
     * @param currentIp 当前ip
     * @return List<MessageProfile>
     * @author gulihua
     */
    List<MessageProfileWithBLOBs> queryMsgTimerMainList(Integer limit, String currentIp);
}
