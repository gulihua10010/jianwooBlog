package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.page.TagsApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.TagListRequest;
import cn.jianwoo.blog.dto.request.TagRequest;
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
            TagRequest request = this.convertParam(param, TagRequest.class);
            BizValidation.paramValidate(request.getTagText(), "tagText", "标签文本不能为空!");
            BizValidation.paramLengthValidate(request.getTagText(), Constants.TAGS_LENGTH, "tagText", "标签内容不能大于10个字符!");
            tagsBizService.doAddTag(request.getTagText());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

    @PostMapping(TagsApiUrlConfig.URL_TAG_UPDATE)
    public String updateTag(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TagRequest request = this.convertParam(param, TagRequest.class);
            BizValidation.paramValidate(request.getOid(), "oid", "标签oid不能为空!");
            BizValidation.paramValidate(request.getTagText(), "tagText", "标签内容不能为空!");
            BizValidation.paramLengthValidate(request.getTagText(), Constants.TAGS_LENGTH, "tagText", "标签内容不能大于10个字符!");
            tagsBizService.doUpdateTags(request.getTagText(), request.getOid());

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

    @PostMapping(TagsApiUrlConfig.URL_TAG_ADD_LIST)
    public String addTagList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TagListRequest request = this.convertParam(param, TagListRequest.class);
            BizValidation.paramValidate(request.getTagList(), "tagList", "列表中存在空标签!");
            for (String tag : request.getTagList()) {
                BizValidation.paramLengthValidate(tag.trim(), Constants.TAGS_LENGTH, "tagName", "列表中标签内容不能大于10个字符!");
            }
            tagsBizService.doAddTagList(request.getTagList());

        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

}
