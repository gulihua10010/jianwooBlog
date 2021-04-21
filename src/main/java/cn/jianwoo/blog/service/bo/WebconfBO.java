package cn.jianwoo.blog.service.bo;

import cn.jianwoo.blog.enums.FormTypeEnum;
import cn.jianwoo.blog.enums.ValidateTypeEnum;
import cn.jianwoo.blog.enums.ValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:53
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class WebconfBO implements Serializable {
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
    private String title;
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
    private Boolean mandatory;
    /**
     * 验证类型  {@link ValidateTypeEnum}
     */
    private String validateType;
    /**
     * 验证值
     */
    private String validateValue;



}