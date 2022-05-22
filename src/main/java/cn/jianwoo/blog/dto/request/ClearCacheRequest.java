package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 14:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClearCacheRequest extends BaseRequestDto {
    private static final long serialVersionUID = -7558874574721985156L;
    /**
     * 临时目录
     */
    private Boolean temp;
    /**
     * 日志目录
     */
    private Boolean log;
    /**
     * 缓存目录
     */
    private Boolean cache;
    /**
     * 内存缓存:jwCacheStore
     */
    private Boolean memory;



}
