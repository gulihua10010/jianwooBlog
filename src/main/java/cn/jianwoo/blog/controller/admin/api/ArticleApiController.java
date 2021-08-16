package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.ArticleApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.ArticlePageRequest;
import cn.jianwoo.blog.dto.request.ArticleSubmitRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.TempArticleQueryRequest;
import cn.jianwoo.blog.dto.request.TempArticleSaveRequest;
import cn.jianwoo.blog.dto.request.TempArticleStatusRequest;
import cn.jianwoo.blog.dto.response.ArticleInfoResponse;
import cn.jianwoo.blog.dto.response.ArticleSummaryResponse;
import cn.jianwoo.blog.dto.response.TempArticleInfoResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleMenuVO;
import cn.jianwoo.blog.dto.response.vo.ArticleSummaryVO;
import cn.jianwoo.blog.dto.response.vo.ArticleVO;
import cn.jianwoo.blog.dto.response.vo.TagsVO;
import cn.jianwoo.blog.dto.response.vo.TempArticleVO;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.TempArticleBizService;
import cn.jianwoo.blog.service.bo.ArticleBO;
import cn.jianwoo.blog.service.bo.TagsBO;
import cn.jianwoo.blog.service.bo.TempArticleBO;
import cn.jianwoo.blog.service.param.ArticleParam;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
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
    private TempArticleBizService tempArticleBizService;


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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SUBMIT)
    public String doArticleSubmit(@RequestBody String param) {
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
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList())
                    .with(ArticleBO::setTempArtOid, request.getTempArtOid())
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_DRAFT)
    public String doArticleSaveToDraft(@RequestBody String param) {
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
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList())
                    .with(ArticleBO::setTempArtOid, request.getTempArtOid())
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_RECYCLE)
    public String doArticleSaveToRecycle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
