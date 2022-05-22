package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
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
public class AnnounceRequest  extends BaseRequestDto implements Serializable {
    private static final long serialVersionUID = -7982293393643864915L;

    /**
     * 主键OID
     */
    private Long oid;

    /**
     * 标题
     */
    private String title;
    /**
     * 发布人
     */
    private String pushBy;
    /**
     * 过期时间
     */
    private String expiationTime;
    /**
     * 状态: 00:有效,91:无效
     */
    private String status;
    /**
     * 内容
     */
    private String content;


}
