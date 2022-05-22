package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.exception.JwBlogException;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-04 23:57
 */
public interface CommentBaseService {

    /**
     * 通过主键oid去查询评论
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    Comment queryCommentByOid(Long oid) throws JwBlogException;
}
