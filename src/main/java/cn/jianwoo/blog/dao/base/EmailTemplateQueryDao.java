package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.query.EmailTplQuery;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface EmailTemplateQueryDao {
    EmailTemplate queryEmailTemplateByPrimaryKey(String code) throws DaoException;

    /**
     * 查询所有有效的模板
     *
     * @return
     * @author gulihua
     */
    List<EmailTemplate> queryAllEffectiveEmailTplList(EmailTplQuery query);

    /**
     * 根据code查询模板
     *
     * @return
     * @author gulihua
     */
    EmailTemplate queryEmailTplByCode(String code);
}