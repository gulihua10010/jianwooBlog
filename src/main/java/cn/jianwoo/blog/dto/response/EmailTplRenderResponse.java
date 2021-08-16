package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmailTplRenderResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private String data;

    public static EmailTplRenderResponse getInstance() {
        return new EmailTplRenderResponse();
    }


}
