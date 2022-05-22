package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.ArticleAccess;

/**
 * @author gulihua
 */
public class AccessExt extends ArticleAccess {

    private static final long serialVersionUID = 7073044592460293237L;
    private String articleTitle;

    @Override
    public String getArticleTitle() {
        return this.articleTitle;
    }

    @Override
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
}
