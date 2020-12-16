package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.ArticleVO;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 12:00
 */
public class ArticleResponse extends LayuiBaseResponse {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<ArticleVO> data;
    private Long count;

    public static ArticleResponse getInstance() {
        return new ArticleResponse();
    }


    public Long getCount() {
        return count;
    }


    public void setCount(Long count) {
        this.count = count;
    }


    public List<ArticleVO> getData() {
        return data;
    }


    public void setData(List<ArticleVO> data) {
        this.data = data;
    }
}
