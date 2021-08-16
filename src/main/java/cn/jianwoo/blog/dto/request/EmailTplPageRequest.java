package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmailTplPageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 邮件模板编号
     */
    private String code;
    /**
     * 描述
     */
    private String desc;


}
