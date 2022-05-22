package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.ArticleAccessBizDao;
import cn.jianwoo.blog.dao.biz.mapper.ArticleAccessBizMapper;
import cn.jianwoo.blog.entity.extension.AccessExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 18:18
 */
@Service
public class ArticleAccessBizDaoImpl implements ArticleAccessBizDao {
    @Autowired
    private ArticleAccessBizMapper articleAccessBizMapper;

    @Override
    public List<AccessExt> queryRecentAccess(Integer limit) {
        return articleAccessBizMapper.selectRecentAccess(limit);
    }


    @Override
    public List<AccessExt> queryRecentAccess() {
        return articleAccessBizMapper.selectRecentAccess(null);
    }
}
