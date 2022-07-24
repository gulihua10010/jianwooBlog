package cn.jianwoo.blog.service.bo;

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
public class WebconfBO implements Serializable {
    private static final long serialVersionUID = 1325358067879236044L;
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
    /**
     * TAG标签名
     */
    private String tabType;
    /**
     * TAG标签描述
     */
    private String tabTypeDsp;
    /**
     * 索引
     */
    private Integer index ;


}