package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.WebconfFacadeQueryDao;
import cn.jianwoo.blog.dao.base.mapper.WebconfFacadeMapper;
import cn.jianwoo.blog.entity.WebconfFacade;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("webconfFacadeQueryDao")
public class WebconfFacadeQueryDaoImpl implements WebconfFacadeQueryDao {
    @Autowired
    WebconfFacadeMapper webconfFacadeMapper;

    @Override
    public WebconfFacade queryWebconfFacadeByPrimaryKey(Long oid) throws DaoException {
        WebconfFacade record = webconfFacadeMapper.selectByPrimaryKey(oid);
        if(null == record){
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}