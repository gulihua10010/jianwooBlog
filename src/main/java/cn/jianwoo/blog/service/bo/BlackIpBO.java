package cn.jianwoo.blog.service.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class BlackIpBO implements Serializable {
    private static final long serialVersionUID = -7982293393643864916L;

    /**
     * 主键OID
     */
    private Long oid;

    /**
     * 访问IP
     */
    private String accessIp;

    /**
     * 创建时间
     */
    private Date createTime;
}