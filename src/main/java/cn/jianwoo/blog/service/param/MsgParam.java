package cn.jianwoo.blog.service.param;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class MsgParam extends PageParam {
    private static final long serialVersionUID = 7533112279053614145L;
    private String isRead;

    public String getIsRead() {
        return this.isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
