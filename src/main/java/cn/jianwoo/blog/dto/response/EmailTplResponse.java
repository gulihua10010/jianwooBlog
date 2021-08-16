package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.EmailTplVO;
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
public class EmailTplResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private EmailTplVO data;

    public static EmailTplResponse getInstance() {
        return new EmailTplResponse();
    }


}
