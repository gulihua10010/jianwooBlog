package cn.jianwoo.blog.dto.response.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-18 15:53
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class FileUploadResVO implements Serializable {
    private static final long serialVersionUID = 4563971387655597400L;
    /**
     * 文件名
     */
    private String fileName;

//    private String path;

    /**
     * 文件 web url
     */
    private String url;

    /**
     * 原文件名
     */
    private String realFileName;

    /**
     *
     * 媒体信息（视频/音频）
     */
    private JSONObject mediaInfo;


}
