package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.TempArticleVO;
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
public class TempArticleInfoResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;
    private TempArticleVO data;

    public static TempArticleInfoResponse getInstance() {
        return new TempArticleInfoResponse();
    }
}