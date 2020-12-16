package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.Visit;

/**
 * @author gulihua
 */
public class VisitExt extends Visit {

    private static final long serialVersionUID = 7073044592460293237L;
    private String title;

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
