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
@AllArgsConstructor
public class ValidationResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;
    private String key;

    public ValidationResponse(String code, String msg, String key) {
        super(code, msg);
        this.key = key;
    }

    public static ValidationResponse getInstance() {
        return new ValidationResponse();
    }
}