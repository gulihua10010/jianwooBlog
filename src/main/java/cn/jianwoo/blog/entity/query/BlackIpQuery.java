package cn.jianwoo.blog.entity.query;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class BlackIpQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 2478443514263410456L;

    /**
     * IP
     */
    private String ip;


    public String getIp() {
        if (StringUtils.isBlank(this.ip)) {
            return null;
        }
        return String.format("%%%s%%", this.ip);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
