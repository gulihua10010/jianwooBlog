package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.AnnounceBO;
import cn.jianwoo.blog.service.param.AnnounceParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:22
 */
public interface AnnouncementBizService {

    /**
     * 获取所有公告
     *
     * @param param 参数
     * @return PageInfo<AnnounceBO>
     * @author gulihua
     */
    PageInfo<AnnounceBO> queryAllAnnouncePage(AnnounceParam param);

    /**
     * 添加公告
     *
     * @param param 参数
     * @author gulihua
     */
    void doCreateAnnounce(AnnounceBO param) throws JwBlogException;

    /**
     * 更新公告
     *
     * @param param 参数
     * @author gulihua
     */
    void doUpdateAnnounce(AnnounceBO param) throws JwBlogException;

    /**
     * 删除公告
     *
     * @param oid OID
     * @author gulihua
     */
    void doRemoveAnnounce(Long oid) throws JwBlogException;

    /**
     * 获取公告
     *
     * @param oid OID
     * @return EmailTplBO
     * @author gulihua
     */
    AnnounceBO queryAnnounceByOid(String oid) throws JwBlogException;


    /**
     * 作废公告
     *
     * @param oid OID
     * @author gulihua
     */
    void doVoidAnnounce(Long oid) throws JwBlogException;


    /**
     * 还原公告
     *
     * @param oid OID
     * @author gulihua
     */
    void doRevertAnnounce(Long oid) throws JwBlogException;


    /**
     * 作废多条公告
     *
     * @param oidList OID list
     * @author gulihua
     */
    void doVoidAnnounceList(List<Long> oidList) throws JwBlogException;


    /**
     * 还原多条公告
     *
     * @param oidList OID list
     * @author gulihua
     */
    void doRevertAnnounceList(List<Long> oidList) throws JwBlogException;


    /**
     * 删除多条公告
     *
     * @param oidList OID list
     * @author gulihua
     */
    void doRemoveAnnounceList(List<Long> oidList) throws JwBlogException;


    /**
     * 获取可用公告(最多4条)
     *
     * @return List<AnnounceBO>
     * @author gulihua
     */
    List<AnnounceBO> queryUsefulAnnounce();
}
