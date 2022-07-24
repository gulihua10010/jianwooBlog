package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.exception.DaoException;

public interface ArticleTagsTransDao extends ArticleTagsQueryDao {
    void doInsert(ArticleTags record) throws DaoException;


    void doInsertSelective(ArticleTags record) throws DaoException;


    void doUpdateByPrimaryKey(ArticleTags record) throws DaoException;


    void doUpdateByPrimaryKeySelective(ArticleTags record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;


    /**
     * 根据文章oid删除 文章-标签
     *
     * @param %param name% %param description%
     * @return
     * @author gulihua
     */
    void doDeleteByArticleOid(Long artOid);

    /**
     * 根据标签oid删除 文章-标签
     *
     * @param %param name% %param description%
     * @return
     * @author gulihua
     */
    void doDeleteByTagsOid(Long tagsOid);

    /**
     * 根据文章oid更新 文章-标签
     *
     * @param %param name% %param description%
     * @return
     * @author gulihua
     */
    void doUpdateByArticleOid(ArticleTags record, Long artOid);
}