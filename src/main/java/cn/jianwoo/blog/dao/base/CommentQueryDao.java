package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface CommentQueryDao {
    Comment queryCommentByPrimaryKey(Long oid) throws DaoException;


    /**
     * 通过文章oid获取评论
     *
     * @param oid 文章oid
     * @return
     * @author gulihua
     */
    List<Comment> queryCommentByArticleOid(Long oid);


    /**
     * 通过父oid获取评论
     *
     * @param oid 父oid
     * @return
     * @author gulihua
     */
    List<Comment> queryCommentByParentOid(Long oid);


    /**
     * 获取所有评论
     *
     * @return
     * @author gulihua
     */
    List<Comment> queryAllComment();


    /**
     * 根据日期范围和IP地址获取评论
     *
     * @param from 开始时间
     * @param to   截止时间
     * @param ip   IP地址
     * @return
     * @author gulihua
     */
    Long queryCommentsByDateRangeAndIp(Date from, Date to, String ip);

}