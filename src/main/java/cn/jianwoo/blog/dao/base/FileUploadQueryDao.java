package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.DaoException;

public interface FileUploadQueryDao {
    FileUpload queryfileUploadByPrimaryKey(Long oid) throws DaoException;
}