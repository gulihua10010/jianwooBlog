package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 16:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CaptchaCfgRequest extends BaseRequestDto {
    private static final long serialVersionUID = -8260719513799536531L;
    /**
     * 类型<br/>
     * 10:登录<br/>
     * 20:忘记密码<br/>
     */
    private String type;

}