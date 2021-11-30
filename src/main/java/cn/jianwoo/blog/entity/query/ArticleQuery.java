package cn.jianwoo.blog.entity.query;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class ArticleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 7533112279053614145L;
    private List<String> statusParams;
    private String title;
    private String text;


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public List<String> getStatusParams() {
        return statusParams;
    }


    public void setStatusParams(List<String> statusParams) {
        this.statusParams = statusParams;
    }
}
