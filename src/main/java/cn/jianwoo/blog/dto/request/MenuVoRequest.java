package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 14:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuVoRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 4600458570269532916L;
    /**
     * 菜单oid
     */
    private Long oid;
    /**
     * 菜单父oid
     */
    private Long parentOid;
    /**
     * 菜单显示文本
     */
    private String text;
    /**
     * 菜单跳转路由
     */
    private String url;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否有效
     */
    private Boolean valid;


}
