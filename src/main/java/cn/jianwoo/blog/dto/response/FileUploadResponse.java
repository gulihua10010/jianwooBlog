package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.FileUploadResVO;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-18 15:53
 */
public class FileUploadResponse extends BaseResponseDto {
    private static final long serialVersionUID = -3603543587584431681L;
    private FileUploadResVO file;

    public FileUploadResVO getFile() {
        return file;
    }


    public void setFile(FileUploadResVO file) {
        this.file = file;
    }
}
