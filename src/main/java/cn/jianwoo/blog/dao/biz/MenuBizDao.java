package cn.jianwoo.blog.dao.biz;

import java.util.Map;

public interface MenuBizDao {

    /**
     * 根据菜单type类型统计数量
     *
     * @param type 菜单类型 [TYPE]
     * @return
     * @author gulihua
     */
    int countMenu(String type);


    /**
     * 统计相同层级的菜单数量（统计添加菜单，获取排序索引）
     *
     * @param params type 菜单类型 [TYPE]
     * @param params parentOid 菜单父oid [PARENT_OID]
     * @return
     * @author gulihua
     */
    int countMenuWithSameLevel(Map<String, Object> params);

}