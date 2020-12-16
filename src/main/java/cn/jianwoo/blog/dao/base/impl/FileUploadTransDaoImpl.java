package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.FileUploadTransDao;
import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fileUploadTransDao")
public class FileUploadTransDaoImpl extends FileUploadQueryDaoImpl implements FileUploadTransDao {
    @Autowired
    cn.jianwoo.blog.dao.base.mapper.fileUploadMapper fileUploadMapper;

    @Override
    public void doInsert(FileUpload record) throws DaoException {
        int intRlt = fileUploadMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(FileUpload record) throws DaoException {
        int intRlt = fileUploadMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(FileUpload record) throws DaoException {
        int updRlt = fileUploadMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(FileUpload record) throws DaoException {
        int updRlt = fileUploadMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = fileUploadMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}