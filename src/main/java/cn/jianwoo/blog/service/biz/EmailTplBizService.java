package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.query.EmailTplParam;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.EmailTplBO;
import com.github.pagehelper.PageInfo;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:22
 */
public interface EmailTplBizService {

    /**
     * 获取所有邮件模板
     *
     * @param param 参数
     * @return PageInfo<EmailTemplate>
     * @author gulihua
     */
    PageInfo<EmailTemplate> queryAllEmailTplPage(EmailTplParam param);

    /**
     * 添加邮件模板
     *
     * @param param 参数
     * @return PageInfo<EmailTemplate>
     * @author gulihua
     */
    void doAddEmailTpl(EmailTplBO param) throws JwBlogException;

    /**
     * 更新邮件模板
     *
     * @param param 参数
     * @author gulihua
     */
    void doUpdateEmailTpl(EmailTplBO param) throws JwBlogException;

    /**
     * 删除邮件模板
     *
     * @param oid OID
     * @author gulihua
     */
    void doRemoveEmailTpl(Long oid) throws JwBlogException;

    /**
     * 获取所有邮件模板
     *
     * @param oid OID
     * @return EmailTplBO
     * @author gulihua
     */
    EmailTplBO queryEmailTplByOid(Long oid) throws JwBlogException;
}
