package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.page.ClearCacheApiUrlConfig;
import cn.jianwoo.blog.dto.request.ClearCacheRequest;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ClearCacheBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 14:47
 */

@RestController
@RequestMapping(ClearCacheApiUrlConfig.URL_PREFIX)
public class ClearCacheApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TagsApiController.class);
    @Autowired
    private ClearCacheBizService clearCacheBizService;

    @PostMapping(ClearCacheApiUrlConfig.URL_COMMENT_ADD)
    public String clear(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ClearCacheRequest request = this.convertParam(param, ClearCacheRequest.class);
            clearCacheBizService.clearCache(request.isCache(), request.isLog(), request.isTemp());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }
}
