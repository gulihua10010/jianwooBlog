package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-18 15:52
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class CacheBO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 临时文件大小
     */
    private Long tempSize;
    /**
     * 缓存文件大小
     */
    private Long cacheSize;
    /**
     * 日志文件大小
     */
    private Long logSize;

}
