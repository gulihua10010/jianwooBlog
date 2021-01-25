package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.ArticleTagsTransDao;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.biz.ArticleBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleParam;
import cn.jianwoo.blog.enums.ArtCommStatusEnum;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.ArticleTagsBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.ArticleBizService;
import cn.jianwoo.blog.service.biz.UidGenService;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TestUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
                .with(Article::setOid, oid)
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
        if (visitType == -1) {
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
        if (visitType == -1) {
            article.setPassword(password);
        }
        article.setUpdateDate(new Date());
        article.setModifiedDate(new Date());
        article.setVisitType(visitType == null ? ArticleVisitEnum.PUBLIC.getValue() : visitType);
        article.setContent(content);
        article.setText(content.replaceAll("\\<.*?>", "").replaceAll("\n", ""));
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
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public void doDeleteArticle(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        try {
            articleTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            throw ArticleBizException.DELETE_FAILED_EXCEPTION.format(oid).print();

        }
    }


    @Override
    public void doRemoveToDraft(Long oid) throws JwBlogException {
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        article.setStatus(ArticleStatusEnum.DRAFT.getValue());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
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
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        article.setStatus(ArticleStatusEnum.PUBLISHED.getValue());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
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
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(artOid).print();

        }

        article.setPraiseCount((article.getPraiseCount() == null ? 0 : article.getPraiseCount()) + 1);
        article.setUpdateDate(new Date());
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
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
                try {
                    this.doRemoveToRecycle(oid);
                } catch (JwBlogException e) {
                    throw ArticleBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
                }
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
                    throw ArticleBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
                }
            }
        }
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