//            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            ArticleBO articleBO = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setTitle, request.getTitle())
                    .with(ArticleBO::setContent, request.getArticleContent())
                    .with(ArticleBO::setAuthor, request.getAuthor())
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList())
                    .with(ArticleBO::setTempArtOid, request.getTempArtOid())
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_UPDATE)
    public String doArticleUpdate(@RequestBody String param) {
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
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList())
                    .with(ArticleBO::setTempArtOid, request.getTempArtOid())
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DRAFT_STATUS_PUBLISH)
    public String doArticleDraft2Publish(@RequestBody String param) {
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_INFO_UPDATE)
    public String doArticleInfoUpdate(@RequestBody String param) {
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
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList()).build();


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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DRAFT_SAVE)
    public String doArticleDraftSave(@RequestBody String param) {
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
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList())
                    .with(ArticleBO::setTempArtOid, request.getTempArtOid())
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_AND_REMOVE_RECYCLE)
    public String doArticleSaveAndRemove2Recycle(@RequestBody String param) {
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
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList())
                    .with(ArticleBO::setTempArtOid, request.getTempArtOid())
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DRAFT_PUBLISH)
    public String doArticleDraft2PublishInEdit(@RequestBody String param) {
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
                    .with(ArticleBO::setMenuId, request.getType())
                    .with(ArticleBO::setIsComment, request.getIsComment())
                    .with(ArticleBO::setVisitType, request.getVisitType())
                    .with(ArticleBO::setImgSrc, request.getImgSrc())
                    .with(ArticleBO::setPassword, request.getPassword())
                    .with(ArticleBO::setTagOidList, request.getTagOidList())
                    .with(ArticleBO::setTempArtOid, request.getTempArtOid())
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
    @ApiVersion()
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
        if (StringUtils.isNotBlank(param.getStatus())) {
            List<String> statusList = new ArrayList<>();
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
        PageInfo<ArticleBO> articleExtPageInfo = articleBizService.queryEffectiveArticleList(artParam);
        List<ArticleSummaryVO> articleSummaryVOList = new ArrayList<>();
        ArticleSummaryResponse response = ArticleSummaryResponse.getInstance();
        for (ArticleBO articleBO : articleExtPageInfo.getList()) {
            ArticleSummaryVO vo = new ArticleSummaryVO();
            vo.setAuthor(articleBO.getAuthor());
            vo.setOid(articleBO.getOid());
            vo.setTitle(articleBO.getTitle());
            vo.setType(articleBO.getTypeName());
            vo.setStatus(articleBO.getStatus());
            vo.setPublishTimeStr(DateUtil.formatDateTime(articleBO.getPushTime()));
            vo.setPublishTime(articleBO.getPushTime());
            vo.setModifiedTimeStr(DateUtil.formatDateTime(articleBO.getModifiedTime()));
            vo.setModifiedTime(articleBO.getModifiedTime());
            articleSummaryVOList.add(vo);
        }
        response.setData(articleSummaryVOList);
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
    @ApiVersion()
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
        PageInfo<ArticleBO> articleExtPageInfo = articleBizService.queryRecycleBinArticleList(artParam);
        List<ArticleSummaryVO> articleSummaryVOList = new ArrayList<>();
        ArticleSummaryResponse response = ArticleSummaryResponse.getInstance();
        for (ArticleBO articleBO : articleExtPageInfo.getList()) {
            ArticleSummaryVO vo = new ArticleSummaryVO();
            vo.setAuthor(articleBO.getAuthor());
            vo.setOid(articleBO.getOid());
            vo.setTitle(articleBO.getTitle());
            vo.setType(articleBO.getTypeName());
            vo.setStatus(articleBO.getStatus());
            vo.setPublishTimeStr(DateUtil.formatDateTime(articleBO.getPushTime()));
            vo.setPublishTime(articleBO.getPushTime());
            vo.setModifiedTimeStr(DateUtil.formatDateTime(articleBO.getModifiedTime()));
            vo.setModifiedTime(articleBO.getModifiedTime());
            articleSummaryVOList.add(vo);
        }
        response.setData(articleSummaryVOList);
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_REMOVE_RECYCLE_LIST)
    public String doRemoveArticleListToRecycleBin(@RequestBody String param) {
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_REMOVE_RECYCLE)
    public String doRemoveArticleToRecycleBin(@RequestBody String param) {
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_RESTORE_DRAFT_LIST)
    public String doRestoreArticleListToDraftList(@RequestBody String param) {
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
    @ApiVersion()
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_RESTORE_DRAFT)
    public String doRestoreArticleToDraft(@RequestBody String param) {
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
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_DELETE)
    public String doDelArticle(@RequestBody String param) {
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
     * --menuName<br/>
     * --tempArticle
     * ----oid<br/>
     * ----title<br/>
     * ----author<br/>
     * ----content<br/>
     * ----menuOid<br/>
     * ----imgSrc<br/>
     * ----visitType<br/>
     * ----password<br/>
     * ----isComment<br/>
     * ----artTagsList<br/>
     * ------id<br/>
     *
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ArticleApiUrlConfig.URL_ARTICLE_INFO)
    public String queryArticleInfo(@PathVariable("id") String  id) {
        ArticleInfoResponse response = ArticleInfoResponse.getInstance();
        try {
            BizValidation.paramValidate(id, "id", "文章id不能为空!");
            ArticleBO articleBO = articleBizService.queryArticleEditInfo(id);
            if (articleBO != null) {
                ArticleVO articleVO = new ArticleVO();
                BeanUtils.copyProperties(articleBO, articleVO);
                articleVO.setId(articleBO.getOid());
                articleVO.setMenuOid(articleBO.getMenuId());
                if (CollectionUtils.isNotEmpty(articleBO.getArtTagsList())) {
                    List<TagsVO> tagsVOS = new ArrayList<>(articleBO.getArtTagsList().size());
                    articleBO.getArtTagsList().forEach(o -> {
                        TagsVO tagsVO = new TagsVO();
                        BeanUtils.copyProperties(o, tagsVO);
                        tagsVOS.add(tagsVO);
                    });
                    articleVO.setArtTagsList(tagsVOS);
                }
                if (CollectionUtils.isNotEmpty(articleBO.getAllTagsList())) {
                    List<TagsVO> tagsVOS = new ArrayList<>(articleBO.getAllTagsList().size());
                    articleBO.getAllTagsList().forEach(o -> {
                        TagsVO tagsVO = new TagsVO();
                        BeanUtils.copyProperties(o, tagsVO);
                        tagsVOS.add(tagsVO);
                    });
                    articleVO.setAllTagsList(tagsVOS);
                }
                if (CollectionUtils.isNotEmpty(articleBO.getMenuList())) {
                    List<ArticleMenuVO> menuVOList = new ArrayList<>(articleBO.getMenuList().size());
                    articleBO.getMenuList().forEach(o -> {
                        ArticleMenuVO menuVO = new ArticleMenuVO();
                        BeanUtils.copyProperties(o, menuVO);
                        menuVOList.add(menuVO);
                    });
                    articleVO.setMenuList(menuVOList);
                }
                if (null != articleBO.getTempArticle()) {
                    TempArticleVO tempArticleInfoBO = new TempArticleVO();
                    BeanUtils.copyProperties(articleBO.getTempArticle(), tempArticleInfoBO);
                    if (CollectionUtils.isNotEmpty(articleBO.getTempArticle().getArtTagsList())) {
                        List<TagsVO> tagsVOS = new ArrayList<>(articleBO.getTempArticle()
                                .getArtTagsList().size());
                        articleBO.getTempArticle().getArtTagsList().forEach(o -> {
                            TagsVO tagsVO = new TagsVO();
                            BeanUtils.copyProperties(o, tagsVO);
                            tagsVOS.add(tagsVO);
                        });
                        tempArticleInfoBO.setArtTagsList(tagsVOS);
                    }
                    articleVO.setTempArticle(tempArticleInfoBO);
                }

                response.setData(articleVO);

            }
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
     * @param param JSON 参数({@link TempArticleSaveRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              menuOid<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     *              oldOid<br/>
     *              pageType<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @SubToken
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_TEMP_SAVE)
    public String doTempArticleSave(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TempArticleSaveRequest request = this.convertParam(param, TempArticleSaveRequest.class);

            TempArticleBO articleBO = JwBuilder.of(TempArticleBO::new)
                    .with(TempArticleBO::setOid, request.getOid())
                    .with(TempArticleBO::setTitle, request.getTitle())
                    .with(TempArticleBO::setContent, request.getArticleContent())
                    .with(TempArticleBO::setAuthor, request.getAuthor())
                    .with(TempArticleBO::setMenuOid, request.getMenuId())
                    .with(TempArticleBO::setIsComment, request.getIsComment())
                    .with(TempArticleBO::setVisitType, request.getVisitType())
                    .with(TempArticleBO::setImgSrc, request.getImgSrc())
                    .with(TempArticleBO::setPassword, request.getPassword())
                    .with(TempArticleBO::setOldOid, request.getOldOid())
                    .with(TempArticleBO::setPageType, request.getPageType())
                    .build();
            if (CollectionUtils.isNotEmpty(request.getTags())) {
                List<TagsBO> list = new ArrayList<>();
                request.getTags().forEach(o -> {
                    TagsBO bo = new TagsBO();
                    bo.setId(o.getId());
                    bo.setName(o.getName());
                    list.add(bo);
                });
                articleBO.setArtTagsList(list);
            }
            tempArticleBizService.doSaveTempArticle(articleBO);

        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 临时文章保存<br/>
     * url:/api/admin/article/temp/update<br/>
     *
     * @param param JSON 参数({@link TempArticleSaveRequest})
     *              title<br/>
     *              author<br/>
     *              articleContent<br/>
     *              tags<br/>
     *              menuOid<br/>
     *              imgSrc<br/>
     *              visitType
     *              password<br/>
     *              isComment<br/>
     *              oldOid<br/>
     *              pageType<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @SubToken
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_TEMP_UPDATE)
    public String doTempArticleUpdate(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TempArticleSaveRequest request = this.convertParam(param, TempArticleSaveRequest.class);
            BizValidation.paramValidate(request.getOid(), "oid", "临时文章id不能为空!");

            TempArticleBO articleBO = JwBuilder.of(TempArticleBO::new)
                    .with(TempArticleBO::setOid, request.getOid())
                    .with(TempArticleBO::setTitle, request.getTitle())
                    .with(TempArticleBO::setContent, request.getArticleContent())
                    .with(TempArticleBO::setAuthor, request.getAuthor())
                    .with(TempArticleBO::setMenuOid, request.getMenuId())
                    .with(TempArticleBO::setIsComment, request.getIsComment())
                    .with(TempArticleBO::setVisitType, request.getVisitType())
                    .with(TempArticleBO::setImgSrc, request.getImgSrc())
                    .with(TempArticleBO::setPassword, request.getPassword())
                    .with(TempArticleBO::setOldOid, request.getOldOid())
                    .with(TempArticleBO::setPageType, request.getPageType())
                    .build();
            if (CollectionUtils.isNotEmpty(request.getTags())) {
                List<TagsBO> list = new ArrayList<>();
                request.getTags().forEach(o -> {
                    TagsBO bo = new TagsBO();
                    bo.setId(o.getId());
                    bo.setName(o.getName());
                    list.add(bo);
                });
                articleBO.setArtTagsList(list);
            }
            tempArticleBizService.doUpdateTempArticle(articleBO);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 获取文章信息<br/>
     * url:/api/admin/article/last/temp/info<br/>
     *
     * @param param JSON 参数({@link TempArticleQueryRequest})
     *              editArtOid<br/>
     *              page<br/>
     * @return 返回响应 {@link TempArticleInfoResponse}
     * status<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --author<br/>
     * --content<br/>
     * --menuOid<br/>
     * --imgSrc<br/>
     * --visitType<br/>
     * --password<br/>
     * --isComment<br/>
     * --artTagsList<br/>
     * ----id<br/>
     * ----name<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_LAST_TEMP_INFO)
    public String queryLastTempArticleInfo(@RequestBody String param) {
        TempArticleInfoResponse response = TempArticleInfoResponse.getInstance();
        TempArticleBO article;
        try {
            TempArticleQueryRequest request = this.convertParam(param, TempArticleQueryRequest.class);
            BizValidation.paramValidate(request.getPageType(), "pageType", "page不能为空!");

            article = tempArticleBizService.queryLastestTempArticle(request.getEditArtOid(), request.getPageType());
            if (null != article) {
                TempArticleVO vo = JwBuilder.of(TempArticleVO::new)
                        .with(TempArticleVO::setOid, article.getOid())
                        .with(TempArticleVO::setTitle, StringEscapeUtils.escapeHtml4(article.getTitle()))
                        .with(TempArticleVO::setAuthor, StringEscapeUtils.escapeHtml4(article.getAuthor()))
                        .with(TempArticleVO::setContent, article.getContent())
                        .with(TempArticleVO::setMenuOid, article.getMenuOid())
                        .with(TempArticleVO::setImgSrc, article.getImgSrc())
                        .with(TempArticleVO::setIsComment, article.getIsComment())
                        .with(TempArticleVO::setPassword, article.getPassword())
                        .with(TempArticleVO::setVisitType, article.getVisitType())
                        .with(TempArticleVO::setStatus, article.getStatus())
                        .build();


                if (CollectionUtils.isNotEmpty(article.getArtTagsList())) {
                    List<TagsVO> tagsList = new ArrayList<TagsVO>();
                    for (TagsBO tagsBO : article.getArtTagsList()) {
                        TagsVO tagsListVO = JwBuilder.of(TagsVO::new)
                                .with(TagsVO::setId, tagsBO.getId())
                                .with(TagsVO::setName, StringEscapeUtils.escapeHtml4(tagsBO.getName()))
                                .build();
                        tagsList.add(tagsListVO);

                    }
                    vo.setArtTagsList(tagsList);


                }


                response.setData(vo);
            }

        } catch (Exception e) {
            log.error(">> AdminPageController.getLastTempArticleInfo exec failed, exception: \n", e);
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }

    /**
     * 临时文章状态更新为恢复<br/>
     * url:/api/admin/article/temp/update/restore<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @SubToken
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_TEMP_UPDATE_RESTORE)
    public String doTempStatusUpdateRestore(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TempArticleStatusRequest request = this.convertParam(param, TempArticleStatusRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "临时文章id不能为空!");
            BizValidation.paramValidate(request.getEntityOid(), "restoreOid", "恢复文章id不能为空!");
            tempArticleBizService.doUpdateTempArticleStatus(request.getEntityOid(), TempArticleStatusEnum.RESTORE,
                    request.getRestoreOid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

    /**
     * 临时文章状态更新为作废<br/>
     * url:/api/admin/article/temp/update/void<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @SubToken
    @ApiVersion()
    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_TEMP_UPDATE_VOID)
    public String doTempStatusUpdateVoid(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "临时文章id不能为空!");
            tempArticleBizService.doUpdateTempArticleStatus(request.getEntityOid(), TempArticleStatusEnum.VOID, null);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }
}