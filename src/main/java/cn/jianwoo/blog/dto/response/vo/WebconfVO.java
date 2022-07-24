package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.enums.FormTypeEnum;
import cn.jianwoo.blog.enums.ValidateTypeEnum;
import cn.jianwoo.blog.enums.ValueTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:53
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class WebconfVO implements Serializable {
    /**
     * 键名
     */
    private String key;
    /**
     * 配置描述
     */
    private String desc;
    /**
     * 配置标题/标签
     */
    private String titleDsp;
    /**
     * 配置提示
     */
    private String tipsDsp;

    /**
     * 值类型  {@link ValueTypeEnum}
     */
    private String valueType;
    /**
     * 表单类型  {@link FormTypeEnum}
     */
    private String formType;
    /**
     * 值
     */
    private String value;
    /**
     * 是否必填
     */
    private Boolean required;
    /**
     * 验证类型  {@link ValidateTypeEnum}
     */
    private String validateType;
    /**
     * 验证值
     */
    private String validateValue;
}