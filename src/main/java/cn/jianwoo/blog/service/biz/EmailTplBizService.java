package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.EmailTplBO;
import cn.jianwoo.blog.service.param.EmailTplParam;
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
    PageInfo<EmailTplBO> queryAllEmailTplPage(EmailTplParam param);

    /**
     * 添加邮件模板
     *
     * @param param 参数
     * @author gulihua
     */
    void doCreateEmailTpl(EmailTplBO param) throws JwBlogException;

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
     * @param code 模板编码
     * @author gulihua
     */
    void doRemoveEmailTpl(String code) throws JwBlogException;

    /**
     * 获取所有邮件模板
     *
     * @param code 模板编码
     * @return EmailTplBO
     * @author gulihua
     */
    EmailTplBO queryEmailTplByCode(String code) throws JwBlogException;


    /**
     * 渲染邮件模板
     *
     * @param content 内容
     * @param param   JSON参数
     * @author gulihua
     */
    String doRenderEmailTpl(String content, String param) throws JwBlogException;
}
