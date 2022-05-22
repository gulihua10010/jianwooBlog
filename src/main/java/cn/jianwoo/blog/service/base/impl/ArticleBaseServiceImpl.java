package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.ArticleBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-13 19:59
 */
@Service
@Slf4j
public class ArticleBaseServiceImpl implements ArticleBaseService {
    @Autowired
    private ArticleTransDao articleTransDao;

    @Override
    public Article queryArticleByOid(Long artOid) throws JwBlogException {
        try {
            return articleTransDao.queryArticleByOid(artOid);
        } catch (DaoException e) {
            log.error("ArticleBaseService.queryArticleByOid exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(artOid).print();

        }
    }

    @Override
    public ArticleWithBLOBs queryArticleByOidWithBLOBs(Long artOid) throws JwBlogException {
        try {
            return articleTransDao.queryArticleByOidWithBLOBs(artOid);
        } catch (DaoException e) {
            log.error("ArticleBaseService.queryArticleByOidWithBLOBs exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION_CN.format(artOid).print();

        }
    }
}
