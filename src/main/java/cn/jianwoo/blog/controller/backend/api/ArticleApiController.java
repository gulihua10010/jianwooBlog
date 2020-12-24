package cn.jianwoo.blog.controller.backend.api;

import cn.hutool.core.date.DateUtil;
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
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            articleBizService.doSaveArticle(request.getTitle(), request.getArticleContent(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags(), ArticleStatusEnum.PUBLISHED.getValue());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_DRAFT)
    public String saveToDraft(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            articleBizService.doSaveArticle(request.getTitle(), request.getArticleContent(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags(), ArticleStatusEnum.DRAFT.getValue());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_SAVE_RECYCLE)
    public String saveToRecycle(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            articleBizService.doSaveArticle(request.getTitle(), request.getArticleContent(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags(), ArticleStatusEnum.RECYCLE.getValue());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_UPDATE)
    public String artUpdate(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            articleBizService.doUpdateArticle(request.getArtOid(), request.getTitle(), request.getArticleContent(),
                    request.getAuthor(), request.getType(), request.getIsComment(), request.getVisitType(),
                    request.getImgSrc(), request.getPassword(), request.getTags(),
                    ArticleStatusEnum.PUBLISHED.getValue());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


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
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            articleBizService.doUpdateArticleInfo(request.getArtOid(), request.getTitle(), request.getAuthor(),
                    request.getType(), request.getIsComment(), request.getVisitType(), request.getImgSrc(),
                    request.getPassword(), request.getTags());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DRAFT_SAVE)
    public String artDraftSave(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticleSubmitRequest request = this.convertParam(param, ArticleSubmitRequest.class);
            BizValidation.paramValidate(request.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(request.getTitle(), "title", "标题不能为空!");
            BizValidation.paramValidate(request.getArticleContent(), "articleContent", "文章内容不能为空!");
            BizValidation.paramValidate(request.getAuthor(), "author", "作者不能为空!");
            articleBizService.doUpdateArticle(request.getArtOid(), request.getTitle(), request.getArticleContent(),
                    request.getAuthor(), request.getType(), request.getIsComment(), request.getVisitType(),
                    request.getImgSrc(), request.getPassword(), request.getTags(), ArticleStatusEnum.DRAFT.getValue());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


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
            BizValidation.paramRangeValidate(request.getVisitType(), "visitType", "文章访问类型必须在[-1,0,1,2]中!",
                    ArticleVisitEnum.PUBLIC.getValue(), ArticleVisitEnum.PASSWORD.getValue(),
                    ArticleVisitEnum.PRIVATE.getValue(), ArticleVisitEnum.TOP.getValue());
            articleBizService.doUpdateArticle(request.getArtOid(), request.getTitle(), request.getArticleContent(),
                    request.getAuthor(), request.getType(), request.getIsComment(), request.getVisitType(),
                    request.getImgSrc(), request.getPassword(), request.getTags(),
                    ArticleStatusEnum.PUBLISHED.getValue());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


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


    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_RECYCLE_RESTORE_DRAFT)
    public String restoreArticleListToDraft(@RequestBody String param) {
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


    @PostMapping(ArticleApiUrlConfig.URL_ARTICLE_DELETE_RECYCLE)
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


    private String clearHtml(String html) {
        return html.replaceAll("\\<.*?>", "").replaceAll("\n", "").trim();
    }
}
