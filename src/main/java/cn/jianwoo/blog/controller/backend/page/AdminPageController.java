package cn.jianwoo.blog.controller.backend.page;

import cn.jianwoo.blog.config.page.CommBackendPageTemplateConfig;
import cn.jianwoo.blog.config.page.CommBackendPageUrlConfig;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.MenuTransDao;
import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.extension.MenuExt;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.biz.VisitBizService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.WebconfBO;
import cn.jianwoo.blog.util.DomainUtil;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-09 14:57
 */
@Controller()
@RequestMapping(CommBackendPageUrlConfig.URL_PREFIX)
public class AdminPageController {
    private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);

    @Autowired
    private TagsTransDao tagsTransDao;
    @Autowired
    private MenuBizService menuBizService;
    @Autowired
    private ArticleBizService articleBizService;
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private TagsBizService tagsBizService;
    @Autowired
    private CommentBizService commentBizService;
    @Autowired
    private MenuTransDao menuTransDao;
    @Autowired
    private WebconfBizService webconfBizService;
    @Autowired
    private VisitBizService visitBizService;


    @RequestMapping(CommBackendPageUrlConfig.URL_INDEX)
    public String index(Model model) {
        int publishedArtsCount = articleBizService.countWithPublishArts();
        int draftArtsCount = articleBizService.countWithDraftArts();
        int commentCount = commentBizService.countAllComments();

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_INDEX;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_PUBLISHED)
    public String articlePublished(Model model) {
        List<Tags> tags = tagsTransDao.queryAllTags();
        List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());
        model.addAttribute("tags", tags);
        model.addAttribute("menuList", menuList);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_PUBLISHED;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_EDIT)
    public String articleEdit(Model model, @PathVariable("id") Long id) {
        List<Tags> tags = tagsTransDao.queryAllTags();
        List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());
        List<Tags> artTags = tagsBizService.queryTagsByArtOid(id);
        Article article;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(id);
        } catch (DaoException e) {
            logger.error(">> AdminPageController.articleEdit exec failed, exception: \n", e);
            logger.error(">> Article {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;
        }
        Long lastCommOid = commentBizService.queryLastCommentOid();
        //        List<CommentExt> commentExtList = commentBizService.queryCommentsByArtOid(id);
        model.addAttribute("tags", tags);
        model.addAttribute("menuList", menuList);
        model.addAttribute("article", article);
        model.addAttribute("artTags", artTags);
        model.addAttribute("artOid", id);
        //        model.addAttribute("commentList", commentExtList);
        model.addAttribute("lastCommOid", lastCommOid);
        //        logger.info(">> article :oid({}), title({}), comment:{}", article.getOid(), article.getTitle(), commentExtList);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_EDIT;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_TAGS)
    public String articleTags(Model model) {
        List<Tags> tags = tagsTransDao.queryAllTags();
        model.addAttribute("tags", tags);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_TAGS;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_QUICK_EDIT)
    public String articleQuickEdit(Model model, @PathVariable("id") Long id) {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(id);
        } catch (DaoException e) {

            logger.error(">> AdminPageController.articleQuickEdit exec failed, exception: \n", e);
            logger.error(">> Article {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;
        }
        List<Tags> tags = tagsTransDao.queryAllTags();
        List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());
        List<Tags> artTags = tagsBizService.queryTagsByArtOid(id);
        model.addAttribute("article", article);
        model.addAttribute("tags", tags);
        model.addAttribute("artTags", artTags.stream().map(Tags::getOid).collect(Collectors.toList()));
        model.addAttribute("menuList", menuList);

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_QUICK_EDIT;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_RECYCLE_VIEW)
    public String articleRecycleView(Model model, @PathVariable("id") Long id) {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(id);
        } catch (DaoException e) {
            logger.error(">> AdminPageController.articleQuickEdit exec failed, exception: \n", e);
            logger.error(">> Article {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;
        }
        model.addAttribute("article", article);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_RECYCLE_VIEW;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_CLEAR_CACHE)
    public String clearCache() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_CLEAR_CACHE;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_COMMENT_MANAGEMENT)
    public String commentManagement() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_COMMENT_MANAGEMENT;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_COMMENT_REPLY)
    public String commentReply() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_COMMENT_REPLY;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_COMMENT_VIEW)
    public String commentView(Model model, @PathVariable("id") Long id) {
        CommentExt commentExt = commentBizService.queryCommentExtByOid(id);
        try {
            commentBizService.doUpdateReadByOid(id);
        } catch (JwBlogException e) {
            logger.error(">> AdminPageController.commentView exec failed, exception: \n", e);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ERROR;
        }
        model.addAttribute("comm", commentExt);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_COMMENT_VIEW;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_CONSOLE)
    public String console(Model model) {

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_CONSOLE;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_DYNAMIC)
    public String dynamic() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_DYNAMIC;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_MENU_MANAGEMENT)
    public String menuMg(Model model) {
        List<MenuExt> menuExtList = null;
        try {
            menuExtList = menuBizService.queryFrontDeskMenuList();
        } catch (JwBlogException e) {

            logger.error(">> AdminPageController.menuMg exec failed, exception: \n", e);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ERROR;
        }
        model.addAttribute("menu", menuExtList);

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MENU_MANAGEMENT;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_MENU_ADD)
    public String menuAdd() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MENU_ADD;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_MENU_EDIT)
    public String menuEdit(Model model, @PathVariable("id") Long id) {
        try {
            Menu menu = menuTransDao.queryMenuByPrimaryKey(id);
            model.addAttribute("menu", menu);
        } catch (DaoException e) {
            logger.error(">> AdminPageController.menuEdit exec failed, exception: \n", e);
            logger.error(">> Menu {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;

        }
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MENU_EDIT;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_MY_ARTICLE)
    public String myArticle() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MY_ARTICLE;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_RECYCLE_BIN)
    public String recycleBin() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_RECYCLE_BIN;
    }


    @RequestMapping(CommBackendPageUrlConfig.URL_WEB_CONFIG)
    public String webConfig(Model model) {
        WebconfBO webConf = webconfBizService.queryConfigWithBO();
        logger.info("==>> query webconf data: {}", DomainUtil.toString(webConf));
        model.addAttribute("webConf", webConf);

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_WEB_CONFIG;
    }

}
