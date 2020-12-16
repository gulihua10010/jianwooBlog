package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.dao.base.mapper.TagsMapper;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagsTransDao")
public class TagsTransDaoImpl extends TagsQueryDaoImpl implements TagsTransDao {
    @Autowired
    TagsMapper tagsMapper;

    @Override
    public void doInsert(Tags record) throws DaoException {
        int intRlt = tagsMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Tags record) throws DaoException {
        int intRlt = tagsMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Tags record) throws DaoException {
        int updRlt = tagsMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Tags record) throws DaoException {
        int updRlt = tagsMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = tagsMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}