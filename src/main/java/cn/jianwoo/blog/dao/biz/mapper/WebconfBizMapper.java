package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.entity.extension.WebconfExt;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 获取所有有效的配置
     *
     * @return
     * @author gulihua
     */
    List<WebconfExt> selectEffectiveWebconf();


    /**
     * 通過key更新value
     *
     * @param webconf 只包含value的对象
     * @param key     KEY
     * @return
     * @author gulihua
     */
    int updateWebconfByKey(@Param("webconf") Webconf webconf, @Param("key") String key);
}
