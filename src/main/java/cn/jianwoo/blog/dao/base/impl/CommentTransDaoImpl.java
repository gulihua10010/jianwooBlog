package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.dao.base.mapper.CommentMapper;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.example.CommentExample;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentTransDao")
public class CommentTransDaoImpl extends CommentQueryDaoImpl implements CommentTransDao {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void doInsert(Comment record) throws DaoException {
        int intRlt = commentMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Comment record) throws DaoException {
        int intRlt = commentMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Comment record) throws DaoException {
        int updRlt = commentMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Comment record) throws DaoException {
        int updRlt = commentMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
//        int delRlt = commentMapper.deleteByPrimaryKey(oid);
//        if (1 != delRlt) {
//            throw DaoException.DAO_DELETE_RESULT_0.print();
//        }
        Comment record = JwBuilder.of(Comment::new)
                .with(Comment::setOid, oid)
                .with(Comment::setIsDelete, true)
                .build();
        int updRlt = commentMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateArtInfoByArtOidSelective(Comment record, Long artOid) throws DaoException {
        CommentExample example = new CommentExample();
        example.createCriteria().andArticleOidEqualTo(artOid);
        record.setUpdateTime(DateUtil.getNow());
        int updRlt = commentMapper.updateByExampleSelective(record, example);
        if (0 == updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }
}