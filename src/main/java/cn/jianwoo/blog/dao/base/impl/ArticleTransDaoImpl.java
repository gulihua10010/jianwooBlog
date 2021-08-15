package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.mapper.ArticleMapper;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleTransDao")
public class ArticleTransDaoImpl extends ArticleQueryDaoImpl implements ArticleTransDao {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void doInsert(ArticleWithBLOBs record) throws DaoException {
        int intRlt = articleMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(ArticleWithBLOBs record) throws DaoException {
        int intRlt = articleMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Article record) throws DaoException {
        int updRlt = articleMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record) throws DaoException {
        int updRlt = articleMapper.updateByPrimaryKeyWithBLOBs(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(ArticleWithBLOBs record) throws DaoException {
        int updRlt = articleMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = articleMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}