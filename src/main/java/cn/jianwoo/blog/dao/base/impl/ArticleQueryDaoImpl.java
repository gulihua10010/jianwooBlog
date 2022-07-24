package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ArticleQueryDao;
import cn.jianwoo.blog.dao.base.mapper.ArticleMapper;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.entity.example.ArticleExample;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("articleQueryDao")
public class ArticleQueryDaoImpl implements ArticleQueryDao {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public ArticleWithBLOBs queryArticleByPrimaryKey(Long oid) throws DaoException {
        ArticleWithBLOBs record = articleMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<Article> queryArticleByStatus(String status) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andStatusEqualTo(status);
        return articleMapper.selectByExample(example);
    }

    @Override
    public List<Article> queryArticleByStatusAndAccessType(List<String> statusList, List<String> accessTypeList) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andStatusIn(statusList).andAccessTypeIn(accessTypeList);
        return articleMapper.selectByExample(example);
    }


    @Override
    public List<Article> queryArticleByCategory(Integer categoryId) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        return articleMapper.selectByExample(example);
    }

    @Override
    public Article queryArticleByOid(Long oid) throws DaoException {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andOidEqualTo(oid).andStatusNotEqualTo(ArticleStatusEnum.DELETE.getValue());
        List<Article> list = articleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return list.get(0);
    }

    @Override
    public ArticleWithBLOBs queryArticleByOidWithBLOBs(Long oid) throws DaoException {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andOidEqualTo(oid).andStatusNotEqualTo(ArticleStatusEnum.DELETE.getValue());
        List<ArticleWithBLOBs> list = articleMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isEmpty(list)) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return list.get(0);
    }
}