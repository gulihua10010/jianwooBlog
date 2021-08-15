package cn.jianwoo.blog.dto.response.vo;

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
public class WebconfGroupVO implements Serializable {

    private static final long serialVersionUID = 7217525969848055174L;

    private String tabNameDsp;

    private String tabCode;

    private List<WebconfVO> list;


}
