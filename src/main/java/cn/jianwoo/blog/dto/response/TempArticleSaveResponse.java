package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.LongToStringSerializerConfig;
import com.alibaba.fastjson.annotation.JSONField;
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
public class TempArticleSaveResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;

    @JSONField(serializeUsing = LongToStringSerializerConfig.class)
    private Long oid;

    public static TempArticleSaveResponse getInstance() {
        return new TempArticleSaveResponse();
    }
}