package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-01 14:32
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class WebconfGroupBO implements Serializable {
    private static final long serialVersionUID = -4417069949877654161L;
    private String tabNameDsp;

    private String tabCode;

    private Integer index;

    private List<WebconfBO> list;

}
