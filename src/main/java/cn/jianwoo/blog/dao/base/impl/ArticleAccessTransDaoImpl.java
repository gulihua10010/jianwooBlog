package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ArticleAccessTransDao;
import cn.jianwoo.blog.dao.base.mapper.ArticleAccessMapper;
import cn.jianwoo.blog.entity.ArticleAccess;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleAccessTransDao")
public class ArticleAccessTransDaoImpl extends ArticleAccessQueryDaoImpl implements ArticleAccessTransDao {
    @Autowired
    ArticleAccessMapper articleAccessMapper;

    @Override
    public void doInsert(ArticleAccess record) throws DaoException {
        int intRlt = articleAccessMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(ArticleAccess record) throws DaoException {
        int intRlt = articleAccessMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(ArticleAccess record) throws DaoException {
        int updRlt = articleAccessMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(ArticleAccess record) throws DaoException {
        int updRlt = articleAccessMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = articleAccessMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}