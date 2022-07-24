package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.FileUploadResVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-18 15:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FileUploadResponse extends BaseResponseDto {
    private static final long serialVersionUID = -3603543587584431681L;
    /**
     * 上传的文件
     */
    private FileUploadResVO file;
}
