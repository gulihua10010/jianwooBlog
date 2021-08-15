package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.WebconfBO;
import cn.jianwoo.blog.service.bo.WebconfResBO;

import java.util.List;

public interface WebconfBizService {


    /**
     * 获取网站配置map
     *
     * @return List<WebconfBO>
     * @author gulihua
     */
    WebconfResBO queryConfig();


    /**
     * 更新网站配置
     *
     * @param bo 网站配置map
     * @return
     * @author gulihua
     */
    void doUpdateConfig(List<WebconfBO> configList) throws JwBlogException;


    /**
     * 通过key获取网站配置<br>
     * (优化)先从缓存拿，没有再从数据库拿<br>
     *
     * @param key 键
     * @return
     * @author gulihua
     */
    String queryWebconfByKey(String key) throws JwBlogException;


}
