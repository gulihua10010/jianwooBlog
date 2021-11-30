package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.ArticleTagsTransDao;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.dao.biz.ArticleBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleQuery;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.enums.TempArticlePageEnum;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
import cn.jianwoo.blog.event.BizEventLogEvent;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.ArticleTagsBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.service.biz.TempArticleBizService;
import cn.jianwoo.blog.service.biz.UidGenService;
import cn.jianwoo.blog.service.bo.ArticleBO;
import cn.jianwoo.blog.service.bo.ArticleMenuBO;
import cn.jianwoo.blog.service.bo.TagsBO;
import cn.jianwoo.blog.service.bo.TempArticleBO;
import cn.jianwoo.blog.service.param.ArticleParam;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ArticleBizServiceImpl implements ArticleBizService {
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private ArticleTagsTransDao articleTagsTransDao;
    @Autowired
    private ArticleBizDao articleBizDao;
    @Autowired
    private UidGenService uidGenService;
    @Autowired
    private TempArticleBizService tempArticleBizService;
    @Autowired
    private MenuBizService menuBizService;
    @Autowired
    private TagsTransDao tagsTransDao;
    @Autowired
    private TagsBizService tagsBizService;
    @Autowired
    private TempArticleTransDao tempArticleTransDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateArticle(ArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start insert article, title = {}", articleBO.getTitle());

        Long oid = uidGenService.getUid();
        Date now = DateUtil.getNow();

        articleBO.setIsComment(articleBO.getIsComment() != null && articleBO.getIsComment());
        if (articleBO.getVisitType() == null) {
            articleBO.setVisitType(ArticleVisitEnum.PUBLIC.getValue());
        }
        ArticleWithBLOBs article = JwBuilder.of(ArticleWithBLOBs::new)
                .with(ArticleWithBLOBs::setOid, oid)
                .with(ArticleWithBLOBs::setAuthor, articleBO.getAuthor())
                .with(ArticleWithBLOBs::setCommentCount, 0L)
                .with(ArticleWithBLOBs::setTitle, articleBO.getTitle())
                .with(ArticleWithBLOBs::setMenuId, articleBO.getMenuId())
                .with(ArticleWithBLOBs::setStatus, articleBO.getStatus())
                .with(ArticleWithBLOBs::setIsComment, articleBO.getIsComment())
                .with(ArticleWithBLOBs::setImgSrc, articleBO.getImgSrc())
                .with(ArticleWithBLOBs::setVisitType, articleBO.getVisitType())
                .with(ArticleWithBLOBs::setContent, articleBO.getContent())
                .with(ArticleWithBLOBs::setPraiseCount, 0L)
                .with(ArticleWithBLOBs::setReadCount, 0L)
                .with(ArticleWithBLOBs::setText, JwUtil.clearHtml(articleBO.getContent()))
                .with(ArticleWithBLOBs::setCreateTime, now)
                .with(ArticleWithBLOBs::setUpdateTime, now)
                .with(ArticleWithBLOBs::setPushTime, now)
                .with(ArticleWithBLOBs::setModifiedTime, now)
                .build();

        if (ArticleVisitEnum.PASSWORD.getValue().equals(articleBO.getVisitType())) {
            article.setPassword(articleBO.getPassword());
        }
        if (ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus()))
        {
            article.setRemoveRecycleTime(now);
        }

        try {
            articleTransDao.doInsertSelective(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doSaveArticle exec failed, e:\n", e);
            throw ArticleBizException.CREATE_FAILED_EXCEPTION.format(articleBO.getTitle()).print();
        }
        if (CollectionUtils.isNotEmpty(articleBO.getTagOidList())) {
            for (Integer t : articleBO.getTagOidList()) {
                ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                        .with(ArticleTags::setArticleOid, oid)
                        .with(ArticleTags::setTagsOid, t)
                        .with(ArticleTags::setCreateTime, now)
                        .with(ArticleTags::setUpdateTime, now).build();
                try {
                    articleTagsTransDao.doInsertSelective(articleTags);
                } catch (DaoException e) {
                    log.error("ArticleBizServiceImpl.doSaveArticle exec failed, e:\n", e);
                    throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + oid + ",tagsOid:" + t).print();

                }
            }
        }
        if (articleBO.getTempArtOid() != null) {
            log.info(">>articleBO.getTempArtOid() {}", articleBO.getTempArtOid());
            tempArticleBizService.doUpdateTempArticleStatus(articleBO.getTempArtOid(),
                    TempArticleStatusEnum.RESTORE, article.getOid());
        }
        registerBizEvent(oid, article.getTitle(), article.getStatus(), null, false);


        log.info("==========>Insert article successfully, title = {}", articleBO.getTitle());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateArticleInfo(ArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start update article info, title = {}", articleBO.getTitle());

        Article article = null;
        Date now = DateUtil.getNow();
        try {
            article = articleTransDao.queryArticleByOid(articleBO.getOid());
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(article.getOid()).print();

        }

        if (ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_RECYCLE.format(article.getTitle()).print();
        }

        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        newArticle.setOid(article.getOid());
        newArticle.setAuthor(articleBO.getAuthor());
        newArticle.setTitle(articleBO.getTitle());
        newArticle.setModifiedTime(now);
        newArticle.setMenuId(articleBO.getMenuId());
        newArticle.setIsComment(articleBO.getIsComment() != null && articleBO.getIsComment());
        if (articleBO.getImgSrc() != null) {
            newArticle.setImgSrc(articleBO.getImgSrc());
        }
        if (ArticleVisitEnum.PASSWORD.getValue().equals(articleBO.getVisitType())) {
            newArticle.setPassword(articleBO.getPassword());
        }
        newArticle.setUpdateTime(now);
        newArticle.setVisitType(articleBO.getVisitType() == null ? ArticleVisitEnum.PUBLIC.getValue() : articleBO.getVisitType());
        if (ArticleStatusEnum.RECYCLE.getValue().equals(newArticle.getStatus()))
        {
            article.setRemoveRecycleTime(now);
        }
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doUpdateArticleInfo exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getOid()).print();
        }
        articleTagsTransDao.doDeleteByArticleOid(articleBO.getOid());
        if (CollectionUtils.isNotEmpty(articleBO.getTagOidList())) {
            for (Integer t : articleBO.getTagOidList()) {
                ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                        .with(ArticleTags::setArticleOid, articleBO.getOid())
                        .with(ArticleTags::setTagsOid, t)
                        .with(ArticleTags::setCreateTime, now)
                        .with(ArticleTags::setUpdateTime, now).build();
                try {
                    articleTagsTransDao.doInsertSelective(articleTags);
                } catch (DaoException e) {
                    log.error("ArticleBizServiceImpl.doUpdateArticleInfo exec failed, e:\n", e);
                    throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + articleBO.getOid() + ",tagsOid:" + t).print();
                }
            }
        }
        registerBizEvent(article.getOid(), newArticle.getTitle(), null, article.getStatus(), false);

        log.info("==========>Update article info successfully, title = {}", articleBO.getTitle());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateArticle(ArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start update article, title = {}", articleBO.getTitle());

        Date now = DateUtil.getNow();
        Article article = null;
        try {
            article = articleTransDao.queryArticleByOid(articleBO.getOid());
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(articleBO.getOid()).print();

        }

        if (ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_RECYCLE.format(article.getTitle()).print();
        }


        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        newArticle.setOid(article.getOid());
        newArticle.setAuthor(articleBO.getAuthor());
        newArticle.setTitle(articleBO.getTitle());
        newArticle.setMenuId(articleBO.getMenuId());
        newArticle.setIsComment(articleBO.getIsComment() != null && articleBO.getIsComment());
        newArticle.setImgSrc(articleBO.getImgSrc());
        if (ArticleVisitEnum.PASSWORD.getValue().equals(articleBO.getVisitType())) {
            newArticle.setPassword(articleBO.getPassword());
        }
        newArticle.setUpdateTime(now);
        newArticle.setModifiedTime(now);
        newArticle.setVisitType(articleBO.getVisitType() == null ? ArticleVisitEnum.PUBLIC.getValue() : articleBO.getVisitType());
        newArticle.setContent(articleBO.getContent());
        newArticle.setText(JwUtil.clearHtml(articleBO.getContent()));
        if (StringUtils.isNotBlank(articleBO.getStatus())) {
            newArticle.setStatus(articleBO.getStatus());
        }
        if (ArticleStatusEnum.RECYCLE.getValue().equals(newArticle.getStatus()))
        {
            article.setRemoveRecycleTime(now);
        }
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doUpdateArticle exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getOid()).print();
        }
        articleTagsTransDao.doDeleteByArticleOid(articleBO.getOid());
        if (CollectionUtils.isNotEmpty(articleBO.getTagOidList())) {
            for (Integer t : articleBO.getTagOidList()) {
                ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                        .with(ArticleTags::setArticleOid, articleBO.getOid())
                        .with(ArticleTags::setTagsOid, t)
                        .with(ArticleTags::setCreateTime, now)
                        .with(ArticleTags::setUpdateTime, now).build();
                try {
                    articleTagsTransDao.doInsertSelective(articleTags);
                } catch (DaoException e) {
                    log.error("ArticleBizServiceImpl.doUpdateArticle exec failed, e:\n", e);
                    throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + articleBO.getOid() + ", tagsOid:" + t).print();
                }
            }
        }

        if (articleBO.getTempArtOid() != null) {
            tempArticleBizService.doUpdateTempArticleStatus(articleBO.getTempArtOid(),
                    TempArticleStatusEnum.RESTORE, article.getOid());
        }
        registerBizEvent(article.getOid(), newArticle.getTitle(), newArticle.getStatus(), article.getStatus(), false);

        log.info("==========>Update article successfully, title = {}", articleBO.getTitle());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveToRecycle(Long oid) throws JwBlogException {

        Article article = null;
        try {
            article = articleTransDao.queryArticleByOid(oid);
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();

        }

        if (ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_RECYCLE.format(article.getTitle()).print();
        }
        Date now = DateUtil.getNow();
        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        newArticle.setOid(article.getOid());
        newArticle.setStatus(ArticleStatusEnum.RECYCLE.getValue());
        newArticle.setRemoveRecycleTime(now);
        newArticle.setUpdateTime(now);
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doRemoveToRecycle exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        registerBizEvent(article.getOid(), article.getTitle(), newArticle.getStatus(), article.getStatus(), true);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doDeleteArticle(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByOid(oid);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doDeleteArticle exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();
        }
        if (ArticleStatusEnum.DELETE.getValue().equals(article.getStatus())) {
            throw ArticleBizException.HAS_DELETE_CN.format(article.getTitle()).print();
        }
        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        Date now = DateUtil.getNow();
        try {
            newArticle.setOid(article.getOid());
            newArticle.setStatus(ArticleStatusEnum.DELETE.getValue());
            newArticle.setDelTime(now);
            newArticle.setUpdateTime(now);
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doDeleteArticle exec failed, e:\n", e);
            throw ArticleBizException.DELETE_FAILED_EXCEPTION.format(oid).print();

        }
        registerBizEvent(article.getOid(), article.getTitle(), newArticle.getStatus(), article.getStatus(), true);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveToDraft(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByOid(oid);
        } catch (DaoException e) {
            log.error(" ArticleBizServiceImpl.doRemoveToDraft exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();

        }

        if (ArticleStatusEnum.DRAFT.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_DRAFT.format(article.getTitle()).print();
        } else if (ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_RECYCLE.format(article.getTitle()).print();
        } else if (!ArticleStatusEnum.PUBLISHED.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_NOT_PUBLISHED_CN.format(article.getTitle()).print();
        }
        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        try {
            newArticle.setOid(article.getOid());
            newArticle.setUpdateTime(DateUtil.getNow());
            newArticle.setStatus(ArticleStatusEnum.DRAFT.getValue());
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("  ArticleBizServiceImpl.doRemoveToDraft exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        registerBizEvent(article.getOid(), article.getTitle(), newArticle.getStatus(), article.getStatus(), true);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doPublishedArticle(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByOid(oid);
        } catch (DaoException e) {
            log.error("  ArticleBizServiceImpl.doPublishedArticle exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();

        }
        if (ArticleStatusEnum.PUBLISHED.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_PUBLISHED.format(article.getTitle()).print();
        } else if (ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_RECYCLE.format(article.getTitle()).print();
        } else if (!ArticleStatusEnum.DRAFT.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_NOT_DRAFT.format(article.getTitle()).print();
        }
        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        try {
            newArticle.setOid(article.getOid());
            newArticle.setUpdateTime(DateUtil.getNow());
            newArticle.setStatus(ArticleStatusEnum.PUBLISHED.getValue());
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doPublishedArticle exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        registerBizEvent(article.getOid(), article.getTitle(), newArticle.getStatus(), article.getStatus(), true);
    }


    @Override
    public Integer countWithEffectiveArts() {
        String[] status = new String[]{ArticleStatusEnum.DRAFT.getValue(), ArticleStatusEnum.PUBLISHED.getValue()};
        return articleBizDao.countArtsByStatus(status);
    }


    @Override
    public Integer countWithPublishArts() {
        String[] status = new String[]{ArticleStatusEnum.PUBLISHED.getValue()};
        return articleBizDao.countArtsByStatus(status);
    }


    @Override
    public Integer countWithDraftArts() {
        String[] status = new String[]{ArticleStatusEnum.DRAFT.getValue(),};
        return articleBizDao.countArtsByStatus(status);

    }


    @Override
    public List<ArticleBO> queryRecentPublishedArts(int n) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", ArticleStatusEnum.PUBLISHED.getValue());
        params.put("n", n);
        List<Article> articleList = articleBizDao.queryArticleByStatusAndLimit(params);
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public List<ArticleBO> queryRecentDraft(int n) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", ArticleStatusEnum.DRAFT.getValue());
        params.put("n", n);
        List<Article> articleList = articleBizDao.queryArticleByStatusAndLimit(params);
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public List<ArticleBO> queryRecycleBinArts() {
        List<Article> articleList = articleTransDao.queryArticleByStatus(ArticleStatusEnum.RECYCLE.getValue());
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public List<ArticleBO> queryDraftArts() {
        List<Article> articleList = articleTransDao.queryArticleByStatus(ArticleStatusEnum.DRAFT.getValue());
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRestoreRecycleBinArts(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByOid(oid);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doRestoreRecycleBinArts exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();

        }

        if (ArticleStatusEnum.DRAFT.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_DRAFT.format(article.getTitle()).print();
        } else if (ArticleStatusEnum.PUBLISHED.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_HAS_PUBLISHED.format(article.getTitle()).print();
        } else if (!ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_NOT_RECYCLE.format(article.getTitle()).print();
        }


        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        try {
            newArticle.setOid(article.getOid());
            newArticle.setUpdateTime(DateUtil.getNow());
            newArticle.setStatus(ArticleStatusEnum.DRAFT.getValue());
            newArticle.setRemoveRecycleTime(null);
            articleBizDao.doRestoreFromRecycle(newArticle);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doRestoreRecycleBinArts exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        registerBizEvent(article.getOid(), article.getTitle(), newArticle.getStatus(), article.getStatus(), true);
    }


    @Override
    public List<ArticleBO> queryPublishedArticles() {
        List<Article> articleList = articleTransDao.queryArticleByStatus(ArticleStatusEnum.PUBLISHED.getValue());
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public List<ArticleBO> querySearchArtsByKeyword(String keyword) {
        List<Article> articleList;
        if (StringUtils.isBlank(keyword)) {
            articleList = articleTransDao.queryArticleByStatus(ArticleStatusEnum.PUBLISHED.getValue());
        } else {
            articleList = articleBizDao.queryPublishedArtsByKeyword(keyword);
        }

        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public List<ArticleBO> queryNewestArts(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        List<Article> articleList = articleBizDao.queryPublishedNewestArts(limit);
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public List<ArticleBO> queryHotArts(Integer limit) {
        if (limit == null) {
            limit = 10;
        }

        List<Article> articleList = articleBizDao.queryPublishedHotArts(limit);
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleList)) {
            articleList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public List<ArticleBO> queryRandomArts(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        List<Article> arts = articleTransDao.queryArticleByStatus(ArticleStatusEnum.PUBLISHED.getValue());
        List<Article> randomArts = new ArrayList<>();
        if (randomArts.size() >= limit) {
            List<Integer> oids = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                int oid = TestUtil.getInstance().getRandomInt(0, arts.size());
                if (oids.contains(oid)) {
                    i--;
                    continue;
                }
                oids.add(oid);
            }
            for (int i : oids) {
                randomArts.add(arts.get(i));
            }

        } else {
            randomArts = arts;
        }

        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(randomArts)) {
            randomArts.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddPraise(Long artOid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByOid(artOid);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(artOid).print();

        }
        if (!ArticleStatusEnum.PUBLISHED.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_NOT_PUBLISHED_CN.format(article.getTitle()).print();
        }

        try {

            articleBizDao.doUpdateArticlePraiseCnt(artOid);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(artOid).print();
        }
    }


    @Override
    public PageInfo<ArticleBO> queryEffectiveArticleList(ArticleParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        ArticleQuery query = new ArticleQuery();
        BeanUtils.copyProperties(param, query);
        List<ArticleExt> articleExtList = articleBizDao.queryEffectiveArticleList(query);

        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleExtList)) {
            articleExtList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<ArticleBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;

    }


    @Override
    public PageInfo<ArticleBO> queryRecycleBinArticleList(ArticleParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        ArticleQuery query = new ArticleQuery();
        BeanUtils.copyProperties(param, query);
        List<ArticleExt> articleExtList = articleBizDao.queryRecycleBinArticleList(query);
        List<ArticleBO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleExtList)) {
            articleExtList.forEach(o -> {
                ArticleBO bo = new ArticleBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<ArticleBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveToRecycleBinList(List<Long> oidList) throws JwBlogException {
        if (CollectionUtils.isNotEmpty(oidList)) {
            for (Long oid : oidList) {
                this.doRemoveToRecycle(oid);
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRestoreRecycleToDraftList(List<Long> oidList) throws JwBlogException {
        if (CollectionUtils.isNotEmpty(oidList)) {
            for (Long oid : oidList) {
                try {
                    this.doRestoreRecycleBinArts(oid);
                } catch (JwBlogException e) {
                    log.error("ArticleBizServiceImpl.doRestoreRecycleToDraftList exec failed, e:\n", e);
                    throw ArticleBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
                }
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doDeleteRecycleBinList(List<Long> oidList) throws JwBlogException {
        if (CollectionUtils.isNotEmpty(oidList)) {
            for (Long oid : oidList) {
                try {
                    this.doDeleteArticle(oid);
                } catch (JwBlogException e) {
                    log.error("ArticleBizServiceImpl.doDeleteRecycleBinList exec failed, e:\n", e);
                    throw ArticleBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
                }
            }
        }
    }

    @Override
    public ArticleBO queryArticleEditInfo(String artOid) throws JwBlogException {
        ArticleWithBLOBs article;
        ArticleBO bo = null;
        try {
            Long oid = Long.parseLong(artOid);
            article = articleTransDao.queryArticleByOidWithBLOBs(oid);
            bo = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setOid, article.getOid())
                    .with(ArticleBO::setTitle, StringEscapeUtils.escapeHtml4(article.getTitle()))
                    .with(ArticleBO::setAuthor, StringEscapeUtils.escapeHtml4(article.getAuthor()))
                    .with(ArticleBO::setContent, article.getContent())
                    .with(ArticleBO::setMenuId, article.getMenuId())
                    .with(ArticleBO::setImgSrc, article.getImgSrc())
                    .with(ArticleBO::setIsComment, article.getIsComment())
                    .with(ArticleBO::setPassword, article.getPassword())
                    .with(ArticleBO::setVisitType, article.getVisitType())
                    .with(ArticleBO::setStatus, article.getStatus())
                    .build();

            List<TagsBO> artTags = tagsBizService.queryTagsByArtOid(oid);
            List<TagsBO> tagsList = new ArrayList<TagsBO>();
            if (CollectionUtils.isNotEmpty(artTags)) {
                for (TagsBO tag : artTags) {
                    TagsBO tagsListVO = JwBuilder.of(TagsBO::new)
                            .with(TagsBO::setId, tag.getId())
                            .with(TagsBO::setName, StringEscapeUtils.escapeHtml4(tag.getName())).build();
                    tagsList.add(tagsListVO);
                }
                bo.setArtTagsList(tagsList);
            }
            List<Tags> allTags = tagsTransDao.queryAllTags();
            List<TagsBO> allTagsList = new ArrayList<TagsBO>();
            if (CollectionUtils.isNotEmpty(allTags)) {
                for (Tags tag : allTags) {
                    TagsBO tagsListVO = JwBuilder.of(TagsBO::new)
                            .with(TagsBO::setId, tag.getOid())
                            .with(TagsBO::setName, StringEscapeUtils.escapeHtml4(tag.getContent())).build();
                    allTagsList.add(tagsListVO);
                }
                bo.setAllTagsList(allTagsList);
            }

            List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());
            List<ArticleMenuBO> menuVoList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(menuList)) {
                for (Menu menu : menuList) {
                    ArticleMenuBO menuVo = JwBuilder.of(ArticleMenuBO::new)
                            .with(ArticleMenuBO::setId, menu.getOid())
                            .with(ArticleMenuBO::setName, StringEscapeUtils.escapeHtml4(menu.getText())).build();
                    menuVoList.add(menuVo);
                }
                bo.setMenuList(menuVoList);
            }
            TempArticle tempArticle = tempArticleTransDao.queryLastestTempArticle(oid, TempArticlePageEnum.EDIT.getValue());

            if (null != tempArticle) {
                TempArticleBO tempArticleInfoBO = JwBuilder.of(TempArticleBO::new)
                        .with(TempArticleBO::setOid, tempArticle.getOid())
                        .with(TempArticleBO::setTitle, StringEscapeUtils.escapeHtml4(tempArticle.getTitle()))
                        .with(TempArticleBO::setAuthor, StringEscapeUtils.escapeHtml4(tempArticle.getAuthor()))
                        .with(TempArticleBO::setContent, tempArticle.getContent())
                        .with(TempArticleBO::setMenuOid, tempArticle.getMenuOid())
                        .with(TempArticleBO::setImgSrc, tempArticle.getImgSrc())
                        .with(TempArticleBO::setIsComment, tempArticle.getIsComment())
                        .with(TempArticleBO::setPassword, tempArticle.getPassword())
                        .with(TempArticleBO::setVisitType, tempArticle.getVisitType())
                        .with(TempArticleBO::setStatus, tempArticle.getStatus())
                        .build();


                if (StringUtils.isNotBlank(tempArticle.getTags())) {
                    JSONArray jsonArray = JSON.parseArray(tempArticle.getTags());
                    List<TagsBO> TemptagsList = new ArrayList<TagsBO>();
                    if (jsonArray != null && jsonArray.size() > 0) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject o = jsonArray.getJSONObject(i);
                            TagsBO tagsListVO = JwBuilder.of(TagsBO::new)
                                    .with(TagsBO::setId, o.getLong("id"))
                                    .with(TagsBO::setName, StringEscapeUtils.escapeHtml4(o.getString("name")))
                                    .build();
                            TemptagsList.add(tagsListVO);
                        }
                        tempArticleInfoBO.setArtTagsList(TemptagsList);
                    }
                }


                bo.setTempArticle(tempArticleInfoBO);
            }

        } catch (Exception e) {
            log.error("==>>ArticleBizServiceImpl.queryArticleEditInfo exec failed, e\n", e);
            throw ArticleBizException.QUERY_FAILED_EXCEPTION_CN.format(artOid).print();

        }

        return bo;
    }

    private void registerBizEvent(Long oid, String title, String newStatus, String oldStatus, boolean isOnlyStatusChange) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext());
        event.setBizEventTypeEnum(BizEventTypeEnum.ARTICLE);
        event.setBizEventOptTypeEnum(BizEventOptTypeEnum.valueOfArticleStatus(newStatus, oldStatus, isOnlyStatusChange));
        event.setOid(oid);
        event.setDesc(title);
        applicationContext.publishEvent(event);
    }

}