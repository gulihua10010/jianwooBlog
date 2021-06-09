package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.router.admin.CommApiUrlConfig;
import cn.jianwoo.blog.dto.response.FileUploadResponse;
import cn.jianwoo.blog.dto.response.vo.FileUploadResVO;
import cn.jianwoo.blog.entity.FileUpload;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.FileUploadService;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${spring.servlet.multipart.max-request-size}")
    private String maxSize;

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
        FileUploadResponse response = null;
        try {
            BizValidation.paramValidate(file, "file");
            Long size = JwUtil.processMaxSizeStr(maxSize);
            BizValidation.paramFileSizeValidate(file, size, String.format("上传文件支持的最大的大小为：%s, 当前为：%s", JwUtil.parseSize(size), JwUtil.parseSize(file.getSize())));
            log.info("====>>>Start Uploading files, name = {}, size = {}, Maximum limit size supported = {}.", file.getOriginalFilename(), file.getSize(), size);
            String uploadUrl = getServerIPPort() + CommApiUrlConfig.URL_RES_PREFIX;
            FileUpload fileUpload = fileUploadService.doUpload(file, uploadUrl, true);
            response = new FileUploadResponse();
            FileUploadResVO vo = new FileUploadResVO();
            vo.setFileName(fileUpload.getFileName());
            vo.setUrl(fileUpload.getUrl());
            vo.setRealFileName(fileUpload.getOldFileName());
            JSONObject mediaInfoJson=JSON.parseObject(fileUpload.getMediaInfo());
            vo.setMediaInfo(mediaInfoJson);
            response.setFile(vo);
        } catch (Exception e) {
            return exceptionToString(e);
        }
        return responseToJSONString(response);
    }



}
