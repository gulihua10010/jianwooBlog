package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.DaoException;

public interface FileUploadTransDao extends FileUploadQueryDao {
    void doInsert(FileUpload record) throws DaoException;

    void doInsertSelective(FileUpload record) throws DaoException;

    void doUpdateByPrimaryKey(FileUpload record) throws DaoException;

    void doUpdateByPrimaryKeySelective(FileUpload record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}