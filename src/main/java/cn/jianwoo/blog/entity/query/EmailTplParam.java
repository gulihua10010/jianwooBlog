package cn.jianwoo.blog.entity.query;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class EmailTplParam extends PageParam {
    private static final long serialVersionUID = 2478443514263410456L;
    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
