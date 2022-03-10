package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-08 11:39
 */
public interface LoginFailedBizService {

    /**
     * 保存登录失败记录
     *
     * @param loginId 登录ID
     * @param ip      登录ip地址
     * @return
     * @author gulihua
     */
    void doSaveRecord(String loginId, String ip) throws JwBlogException;

    /**
     * 查询是否冻结
     *
     * @param loginId 登录ID
     * @param ip      登录ip地址
     * @return
     * @author gulihua
     */
    boolean queryIsBlock(String loginId, String ip) throws JwBlogException;

    /**
     * 废弃登录失败记录
     *
     * @param loginId 登录ID
     * @param ip      登录ip地址
     * @return
     * @author gulihua
     */
    void doVoidRecord(String loginId, String ip) throws JwBlogException;

}
