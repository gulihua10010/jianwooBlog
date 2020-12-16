package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.extension.VisitExt;
import cn.jianwoo.blog.entity.query.VisitParam;
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
    List<VisitExt> queryRecentVisit(Integer limit);


    /**
     * 获取最近访问列表
     *
     * @return
     * @author gulihua
     */
    PageInfo<VisitExt> queryRecentVisitPageList(VisitParam param);
}
