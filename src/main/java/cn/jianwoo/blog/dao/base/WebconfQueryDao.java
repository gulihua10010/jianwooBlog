package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface WebconfQueryDao {
    Webconf queryWebconfByPrimaryKey(Long oid) throws DaoException;


    /**
     * 获取所有网站配置
     *
     * @return List<Webconf>
     * @author gulihua
     */
    List<Webconf> queryAllWebconf();


    /**
     * 通过key获取网站配置
     *
     * @param key 键
     * @return
     * @author gulihua
     */
    Webconf queryWebconfByKey(String key);
}