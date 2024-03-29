package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.ArticleBizDao;
import cn.jianwoo.blog.dao.biz.mapper.ArticleBizMapper;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleQuery;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.ArticleBO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 18:10
 */
@Service
public class ArticleBizDaoImpl implements ArticleBizDao {
    @Autowired
    private ArticleBizMapper articleBizMapper;

    @Override
    public int countArtsByStatus(String[] status) {
        return articleBizMapper.countArtsByStatus(status);
    }


    @Override
    public List<Article> queryArticleByStatusAndLimit(Map<String, Object> paramMap) {
        return articleBizMapper.selectArticleByStatusAndLimit(paramMap);
    }


    @Override
    public List<Article> queryPublishedArtsByKeyword(String keyword) {
        return articleBizMapper.selectPublishedArtsByKeyword(keyword);

    }


    @Override
    public List<Article> queryPublishedNewestArts(Integer limit) {
        return articleBizMapper.selectPublishedNewestArts(limit);
    }


    @Override
    public List<Article> queryPublishedHotArts(Integer limit) {
        return articleBizMapper.selectPublishedHotArts(limit);

    }


    @Override
    public List<ArticleExt> queryEffectiveArticleList(ArticleQuery param) {
        if (CollectionUtils.isEmpty(param.getStatusParams())) {
            List<String> status = new ArrayList<>();
            status.add(ArticleStatusEnum.PUBLISHED.getValue());
            status.add(ArticleStatusEnum.DRAFT.getValue());
            param.setStatusParams(status);
        }

        return articleBizMapper.selectArticleListByStatus(param);

    }


    @Override
    public List<ArticleExt> queryRecycleBinArticleList(ArticleQuery param) {
        List<String> status = new ArrayList<>();
        status.add(ArticleStatusEnum.RECYCLE.getValue());
        param.setStatusParams(status);
        return articleBizMapper.selectArticleListByStatus(param);
    }

    @Override
    public void doUpdateArticleCommentCnt(Long artOid) throws DaoException {
        int updRlt = articleBizMapper.updateArticleCommentCnt(artOid);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateArticlePraiseCnt(Long artOid) throws DaoException {
        int updRlt = articleBizMapper.updateArticlePraiseCnt(artOid);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doRestoreFromRecycle(Article record) throws DaoException {
        int updRlt = articleBizMapper.restoreFromRecycle(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public List<ArticleExt> queryArticleListMain(ArticleQuery param, boolean isPrivate, String currentIp) {
        return articleBizMapper.selectArticleListMain(param, isPrivate, currentIp);
    }

    @Override
    public Long queryArticleListMainCount(ArticleQuery param, boolean isPrivate) {
        return articleBizMapper.selectArticleListMainCount(param, isPrivate);
    }

    @Override
    public List<ArticleExt> queryMonthDatePublishList(ArticleQuery param, boolean isPrivate) {
        return articleBizMapper.selectMonthDatePublishList(param, isPrivate);

    }

    @Override
    public Integer queryMonthDatePublishCount(ArticleQuery param, boolean isPrivate) {
        return articleBizMapper.selectMonthDatePublishCount(param, isPrivate);

    }

    @Override
    public List<ArticleExt> queryRecommendArticleByArtOid(Long oid) {
        return articleBizMapper.selectRecommendArticleByArtOid(oid);
    }

    @Override
    public List<ArticleExt> queryRecommendArticleByCategoryOid(Integer categoryOid, Long artOid) {
        return articleBizMapper.selectRecommendArticleByCategoryOid(categoryOid, artOid);
    }

    @Override
    public void doUpdateArticleCommTotalCnt(Long oid, String optType) throws DaoException {
        int updRlt = articleBizMapper.updateArticleCommTotalCnt(oid, optType);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }

    }


}
