package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.MessageBoardBO;
import cn.jianwoo.blog.service.bo.MessageBoardMainPageListBO;
import cn.jianwoo.blog.service.param.MessageBoardMainParam;
import cn.jianwoo.blog.service.param.MessageBoardParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MessagBoardBizService {

    /**
     * 获取留言总数量
     *
     * @return int
     * @author gulihua
     */
    Long countAllMessages();

    /**
     * 获取最近limit条记录的留言 （通过UPDATE_DATE desc排序）
     *
     * @param limit limit
     * @return List<MessageBoardBO>
     * @author gulihua
     */
    List<MessageBoardBO> queryRecentMessages(Integer limit);


    /**
     * 统计未读留言数据 (IS_READ=0)
     *
     * @return int
     * @author gulihua
     */
    Long countWithUnreadMessage();


    /**
     * 添加留言
     *
     * @param bo      留言
     * @param isAdmin 是否是管理员后台创建
     * @author gulihua
     */
    void doCreateMessageBoard(MessageBoardBO bo, boolean isAdmin) throws JwBlogException;


    /**
     * 查询留言
     *
     * @return
     * @author gulihua
     */
    List<MessageBoardBO> queryMessages();


    /**
     * 留言添加赞
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    void doAddMessagePraise(Long oid) throws JwBlogException;


    /**
     * 通过oid删除留言
     *
     * @param oid     主键
     * @param ip      ip地址
     * @param isAdmin 是否是管理员
     * @return
     * @author gulihua
     */
    void doDelMessageById(Long oid, String ip, boolean isAdmin) throws JwBlogException;


    /**
     * 获取所有有效留言
     *
     * @param param 参数
     * @return PageInfo<MessageBoardBO>
     * @author gulihua
     */
    PageInfo<MessageBoardBO> queryAllMessagePage(MessageBoardParam param);


    /**
     * 通过oid list删除留言
     *
     * @param oidList 主键list[Messages.OID]
     * @param ip      ip地址
     * @return
     * @author gulihua
     */
    void doDelMessageByListOid(List<Long> oidList, String ip) throws JwBlogException;


    /**
     * 根据留言oid获取留言
     *
     * @param oid 留言oid
     * @return MessageBoardBO
     * @author gulihua
     */
    MessageBoardBO queryMessageExtByOid(String oid) throws JwBlogException;


    /**
     * 通过oid 标记已读
     *
     * @param oid 主键list
     * @return
     * @author gulihua
     */
    void doUpdateReadByOid(Long oid) throws JwBlogException;


    /**
     * 通过oid list 批量标记已读
     *
     * @param oidList 主键list
     * @return
     * @author gulihua
     */
    void doUpdateReadByOidList(List<Long> oidList) throws JwBlogException;


    /**
     * 分页请求留言
     *
     * @param param 查询参数
     * @return
     * @author gulihua
     */
    MessageBoardMainPageListBO queryMessagesMainPage(MessageBoardMainParam param) throws JwBlogException;


    /**
     * 留言添加赞
     *
     * @param msgOid 留言主键 (OID)
     * @param ip     IP地址
     * @author gulihua
     */
    void doAddPraise(String msgOid, String ip) throws JwBlogException;


    /**
     * 更新留言
     *
     * @param bo 留言
     * @author gulihua
     */
    void doUpdateMessage(MessageBoardBO bo) throws JwBlogException;

}
