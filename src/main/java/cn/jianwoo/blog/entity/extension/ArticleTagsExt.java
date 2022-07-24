package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.Tags;

/**
 * @author gulihua
 */
public class ArticleTagsExt extends Tags {
    private static final long serialVersionUID = 3972884733531840706L;
    /**
     * 文章oid
     */
    private Long artOid;
    /**
     * 文章数量
     */
    private Integer count;

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getArtOid() {
        return artOid;
    }


    public void setArtOid(Long artOid) {
        this.artOid = artOid;
    }

}
