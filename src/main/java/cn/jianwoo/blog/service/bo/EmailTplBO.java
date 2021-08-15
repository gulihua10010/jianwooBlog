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
 * @date 2021-07-18 0:56
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class EmailTplBO implements Serializable {
    private static final long serialVersionUID = -7982293393643864915L;
    private Long oid;
    private String code;
    private String content;
    private String subject;
    private String desc;
    private String jsonData;
    private Date createDate;
}
