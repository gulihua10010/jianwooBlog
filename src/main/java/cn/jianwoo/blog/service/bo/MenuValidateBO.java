package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 19:45
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class MenuValidateBO implements Serializable {
    private static final long serialVersionUID = 3728070239002639495L;
    /**
     * 是否验证成功 Y or N
     */
    private String isSuccess;

    /**
     * 返回消息 isSuccess=N时必填
     */
    private String resultMsg;


}
