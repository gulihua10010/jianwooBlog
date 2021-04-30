package cn.jianwoo.blog.service.biz.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.ArticleTagsTransDao;
import cn.jianwoo.blog.dao.base.TagsTransDao;
import cn.jianwoo.blog.dao.biz.TagsBizDao;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.extension.ArticleTagsExt;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.TagsBizException;
import cn.jianwoo.blog.service.biz.TagsBizService;
import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagsBizServiceImpl implements TagsBizService {
    @Autowired
    TagsTransDao tagsTransDao;
    @Autowired
    ArticleTagsTransDao articleTagsTransDao;
    @Autowired
    TagsBizDao tagsBizDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddTag(String name) throws JwBlogException {
        Tags oldTags = tagsTransDao.queryTagByName(name);
        if (null != oldTags) {
            throw TagsBizException.HAS_EXIST_EXCEPTION_CN.format(name).print();
        }
        Date now = DateUtil.getNow();

        Tags tags = JwBuilder.of(Tags::new)
                .with(Tags::setContent, name)
                .with(Tags::setCreateDate, now)
                .with(Tags::setUpdateDate, now).build();

        try {
            tagsTransDao.doInsert(tags);
        } catch (DaoException e) {
            log.error("TagsBizServiceImpl.doAddTag exec failed, e:\n", e);
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
            log.error("TagsBizServiceImpl.queryTagNameByOid exec failed, e:\n", e);
            throw TagsBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }

        return tags.getContent();
    }


    @Override
    public void doRemoveTags(Long oid) throws JwBlogException {
        articleTagsTransDao.doDeleteByTagsOid(oid);
        try {
            tagsTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("TagsBizServiceImpl.doRemoveTags exec failed, e:\n", e);
            throw TagsBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public Integer countAllTags() {
        return tagsBizDao.countAllTags();
    }

    @Override
    public void doUpdateTags(String name, Long oid) throws JwBlogException {
        Tags tags = new Tags();
        tags.setOid(oid);
        tags.setContent(name);
        tags.setUpdateDate(new Date());
        try {
            tagsTransDao.doUpdateByPrimaryKeySelective(tags);
        } catch (DaoException e) {
            log.error("TagsBizServiceImpl.doUpdateTags exec failed, e:\n", e);
            throw TagsBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddTagList(List<String> tagList) throws JwBlogException {
        tagList = tagList.stream().distinct().collect(Collectors.toList());
        List<String> existTags = new ArrayList<>();
        for (String tagName : tagList) {
            Tags oldTags = tagsTransDao.queryTagByName(tagName);
            if (null != oldTags) {
                existTags.add(tagName);
            }
        }
        if (CollectionUtil.isNotEmpty(existTags)) {
            throw TagsBizException.HAS_EXIST_EXCEPTION_LIST_CN.format(existTags).print();

        }
        for (String tagName : tagList) {
            Tags tags = new Tags();
            tags.setContent(tagName.trim());
            tags.setCreateDate(new Date());
            tags.setUpdateDate(new Date());
            try {
                tagsTransDao.doInsert(tags);
            } catch (DaoException e) {
                log.error("TagsBizServiceImpl.doAddTagList exec failed, e:\n", e);
                throw TagsBizException.CREATE_FAILED_EXCEPTION.format(tagName).print();
            }
        }


    }
}