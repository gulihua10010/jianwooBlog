package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.MenuVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description 前台菜单响应
 * @date 2020-11-26 14:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfoResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;
    private MenuVO data;

    public static MenuInfoResponse getInstance() {
        return new MenuInfoResponse();
    }
}