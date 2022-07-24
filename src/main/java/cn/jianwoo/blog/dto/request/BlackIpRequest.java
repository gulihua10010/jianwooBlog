package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BlackIpRequest extends BaseRequestDto {
    private static final long serialVersionUID = -7682284534958110646L;
    /**
     * IP
     */
    private String ip;
    /**
     * IP 集合
     */
    private List<String> ipList;
}
