package cn.jianwoo.blog.controller.backend.page;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.router.CommBackendPageTemplateConfig;
import cn.jianwoo.blog.config.router.CommBackendPageUrlConfig;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.MenuTransDao;
import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.extension.MenuExt;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-09 14:57
 */
@Controller()
@RequestMapping(CommBackendPageUrlConfig.URL_PREFIX)
@Slf4j
public class AdminPageController {

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
    @Autowired
    private CacheStore cacheStore;

    /**
     * 首頁<br/>
     * url:/admin<br/>
     *
     * @return page /backend/index
     * @author gulihua
     */
    @PageId(PageIdEnum.ADMIN_INDEX)
    @RequestMapping
    public String main() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_INDEX;
    }

    /**
     * 首頁<br/>
     * url:/admin<br/>
     *
     * @return page /backend/index
     * @author gulihua
     */
    @PageId(PageIdEnum.ADMIN_INDEX)
    @RequestMapping(CommBackendPageUrlConfig.URL_INDEX)
    public String index() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_INDEX;
    }

    /**
     * 文章发布页面<br/>
     * url:/admin/article/published<br/>
     *
     * @return page /backend/pages/articlePublished
     * @author gulihua
     */
    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_PUBLISHED)
    @PageId(PageIdEnum.ARTICLE_PUBLISHED)
    public String articlePublished(Model model) {
        List<Tags> tags = tagsTransDao.queryAllTags();
        List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());
        model.addAttribute("tags", tags);
        model.addAttribute("menuList", menuList);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_PUBLISHED;
    }

    /**
     * 文章编辑页面<br/>
     * url:/admin/article/edit/{id}<br/>
     *
     * @return page /backend/pages/articleEdit<br/>
     * 回收站文章：/backend/pages/articleRecycleView
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_EDIT)
    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_EDIT)
    public String articleEdit(Model model, @PathVariable("id") Long id) {
        Article article;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(id);
        } catch (DaoException e) {
            log.error(">> AdminPageController.articleEdit exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;
        }
        List<Tags> artTags = tagsBizService.queryTagsByArtOid(id);
        model.addAttribute("artTags", artTags);
        model.addAttribute("article", article);
        if (ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            String menuName = null;
            try {
                menuName = menuBizService.queryMenuNameById(article.getTypeId().longValue());
            } catch (JwBlogException e) {
                log.error("AdminPageController.articleRecycleView exec failed, exception: \n", e);
            }
            model.addAttribute("menuName", menuName);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_RECYCLE_VIEW;

        }
        List<Tags> tags = tagsTransDao.queryAllTags();
        List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());

        Long lastCommOid = commentBizService.queryLastCommentOid();
        //        List<CommentExt> commentExtList = commentBizService.queryCommentsByArtOid(id);
        model.addAttribute("tags", tags);
        model.addAttribute("menuList", menuList);
        model.addAttribute("artOid", id);
        //        model.addAttribute("commentList", commentExtList);
        model.addAttribute("lastCommOid", lastCommOid);
        //        logger.info(">> article :oid({}), title({}), comment:{}", article.getOid(), article.getTitle(), commentExtList);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_EDIT;
    }

    /**
     * 文章标签页面<br/>
     * url:/admin/article/tags<br/>
     *
     * @return page /backend/pages/articleTags
     * @author gulihua
     */
    @PageId(PageIdEnum.TAGS_LIST)
    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_TAGS)
    public String articleTags(Model model) {
        List<Tags> tags = tagsTransDao.queryAllTags();
        model.addAttribute("tags", tags);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_TAGS;
    }

    /**
     * 文章快速编辑页面<br/>
     * url:/admin/article/quick/edit/{id}<br/>
     *
     * @return page /backend/pages/articleQuickEdit
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_QUICK_EDIT)
    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_QUICK_EDIT)
    public String articleQuickEdit(Model model, @PathVariable("id") Long id) {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(id);
        } catch (DaoException e) {

            log.error(">> AdminPageController.articleQuickEdit exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", id);
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

    /**
     * 文章回收站内容预览页面<br/>
     * url:/admin/article/recycle/content/view/{id}<br/>
     *
     * @return page /backend/pages/articleRecycleContentView
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_RECYCLE_BIN_VIEW)
    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_RECYCLE_CONTENT_VIEW)
    public String articleRecycleContentView(Model model, @PathVariable("id") Long id) {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(id);
        } catch (DaoException e) {
            log.error(">> AdminPageController.articleQuickEdit exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;
        }
        model.addAttribute("article", article);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_RECYCLE_CONTENT_VIEW;
    }

    /**
     * 文章回收站预览页面<br/>
     * url:/admin/article/recycle/view/{id}<br/>
     *
     * @return page /backend/pages/articleRecycleView
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_RECYCLE_BIN_VIEW)
    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_RECYCLE_VIEW)
    public String articleRecycleView(Model model, @PathVariable("id") Long id) {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(id);
        } catch (DaoException e) {
            log.error(">> AdminPageController.articleRecycleView exec failed, exception: \n", e);
            log.error(">> Article {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;
        }

        List<Tags> artTags = tagsBizService.queryTagsByArtOid(id);
        String menuName = null;
        try {
            menuName = menuBizService.queryMenuNameById(article.getTypeId().longValue());
        } catch (JwBlogException e) {
            log.error("AdminPageController.articleRecycleView exec failed, exception: \n", e);
        }

        model.addAttribute("article", article);
        model.addAttribute("menuName", menuName);
        model.addAttribute("artTags", artTags);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ARTICLE_RECYCLE_VIEW;
    }

    /**
     * 清除缓存页面<br/>
     * url:/admin/clear/cache<br/>
     *
     * @return page /backend/pages/clearCache
     * @author gulihua
     */
    @PageId(PageIdEnum.CLEAR_CACHE)
    @RequestMapping(CommBackendPageUrlConfig.URL_CLEAR_CACHE)
    public String clearCache() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_CLEAR_CACHE;
    }

    /**
     * 评论列表页面<br/>
     * url:/admin/comment/management<br/>
     *
     * @return page /backend/pages/commentManagement
     * @author gulihua
     */
    @PageId(PageIdEnum.COMMENT_LIST)
    @RequestMapping(CommBackendPageUrlConfig.URL_COMMENT_MANAGEMENT)
    public String commentManagement() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_COMMENT_MANAGEMENT;
    }

    /**
     * 评论回复页面<br/>
     * url:/admin/comment/reply<br/>
     *
     * @return page /backend/pages/commentReply
     * @author gulihua
     */
    @PageId(PageIdEnum.COMMENT_REPLY)
    @RequestMapping(CommBackendPageUrlConfig.URL_COMMENT_REPLY)
    public String commentReply() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_COMMENT_REPLY;
    }

    /**
     * 评论VIEW页面<br/>
     * url:/admin/comment/view/{id}<br/>
     *
     * @return page /backend/pages/commentView
     * @author gulihua
     */
    @PageId(PageIdEnum.COMMENT_VIEW)
    @RequestMapping(CommBackendPageUrlConfig.URL_COMMENT_VIEW)
    public String commentView(Model model, @PathVariable("id") Long id) {
        CommentExt commentExt = commentBizService.queryCommentExtByOid(id);
        try {
            commentBizService.doUpdateReadByOid(id);
        } catch (JwBlogException e) {
            log.error(">> AdminPageController.commentView exec failed, exception: \n", e);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ERROR;
        }
        model.addAttribute("comm", commentExt);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_COMMENT_VIEW;
    }


    /**
     * 控制台页面<br/>
     * url:/admin/console<br/>
     *
     * @return page /backend/console
     * @author gulihua
     */
    @PageId(PageIdEnum.CONSOLE)
    @RequestMapping(CommBackendPageUrlConfig.URL_CONSOLE)
    public String console(Model model) {
        int publishedArtsCount = articleBizService.countWithPublishArts();
        int draftArtsCount = articleBizService.countWithDraftArts();
        int commentCount = commentBizService.countAllComments();
        int tagsCount = tagsBizService.countAllTags();
        articleBizService.queryRecentDraft(10);
        articleBizService.queryRecentPublishedArts(10);
        model.addAttribute("publishedArtsCount", publishedArtsCount);
        model.addAttribute("draftArtsCount", draftArtsCount);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("tagsCount", tagsCount);

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.CONSOLE;
    }

    /**
     * 动态页面<br/>
     * url:/admin/web/dynamic<br/>
     *
     * @return page /backend/pages/dynamic
     * @author gulihua
     */
    @PageId(PageIdEnum.DYNAMIC)
    @RequestMapping(CommBackendPageUrlConfig.URL_DYNAMIC)
    public String dynamic() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_DYNAMIC;
    }

    /**
     * 菜单管理页面<br/>
     * url:/admin/menu/management<br/>
     *
     * @return page /backend/pages/menuMg
     * @author gulihua
     */
    @PageId(PageIdEnum.MENU_LIST)
    @RequestMapping(CommBackendPageUrlConfig.URL_MENU_MANAGEMENT)
    public String menuMg(Model model) {
        List<MenuExt> menuExtList = null;
        try {
            menuExtList = menuBizService.queryFrontDeskMenuList();
        } catch (JwBlogException e) {

            log.error(">> AdminPageController.menuMg exec failed, exception: \n", e);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_ERROR;
        }
        model.addAttribute("menu", menuExtList);

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MENU_MANAGEMENT;
    }

    /**
     * 菜单添加页面<br/>
     * url:/admin/menu/add<br/>
     *
     * @return page /backend/pages/menuAdd
     * @author gulihua
     */
    @PageId(PageIdEnum.MENU_ADD)
    @RequestMapping(CommBackendPageUrlConfig.URL_MENU_ADD)
    public String menuAdd() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MENU_ADD;
    }


    /**
     * 菜单编辑页面<br/>
     * url:/admin/menu/edit/{id}<br/>
     *
     * @return page /backend/pages/menuEdit
     * @author gulihua
     */
    @PageId(PageIdEnum.MENU_EDIT)
    @RequestMapping(CommBackendPageUrlConfig.URL_MENU_EDIT)
    public String menuEdit(Model model, @PathVariable("id") Long id) {
        try {
            Menu menu = menuTransDao.queryMenuByPrimaryKey(id);
            model.addAttribute("menu", menu);
        } catch (DaoException e) {
            log.error(">> AdminPageController.menuEdit exec failed, exception: \n", e);
            log.error(">> Menu {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;

        }
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MENU_EDIT;
    }


    /**
     * 文章列表页面<br/>
     * url:/admin/my/article<br/>
     *
     * @return page /backend/pages/myArticle
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_LIST)
    @RequestMapping(CommBackendPageUrlConfig.URL_MY_ARTICLE)
    public String myArticle(Model model, String mode) {
        Integer status = 2;
        if (ArticleStatusEnum.PUBLISHED.name().equals(mode)) {
            status = 1;
        } else if (ArticleStatusEnum.DRAFT.name().equals(mode)) {
            status = 0;
        }
        model.addAttribute("status", status);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_MY_ARTICLE;
    }

    /**
     * 回收站文章列表页面<br/>
     * url:/admin/article/recycle/bin<br/>
     *
     * @return page /backend/pages/recycleBin
     * @author gulihua
     */
    @PageId(PageIdEnum.ARTICLE_RECYCLE_BIN)
    @RequestMapping(CommBackendPageUrlConfig.URL_ARTICLE_RECYCLE_BIN)
    public String recycleBin() {
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_RECYCLE_BIN;
    }

    /**
     * 网站配置页面<br/>
     * url:/admin/web/config<br/>
     *
     * @return page /backend/pages/webConfig
     * @author gulihua
     */
    @PageId(PageIdEnum.WEB_CONFIG)
    @RequestMapping(CommBackendPageUrlConfig.URL_WEB_CONFIG)
    public String webConfig(Model model) {
        WebconfBO webConf = webconfBizService.queryConfigWithBO();
        log.info("==>> query webconf data: {}", DomainUtil.toString(webConf));
        model.addAttribute("webConf", webConf);

        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_WEB_CONFIG;
    }

    /**
     * 标签编辑页面<br/>
     * url:/admin/tags/edit/{id}<br/>
     *
     * @return page /backend/pages/tagsEdit
     * @author gulihua
     */
    @PageId(PageIdEnum.TAGS_EDIT)
    @RequestMapping(CommBackendPageUrlConfig.URL_TAGS_EDIT)
    public String tagsEdit(Model model, @PathVariable("id") Long id) {
        try {
            Tags tags = tagsTransDao.queryTagsByPrimaryKey(id);
            model.addAttribute("tag", tags.getContent());
        } catch (DaoException e) {
            log.error(">> AdminPageController.tagsEdit exec failed, exception: \n", e);
            log.error(">> Tags {} cannot be found", id);
            return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_404;
        }
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.PAGE_TAGS_EDIT;
    }


    /**
     * 后台登录页面<br/>
     * url:/admin/login<br/>
     *
     * @return page /backend/pages/login
     * @author gulihua
     */
    @PageId(PageIdEnum.ADMIN_LOGIN)
    @RequestMapping(CommBackendPageUrlConfig.URL_LOGIN)
    public String login(Model model) {
        WebconfBO webconf = webconfBizService.queryConfigWithBO();
        Boolean isCaptcha = webconf.getIsCaptchaOn();
        model.addAttribute("isCaptcha", isCaptcha);
        return CommBackendPageTemplateConfig.PAGE_PREFIX + CommBackendPageTemplateConfig.LOGIN;
    }


}
