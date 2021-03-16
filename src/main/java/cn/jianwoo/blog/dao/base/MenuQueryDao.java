package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface MenuQueryDao {
    Menu queryMenuByPrimaryKey(Long oid) throws DaoException;

    /**
     * 通过菜单type查询 Menu
     *
     * @param type 菜单type {@link cn.jianwoo.blog.enums.MenuTypeEnum} 1:前台 2:后台
     * @return List<Menu>
     * @author gulihua
     */

    List<Menu> queryMenuByType(Integer type);


    /**
     * 通过菜单type查询 有效的Menu
     * VALID = 1
     *
     * @param type 菜单type {@link cn.jianwoo.blog.enums.MenuTypeEnum} 1:前台 2:后台
     * @return List<Menu>
     * @author gulihua
     */

    List<Menu> queryEffectiveMenuByType(Integer type);


    /**
     * 通过菜单type 和 父 oid 查询 Menu
     *
     * @param parentOid 父 oid
     * @param type      菜单type {@link cn.jianwoo.blog.enums.MenuTypeEnum} 1:前台 2:后台
     * @return List<Menu>
     * @author gulihua
     */
    List<Menu> queryMenuByParentIdAndType(Long parentOid, Integer type);

}