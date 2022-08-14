package cn.jianwoo.blog.service.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-07-10 18:29
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class MessageBoardMainPageListBO implements Serializable {
    private static final long serialVersionUID = 2157217103012138771L;
    /**
     * 总计数量
     */
    private Long count;
    /**
     * 留言集合
     */
    private List<MessageBoardBO> list;
    /**
     * 当前IP用户信息
     */
    private UserInfoBO userInfo;
    /**
     * 根留言记录数
     */
    private Long rootMsgCount;

}
