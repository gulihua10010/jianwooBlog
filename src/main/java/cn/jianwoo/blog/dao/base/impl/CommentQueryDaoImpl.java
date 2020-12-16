package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.CommentQueryDao;
import cn.jianwoo.blog.dao.base.mapper.CommentMapper;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.example.CommentExample;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentQueryDao")
public class CommentQueryDaoImpl implements CommentQueryDao {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Comment queryCommentByPrimaryKey(Long oid) throws DaoException {
        Comment record = commentMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<Comment> queryCommentByArticleOid(Long oid) {
        CommentExample example = new CommentExample();
        example.createCriteria().andArticleOidEqualTo(oid);
        return commentMapper.selectByExample(example);
    }


    @Override
    public List<Comment> queryCommentByParentOid(Long oid) {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentEqualTo(oid);
        return commentMapper.selectByExample(example);
    }


    @Override
    public List<Comment> queryAllComment() {
        CommentExample example = new CommentExample();
        return commentMapper.selectByExample(example);
    }
}