package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.FileUploadQueryDao;
import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fileUploadQueryDao")
public class FileUploadQueryDaoImpl implements FileUploadQueryDao {
    @Autowired
    cn.jianwoo.blog.dao.base.mapper.fileUploadMapper fileUploadMapper;

    @Override
    public FileUpload queryfileUploadByPrimaryKey(Long oid) throws DaoException {
        FileUpload record = fileUploadMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}