package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ArticleQueryDao;
import cn.jianwoo.blog.dao.base.mapper.ArticleMapper;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.example.ArticleExample;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleQueryDao")
public class ArticleQueryDaoImpl implements ArticleQueryDao {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Article queryArticleByPrimaryKey(Long oid) throws DaoException {
        Article record = articleMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<Article> queryArticleByStatus(Integer status) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andStatusEqualTo(status);
        return articleMapper.selectByExample(example);
    }


    @Override
    public List<Article> queryArticleByType(Integer typeId) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andTypeIdEqualTo(typeId);
        return articleMapper.selectByExample(example);
    }
}