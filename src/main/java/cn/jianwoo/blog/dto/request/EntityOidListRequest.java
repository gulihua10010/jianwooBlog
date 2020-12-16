package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:15
 */
public class EntityOidListRequest extends BaseRequestDto {
    private static final long serialVersionUID = -7682284534958110646L;
    private List<Long> entityOidList;

    public List<Long> getEntityOidList() {
        return entityOidList;
    }


    public void setEntityOidList(List<Long> entityOidList) {
        this.entityOidList = entityOidList;
    }
}
