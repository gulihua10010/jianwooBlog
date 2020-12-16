package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.Webconf;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-20 10:26
 */
public interface WebconfBizMapper {
    /**
     * 获取所有网站配置
     *
     * @return List<Webconf>
     * @author gulihua
     */
    List<Webconf> selectAllWebconf();
}
