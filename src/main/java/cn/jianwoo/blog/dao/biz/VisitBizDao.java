package cn.jianwoo.blog.dao.biz;

import cn.jianwoo.blog.entity.extension.VisitExt;

import java.util.List;

public interface VisitBizDao {
    /**
     * 获取最近访问的limit条访问者（通过UPDATE_DATE desc排序）
     *
     * @param limit 取limit条
     * @return List<VisitExt>
     * @author gulihua
     */
    List<VisitExt> queryRecentVisit(Integer limit);


    /**
     * 获取最近访问的访问者（通过UPDATE_DATE desc排序）
     *
     * @return List<VisitExt>
     * @author gulihua
     */
    List<VisitExt> queryRecentVisit();

}