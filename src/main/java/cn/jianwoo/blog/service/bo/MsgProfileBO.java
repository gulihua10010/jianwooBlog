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
public class MsgProfileBO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 主键
     */
    private Long oid;

    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 消息标题
     */
    private String msgTitle;
    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息链接
     */

    private String msgLink;
    /**
     * 业务主键
     */
    private String bizId;

    /**
     * 消息接收人
     */
    private String receiverId;

    /**
     * 消息接收名字
     */
    private String receiverName;

    /**
     * 消息接收邮件
     */
    private String receiverEmail;

    /**
     * 消息接收手机号
     */
    private String receiverMobileNo;

    /**
     * 是否邮件通知
     */
    private Boolean emailNotify;

    /**
     * 邮件模板编号
     */
    private String emailTplCode;

    /**
     * 是否已读
     */
    private Boolean flagRead;

    /**
     * 阅读时间
     */
    private Date readTime;

    /**
     * 处理结果
     */
    private String procRslt;

    /**
     * 处理时间
     */
    private Date procTime;

    /**
     * 消息发送时间
     */
    private Date sendTime;

}
