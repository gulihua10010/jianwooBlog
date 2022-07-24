package cn.jianwoo.blog.dto.response.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-18 15:52
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConditionVO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 搜索条件
     */
    private String condition;
    /**
     * 搜索条件Id
     */
    private Integer conditionId;
    /**
     * 搜索类型
     */
    private String conditionType;

}
