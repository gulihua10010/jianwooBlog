package cn.jianwoo.blog.service.param;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class BlackIpParam extends PageParam {
    private static final long serialVersionUID = 2478443514263410456L;

    /**
     * IP
     */
    private String ip;

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
