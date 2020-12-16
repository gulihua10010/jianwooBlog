package cn.jianwoo.blog.dao.biz;

import cn.jianwoo.blog.entity.Webconf;

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

}
