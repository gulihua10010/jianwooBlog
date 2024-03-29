package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import lombok.AllArgsConstructor;
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
public class CaptchaTokenResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800809L;

    private String token;

    public CaptchaTokenResponse(String token) {
        this.token = token;
    }

    public static CaptchaTokenResponse getInstance() {
        return new CaptchaTokenResponse();
    }

}