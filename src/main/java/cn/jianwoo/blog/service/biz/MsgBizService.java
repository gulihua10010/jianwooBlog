package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.MsgProfileBO;
import cn.jianwoo.blog.service.param.MsgParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-19 16:06
 */
public interface MsgBizService {


    /**
     * 获取所有消息
     *
     * @param param 参数
     * @return PageInfo<MsgProfileBO>
     * @author gulihua
     */
    PageInfo<MsgProfileBO> queryMsgPageList(MsgParam param);

    /**
     * 标记消息为已读
     *
     * @param msgId 主键
     * @author gulihua
     */
    void doReadMsg(Long msgId) throws JwBlogException;

    /**
     * 标记全部消息为已读
     *
     * @author gulihua
     */
    void doReadAllMsg();

    /**
     * 页面定时获取最新消息
     *
     * @param limit 消息条数
     * @return List<MsgProfileBO>
     * @author gulihua
     */
    List<MsgProfileBO> queryMsgTimerList(Integer limit);

    /**
     * 获取未读消息数量
     *
     * @return Integer
     * @author gulihua
     */
    Long queryUnreadMsgCount();

    /**
     * 页面定时获取最新消息(首页)
     *
     * @param limit     消息条数
     * @param currentIp 当前ip
     * @return List<MsgProfileBO>
     * @author gulihua
     */
    List<MsgProfileBO> queryMsgTimerMainList(Integer limit, String currentIp);
}
