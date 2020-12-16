package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.extension.VisitExt;

import java.util.List;

public interface VisitBizMapper {
    /**
     * 获取最近访问的limit条访问者（通过UPDATE_DATE desc排序）
     *
     * @param limit 取limit条
     * @return List<VisitExt>
     * @author gulihua
     */
    List<VisitExt> selectRecentVisit(Integer limit);

}