package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.extension.AccessExt;

import java.util.List;

public interface ArticleAccessBizMapper {
    /**
     * 获取最近访问的limit条访问者（通过UPDATE_DATE desc排序）
     *
     * @param limit 取limit条
     * @return List<AccessExt>
     * @author gulihua
     */
    List<AccessExt> selectRecentAccess(Integer limit);

}