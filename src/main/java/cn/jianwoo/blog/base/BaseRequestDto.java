package cn.jianwoo.blog.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-25 17:01
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequestDto implements Serializable {
    private static final long serialVersionUID = 2854392503356329692L;

    private String requestId;
    private String actor;
    private String clientIp;
    private String clientName;
    private String sessionId;
    private String access_token;
    private Date requestDate = new Date();

}
