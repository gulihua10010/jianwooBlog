package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.dao.base.ArticleTagsTransDao;
import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.dao.biz.TagsBizDao;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.extension.ArticleTagsExt;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.TagsBizException;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.validation.BizValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TagsBizServiceImpl implements TagsBizService {
    @Autowired
    TagsTransDao tagsTransDao;
    @Autowired
    ArticleTagsTransDao articleTagsTransDao;
    @Autowired
    TagsBizDao tagsBizDao;

    @Override
    public void doAddTags(String name) throws JwBlogException {
        BizValidation.paramValidate(name, "name");
        Tags oldTags = tagsTransDao.queryTagByName(name);
        if (null != oldTags) {
            throw TagsBizException.HAS_EXIST_EXCEPTION_CN.format(name).print();
        }
        Tags tags = new Tags();
        tags.setContent(name);
        tags.setCreateDate(new Date());
        tags.setUpdateDate(new Date());
        try {
            tagsTransDao.doInsert(tags);
        } catch (DaoException e) {
            throw TagsBizException.CREATE_FAILED_EXCEPTION.format(name).print();
        }

    }


    @Override
    public List<Tags> queryTagsByArtOid(Long artOid) {
        List<ArticleTagsExt> articleTagss = tagsBizDao.queryTagsByArtOid(artOid);

        List<Tags> tags = new ArrayList<>();
        for (ArticleTagsExt articleTagsExt : articleTagss) {
            Tags tag = new Tags();
            BeanUtils.copyProperties(articleTagsExt, tag);
            tags.add(tag);
        }
        return tags;
    }


    @Override
    public String queryTagNameByOid(Long oid) throws JwBlogException {
        Tags tags = null;
        try {
            tags = tagsTransDao.queryTagsByPrimaryKey(oid);
        } catch (DaoException e) {
            throw TagsBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }

        return tags.getContent();
    }


    @Override
    public void doRemoveTags(Long oid) throws JwBlogException {
        try {
            tagsTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            throw TagsBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public Integer countAllTags() {
        return tagsBizDao.countAllTags();
    }
}