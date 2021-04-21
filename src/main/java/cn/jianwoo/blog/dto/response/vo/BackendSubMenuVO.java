package cn.jianwoo.blog.dto.response.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-04 13:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BackendSubMenuVO implements Serializable {

    private static final long serialVersionUID = 1020808623699146901L;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单文本(MENU.TEXT)
     */
    private String title;
    /**
     * 菜单跳转地址
     */
    private String jump;
}
