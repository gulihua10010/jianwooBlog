package cn.jianwoo.blog.service.base;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-20 15:36
 */
public interface IpControlBaseService {

    /**
     * 插入一条IP访问记录
     *
     * @param ip  ip地址
     * @param url 访问的url
     * @return
     * @author gulihua
     */
    void doCreateRecord(String ip, String url);

    /**
     * IP是否在黑名单里
     *
     * @param ip ip地址
     * @return
     * @author gulihua
     */
    boolean isIpInBlackList(String ip);


    /**
     * 插入一条IP黑名单记录
     *
     * @param ip ip地址
     * @return
     * @author gulihua
     */
    void doCreateBlackRecord(String ip);

    /**
     * 移除一条IP黑名单记录
     *
     * @param ip ip地址
     * @return
     * @author gulihua
     */
    void doRemoveBlackRecord(String ip);
}
