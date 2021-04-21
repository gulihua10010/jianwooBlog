package cn.jianwoo.blog.dto.response.vo;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class BackendMenuVO implements Serializable {
    private static final long serialVersionUID = 8924273232106445568L;
    /**
     * 菜单文本(MENU.TEXT)
     */
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 子菜单列表
     */
    private List<BackendSubMenuVO> list;

}
