package cn.jianwoo.blog.entity;

import java.io.Serializable;

public class ArticleWithBLOBs extends Article implements Serializable {
    private String content;

    private String text;

    private static final long serialVersionUID = 1L;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", content=").append(content);
        sb.append(", text=").append(text);
        sb.append("]");
        return sb.toString();
    }
}