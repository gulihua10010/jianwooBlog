package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.TagsApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.TagListRequest;
import cn.jianwoo.blog.dto.request.TagRequest;
import cn.jianwoo.blog.dto.response.TagListResponse;
import cn.jianwoo.blog.dto.response.TagResponse;
import cn.jianwoo.blog.dto.response.vo.TagsVO;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.bo.TagsBO;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:23
 */
@RestController
@RequestMapping(TagsApiUrlConfig.URL_PREFIX)
@Slf4j
public class TagsApiController extends BaseController {
    @Autowired
    private TagsBizService tagsBizService;

    /**
     * 标签添加(标签页面)<br/>
     * url:/api/admin/tag/add<br/>
     *
     * @param param JSON 参数({@link TagRequest})
     *              tagText<br/>
     *              oid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
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

    /**
     * 标签更新(标签页面)<br/>
     * url:/api/admin/tag/update<br/>
     *
     * @param param JSON 参数({@link TagRequest})
     *              tagText<br/>
     *              oid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.TAGS_EDIT)
    @SubToken()
    @ApiVersion()
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

    /**
     * 标签删除(标签页面)<br/>
     * url:/api/admin/tag/remove<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
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

    /**
     * 标签集合的添加(标签页面)<br/>
     * url:/api/admin/tag/add/list<br/>
     *
     * @param param JSON 参数({@link TagListRequest})
     *              tagList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.TAGS_ADD_LIST)
    @SubToken()
    @ApiVersion()
    @PostMapping(TagsApiUrlConfig.URL_TAG_ADD_LIST)
    public String addTagList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TagListRequest request = this.convertParam(param, TagListRequest.class);
            BizValidation.paramValidate(request.getTagList(), "tagList", "提交列表为空!");
            BizValidation.paramValidateListContent(request.getTagList(), "tagList", "列表中存在空标签!");
            for (String tag : request.getTagList()) {
                BizValidation.paramLengthValidate(tag.trim(), Constants.TAGS_LENGTH, "tagName", "列表中标签内容不能大于10个字符!");
            }
            tagsBizService.doAddTagList(request.getTagList());

        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 获取所有标签<br/>
     * url:/api/admin/tag/list<br/>
     *
     * @return 返回响应 {@link TagListResponse}
     * status<br/>
     * data<br/>
     * --id<br/>
     * --name<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(TagsApiUrlConfig.URL_TAG_LIST)
    public String getTagsList() {
        TagListResponse response = TagListResponse.getInstance();
        List<TagsVO> list = new ArrayList<TagsVO>();
        List<TagsBO> tags = tagsBizService.queryAllTags();
        if (CollectionUtils.isNotEmpty(tags)) {
            for (TagsBO tag : tags) {
                TagsVO vo = JwBuilder.of(TagsVO::new)
                        .with(TagsVO::setId, tag.getId())
                        .with(TagsVO::setName, StringEscapeUtils.escapeHtml4(tag.getName())).build();
                list.add(vo);
            }
        }

        response.setData(list);
        return super.responseToJSONString(response);

    }

    /**
     * 获取文章标签<br/>
     * url:/api/admin/tag/article/list/{artId}<br/>
     *
     * @return 返回响应 {@link TagListResponse}
     * status<br/>
     * data<br/>
     * --id<br/>
     * --name<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(TagsApiUrlConfig.URL_TAG_ARTICLE_LIST)
    public String getTagsListByArtOid(@PathVariable("artId") Long artId) {
        TagListResponse response = null;
        try {
            BizValidation.paramValidate(artId, "artId", "文章id不能为空!");
            response = TagListResponse.getInstance();
            List<TagsVO> list = new ArrayList<TagsVO>();
            List<TagsBO> artTags = tagsBizService.queryTagsByArtOid(artId);
            for (TagsBO tag : artTags) {
                TagsVO vo = JwBuilder.of(TagsVO::new)
                        .with(TagsVO::setId, tag.getId())
                        .with(TagsVO::setName, StringEscapeUtils.escapeHtml4(tag.getName())).build();
                list.add(vo);
            }
            response.setData(list);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }

    /**
     * 获取指定标签信息(标签页面)<br/>
     * url:/api/admin/tag/info/{id}<br/>
     *
     * @param id TAG oid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(TagsApiUrlConfig.URL_TAG_INFO)
    public String getTagInfo(@PathVariable("id") Long id) {
        TagResponse response = TagResponse.getInstance();
        try {
            BizValidation.paramValidate(id, "id", "标签oid不能为空!");
            TagsBO tags = tagsBizService.queryTagsByOid(id);
            TagsVO tagsVO = JwBuilder.of(TagsVO::new)
                    .with(TagsVO::setId, tags.getId())
                    .with(TagsVO::setName, tags.getName())
                    .build();
            response.setData(tagsVO);

        } catch (Exception e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }
}
