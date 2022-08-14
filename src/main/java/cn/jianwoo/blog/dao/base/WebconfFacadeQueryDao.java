package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.WebconfFacade;
import cn.jianwoo.blog.exception.DaoException;

public interface WebconfFacadeQueryDao {
    WebconfFacade queryWebconfFacadeByPrimaryKey(String key) throws DaoException;
}