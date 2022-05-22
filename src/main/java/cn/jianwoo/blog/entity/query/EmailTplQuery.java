package cn.jianwoo.blog.entity.query;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class EmailTplQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 2478443514263410456L;
    private String code;
    private String desc;

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        if (StringUtils.isBlank(this.code)) {
            return null;
        }
        return String.format("%%%s%%", this.code);
    }

    public void setCode(String code) {
        this.code = code;
    }
}
