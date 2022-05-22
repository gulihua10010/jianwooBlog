package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ArticleAccessQueryDao;
import cn.jianwoo.blog.dao.base.mapper.ArticleAccessMapper;
import cn.jianwoo.blog.entity.ArticleAccess;
import cn.jianwoo.blog.entity.example.ArticleAccessExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleAccessQueryDao")
public class ArticleAccessQueryDaoImpl implements ArticleAccessQueryDao {
    @Autowired
    ArticleAccessMapper articleAccessMapper;

    @Override
    public ArticleAccess queryArticleAccessByPrimaryKey(Long oid) throws DaoException {
        ArticleAccess record = articleAccessMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public ArticleAccess queryArticleAccessByArtOidAndIp(Long artOid, String ip) {
        ArticleAccessExample example = new ArticleAccessExample();
        example.createCriteria().andArticleOidEqualTo(artOid).andAccessIpEqualTo(ip);
        List<ArticleAccess> list = articleAccessMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}