package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.TagsBizDao;
import cn.jianwoo.blog.dao.biz.mapper.TagsBizMapper;
import cn.jianwoo.blog.entity.extension.ArticleTagsExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 18:18
 */
@Service
public class TagsBizDaoImpl implements TagsBizDao {
    @Autowired
    private TagsBizMapper tagsBizMapper;

    @Override
    public List<ArticleTagsExt> queryTagsByArtOid(Long artOid) {
        return tagsBizMapper.selectTagsByArtOid(artOid);
    }


    @Override
    public Integer countAllTags() {
        return tagsBizMapper.countAllTags();
    }

    @Override
    public List<ArticleTagsExt> queryAllTags(boolean isContainPrivate) {
        return tagsBizMapper.selectAllTags(isContainPrivate);
    }

}
