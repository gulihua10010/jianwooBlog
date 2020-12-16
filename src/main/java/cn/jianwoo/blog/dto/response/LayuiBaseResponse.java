package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;

/**
 * @author GuLihua
 * @Description layui admin json数据返回格式<br/>
 * msg <br/>
 * code <br/>
 * data <br/>
 * --a <br/>
 * --b <br/>
 * @date 2020-10-12 17:28
 */
public class LayuiBaseResponse extends BaseResponseDto {
    public static final String SUCCESS_CODE = "0";
    private static final long serialVersionUID = 3369282587308977275L;
    /**
     * code<br/>
     * success --0
     */
    private String code;

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }
}
