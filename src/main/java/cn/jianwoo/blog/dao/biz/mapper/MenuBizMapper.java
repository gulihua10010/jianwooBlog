package cn.jianwoo.blog.dao.biz.mapper;

import java.util.Map;

public interface MenuBizMapper {
    /**
     * 根据菜单type类型统计数量
     *
     * @param type 菜单类型 [TYPE]
     * @return
     * @author gulihua
     */
    int countMenu(String type);


    /**
     * 获取相同层级的最大菜单索引（添加菜单，获取排序索引）
     *
     * @param params type 菜单类型 [TYPE]
     * @param params parentOid 菜单父oid [PARENT_OID]
     * @return
     * @author gulihua
     */
    int selectMaxIndexMenuWithSameLevel(Map<String, Object> params);

}