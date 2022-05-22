package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.AccessBO;
import cn.jianwoo.blog.service.param.AccessParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleAccessBizService {
    /**
     * 获取最近limit条访问列表
     *
     * @param limit 最近条数
     * @return
     * @author gulihua
     */
    List<AccessBO> queryRecentAccess(Integer limit);


    /**
     * 获取最近访问列表
     *
     * @return
     * @author gulihua
     */
    PageInfo<AccessBO> queryRecentAccessPageList(AccessParam param);

    /**
     * 创建访问记录
     *
     * @param ip     ip地址
     * @param artOid 文章主键
     * @return
     * @author gulihua
     */
    void createAccessRecord(String ip, Long artOid) throws JwBlogException;
}
