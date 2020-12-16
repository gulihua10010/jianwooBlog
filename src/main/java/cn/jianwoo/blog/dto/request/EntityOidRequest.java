package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:15
 */
public class EntityOidRequest extends BaseRequestDto {
    private static final long serialVersionUID = -7682284534958110646L;
    private Long entityOid;

    public Long getEntityOid() {
        return entityOid;
    }


    public void setEntityOid(Long entityOid) {
        this.entityOid = entityOid;
    }
}
