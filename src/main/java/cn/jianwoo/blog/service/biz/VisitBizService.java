package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.query.VisitQuery;
import cn.jianwoo.blog.service.bo.VisitBO;
import cn.jianwoo.blog.service.param.VisitParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface VisitBizService {
    /**
     * 获取最近limit条访问列表
     *
     * @param limit 最近条数
     * @return
     * @author gulihua
     */
    List<VisitBO> queryRecentVisit(Integer limit);


    /**
     * 获取最近访问列表
     *
     * @return
     * @author gulihua
     */
    PageInfo<VisitBO> queryRecentVisitPageList(VisitParam param);
}
