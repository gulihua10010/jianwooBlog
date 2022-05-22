package cn.jianwoo.blog.entity.query;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class AnnounceQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 2478443514263410456L;
    private String title;

    /**
     * 状态: 00:有效,91:无效
     */
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        if (StringUtils.isBlank(this.title)) {
            return null;
        }
        return String.format("%%%s%%", this.title);
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
