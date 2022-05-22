package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-17 09:33
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadBO implements Serializable {

    /**
     * 主键
     */
    private Long oid;

    /**
     * 文件上传UUID
     */
    private String fileUuid;

    /**
     * 重命名过后的文件名
     */
    private String fileName;

    /**
     * 旧的原始文件名
     */
    private String oldFileName;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件媒体信息
     */
    private String mediaInfo;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件网络url
     */
    private String url;

    /**
     * 文件cdn url
     */
    private String cdnUrl;

    /**
     * 文件md5
     */
    private String fileMd5;

    /**
     * 文件是否分片
     */
    private Boolean isChunk;

    /**
     * 文件是否删除
     */
    private Boolean isDelete;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
