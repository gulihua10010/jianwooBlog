package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-18 15:52
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class VisitBO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 主键
     */
    private Long oid;

    /**
     * 访问IP
     */
    private String visitIp;

    /**
     * 访问地区
     */
    private String visitArea;

    /**
     * 访问文章oid
     */
    private Long articleOid;

    /**
     * 访问时间
     */
    private Date visitTime;

    /**
     * 访问文章的标题
     */
    private String articleTitle;

}