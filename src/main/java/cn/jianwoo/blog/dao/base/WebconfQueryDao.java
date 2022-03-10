package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;

import java.util.List;

public interface WebconfQueryDao {
    Webconf queryWebconfByPrimaryKey(Long oid) throws DaoException;


    /**
     * 获取有效的网站配置
     *
     * @return List<Webconf>
     * @author gulihua
     */
    List<Webconf> queryEffectiveWebconf();


    /**
     * 通过key获取网站配置
     *
     * @param key 键
     * @return
     * @author gulihua
     */
    Webconf queryWebconfByKey(String key) throws JwBlogException;


    /**
     * 通过cfgType获取网站配置
     *
     * @param cfgType 配置类型
     * @return
     * @author gulihua
     */
    List<Webconf> queryWebconfsByType(String cfgType) throws JwBlogException;
}