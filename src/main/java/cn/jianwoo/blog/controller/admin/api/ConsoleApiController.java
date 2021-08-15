package cn.jianwoo.blog.controller.admin.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.ConsoleApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.response.ArticleSummaryResponse;
import cn.jianwoo.blog.dto.response.CommentSummaryResponse;
import cn.jianwoo.blog.dto.response.ConsoleCountResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleSummaryVO;
import cn.jianwoo.blog.dto.response.vo.CommentSummaryVO;
import cn.jianwoo.blog.dto.response.vo.CommentVO;
import cn.jianwoo.blog.dto.response.vo.ConsoleCountVO;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.bo.ArticleBO;
import cn.jianwoo.blog.service.bo.CommentBO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GuLihua
 * @Description
 * @date 2020-12-29 13:44
 */
@RestController
@RequestMapping(ConsoleApiUrlConfig.URL_PREFIX)
public class ConsoleApiController extends BaseController {
    @Autowired
    private CommentBizService commentBizService;
    @Autowired
    private ArticleBizService articleBizService;
    @Autowired
    private TagsBizService tagsBizService;
    private final static String templateName = "CONSOLE_TEMPLATE";

    /**
     * 查詢已发布的最近10篇文章列表(控制台首页)<br/>
     * url:/api/admin/console/recent/article/published/query<br/>
     *
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
    @GetMapping(ConsoleApiUrlConfig.URL_RECENT_ARTICLE_PUBLISHED_QUERY)
    public String queryPublishedArticleList() {
        List<ArticleBO> articleList = articleBizService.queryRecentPublishedArts(10);
        ArticleSummaryResponse response = ArticleSummaryResponse.getInstance();
        if (CollectionUtils.isNotEmpty(articleList)) {
            List<ArticleSummaryVO> articleSummaryVOList = articleList.stream().map(data -> {
                ArticleSummaryVO vo = new ArticleSummaryVO();
                vo.setAuthor(data.getAuthor());
                vo.setPublishTimeStr(DateUtil.formatDateTime(data.getPushTime()));
                vo.setPublishTime(data.getPushTime());
                vo.setOid(data.getOid());
                vo.setTitle(data.getTitle());
                return vo;
            }).collect(Collectors.toList());
            response.setData(articleSummaryVOList);
        }
        return super.responseToJSONString(response);
    }

    /**
     * 查詢最近10篇文章草稿列表(控制台首页)<br/>
     * url:/api/admin/console/recent/article/draft/query<br/>
     *
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
    @GetMapping(ConsoleApiUrlConfig.URL_RECENT_ARTICLE_DRAFT_QUERY)
    public String queryDraftArticleList() {
        List<ArticleBO> articleList = articleBizService.queryRecentDraft(10);
        ArticleSummaryResponse response = ArticleSummaryResponse.getInstance();
        if (CollectionUtils.isNotEmpty(articleList)) {
            List<ArticleSummaryVO> articleSummaryVOList = articleList.stream().map(data -> {
                ArticleSummaryVO vo = new ArticleSummaryVO();
                vo.setAuthor(data.getAuthor());
                vo.setPublishTimeStr(DateUtil.formatDateTime(data.getPushTime()));
                vo.setPublishTime(data.getPushTime());
                vo.setOid(data.getOid());
                vo.setTitle(data.getTitle());
                return vo;
            }).collect(Collectors.toList());
            response.setData(articleSummaryVOList);
        }
        return super.responseToJSONString(response);
    }

    /**
     * 查詢最近10个文章评论列表(控制台首页)<br/>
     * url:/api/admin/console/recent/comment/query<br/>
     *
     * @return 返回响应 {@link CommentSummaryResponse}
     * code<br/>
     * count<br/>
     * data<br/>
     * --seq<br/>
     * --artTitle<br/>
     * --user<br/>
     * --date<br/>
     * --replyTo<br/>
     * --content<br/>
     * --replyOid<br/>
     * --oid<br/>
     * --artOid<br/>
     * --ip<br/>
     * --area<br/>
     * --desc<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ConsoleApiUrlConfig.URL_RECENT_COMMENT_QUERY)
    public String queryCommentList() {
        List<CommentBO> commentExtList = commentBizService.queryRecentComments(10);
        CommentSummaryResponse response = CommentSummaryResponse.getInstance();
        if (CollectionUtils.isNotEmpty(commentExtList)) {
            List<CommentSummaryVO> list = new ArrayList<>();
            commentExtList.forEach(domain -> {
                CommentSummaryVO vo = new CommentSummaryVO();
                vo.setArtOid(domain.getArticleOid());
                vo.setCommentTimeStr(DateUtil.formatDateTime(domain.getCommentTime()));
                vo.setCommentTime(domain.getCommentTime());
                vo.setArtTitle(domain.getTitle());
                vo.setUserName(domain.getUserName());
                vo.setClientIp(domain.getClientIp());
                vo.setTemplateName(templateName);
                String content = StringEscapeUtils.escapeHtml4(domain.getContent());
                if (content.length() > 50) {
                    content.substring(0, 50).concat(Constants.ELLIPSIS);
                }
                vo.setContent(content);
                list.add(vo);
            });
            response.setData(list);
        }
        return super.responseToJSONString(response);
    }


    /**
     * 查詢发布文章的数量<br/>
     * url:/api/admin/console/published/articles/count<br/>
     *
     * @return 返回响应 {@link ConsoleCountResponse}
     * status<br/>
     * data<br/>
     * --count<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ConsoleApiUrlConfig.URL_PUBLISHED_ARTICLES_COUNT)
    public String queryPublishedArtsCount() {
        ConsoleCountResponse response = ConsoleCountResponse.getInstance();
        int publishedArtsCount = articleBizService.countWithPublishArts();
        ConsoleCountVO vo = JwBuilder.of(ConsoleCountVO::new)
                .with(ConsoleCountVO::setCount, publishedArtsCount).build();
        response.setData(vo);
        return super.responseToJSONString(response);
    }


    /**
     * 查詢草稿文章的数量<br/>
     * url:/api/admin/console/draft/articles/count<br/>
     *
     * @return 返回响应 {@link ConsoleCountResponse}
     * status<br/>
     * data<br/>
     * --count<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ConsoleApiUrlConfig.URL_DRAFT_ARTICLES_COUNT)
    public String queryDraftArtsCount() {
        ConsoleCountResponse response = ConsoleCountResponse.getInstance();
        int draftArtsCount = articleBizService.countWithDraftArts();
        ConsoleCountVO vo = JwBuilder.of(ConsoleCountVO::new)
                .with(ConsoleCountVO::setCount, draftArtsCount).build();
        response.setData(vo);
        return super.responseToJSONString(response);
    }

    /**
     * 查詢评论的数量<br/>
     * url:/api/admin/console/comment/count<br/>
     *
     * @return 返回响应 {@link ConsoleCountResponse}
     * status<br/>
     * data<br/>
     * --count<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ConsoleApiUrlConfig.URL_COMMENT_COUNT)
    public String queryCommentCount() {
        ConsoleCountResponse response = ConsoleCountResponse.getInstance();
        int commentCount = commentBizService.countAllComments();
        ConsoleCountVO vo = JwBuilder.of(ConsoleCountVO::new)
                .with(ConsoleCountVO::setCount, commentCount).build();
        response.setData(vo);
        return super.responseToJSONString(response);
    }

    /**
     * 查詢文章表情的数量<br/>
     * url:/api/admin/console/tags/count<br/>
     *
     * @return 返回响应 {@link ConsoleCountResponse}
     * status<br/>
     * data<br/>
     * --count<br/>
     * @author gulihua
     */
    @ApiVersion()
    @GetMapping(ConsoleApiUrlConfig.URL_TAGS_COUNT)
    public String queryTagsCount() {
        ConsoleCountResponse response = ConsoleCountResponse.getInstance();
        int tagsCount = tagsBizService.countAllTags();
        ConsoleCountVO vo = JwBuilder.of(ConsoleCountVO::new)
                .with(ConsoleCountVO::setCount, tagsCount).build();
        response.setData(vo);
        return super.responseToJSONString(response);
    }
}
