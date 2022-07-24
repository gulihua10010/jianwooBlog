package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.WebPageConfigVO;
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
public class WebConfigPageResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;
    private WebPageConfigVO data;

    public static WebConfigPageResponse getInstance() {
        return new WebConfigPageResponse();
    }
}