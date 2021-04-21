package cn.jianwoo.blog.dto.response.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description 前台子菜单
 * @date 2021-02-18 15:52
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class HomeSubMenuVO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单索引
     */
    private Integer index;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单文本
     */
    private String text;
    /**
     * 菜单路由
     */
    private String url;
    /**
     * 是否有效
     */
    private Boolean valid;
    /**
     * 父菜单oid
     */
    private Long parentOid;
}
