package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ArticleTagsQueryDao;
import cn.jianwoo.blog.dao.base.mapper.ArticleTagsMapper;
import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.entity.example.ArticleTagsExample;
import cn.jianwoo.blog.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleTagsQueryDao")
public class ArticleTagsQueryDaoImpl implements ArticleTagsQueryDao {

    public static final Logger logger = LoggerFactory.getLogger(ArticleTagsQueryDaoImpl.class);
    @Autowired
    ArticleTagsMapper articleTagsMapper;

    @Override
    public ArticleTags queryArticleTagsByPrimaryKey(Long oid) throws DaoException {
        ArticleTags record = articleTagsMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<ArticleTags> queryTagsByArtOid(Long artOid) {
        ArticleTagsExample example = new ArticleTagsExample();
        example.createCriteria().andArticleOidEqualTo(artOid);
        return articleTagsMapper.selectByExample(example);
    }
}