package cn.jianwoo.blog.dao.biz;

import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.entity.extension.WebconfExt;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-20 10:23
 */
public interface WebconfBizDao {
    /**
     * 获取所有网站配置
     *
     * @return List<Webconf>
     * @author gulihua
     */
    List<Webconf> queryAllWebconf();


    /**
     * 获取所有有效的配置
     *
     * @return
     * @author gulihua
     */
    List<WebconfExt> queryEffectiveWebconf();


    /**
     * 通過key更新value
     *
     * @param webconf 只包含value的对象
     * @param key     KEY
     * @return
     * @author gulihua
     */
    void doUpdateWebconfByKey(Webconf webconf, String key) throws DaoException;


}
