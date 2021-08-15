package cn.jianwoo.blog.dao.base.impl;


import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.dao.base.mapper.TempArticleMapper;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tempArticleTransDao")
public class TempArticleTransDaoImpl extends TempArticleQueryDaoImpl implements TempArticleTransDao {
    @Autowired
    TempArticleMapper tempArticleMapper;

    @Override
    public void doInsert(TempArticle record) throws DaoException {
        int intRlt = tempArticleMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(TempArticle record) throws DaoException {
        int intRlt = tempArticleMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(TempArticle record) throws DaoException {
        int updRlt = tempArticleMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(TempArticle record) throws DaoException {
        int updRlt = tempArticleMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = tempArticleMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeyWithBLOBs(TempArticle record) throws DaoException {
        int delRlt = tempArticleMapper.updateByPrimaryKeyWithBLOBs(record);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}