package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.Tags;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 11:29
 */
public class TagsExt extends Tags {
    private static final long serialVersionUID = 6995339458197709141L;
    private Long artOid;

    public Long getArtOid() {
        return artOid;
    }


    public void setArtOid(Long artOid) {
        this.artOid = artOid;
    }
}
