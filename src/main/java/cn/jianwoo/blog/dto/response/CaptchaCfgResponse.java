package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-26 14:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CaptchaCfgResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;
    /**
     * 配置结果<br/>
     * TRUE:开启<br/>
     * FALSE:未开启<br/>
     */
    private String flag;

    public static CaptchaCfgResponse getInstance() {
        return new CaptchaCfgResponse();
    }
}