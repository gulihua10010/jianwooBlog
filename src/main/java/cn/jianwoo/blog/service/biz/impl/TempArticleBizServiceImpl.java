package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.TempArticleTransDao;
import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.enums.ArticleAccessEnum;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
import cn.jianwoo.blog.enums.TopPlaceEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.TempArticleBizException;
import cn.jianwoo.blog.service.biz.TempArticleBizService;
import cn.jianwoo.blog.service.bo.TagsBO;
import cn.jianwoo.blog.service.bo.TempArticleBO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
    public Long doSaveTempArticle(TempArticleBO articleBO) throws JwBlogException {

        log.info("==========>Start insert Temp article,title = {}", articleBO.getTitle());
        Long oid = articleBO.getOid();
        Date now = DateUtil.getNow();
        if (articleBO.getOid() != null) {
            TempArticle tempArticle = null;
            try {
                tempArticle = tempArticleTransDao.queryTempArticleByPrimaryKey(articleBO.getOid());
            } catch (DaoException e) {
                throw TempArticleBizException.NOT_EXIST_EXCEPTION.format(articleBO.getOid()).print();
            }
            if (TempArticleStatusEnum.VOID.getValue().equals(tempArticle.getStatus())) {
                throw TempArticleBizException.STATUS_VOID_CN.format(articleBO.getOid()).print();
            } else if (TempArticleStatusEnum.RESTORE.getValue().equals(tempArticle.getStatus())) {
                throw TempArticleBizException.STATUS_RESTORE_CN.format(articleBO.getOid()).print();
            }
        }
        articleBO.setIsComment(articleBO.getIsComment() != null && articleBO.getIsComment());
        articleBO.setFlagOriginal(articleBO.getFlagOriginal() == null || articleBO.getFlagOriginal());
        if (articleBO.getAccessType() == null) {
            articleBO.setAccessType(ArticleAccessEnum.PUBLIC.getValue());
        }
        TempArticle article = JwBuilder.of(TempArticle::new)
                .with(TempArticle::setAuthor, articleBO.getAuthor())
                .with(TempArticle::setTitle, articleBO.getTitle())
                .with(TempArticle::setCategoryId, articleBO.getCategoryId())
                .with(TempArticle::setStatus, TempArticleStatusEnum.TEMP.getValue())
                .with(TempArticle::setIsComment, articleBO.getIsComment())
                .with(TempArticle::setFlagOriginal, articleBO.getFlagOriginal())
                .with(TempArticle::setOriginalUrl, articleBO.getOriginalUrl())
                .with(TempArticle::setImgSrc, articleBO.getImgSrc())
                .with(TempArticle::setAccessType, articleBO.getAccessType())
                .with(TempArticle::setTopPlaceStatus, articleBO.getTopPlaceStatus())
                .with(TempArticle::setContent, articleBO.getContent())
                .with(TempArticle::setOldArticleOid, articleBO.getOldArticleOid())
                .with(TempArticle::setPageType, articleBO.getPageType())
                .with(TempArticle::setUpdateTime, now)
                .build();

        if (ArticleAccessEnum.PASSWORD.getValue().equals(articleBO.getAccessType())) {
            article.setPassword(JwUtil.encrypt(articleBO.getPassword()));
        }
        if (CollectionUtils.isNotEmpty(articleBO.getArtTagsList())) {
            article.setTags(JSON.toJSONString(articleBO.getArtTagsList()));
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
                throw TempArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getTitle()).print();
            }
        } else {
            try {
                article.setCreateTime(now);
                tempArticleTransDao.doInsertSelective(article);
                oid = article.getOid();
            } catch (DaoException e) {
                log.error("TempArticleBizServiceImpl.doSaveTempArticle exec failed, e:\n", e);
                throw TempArticleBizException.CREATE_FAILED_EXCEPTION.format(articleBO.getTitle()).print();
            }
        }


        log.info("==========>Insert Temp article successfully,title = {}", articleBO.getTitle());
        return oid;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateTempArticle(TempArticleBO articleBO) throws JwBlogException {
        log.info("==========>Start update Temp article,title = {}", articleBO.getTitle());
        TempArticle tempArticle = null;
        try {
            tempArticle = tempArticleTransDao.queryTempArticleByPrimaryKey(articleBO.getOid());
        } catch (DaoException e) {
            throw TempArticleBizException.NOT_EXIST_EXCEPTION.format(articleBO.getOid()).print();
        }
        if (TempArticleStatusEnum.VOID.getValue().equals(tempArticle.getStatus())) {
            throw TempArticleBizException.STATUS_VOID_CN.format(articleBO.getOid()).print();
        } else if (TempArticleStatusEnum.RESTORE.getValue().equals(tempArticle.getStatus())) {
            throw TempArticleBizException.STATUS_RESTORE_CN.format(articleBO.getOid()).print();
        }

        Date now = DateUtil.getNow();
        TempArticle article = new TempArticle();
        article.setOid(articleBO.getOid());
        article.setAuthor(articleBO.getAuthor());
        article.setTitle(articleBO.getTitle());
        article.setCategoryId(articleBO.getCategoryId());
        article.setIsComment(articleBO.getIsComment() != null && articleBO.getIsComment());
        article.setFlagOriginal(articleBO.getFlagOriginal() == null || articleBO.getFlagOriginal());
        article.setImgSrc(articleBO.getImgSrc());
        article.setOriginalUrl(articleBO.getOriginalUrl());
        if (ArticleAccessEnum.PASSWORD.getValue().equals(articleBO.getAccessType())) {
            article.setPassword(JwUtil.encrypt(articleBO.getPassword()));
        }
        article.setUpdateTime(now);
        article.setAccessType(articleBO.getAccessType() == null ? ArticleAccessEnum.PUBLIC.getValue() : articleBO.getAccessType());
        article.setContent(articleBO.getContent());
        article.setTopPlaceStatus(articleBO.getTopPlaceStatus());
        if (articleBO.getStatus() != null) {
            article.setStatus(articleBO.getStatus());
        }
        if (CollectionUtils.isNotEmpty(articleBO.getArtTagsList())) {
            article.setTags(JSON.toJSONString(articleBO.getArtTagsList()));
        }
        try {
            tempArticleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("TempArticleBizServiceImpl.doUpdateTempArticle exec failed, e:\n", e);
            throw TempArticleBizException.MODIFY_FAILED_EXCEPTION.format(articleBO.getOid()).print();
        }

        log.info("==========>Update Temp article successfully,title = {}", articleBO.getTitle());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateTempArticleStatus(Long oid, TempArticleStatusEnum status, Long restoreOid) throws JwBlogException {
        TempArticle tempArticle = null;
        try {
            tempArticle = tempArticleTransDao.queryTempArticleByPrimaryKey(oid);
        } catch (DaoException e) {
            throw TempArticleBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        if (TempArticleStatusEnum.RESTORE.equals(status) || TempArticleStatusEnum.VOID.equals(status)) {
            if (TempArticleStatusEnum.VOID.getValue().equals(tempArticle.getStatus())) {
                throw TempArticleBizException.STATUS_VOID_CN.format(oid).print();
            } else if (TempArticleStatusEnum.RESTORE.getValue().equals(tempArticle.getStatus())) {
                throw TempArticleBizException.STATUS_RESTORE_CN.format(oid).print();
            }
        }
        TempArticle article = new TempArticle();
        article.setOid(oid);
        article.setStatus(status.getValue());
        article.setUpdateTime(DateUtil.getNow());
        if (TempArticleStatusEnum.RESTORE.equals(status)) {
            article.setRestoreArticleOid(restoreOid);
        }
        try {
            tempArticleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("TempArticleBizServiceImpl.doUpdateTempArticleStatus exec failed, e:\n", e);
            throw TempArticleBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TempArticleBO queryLastestTempArticle(Long oldArticleOid, String pageType) {
        TempArticle article = tempArticleTransDao.queryLastestTempArticle(oldArticleOid, pageType);
        if (null == article) {
            return null;
        }
        TempArticleBO tempArticleBO = new TempArticleBO();
        BeanUtils.copyProperties(article, tempArticleBO);
        tempArticleBO.setFlagTop(TopPlaceEnum.TOP.getValue().equals(article.getTopPlaceStatus()));
        tempArticleBO.setArtTagsList(JSONArray.parseArray(article.getTags(), TagsBO.class));
        return tempArticleBO;
    }
}
