package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.MenuBO;
import cn.jianwoo.blog.service.bo.MenuValidateBO;

import java.util.List;

public interface MenuBizService {
    /**
     * 获取后台的菜单
     *
     * @return
     * @author gulihua
     */
    List<MenuBO> queryAdminMenuList() throws JwBlogException;


    /**
     * 获取所有前台的菜单
     *
     * @return
     * @author gulihua
     */
    List<MenuBO> queryAllMainMenuList() throws JwBlogException;

    /**
     * 获取有效的前台的菜单
     *
     * @return
     * @author gulihua
     */
    List<MenuBO> queryEffectiveMainMenuList() throws JwBlogException;


    /**
     * T根据类型统计菜单数量
     *
     * @param type 类型 [MENU.TYPE] {@link MenuTypeEnum}
     * @return
     * @author gulihua
     */
    int countMenu(String type);


    /**
     * 添加菜单
     *
     * @param menuBO 菜单名
     * @return
     * @author gulihua
     */
    void doAddMenu(MenuBO menuBO) throws JwBlogException;


    /**
     * 根据类型和父oid统计菜单数量
     *
     * @param type      类型 [MENU.TYPE] {@link MenuTypeEnum}
     * @param parentOid 菜单父oid
     * @return
     * @author gulihua
     */
    int countMenuWithSameLevel(String type, Long parentOid);


    /**
     * 菜单序号重置
     *
     * @param oids 菜单oid list
     * @return
     * @author gulihua
     */
    void doReSortMenuByoids(Long[] oids) throws JwBlogException;


    /**
     * 更新菜单名字
     *
     * @param text 菜单文本
     * @param oid  菜单主键
     * @return
     * @author gulihua
     */
    void doUpdateMenuName(String text, Long oid) throws JwBlogException;


    /**
     * 通过主键获取菜单名字
     *
     * @param oid 菜单主键
     * @return
     * @author gulihua
     */
    String queryMenuNameById(Long oid) throws JwBlogException;


    /**
     * 根据子oid获取父菜单文本名(TEXT)
     *
     * @param oid 子菜单主键
     * @return
     * @author gulihua
     */
    String queryParentMenuTextBySubId(Long oid) throws JwBlogException;


    /**
     * 根据子oid获取父菜单
     *
     * @param oid 子菜单主键
     * @return
     * @author gulihua
     */
    Menu queryParentMenuBySubId(Long oid) throws JwBlogException;


    /**
     * 根据父oid获取子菜单list
     *
     * @param oid 父菜单主键
     * @return
     * @author gulihua
     */
    List<Menu> querySubMenuByParentId(Long oid) throws JwBlogException;


    /**
     * 根据类型获取所有排好序的子菜单
     *
     * @param type 类型 [MENU.TYPE] {@link MenuTypeEnum}
     * @return
     * @author gulihua
     */
    List<Menu> querySubMenuOrderedList(String type);


    /**
     * 把前台菜单重新排序
     *
     * @param oidList 排序的menu的oid列表
     * @return
     * @author gulihua
     */
    void doSortMainMenuList(List<Long> oidList) throws JwBlogException;


    /**
     * 更新菜单名字
     *
     * @param menuBO 菜单
     * @return
     * @author gulihua
     */
    void doUpdateMenu(MenuBO menuBO) throws JwBlogException;


    /**
     * 删除菜单
     *
     * @param oid 菜单主键
     * @return
     * @author gulihua
     */
    void doRemoveMenuById(Long oid) throws JwBlogException;

    /**
     * 验证菜单中是否有子菜单（删除菜单前使用）
     *
     * @param oid 菜单主键
     * @return MenuValidateBO
     * @author gulihua
     */
    MenuValidateBO doValidateSubMenuExist(Long oid) throws JwBlogException;

    /**
     * 验证菜单中是否有文章（删除菜单前使用）
     *
     * @param oid 菜单主键
     * @return MenuValidateBO
     * @author gulihua
     */
    MenuValidateBO doValidateArticleExistsInMenu(Long oid) throws JwBlogException;


    /**
     * 通过oid查询菜单
     *
     * @param oid 菜单主键
     * @return MenuBO
     * @author gulihua
     */
    MenuBO queryMenuByOid(String oid) throws JwBlogException;

}
