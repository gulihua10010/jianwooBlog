package cn.jianwoo.blog.service.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 14:31
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class MenuBO implements Serializable {
    private static final long serialVersionUID = -7200997826206334561L;

    /**
     * 主键 oid [MENU.OID]
     */
    private Long oid;

    /**
     * 菜单名 [MENU.NAME]
     */
    private String name;

    /**
     * 索引 [MENU.INDEX]
     */
    private Integer index;

    /**
     * 类型 [MENU.TYPE]
     */
    private String type;

    /**
     * 图标[MENU.ICON]
     */
    private String icon;

    /**
     * 文本，用于显示[MENU.TEXT]
     */
    private String text;

    /**
     * url[MENU.URL]
     */
    private String url;

    /**
     * 是否是文章类别[MENU.FLAG_CATEGORY]
     */
    private Boolean flagCategory;


    /**
     * 是否有效[MENU.VALID]
     */
    private Boolean valid;

    /**
     * 父oid[MENU.PARENT_OID]
     */
    private Long parentOid;

    /**
     * 子菜单
     */
    private List<MenuBO> subMenuList;

    /**
     * 是否是父菜单
     */
    private Boolean flagParentMenu;



}
