package cn.jianwoo.blog.entity.query;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class CommentQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 7533112279053614145L;
    private String title;
    private String readStatus;

    private Long oid;

    public Long getOid() {
        return this.oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getReadStatus() {
        return this.readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
