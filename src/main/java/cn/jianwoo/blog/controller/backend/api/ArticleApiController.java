package cn.jianwoo.blog.controller.backend.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.router.ArticleApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.dto.request.ArticlePageRequest;
import cn.jianwoo.blog.dto.request.ArticleSubmitRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.ArticleInfoResponse;
import cn.jianwoo.blog.dto.response.ArticleSummaryResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleInfoVO;
import cn.jianwoo.blog.dto.response.vo.ArticleMenuVO;
import cn.jianwoo.blog.dto.response.vo.ArticleVO;
import cn.jianwoo.blog.dto.response.vo.TagsVO;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleParam;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.biz.TempArticleBizService;
import cn.jianwoo.blog.service.bo.ArticleBO;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @date 2020-08-25 15:45
 */
@RestController
@RequestMapping(ArticleApiUrlConfig.URL_PREFIX)
@Slf4j
public class ArticleApiController extends BaseController {
    @Autowired
    private ArticleBizService articleBizService;
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private TagsBizService tagsBizService;
    @Autowired
    private TagsTransDao tagsTransDao;
    @Autowired
    private MenuBizService menuBizService;
    @Autowired
    private TempArticleBizService tempArticleBizService;
    @Autowired
    private TempArticleTransDao tempArticleTransDao;


