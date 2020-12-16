package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface MenuQueryDao {
    Menu queryMenuByPrimaryKey(Long oid) throws DaoException;


    List<Menu> queryMenuByType(Integer type);


    List<Menu> queryMenuByParentIdAndType(Long parentOid, Integer type);

}