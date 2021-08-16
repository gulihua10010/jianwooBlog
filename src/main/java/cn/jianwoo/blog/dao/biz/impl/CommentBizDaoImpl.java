package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.CommentBizDao;
import cn.jianwoo.blog.dao.biz.mapper.CommentBizMapper;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.query.CommentQuery;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 18:14
 */
@Service
public class CommentBizDaoImpl implements CommentBizDao {
    @Autowired
    private CommentBizMapper commentBizMapper;


    @Override
    public int countWithCommentByArt(Long articleOid) {
        return commentBizMapper.countWithCommentByArt(articleOid);
    }


    @Override
    public int countAllComments() {
        return commentBizMapper.countAllComments();
    }


    @Override
    public int countWithUnreadComm() {
        return commentBizMapper.countWithUnreadComm();
    }


    @Override
    public List<CommentExt> queryRecentComments(Integer limit) {
        return commentBizMapper.selectRecentComments(limit);
    }


    @Override
    public Comment queryLastComment() {
        return commentBizMapper.selectLastComment();
    }


    @Override
    public List<CommentExt> queryCommentsExtByArticleOid(Long artOid) {
        return commentBizMapper.selectCommentsExtByArticleOid(artOid);

    }


    @Override
    public List<CommentExt> queryAllCommentsExt(CommentQuery param) {
        return commentBizMapper.selectAllCommentsExt(param);

    }


    @Override
    public CommentExt queryCommentExtByOid(Long oid) throws DaoException {
        CommentExt commentExt = commentBizMapper.selectCommentExtByOid(oid);
        if (null == commentExt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
        return commentExt;
    }

    @Override
    public void doUpdateCommentPraiseCnt(Long oid) throws DaoException {
        int updRlt = commentBizMapper.updateCommentPraiseCnt(oid);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

}
