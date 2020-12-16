package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.JwBlogException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-18 14:04
 */
public interface FileUploadService {
    /**
     * 上传文件到服务器
     *
     * @param multipartFile 表单文件对象
     * @param url           文件路由（不包含文件名, eg: http://127.0.0.1/context/res）
     * @param isReName      是否重命名
     * @return FileUpload 文件上传对象
     * @author gulihua
     */
    FileUpload doUpload(MultipartFile multipartFile, String url, boolean isReName) throws JwBlogException;
}
