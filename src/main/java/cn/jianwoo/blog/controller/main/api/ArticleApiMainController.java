package cn.jianwoo.blog.controller.main.api;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReUtil;
import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.main.ArticleMainApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.request.ArticlePageRequest;
import cn.jianwoo.blog.dto.request.ArticlePassVerifyRequest;
import cn.jianwoo.blog.dto.request.ArticlePraiseRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.MonthPublishRequest;
import cn.jianwoo.blog.dto.response.ArticleDetailRecommendResponse;
import cn.jianwoo.blog.dto.response.ArticleMainInfoResponse;
import cn.jianwoo.blog.dto.response.ArticleMainPageResponse;
import cn.jianwoo.blog.dto.response.ArticleMainRecommendResponse;
import cn.jianwoo.blog.dto.response.MonthPublishResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleMainPageVO;
import cn.jianwoo.blog.dto.response.vo.ArticleMainVO;
import cn.jianwoo.blog.dto.response.vo.ConditionVO;
import cn.jianwoo.blog.dto.response.vo.MonthPublishVO;
import cn.jianwoo.blog.dto.response.vo.TagsVO;
import cn.jianwoo.blog.enums.ArtRecmdTypeEnum;
import cn.jianwoo.blog.enums.TopPlaceEnum;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.bo.ArticleBO;
import cn.jianwoo.blog.service.bo.MenuBO;
import cn.jianwoo.blog.service.bo.MonthPublishBO;
import cn.jianwoo.blog.service.param.ArticleParam;
import cn.jianwoo.blog.validation.BizValidation;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MenuBizService menuBizService;
    @Autowired
    private TagsBizService tagsBizService;

    public static final String SEARCH_RESULT = "搜索结果: ";
    public static final String SEARCH_TYPE_1 = "CATEGORY1";
    public static final String SEARCH_TYPE_2 = "CATEGORY2";
    public static final String SEARCH_TYPE_3 = "KEYWORDS";
    public static final String SEARCH_TYPE_4 = "TAGS";


    /**
     * 分页查询有效的文章集合(STATUS = 90)首页<br/>
     * url:/api/article/query/page/list<br/>
     *
     * @param param JSON 参数({@link ArticlePageRequest})<br/>
     *              title<br/>
     *              text<br/>
     *              keywords<br/>
     *              tags<br/>
     *              tag<br/>
     *              publishDate(yyyy-MM-dd)<br/>
     *              category1<br/>
     *              category2<br/>
     * @return 返回响应 {@link ArticleMainPageResponse}<br/>
     * code<br/>
     * count<br/>
     * condition1<br/>
     * --condition<br/>
     * --conditionId<br/>
     * --conditionType<br/>
     * condition2<br/>
     * condition3<br/>
     * conditions<br/>
     * data<br/>
     * --oid<br/>
     * --title<br/>
     * --publishDate<br/>
     * --modifiedDate<br/>
     * --author<br/>
     * --category<br/>
     * --permission<br/>
     * --imgSrc<br/>
     * --desc<br/>
     * --commentCount<br/>
     * --readCount<br/>
     * --praiseCount<br/>
     * --isPraise<br/>
     * --tags<br/>
     * --topPlaceFlag<br/>
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
            if (StringUtils.isNotBlank(req.getKeywords())) {
                artParam.setKeywords(req.getKeywords().trim());
            }
            if (CollectionUtils.isNotEmpty(req.getTags())) {
                artParam.setTags(req.getTags());
            }

            if (StringUtils.isNotBlank(req.getPublishDate())) {
                if (validateDate(req.getPublishDate())) {
                    artParam.setPublishDate(req.getPublishDate());
                }
            }


            if (req.getTag() != null) {
                if (artParam.getTags() == null) {
                    artParam.setTags(new ArrayList<>());
                }
                artParam.getTags().add(req.getTag());
            }

            artParam.setCategory1(req.getCategory1());
            artParam.setCategory2(req.getCategory2());
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
                vo.setCategory(articleBO.getCategoryName());
                vo.setPublishTimeStr(DateUtil.formatDateTime(articleBO.getPushTime()));
                vo.setPublishTime(articleBO.getPushTime());
                vo.setModifiedTimeStr(DateUtil.formatDateTime(articleBO.getModifiedTime()));
                vo.setModifiedTime(articleBO.getModifiedTime());
                vo.setImgSrc(articleBO.getImgSrc());
                vo.setCommentCount(articleBO.getCommentCount());
                vo.setPraiseCount(articleBO.getPraiseCount());
                vo.setReadCount(articleBO.getReadCount());
                vo.setPermission(articleBO.getAccessType());
                vo.setIsPraise(articleBO.getIsPraise());
                vo.setDesc(articleBO.getText());
                vo.setTopPlaceFlag(false);
                if (TopPlaceEnum.TOP.getValue().equals(articleBO.getTopPlaceStatus())) {
                    vo.setTopPlaceFlag(true);
                }
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
            if (req.getCategory1() != null || req.getCategory2() != null) {
                Integer category = req.getCategory2() == null ? req.getCategory1() : req.getCategory2();
                MenuBO menuBO = menuBizService.queryCascadeMenuByOid(category.toString());
                response.setCondition1(ConditionVO.builder().condition(menuBO.getText())
                        .conditionId(menuBO.getOid().intValue()).conditionType(SEARCH_TYPE_1).build());
                if (CollectionUtils.isNotEmpty(menuBO.getSubMenuList())) {
                    MenuBO subMenuBO = menuBO.getSubMenuList().get(0);
                    response.setCondition2(ConditionVO.builder().condition(subMenuBO.getText())
                            .conditionId(subMenuBO.getOid().intValue()).conditionType(SEARCH_TYPE_2).build());
                }
            } else if (CollectionUtils.isNotEmpty(req.getTags())) {
                for (Integer tagOid : req.getTags()) {
                    String tagName = tagsBizService.queryTagNameByOid(tagOid.longValue());
                    response.setConditions(new ArrayList<>());
                    response.getConditions().add(ConditionVO.builder().condition(tagName)
                            .conditionId(tagOid).conditionType(SEARCH_TYPE_4).build());
                }

            } else if (StringUtils.isNotEmpty(req.getKeywords())) {
                response.setCondition1(ConditionVO.builder().condition(SEARCH_RESULT + req.getKeywords())
                        .conditionType(SEARCH_TYPE_3).build());
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
     * url:/api/article/query/detail<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOid<br/>
     * @return 返回响应 {@link ArticleMainInfoResponse}<br/>
     * status<br/>
     * data<br/>
     * --id<br/>
     * --title<br/>
     * --author<br/>
     * --publishDate<br/>
     * --modifiedDate<br/>
     * --content<br/>
     * --imgSrc<br/>
     * --categoryOid<br/>
     * --category<br/>
     * --parentCategoryOid<br/>
     * --parentCategory<br/>
     * --permission<br/>
     * --tags<br/>
     * ----id<br/>
     * ----name<br/>
     * --commentCount<br/>
     * --readCount<br/>
     * --praiseCount<br/>
     * --isPraise<br/>
     * --isComment<br/>
     * --flagOriginal<br/>
     * --originalUrl<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_QUERY_DETAIL)
    @IpLimit(key = "queryArticleDetail")
    public String queryArticleDetail(@RequestBody String param) {
        ArticleMainInfoResponse response = ArticleMainInfoResponse.getInstance();
        try {
            super.printRequestParams(param);
            EntityOidRequest req = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(req.getEntityOid(), "entityOid", "文章id不能为空!");
            ArticleBO articleBO = articleBizService.queryArticleMainDetail(req.getEntityOid(), request.getRemoteAddr());
            if (articleBO != null) {
                ArticleMainVO articleVO = new ArticleMainVO();
                BeanUtils.copyProperties(articleBO, articleVO);
                articleVO.setPublishTime(articleBO.getPushTime());
                articleVO.setId(articleBO.getOid());
                articleVO.setCategoryOid(articleBO.getCategoryId());
                articleVO.setCategory(articleBO.getCategoryName());
                articleVO.setParentCategoryOid(articleBO.getParentCategoryId());
                articleVO.setPermission(articleBO.getAccessType());
                if (CollectionUtils.isNotEmpty(articleBO.getArtTagsList())) {
                    List<TagsVO> tagsVOS = new ArrayList<>(articleBO.getArtTagsList().size());
                    articleBO.getArtTagsList().forEach(o -> {
                        TagsVO tagsVO = new TagsVO();
                        tagsVO.setId(o.getId());
                        tagsVO.setName(o.getName());
                        tagsVOS.add(tagsVO);
                    });
                    articleVO.setTags(tagsVOS);
                }


                response.setData(articleVO);

            }
        } catch (Exception e) {
            log.error(">> ArticleApiMainController.queryArticleDetail exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", param);
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(response);

    }


    /**
     * 查询推荐文章<br/>
     * url:/api/article/query/recommend/list<br/>
     *
     * @return 返回响应 {@link ArticleMainRecommendResponse}<br/>
     * code<br/>
     * count<br/>
     * newestList<br/>
     * --oid<br/>
     * --title<br/>
     * --author<br/>
     * randomVoList<br/>
     * hotVoList<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_QUERY_RECOMMEND_LIST)
    @IpLimit(key = "queryRecommendArticle")
    public String queryRecommendArticle(@RequestBody String param) {

        try {
            super.printRequestParams(param);
            List<ArticleBO> newestList = articleBizService.queryRecommendArticleList(ArtRecmdTypeEnum.NEWEST.getValue());
            List<ArticleBO> randomList = articleBizService.queryRecommendArticleList(ArtRecmdTypeEnum.RANDOM.getValue());
            List<ArticleBO> hotList = articleBizService.queryRecommendArticleList(ArtRecmdTypeEnum.HOT.getValue());
            List<ArticleMainPageVO> newestVoList = new ArrayList<>();
            List<ArticleMainPageVO> randomVoList = new ArrayList<>();
            List<ArticleMainPageVO> hotVoList = new ArrayList<>();
            ArticleMainRecommendResponse response = ArticleMainRecommendResponse.getInstance();
            for (ArticleBO articleBO : newestList) {
                ArticleMainPageVO vo = new ArticleMainPageVO();
                vo.setAuthor(articleBO.getAuthor());
                vo.setOid(articleBO.getOid());
                vo.setTitle(articleBO.getTitle());
                newestVoList.add(vo);
            }
            for (ArticleBO articleBO : randomList) {
                ArticleMainPageVO vo = new ArticleMainPageVO();
                vo.setAuthor(articleBO.getAuthor());
                vo.setOid(articleBO.getOid());
                vo.setTitle(articleBO.getTitle());
                randomVoList.add(vo);
            }
            for (ArticleBO articleBO : hotList) {
                ArticleMainPageVO vo = new ArticleMainPageVO();
                vo.setAuthor(articleBO.getAuthor());
                vo.setOid(articleBO.getOid());
                vo.setTitle(articleBO.getTitle());
                hotVoList.add(vo);
            }
            response.setHotList(hotVoList);
            response.setRandomList(randomVoList);
            response.setNewestList(newestVoList);
            return super.responseToJSONString(response);

        } catch (Exception e) {
            return super.exceptionToString(e);
        }


    }


    /**
     * 查询推荐文章<br/>
     * url:/api/query/detail/recommend/list<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})<br/>
     *              entityOid<br/>
     * @return 返回响应 {@link ArticleDetailRecommendResponse}<br/>
     * code<br/>
     * count<br/>
     * recommendList<br/>
     * --oid<br/>
     * --title<br/>
     * --author<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_QUERY_DETAIL_RECOMMEND_LIST)
    @IpLimit(key = "queryDetailRecommendArticle")
    public String queryDetailRecommendArticle(@RequestBody String param) {

        try {
            super.printRequestParams(param);

            EntityOidRequest req = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(req.getEntityOid(), "entityOid", "文章id不能为空!");
            List<ArticleBO> recommendList = articleBizService.queryDetailRecommendArticle(req.getEntityOid());

            List<ArticleMainPageVO> list = new ArrayList<>();

            ArticleDetailRecommendResponse response = ArticleDetailRecommendResponse.getInstance();
            for (ArticleBO articleBO : recommendList) {
                ArticleMainPageVO vo = new ArticleMainPageVO();
                vo.setAuthor(articleBO.getAuthor());
                vo.setOid(articleBO.getOid());
                vo.setTitle(articleBO.getTitle());
                list.add(vo);
            }
            response.setRecommendList(list);
            return super.responseToJSONString(response);

        } catch (Exception e) {
            return super.exceptionToString(e);
        }


    }


    /**
     * 查询指定月份的发布文章的日期<br/>
     * url:/api/article/month/date/publish/query<br/>
     *
     * @param param JSON 参数({@link MonthPublishRequest})<br/>
     *              month<br/>
     * @return 返回响应 {@link MonthPublishResponse}<br/>
     * code<br/>
     * data<br/>
     * --date<br/>
     * --day<br/>
     * --count<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_MONTH_DATE_PUBLISH_QUERY)
    @IpLimit(key = "queryMonthDatePublish")
    public String queryMonthDatePublish(@RequestBody String param) {

        try {
            super.printRequestParams(param);
            MonthPublishRequest req = this.convertParam(param, MonthPublishRequest.class);
            BizValidation.paramValidate(req.getMonth(), "month", "月份不能为空!");
            BizValidation.paramMinLengthValidate(req.getMonth(), 7, "month", "月份格式(yyyy-MM)不正确!");
            MonthPublishResponse response = MonthPublishResponse.getInstance();
            List<MonthPublishBO> data = articleBizService.queryMonthDatePublishList(req.getMonth(), request.getRemoteAddr());
            if (CollectionUtils.isNotEmpty(data)) {
                List<MonthPublishVO> voList = new ArrayList<>(data.size());
                data.forEach(o -> {
                    MonthPublishVO vo = new MonthPublishVO();
                    vo.setDate(o.getDate());
                    vo.setDay(o.getDay());
                    vo.setCount(o.getCount());
                    voList.add(vo);
                });
                response.setData(voList);
            }
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
     * --publishDate<br/>
     * --modifiedDate<br/>
     * --content<br/>
     * --categoryOid<br/>
     * --category<br/>
     * --parentCategoryOid<br/>
     * --parentCategory<br/>
     * --imgSrc<br/>
     * --permission<br/>
     * --tags<br/>
     * ----id<br/>
     * ----name<br/>
     * --commentCount<br/>
     * --readCount<br/>
     * --praiseCount<br/>
     * --isPraise<br/>
     * --isComment<br/>
     * --flagOriginal<br/>
     * --originalUrl<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(ArticleMainApiUrlConfig.URL_PASSWORD_VERIFY)
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
                articleVO.setCategoryOid(articleBO.getCategoryId());
                articleVO.setCategory(articleBO.getCategoryName());
                articleVO.setPermission(articleBO.getAccessType());
                articleVO.setParentCategoryOid(articleBO.getParentCategoryId());
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

    private boolean validateDate(String date) {
        return ReUtil.isMatch(Constants.DATE_REGEX, StringUtils.trim(date));
    }
}
