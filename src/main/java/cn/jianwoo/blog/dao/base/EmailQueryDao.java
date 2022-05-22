package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.entity.query.EmailQuery;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface EmailQueryDao {
    Email queryEmailByPrimaryKey(Long oid) throws DaoException;

    /**
     * 分页查询Email 列表
     *
     * @param query 查询参数
     * @return
     * @author gulihua
     */
    List<Email> queryEmailPageList(EmailQuery query);
}