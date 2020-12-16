package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface TagsQueryDao {
    Tags queryTagsByPrimaryKey(Long oid) throws DaoException;


    List<Tags> queryAllTags();


    Tags queryTagByName(String name);

}