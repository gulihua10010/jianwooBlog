package cn.jianwoo.blog.service.param;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class AnnounceParam extends PageParam {
    private static final long serialVersionUID = 2478443514263410456L;
    private String title;

    /**
     * 状态: 00:有效,91:无效
     */
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
