package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.VisitVO;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-26 14:53
 */
public class VisitResponse extends LayuiBaseResponse {
    private static final long serialVersionUID = 1970975993765800809L;
    private List<VisitVO> data;
    private Long count;

    public static VisitResponse getInstance() {
        return new VisitResponse();
    }


    public List<VisitVO> getData() {
        return this.data;
    }


    public void setData(List<VisitVO> data) {
        this.data = data;
    }


    public Long getCount() {
        return this.count;
    }


    public void setCount(Long count) {
        this.count = count;
    }
}