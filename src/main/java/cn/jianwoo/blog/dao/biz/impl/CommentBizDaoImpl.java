package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.CommentBizDao;
import cn.jianwoo.blog.dao.biz.mapper.CommentBizMapper;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.query.CommentParam;
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
    public List<CommentExt> queryAllCommentsExt(CommentParam param) {
        return commentBizMapper.selectAllCommentsExt(param);

    }


    @Override
    public CommentExt queryCommentExtByOid(Long oid) {
        return commentBizMapper.selectCommentExtByOid(oid);
    }

}