    /**
     * 文章提交(文章发布页面)<br/>
     * 文章STATUS 为 1 <br/>
     * url:/api/admin/article/submit<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_PUBLISHED)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SUBMIT)
    public String submit(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(JwUtil.clearHtmlWithoutMedia(request.getArticleContent()), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramValidate(request.getVisitType(), "visitType", "文章访问类型不能为空!");
            BizValidation.paramValidate(request.getType(), "type", "文章类型不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            if (ArticleVisitEnum.PASSWORD.getValue().equals(request.getVisitType())) {
                BizValidation.paramValidate(request.getPassword(), "password", "文章密码不能为空!");
            }
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setStatus, ArticleStatusEnum.PUBLISHED.getValue()).build();
            articleBizService.doSaveArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 文章提交保存至草稿(文章发布页面)<br/>
     * 文章STATUS 为 0 <br/>
     * <p>
     * url:/api/admin/article/save/draft<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_PUBLISHED)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_DRAFT)
    public String saveToDraft(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setStatus, ArticleStatusEnum.DRAFT.getValue()).build();
            articleBizService.doSaveArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 文章提交保存至回收站(文章发布页面)<br/>
     * 文章STATUS 为 -1 <br/>
     * url:/api/admin/article/save/recycle<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_PUBLISHED)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_RECYCLE)
    public String saveToRecycle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setStatus, ArticleStatusEnum.RECYCLE.getValue()).build();
            articleBizService.doSaveArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 已提交的文章进行更新(文章编辑页面)<br/>
     * 文章STATUS 为 1 <br/>
     * url:/api/admin/article/update<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_EDIT)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_UPDATE)
    public String artUpdate(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(JwUtil.clearHtmlWithoutMedia(request.getArticleContent()), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramValidate(request.getVisitType(), "visitType", "文章访问类型不能为空!");
            BizValidation.paramValidate(request.getType(), "type", "文章类型不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            if (ArticleVisitEnum.PASSWORD.getValue().equals(request.getVisitType())) {
                BizValidation.paramValidate(request.getPassword(), "password", "文章密码不能为空!");
            }
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setOid, request.getArtOid())
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setStatus, ArticleStatusEnum.PUBLISHED.getValue()).build();

            articleBizService.doUpdateArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    /**
     * 文章草稿更新至发布状态(文章列表页面)<br/>
     * 文章STATUS 为 0 --> 1 <br/>
     * url:/api/admin/article/draft/status/publish<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DRAFT_STATUS_PUBLISH)
    public String draftArtPublish(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "文章id不能为空!");
            articleBizService.doPublishedArticle(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 文章信息(不包括文章内容)进行更新(文章列表页面)<br/>
     * url:/api/admin/article/info/update<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_QUICK_EDIT)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_INFO_UPDATE)
    public String artInfoUpdate(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramValidate(request.getVisitType(), "visitType", "文章访问类型不能为空!");
//            BizValidation.paramValidate(request.getType(), "type", "文章类型不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            if (ArticleVisitEnum.PASSWORD.getValue().equals(request.getVisitType())) {
                BizValidation.paramValidate(request.getPassword(), "password", "文章密码不能为空!");
            }
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setOid, request.getArtOid())
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags()).build();

            articleBizService.doUpdateArticleInfo(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 已保存的文章草稿进行更新(文章编辑页面)<br/>
     * 文章STATUS 为 0 <br/>
     * url:/api/admin/article/draft/save<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_EDIT)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DRAFT_SAVE)
    public String artDraftSave(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setOid, request.getArtOid())
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setStatus, ArticleStatusEnum.DRAFT.getValue()).build();

            articleBizService.doUpdateArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 把文章更新并且移动到回收站里(文章编辑页面)<br/>
     * 文章STATUS 为 0/1 --> -1 <br/>
     * url:/api/admin/article/save/remove/recycle<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_EDIT)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_AND_REMOVE_RECYCLE)
    public String artSaveAndRemoveToRecycle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setOid, request.getArtOid())
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setStatus, ArticleStatusEnum.RECYCLE.getValue()).build();
            articleBizService.doUpdateArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 把文章草稿更新并且发布(文章编辑页面)<br/>
     * 文章STATUS 为 0 --> 1 <br/>
     * url:/api/admin/article/draft/publish<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_EDIT)
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DRAFT_PUBLISH)
    public String artDraftPublish(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(JwUtil.clearHtmlWithoutMedia(request.getArticleContent()), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramValidate(request.getVisitType(), "visitType", "文章访问类型不能为空!");
            BizValidation.paramValidate(request.getType(), "type", "文章类型不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            if (ArticleVisitEnum.PASSWORD.getValue().equals(request.getVisitType())) {
                BizValidation.paramValidate(request.getPassword(), "password", "文章密码不能为空!");
            }
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setOid, request.getArtOid())
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setStatus, ArticleStatusEnum.PUBLISHED.getValue()).build();
            articleBizService.doUpdateArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 分页查询有效的文章集合(STATUS = 0/1)(文章列表页面)<br/>
     * url:/api/admin/article/effective/list<br/>
     *
     * @param param JSON 参数({@link ArticlePageRequest})
     *              title<br/>
     *              text<br/>
     *              status<br/>
     * @return 返回响应 {@link ArticleSummaryResponse}
     * code<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --publishDate<br/>
     * --modifiedDate<br/>
     * --author<br/>
     * --type<br/>
     * --status<br/>
     * --desc<br/>
     * @author gulihua
     */
    @GetMapping(ArticleApiUrlConfig.URL_ARTICLE_EFFECTIVE_LIST)
    public String queryEffectiveArticlePage(ArticlePageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));
        ArticleParam artParam = new ArticleParam();
        if (StringUtils.isNotBlank(param.getTitle())) {
            artParam.setTitle(param.getTitle().trim());
        }
        if (StringUtils.isNotBlank(param.getText())) {
            artParam.setText(param.getText().trim());
        }
        if (param.getStatus() != null) {
            List<Integer> statusList = new ArrayList<Integer>();
            if (ArticleStatusEnum.ALL.getValue().equals(param.getStatus())) {
                statusList.add(ArticleStatusEnum.PUBLISHED.getValue());
                statusList.add(ArticleStatusEnum.DRAFT.getValue());
            } else {
                statusList.add(param.getStatus());
            }
            artParam.setStatusParams(statusList);
        }
        artParam.setPageNo(param.getPage());
        artParam.setPageSize(param.getLimit());
        PageInfo<ArticleExt> articleExtPageInfo = articleBizService.queryEffectiveArticleList(artParam);
        List<ArticleVO> articleVOList = new ArrayList<>();
        ArticleSummaryResponse response = ArticleSummaryResponse.getInstance();
        for (ArticleExt articleExt : articleExtPageInfo.getList()) {
            ArticleVO vo = new ArticleVO();
            vo.setAuthor(articleExt.getAuthor());
            vo.setOid(articleExt.getOid());
            vo.setTitle(articleExt.getTitle());
            vo.setType(articleExt.getTypeName());
            vo.setStatus(articleExt.getStatus());
            vo.setPublishDate(DateUtil.formatDateTime(articleExt.getPushDate()));
            vo.setModifiedDate(DateUtil.formatDateTime(articleExt.getModifiedDate()));
            articleVOList.add(vo);
        }
        response.setData(articleVOList);
        response.setCount(articleExtPageInfo.getTotal());
        return super.responseToJSONString(response);

    }

    /**
     * 分页查询回收站文章集合(STATUS = -1)(文章列表页面)<br/>
     * url:/api/admin/article/recycle/list<br/>
     *
     * @param param JSON 参数({@link ArticlePageRequest})
     *              title<br/>
     *              text<br/>
     *              status<br/>
     * @return 返回响应 {@link ArticleSummaryResponse}
     * code<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --publishDate<br/>
     * --modifiedDate<br/>
     * --author<br/>
     * --type<br/>
     * --status<br/>
     * --desc<br/>
     * @author gulihua
     */
    @GetMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_LIST)
    public String queryRecycleBinArticlePage(ArticlePageRequest param) {
        super.printRequestParams(DomainUtil.toString(param));
        ArticleParam artParam = new ArticleParam();
        if (StringUtils.isNotBlank(param.getTitle())) {
            artParam.setTitle(param.getTitle().trim());
        }
        if (StringUtils.isNotBlank(param.getText())) {
            artParam.setText(param.getText().trim());
        }
        artParam.setPageNo(param.getPage());
        artParam.setPageSize(param.getLimit());
        PageInfo<ArticleExt> articleExtPageInfo = articleBizService.queryRecycleBinArticleList(artParam);
        List<ArticleVO> articleVOList = new ArrayList<>();
        ArticleSummaryResponse response = ArticleSummaryResponse.getInstance();
        for (ArticleExt articleExt : articleExtPageInfo.getList()) {
            ArticleVO vo = new ArticleVO();
            vo.setAuthor(articleExt.getAuthor());
            vo.setOid(articleExt.getOid());
            vo.setTitle(articleExt.getTitle());
            vo.setType(articleExt.getTypeName());
            vo.setStatus(articleExt.getStatus());
            vo.setPublishDate(DateUtil.formatDateTime(articleExt.getPushDate()));
            vo.setModifiedDate(DateUtil.formatDateTime(articleExt.getModifiedDate()));
            articleVOList.add(vo);
        }
        response.setData(articleVOList);
        response.setCount(articleExtPageInfo.getTotal());
        return super.responseToJSONString(response);

    }

    /**
     * 把文章集合移动至回收站(文章列表页面)<br/>
     * 文章STATUS 为 0/1 --> -1 <br/>
     * url:/api/admin/article/remove/recycle/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_REMOVE_RECYCLE_LIST)
    public String removeArticleListToRecycleBin(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "文章id不能为空!");
            articleBizService.doRemoveToRecycleBinList(request.getEntityOidList());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 把文章移动至回收站(文章列表页面)<br/>
     * 文章STATUS 为 0/1 --> -1 <br/>
     * url:/api/admin/article/remove/recycle<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_REMOVE_RECYCLE)
    public String removeArticleToRecycleBin(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "文章id不能为空!");
            articleBizService.doRemoveToRecycle(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 把回收站文章集合恢复至草稿状态(回收站列表页面)<br/>
     * 文章STATUS 为 -1 --> 0 <br/>
     * url:/api/admin/article/recycle/restore/draft/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_RESTORE_DRAFT_LIST)
    public String restoreArticleListToDraftList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "文章id不能为空!");
            articleBizService.doRestoreRecycleToDraftList(request.getEntityOidList());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 把回收站文章集合删除(回收站列表页面)<br/>
     * url:/api/admin/article/delete/recycle/list<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DELETE_RECYCLE_LIST)
    public String delArticleList(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "文章id不能为空!");
            articleBizService.doDeleteRecycleBinList(request.getEntityOidList());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 把回收站文章恢复至草稿状态(回收站列表页面)<br/>
     * 文章STATUS 为 -1 --> 0 <br/>
     * url:/api/admin/article/recycle/restore/draft<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_RESTORE_DRAFT)
    public String restoreArticleToDraft(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "文章id不能为空!");
            articleBizService.doRestoreRecycleBinArts(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 把回收站文章删除(回收站列表页面)<br/>
     * url:/api/admin/article/recycle/delete<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_DELETE)
    public String delArticle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "文章id不能为空!");
            articleBizService.doDeleteArticle(request.getEntityOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 获取文章信息<br/>
     * url:/api/admin/article/info/{id}<br/>
     *
     * @return 返回响应 {@link ArticleInfoResponse}
     * status<br/>
     * data<br/>
     * --id<br/>
     * --title<br/>
     * --author<br/>
     * --content<br/>
     * --menuOid<br/>
     * --imgSrc<br/>
     * --status<br/>
     * --visitType<br/>
     * --password<br/>
     * --isComment<br/>
     * --artTagsList<br/>
     * --tagsList<br/>
     * ----id<br/>
     * ----name<br/>
     * --menuList<br/>
     * ----id<br/>
     * ----name<br/>
     * ----id<br/>
     * ----name<br/>
     * --menuName<br/>
     * @author gulihua
     */
    @GetMapping(ArticleApiUrlConfig.URL_ARTICLE_INFO)
    public String getArticleInfo(@PathVariable("id") Long id) {
        ArticleInfoResponse response = ArticleInfoResponse.getInstance();
        Article article;
        try {
            BizValidation.paramValidate(id, "id", "文章id不能为空!");
            article = articleTransDao.queryArticleByPrimaryKey(id);
            ArticleInfoVO vo = JwBuilder.of(ArticleInfoVO::new)
                    .with(ArticleInfoVO::setId, article.getOid())
                    .with(ArticleInfoVO::setTitle, StringEscapeUtils.escapeHtml4(article.getTitle()))
                    .with(ArticleInfoVO::setAuthor, StringEscapeUtils.escapeHtml4(article.getAuthor()))
                    .with(ArticleInfoVO::setContent, article.getContent())
                    .with(ArticleInfoVO::setMenuOid, article.getTypeId())
                    .with(ArticleInfoVO::setImgSrc, article.getImgSrc())
                    .with(ArticleInfoVO::setIsComment, article.getIsComment())
                    .with(ArticleInfoVO::setPassword, article.getPassword())
                    .with(ArticleInfoVO::setVisitType, article.getVisitType())
                    .with(ArticleInfoVO::setStatus, article.getStatus())
                    .build();


            List<Tags> artTags = tagsBizService.queryTagsByArtOid(id);
            List<TagsVO> tagsList = new ArrayList<TagsVO>();
            if (CollectionUtils.isNotEmpty(artTags)) {
                for (Tags tag : artTags) {
                    TagsVO tagsListVO = JwBuilder.of(TagsVO::new)
                            .with(TagsVO::setId, tag.getOid())
                            .with(TagsVO::setName, StringEscapeUtils.escapeHtml4(tag.getContent())).build();
                    tagsList.add(tagsListVO);
                }
                vo.setArtTagsList(tagsList);
            }
            List<Tags> allTags = tagsTransDao.queryAllTags();
            List<TagsVO> allTagsList = new ArrayList<TagsVO>();
            if (CollectionUtils.isNotEmpty(allTags)) {
                for (Tags tag : allTags) {
                    TagsVO tagsListVO = JwBuilder.of(TagsVO::new)
                            .with(TagsVO::setId, tag.getOid())
                            .with(TagsVO::setName, StringEscapeUtils.escapeHtml4(tag.getContent())).build();
                    allTagsList.add(tagsListVO);
                }
                vo.setTagsList(allTagsList);
            }

            List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());
            List<ArticleMenuVO> menuVoList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(menuList)) {
                for (Menu menu : menuList) {
                    ArticleMenuVO menuVo = JwBuilder.of(ArticleMenuVO::new)
                            .with(ArticleMenuVO::setId, menu.getOid())
                            .with(ArticleMenuVO::setName, StringEscapeUtils.escapeHtml4(menu.getText())).build();
                    menuVoList.add(menuVo);
                }
                vo.setMenuList(menuVoList);
            }

            response.setData(vo);
        } catch (Exception e) {
            log.error(">> AdminPageController.articleEdit exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", id);
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }

    /**
     * 临时文章保存<br/>
     * url:/api/admin/article/temp/save<br/>
     *
     * @param param JSON 参数({@link ArticleSubmitRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              type<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     *              oldOid<br/>
     *              page<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @SubToken
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_TEMP_SAVE)
    public String doTempArticleSave(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);

            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setTypeId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTags, request.getTags())
                    .with(ArticleBO::setOldOid, request.getOldOid())
                    .with(ArticleBO::setPage, request.getPage())
                    .build();
            tempArticleBizService.doSaveTempArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 获取文章信息<br/>
     * url:/api/admin/article/last/temp/info<br/>
     *
     * @return 返回响应 {@link ArticleInfoResponse}
     * status<br/>
     * data<br/>
     * --id<br/>
     * --title<br/>
     * --author<br/>
     * --content<br/>
     * --menuOid<br/>
     * --imgSrc<br/>
     * --visitType<br/>
     * --password<br/>
     * --isComment<br/>
     * --artTagsList<br/>
     * --tagsList<br/>
     * ----id<br/>
     * @author gulihua
     */
    @GetMapping(ArticleApiUrlConfig.URL_ARTICLE_LAST_TEMP_INFO)
    public String getLastTempArticleInfo(Integer page) {
        ArticleInfoResponse response = ArticleInfoResponse.getInstance();
        TempArticle article;
        try {
            article = tempArticleTransDao.queryLastestTempArticle(page);
            if (null!=article)
            {
                ArticleInfoVO vo = JwBuilder.of(ArticleInfoVO::new)
                        .with(ArticleInfoVO::setId, article.getOid())
                        .with(ArticleInfoVO::setTitle, StringEscapeUtils.escapeHtml4(article.getTitle()))
                        .with(ArticleInfoVO::setAuthor, StringEscapeUtils.escapeHtml4(article.getAuthor()))
                        .with(ArticleInfoVO::setContent, article.getContent())
                        .with(ArticleInfoVO::setMenuOid, article.getTypeId())
                        .with(ArticleInfoVO::setImgSrc, article.getImgSrc())
                        .with(ArticleInfoVO::setIsComment, article.getIsComment())
                        .with(ArticleInfoVO::setPassword, article.getPassword())
                        .with(ArticleInfoVO::setVisitType, article.getVisitType())
                        .with(ArticleInfoVO::setStatus, article.getStatus())
                        .build();


                if (StringUtils.isNotBlank(article.getTags())) {
                    JSONArray jsonArray = JSON.parseArray(article.getTags());
                    List<TagsVO> tagsList = new ArrayList<TagsVO>();
                    if (jsonArray != null && jsonArray.size() > 0) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            TagsVO tagsListVO = JwBuilder.of(TagsVO::new)
                                    .with(TagsVO::setId, jsonArray.getLong(i))
                                    .build();
                            tagsList.add(tagsListVO);
                        }
                        vo.setArtTagsList(tagsList);
                    }
                }


                response.setData(vo);
            }

        } catch (Exception e) {
            log.error(">> AdminPageController.getLastTempArticleInfo exec failed, exception: \n", e);
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }

}