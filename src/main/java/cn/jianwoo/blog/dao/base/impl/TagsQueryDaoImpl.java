package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.TagsQueryDao;
import cn.jianwoo.blog.dao.base.mapper.TagsMapper;
import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.entity.example.TagsExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagsQueryDao")
public class TagsQueryDaoImpl implements TagsQueryDao {
    @Autowired
    TagsMapper tagsMapper;

    @Override
    public Tags queryTagsByPrimaryKey(Long oid) throws DaoException {
        Tags record = tagsMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public List<Tags> queryAllTags() {
        TagsExample example = new TagsExample();
        return tagsMapper.selectByExample(example);
    }


    @Override
    public Tags queryTagByName(String name) {
        TagsExample example = new TagsExample();
        example.createCriteria().andContentEqualTo(name);
        List<Tags> list = tagsMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}