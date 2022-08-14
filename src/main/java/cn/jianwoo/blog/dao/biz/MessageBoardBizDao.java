package cn.jianwoo.blog.dao.biz;

import cn.jianwoo.blog.entity.extension.MessageBoardExt;
import cn.jianwoo.blog.entity.query.MessageBoardPageQuery;
import cn.jianwoo.blog.entity.query.MessageBoardQuery;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface MessageBoardBizDao {


    /**
     * 获取留言总数量
     *
     * @return int
     * @author gulihua
     */
    Long countAllMessage();

    /**
     * 统计未读留言数据 (ART_DEL!=-1 AND IS_READ=0)
     *
     * @return int
     * @author gulihua
     */
    Long countWithUnreadMsg();


    /**
     * 获取最近limit条记录的留言 （通过UPDATE_DATE desc排序）
     *
     * @param limit limit
     * @return List<MessageBoardExt>
     * @author gulihua
     */
    List<MessageBoardExt> queryRecentMessage(Integer limit);


    /**
     * 通过文章oid分页获取留言（带文章标题）(首页留言)
     *
     * @param query 查询参数
     * @return List<MessageBoardExt>
     * @author gulihua
     */
    List<MessageBoardExt> queryMessagePageList(MessageBoardPageQuery query);


    /**
     * 通过文章oid查询根留言记录数(首页留言)
     *
     * @return Integer
     * @author gulihua
     */
    Long queryMessageRootCount();


    /**
     * 获取留言(后台管理页面)
     *
     * @return List<MessageBoardExt>
     * @author gulihua
     */
    List<MessageBoardExt> queryMessageExt();

    /**
     * 获取所有留言
     *
     * @return List<MessageBoardExt>
     * @author gulihua
     */
    List<MessageBoardExt> queryAllMessageExt(MessageBoardQuery param);


    /**
     * 更新留言的赞数量
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    void doUpdateMessagePraiseCnt(Long oid) throws DaoException;


    /**
     * 更新留言回复数量
     *
     * @param oid     主键
     * @param optType 操作类型(10:创建,40:删除)
     * @return
     * @author gulihua
     */
    void doUpdateMessageReplyCnt(Long oid, String optType) throws DaoException;

    /**
     * 更新留言回复总数量
     *
     * @param oid     主键
     * @param optType 操作类型(10:创建,40:删除)
     * @return
     * @author gulihua
     */
    void doUpdateMessageTotalReplyCnt(Long oid, String optType) throws DaoException;
}