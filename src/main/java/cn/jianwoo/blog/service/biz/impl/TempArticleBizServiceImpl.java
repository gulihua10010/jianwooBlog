package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.enums.ArtCommStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.TempArticleBizService;
import cn.jianwoo.blog.service.bo.TempArticleBO;
import cn.jianwoo.blog.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-04-21 10:48
 */
@Service
@Slf4j
public class TempArticleBizServiceImpl implements TempArticleBizService {
    @Autowired
    private TempArticleTransDao tempArticleTransDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSaveTempArticle(TempArticleBO articleBO) throws JwBlogException {

        log.info("==========>Start insert Temp article,title = {}", articleBO.getTitle());
        Date now = DateUtil.getNow();

        articleBO.setIsComment(articleBO.getIsComment() == null ? ArtCommStatusEnum.NO_COMMENT.getValue() : articleBO.getIsComment());
        if (articleBO.getVisitType() == null) {
            articleBO.setVisitType(ArticleVisitEnum.PUBLIC.getValue());
        }
        TempArticle article = JwBuilder.of(TempArticle::new)
                .with(TempArticle::setAuthor, articleBO.getAuthor())
                .with(TempArticle::setTitle, articleBO.getTitle())
                .with(TempArticle::setTypeId, articleBO.getTypeId())
                .with(TempArticle::setStatus, TempArticleStatusEnum.TEMP.getValue())
                .with(TempArticle::setIsComment, articleBO.getIsComment())
                .with(TempArticle::setImgSrc, articleBO.getImgSrc())
                .with(TempArticle::setVisitType, articleBO.getVisitType())
                .with(TempArticle::setContent, articleBO.getContent())
                .with(TempArticle::setOldOid, articleBO.getOldOid())
                .with(TempArticle::setPage, articleBO.getPage())
                .with(TempArticle::setCreateDate, now)
                .with(TempArticle::setUpdateDate, now)
                .build();

        if (ArticleVisitEnum.PASSWORD.getValue().equals(articleBO.getVisitType())) {
            article.setPassword(articleBO.getPassword());
        }
        if (CollectionUtils.isNotEmpty(articleBO.getTags())) {
            article.setTags(JSON.toJSONString(articleBO.getTags()));
        } else {
            article.setTags(JSON.toJSONString(new JSONArray()));

        }

//        if (article.getOldOid()!=null)
//        {
//            article.setRestoreOid(article.getOldOid());
//        }
        if (articleBO.getOid() != null) {
            try {
                article.setOid(articleBO.getOid());
                tempArticleTransDao.doUpdateByPrimaryKeySelective(article);
            } catch (DaoException e) {
                log.error("TempArticleBizServiceImpl.doSaveTempArticle exec failed, e:\n", e);
                throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getTitle()).print();
            }
        } else {
            try {
                tempArticleTransDao.doInsert(article);
            } catch (DaoException e) {
                log.error("TempArticleBizServiceImpl.doSaveTempArticle exec failed, e:\n", e);
                throw ArticleBizException.CREATE_FAILED_EXCEPTION.format(articleBO.getTitle()).print();
            }
        }


        log.info("==========>Insert Temp article successfully,title = {}", articleBO.getTitle());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateTempArticle(TempArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start update Temp article,title = {}", articleBO.getTitle());

        Date now = DateUtil.getNow();
        TempArticle article = null;
        try {
            article = tempArticleTransDao.queryTempArticleByPrimaryKey(articleBO.getOid());
        } catch (DaoException e) {
            log.error("TempArticleBizServiceImpl.doUpdateTempArticle exec failed, e:\n", e);
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
        article.setVisitType(articleBO.getVisitType() == null ? ArticleVisitEnum.PUBLIC.getValue() : articleBO.getVisitType());
        article.setContent(articleBO.getContent());
        if (articleBO.getStatus() != null) {
            article.setStatus(articleBO.getStatus());
        }
        if (CollectionUtils.isNotEmpty(articleBO.getTags())) {
            article.setTags(JSON.toJSONString(articleBO.getTags()));
        }
        try {
            tempArticleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("TempArticleBizServiceImpl.doUpdateTempArticle exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getOid()).print();
        }

        log.info("==========>Update Temp article successfully,title = {}", articleBO.getTitle());
    }

    @Override
    public void doUpdateTempArticleStatus(Long oid, TempArticleStatusEnum status, Long restoreOid) throws JwBlogException {
        TempArticle article = new TempArticle();
        article.setOid(oid);
        article.setStatus(status.getValue());
        article.setUpdateDate(new Date());
        if (TempArticleStatusEnum.RESTORE.equals(status)) {
            article.setRestoreOid(restoreOid);
        }
        try {
            tempArticleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("TempArticleBizServiceImpl.doUpdateTempArticleStatus exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }
}