package cn.jianwoo.blog.service.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 21:51
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class UserBO implements Serializable {

    private Long id;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String nick;

    private List<String> roles;


}
