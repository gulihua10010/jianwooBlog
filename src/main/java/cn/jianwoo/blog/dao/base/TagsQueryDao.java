package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface TagsQueryDao {
    Tags queryTagsByPrimaryKey(Long oid) throws DaoException;

    /**
     * 查询所有标签
     *
     * @return
     * @author gulihua
     */
    List<Tags> queryAllTags();

    /**
     * 根据标签名查询标签
     *
     * @param name 标签名
     * @return
     * @author gulihua
     */
    Tags queryTagByName(String name);

}