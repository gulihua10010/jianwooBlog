package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 19:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SubTokenResponse extends BaseResponseDto {
    private static final long serialVersionUID = 3728070239002639495L;
    /**
     * 生成的token
     */
    String token;

    public static SubTokenResponse getInstance() {
        return new SubTokenResponse();
    }


}
