package cn.jianwoo.blog.service.biz;

/**
 * @author GuLihua
 * @Description 网络服务
 * @date 2020-12-16 18:27
 */
public interface NetWorkService {
    /**
     * 根据ip获取地区域
     *
     * @param ip ip地址
     * @return
     * @author gulihua
     */
    String getIpArea(String ip);
}
