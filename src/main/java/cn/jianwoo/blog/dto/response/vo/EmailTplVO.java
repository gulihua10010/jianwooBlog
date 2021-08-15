package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DomainUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class EmailTplVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130157L;

    /**
     * 文章标题
     */
    private String code;
    /**
     * 用户名
     */
    private String desc;
    /**
     * 评论日期
     */
    private String createDate;

}