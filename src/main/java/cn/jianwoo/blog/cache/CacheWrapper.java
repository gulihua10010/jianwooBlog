package cn.jianwoo.blog.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 18:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheWrapper<V> implements Serializable {

    /**
     * 缓存中的数据
     */
    private V data;

    /**
     * 是否设置过期
     */
    private Boolean isSetExpire = false;


    /**
     * 过期时间
     */
    private Date expireAt;

    /**
     * 创建时间
     */
    private Date createAt;
}
