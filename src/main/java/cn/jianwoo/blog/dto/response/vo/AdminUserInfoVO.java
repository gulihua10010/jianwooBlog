package cn.jianwoo.blog.dto.response.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-04 11:51
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInfoVO implements Serializable {
    private static final long serialVersionUID = 8360453789834911774L;

    private String nickName;
    private String userEmail;
    private String userSex;
}
