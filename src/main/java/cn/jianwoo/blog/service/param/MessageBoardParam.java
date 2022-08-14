package cn.jianwoo.blog.service.param;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class MessageBoardParam extends PageParam {
    private static final long serialVersionUID = 7533112279053614145L;
    private String readStatus;

    private Long oid;

    public Long getOid() {
        return this.oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getReadStatus() {
        return this.readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public void processSortField(String field, String order) {
        this.setSortOrder(order);
        if ("pushTimeDesc".equals(field)) {
            this.setSortField("M.PUSH_TIME");
        }
    }
}
