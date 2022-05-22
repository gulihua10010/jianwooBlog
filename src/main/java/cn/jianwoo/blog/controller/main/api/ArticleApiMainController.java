package cn.jianwoo.blog.controller.main.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.ArticleMainApiUrlConfig;
import cn.jianwoo.blog.dto.request.ArticlePageRequest;
import cn.jianwoo.blog.dto.request.ArticlePassVerifyRequest;
import cn.jianwoo.blog.dto.request.ArticlePraiseRequest;
import cn.jianwoo.blog.dto.request.ArticleRecmdRequest;
import cn.jianwoo.blog.dto.response.ArticleMainInfoResponse;
import cn.jianwoo.blog.dto.response.ArticleMainPageResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleMainPageVO;
import cn.jianwoo.blog.dto.response.vo.ArticleMainVO;
import cn.jianwoo.blog.dto.response.vo.TagsVO;
import cn.jianwoo.blog.exception.ControllerBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.bo.ArticleBO;
import cn.jianwoo.blog.service.param.ArticleParam;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @author gulihua
 * @Description
 * @date 2022-03-10 18:02
 */
@RestController
@RequestMapping(ArticleMainApiUrlConfig.URL_PREFIX)
@Slf4j
public class ArticleApiMainController extends BaseController {
    @Autowired
    private ArticleBizService articleBizService;

