package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.ArticleTagsTransDao;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.dao.biz.ArticleBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleParam;
import cn.jianwoo.blog.enums.ArtCommStatusEnum;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.enums.TempArticlePageEnum;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
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
import cn.jianwoo.blog.service.bo.TempArticleInfoBO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSaveArticle(String title, String content, String author, Integer typeId, Integer isComment,
                              Integer visitType, String imsSrc, String password, Integer[] tags, Integer status) throws JwBlogException {
        log.info("==========>Start insert article,title = {}", title);

        Long oid = uidGenService.getUid();
        Date now = DateUtil.getNow();

        isComment = isComment == null ? ArtCommStatusEnum.NO_COMMENT.getValue() : isComment;
        if (visitType == null) {
            visitType = ArticleVisitEnum.PUBLIC.getValue();
        }
        Article article = JwBuilder.of(Article::new)
                .with(Article::setOid, oid)
                .with(Article::setAuthor, author)
                .with(Article::setCommentCount, 0L)
                .with(Article::setTitle, title)
                .with(Article::setTypeId, typeId)
                .with(Article::setStatus, status)
                .with(Article::setIsComment, isComment)
                .with(Article::setImgSrc, imsSrc)
                .with(Article::setVisitType, visitType)
                .with(Article::setContent, content)
                .with(Article::setPraiseCount, 0L)
                .with(Article::setReadCount, 0L)
                .with(Article::setText, JwUtil.clearHtml(content))
                .with(Article::setCreateDate, now)
                .with(Article::setUpdateDate, now)
                .with(Article::setPushDate, now)
                .with(Article::setModifiedDate, now)
                .build();

        if (ArticleVisitEnum.PASSWORD.getValue().equals(visitType)) {
            article.setPassword(password);
        }
        try {
            articleTransDao.doInsert(article);
        } catch (DaoException e) {
            throw ArticleBizException.CREATE_FAILED_EXCEPTION.format(title).print();
        }
        if (tags != null) {
            for (Integer t : tags) {
                ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                        .with(ArticleTags::setArticleOid, oid)
                        .with(ArticleTags::setTagsOid, t)
                        .with(ArticleTags::setCreateDate, now)
                        .with(ArticleTags::setUpdateDate, now).build();
                try {
                    articleTagsTransDao.doInsert(articleTags);
                } catch (DaoException e) {
                    throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + oid + ",tagsOid:" + t).print();

                }
            }
        }

        log.info("==========>Insert article successfully,title = {}", title);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSaveArticle(ArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start insert article,title = {}", articleBO.getTitle());

        Long oid = uidGenService.getUid();
        Date now = DateUtil.getNow();

        articleBO.setIsComment(articleBO.getIsComment() == null ? ArtCommStatusEnum.NO_COMMENT.getValue() : articleBO.getIsComment());
        if (articleBO.getVisitType() == null) {
            articleBO.setVisitType(ArticleVisitEnum.PUBLIC.getValue());
        }
        Article article = JwBuilder.of(Article::new)
                .with(Article::setOid, oid)
                .with(Article::setAuthor, articleBO.getAuthor())
                .with(Article::setCommentCount, 0L)
                .with(Article::setTitle, articleBO.getTitle())
                .with(Article::setTypeId, articleBO.getTypeId())
                .with(Article::setStatus, articleBO.getStatus())
                .with(Article::setIsComment, articleBO.getIsComment())
                .with(Article::setImgSrc, articleBO.getImgSrc())
                .with(Article::setVisitType, articleBO.getVisitType())
                .with(Article::setContent, articleBO.getContent())
                .with(Article::setPraiseCount, 0L)
                .with(Article::setReadCount, 0L)
                .with(Article::setText, JwUtil.clearHtml(articleBO.getContent()))
                .with(Article::setCreateDate, now)
                .with(Article::setUpdateDate, now)
                .with(Article::setPushDate, now)
                .with(Article::setModifiedDate, now)
                .build();

        if (ArticleVisitEnum.PASSWORD.getValue().equals(articleBO.getVisitType())) {
            article.setPassword(articleBO.getPassword());
        }
        try {
            articleTransDao.doInsert(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doSaveArticle exec failed, e:\n", e);
            throw ArticleBizException.CREATE_FAILED_EXCEPTION.format(articleBO.getTitle()).print();
        }
        if (articleBO.getTags() != null) {
            for (Integer t : articleBO.getTags()) {
                ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                        .with(ArticleTags::setArticleOid, oid)
                        .with(ArticleTags::setTagsOid, t)
                        .with(ArticleTags::setCreateDate, now)
                        .with(ArticleTags::setUpdateDate, now).build();
                try {
                    articleTagsTransDao.doInsert(articleTags);
                } catch (DaoException e) {
                    log.error("ArticleBizServiceImpl.doSaveArticle exec failed, e:\n", e);
                    throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + oid + ",tagsOid:" + t).print();

                }
            }
        }
        if (articleBO.getTempArtOid() != null) {
            tempArticleBizService.doUpdateTempArticleStatus(articleBO.getTempArtOid(),
                    TempArticleStatusEnum.RESTORE, article.getOid());
        }

        log.info("==========>Insert article successfully,title = {}", articleBO.getTitle());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateArticleInfo(Long oid, String title, String author, Integer typeId, Integer isComment,
                                    Integer visitType, String imsSrc, String password, Integer[] tags) throws JwBlogException {
        log.info("==========>Start update article,title = {}", title);

        Article article = null;
        Date now = DateUtil.getNow();
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        article.setAuthor(author);
        article.setTitle(title);
        article.setModifiedDate(new Date());
        article.setTypeId(typeId);
        article.setIsComment(isComment == null ? ArtCommStatusEnum.NO_COMMENT.getValue() : isComment);
        if (imsSrc != null) {
            article.setImgSrc(imsSrc);
        }
        if (ArticleVisitEnum.PASSWORD.getValue().equals(visitType)) {
            article.setPassword(password);
        }
        article.setUpdateDate(new Date());
        article.setVisitType(visitType == null ? ArticleVisitEnum.PUBLIC.getValue() : visitType);
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        articleTagsTransDao.doDeleteByArticleOid(oid);

        for (Integer t : tags) {
            ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                    .with(ArticleTags::setArticleOid, oid)
                    .with(ArticleTags::setTagsOid, t)
                    .with(ArticleTags::setCreateDate, now)
                    .with(ArticleTags::setUpdateDate, now).build();
            try {
                articleTagsTransDao.doInsert(articleTags);
            } catch (DaoException e) {
                throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + oid + ",tagsOid:" + t).print();
            }
        }
        log.info("==========>Update article successfully,title = {}", title);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateArticleInfo(ArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start update article info,title = {}", articleBO.getTitle());

        Article article = null;
        Date now = DateUtil.getNow();
        try {
            article = articleTransDao.queryArticleByPrimaryKey(articleBO.getOid());
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(articleBO.getOid()).print();

        }
        article.setAuthor(articleBO.getAuthor());
        article.setTitle(articleBO.getTitle());
        article.setModifiedDate(new Date());
        article.setTypeId(articleBO.getTypeId());
        article.setIsComment(articleBO.getIsComment() == null ?
                ArtCommStatusEnum.NO_COMMENT.getValue() : articleBO.getIsComment());
        if (articleBO.getImgSrc() != null) {
            article.setImgSrc(articleBO.getImgSrc());
        }
        if (ArticleVisitEnum.PASSWORD.getValue().equals(articleBO.getVisitType())) {
            article.setPassword(articleBO.getPassword());
        }
        article.setUpdateDate(new Date());
        article.setVisitType(articleBO.getVisitType() == null ? ArticleVisitEnum.PUBLIC.getValue() : articleBO.getVisitType());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doUpdateArticleInfo exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getOid()).print();
        }
        articleTagsTransDao.doDeleteByArticleOid(articleBO.getOid());

        for (Integer t : articleBO.getTags()) {
            ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                    .with(ArticleTags::setArticleOid, articleBO.getOid())
                    .with(ArticleTags::setTagsOid, t)
                    .with(ArticleTags::setCreateDate, now)
                    .with(ArticleTags::setUpdateDate, now).build();
            try {
                articleTagsTransDao.doInsert(articleTags);
            } catch (DaoException e) {
                log.error("ArticleBizServiceImpl.doUpdateArticleInfo exec failed, e:\n", e);
                throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + articleBO.getOid() + ",tagsOid:" + t).print();
            }
        }
        log.info("==========>Update article info successfully,title = {}", articleBO.getTitle());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateArticle(Long oid, String title, String content, String author, Integer typeId,
                                Integer isComment, Integer visitType, String imsSrc, String password, Integer[] tags, Integer status)
            throws JwBlogException {
        log.info("==========>Start update article,title = {}", title);

        Date now = DateUtil.getNow();
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        article.setAuthor(author);
        article.setTitle(title);
        article.setTypeId(typeId);
        article.setIsComment(isComment == null ? ArtCommStatusEnum.NO_COMMENT.getValue() : isComment);
        article.setImgSrc(imsSrc);
        if (ArticleVisitEnum.PASSWORD.getValue().equals(visitType)) {

            article.setPassword(password);
        }
        article.setUpdateDate(new Date());
        article.setModifiedDate(new Date());
        article.setVisitType(visitType == null ? ArticleVisitEnum.PUBLIC.getValue() : visitType);
        article.setContent(content);
        article.setText(JwUtil.clearHtml(content));
        if (status != null) {
            article.setStatus(status);
        }
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        articleTagsTransDao.doDeleteByArticleOid(oid);

        for (Integer t : tags) {
            ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                    .with(ArticleTags::setArticleOid, oid)
                    .with(ArticleTags::setTagsOid, t)
                    .with(ArticleTags::setCreateDate, now)
                    .with(ArticleTags::setUpdateDate, now).build();
            try {
                articleTagsTransDao.doInsert(articleTags);
            } catch (DaoException e) {
                throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + oid + ",tagsOid:" + t).print();
            }
        }
        log.info("==========>Update article successfully,title = {}", title);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateArticle(ArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start update article,title = {}", articleBO.getTitle());

        Date now = DateUtil.getNow();
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(articleBO.getOid());
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(articleBO.getOid()).print();

        }
        article.setAuthor(articleBO.getAuthor());
        article.setTitle(articleBO.getTitle());
        article.setTypeId(articleBO.getTypeId());
        article.setIsComment(articleBO.getIsComment() == null ? ArtCommStatusEnum.NO_COMMENT.getValue() : articleBO.getIsComment());
        article.setImgSrc(articleBO.getImgSrc());
        if (ArticleVisitEnum.PASSWORD.getValue().equals(articleBO.getVisitType())) {
            article.setPassword(articleBO.getPassword());
        }
        article.setUpdateDate(new Date());
        article.setModifiedDate(new Date());
        article.setVisitType(articleBO.getVisitType() == null ? ArticleVisitEnum.PUBLIC.getValue() : articleBO.getVisitType());
        article.setContent(articleBO.getContent());
        article.setText(JwUtil.clearHtml(articleBO.getContent()));
        if (articleBO.getStatus() != null) {
            article.setStatus(articleBO.getStatus());
        }
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doUpdateArticle exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getOid()).print();
        }
        articleTagsTransDao.doDeleteByArticleOid(articleBO.getOid());

        for (Integer t : articleBO.getTags()) {
            ArticleTags articleTags = JwBuilder.of(ArticleTags::new)
                    .with(ArticleTags::setArticleOid, articleBO.getOid())
                    .with(ArticleTags::setTagsOid, t)
                    .with(ArticleTags::setCreateDate, now)
                    .with(ArticleTags::setUpdateDate, now).build();
            try {
                articleTagsTransDao.doInsert(articleTags);
            } catch (DaoException e) {
                log.error("ArticleBizServiceImpl.doUpdateArticle exec failed, e:\n", e);
                throw ArticleTagsBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + articleBO.getOid() + ",tagsOid:" + t).print();
            }
        }
        if (articleBO.getTempArtOid() != null) {
            tempArticleBizService.doUpdateTempArticleStatus(articleBO.getTempArtOid(),
                    TempArticleStatusEnum.RESTORE, article.getOid());
        }
        log.info("==========>Update article successfully,title = {}", articleBO.getTitle());
    }


    @Override
    public void doRemoveToRecycle(Long oid) throws JwBlogException {

        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        article.setStatus(ArticleStatusEnum.RECYCLE.getValue());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doRemoveToRecycle exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public void doDeleteArticle(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doDeleteArticle exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        try {
            Article newArticle = new Article();
            newArticle.setOid(article.getOid());
            newArticle.setStatus(ArticleStatusEnum.DELETE.getValue());
            newArticle.setUpdateDate(new Date());
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doDeleteArticle exec failed, e:\n", e);
            throw ArticleBizException.DELETE_FAILED_EXCEPTION.format(oid).print();

        }
    }


    @Override
    public void doRemoveToDraft(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error(" ArticleBizServiceImpl.doRemoveToDraft exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        article.setStatus(ArticleStatusEnum.DRAFT.getValue());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("  ArticleBizServiceImpl.doRemoveToDraft exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doPublishedArticle(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("  ArticleBizServiceImpl.doPublishedArticle exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        article.setStatus(ArticleStatusEnum.PUBLISHED.getValue());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doPublishedArticle exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public Integer countWithEffectiveArts() {
        int[] status = new int[]{ArticleStatusEnum.DRAFT.getValue(), ArticleStatusEnum.PUBLISHED.getValue()};
        return articleBizDao.countArtsByStatus(status);
    }


    @Override
    public Integer countWithPublishArts() {
        int[] status = new int[]{ArticleStatusEnum.PUBLISHED.getValue()};
        return articleBizDao.countArtsByStatus(status);
    }


    @Override
    public Integer countWithDraftArts() {
        int[] status = new int[]{ArticleStatusEnum.DRAFT.getValue(),};
        return articleBizDao.countArtsByStatus(status);

    }


    @Override
    public List<Article> queryRecentPublishedArts(int n) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", ArticleStatusEnum.PUBLISHED.getValue());
        params.put("n", n);
        return articleBizDao.queryArticleByStatusAndLimit(params);
    }


    @Override
    public List<Article> queryRecentDraft(int n) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", ArticleStatusEnum.DRAFT.getValue());
        params.put("n", n);
        return articleBizDao.queryArticleByStatusAndLimit(params);
    }


    @Override
    public List<Article> queryRecycleBinArts() {
        return articleTransDao.queryArticleByStatus(ArticleStatusEnum.RECYCLE.getValue());
    }


    @Override
    public List<Article> queryDraftArts() {
        return articleTransDao.queryArticleByStatus(ArticleStatusEnum.DRAFT.getValue());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRestoreRecycleBinArts(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doRestoreRecycleBinArts exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        if (!ArticleStatusEnum.RECYCLE.getValue().equals(article.getStatus())) {
            String desc = String.format(ExceptionConstants.ARTICLE_STATUS_NOT_MATCH_DESC, article.getStatus(),
                    article.getTitle());
            throw new ArticleBizException().getNewInstance(ExceptionConstants.ARTICLE_STATUS_NOT_MATCH, desc).print();

        }
        article.setStatus(ArticleStatusEnum.DRAFT.getValue());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doRestoreRecycleBinArts exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public List<Article> queryPublishedArticles() {
        return articleTransDao.queryArticleByStatus(ArticleStatusEnum.PUBLISHED.getValue());
    }


    @Override
    public List<Article> querySearchArtsByKeyword(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return articleTransDao.queryArticleByStatus(ArticleStatusEnum.PUBLISHED.getValue());

        }
        return articleBizDao.queryPublishedArtsByKeyword(keyword);
    }


    @Override
    public List<Article> queryNewestArts(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        return articleBizDao.queryPublishedNewestArts(limit);
    }


    @Override
    public List<Article> queryHotArts(Integer limit) {
        if (limit == null) {
            limit = 10;
        }

        return articleBizDao.queryPublishedHotArts(limit);

    }


    @Override
    public List<Article> queryRandomArts(Integer limit) {
        if (limit == null) {
            limit = 10;
        }

        List<Article> arts = articleTransDao.queryArticleByStatus(ArticleStatusEnum.PUBLISHED.getValue());
        if (arts.size() < limit) {
            return arts;
        }
        int[] oids = new int[limit];
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < limit; i++) {
            int oid = TestUtil.getInstance().getRandomInt(0, arts.size());
            if (map.containsKey(oid)) {
                i--;
                continue;
            }
            oids[i] = oid;
            map.put(oid, oid);
        }
        List<Article> randomArts = new ArrayList<>();
        for (int i : oids) {
            randomArts.add(arts.get(i));
        }
        return randomArts;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddPraise(Long artOid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(artOid);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(artOid).print();

        }

        article.setPraiseCount((article.getPraiseCount() == null ? 0 : article.getPraiseCount()) + 1);
        article.setUpdateDate(new Date());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(artOid).print();
        }
    }


    @Override
    public PageInfo<ArticleExt> queryEffectiveArticleList(ArticleParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<ArticleExt> list = articleBizDao.queryEffectiveArticleList(param);
        PageInfo<ArticleExt> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }


    @Override
    public PageInfo<ArticleExt> queryRecycleBinArticleList(ArticleParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<ArticleExt> list = articleBizDao.queryRecycleBinArticleList(param);
        PageInfo<ArticleExt> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public void doRemoveToRecycleBinList(List<Long> oidList) throws JwBlogException {
        if (CollectionUtils.isNotEmpty(oidList)) {
            for (Long oid : oidList) {
                this.doRemoveToRecycle(oid);
            }
        }
    }


    @Override
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
    public ArticleBO queryArticleEditInfo(Long artOid) throws JwBlogException {
        Article article;
        ArticleBO bo = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(artOid);
            bo = JwBuilder.of(ArticleBO::new)
                    .with(ArticleBO::setOid, article.getOid())
                    .with(ArticleBO::setTitle, StringEscapeUtils.escapeHtml4(article.getTitle()))
                    .with(ArticleBO::setAuthor, StringEscapeUtils.escapeHtml4(article.getAuthor()))
                    .with(ArticleBO::setContent, article.getContent())
                    .with(ArticleBO::setTypeId, article.getTypeId())
                    .with(ArticleBO::setImgSrc, article.getImgSrc())
                    .with(ArticleBO::setIsComment, article.getIsComment())
                    .with(ArticleBO::setPassword, article.getPassword())
                    .with(ArticleBO::setVisitType, article.getVisitType())
                    .with(ArticleBO::setStatus, article.getStatus())
                    .build();

            List<Tags> artTags = tagsBizService.queryTagsByArtOid(artOid);
            List<TagsBO> tagsList = new ArrayList<TagsBO>();
            if (CollectionUtils.isNotEmpty(artTags)) {
                for (Tags tag : artTags) {
                    TagsBO tagsListVO = JwBuilder.of(TagsBO::new)
                            .with(TagsBO::setId, tag.getOid())
                            .with(TagsBO::setName, StringEscapeUtils.escapeHtml4(tag.getContent())).build();
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
                bo.setTagsList(allTagsList);
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
            TempArticle tempArticle = tempArticleTransDao.queryLastestTempArticle(artOid, TempArticlePageEnum.EDIT.getValue());

            if (null != tempArticle) {
                TempArticleInfoBO tempArticleInfoBO = JwBuilder.of(TempArticleInfoBO::new)
                        .with(TempArticleInfoBO::setId, tempArticle.getOid())
                        .with(TempArticleInfoBO::setTitle, StringEscapeUtils.escapeHtml4(tempArticle.getTitle()))
                        .with(TempArticleInfoBO::setAuthor, StringEscapeUtils.escapeHtml4(tempArticle.getAuthor()))
                        .with(TempArticleInfoBO::setContent, tempArticle.getContent())
                        .with(TempArticleInfoBO::setMenuOid, tempArticle.getTypeId())
                        .with(TempArticleInfoBO::setImgSrc, tempArticle.getImgSrc())
                        .with(TempArticleInfoBO::setIsComment, tempArticle.getIsComment())
                        .with(TempArticleInfoBO::setPassword, tempArticle.getPassword())
                        .with(TempArticleInfoBO::setVisitType, tempArticle.getVisitType())
                        .with(TempArticleInfoBO::setStatus, tempArticle.getStatus())
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


                bo.setTempArticleInfo(tempArticleInfoBO);
            }

        } catch (DaoException e) {
            log.error("==>>ArticleBizServiceImpl.queryArticleEditInfo exec failed, e\n", e);
            throw ArticleBizException.QUERY_FAILED_EXCEPTION.format(artOid).print();

        }

        return bo;
    }

    // public static void main(String[] args)
//    {
//        String html="<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
//                + "    <title>Title</title>\n" + "</head>\n" + "<body>\n" + "adfadfadfadfadsfadf\n" + "</body>\n"
//                + "</html>";
//        html=html.replaceAll("\\<.*?>","").replaceAll("\n","");
//        System.out.println(html);
//    }
}