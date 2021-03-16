package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.router.CommApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.response.FileUploadResponse;
import cn.jianwoo.blog.dto.response.vo.FileUploadResVO;
import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.FileUploadService;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-18 11:01
 */
@RestController
@RequestMapping(CommApiUrlConfig.URL_PREFIX)
@Slf4j
public class UploadController extends BaseController {
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 标签集合的添加(标签页面)<br/>
     * url:/api/file/upload<br/>
     *
     * @param file 上传的文件
     * @return 返回响应 {@link FileUploadResponse}
     * file
     * --fileName<br/>
     * --url<br/>
     * @author gulihua
     */
    @PostMapping(CommApiUrlConfig.URL_FILE_UPLOAD)
    public String upload(@RequestParam("file") MultipartFile file) throws JwBlogException {
        BizValidation.paramValidate(file, "file");
        log.info("====>>>Start Uploading files, name = {}, size = {}.", file.getOriginalFilename(), file.getSize());
        String uploadUrl = getServerIPPort() + Constants.URL_SEPARATOR + CommApiUrlConfig.URL_RES_PREFIX;
        FileUpload fileUpload = fileUploadService.doUpload(file, uploadUrl, true);
        FileUploadResponse response = new FileUploadResponse();
        FileUploadResVO vo = new FileUploadResVO();
        BeanUtils.copyProperties(fileUpload, vo);
        response.setFile(vo);
        return responseToJSONString(response);
    }
}
