package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.page.TagsApiUrlConfig;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.TagAddRequest;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.validation.BizValidation;
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
 * @date 2020-09-30 11:23
 */
@RestController
@RequestMapping(TagsApiUrlConfig.URL_PREFIX)
public class TagsApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TagsApiController.class);
    @Autowired
    private TagsBizService tagsBizService;

    @PostMapping(TagsApiUrlConfig.URL_TAG_ADD)
    public String addTag(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TagAddRequest request = this.convertParam(param, TagAddRequest.class);
            BizValidation.paramValidate(request.getTagText(), "tagText", "标签文本不能为空!");
            tagsBizService.doAddTags(request.getTagText());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    @PostMapping(TagsApiUrlConfig.URL_TAG_REMOVE)
    public String removeTag(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "标签oid不能为空!");
            tagsBizService.doRemoveTags(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

}
