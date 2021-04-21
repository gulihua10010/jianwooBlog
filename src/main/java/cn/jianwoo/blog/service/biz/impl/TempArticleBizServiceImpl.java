package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.enums.ArtCommStatusEnum;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.ArticleVisitEnum;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.TempArticleBizService;
import cn.jianwoo.blog.service.bo.ArticleBO;
import cn.jianwoo.blog.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
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
    public void doSaveTempArticle(ArticleBO articleBO) throws JwBlogException {

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
        if (articleBO.getTags() != null) {
            JSONArray tags = new JSONArray();
            for (Integer t : articleBO.getTags()) {
                tags.add(t);
            }
            article.setTags(tags.toJSONString());
        }
        try {
            tempArticleTransDao.doInsert(article);
        } catch (DaoException e) {
            throw ArticleBizException.CREATE_FAILED_EXCEPTION.format(articleBO.getTitle()).print();
        }


        log.info("==========>Insert Temp article successfully,title = {}", articleBO.getTitle());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateTempArticle(ArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start update Temp article,title = {}", articleBO.getTitle());

        Date now = DateUtil.getNow();
        TempArticle article = null;
        try {
            article = tempArticleTransDao.queryTempArticleByPrimaryKey(articleBO.getOid());
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
        article.setVisitType(articleBO.getVisitType() == null ? ArticleVisitEnum.PUBLIC.getValue() : articleBO.getVisitType());
        article.setContent(articleBO.getContent());
        if (articleBO.getStatus() != null) {
            article.setStatus(articleBO.getStatus());
        }
        if (articleBO.getTags() != null) {
            JSONArray tags = new JSONArray();
            for (Integer t : articleBO.getTags()) {
                tags.add(t);
            }
            article.setTags(tags.toJSONString());
        }
        try {
            tempArticleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getOid()).print();
        }

        log.info("==========>Update Temp article successfully,title = {}", articleBO.getTitle());
    }

    @Override
    public void doUpdateTempArticleStatus(Long oid, ArticleStatusEnum status) throws JwBlogException {
        TempArticle article = new TempArticle();
        article.setOid(oid);
        article.setStatus(status.getValue());
        try {
            tempArticleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }
}
