package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.ArticleWithBLOBs;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class ArticleExt extends ArticleWithBLOBs {
    private static final long serialVersionUID = 7533112279053614145L;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
