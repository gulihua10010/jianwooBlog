package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.ArticleWithBLOBs;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class ArticleExt extends ArticleWithBLOBs {
    private static final long serialVersionUID = 7533112279053614145L;

    private String pushDateStr;
    private Integer count;
    private Long praiseOid;

    public Long getPraiseOid() {
        return this.praiseOid;
    }

    public void setPraiseOid(Long praiseOid) {
        this.praiseOid = praiseOid;
    }

    public String getPushDateStr() {
        return this.pushDateStr;
    }

    public void setPushDateStr(String pushDateStr) {
        this.pushDateStr = pushDateStr;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
