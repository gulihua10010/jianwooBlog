package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.MessageTemplate;
import cn.jianwoo.blog.exception.DaoException;

public interface MessageTemplateQueryDao {
    MessageTemplate queryMessageTemplateByPrimaryKey(String busiSceneCode) throws DaoException;

    /***
     * 根据业务场景编码查询模板
     * @param busiSceneCode 业务场景编码
     * @return
     * @throws DaoException
     */
    MessageTemplate queryMessageTemplateByBusiSceneCode(String busiSceneCode) throws DaoException;
}