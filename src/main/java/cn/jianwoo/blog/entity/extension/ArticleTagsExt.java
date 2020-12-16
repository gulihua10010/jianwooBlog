package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.Tags;

/**
 * @author gulihua
 */
public class ArticleTagsExt extends Tags {
    private static final long serialVersionUID = 3972884733531840706L;
    private Long artOid;

    public Long getArtOid() {
        return artOid;
    }


    public void setArtOid(Long artOid) {
        this.artOid = artOid;
    }

}
