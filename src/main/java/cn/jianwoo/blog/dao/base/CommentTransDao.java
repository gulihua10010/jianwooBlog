package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.exception.DaoException;

public interface CommentTransDao extends CommentQueryDao {
    void doInsert(Comment record) throws DaoException;


    void doInsertSelective(Comment record) throws DaoException;


    void doUpdateByPrimaryKey(Comment record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Comment record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;

    /**
     * 通过文章oid批量更新评论中的文章信息
     *
     * @param record 评论
     * @param artOid 文章oid
     * @return
     * @author gulihua
     */
    void doUpdateArtInfoByArtOidSelective(Comment record, Long artOid) throws DaoException;
}