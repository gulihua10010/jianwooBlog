package cn.jianwoo.blog.service.param;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class CommentParam extends PageParam {
    private static final long serialVersionUID = 7533112279053614145L;
    private String title;
    private String readStatus;

    public String getReadStatus() {
        return this.readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void processSortField(String field, String order) {
        this.setSortOrder(order);
        if ("artTitle".equals(field)) {
            this.setSortField("A.TITLE");
        } else if ("commentTimeDesc".equals(field)) {
            this.setSortField(" C.COMMENT_TIME");
        }
    }
}