    /**
     * 分页查询有效的文章集合(STATUS = 90)首页<br/>
     * url:/api/article/query/page/list<br/>
     *
     * @param param JSON 参数({@link ArticlePageRequest})<br/>
     *              title<br/>
     *              text<br/>
     *              tags<br/>
     *              category<br/>
     * @return 返回响应 {@link ArticleMainPageResponse}<br/>
     * code<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --publishDate<br/>
     * --modifiedDate<br/>
     * --author<br/>
     * --category<br/>
     * --permission<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_QUERY_PAGE_LIST)
    @IpLimit(key = "queryEffectiveArticlePage")
    public String queryEffectiveArticlePage(@RequestBody String param) {

        try {
            super.printRequestParams(param);
            ArticlePageRequest req = this.convertParam(param, ArticlePageRequest.class);
            ArticleParam artParam = new ArticleParam();
            if (StringUtils.isNotBlank(req.getTitle())) {
                artParam.setTitle(req.getTitle().trim());
            }
            if (StringUtils.isNotBlank(req.getText())) {
                artParam.setText(req.getText().trim());
            }
            if (CollectionUtils.isNotEmpty(req.getTags())) {
                artParam.setTags(req.getTags());
            }
            artParam.setCategory(req.getCategory());
            artParam.setPageNo(req.getPage());
            artParam.setPageSize(req.getLimit());
            artParam.processSortField(req.getSortField(), req.getSortOrder());


            PageInfo<ArticleBO> pageInfo = articleBizService.queryMainArticleList(artParam, request.getRemoteAddr());
            List<ArticleMainPageVO> list = new ArrayList<>();
            ArticleMainPageResponse response = ArticleMainPageResponse.getInstance();
            for (ArticleBO articleBO : pageInfo.getList()) {
                ArticleMainPageVO vo = new ArticleMainPageVO();
                vo.setAuthor(articleBO.getAuthor());
                vo.setOid(articleBO.getOid());
                vo.setTitle(articleBO.getTitle());
                vo.setCategory(articleBO.getTypeName());
                vo.setPublishTimeStr(DateUtil.formatDateTime(articleBO.getPushTime()));
                vo.setPublishTime(articleBO.getPushTime());
                vo.setModifiedTimeStr(DateUtil.formatDateTime(articleBO.getModifiedTime()));
                vo.setModifiedTime(articleBO.getModifiedTime());
                vo.setImgSrc(articleBO.getImgSrc());
                vo.setCommentCount(articleBO.getCommentCount());
                vo.setPraiseCount(articleBO.getPraiseCount());
                vo.setReadCount(articleBO.getReadCount());
                vo.setPermission(articleBO.getAccessType());
                vo.setDesc(articleBO.getText());
                if (CollectionUtils.isNotEmpty(articleBO.getArtTagsList())) {
                    List<TagsVO> tagsVOS = new ArrayList<>();
                    articleBO.getArtTagsList().forEach(o -> {
                        TagsVO tagsVO = new TagsVO();
                        tagsVO.setId(o.getId());
                        tagsVO.setName(o.getName());
                        tagsVOS.add(tagsVO);
                    });
                    vo.setTags(tagsVOS);
                }
                list.add(vo);
            }
            response.setData(list);
            response.setCount(pageInfo.getTotal());
            return super.responseToJSONString(response);

        } catch (Exception e) {
            return super.exceptionToString(e);
        }


    }


    /**
     * 获取文章信息<br/>
     * url:/api/article/query/detail?id={id}<br/>
     *
     * @return 返回响应 {@link ArticleMainInfoResponse}<br/>
     * status<br/>
     * data<br/>
     * --id<br/>
     * --title<br/>
     * --author<br/>
     * --content<br/>
     * --menuOid<br/>
     * --imgSrc<br/>
     * --category<br/>
     * --permission<br/>
     * --tags<br/>
     * ----id<br/>
     * ----name<br/>
     * --commentCount<br/>
     * --readCount<br/>
     * --praiseCount<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ArticleMainApiUrlConfig.URL_QUERY_DETAIL)
    @IpLimit(key = "queryArticleDetail")
    public String queryArticleDetail(@PathVariable("id") String id) {
        ArticleMainInfoResponse response = ArticleMainInfoResponse.getInstance();
        try {
            BizValidation.paramValidate(id, "id", "文章id不能为空!");
            ArticleBO articleBO = articleBizService.queryArticleMainDetail(id, request.getRemoteAddr());
            if (articleBO != null) {
                ArticleMainVO articleVO = new ArticleMainVO();
                BeanUtils.copyProperties(articleBO, articleVO);
                articleVO.setId(articleBO.getOid());
                articleVO.setMenuOid(articleBO.getMenuId());
                articleVO.setCategory(articleBO.getTypeName());
                articleVO.setPermission(articleBO.getAccessType());
                if (CollectionUtils.isNotEmpty(articleBO.getArtTagsList())) {
                    List<TagsVO> tagsVOS = new ArrayList<>(articleBO.getArtTagsList().size());
                    articleBO.getArtTagsList().forEach(o -> {
                        TagsVO tagsVO = new TagsVO();
                        BeanUtils.copyProperties(o, tagsVO);
                        tagsVOS.add(tagsVO);
                    });
                    articleVO.setTags(tagsVOS);
                }


                response.setData(articleVO);

            }
        } catch (Exception e) {
            log.error(">> ArticleApiMainController.queryArticleDetail exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", id);
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }


    /**
     * 查询推荐文章<br/>
     * url:/api/article/query/recommend/list<br/>
     *
     * @param param JSON 参数({@link ArticleRecmdRequest})<br/>
     *              type<br/>
     * @return 返回响应 {@link ArticleMainPageResponse}<br/>
     * code<br/>
     * count<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --publishDate<br/>
     * --modifiedDate<br/>
     * --author<br/>
     * --category<br/>
     * --permission<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_QUERY_RECOMMEND_LIST)
    @IpLimit(key = "queryRecommendArticle")
    public String queryRecommendArticle(@RequestBody String param) {

        try {
            super.printRequestParams(param);
            ArticleRecmdRequest request = this.convertParam(param, ArticleRecmdRequest.class);
            List<ArticleBO> articleList = articleBizService.queryRecommendArticleList(request.getType());
            List<ArticleMainPageVO> list = new ArrayList<>();
            ArticleMainPageResponse response = ArticleMainPageResponse.getInstance();
            for (ArticleBO articleBO : articleList) {
                ArticleMainPageVO vo = new ArticleMainPageVO();
                vo.setAuthor(articleBO.getAuthor());
                vo.setOid(articleBO.getOid());
                vo.setTitle(articleBO.getTitle());
                list.add(vo);
            }
            response.setData(list);
            return super.responseToJSONString(response);

        } catch (Exception e) {
            return super.exceptionToString(e);
        }


    }


    /**
     * 文章赞<br/>
     * url:/api/article/praise/add<br/>
     *
     * @param param JSON 参数({@link ArticlePraiseRequest})<br/>
     *              artOid<br/>
     * @return 返回响应 {@link BaseResponseDto}<br/>
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_PRAISE_ADD)
    @IpLimit(key = "doAddPraise")
    public String doAddPraise(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ArticlePraiseRequest req = this.convertParam(param, ArticlePraiseRequest.class);
            BizValidation.paramValidate(req.getArtOid(), "artOid", "文章id不能为空!");

            articleBizService.doAddPraise(req.getArtOid(), request.getRemoteAddr());

        } catch (Exception e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 验证文章密码<br/>
     * url:/api/article/password/verify<br/>
     *
     * @param param JSON 参数({@link ArticlePassVerifyRequest})<br/>
     *              artOid<br/>
     *              password<br/>
     * @return 返回响应 {@link ArticleMainInfoResponse}<br/>
     * status<br/>
     * data<br/>
     * --id<br/>
     * --title<br/>
     * --author<br/>
     * --content<br/>
     * --menuOid<br/>
     * --imgSrc<br/>
     * --category<br/>
     * --permission<br/>
     * --tags<br/>
     * ----id<br/>
     * ----name<br/>
     * --commentCount<br/>
     * --readCount<br/>
     * --praiseCount<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ArticleMainApiUrlConfig.URL_PASSWORD_VERIFY)
    @IpLimit(key = "doVerifyPasswordArticle")
    public String doVerifyPasswordArticle(@RequestBody String param) {
        ArticleMainInfoResponse response = ArticleMainInfoResponse.getInstance();
        try {
            super.printRequestParams(param);
            ArticlePassVerifyRequest req = this.convertParam(param, ArticlePassVerifyRequest.class);
            BizValidation.paramValidate(req.getArtOid(), "artOid", "文章id不能为空!");
            BizValidation.paramValidate(req.getPassword(), "password", "文章密码不能为空!");
            ArticleBO articleBO = articleBizService.queryArticleMainDetail(req.getArtOid(), req.getPassword(), request.getRemoteAddr());
            if (articleBO != null) {
                ArticleMainVO articleVO = new ArticleMainVO();
                BeanUtils.copyProperties(articleBO, articleVO);
                articleVO.setId(articleBO.getOid());
                articleVO.setMenuOid(articleBO.getMenuId());
                articleVO.setCategory(articleBO.getTypeName());
                articleVO.setPermission(articleBO.getAccessType());
                if (CollectionUtils.isNotEmpty(articleBO.getArtTagsList())) {
                    List<TagsVO> tagsVOS = new ArrayList<>(articleBO.getArtTagsList().size());
                    articleBO.getArtTagsList().forEach(o -> {
                        TagsVO tagsVO = new TagsVO();
                        BeanUtils.copyProperties(o, tagsVO);
                        tagsVOS.add(tagsVO);
                    });
                    articleVO.setTags(tagsVOS);
                }


                response.setData(articleVO);

            }
        } catch (Exception e) {
            log.error(">> ArticleApiMainController.doVerifyPasswordArticle exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", param);
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }
}
