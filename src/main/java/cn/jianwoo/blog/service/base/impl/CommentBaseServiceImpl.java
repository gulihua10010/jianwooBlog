package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.exception.CommentBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.CommentBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-04 23:59
 */
@Service
@Slf4j
public class CommentBaseServiceImpl implements CommentBaseService {

    @Autowired
    private CommentTransDao commentTransDao;

    @Override
    public Comment queryCommentByOid(Long oid) throws JwBlogException {
        try {
            return commentTransDao.queryCommentByPrimaryKey(oid);
        } catch (DaoException e) {
            throw CommentBizException.THIS_COMMENT_NOT_EXIST_EXCEPTION_CN.print();

        }

    }
}
