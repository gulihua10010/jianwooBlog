package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.WebconfBO;

import java.util.List;

public interface WebconfBizService {


    /**
     * 获取网站配置map
     *
     * @return List<WebconfBO>
     * @author gulihua
     */
    List<WebconfBO> queryConfig();


    /**
     * 更新网站配置
     *
     * @param bo 网站配置map
     * @return
     * @author gulihua
     */
    void doUpdateConfig(List<WebconfBO> configList) throws JwBlogException;


}
