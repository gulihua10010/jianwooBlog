package cn.jianwoo.blog.controller.backend.api;

import cn.hutool.core.date.DateUtil;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.page.ConsoleApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dto.response.ArticleResponse;
import cn.jianwoo.blog.dto.response.CommentResponse;
import cn.jianwoo.blog.dto.response.LayuiBaseResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleVO;
import cn.jianwoo.blog.dto.response.vo.CommentVO;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.CommentBizService;
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
    private final static String templateName = "CONSOLE_TEMPLATE";

    @GetMapping(ConsoleApiUrlConfig.URL_RECENT_ARTICLE_PUBLISHED_QUERY)
    public String queryPublishedArticleList() {
        List<Article> articleList = articleBizService.queryRecentPublishedArts(10);
        ArticleResponse response = ArticleResponse.getInstance();
        if (CollectionUtils.isNotEmpty(articleList)) {
            List<ArticleVO> articleVOList = articleList.stream().map(data -> {
                ArticleVO vo = new ArticleVO();
                vo.setAuthor(data.getAuthor());
                vo.setPublishDate(DateUtil.format(data.getPushDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
                vo.setOid(data.getOid());
                vo.setTitle(data.getTitle());
                return vo;
            }).collect(Collectors.toList());
            response.setData(articleVOList);
        }
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
        return super.responseToJSONString(response);
    }

    @GetMapping(ConsoleApiUrlConfig.URL_RECENT_ARTICLE_DRAFT_QUERY)
    public String queryDraftArticleList() {
        List<Article> articleList = articleBizService.queryRecentDraft(10);
        ArticleResponse response = ArticleResponse.getInstance();
        if (CollectionUtils.isNotEmpty(articleList)) {
            List<ArticleVO> articleVOList = articleList.stream().map(data -> {
                ArticleVO vo = new ArticleVO();
                vo.setAuthor(data.getAuthor());
                vo.setPublishDate(DateUtil.format(data.getPushDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
                vo.setOid(data.getOid());
                vo.setTitle(data.getTitle());
                return vo;
            }).collect(Collectors.toList());
            response.setData(articleVOList);
        }
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
        return super.responseToJSONString(response);
    }

    @GetMapping(ConsoleApiUrlConfig.URL_RECENT_COMMENT_QUERY)
    public String queryCommentList() {
        List<CommentExt> commentExtList = commentBizService.queryRecentComments(10);
        CommentResponse response = CommentResponse.getInstance();
        if (CollectionUtils.isNotEmpty(commentExtList)) {
            List<CommentVO> list = new ArrayList<>();
            commentExtList.forEach(domain -> {
                CommentVO vo = new CommentVO();
                vo.setArtOid(domain.getArticleOid());
                vo.setDate(DateUtil.format(domain.getDate(), Constants.DATE_FORMAT_YYYYMMDDHHMMSS));
                vo.setArtTitle(domain.getTitle());
                vo.setUser(domain.getUser());
                vo.setIp(domain.getIp());
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
        response.setCode(LayuiBaseResponse.SUCCESS_CODE);
        return super.responseToJSONString(response);
    }
}