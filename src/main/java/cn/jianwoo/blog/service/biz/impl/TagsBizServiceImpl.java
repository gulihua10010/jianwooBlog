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
import cn.jianwoo.blog.service.bo.TagsBO;
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
                .with(Tags::setCreateTime, now)
                .with(Tags::setUpdateTime, now).build();

        try {
            tagsTransDao.doInsert(tags);
        } catch (DaoException e) {
            log.error("TagsBizServiceImpl.doAddTag exec failed, e:\n", e);
            throw TagsBizException.CREATE_FAILED_EXCEPTION.format(name).print();
        }

    }


    @Override
    public List<TagsBO> queryTagsByArtOid(Long artOid) {
        List<ArticleTagsExt> articleTagss = tagsBizDao.queryTagsByArtOid(artOid);

        List<TagsBO> tags = new ArrayList<>();
        for (ArticleTagsExt articleTagsExt : articleTagss) {
            TagsBO tag = new TagsBO();
            tag.setId(articleTagsExt.getOid());
            tag.setName(articleTagsExt.getContent());
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateTags(String name, Long oid) throws JwBlogException {
        Tags tags = new Tags();
        tags.setOid(oid);
        tags.setContent(name);
        tags.setUpdateTime(DateUtil.getNow());
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
        Date now = DateUtil.getNow();
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
            tags.setCreateTime(now);
            tags.setUpdateTime(now);
            try {
                tagsTransDao.doInsertSelective(tags);
            } catch (DaoException e) {
                log.error("TagsBizServiceImpl.doAddTagList exec failed, e:\n", e);
                throw TagsBizException.CREATE_FAILED_EXCEPTION.format(tagName).print();
            }
        }


    }

    @Override
    public List<TagsBO> queryAllTags() {
        List<Tags> tagsList = tagsTransDao.queryAllTags();
        List<TagsBO> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(tagsList)) {
            tagsList.forEach(o -> {
                TagsBO tagsBO = new TagsBO();
                tagsBO.setId(o.getOid());
                tagsBO.setName(o.getContent());
                list.add(tagsBO);
            });

        }
        return list;
    }

    @Override
    public TagsBO queryTagsByOid(Long oid) throws JwBlogException {
        Tags tags;
        try {
            tags = tagsTransDao.queryTagsByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error(">>Query Tags by oid {} failed, e\r\n{}", oid, e);
            throw TagsBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        TagsBO tagsBO = new TagsBO();
        tagsBO.setId(tags.getOid());
        tagsBO.setName(tags.getContent());
        return tagsBO;
    }
}