package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.BlackIpBO;
import cn.jianwoo.blog.service.param.BlackIpParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-06-30 11:29
 */
public interface BlackIpBizService {

    /**
     * 获取所有黑名单IP
     *
     * @param param 参数
     * @return PageInfo<EmailBO>
     * @author gulihua
     */
    PageInfo<BlackIpBO> queryAllIpBlackListPage(BlackIpParam param);

    /**
     * 添加黑名单
     *
     * @param ip IP
     * @author gulihua
     */
    void doAddBlackIp(String ip) throws JwBlogException;


    /**
     * 移除黑名单
     *
     * @param ip IP
     * @author gulihua
     */
    void doRemoveBlackIp(String ip) throws JwBlogException;


    /**
     * 批量移除黑名单
     *
     * @param ipList IP列表
     * @author gulihua
     */
    void doRemoveBlackIpList(List<String> ipList) throws JwBlogException;

    /**
     * 添加黑名单列表
     *
     * @param ipList IP 列表
     * @author gulihua
     */
    void doAddBlackIpList(List<String> ipList) throws JwBlogException;


}
