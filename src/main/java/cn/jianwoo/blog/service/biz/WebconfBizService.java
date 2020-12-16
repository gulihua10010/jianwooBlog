package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.WebconfBO;

import java.util.Map;

public interface WebconfBizService {
    /**
     * 获取网站配置map
     *
     * @return
     * @author gulihua
     */
    Map<String, Object> queryConfigWithMap();


    /**
     * 更新网站配置
     *
     * @param conf 网站配置map
     * @return
     * @author gulihua
     */
    void doUpdateConfigWithMap(Map<String, Object> conf) throws JwBlogException;


    /**
     * 转换网站配置 Map格式 为 实体类WebconfBO{@link WebconfBO} 格式
     *
     * @param conf Map格式的数据
     * @return
     * @author gulihua
     */
    WebconfBO convertWebconfMaptoBO(Map<String, Object> conf);


    /**
     * 获取网站配置map
     *
     * @return
     * @author gulihua
     */
    WebconfBO queryConfigWithBO();


    /**
     * 更新网站配置
     *
     * @param bo 网站配置map
     * @return
     * @author gulihua
     */
    void doUpdateConfigWithBO(WebconfBO bo) throws JwBlogException;


    /**
     * 转换网站配置 实体类WebconfBO{@link WebconfBO} 为 Map格式 格式
     *
     * @param bo WebconfBO{@link WebconfBO} 格式的数据
     * @return
     * @author gulihua
     */
    Map<String, Object> convertBOtoWebconfMap(WebconfBO bo);

}
