package cn.jianwoo.blog.entity.query;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class CommentParam extends PageParam {
    private static final long serialVersionUID = 7533112279053614145L;
    private String title;
    private Integer isRead;

    public Integer getIsRead() {
        return this.isRead;
    }


    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
