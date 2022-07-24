package cn.jianwoo.blog.dto.response.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-04 13:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MainHomeMenuVO implements Serializable {
    private static final long serialVersionUID = 8924273232106445568L;
    /**
     * 菜单ID
     */
    private Long id;
    /**
     * 菜单,子菜单ID
     */
    private List<Long> ids;
    /**
     * 菜单文本(MENU.TEXT)
     */
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单跳转地址
     */
    private String url;
    /**
     * 是否是文章类别
     */
    private Boolean isCategory;
    /**
     * 子菜单列表
     */
    private List<MainHomeSubMenuVO> subList;

}
