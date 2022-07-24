package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-21 19:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GeetestResponse extends BaseResponseDto {
    private String challenge;
    private String gt;
    private boolean newCaptcha;
}