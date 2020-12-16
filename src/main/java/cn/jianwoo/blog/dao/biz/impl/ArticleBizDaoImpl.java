package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.ArticleBizDao;
import cn.jianwoo.blog.dao.biz.mapper.ArticleBizMapper;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleParam;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
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
    public int countArtsByStatus(int[] status) {
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
    public List<ArticleExt> queryEffectiveArticleList(ArticleParam param) {
        List<Integer> status = new ArrayList<>();
        status.add(ArticleStatusEnum.PUBLISHED.getValue());
        status.add(ArticleStatusEnum.DRAFT.getValue());
        param.setStatusParams(status);
        return articleBizMapper.selectArticleListByStatus(param);

    }


    @Override
    public List<ArticleExt> queryRecycleBinArticleList(ArticleParam param) {
        List<Integer> status = new ArrayList<>();
        status.add(ArticleStatusEnum.RECYCLE.getValue());
        param.setStatusParams(status);
        return articleBizMapper.selectArticleListByStatus(param);
    }
}
