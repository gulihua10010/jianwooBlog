package cn.jianwoo.blog.controller.backend.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.page.ArticleApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.ArticlePageRequest;
import cn.jianwoo.blog.dto.request.ArticleSubmitRequest;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.response.ArticleResponse;
import cn.jianwoo.blog.dto.response.LayuiBaseResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleVO;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleParam;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ArticleApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleApiController.class);
    @Autowired
    private ArticleBizService articleBizService;

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
            BizValidation.paramValidate(clearHtml(request.getArticleContent()), "articleContent", "文章内容不能为空!");
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
            articleBizService.doSaveArticle(request.getTitle(), request.getArticleContent(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags(), ArticleStatusEnum.PUBLISHED.getValue());
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
            articleBizService.doSaveArticle(request.getTitle(), request.getArticleContent(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags(), ArticleStatusEnum.DRAFT.getValue());
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
            articleBizService.doSaveArticle(request.getTitle(), request.getArticleContent(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags(), ArticleStatusEnum.RECYCLE.getValue());
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
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            articleBizService.doUpdateArticle(request.getArtOid(), request.getTitle(), request.getArticleContent(),
                    request.getAuthor(), request.getType(), request.getIsComment(), request.getVisitType(),
                    request.getImgSrc(), request.getPassword(), request.getTags(),
                    ArticleStatusEnum.PUBLISHED.getValue());
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
            BizValidation.paramValidate(request.getType(), "type", "文章类型不能为空!");
            BizValidation.paramLengthValidate(request.getTitle(), Constants.TITLE_LENGTH, "title", "文章标题不能大于50个字符!");
            BizValidation.paramLengthValidate(request.getAuthor(), Constants.AUTHOR_LENGTH, "author", "文章作者不能大于10个字符!");
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            if (ArticleVisitEnum.PASSWORD.getValue().equals(request.getVisitType())) {
                BizValidation.paramValidate(request.getPassword(), "password", "文章密码不能为空!");
            }
            articleBizService.doUpdateArticleInfo(request.getArtOid(), request.getTitle(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags());
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
            articleBizService.doUpdateArticle(request.getArtOid(), request.getTitle(), request.getArticleContent(),
                    request.getAuthor(), request.getType(), request.getIsComment(), request.getVisitType(),
                    request.getImgSrc(), request.getPassword(), request.getTags(), ArticleStatusEnum.DRAFT.getValue());
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
            articleBizService.doUpdateArticle(request.getArtOid(), request.getTitle(), request.getArticleContent(),
                    request.getAuthor(), request.getType(), request.getIsComment(), request.getVisitType(),
                    request.getImgSrc(), request.getPassword(), request.getTags(), ArticleStatusEnum.RECYCLE.getValue());
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
            BizValidation.paramValidate(clearHtml(request.getArticleContent()), "articleContent", "文章内容不能为空!");
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
            articleBizService.doUpdateArticle(request.getArtOid(), request.getTitle(), request.getArticleContent(),
                    request.getAuthor(), request.getType(), request.getIsComment(), request.getVisitType(),
                    request.getImgSrc(), request.getPassword(), request.getTags(),
                    ArticleStatusEnum.PUBLISHED.getValue());
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
     * @return 返回响应 {@link ArticleResponse}
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
        ArticleResponse response = ArticleResponse.getInstance();
        for (ArticleExt articleExt : articleExtPageInfo.getList()) {
            ArticleVO vo = new ArticleVO();
            vo.setAuthor(articleExt.getAuthor());
            vo.setOid(articleExt.getOid());
            vo.setTitle(articleExt.getTitle());
            vo.setType(articleExt.getTypeName());
            vo.setStatus(articleExt.getStatus());
            vo.setPublishDate(DateUtil.format(articleExt.getPushDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
            vo.setModifiedDate(DateUtil.format(articleExt.getModifiedDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
            articleVOList.add(vo);
        }
        response.setData(articleVOList);
        response.setCount(articleExtPageInfo.getTotal());
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
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
     * @return 返回响应 {@link ArticleResponse}
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
        ArticleResponse response = ArticleResponse.getInstance();
        for (ArticleExt articleExt : articleExtPageInfo.getList()) {
            ArticleVO vo = new ArticleVO();
            vo.setAuthor(articleExt.getAuthor());
            vo.setOid(articleExt.getOid());
            vo.setTitle(articleExt.getTitle());
            vo.setType(articleExt.getTypeName());
            vo.setStatus(articleExt.getStatus());
            vo.setPublishDate(DateUtil.format(articleExt.getPushDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
            vo.setModifiedDate(DateUtil.format(articleExt.getModifiedDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
            articleVOList.add(vo);
        }
        response.setData(articleVOList);
        response.setCount(articleExtPageInfo.getTotal());
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
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
     * 把html标签文本的标签移除<br/>
     *
     * @param html html标签文本
     * @return 纯html文本
     * @author gulihua
     */
    private String clearHtml(String html) {
        return html.replaceAll(Constants.CLEAR_HTML_TAGS, Constants.BLANK).
                replaceAll("\n", "").trim();
    }


}
