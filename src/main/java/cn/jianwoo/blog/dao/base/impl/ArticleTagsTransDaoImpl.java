package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ArticleTagsTransDao;
import cn.jianwoo.blog.dao.base.mapper.ArticleTagsMapper;
import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.entity.example.ArticleTagsExample;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleTagsTransDao")
public class ArticleTagsTransDaoImpl extends ArticleTagsQueryDaoImpl implements ArticleTagsTransDao {
    @Autowired
    ArticleTagsMapper articleTagsMapper;

    @Override
    public void doInsert(ArticleTags record) throws DaoException {
        int intRlt = articleTagsMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(ArticleTags record) throws DaoException {
        int intRlt = articleTagsMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(ArticleTags record) throws DaoException {
        int updRlt = articleTagsMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(ArticleTags record) throws DaoException {
        int updRlt = articleTagsMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = articleTagsMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByArticleOid(Long artOid) throws DaoException {
        ArticleTagsExample example = new ArticleTagsExample();
        example.createCriteria().andArticleOidEqualTo(artOid);
        int delRlt = articleTagsMapper.deleteByExample(example);
        if (delRlt < 1) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}